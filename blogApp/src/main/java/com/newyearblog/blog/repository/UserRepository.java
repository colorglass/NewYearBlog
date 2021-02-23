package com.newyearblog.blog.repository;


import com.newyearblog.blog.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username,String password);
}
