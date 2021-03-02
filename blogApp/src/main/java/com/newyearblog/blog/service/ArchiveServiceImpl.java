package com.newyearblog.blog.service;

import com.newyearblog.blog.entity.Blog;
import com.newyearblog.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private BlogRepository blogRepository;


    @Override
    public List<Integer> getAllYear() {
        return blogRepository.getAllYear();
    }

    @Override
    public List<Blog> getBlogByYear(int year) {
        return blogRepository.getBlogByYear(year);
    }

    @Override
    public List<Blog> getBlogByYearAndMonth(int year, int month) {
        return blogRepository.getBlogByYearAndMonth(year, month);
    }
}
