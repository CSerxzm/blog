package com.xzm.service;

import com.xzm.bean.Comment;
/*
发送邮件
 */
public interface EmailService {
    void sendtoAdmin(Comment comment);
}
