package com.xzm.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzm.service.BlogService;
import com.xzm.service.TagService;
import com.xzm.service.TypeService;
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
    public String index(@RequestParam(value="page",defaultValue = "1") int pageIndex,Model model) {
        Page page = PageHelper.startPage(pageIndex, 5);
        model.addAttribute("blogs",blogService.selectBlog());
        model.addAttribute("page",page);
        page=null;
        model.addAttribute("types", typeService.selectTypeTop(8));
        model.addAttribute("tags", tagService.selectTagTop(8));
        model.addAttribute("recommendBlogs", blogService.selectRecommendBlogTop(5));
        model.addAttribute("hotBlogs", blogService.selectHotBlogTop(5));
        return "index";
    }


    @RequestMapping("/search")
    public String search(@RequestParam(value="page",defaultValue = "1") int pageIndex, @Param("query") String query, Model model, HttpSession session) {
        Page page = PageHelper.startPage(pageIndex,5);
        if(query==null){
            query = (String) session.getAttribute("query");
        }else{
            session.setAttribute("query",query);
        }
        model.addAttribute("blogs", blogService.selectByTitlelike(query));
        model.addAttribute("page", page);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Integer id,Model model) {
        model.addAttribute("blog", blogService.selectAndConvert(id));
        model.addAttribute("tags",tagService.selectTagByBlogId(id));
        return "blog";
    }

}
