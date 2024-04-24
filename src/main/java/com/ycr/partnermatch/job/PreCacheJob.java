package com.ycr.partnermatch.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ycr.partnermatch.mapper.UserMapper;
import com.ycr.partnermatch.model.domain.User;
import com.ycr.partnermatch.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 缓存预热
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/24 19:09
 */
@Component
@Slf4j
public class PreCacheJob {

	@Resource
	private UserService userService;

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	private List<Long> mainUserList = Arrays.asList(1L);

	// 每天执行一次缓存推荐用户
	@Scheduled(cron = "0 0 3 * * *")
	public void doCacheRecommendUser() {
		for (Long userId : mainUserList) {
			QueryWrapper<User> wrapper = new QueryWrapper<>();
			Page<User> userPage = userService.page(new Page<>(1, 20), wrapper);
			String redisKey = String.format("partnerMatch:user:recommend:%s", userId);
			ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
			try {
				valueOperations.set(redisKey, userPage, 60000, TimeUnit.MILLISECONDS);
			} catch (Exception e) {
				log.error("redis set key error", e);
			}
		}
	}

}
