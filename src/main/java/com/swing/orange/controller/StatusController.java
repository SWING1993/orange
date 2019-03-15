package com.swing.orange.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.swing.orange.entity.Status;
import com.swing.orange.entity.User;
import com.swing.orange.mapper.StatusMapper;
import com.swing.orange.mapper.UserMapper;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatusController {

    @Autowired
    StatusMapper statusMapper;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/status")
    public RestResult postStatus(@RequestParam(value = "status") String statusJson) {
        System.out.println(statusJson);
        Gson gson = new Gson();
        Status status = gson.fromJson(statusJson, Status.class);
        this.statusMapper.insert(status);
        return RestResultGenerator.genSuccessResult();
    }

    @GetMapping("/status")
    public RestResult statusList(@RequestHeader(value = "uid") int uid, @RequestParam(value = "pageNum") int pageNum) {
        Page<Status> page = PageHelper.startPage(pageNum, 20, "id desc").doSelectPage(()-> this.statusMapper.selectByUid(uid));
        User user = this.userMapper.selectById(uid);
        if (user.getNickname().isEmpty()) {
            user.setNickname("");
        }
        if (user.getAvatarUrl().isEmpty()) {
            user.setAvatarUrl("");
        }
        for (int i = 0; i < page.size(); i++) {
            Status status = page.get(i);
            status.setAvatarUrl(user.getAvatarUrl());
            status.setNickname(user.getNickname());
        }
        return RestResultGenerator.genSuccessResult(page);
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

    @GetMapping("/status/all")
    public RestResult allStatusList(@RequestParam(value = "pageNum") int pageNum) {
        Page<Status> page = PageHelper.startPage(pageNum, 20, "id desc").doSelectPage(()-> this.statusMapper.getAll());
        for (int i = 0; i < page.size(); i++) {
            Status status = page.get(i);
            User user = this.userMapper.selectById(status.getUid());
            if (user != null) {
                status.setAvatarUrl(user.getAvatarUrl());
                status.setNickname(user.getNickname());
            }
        }
        return RestResultGenerator.genSuccessResult(page);
    }
}
