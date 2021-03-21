package com.xzm.blog.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzm.blog.bean.Type;
import com.xzm.blog.service.BlogService;
import com.xzm.blog.service.TypeService;
import com.xzm.blog.util.BlogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(value = "page", defaultValue = "1") int pageIndex, @PathVariable Integer id, Model model) {
        List<Type> types = typeService.selectTypeTop(BlogConstant.SIZEALL);
        if (!types.isEmpty() && id == -1) {
            id = types.get(0).getId();
        }
        Page page = PageHelper.startPage(pageIndex, BlogConstant.PAGESIZE);
        model.addAttribute(BlogConstant.BLOGS, blogService.selectBlogByTypeId(id));
        model.addAttribute(BlogConstant.PAGE, page);
        model.addAttribute(BlogConstant.TYPES, types);
        model.addAttribute(BlogConstant.ACTIVETYPEID, id);
        return "types";
    }
}
