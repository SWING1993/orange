package com.swing.orange.service;

import com.swing.orange.entity.Message;
import com.swing.orange.entity.User;
import com.swing.orange.mapper.MessageMapper;
import com.swing.orange.mapper.UserMapper;
import com.swing.orange.utils.AppPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MessageMapper messageMapper;
    /*
    cron 通过表达式来配置任务执行时间
    秒（0~59）分钟（0~59）小时（0~23）天（0~31）月（0~11）星期（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
     */
    @Scheduled(cron = "0 50 18 * * 1-5")
    public void scheduled() {
        List<User> userList = userMapper.getAll();
        for (User user:userList) {
            Message message = new Message(user.getId(), 0, "打卡", "人生苦短，及时行乐。", System.currentTimeMillis());
            this.messageMapper.insert(message);
            AppPush.pushMessageToApp(message.getTitle(), message.getContent());
        }
        System.out.println("scheduled task");
    }

//    @Scheduled(cron = "0 50 18 * * 1-5")
//    public void scheduled() {
//        List<User> userList = userMapper.getAll();
//        for (User user:userList) {
//            Message message = new Message(user.getId(), 0, "打卡", "人生苦短，及时行乐。", System.currentTimeMillis());
//            this.messageMapper.insert(message);
//            AppPush.pushMessageToApp(message.getTitle(), message.getContent());
//        }
//        System.out.println("scheduled task");
//    }

}
