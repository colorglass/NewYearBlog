package com.newyearblog.blog.service;

import java.util.ArrayList;
import java.util.List;

import com.newyearblog.blog.entity.Blog;
import com.newyearblog.blog.entity.Tag;
import com.newyearblog.blog.repository.BlogRepository;
import com.newyearblog.blog.repository.TagRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag deletTag(Long id) {
        Tag tag = null;
        if (tagRepository.existsById(id)) {
            tag = tagRepository.findById(id).get();
            List<Blog> blogs = blogRepository.findAll(Blog.filter(null, null, tag.getId(), false));
            for(Blog blog : blogs){
                List<Tag> tagList = blog.getTags();
                tagList.remove(tag);
                blog.setTags(tagList);
                blogRepository.save(blog);
            }
            tagRepository.deleteById(id);
        }
        return tag;
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag _tag = null;
        if(tagRepository.existsById(id)){
            _tag = tagRepository.getOne(id);
            BeanUtils.copyProperties(tag, _tag);
        }
        return _tag;
    }

    @Override
    public Tag getTag(Long id) {
        Tag tag = null;
        if (tagRepository.existsById(id)) {
            tag = tagRepository.getOne(id);
        }
        return tag;
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {

        return tagRepository.findAll(pageable);

    }

    @Override
    public Tag getByTagName(String tagName) {
        return tagRepository.findByTagName(tagName);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> getByIds(String ids) {
        return tagRepository.findAllById(stringToList(ids));
    }

    private List<Long> stringToList(String str) {
        List<Long> list = new ArrayList<>();
        if (!("".equals(str)) && str != null) {
            String[] strArray = str.split(",");
            for (int i = 0; i < strArray.length; i++) {
                list.add(Long.valueOf(strArray[i]));
            }
        }
        
        return list;
    }

}
