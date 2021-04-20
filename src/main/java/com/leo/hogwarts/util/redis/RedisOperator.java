package com.leo.hogwarts.util.redis;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RedisOperator.java
 * @Author gonglinjie
 * @Description
 * @Date 2021/4/20
 */
public interface RedisOperator{

    void set(String key,Object value);

    void set(String key, Object value, long ttl);

    Boolean setnx(String key, Object value);

    boolean setNx(String key, Object value, Integer timeout);

    Object get(String key);

    Boolean delete(String key);

    Long mDelete(List<String> keys);

    Boolean expire(String key, long ttl);

    void hset(String hashKey, String key, Object value);

    Object hget(String hashKey, String key);

    Map<String, Object> hMultiGet(String hashKey, List<String> fields);

    Long hdelete(String hashKey, String key);

    void mset(Map<String, Object> data, long ttl);

    Map<String, Object> mget(List<String> ids);
}
