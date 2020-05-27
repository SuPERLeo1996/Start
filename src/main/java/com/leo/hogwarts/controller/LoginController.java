package com.leo.hogwarts.controller;

import com.leo.hogwarts.entity.LoginUser;
import com.leo.hogwarts.entity.base.ResultDTO;
import com.leo.hogwarts.service.Impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName LoginController
 * @Description
 * @Author Leo
 * @Date 2020/3/31 15:33
 */
@RestController
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultDTO<Object> login(@RequestBody LoginUser loginUser) throws Exception {
        return new ResultDTO<Object>(loginService.login(loginUser));
    }

}
