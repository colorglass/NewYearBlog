package com.newyearblog.blog.service;

import com.newyearblog.blog.entity.Blog;
import com.newyearblog.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    public Map<String, List<Blog>> getArcBlogs(int year) {
        Map<String, List<Blog>> blogMap = new LinkedHashMap<>();
        blogMap.put("十二月", getBlogByYearAndMonth(year, 12));
        blogMap.put("十一月", getBlogByYearAndMonth(year, 11));
        blogMap.put("十月", getBlogByYearAndMonth(year, 10));
        blogMap.put("九月", getBlogByYearAndMonth(year, 9));
        blogMap.put("八月", getBlogByYearAndMonth(year, 8));
        blogMap.put("七月", getBlogByYearAndMonth(year, 7));
        blogMap.put("六月", getBlogByYearAndMonth(year, 6));
        blogMap.put("五月", getBlogByYearAndMonth(year, 5));
        blogMap.put("四月", getBlogByYearAndMonth(year, 4));
        blogMap.put("三月", getBlogByYearAndMonth(year, 3));
        blogMap.put("二月", getBlogByYearAndMonth(year, 2));
        blogMap.put("一月", getBlogByYearAndMonth(year, 1));
        return blogMap;
    }
}
