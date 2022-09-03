package com.softeem.service;

import com.softeem.dao.UserDao;
import com.softeem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/9/3 16:02
 * 项目名称 spring-boot-git-upload
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findAllUsers(){
        return userDao.findAllUsers();
    }
}
