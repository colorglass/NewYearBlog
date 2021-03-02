package com.newyearblog.blog.service;

import com.newyearblog.blog.entity.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {



    Comment addComment(Comment comment);

    Page<Comment> queryCommentsPage(int page);

    Page<Comment> queryCommentsByDate(int year, int month, int page);

    List<Integer> findAllYear();

    //删除评论
    int deleteComment(Long id);

    int getCommentCount();
}
