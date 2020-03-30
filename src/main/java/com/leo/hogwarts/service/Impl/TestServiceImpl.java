package com.leo.hogwarts.service.Impl;

import com.leo.hogwarts.mapper.TestMapper;
import com.leo.hogwarts.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TestServiceImpl
 * @Description
 * @Author Leo
 * @Date 2020/3/30 14:42
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;


    @Override
    public List<Map<String, Object>> test() {
        return testMapper.getTest();
    }
}
