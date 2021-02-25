package com.newyearblog.blog.service;

import com.newyearblog.blog.entity.Type;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {
    
    public Type saveType(Type type);

    public Type deletType(Long id);

    public Type updateType(Long id,Type type);

    public Type getType(Long id);

    public Page<Type> listType(Pageable pageable);

    public Type getByTypeName(String typeName);
}
