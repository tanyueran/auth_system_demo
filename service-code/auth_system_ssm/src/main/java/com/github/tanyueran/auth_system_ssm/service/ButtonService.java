package com.github.tanyueran.auth_system_ssm.service;


import com.github.tanyueran.auth_system_ssm.entity.Button;

import java.util.List;

public interface ButtonService {
    List<Button> getAllBtn();

    Boolean delBtnById(String id);

    Boolean addBtn(Button button) throws Exception;

    Boolean editBtn(Button button) throws Exception;
}
