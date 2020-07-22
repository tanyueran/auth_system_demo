package com.github.tanyueran.auth_system_springboot.controller;

import com.github.tanyueran.auth_system_springboot.utils.IdBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 公用的接口类
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private IdBuilder idBuilder;

    @GetMapping("/id/{num}")
    public List<String> getPrimaryKeys(@PathVariable("num") Integer num) {
        if (num < 0) num = 1;
        List<String> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            list.add(idBuilder.nextId() + "");
        }
        return list;
    }
}
