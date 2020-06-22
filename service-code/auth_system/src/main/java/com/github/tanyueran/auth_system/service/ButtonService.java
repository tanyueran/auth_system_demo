package com.github.tanyueran.auth_system.service;


import com.github.tanyueran.auth_system.entity.Button;

import java.util.List;

public interface ButtonService {
    List<Button> getAllBtn();

    Boolean delBtnById(String id);

    Boolean addBtn(Button button);

    Boolean editBtn(Button button);
}
