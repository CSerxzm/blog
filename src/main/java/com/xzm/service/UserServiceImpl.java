package com.xzm.service;

import com.xzm.bean.User;
import com.xzm.dao.UserMapper;
import com.xzm.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        password=MD5Utils.code(password);
        User user = userMapper.checkUser(username,password);
        if(user==null){
            return null;
        }
        else{
            return user;
        }
    }
}
