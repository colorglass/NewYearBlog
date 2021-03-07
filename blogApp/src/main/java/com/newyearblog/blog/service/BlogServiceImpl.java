package com.newyearblog.blog.service;

import java.util.List;

import com.newyearblog.blog.entity.Blog;
import com.newyearblog.blog.repository.BlogRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Blog blog = null;
        if (blogRepository.existsById(id)) {
            blog = blogRepository.findById(id).get();
            blogRepository.deleteById(id);
        }
        return blog;
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog _blog = null;
        if (blogRepository.existsById(id)) {
            _blog = blogRepository.getOne(id);
            BeanUtils.copyProperties(blog, _blog);
        }
        return _blog;
    }

    @Override
    public Blog getBlog(Long id) {
        Blog blog = null;
        if (blogRepository.existsById(id)) {
            blog = blogRepository.getOne(id);
        }
        return blog;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> filteListBlog(String title, Long typeId, Long tagId, Boolean publishedFilter, Pageable pageable) {
        return blogRepository.findAll(Blog.filter(title, typeId, tagId, publishedFilter), pageable);
    }

    @Override
    public List<Blog> findAll() {

        return blogRepository.findAll();
    }

}
