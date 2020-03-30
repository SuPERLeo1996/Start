package com.leo.hogwarts.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TestMapper
 * @Description
 * @Author Leo
 * @Date 2020/3/30 14:43
 */
@Mapper
public interface TestMapper {

    List<Map<String,Object>> getTest();
}
