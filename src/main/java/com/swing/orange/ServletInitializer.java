package com.swing.orange;

import com.swing.orange.utils.DingChatBot;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        DingChatBot.sendMsg("OrangeApplication(Tomcat) 启动");
        return builder.sources(OrangeApplication.class);
    }
}