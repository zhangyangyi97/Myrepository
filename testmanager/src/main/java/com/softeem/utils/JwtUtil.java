package com.softeem.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Date;

//jwt(json web token)工具类,用于字符串的解析和加密
public class JwtUtil {

    //密钥  (最后解密的重要的钥匙🔑)
    private final static String SECRET = "SOFTEEM";

    //过期时间 当前时间+2小时
    private final static Duration expiration = Duration.ofHours(3);


    //构建JWT字符串
    public static String gengrate(String username){
        Date expDate = new Date(System.currentTimeMillis()+expiration.toMillis());
        return Jwts.builder()
                .setSubject(username)  //将用户名存入
                .setIssuedAt(new Date()) //设置开启时间
                .setExpiration(expDate)  //设置过期时间
                .signWith(SignatureAlgorithm.HS512,SECRET)  //选择何种加密方式
                .compact();     //完成
    }

    //解析JWT字符串
    //Claims 荷载  playLoad
    public static Claims parse(String token){
        if (StringUtils.isEmpty(token)){
            return null;
        }
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)  //设置密钥
                    .parseClaimsJws(token)  //将字符串传递
                    .getBody();             //得到数据
        }catch (JwtException exception){
            exception.printStackTrace();
        }
        return claims;
    }
}
