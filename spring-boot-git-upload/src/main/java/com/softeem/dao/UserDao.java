package com.softeem.dao;

import com.softeem.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/9/3 16:01
 * 项目名称 spring-boot-git-upload
 */
@Repository
public interface UserDao {

    @Select("select * from user")
    List<User> findAllUsers();
}
