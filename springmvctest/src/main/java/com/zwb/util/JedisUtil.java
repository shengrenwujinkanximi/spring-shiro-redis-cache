package com.zwb.util;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtil {
	@Resource
	private JedisPool jedisPool;
	
	private Jedis getResource() {
		return jedisPool.getResource();		
	}
	
	public byte[] set(byte[] key, byte[] value) {
		Jedis jedis = getResource();
		try {
			jedis.set(key, value);
			return value;
		} finally {
			jedis.close();
		}
	}

	public void expire(byte[] key, int i) {
		Jedis jedis = getResource();
		try {
			jedis.expire(key, i);
		} finally {
			jedis.close();
		}
	}

	public byte[] get(byte[] key) {
		Jedis jedis = getResource();
		try {
			return jedis.get(key);
		} finally {
			jedis.close();
		}
	}

	public void del(byte[] key) {
		Jedis jedis = getResource();
		try {
			jedis.del(key);
		} finally {
			jedis.close();
		}
	}

	public Set<byte[]> keys(String SHIRO_SESSION_PREFIX) {
		Jedis jedis = getResource();
		try {
			return jedis.keys((SHIRO_SESSION_PREFIX+"*").getBytes());
		} finally {
			jedis.close();
		}
	}
}
