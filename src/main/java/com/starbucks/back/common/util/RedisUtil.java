package com.starbucks.back.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil<T> {

    private final RedisTemplate<String, T> redisTemplate;

    public void set(String key, T value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }
    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public Long increase(String key, long timeout, TimeUnit unit) {
        Long value = redisTemplate.opsForValue().increment(key);
        if (value != null && value == 1L) {
            redisTemplate.expire(key, timeout, unit);
        }
        return value;
    }

    public Long decrement(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    // **** List 관련 메서드 ****

    // 왼쪽에 값 추가 (LPUSH)
    public void leftPush(String key, T value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    // 해당 값 제거 (LREM; count 0이면 모두 제거)
    public void removeFromList(String key, long count, T value) {
        redisTemplate.opsForList().remove(key, count, value);
    }

    // 리스트 범위 자르기 (LTRIM)
    public void trimList(String key, long start, long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    // 리스트 범위 조회 (LRANGE)
    public List<T> getListRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    // 패턴에 맞는 모든 키 조회
    public Set<String> getKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    // **** lua script 주입 ****
    public Long executeCouponDownloadScript(String key) {
        try {
            Resource resource = new ClassPathResource("lua/coupon_download.lua");
            String script = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);


            return redisTemplate.execute(
                    (RedisCallback<Long>) connection ->
                            connection.eval(script.getBytes(), ReturnType.INTEGER, 1, key.getBytes())
            );
        } catch (IOException e) {
            throw new RuntimeException("Lua 스크립트 읽기 실패 ", e);
        }
    }
}
