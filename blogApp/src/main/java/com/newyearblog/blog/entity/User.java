package com.newyearblog.blog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;


@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Type> types = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Tag> tags = new ArrayList<>();
}
