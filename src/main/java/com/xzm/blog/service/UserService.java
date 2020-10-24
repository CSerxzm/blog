package com.xzm.blog.service;

import com.xzm.blog.bean.User;

public interface UserService {

    User checkUser(String username, String password);

}
