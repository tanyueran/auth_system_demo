package com.github.tanyueran.auth_system.web.controller;

import com.github.tanyueran.auth_system.entity.Button;
import com.github.tanyueran.auth_system.service.ButtonService;
import com.github.tanyueran.auth_system.web.vo.MyResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/btn")
public class ButtonController {

    @Autowired
    private ButtonService buttonService;

    @GetMapping("/all")
    public List<Button> getAllButton() {
        return buttonService.getAllBtn();
    }

    @DeleteMapping("/{id}")
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
    public Boolean addButton(@RequestBody Button button) throws Exception {
        Boolean aBoolean = buttonService.addBtn(button);
        return aBoolean;
    }

    @PutMapping("/")
    public Boolean editButton(@RequestBody Button button) throws Exception {
        Boolean aBoolean = buttonService.editBtn(button);
        return aBoolean;
    }
}
