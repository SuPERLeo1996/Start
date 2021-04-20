package com.leo.hogwarts.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisOperatorImpl.java
 * @Author gonglinjie
 * @Description
 * @Date 2021/4/20
 */
@Service
public class RedisOperatorImpl implements RedisOperator {

    @Autowired
    private RedisTemplate<String, Object> redisTemplateJson;

    @Override
    public void set(String key, Object value) {
        redisTemplateJson.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, long timeout) {
        redisTemplateJson.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public Boolean setnx(String key, Object value) {
        return redisTemplateJson.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public boolean setNx(String key, Object value, Integer timeout) {
        Boolean bool = redisTemplateJson.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
        if (bool == null) {
            return false;
        }
        return bool;
    }

    @Override
    public Object get(String key) {
        return redisTemplateJson.opsForValue().get(key);
    }

    @Override
    public Boolean delete(String key) {
        return redisTemplateJson.delete(key);
    }

    @Override
    public Long mDelete(List<String> keys) {
        return redisTemplateJson.delete(keys);
    }

    @Override
    public Boolean expire(String key, long timeout) {
        return redisTemplateJson.expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void hset(String hashKey, String field, Object value) {
        redisTemplateJson.<String, Object>opsForHash().put(hashKey, field, value);
    }

    @Override
    public Object hget(String hashKey, String field) {
        return redisTemplateJson.<String, Object>opsForHash().get(hashKey, field);
    }

    @Override
    public Map<String, Object> hMultiGet(String hashKey, List<String> fields) {
        Map<String, Object> map = new HashMap<>();

        List<Object> list = this.redisTemplateJson.<String, Object>opsForHash().multiGet(hashKey, fields);
        Iterator<Object> iteratorV = list.iterator();
        for (String k : fields) {
            if (iteratorV.hasNext()) {
                Object v = iteratorV.next();
                map.put(k, v);
            }
        }
        return map;
    }

    @Override
    public Long hdelete(String hashKey, String key) {
        return redisTemplateJson.<String, Object>opsForHash().delete(hashKey, key);
    }

    @Override
    public void mset(Map<String, Object> data, long ttl) {
        RedisCallback<List<Object>> pipelineCallback = connection -> {
            Expiration expiration = Expiration.from(ttl, TimeUnit.SECONDS);
            RedisStringCommands.SetOption setOption = RedisStringCommands.SetOption.UPSERT;
            connection.openPipeline();
            for (Map.Entry<String, Object> entry : data.entrySet()) {

                StringRedisSerializer keySerializer = (StringRedisSerializer) redisTemplateJson.getKeySerializer();
                GenericJackson2JsonRedisSerializer valueSerializer =
                        (GenericJackson2JsonRedisSerializer) redisTemplateJson.getValueSerializer();

                byte[] rawKey = keySerializer.serialize(entry.getKey());
                byte[] rawValue = valueSerializer.serialize(entry.getValue());

                if (rawKey == null || rawValue == null) {
                    continue;
                }

                connection.set(rawKey, rawValue, expiration, setOption);
            }
            return connection.closePipeline();
        };

        redisTemplateJson.execute(pipelineCallback);

    }

    @Override
    public Map<String, Object> mget(List<String> ids) {
        List<Object> values = redisTemplateJson.opsForValue().multiGet(ids);
        Map<String, Object> map = new HashMap<>();
        if (values == null) {
            return map;
        }

        for (Integer idx = 0; idx < values.size(); idx++) {
            Object value = values.get(idx);
            if (value == null) {
                continue;
            }
            map.put(ids.get(idx), value);
        }


        return map;
    }
}
