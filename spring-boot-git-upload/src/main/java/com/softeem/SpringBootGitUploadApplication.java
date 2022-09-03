package com.softeem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.softeem.dao")
public class SpringBootGitUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGitUploadApplication.class, args);
    }

}
