package com.yywzgh.redis;

import redis.clients.jedis.Jedis;

public class RedisConnet {

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.1.1");
        // 如果 Redis 服务设置来密码，需要下面这行，没有就不需要
        //jedis.auth("iy4XjhfydBCs7oBCEHRH"); 
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
    }

}
