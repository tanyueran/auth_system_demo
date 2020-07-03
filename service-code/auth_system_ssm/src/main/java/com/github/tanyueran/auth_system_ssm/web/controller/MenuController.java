package com.github.tanyueran.auth_system_ssm.web.controller;

import com.github.tanyueran.auth_system_ssm.entity.Button;
import com.github.tanyueran.auth_system_ssm.entity.Menu;
import com.github.tanyueran.auth_system_ssm.pojo.LevelMenuPojo;
import com.github.tanyueran.auth_system_ssm.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/menu")
@Api(value = "菜单模块", tags = "菜单模块", description = "菜单模块")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/all")
    @ApiOperation("获取所有的菜单")
    public List<Menu> getAllMenu() {
        return menuService.getAllMenu();
    }

    @GetMapping("/levelMenu/all")
    @ApiOperation("按照层级获取菜单")
    public List<LevelMenuPojo> getAllLevelMenu() {
        return menuService.getLevelMenu();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("根据id删除菜单")
    public Boolean delMenuById(@PathVariable("id") String id) throws Exception {
        return menuService.deleteMenuById(id);
    }

    @PostMapping("/add")
    @ApiOperation("添加菜单")
    public Boolean addMenu(@RequestBody Menu menu) throws Exception {
        return menuService.addMenu(menu);
    }

    @PutMapping("/edit")
    @ApiOperation("编辑菜单")
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
