package com.newyearblog.blog.service;

import java.util.List;

import com.newyearblog.blog.entity.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {
    
    public Tag saveTag(Tag tag);

    public Tag deletTag(Long id);

    public Tag updateTag(Long id,Tag tag);

    public Tag getTag(Long id);

    public Page<Tag> listTag(Pageable pageable);

    public Tag getByTagName(String tagName);

    public List<Tag> getAllTags();
}
