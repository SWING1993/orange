package com.swing.orange;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.swing.orange.mapper")
public class OrangeApplication {
	public static void main(String[] args) {
		SpringApplication.run(com.swing.orange.OrangeApplication.class, args);
	}
}

