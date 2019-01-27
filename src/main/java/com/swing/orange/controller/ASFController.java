package com.swing.orange.controller;

import com.swing.orange.utils.HttpClientUtils;
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

    private final String url = "http://35.241.122.72:8080/tomato";

    @GetMapping("/asf/findBots")
    public RestResult findBots() throws Exception {
        return HttpClientUtils.getHandle( url + "/asf/findBots");
    }

    @GetMapping("/asf/logs")
    public RestResult findLogs() throws Exception {
        return HttpClientUtils.getHandle( url + "/asf/logs");
    }

    @PostMapping("/asf/save")
    public RestResult saveBot(@RequestParam(value = "filename") String filename, @RequestParam(value = "content") String content) throws Exception {
        ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("filename", filename));
        parameters.add(new BasicNameValuePair("content", content));
        return HttpClientUtils.postHandle(url + "/asf/save", parameters);
    }

}
