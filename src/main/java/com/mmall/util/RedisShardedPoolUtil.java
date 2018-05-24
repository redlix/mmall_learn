package com.mmall.util;

/**
 * @author redLi
 * @package com.mmall.util
 * @time 2018/03/14 21:05
 * @description:
 */

import com.mmall.common.RedisShardedPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;

@Slf4j
public class RedisShardedPoolUtil {
    /**
     * 设置key有效期,单位秒
     *
     * @param key
     * @param exTime
     * @return
     */
    public static Long expire(String key, int exTime) {
        ShardedJedis shardedJedis = null;
        Long result = null;
        try {
            shardedJedis = RedisShardedPool.getShardedJedis();
            result = shardedJedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("expire key:{}", key, e);
            RedisShardedPool.returnBrokenResource(shardedJedis);
            return result;
        }
        RedisShardedPool.returnResource(shardedJedis);
        return result;
    }

    /**
     * exTime 单位 秒
     *
     * @param key
     * @param value
     * @param exTime
     * @return
     */
    public static String setEx(String key, String value, int exTime) {
        ShardedJedis ShardedJedis = null;
        String result = null;
        try {
            ShardedJedis = RedisShardedPool.getShardedJedis();
            result = ShardedJedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("setex key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(ShardedJedis);
            return result;
        }
        RedisShardedPool.returnResource(ShardedJedis);
        return result;
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public static String set(String key, String value) {
        ShardedJedis ShardedJedis = null;
        String result = null;

        try {
            ShardedJedis = RedisShardedPool.getShardedJedis();
            result = ShardedJedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(ShardedJedis);
            return result;
        }
        RedisShardedPool.returnResource(ShardedJedis);
        return result;
    }

    /**
     * @param key
     * @return
     */
    public static String get(String key) {
        ShardedJedis ShardedJedis = null;
        String result = null;
        try {
            ShardedJedis = RedisShardedPool.getShardedJedis();
            result = ShardedJedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error", key, e);
            RedisShardedPool.returnBrokenResource(ShardedJedis);
            return result;
        }
        RedisShardedPool.returnResource(ShardedJedis);
        return result;
    }

    /**
     * @param key
     * @return
     */
    public static Long del(String key) {
        ShardedJedis ShardedJedis = null;
        Long result = null;
        try {
            ShardedJedis = RedisShardedPool.getShardedJedis();
            result = ShardedJedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error", key, e);
            RedisShardedPool.returnBrokenResource(ShardedJedis);
            return result;
        }
        RedisShardedPool.returnResource(ShardedJedis);
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ShardedJedis ShardedJedis = RedisShardedPool.getShardedJedis();

        RedisShardedPoolUtil.set("keyTest", "value");
        String value = RedisShardedPoolUtil.get("keyTest");
        RedisShardedPoolUtil.setEx("keyex", "valueex", 60 * 10);
        RedisShardedPoolUtil.expire("keyTest", 60 * 20);
        RedisShardedPoolUtil.del("keyTest");

//        RedisShardedPoolUtil.set("keyTest","value");
//        String value = RedisShardedPoolUtil.get("keyTest");
//        RedisShardedPoolUtil.setEx("keyex","valueex",60*10);
//        RedisShardedPoolUtil.expire("keyTest",60*20);
//        RedisShardedPoolUtil.del("keyTest");
//        String aaa = RedisShardedPoolUtil.get(null);

        System.out.println("end");
    }
}
