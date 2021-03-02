package com.newyearblog.blog.controller;

import com.newyearblog.blog.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class ArchiveController {
    @Autowired
    public ArchiveService archiveService;

    @RequestMapping("/archive")
    public String toArchive(Map<String, Object> map){
        handleYear(map);
        int year = archiveService.getAllYear().get(0);
        handleBlog(map, year);
        return "archive";
    }

    @RequestMapping("archive/{year}")
    public String archiveByYear(@PathVariable("year") int year, Map<String, Object> map){
        handleYear(map);
        handleBlog(map, year);
        return "archive";
    }

    private void handleYear(Map<String, Object> map){
        map.put("years", archiveService.getAllYear());
    }

    private void handleBlog(Map<String, Object> map, int year){
        map.put("currentYear", year);
        map.put("blogOne", archiveService.getBlogByYearAndMonth(year, 1));
        map.put("blogTwo", archiveService.getBlogByYearAndMonth(year, 2));
        map.put("blogThree", archiveService.getBlogByYearAndMonth(year, 3));
        map.put("blogFour", archiveService.getBlogByYearAndMonth(year, 4));
        map.put("blogFive", archiveService.getBlogByYearAndMonth(year, 5));
        map.put("blogSix", archiveService.getBlogByYearAndMonth(year, 6));
        map.put("blogSeven", archiveService.getBlogByYearAndMonth(year, 7));
        map.put("blogEight", archiveService.getBlogByYearAndMonth(year, 8));
        map.put("blogNine", archiveService.getBlogByYearAndMonth(year, 9));
        map.put("blogTen", archiveService.getBlogByYearAndMonth(year, 10));
        map.put("blogEleven", archiveService.getBlogByYearAndMonth(year, 11));
        map.put("blogTwelve", archiveService.getBlogByYearAndMonth(year, 12));
    }
}
