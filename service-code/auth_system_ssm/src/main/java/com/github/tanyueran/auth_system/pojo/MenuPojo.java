package com.github.tanyueran.auth_system.pojo;

import com.github.tanyueran.auth_system.entity.Button;
import com.github.tanyueran.auth_system.entity.Menu;

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
