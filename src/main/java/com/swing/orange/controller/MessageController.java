package com.swing.orange.controller;

import com.swing.orange.Authentication.JWTUtil;
import com.swing.orange.entity.Message;
import com.swing.orange.entity.User;
import com.swing.orange.mapper.MessageMapper;
import com.swing.orange.mapper.UserMapper;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/message/send")
    public RestResult insertMessage(@RequestParam(value = "access_token") String access_token, @RequestBody String data) {
        System.out.println("access_token:" + access_token);
        System.out.println("data:" + data);
        String uidStr = JWTUtil.getUid(access_token);
        Long uid = Long.valueOf(uidStr);
        User user = this.userMapper.selectById(uid);
        if (user != null) {
            Message message = new Message(uid, 0, data, new Date().getTime());
            this.messageMapper.insert(message);
            return RestResultGenerator.genSuccessResult();
        } else {
            return RestResultGenerator.genErrorResult("用户不存在");
        }
    }

    @DeleteMapping("/message")
    public RestResult deleteMessage(@RequestHeader(value = "uid") long uid, @RequestParam(value = "msgId") long msgId) {
        Message message = this.messageMapper.selectById(msgId);
        if (message != null && message.getUid() == uid) {
            this.messageMapper.deleteById(msgId);
            return RestResultGenerator.genSuccessResult();
        } else {
            return RestResultGenerator.genErrorResult("没有该条消息");
        }
    }

    public RestResult<Message> messageList(@RequestHeader(value = "uid") long uid) {
        List list = this.messageMapper.g
    }

}
