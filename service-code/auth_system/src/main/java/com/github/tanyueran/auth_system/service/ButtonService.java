package com.github.tanyueran.auth_system.service;


import com.github.tanyueran.auth_system.entity.Button;

import java.util.List;

public interface ButtonService {
    List<Button> getAllBtn();

    Boolean delBtnById(String id);

    Boolean addBtn(Button button) throws Exception;

    Boolean editBtn(Button button) throws Exception;
}
