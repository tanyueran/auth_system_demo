package com.github.tanyueran.auth_system_ssm.mapper;

import com.github.tanyueran.auth_system_ssm.entity.Button;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ButtonMapper {

    // 查询所有的按钮
    List<Button> getAllBtn();

    // 删除按钮
    Integer delButtonById(String id);

    // 是否可以删除
    Integer canDelById(String id);

    // 新增按钮
    Integer addButton(Button button);

    // 编辑按钮
    Integer updateButtonById(Button button);

    // 查询button by code
    Button queryButtonByCode(Button button);

    // 根据id 查询Button
    Button queryButtonById(Button button);
}
