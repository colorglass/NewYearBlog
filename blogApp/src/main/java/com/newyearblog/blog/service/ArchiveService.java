package com.newyearblog.blog.service;

import com.newyearblog.blog.entity.Blog;
import com.newyearblog.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ArchiveService {

    List<Integer> getAllYear();

    List<Blog> getBlogByYear(int year);

    List<Blog> getBlogByYearAndMonth(int year, int month);

}
