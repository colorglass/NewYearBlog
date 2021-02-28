package com.newyearblog.blog.repository;

import com.newyearblog.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
    @Query(value = "SELECT * from comment", nativeQuery = true)
    List<Comment> findAll();

    @Query(value = "SELECT distinct year(create_time) from comment", nativeQuery = true)
    List<Integer> findAllYear();

    @Transactional
    int deleteCommentById(long id);
}
