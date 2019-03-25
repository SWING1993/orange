package com.swing.orange.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.swing.orange.Authentication.JWTUtil;
import com.swing.orange.entity.Message;
import com.swing.orange.entity.User;
import com.swing.orange.mapper.MessageMapper;
import com.swing.orange.mapper.UserMapper;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.swing.orange.utils.AppPush;

@RestController
public class MessageController {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    UserMapper userMapper;

    // 新增消息
    @PostMapping("/message/send")
    public RestResult insertMessage(@RequestParam(value = "access_token") String access_token,
                                    @RequestParam(value = "content") String content,
                                    @RequestParam(value = "title", defaultValue = "", required = false) String title) {
        System.out.println("access_token:" + access_token);
        System.out.println("messageContent:" + content);
        String uidStr = JWTUtil.getUid(access_token);
        Long uid = Long.valueOf(uidStr);
        User user = this.userMapper.selectById(uid);
        if (user != null) {
            Message message = new Message(uid, 0, title ,content, System.currentTimeMillis());
            this.messageMapper.insert(message);
            AppPush.pushMessageToApp(user.getClientId(), title, content);
            return RestResultGenerator.genSuccessResult();
        } else {
            return RestResultGenerator.genErrorResult("用户不存在");
        }
    }

    // 查询消息
    @GetMapping("/message")
    public RestResult messageList(@RequestHeader(value = "uid") long uid,
                                  @RequestParam(value = "pageNum",required = false, defaultValue = "1") int pageNum) {
        Page<Message> page = PageHelper.startPage(pageNum, 20, "id desc").doSelectPage(()-> this.messageMapper.selectByUid(uid));
        return RestResultGenerator.genSuccessResult(page);
    }

    // 删除消息
    @DeleteMapping("/message")
    public RestResult deleteMessage(@RequestHeader(value = "uid") long uid,
                                    @RequestParam(value = "msgId") long msgId) {
        Message message = this.messageMapper.selectById(msgId);
        if (message != null && message.getUid() == uid) {
            this.messageMapper.deleteById(msgId);
            return RestResultGenerator.genSuccessResult();
        } else {
            return RestResultGenerator.genErrorResult("没有该条消息");
        }
    }

}
