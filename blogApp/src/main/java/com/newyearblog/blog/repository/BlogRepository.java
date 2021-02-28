package com.newyearblog.blog.repository;

import com.newyearblog.blog.entity.Blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BlogRepository extends JpaRepository<Blog, Long>,JpaSpecificationExecutor<Blog> {
    
}
