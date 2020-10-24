package com.xzm.blog.controller;

import com.xzm.blog.service.BlogService;
import com.xzm.blog.util.BlogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute(BlogConstant.ARCHIVEMAP, blogService.archiveBlog());
        model.addAttribute(BlogConstant.BLOGCOUNT, blogService.countBlog());
        return "archives";
    }
}
