package com.swing.orange.Authentication;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class AuthenticationWebMvcConfigurer implements WebMvcConfigurer {

    @Bean
    TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor()).excludePathPatterns("/", "/user/authCode", "/user/register", "/user/login", "/message/send");
    }


}
