package com.github.tanyueran.auth_system.web.controller;

import com.github.tanyueran.auth_system.utils.IdBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {

    public static IdBuilder idBuilder = new IdBuilder(1, 1, 1);

    // 生成主键
    @GetMapping("/key/{num}")
    public List<String> getPrimaryKey(@PathVariable("num") Integer num) {
        List<String> list = new ArrayList<>();
        if (num < 0) num = 1;
        for (int i = 0; i < num; i++) {
            list.add(String.valueOf(idBuilder.nextId()));
        }
        return list;
    }
}
