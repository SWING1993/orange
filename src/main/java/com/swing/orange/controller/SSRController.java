package com.swing.orange.controller;

import com.swing.orange.utils.HttpClientUtils;
import com.swing.orange.utils.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SSRController {

    private final String url = "http://35.241.122.72:8080/tomato";

    // 查询所有SSR用户的配置
    @GetMapping("/ssr/user")
    public RestResult<String> findBots() throws Exception {
       return HttpClientUtils.getHandle(url + "ssr/user");
    }
}
