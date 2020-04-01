package com.leo.hogwarts.mapper;

import com.leo.hogwarts.entity.LoginUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName LoginMapper
 * @Description
 * @Author Leo
 * @Date 2020/3/31 16:03
 */
@Repository
public interface LoginMapper {

    LoginUser getUserByUsername(@Param("username") String username);

}
