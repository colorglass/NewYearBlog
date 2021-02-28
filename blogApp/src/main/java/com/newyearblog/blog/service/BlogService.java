package com.newyearblog.blog.service;

import com.newyearblog.blog.entity.Blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Blog saveBlog(Blog blog);

    Blog deleteBlog(Long id);

    Blog updateBlog(Long id,Blog blog);

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable);
}
