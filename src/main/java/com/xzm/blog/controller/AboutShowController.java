package com.xzm.blog.controller;

import com.xzm.blog.service.TagService;
import com.xzm.blog.service.TypeService;
import com.xzm.blog.util.BlogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AboutShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute(BlogConstant.TAGS, tagService.selectTagTop(BlogConstant.SIZETOP));
        model.addAttribute(BlogConstant.TYPES, typeService.selectTypeTop(BlogConstant.SIZETOP));
        return "about";
    }
}
