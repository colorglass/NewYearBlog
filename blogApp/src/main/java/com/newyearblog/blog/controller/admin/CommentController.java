package com.newyearblog.blog.controller.admin;


import com.newyearblog.blog.entity.Comment;
import com.newyearblog.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class CommentController {
    @Autowired
    CommentService commentService;


    @GetMapping("/comments")
    public String toCommentsPage(Map<String, Object> map){
        Page<Comment> comments = commentService.queryCommentsPage(0);
        handlePaging(map, comments);
        return "admin/comments";
    }


    @PostMapping("/comments")
    public String commentsPage(@RequestParam(value = "year", required = false)int year, @RequestParam(value = "month", required = false)int month,
                               Map<String, Object> map){
        Page<Comment> comments = commentService.queryCommentsByDate(year, month, 0);
        handlePaging(map, comments);
        map.put("chooseYear", year);
        map.put("chooseMonth", month);
        return "admin/comments";
    }


    @GetMapping("/comments/delete/{id}")
    public void deleteComment(@PathVariable("id") long id, HttpServletResponse response){
        commentService.deleteComment(id);
        try {
            response.sendRedirect("/admin/comments");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/comments/page/{page}")
    public String pageComment(@PathVariable("page") int page, @RequestParam("year")int year, @RequestParam("month")int month,
                              Map<String, Object> map){
        Page<Comment> comments = commentService.queryCommentsByDate(year, month, page-1);
        handlePaging(map, comments);
        map.put("chooseYear", year);
        map.put("chooseMonth", month);
        return "admin/comments";
    }


    private void handlePaging(Map<String, Object> map, Page<Comment> comments){
        map.put("years", commentService.findAllYear());

        map.put("commentCount", commentService.getCommentCount());
        map.put("comments", comments.getContent());
        ArrayList<Integer> pageCount = new ArrayList<>();
        for (int i = 1; i <= comments.getTotalPages(); i++) {
            pageCount.add(i);
        }

        map.put("pageCount", pageCount);
        map.put("totalPage", comments.getTotalPages());
        map.put("currentPage", comments.getNumber() + 1);
    }
}
