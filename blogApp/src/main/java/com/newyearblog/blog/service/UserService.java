package com.newyearblog.blog.service;

import com.newyearblog.blog.entity.User;

public interface UserService {
    User checkUser(String username,String password);
    
}
