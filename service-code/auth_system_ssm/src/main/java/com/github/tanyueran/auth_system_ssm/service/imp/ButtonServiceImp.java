package com.github.tanyueran.auth_system_ssm.service.imp;

import com.github.tanyueran.auth_system_ssm.entity.Button;
import com.github.tanyueran.auth_system_ssm.mapper.ButtonMapper;
import com.github.tanyueran.auth_system_ssm.service.ButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(noRollbackFor = RuntimeException.class)
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
    public Boolean addBtn(Button button) throws Exception {
        Button btn = buttonMapper.queryButtonByCode(button);
        if (btn != null) {
            throw new Exception("按钮标识符已被使用，请更换");
        }
        return buttonMapper.addButton(button) == 1;
    }

    @Override
    public Boolean editBtn(Button button) throws Exception {
        Button btn = buttonMapper.queryButtonById(button);
        if (btn == null) {
            throw new Exception("需要更新的按钮不存在");
        } else if (btn.getButtonCode().equals(button.getButtonCode())) {
            return buttonMapper.updateButtonById(button) == 1;
        } else {
            Button btn2 = buttonMapper.queryButtonByCode(button);
            if (btn2 == null) {
                return buttonMapper.updateButtonById(button) == 1;
            }
            throw new Exception("按钮标识符已被使用，请更换");
        }
    }
}
