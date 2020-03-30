package com.leo.hogwarts.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Description
 * @Author Leo
 * @Date 2020/3/30 15:14
 */
@Repository("redisUtil")
public class RedisUtil {

    @Autowired
    private StringRedisTemplate template;

    public void set(String key, String value) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit unit) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, timeout, unit);
    }

    public String get(String key) {
        ValueOperations<String, String> ops = template.opsForValue();
        return ops.get(key);
    }

    public long getExpire(String key, TimeUnit unit) {
        return template.getExpire(key, unit);
    }

    //查看某个值redis中存不存在
    public boolean hasKey(String key) {
        return template.hasKey(key);
    }

    public void deleteKey(String key) {
        template.delete(key);
    }

    public void deleteKey(Collection<String> keyList) {
        template.delete(keyList);
    }

    public Long listSize(String key) {
        ListOperations<String, String> ops = template.opsForList();
        return ops.size(key);
    }

    public void leftPush(String key, String value) {
        ListOperations<String, String> ops = template.opsForList();
        ops.leftPush(key, value);
    }

    public void leftPushAll(String key, Collection<String> values) {
        ListOperations<String, String> ops = template.opsForList();
        ops.leftPushAll(key, values);
    }

    public void rightPush(String key, String value) {
        ListOperations<String, String> ops = template.opsForList();
        ops.rightPush(key, value);
    }

    public void rightPushAll(String key, Collection<String> values) {
        ListOperations<String, String> ops = template.opsForList();
        ops.rightPushAll(key, values);
    }

    public String leftPop(String key) {
        ListOperations<String, String> ops = this.template.opsForList();
        return ops.leftPop(key);
    }

    public String rightPop(String key) {
        ListOperations<String, String> ops = this.template.opsForList();
        return ops.rightPop(key);
    }

    public void add(String key, String values) {
        SetOperations<String, String> ops = template.opsForSet();
        ops.add(key, values);
    }

    public Set<String> members(String key) {
        SetOperations<String, String> ops = template.opsForSet();
        return ops.members(key);
    }

    public String pop(String key) {
        SetOperations<String, String> ops = this.template.opsForSet();
        return ops.pop(key);
    }

    public List<String> range(String key, long start, long end) {
        ListOperations<String, String> ops = this.template.opsForList();
        return ops.range(key, start, end);
    }

    public boolean expire(String key, long timeout, TimeUnit unit) {
        return this.template.expire(key, timeout, unit);
    }

    /**
     * @param key
     * @param count count> 0：删除等于从左到右移动的值的第一个元素
     *              count< 0：删除等于从右到左移动的值的第一个元素
     *              count = 0：删除等于value的所有元素。
     * @param value
     * @return void
     * @Title: deleteFromList
     * @Description:
     */

    public void deleteFromList(String key, long count, Object value) {
        ListOperations<String, String> ops = this.template.opsForList();
        ops.remove(key, count, value);
    }

    /**
     * @param pattern
     * @return Set<String>
     * @Title: getKeys, 不适用海量key获取
     * @Description: 模糊获取Set<String> of key
     */
    public Set<String> getKeys(String pattern) {
        return this.template.keys(pattern);
    }

    public Set<String> scan(String pattern, int limit) {

        RedisCallback<Set<String>> callback = new RedisCallback<Set<String>>() {

            @Override
            public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {

                Set<String> binaryKeys = new HashSet<>();

                Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder().match(pattern).count(limit).build());
                int count = 1;
                while (cursor.hasNext()) {
                    if (count > limit) {
                        break;
                    }
                    count++;
                    String key = new String(cursor.next());
                    binaryKeys.add(key);
                }

                return binaryKeys;
            }
        };
        Set<String> keySet = template.execute(callback);
        return keySet;
    }

    public String hget(String key, String item) {
        HashOperations<String, String, String> ops = template.opsForHash();
        return ops.get(key, item);
    }

    public Map<String, String> hgetAll(String key) {
        HashOperations<String, String, String> ops = template.opsForHash();
        return ops.entries(key);
    }

    public boolean hsetAll(String key, Map<String, String> map) {
        try {
            HashOperations<String, String, String> ops = template.opsForHash();
            ops.putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param timeout 时间
     * @param unit 时间单位
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, String> map, long timeout, TimeUnit unit) {
        try {
            HashOperations<String, String, String> ops = template.opsForHash();
            ops.putAll(key, map);
            if (timeout > 0) {
                expire(key, timeout, unit);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean hset(String key, String item, String value) {
        try {
            HashOperations<String, String, String> ops = template.opsForHash();
            ops.put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hset(String key, String item, String value, long timeout,TimeUnit timeUnit) {
        try {
            HashOperations<String, String, String> ops = template.opsForHash();
            ops.put(key, item, value);
            if (timeout > 0) {
                expire(key, timeout,timeUnit);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void hdel(String key, String... item) {
        HashOperations<String, String, String> ops = template.opsForHash();
        ops.delete(key, item);
    }

    public boolean hHasKey(String key, String item) {
        HashOperations<String, String, String> ops = template.opsForHash();
        return ops.hasKey(key, item);
    }

}
