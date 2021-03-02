package com.newyearblog.blog.repository;

import com.newyearblog.blog.entity.Blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>,JpaSpecificationExecutor<Blog> {
    @Query(value = "SELECT distinct year(update_time) from blog order by update_time DESC", nativeQuery = true)
    List<Integer> getAllYear();

    @Query(value = "SELECT * from blog where year(update_time) = ?", nativeQuery = true)
    List<Blog> getBlogByYear(int year);

    @Query(value = "SELECT * from blog inner join user " +
            "on user_id where year(update_time) = ? and month(update_time) = ? order by update_time ASC",
            nativeQuery = true)
    List<Blog> getBlogByYearAndMonth(int year, int month);
}
