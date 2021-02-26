package com.newyearblog.blog.service;

import com.newyearblog.blog.entity.Blog;
import com.newyearblog.blog.repository.BlogRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog deleteBlog(Long id) {
        Blog blog = blogRepository.getOne(id);
        if (blog != null) {
            blogRepository.deleteById(id);
        }
        return blog;
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog _blog = blogRepository.getOne(id);
        if (_blog == null) {
            try {
                throw new NotFoundException("该博文不存在");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        BeanUtils.copyProperties(blog, _blog);
        return _blog;
    }

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

}
