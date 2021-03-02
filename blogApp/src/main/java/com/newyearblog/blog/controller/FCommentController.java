package com.newyearblog.blog.controller;

import java.sql.Date;

import com.newyearblog.blog.entity.Comment;
import com.newyearblog.blog.service.BlogService;
import com.newyearblog.blog.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comment")
public class FCommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @PostMapping("/save")
    public String savaComment(@RequestParam Long blogId, Comment comment, RedirectAttributes attributes) {
        if (comment.getContent() != null && comment.getEmail() != null && comment.getNickName() != null) {
            attributes.addFlashAttribute("message", "评论成功");

            Date createTime = new Date(new java.util.Date().getTime());
            comment.setCreateTime(createTime);
            comment.setBlog(blogService.getBlog(blogId));
            commentService.addComment(comment);
        } else {
            attributes.addFlashAttribute("message", "存在输入为空的值！");
        }

        attributes.addAttribute("id", blogId);
        return "redirect:/blog";
    }
}
