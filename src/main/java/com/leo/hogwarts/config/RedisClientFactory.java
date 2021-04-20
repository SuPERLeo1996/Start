package com.leo.hogwarts.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.TransportMode;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ClassName RedisClientFactory.java
 * @Author gonglinjie
 * @Description
 * @Date 2021/4/20
 */
@Component
public class RedisClientFactory {

    private final Config config;

    public RedisClientFactory(RedisProperties redisProperties) {
        Config redisConfig = new Config();
        redisConfig.setTransportMode(TransportMode.NIO);

        // 单节点
        if (redisProperties.getSentinel() == null || redisProperties.getSentinel().getMaster() == null) {
            redisConfig.useSingleServer().setAddress(String.format("redis://%s:%d", redisProperties.getHost(), redisProperties.getPort()));
        } else {
            // 哨兵模式
            redisConfig.useSentinelServers()
                    .setMasterName(redisProperties.getSentinel().getMaster())
                    .addSentinelAddress(redisProperties.getSentinel().getNodes().stream().map(s -> String.format("redis://%s", s)).toArray(String[]::new))
                    .setReadMode(ReadMode.MASTER); // 只从 master 读数据，保证强一致性
        }

        this.config = redisConfig;
    }

    @Bean
    public RedissonClient redisClient() {
        return Redisson.create(config);
    }
}
