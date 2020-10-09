package com.xzm.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzm.bean.Tag;
import com.xzm.service.CommentService;
import com.xzm.service.TagService;
import com.xzm.util.BlogConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public String tags(@RequestParam(value="page",defaultValue = "1") int pageIndex,Model model) {
        Page page = PageHelper.startPage(pageIndex,BlogConstant.PAGESIZEADMIN);
        model.addAttribute(BlogConstant.COMMENTS,commentService.selectAll());
        model.addAttribute(BlogConstant.PAGE,page);
        return "admin/comments";
    }

    @GetMapping("/comments/{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes attributes) {
        int res = commentService.deleteByPrimaryKey(id);
        if(res>0){
            attributes.addFlashAttribute(BlogConstant.MESSAGE, "删除成功");
        }else{
            attributes.addFlashAttribute(BlogConstant.MESSAGE, "删除失败");
        }
        return "redirect:/admin/comments";
    }


}
