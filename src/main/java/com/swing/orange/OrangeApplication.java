package com.swing.orange;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.swing.orange.utils.DingChatBot;
@SpringBootApplication
@EnableScheduling
@MapperScan("com.swing.orange.mapper")
public class OrangeApplication {
	public static void main(String[] args) throws Exception {
		DingChatBot.sendMsg("OrangeApplication 启动");
		SpringApplication.run(com.swing.orange.OrangeApplication.class, args);
	}
}

