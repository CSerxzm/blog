package com.xzm.blog.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzm.blog.bean.Tag;
import com.xzm.blog.service.TagService;
import com.xzm.blog.util.BlogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@RequestParam(value = "page", defaultValue = "1") int pageIndex, Model model) {
        Page page = PageHelper.startPage(pageIndex, BlogConstant.PAGESIZEADMIN);
        model.addAttribute(BlogConstant.TAGS, tagService.selectTag());
        model.addAttribute(BlogConstant.PAGE, page);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute(BlogConstant.ONETAG, new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Integer id, Model model) {
        model.addAttribute(BlogConstant.ONETAG, tagService.selectTag(id));
        return "admin/tags-input";
    }


    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        Tag tag1 = tagService.selectTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name", "nameError", "不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        int res = tagService.saveTag(tag);
        if (res == 0) {
            attributes.addFlashAttribute(BlogConstant.MESSAGE, "新增失败");
        } else {
            attributes.addFlashAttribute(BlogConstant.MESSAGE, "新增成功");
        }
        return "redirect:/admin/tags";
    }


    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable Integer id, RedirectAttributes attributes) {
        Tag tag1 = tagService.selectTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name", "nameError", "不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        int res = tagService.updateTag(id, tag);
        if (res == 0) {
            attributes.addFlashAttribute(BlogConstant.MESSAGE, "更新失败");
        } else {
            attributes.addFlashAttribute(BlogConstant.MESSAGE, "更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
        int res = tagService.deleteTag(id);
        if (res > 0) {
            attributes.addFlashAttribute(BlogConstant.MESSAGE, "删除成功");
        } else {
            attributes.addFlashAttribute(BlogConstant.MESSAGE, "删除失败");
        }
        return "redirect:/admin/tags";
    }


}
