package com.newyearblog.blog.service;

import com.newyearblog.blog.entity.Blog;

import java.util.List;
import java.util.Map;

public interface ArchiveService {

    List<Integer> getAllYear();

    List<Blog> getBlogByYearAndMonth(int year, int month);

    Map<String,List<Blog>> getArcBlogs(int year);

}
