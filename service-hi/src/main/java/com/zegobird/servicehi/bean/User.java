package com.zegobird.servicehi.bean;

import lombok.Data;

/**
 * @author huanfion
 * @version 1.0
 * @date 2019/7/23 12:09
 */
@Data
public class User {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String passWord;

    public User() {
    }

    public User(String name, int age, String nickName, String passWord) {
        this.name = name;
        this.age = age;
        this.nickName = nickName;
        this.passWord = passWord;
    }
}
