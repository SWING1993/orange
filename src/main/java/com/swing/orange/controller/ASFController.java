package com.swing.orange.controller;

import com.swing.orange.utils.MyHttpClientUtils;
import com.swing.orange.utils.RestResult;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

@RestController
public class ASFController {

    private final String url = "http://35.221.106.79:8080/tomato";

    @GetMapping("/asf/findBots")
    public RestResult findBots() throws Exception {
        return MyHttpClientUtils.getHandle( url + "/asf/findBots");
    }

    @GetMapping("/asf/logs")
    public RestResult findLogs() throws Exception {
        return MyHttpClientUtils.getHandle( url + "/asf/logs");
    }

    @PostMapping("/asf/save")
    public RestResult saveBot(@RequestParam(value = "filename") String filename, @RequestParam(value = "content") String content) throws Exception {
        ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("filename", filename));
        parameters.add(new BasicNameValuePair("content", content));
        return MyHttpClientUtils.postHandle(url + "/asf/save", parameters);
    }

}
