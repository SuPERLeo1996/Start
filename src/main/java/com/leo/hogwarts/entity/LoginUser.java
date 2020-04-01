package com.leo.hogwarts.entity;

/**
 * @ClassName LoginUser
 * @Description
 * @Author Leo
 * @Date 2020/3/31 9:59
 */

public class LoginUser {

    private String userId;
    private String username;
    private String password;
    private String salt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
