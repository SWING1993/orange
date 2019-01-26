package com.swing.orange.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

//    @Autowired
//    private RabbitSender helloSender;

    @GetMapping("/")
    public String index() throws Exception {
        return "hello world";
    }
}
