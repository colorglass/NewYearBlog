package com.newyearblog.blog.service;

import java.util.List;

import com.newyearblog.blog.entity.Type;
import com.newyearblog.blog.repository.TypeRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type saveType(Type type) {

        return typeRepository.save(type);
    }

    @Override
    public Type deletType(Long id) {
        Type type = null;
        if (typeRepository.existsById(id)) {
            type = typeRepository.findById(id).get();
            typeRepository.deleteById(id);
        }
        return type;
    }

    @Override
    public Type updateType(Long id, Type type) {
        Type _type = null;
        if(typeRepository.existsById(id)){
            _type = typeRepository.getOne(id);
            BeanUtils.copyProperties(type, _type);
        }
        return _type;
    }

    @Override
    public Type getType(Long id) {
        Type type = null;
        if (typeRepository.existsById(id)) {
            type = typeRepository.getOne(id);
        }
        return type;
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public Type getByTypeName(String typeName) {
        return typeRepository.findByTypeName(typeName);
    }

    @Override
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

}
