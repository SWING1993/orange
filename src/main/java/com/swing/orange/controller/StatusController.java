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
    UserMapper userMapper;

    @Autowired
    StatusMapper statusMapper;

    @PostMapping("/status")
    public RestResult postStatus(@RequestParam(value = "uid") long uid,
                                 @RequestParam(value = "type") int type,
                                 @RequestParam(value = "content") String content,
                                 @RequestParam(value = "imageUrls", required = false) String imageUrls,
                                 @RequestParam(value = "vedioUrl", required = false) String vedioUrl,
                                 @RequestParam(value = "fromDevice", required = false) String fromDevice) {
        User user = this.userMapper.selectById(uid);
        Status status = new Status(uid, type, user.getNickname(), user.getAvatarUrl(), content, imageUrls, vedioUrl, System.currentTimeMillis(),fromDevice);
        this.statusMapper.insert(status);
        return RestResultGenerator.genSuccessResult();
    }

    @GetMapping("/status")
    public RestResult statusList(@RequestHeader(value = "uid") int uid,
                                 @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
        Page<Status> page = PageHelper.startPage(pageNum, 20, "status_tbl.id desc").doSelectPage(()-> this.statusMapper.selectByUid(uid));
        return RestResultGenerator.genSuccessResult(page);
    }

    @DeleteMapping("/status")
    public RestResult deleteStatus(@RequestHeader(value = "uid") int uid,
                                   @RequestParam(value = "id") int id) {
        Status status = this.statusMapper.selectById(id);
        if (status.getUid() == uid) {
            this.statusMapper.deleteById(id);
            return RestResultGenerator.genSuccessResult();
        }
        return RestResultGenerator.genErrorResult("删除失败");
    }

    @GetMapping("/status/all")
    public RestResult allStatusList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
        Page<Status> page = PageHelper.startPage(pageNum, 20, "status_tbl.id desc").doSelectPage(()-> this.statusMapper.getAll());
        return RestResultGenerator.genSuccessResult(page);
    }
}
