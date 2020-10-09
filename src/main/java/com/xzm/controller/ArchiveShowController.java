package com.xzm.controller;

import com.xzm.service.BlogService;
import com.xzm.util.BlogConstant;
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
