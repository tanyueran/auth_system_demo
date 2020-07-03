package com.github.tanyueran.auth_system_ssm.pojo;

import com.github.tanyueran.auth_system_ssm.entity.Button;
import com.github.tanyueran.auth_system_ssm.entity.Menu;

import java.util.List;

public class MenuPojo extends Menu {
    private List<Button> buttons;

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }
}
