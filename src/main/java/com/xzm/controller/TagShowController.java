package com.xzm.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzm.bean.Tag;
import com.xzm.service.BlogService;
import com.xzm.service.TagService;
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
    public String tags(@RequestParam(value="page",defaultValue = "1") int pageIndex, @PathVariable Integer id, Model model) {
        List<Tag> tags = tagService.selectTagTop(10000);
        if (id == -1) {
           id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        Page page = PageHelper.startPage(pageIndex, 5);
        model.addAttribute("blogs", tagService.selectBlogByTagId(id));
        model.addAttribute("page",page);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
