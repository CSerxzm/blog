package com.xzm.blog.service;

import com.xzm.blog.bean.User;
import com.xzm.blog.dao.UserMapper;
import com.xzm.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        password = MD5Utils.code(password);
        User user = userMapper.checkUser(username, password);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }
}
