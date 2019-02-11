package com.swing.orange;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.swing.orange.mapper")
public class OrangeApplication {
	public static void main(String[] args) {
		SpringApplication.run(com.swing.orange.OrangeApplication.class, args);
	}
}

