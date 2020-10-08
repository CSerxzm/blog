package com.xzm.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzm.bean.Type;
import com.xzm.service.BlogService;
import com.xzm.service.TypeService;
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
    public String types(@RequestParam(value="page",defaultValue = "1") int pageIndex, @PathVariable Integer id, Model model) {
        List<Type> types = typeService.selectTypeTop(10000);
        if ( ! types.isEmpty() && id == -1 ) {
           id = types.get(0).getId();
        }
        Page page = PageHelper.startPage(pageIndex, 5);
        model.addAttribute("blogs", blogService.selectBlogByTypeId(id));
        model.addAttribute("page",page);
        model.addAttribute("types", types);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
