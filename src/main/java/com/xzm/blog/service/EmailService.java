package com.xzm.blog.service;

import com.xzm.blog.bean.Comment;
/*
发送邮件
 */
public interface EmailService {
    void sendtoAdmin(Comment comment);
}
