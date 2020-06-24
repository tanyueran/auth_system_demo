package com.github.tanyueran.auth_system.web.controller;

import com.github.tanyueran.auth_system.entity.Button;
import com.github.tanyueran.auth_system.entity.Menu;
import com.github.tanyueran.auth_system.pojo.LevelMenuPojo;
import com.github.tanyueran.auth_system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public Boolean delMenuById(@PathVariable("id") String id) throws Exception {
        return menuService.deleteMenuById(id);
    }

    @PostMapping("/")
    public Boolean addMenu(@RequestBody Menu menu) throws Exception {
        return menuService.addMenu(menu);
    }

    @PutMapping("/")
    public Boolean editMenu(@RequestBody Menu menu) throws Exception {
        return menuService.editMenu(menu);
    }

    // 获取菜单下面挂载的按钮
    @GetMapping("/btn/{id}")
    public List<Button> getButtonByMenuId(@PathVariable("id") String id) {
        return menuService.getButtonForMenuId(id);
    }

    // 菜单下的挂载的按钮更新
    @PutMapping("/btn/update/{menuId}/{idList}")
    public Boolean updateButtonByMenuId(@PathVariable("menuId") String menuId, @PathVariable("idList") String idList) {
        List<String> list = Arrays.asList(idList.split(","));
        return menuService.updateButtonForMenuId(menuId, list);
    }
}
