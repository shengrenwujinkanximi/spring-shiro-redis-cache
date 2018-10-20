package com.zwb.testredis;

import java.net.URI;
import java.net.URISyntaxException;

import redis.clients.jedis.Jedis;

public class RedisTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.0.9");
		System.out.println(jedis.ping());

	}
}
