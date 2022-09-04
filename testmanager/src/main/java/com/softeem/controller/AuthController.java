package com.softeem.controller;

import com.softeem.http.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/21 16:12
 * 项目名称 spring-boot-hr-0721
 */
@RestController
@RequestMapping("/auth")

//用作请求试探是否存有jwt
public class AuthController {

    @GetMapping("/authentication")
    public R authentication(){
        return R.ok(200,"校验成功！");
    }
}
