package com.xzm.blog.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzm.blog.bean.Tag;
import com.xzm.blog.service.BlogService;
import com.xzm.blog.service.TagService;
import com.xzm.blog.constant.BlogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@RequestParam(value = "page", defaultValue = "1") int pageIndex, @PathVariable Integer id, Model model) {
        List<Tag> tags = tagService.selectTagTop(BlogConstant.SIZEALL);
        if (!tags.isEmpty() && id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute(BlogConstant.TAGS, tags);
        Page page = PageHelper.startPage(pageIndex, BlogConstant.PAGESIZE);
        model.addAttribute(BlogConstant.BLOGS, tagService.selectBlogByTagId(id));
        model.addAttribute(BlogConstant.PAGE, page);
        model.addAttribute(BlogConstant.ACTIVETAGID, id);
        return "tags";
    }
}
