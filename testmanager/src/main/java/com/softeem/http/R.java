package com.softeem.http;

/**
 * 创建作者 ：ZYY
 * 创建时间 2022/7/13 18:57
 * 项目名称 spring-boot-0713-homework
 */
public class R {

    private Integer code;
    private String message;
    private Object data;

    public R() {
    }

    public R(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public R(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public R(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public R(Object data) {
        this.data = data;
    }

    //写一些自定义的方法
    public static R ok(Integer code, String message){
        code = code;
        message = message;
        return new R(code,message);
    }
    public static R ok(String message){
        message = "登录成功";
        return new R(message);
    }

    public static R ok(Integer code, String message, Object data){
        return new R(code,message,data);
    }

    public static R ok(Object data){
        return new R(data);
    }


    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
