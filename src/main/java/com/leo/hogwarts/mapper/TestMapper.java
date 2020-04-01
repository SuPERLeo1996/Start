package com.leo.hogwarts.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TestMapper
 * @Description
 * @Author Leo
 * @Date 2020/3/30 14:43
 */
@Repository
public interface TestMapper {

    List<Map<String,Object>> getTest();
}
