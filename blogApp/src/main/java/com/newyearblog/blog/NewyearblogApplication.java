package com.newyearblog.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.newyearblog.blog.mapper")
public class NewyearblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewyearblogApplication.class, args);
	}

}
