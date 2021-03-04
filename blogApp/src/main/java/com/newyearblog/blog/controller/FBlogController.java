package com.newyearblog.blog.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FBlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value = { "/index", "/" })
    public String indexPage(
            @PageableDefault(size = 6, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable, Model model,
            String title, Long typeId, Long tagId) {
        model.addAttribute("page", blogService.filteListBlog(title, typeId, tagId, true, pageable));
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("typeFilter", typeId);
        model.addAttribute("tagFilter", tagId);
        model.addAttribute("titleFilter", title);
        return "index";
    }

    @RequestMapping("/blog")
    public String blogPage(@RequestParam Long id, Model model) {
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("blog", blogService.getBlog(id));
        return "blog";
    }

    // 关于我页
    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("tags", tagService.getAllTags());
        return "about";
    }

}
