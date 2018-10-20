package com.zwb.cache;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

public class RedisCacheManage implements CacheManager {

	@Resource
	private RedisCache redisCache;
	@Override
	public <K, V> Cache<K, V> getCache(String arg0) throws CacheException {
		return redisCache;
	}

}
