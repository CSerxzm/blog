package com.xzm.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzm.bean.Type;
import com.xzm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;



@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@RequestParam(value="page",defaultValue = "1") int pageIndex, Model model) {
        Page page = PageHelper.startPage(pageIndex,10);
        model.addAttribute("types",typeService.selectType());
        model.addAttribute("page",page);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Integer id, Model model) {
        model.addAttribute("type", typeService.selectType(id));
        return "admin/types-input";
    }


    @PostMapping("/types")
    public String post(@Valid Type type,BindingResult result, RedirectAttributes attributes) {
        Type type1 = typeService.selectTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        int res = typeService.saveType(type);
        if (res==0) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }


    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Integer id, RedirectAttributes attributes) {
        Type type1 = typeService.selectTypeByName(type.getName());
        if (type1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/types-input";
        }
        int res = typeService.updateType(id,type);
        if (res==0) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes attributes) {
        int res = typeService.deleteType(id);
        if(res>0){
            attributes.addFlashAttribute("message", "删除成功");
        }else{
            attributes.addFlashAttribute("message", "删除失败");
        }
        return "redirect:/admin/types";
    }


}
