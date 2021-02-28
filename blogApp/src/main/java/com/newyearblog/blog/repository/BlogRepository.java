package com.newyearblog.blog.repository;

import com.newyearblog.blog.entity.Blog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Long>{
    
}
