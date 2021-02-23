package com.newyearblog.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping("/index")
    public String indexPage(){
        return "admin/index";
    }

    @GetMapping("/tags")
    public String tagsPage(){
        return "admin/tags";
    }

    @GetMapping("/comments")
    public String commentsPage(){
        return "admin/comments";
    }


}
