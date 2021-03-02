package com.newyearblog.blog.controller;

import com.newyearblog.blog.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FBlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/index")
    public String indexPage(
            @PageableDefault(size = 6, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {
        model.addAttribute("page", blogService.filteListBlog(null, null, null, true, pageable));
        return "index";
    }

    @RequestMapping("/blog")
    public String blogPage(@RequestParam Long id, Model model) {
        model.addAttribute("blog", blogService.getBlog(id));
        return "blog";
    }
    
}
