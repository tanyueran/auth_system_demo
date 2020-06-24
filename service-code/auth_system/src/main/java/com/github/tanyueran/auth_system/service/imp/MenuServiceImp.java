package com.github.tanyueran.auth_system.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.tanyueran.auth_system.entity.Button;
import com.github.tanyueran.auth_system.entity.Menu;
import com.github.tanyueran.auth_system.mapper.MenuMapper;
import com.github.tanyueran.auth_system.pojo.LevelMenuPojo;
import com.github.tanyueran.auth_system.service.MenuService;
import com.github.tanyueran.auth_system.web.controller.CommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Transactional
public class MenuServiceImp implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenu();
    }

    @Override
    public List<LevelMenuPojo> getLevelMenu() {
        List<Menu> menus = menuMapper.getAllMenu();
        Map<String, LevelMenuPojo> map = new HashMap<>();

        menus.forEach(item -> {
            if (StringUtils.isEmpty(item.getPid())) {
                LevelMenuPojo levelMenuPojo = JSON.parseObject(JSONObject.toJSONString(item), LevelMenuPojo.class);
                levelMenuPojo.setChildren(new ArrayList<>());
                map.put(item.getId(), levelMenuPojo);
            }
        });
        menus.forEach(item -> {
            if (!StringUtils.isEmpty(item.getPid())) {
                map.get(item.getPid()).getChildren().add(item);
            }
        });

        List<LevelMenuPojo> list = new ArrayList<>();
        Collection<LevelMenuPojo> values = map.values();
        Iterator<LevelMenuPojo> iterator = values.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    @Override
    public Boolean deleteMenuById(String id) throws Exception {
        Boolean b = false;
        // 先根据id查询按钮
        Menu menu = menuMapper.queryMenuById(id);
        if (menu == null) {
            throw new Exception("被删除的菜单不存在");
        }
        // 1、一级菜单需要判断是否有子集，
        //      没有，直接删除
        //      有，不可以删除
        // 2、 二级菜单，需要判断是否有角色引用的，
        //       没有，删除菜单，并删除 菜单-按钮 的中间表数据
        //       有，不可以删除
        if (StringUtils.isEmpty(menu.getPid())) {// 一级菜单
            List<Menu> menus = menuMapper.queryMenuChildrenById(id);
            if (menus == null || menus.size() == 0) {
                Integer i = menuMapper.deleteMenuById(id);
                if (i > 0) {
                    b = true;
                }
            } else {
                throw new Exception("当前菜单下有二级菜单，不可删除");
            }
        } else {// 二级菜单
            Integer i = menuMapper.checkMenuForRoleById(id);
            if (i > 0) {
                throw new Exception("该菜单被角色使用着，不可删除");
            } else {
                menuMapper.deleteMenuById(id);
                menuMapper.deleteMenuButtonByMenuId(id);
                b = true;
            }
        }
        return b;
    }

    @Override
    public Boolean addMenu(Menu menu) throws Exception {
        Boolean b = false;
        // 0、查询菜单code 是否可以使用
        // 1、一级菜单
        //      直接添加即可
        // 2、 二级菜单
        //      需要 在 菜单-按钮 的中间表添加增删改查的基本配置
        Menu menu1 = menuMapper.queryMenuByCode(menu.getMenuCode());
        if (menu1 != null) {
            throw new Exception("菜单标识已经被使用了，请更换");
        }
        if (StringUtils.isEmpty(menu.getPid()) && menu.getMenuType().equals("0")) {// 一级菜单
            Integer i = menuMapper.addMenu(menu);
            if (i > 0) {
                b = true;
            }
        } else {// 二级菜单
            Integer i = menuMapper.addMenu(menu);
            List<Map<String, String>> list = new ArrayList<>();
            for (int u = 0; u < 4; u++) {
                Map<String, String> m = new HashMap<>();
                m.put("id", String.valueOf(CommonController.idBuilder.nextId()));
                m.put("buttonId", (u + 1) + "");
                m.put("menuId", menu.getId());
                list.add(m);
            }
            Integer j = menuMapper.addBasicButtonForMenuByMenuId(list);
            if (i > 0 && j > 0) {
                b = true;
            }
        }
        return b;
    }

    @Override
    public Boolean editMenu(Menu menu) throws Exception {
        boolean b = false;
        // 注意：只能修改名称、编码、其他、备注（不允许修改类型）
        // 1、先用id查询出原来的东西，
        Menu oldMenu = menuMapper.queryMenuById(menu.getId());
        // 2、匹对code值是否变化
        //      没变，则直接修改
        //      变了，判断是否这个code，是否可以使用，
        if (oldMenu.getMenuCode().equals(menu.getMenuCode())) {
            Integer i = menuMapper.updateMenuById(menu);
            if (i > 0) {
                b = true;
            }
        } else {
            Menu menu1 = menuMapper.queryMenuByCode(menu.getMenuCode());
            if (menu1 != null) {
                throw new Exception("菜单标识已经被使用了，请更换");
            } else {
                Integer j = menuMapper.updateMenuById(menu);
                if (j > 0) {
                    b = true;
                }
            }
        }
        return b;
    }

    @Override
    public List<Button> getButtonForMenuId(String menuId) {
        return menuMapper.queryButtonByMenuId(menuId);
    }

    @Override
    public Boolean updateButtonForMenuId(String menuId, List<String> buttonIdList) {
        // 先删除以前存在的按钮
        Integer i = menuMapper.deleteMenuButtonByMenuId(menuId);
        // 在插入新的按钮
        List<Map<String, String>> list = new ArrayList<>();
        buttonIdList.forEach(item -> {
            Map<String, String> map = new HashMap();
            map.put("id", String.valueOf(CommonController.idBuilder.nextId()));
            map.put("menuId", menuId);
            map.put("buttonId", item);
            list.add(map);
        });
        Integer j = menuMapper.addButtonByMenuId(list);
        if (i >= 0 && j > 0) {
            return true;
        }
        return false;
    }
}
