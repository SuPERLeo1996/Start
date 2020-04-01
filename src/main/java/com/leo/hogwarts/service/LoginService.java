package com.leo.hogwarts.service;

import com.leo.hogwarts.entity.LoginUser;

/**
 * @ClassName LoginService
 * @Description
 * @Author Leo
 * @Date 2020/3/31 15:36
 */

public interface LoginService {
    LoginUser login(LoginUser loginUser) throws Exception;
}
