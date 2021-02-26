package com.newyearblog.blog.repository;

import com.newyearblog.blog.entity.Tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByTagName(String tagName);
}
