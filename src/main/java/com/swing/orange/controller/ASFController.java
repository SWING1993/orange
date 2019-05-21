package com.swing.orange.controller;

import com.swing.orange.entity.User;
import com.swing.orange.mapper.UserMapper;
import com.swing.orange.utils.MyHttpClientUtils;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class ASFController {

    @Autowired
    UserMapper userMapper;
    private final String url = "http://35.221.106.79:8080/tomato";

    @GetMapping("/asf/findBots")
    public RestResult findBots(@RequestHeader(value = "uid") long uid) throws Exception {
        User user = this.userMapper.selectById(uid);
        if (user.getPhone().equals("18667905583")) {
            return MyHttpClientUtils.getHandle( url + "/asf/findBots");
        } else {
            return RestResultGenerator.genErrorResult("无权限");
        }
    }

    @GetMapping("/asf/logs")
    public RestResult findLogs(@RequestHeader(value = "uid") long uid) throws Exception {
        User user = this.userMapper.selectById(uid);
        if (user.getPhone().equals("18667905583")) {
            return MyHttpClientUtils.getHandle(url + "/asf/logs");
        } else {
            return RestResultGenerator.genErrorResult("无权限");
        }
    }

    @PostMapping("/asf/save")
    public RestResult saveBot(@RequestHeader(value = "uid") long uid, @RequestParam(value = "filename") String filename, @RequestParam(value = "content") String content) throws Exception {
        User user = this.userMapper.selectById(uid);
        if (user.getPhone().equals("18667905583")) {
            ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
            parameters.add(new BasicNameValuePair("filename", filename));
            parameters.add(new BasicNameValuePair("content", content));
            return MyHttpClientUtils.postHandle(url + "/asf/save", parameters);
        } else {
            return RestResultGenerator.genErrorResult("无权限");
        }
    }

}
