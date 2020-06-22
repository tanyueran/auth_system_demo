package com.github.tanyueran.auth_system.service.imp;

import com.github.tanyueran.auth_system.entity.Button;
import com.github.tanyueran.auth_system.mapper.ButtonMapper;
import com.github.tanyueran.auth_system.service.ButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ButtonServiceImp implements ButtonService {

    @Autowired
    private ButtonMapper buttonMapper;

    @Override
    public List<Button> getAllBtn() {
        return buttonMapper.getAllBtn();
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return null:不可以删除，true删除成功，false，删除失败
     */
    @Override
    public Boolean delBtnById(String id) {
        Integer i = buttonMapper.canDelById(id);
        if (i != 0) {
            return null;
        }
        Integer j = buttonMapper.delButtonById(id);
        if (j == 1) return true;
        return false;
    }

    @Override
    public Boolean addBtn(Button button) {
        return buttonMapper.addButton(button) == 1;
    }

    @Override
    public Boolean editBtn(Button button) {
        return buttonMapper.updateButtonById(button) == 1;
    }
}
