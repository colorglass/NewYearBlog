package com.newyearblog.blog.controller;

import com.newyearblog.blog.service.ArchiveService;
import com.newyearblog.blog.service.TagService;
import com.newyearblog.blog.service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchiveController {
    @Autowired
    public ArchiveService archiveService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @RequestMapping("/archive")
    public String toArchive(Model model) {
        List<Integer> yearList = archiveService.getAllYear();
        if (!yearList.isEmpty()) {
            model.addAttribute("years", yearList);
            model.addAttribute("currentYear", yearList.get(0));
            model.addAttribute("arcBlogs", archiveService.getArcBlogs(yearList.get(0)));
        }
        // 搜索框用的
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("tags", tagService.getAllTags());
        return "archive";
    }

    @RequestMapping("archive/{year}")
    public String archiveByYear(@PathVariable("year") int year, Map<String, Object> map, Model model) {
        List<Integer> yearList = archiveService.getAllYear();
        if (!yearList.isEmpty()) {
            model.addAttribute("years", yearList);
            model.addAttribute("currentYear", year);
            model.addAttribute("arcBlogs", archiveService.getArcBlogs(year));
        }

        // 搜索框用的
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("tags", tagService.getAllTags());
        return "archive";
    }
}
