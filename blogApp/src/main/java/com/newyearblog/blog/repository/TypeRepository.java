package com.newyearblog.blog.repository;

import com.newyearblog.blog.entity.Type;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByTypeName(String typeName);
}
