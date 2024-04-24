package com.ycr.partnermatch.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * @author null&&
 * @version 1.0
 * @date 2024/4/23 21:44
 */
@SpringBootTest
class RedisTest {

	@Resource
	private RedisTemplate redisTemplate;

	@Test
	void test() {
		ValueOperations valueOperations = redisTemplate.opsForValue();
		valueOperations.set("name", "ycr");
		Assertions.assertEquals("ycr", valueOperations.get("name"));
		System.out.println(valueOperations.get("name"));

	}
}
