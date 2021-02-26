package com.newyearblog.blog.controller.admin;

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
            @PageableDefault(size = 1, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("tags", tagService.getAllTags());
        return "admin/index";
    }

    @GetMapping("/blog/new")
    public String newBlogPage() {
        return "admin/blog";
    }

}
