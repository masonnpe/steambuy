package com.steambuy.service;

import com.steambuy.mapper.UserMapper;
import com.steambuy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CheckService {
    @Resource
    UserMapper userMapper;

    public Boolean check(String data,String type){
        User user = new User();
        switch (type){
            case "username":
                user.setUsername(data);
                break;
            case "phone":
                user.setPhone(data);
                break;
            default:
                return null;
        }
        return userMapper.selectCount(user)==0;
    }
}
