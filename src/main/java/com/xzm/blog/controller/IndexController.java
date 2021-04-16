package com.xzm.blog.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzm.blog.service.BlogService;
import com.xzm.blog.service.TagService;
import com.xzm.blog.service.TypeService;
import com.xzm.blog.constant.BlogConstant;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@RequestParam(value = "page", defaultValue = "1") int pageIndex, Model model) {
        Page page = PageHelper.startPage(pageIndex, BlogConstant.PAGESIZE);
        model.addAttribute(BlogConstant.BLOGS, blogService.selectBlog());
        model.addAttribute(BlogConstant.PAGE, page);
        model.addAttribute(BlogConstant.TYPES, typeService.selectTypeTop(BlogConstant.SIZETOP));
        model.addAttribute(BlogConstant.TAGS, tagService.selectTagTop(BlogConstant.SIZETOP));
        model.addAttribute(BlogConstant.RECOMMENDBLOGS, blogService.selectRecommendBlogTop(BlogConstant.BLOGTOP));
        model.addAttribute(BlogConstant.HOTBLOGS, blogService.selectHotBlogTop(BlogConstant.BLOGTOP));
        return "index";
    }


    @RequestMapping("/search")
    public String search(@RequestParam(value = "page", defaultValue = "1") int pageIndex, @Param("query") String query, Model model, HttpSession session) {
        Page page = PageHelper.startPage(pageIndex, BlogConstant.PAGESIZE);
        if (query == null) {
            query = (String) session.getAttribute("query");
        } else {
            session.setAttribute("query", query);
        }
        model.addAttribute(BlogConstant.BLOGS, blogService.selectByTitlelike(query));
        model.addAttribute(BlogConstant.PAGE, page);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Integer id, Model model) {
        model.addAttribute(BlogConstant.ONEBLOG, blogService.selectAndConvert(id));
        model.addAttribute(BlogConstant.TAGS, tagService.selectTagByBlogId(id));
        return "blog";
    }

}
