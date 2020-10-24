package com.xzm.blog.controller;

import com.xzm.blog.bean.Comment;
import com.xzm.blog.bean.User;
import com.xzm.blog.service.EmailService;
import com.xzm.blog.service.CommentService;
import com.xzm.blog.util.BlogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


@Controller
public class CommentShowController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private EmailService emailService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Integer blogId, Model model) {
        model.addAttribute(BlogConstant.COMMENTS, commentService.selectCommentByBlogId(blogId));
        return "blog :: commentList";
    }


    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        System.out.println(comment);
        Integer blogId = comment.getBlogId();
        comment.setBlogId(blogId);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
            comment.setAdminComment(false);
        }
        commentService.saveComment(comment);
        emailService.sendtoAdmin(comment);
        return "redirect:/comments/" + blogId;
    }



}
