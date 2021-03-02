package com.newyearblog.blog.repository;

import com.newyearblog.blog.entity.Blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>,JpaSpecificationExecutor<Blog> {
    @Query(value = "SELECT  year(update_time) from blog ORDER BY update_time DESC", nativeQuery = true)
    List<Integer> getAllYear();

    @Query(value = "SELECT * from blog inner join user " +
            "on user_id where year(update_time) = ? and month(update_time) = ? ORDER BY update_time DESC",
            nativeQuery = true)
    List<Blog> getBlogByYearAndMonth(int year, int month);
}
