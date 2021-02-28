package com.newyearblog.blog.controller.admin;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import com.newyearblog.blog.entity.Blog;
import com.newyearblog.blog.entity.User;
import com.newyearblog.blog.service.BlogService;
import com.newyearblog.blog.service.TagService;
import com.newyearblog.blog.service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/index")
    public String indexPage(
            @PageableDefault(size = 6, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable, Model model,
            String title, Long typeId, Long tagId) {
        model.addAttribute("page", blogService.filteListBlog(title, typeId, tagId, pageable));
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("typeFilter", typeId);
        model.addAttribute("tagFilter", tagId);
        model.addAttribute("titleFilter", title);
        model.addAttribute("totalBlogs", blogService.findAll().size());
        return "admin/index";
    }

    @GetMapping("/blog/new")
    public String newBlogPage(Model model) {
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("tags", tagService.getAllTags());

        if (model.getAttribute("blog") == null) {
            model.addAttribute("blog", new Blog());
        }

        return "admin/blog";
    }

    @PostMapping("/blog/save")
    public String saveBlog(@RequestParam Long typeId, String tagIds, Blog blog, HttpSession session,
            RedirectAttributes attributes) {
        if (blog.getTitle() == null) {
            attributes.addFlashAttribute("message", "标题或类型为空！");
            attributes.addFlashAttribute("blog", blog);
            return "redirect:/admin/blog/new";
        }

        blog.setType(typeService.getType(typeId));
        blog.setTags(tagService.getByIds(tagIds));
        blog.setUser((User) session.getAttribute("user"));
        blog.setUpdateTime(new Date(new java.util.Date().getTime()));

        if (blog.getId() == null) {
            blogService.saveBlog(blog);
            attributes.addFlashAttribute("message", "新建成功！");
        } else {
            blogService.updateBlog(blog.getId(), blog);
            attributes.addFlashAttribute("message", "修改成功！");
        }
        return "redirect:/admin/index";
    }

    @PostMapping("blog/del")
    public String delBlog(@RequestParam Long id, RedirectAttributes attributes) {

        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除操作成功！");
        return "redirect:/admin/index";
    }

    @GetMapping("blog/update")
    public String updatePage(@RequestParam Long id, Model model) {

        model.addAttribute("blog", blogService.getBlog(id));
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("tags", tagService.getAllTags());

        return "admin/blog";
    }

    @PostMapping("blog/publish")
    public String publishBlog(@RequestParam Long id, Model model) {
        Blog blog = blogService.getBlog(id);
        blog.setIsPublished(true);
        blogService.saveBlog(blog);
        System.out.println(blogService.getBlog(id).getIsPublished());
        return "redirect:/admin/index";
    }

}
