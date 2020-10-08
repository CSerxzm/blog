package com.xzm.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzm.bean.Blog;
import com.xzm.bean.Tag;
import com.xzm.bean.User;
import com.xzm.service.BlogService;
import com.xzm.service.TagService;
import com.xzm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@RequestParam(value="page",defaultValue = "1") int pageIndex, Model model) {
        model.addAttribute("types", typeService.selectType());
        Page page = PageHelper.startPage(pageIndex, 10);
        model.addAttribute("blogs", blogService.selectBlogAdmin());
        model.addAttribute("page",page);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@RequestParam(value="title",required=false) String title,
                         @RequestParam(value="type_id",required=false) Integer type_id,
                         @RequestParam(value="recommend",required=false) boolean recommend,Model model) {
        List<Blog> blogs = blogService.selectByTitleTypeandRecommend(title,type_id,recommend);
        model.addAttribute("blogs", blogs);
        return "admin/blogs :: blogList";
    }


    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.selectType());
        model.addAttribute("tags", tagService.selectTag());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable int id, Model model) {
        model.addAttribute("types", typeService.selectType());
        model.addAttribute("tags", tagService.selectTag());
        Blog blog = blogService.selectBlog(id);
        List<Tag> tags = tagService.selectTagByBlogId(blog.getId());
        blog.setTagIds(tagsToIds(tags));
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }

    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return null;
        }
    }



    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        int b;
        if (blog.getId() == null) {
            User user =(User) session.getAttribute("user");
            blog.setUser(user);
            b = blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if (b == 0) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/blogs";
    }


    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Integer id,RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }



}
