package com.newyearblog.blog.service;

import com.newyearblog.blog.entity.Blog;
import com.newyearblog.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArchiveServiceImpl implements ArchiveService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Integer> getAllYear() {
        List<Integer> yearList = blogRepository.getAllYear().stream().distinct().collect(Collectors.toList());
        return yearList;
    }

    @Override
    public List<Blog> getBlogByYearAndMonth(int year, int month) {
        return blogRepository.getBlogByYearAndMonth(year, month);
    }
}
