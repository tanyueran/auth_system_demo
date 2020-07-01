package com.github.tanyueran.auth_system.web.controller;

import com.github.tanyueran.auth_system.entity.Button;
import com.github.tanyueran.auth_system.entity.Menu;
import com.github.tanyueran.auth_system.pojo.LevelMenuPojo;
import com.github.tanyueran.auth_system.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/all")
    public List<Menu> getAllMenu() {
        return menuService.getAllMenu();
    }

    @GetMapping("/levelMenu/all")
    public List<LevelMenuPojo> getAllLevelMenu() {
        return menuService.getLevelMenu();
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delMenuById(@PathVariable("id") String id) throws Exception {
        return menuService.deleteMenuById(id);
    }

    @PostMapping("/add")
    public Boolean addMenu(@RequestBody Menu menu) throws Exception {
        return menuService.addMenu(menu);
    }

    @PutMapping("/edit")
    public Boolean editMenu(@RequestBody Menu menu) throws Exception {
        return menuService.editMenu(menu);
    }

    @ApiOperation("获取菜单下面挂载的按钮")
    @GetMapping("/btn/{id}")
    public List<Button> getButtonByMenuId(@PathVariable("id") String id) {
        return menuService.getButtonForMenuId(id);
    }

    @ApiOperation("菜单下的挂载的按钮更新")
    @PutMapping("/btn/update/{menuId}/{idList}")
    public Boolean updateButtonByMenuId(@PathVariable("menuId") String menuId, @PathVariable("idList") String idList) {
        if (idList.equals("null")) {
            return menuService.updateButtonForMenuId(menuId, new ArrayList<String>());
        }
        List<String> list = Arrays.asList(idList.split(","));
        return menuService.updateButtonForMenuId(menuId, list);
    }

    @ApiOperation("获取该用户下的菜单配置")
    @PostMapping("/levelMenu/roleIds")
    public List<LevelMenuPojo> getAllMenuOnThisUser(@RequestBody List<String> roleIds) throws Exception {
        if (roleIds == null || roleIds.size() == 0) {
            return new ArrayList<>();
        }
        return menuService.getMenuByRoleIds(roleIds);
    }
}
