package com.ycr.partnermatch.service;

import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author null&&
 * @version 1.0
 * @date 2024/4/25 19:45
 */
@SpringBootTest
class RedissonTest {

	// @Resource
	// private RedissonClient redissonClient;
	//
	// @Test
	// void test() {
	// 	RList<Object> rList = redissonClient.getList("test-list");
	// 	//rList.add("ycr");
	// 	System.out.println("rList:" + rList.get(0));
	// 	rList.remove(0);
	// }
}
