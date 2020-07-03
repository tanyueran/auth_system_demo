package com.github.tanyueran.auth_system_ssm.web.controller;

import com.github.tanyueran.auth_system_ssm.utils.IdBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/common")
@Api(value = "公用模块接口", tags = "公用模块接口", description = "公用模块接口")
public class CommonController {

    public static IdBuilder idBuilder = new IdBuilder(1, 1, 1);

    // 生成主键
    @GetMapping("/key/{num}")
    @ApiOperation("获取主键")
    public List<String> getPrimaryKey(@PathVariable("num") Integer num) {
        List<String> list = new ArrayList<>();
        if (num < 0) num = 1;
        for (int i = 0; i < num; i++) {
            list.add(String.valueOf(idBuilder.nextId()));
        }
        return list;
    }
}
