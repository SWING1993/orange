package com.swing.orange;

import com.swing.orange.entity.User;
import com.swing.orange.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        userMapper.insert(new User("18667905583", "10446627@qq.com", "123456", new Date()));
        System.out.println("插入User:18667905583");

    }


    @Test
    public void selectUserByPhone() {
        User user = userMapper.selectByPhone("18667905583");
        System.out.println(user);
    }


    @Test
    public void selectUserByEmail() {
        User user = userMapper.selectByEmail("10446627@qq.com");
        System.out.println(user);
    }

    @Test
    public void selectUserById() {
        User user = userMapper.selectByPhone("18667905583");
        if (user != null) {
            User newUser = userMapper.selectById(user.getId());
            System.out.println(newUser);
        }
    }


    @Test
    public void testGetAll() {
        List<User> users = userMapper.getAll();
        System.out.println(users);
    }

    @Test
    public void testDeleteById() {
        User user = userMapper.selectByPhone("18667905583");
        if (user != null) {
            userMapper.deleteById(user.getId());
            System.out.println("删除User:" + user.getPhone());
        }
    }

}
