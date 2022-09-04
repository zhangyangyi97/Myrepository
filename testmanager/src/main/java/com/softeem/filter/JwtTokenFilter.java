package com.softeem.filter;

import com.softeem.exception.TokenFailException;
import com.softeem.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;



@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1. 不要拦截登录请求
        if(request.getRequestURI().endsWith("login")){
            filterChain.doFilter(request,response);
            return;
        }
        //注册请求的放行
        if(request.getRequestURI().endsWith("register")){
            filterChain.doFilter(request,response);
            return;
        }
        //2. 基于ajax 不要拦截options请求
        if(request.getMethod().equalsIgnoreCase("OPTIONS")){
            filterChain.doFilter(request,response);
            return;
        }
        // 解析请求头中包含的凭证
        String token = request.getHeader("Authorization");
        System.out.println("token = " + token);
        Claims claims = JwtUtil.parse(token);
        if(claims==null){
            throw new TokenFailException("令牌解析失败");
        }
        // 存入安全上下文
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                claims.getSubject(),null, Collections.emptyList()
        ));
        filterChain.doFilter(request,response);
    }
}
