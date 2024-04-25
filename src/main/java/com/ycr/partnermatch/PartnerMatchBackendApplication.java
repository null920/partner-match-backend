package com.ycr.partnermatch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.ycr.partnermatch.mapper")
@EnableScheduling
@EnableConfigurationProperties
public class PartnerMatchBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartnerMatchBackendApplication.class, args);
	}

}
