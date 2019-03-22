package com.swing.orange;

import com.swing.orange.entity.Message;
import com.swing.orange.entity.User;
import com.swing.orange.mapper.MessageMapper;
import com.swing.orange.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageMapperTest {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertTest() {
        User user = this.userMapper.getAll().get(0);
        Message message = new Message(user.getId(), 0, "title", "subtitle", "2...测试一下哈", 0);
        this.messageMapper.insert(message);
    }

    @Test
    public void getAllTest() {
        List<Message> list = this.messageMapper.getAll();
        System.out.println(list);
    }

    @Test
    public void selectByUidTest() {
        List<Message> messagesList = this.messageMapper.getAll();
        Message status = messagesList.get(messagesList.size()-1);
        List<Message> list = this.messageMapper.selectByUid(status.getUid());
        System.out.println(list);
    }

    @Test
    public void deleteByIdTest() {
        List<Message> messageList = this.messageMapper.getAll();
        Message message = messageList.get(0);
        this.messageMapper.deleteById(message.getId());
    }
}
