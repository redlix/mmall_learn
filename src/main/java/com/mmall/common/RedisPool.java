package com.mmall.common;

import com.mmall.util.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author redLi
 * @package com.mmall.common
 * @time 2018/03/13 23:13
 * @description:
 */
public class RedisPool {
    /**
     * jedis连接池
     */
    private static JedisPool pool;
    /**
     * redisIp
     */
    private static String redisIp = PropertiesUtil.getProperty("redis.ip");
    /**
     * redisPort
     */
    private static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperty("redis.port"));
    /**
     * 最大连接数
     */
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total", "20"));
    /**
     * 在jedisPool中最大的idle(空闲的)的jedis实例个数
     */
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle", "10"));
    /**
     * 在jedispool中最小的idle(空闲的)的jedis实例个数
     */
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle", "2"));
    /**
     * 在borrow一个jedis实例时，是否进行验证操作，若赋值true，则可用
     */
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow", "true"));
    /**
     * 在borrow一个jedis实例时，是否进行验证操作，若赋值true，则放回jedispoll的jedis实例可用
     */
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return", "false"));

    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        //连接耗尽的时候，是否会阻塞，false回抛出异常，true阻塞直到超时
        config.setBlockWhenExhausted(true);

        pool = new JedisPool(config, redisIp, redisPort, 1000*2);
    }

    static{
        initPool();
    }

    public static Jedis getJedis(){
        return pool.getResource();
    }

    public static void returnBrokenResource(Jedis jedis){
        pool.returnBrokenResource(jedis);
    }

    public static void returnResource(Jedis jedis){
        pool.returnResource(jedis);
    }

    public static void main(String[] args) {
        Jedis jedis = pool.getResource();
        jedis.set("redlikey","redlivalue");
        returnResource(jedis);

        //临时调用，销毁连接池中的所有连接
        pool.destroy();
        System.out.println("program is end");
    }
}