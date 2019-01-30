package com.swing.orange.controller;

import com.google.gson.Gson;
import com.swing.orange.entity.Status;
import com.swing.orange.mapper.StatusMapper;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @Autowired
    StatusMapper statusMapper;

    @PostMapping("/status")
    public RestResult postStatus(@RequestParam(value = "status") String statusJson) {
        Gson gson = new Gson();
        Status status = gson.fromJson(statusJson, Status.class);
        this.statusMapper.insert(status);
        return RestResultGenerator.genSuccessResult();
    }

}
