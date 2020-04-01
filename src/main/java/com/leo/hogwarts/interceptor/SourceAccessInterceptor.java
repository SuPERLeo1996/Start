package com.leo.hogwarts.interceptor;

import com.alibaba.fastjson.JSON;
import com.leo.hogwarts.annotation.Auth;
import com.leo.hogwarts.constant.HogwartsConstant;
import com.leo.hogwarts.entity.LoginUser;
import com.leo.hogwarts.exception.AuthException;
import com.leo.hogwarts.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName SourceAccessInterceptor
 * @Description
 * @Author Leo
 * @Date 2020/3/31 15:00
 */
public class SourceAccessInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    private static final Logger logger = LoggerFactory.getLogger(SourceAccessInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        Auth auth = (Auth)method.getAnnotation(Auth.class);
        String token = request.getHeader("access_token");
        if (auth != null) {
            if (StringUtils.isNotBlank(token)) {
                String val = redisUtil.get(HogwartsConstant.TOKEN_PREFIX + token);
                if (StringUtils.isBlank(val)) {
                    throw new AuthException("登录失效,请重新登录");
                }else {
                    request.setAttribute(HogwartsConstant.USER, JSON.parseObject(val, LoginUser.class));
                    return true;
                }
            } else {
                throw new AuthException("登录失效,请重新登录");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
