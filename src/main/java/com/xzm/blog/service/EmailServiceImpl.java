package com.xzm.blog.service;

import com.xzm.blog.bean.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    JavaMailSender javaMailSender;

    @Async
    @Override
    public void sendtoAdmin(Comment comment) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);
            //邮件设置
            helper.setSubject("Blog通知");
            helper.setText("<div><h3>亲爱的主人：</h3>" +
                    "<h3>您好！您的博客(没有代码的程序)有新的评论来到，详情点击" +
                    "<a href='https://fuyuanplant.cn/blog/" + comment.getBlogId() + "'>评论详情</a>查看。</h3>" +
                    "</br></br><h3>评论预览</h3>" +
                    "<div><h4>评论者:</h4>" + comment.getNickname() + "(" + comment.getEmail() + ")</br></br>" +
                    "<h4>评论内容：</h4>" + comment.getContent() + "</div></div>", true);
            helper.setTo("3052720966@qq.com");
            helper.setFrom("3052720966@qq.com");
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
