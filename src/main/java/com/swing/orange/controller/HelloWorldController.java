package com.swing.orange.controller;

import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloWorldController {
    @GetMapping("/")
    public RestResult index() {
        return RestResultGenerator.genSuccessResult("Hello World !!!");
    }
}
