package com.github.tanyueran.auth_system_ssm.web.controller;

import com.github.tanyueran.auth_system_ssm.entity.Button;
import com.github.tanyueran.auth_system_ssm.service.ButtonService;
import com.github.tanyueran.auth_system_ssm.web.vo.MyResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/btn")
@Api(value = "按钮模块", tags = "按钮模块", description = "按钮模块")
public class ButtonController {

    @Autowired
    private ButtonService buttonService;

    @GetMapping("/all")
    @ApiOperation("请求所有的按钮")
    public List<Button> getAllButton() {
        return buttonService.getAllBtn();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除按钮")
    public MyResponseBody delButtonById(@PathVariable("id") String id) {
        Boolean b = buttonService.delBtnById(id);
        if (b == null) {
            MyResponseBody body = new MyResponseBody();
            body.setCode(1);
            body.setMsg("该按钮在其他地方引用，不可以删除");
            body.setData(false);
            return body;
        } else {
            return MyResponseBody.success(true, "ok");
        }
    }

    @PostMapping("/")
    @ApiOperation("添加按钮")
    public Boolean addButton(@RequestBody Button button) throws Exception {
        Boolean aBoolean = buttonService.addBtn(button);
        return aBoolean;
    }

    @PutMapping("/")
    @ApiOperation("更新按钮")
    public Boolean editButton(@RequestBody Button button) throws Exception {
        Boolean aBoolean = buttonService.editBtn(button);
        return aBoolean;
    }

}
