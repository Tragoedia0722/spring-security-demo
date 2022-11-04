package com.example.springsecuritydemo.util;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redis 缓存工具类
 *
 * @author Tragoedia
 */
@Component
@SuppressWarnings("unused")
public class RedisCache {
    @Resource
    public RedisTemplate<String, Object> redisTemplate;

    /**
     * 缓存基本对象
     *
     * @param key   键
     * @param value 值
     */
    public void setCacheObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存基本对象
     *
     * @param key      键
     * @param value    值
     * @param timeout  超时时间
     * @param timeUnit 时间粒度
     */
    public void setCacheObject(String key, Object value, Integer timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置超时时间
     *
     * @param key     键
     * @param timeout 超时时间
     * @return 是否生效
     */
    public boolean expire(String key, long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置超时时间
     *
     * @param key     键
     * @param timeout 超时时间
     * @param unit    时间粒度
     * @return 是否生效
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, unit));
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 键
     * @return 缓存对象数据
     */
    public Object getCacheObject(String key) {
        ValueOperations<String, Object> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除缓存对象
     *
     * @param key 键
     */
    public void deleteObject(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 缓存List数据
     *
     * @param key      键
     * @param dataList List数据
     * @return 缓存对象个数
     */
    public long setCacheList(final String key, final List<Object> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的 list 对象
     *
     * @param key 键
     * @return 数据
     */
    public List<Object> getCacheList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 移除 list 中的对象
     *
     * @param key   键
     * @param count 移除个数
     *              count > 0 表头移除
     *              count < 0 表尾移除
     *              count = 0 全部移除
     * @param value 值
     */
    public void removeCacheList(String key, long count, String value) {
        redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 缓存Set
     *
     * @param key     键
     * @param dataSet 缓存数据
     * @return 缓存数据的对象
     */
    public BoundSetOperations<String, Object> setCacheSet(String key, Set<Object> dataSet) {
        BoundSetOperations<String, Object> setOperation = redisTemplate.boundSetOps(key);
        for (Object o : dataSet) {
            setOperation.add(o);
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key 键
     * @return Set
     */
    public Set<Object> getCacheSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存Map
     *
     * @param key     键
     * @param dataMap 目标map
     */
    public void setCacheMap(String key, Map<String, Object> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获得缓存的Map
     *
     * @param key 键
     * @return map
     */
    public Map<Object, Object> getCacheMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key   键
     * @param hKey  Hash键
     * @param value 值
     */
    public <T> void setCacheMapValue(String key, String hKey, T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(String key, String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 删除Hash中的数据
     *
     * @param key     键
     * @param hashKey hash键
     */
    public void delCacheMapValue(String key, String hashKey) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hashKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public List<Object> getMultiCacheMapValue(String key, Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }
}