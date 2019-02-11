package com.swing.orange.controller;

import com.google.gson.Gson;
import com.swing.orange.entity.Status;
import com.swing.orange.mapper.StatusMapper;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StatusController {

    @Autowired
    StatusMapper statusMapper;

    @PostMapping("/status")
    public RestResult postStatus(@RequestParam(value = "status") String statusJson) {
        System.out.println(statusJson);
        Gson gson = new Gson();
        Status status = gson.fromJson(statusJson, Status.class);
        this.statusMapper.insert(status);
        return RestResultGenerator.genSuccessResult();
    }

    @GetMapping("/status")
    public RestResult statusList(@RequestHeader(value = "uid") int uid) {
        List<Status> list = this.statusMapper.selectByUid(uid);
        return RestResultGenerator.genSuccessResult(list);
    }

    @DeleteMapping("/status")
    public RestResult deleteStatus(@RequestHeader(value = "uid") int uid, @RequestParam(value = "id") int id) {
        Status status = this.statusMapper.selectById(id);
        if (status.getUid() == uid) {
            this.statusMapper.deleteById(id);
            return RestResultGenerator.genSuccessResult();
        }
        return RestResultGenerator.genErrorResult("删除失败");
    }


}
