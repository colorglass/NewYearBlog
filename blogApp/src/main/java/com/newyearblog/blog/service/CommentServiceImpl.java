package com.newyearblog.blog.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import com.newyearblog.blog.entity.Comment;
import com.newyearblog.blog.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<Comment> queryCommentsPage(int page) {

        Page<Comment> comments = commentRepository.findAll(PageRequest.of(page, 10));
        return comments;
    }

    @Override
    public Page<Comment> queryCommentsByDate(int year, int month, int page) {
        Page<Comment> comments = commentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (year != 0) {
                Predicate predicate = criteriaBuilder
                        .equal(criteriaBuilder.function("year", Integer.class, root.get("createTime")), year);
                predicates.add(predicate);
            }
            if (month != 0) {
                Predicate predicate = criteriaBuilder
                        .equal(criteriaBuilder.function("month", Integer.class, root.get("createTime")), month);
                predicates.add(predicate);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, PageRequest.of(page, 10));
        return comments;
    }

    @Override
    public List<Integer> findAllYear() {
        return commentRepository.findAllYear();
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public int deleteComment(Long id) {
        return commentRepository.deleteCommentById(id);
    }

    @Override
    public int getCommentCount() {
        List<Comment> comments = commentRepository.findAll();
        return comments.size();
    }

}
