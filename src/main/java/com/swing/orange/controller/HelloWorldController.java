package com.swing.orange.controller;

import com.swing.orange.utils.RabbitSender;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloWorldController {

    @Autowired
    private RabbitSender helloSender;

    @GetMapping("/")
    public RestResult helloworld(){
        helloSender.send("Rabbit 消息队列");
        return RestResultGenerator.genSuccessResult("hello world! " + new Date().toString());
    }

}
