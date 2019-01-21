package com.swing.orange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrangeApplication {
	public static void main(String[] args) {
		SpringApplication.run(com.swing.orange.OrangeApplication.class, args);
	}
}

///**
// * 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
// */
//@SpringBootApplication
//public class OrangeApplication extends SpringBootServletInitializer {
//
//    public static void main(String[] args) {
//        SpringApplication.run(OrangeApplication.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(OrangeApplication.class);
//    }
//
//}

