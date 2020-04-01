package com.leo.hogwarts.service.Impl;

import com.alibaba.fastjson.JSON;
import com.leo.hogwarts.constant.HogwartsConstant;
import com.leo.hogwarts.entity.LoginUser;
import com.leo.hogwarts.exception.AuthException;
import com.leo.hogwarts.mapper.LoginMapper;
import com.leo.hogwarts.service.LoginService;
import com.leo.hogwarts.util.RedisUtil;
import com.leo.hogwarts.util.TokenUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName LoginServiceImpl
 * @Description
 * @Author Leo
 * @Date 2020/3/31 15:37
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public LoginUser login(LoginUser loginUser) throws Exception{
        LoginUser u = loginMapper.getUserByUsername(loginUser.getUsername());
        if (u == null) {
            throw new AuthException("用户名或密码错误");
        }
        if (u.getPassword().equals(DigestUtils.md5Hex(loginUser.getPassword() + u.getSalt()))) {
            String token = TokenUtil.getToken(loginUser.getUsername());
            redisUtil.set(HogwartsConstant.TOKEN_PREFIX + token, JSON.toJSONString(u),1, TimeUnit.DAYS);
            return u;
        }else {
            throw new AuthException("用户名或密码错误");
        }
    }
}
