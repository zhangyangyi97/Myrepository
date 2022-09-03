package com.softeem.controller;

import com.softeem.entity.User;
import com.softeem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/9/3 16:03
 * 项目名称 spring-boot-git-upload
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAllUsers")
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }
}
