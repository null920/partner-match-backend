package com.ycr.partnermatch.service;


import com.ycr.partnermatch.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/**
 * @author null&&
 * @version 1.0
 * @date 2024/4/23 16:44
 */
@SpringBootTest
class InsertUsersTest {
	@Resource
	private UserService userService;

	/**
	 * 批量插入用户
	 */
	@Test
	void doInsertUsers() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		final int INSERT_NUM = 100000;
		ArrayList<User> userList = new ArrayList<>();
		for (int i = 0; i < INSERT_NUM; i++) {
			User user = new User();
			user.setUsername("假用户");
			user.setUserAccount("fake");
			user.setAvatarUrl("/avatar.jpeg");
			user.setGender(0);
			user.setUserPassword("77777777");
			user.setPhone("16454156725");
			user.setEmail("6161890@qq.com");
			user.setTags("[]");
			userList.add(user);
		}
		// 每100条插入一次，减少SqlSession的开销
		// 2646
		// 19409 1000条
		userService.saveBatch(userList, 10000);
		stopWatch.stop();
		System.out.println(stopWatch.getTotalTimeMillis());
		System.out.println(stopWatch.prettyPrint());
	}

	@Test
	void doConcurrencyInsertUsers() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ArrayList<CompletableFuture<Void>> futureList = new ArrayList<>();
		int j = 0;
		int batchSize = 5000;
		for (int i = 0; i < 100; i++) {
			ArrayList<User> userList = new ArrayList<>();
			while (true) {
				j++;
				User user = new User();
				user.setUsername("假用户");
				user.setUserAccount("fake");
				user.setAvatarUrl("/avatar.jpeg");
				user.setGender(0);
				user.setUserPassword("77777777");
				user.setPhone("16454156725");
				user.setEmail("6161890@qq.com");
				user.setTags("[]");
				userList.add(user);
				if (j % batchSize == 0) {
					break;
				}
			}
			CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
				System.out.println("ThreadName: " + Thread.currentThread().getName());
				userService.saveBatch(userList, batchSize);
			});
			futureList.add(completableFuture);
		}
		CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
		// 每100条插入一次，减少SqlSession的开销
		// 2646
		// 19409 1000条
		stopWatch.stop();
		System.out.println(stopWatch.getTotalTimeMillis());
		System.out.println(stopWatch.prettyPrint());
	}
}
