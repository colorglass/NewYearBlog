package com.newyearblog.blog.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String typeName;
    private Date createTime;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();

    @ManyToOne
    private User user;
}
