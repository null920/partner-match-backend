package com.ycr.partnermatch.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson配置
 *
 * @author null&&
 * @version 1.0
 * @date 2024/4/25 19:33
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {
	private String host;
	private String port;

	@Bean
	public RedissonClient redissonClient() {
		Config config = new Config();
		String redisAddress = String.format("redis://%s:%s", host, port);
		config.useSingleServer().setAddress(redisAddress).setDatabase(1);
		return Redisson.create(config);
	}
}
