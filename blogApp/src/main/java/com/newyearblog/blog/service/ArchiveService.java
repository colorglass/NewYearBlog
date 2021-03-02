package com.newyearblog.blog.service;

import com.newyearblog.blog.entity.Blog;

import java.util.List;

public interface ArchiveService {

    List<Integer> getAllYear();

    List<Blog> getBlogByYearAndMonth(int year, int month);

}
