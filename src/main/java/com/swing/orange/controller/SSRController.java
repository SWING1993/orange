package com.swing.orange.controller;

import com.swing.orange.entity.User;
import com.swing.orange.mapper.UserMapper;
import com.swing.orange.utils.MyHttpClientUtils;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SSRController {

    @Autowired
    UserMapper userMapper;
    private final String url = "http://35.221.106.79:8080/tomato";

    // 查询所有SSR用户的配置
    @GetMapping("/ssr/user")
    public RestResult<String> findBots(@RequestHeader(value = "uid") long uid) throws Exception {
        User user = this.userMapper.selectById(uid);
        if (user.getPhone().equals("18667905583")) {
            return MyHttpClientUtils.getHandle(url + "/ssr/user");
        } else {
            return RestResultGenerator.genErrorResult("无权限");
        }
    }
}
