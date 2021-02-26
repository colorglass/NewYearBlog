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

import javassist.NotFoundException;

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
        Type type = typeRepository.getOne(id);
        typeRepository.deleteById(id);
        return type;
    }

    @Override
    public Type updateType(Long id, Type type) {
        Type _type = typeRepository.getOne(id);
        if (_type == null) {
            try {
                throw new NotFoundException("该分类不存在");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        BeanUtils.copyProperties(type, _type);
        return _type;
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
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
