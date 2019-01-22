package com.swing.orange.controller;

import com.swing.orange.dao.UserDao;
import com.swing.orange.entity.User;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController("/user")
public class UserController {

    @Autowired
    private UserDao userRepository;

    @PostMapping
    public RestResult saveUser(@RequestParam(value = "phone") String phone ,@RequestParam(value = "password") String password) {
        Date date = new Date();
        userRepository.save(new User(phone, password, date));
        return RestResultGenerator.genSuccessResult();
    }

    @DeleteMapping
    public RestResult deleteUser(@RequestParam(value = "uid") long uid){
        User user = this.userRepository.findById(uid);
        System.out.println("deleteUser: " + user);
        this.userRepository.delete(user);
        return RestResultGenerator.genSuccessResult();
    }

    @PutMapping
    public RestResult<User> updateUser(@RequestParam(value = "id") long id,
                                 @RequestParam(value = "email", required = false) String email,
                                 @RequestParam(value = "nickname", required = false) String nickname,
                                 @RequestParam(value = "sex", required = false, defaultValue = "0") byte sex,
                                 @RequestParam(value = "avatarUrl", required = false) String avatarUrl,
                                 @RequestParam(value = "userDesc", required = false) String userDesc) {
        User user = this.userRepository.findById(id);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setSex(sex);
        user.setAvatarUrl(avatarUrl);
        user.setUserDesc(userDesc);
        user.setUpdated(new Date());
        this.userRepository.save(user);
        return RestResultGenerator.genSuccessResult(user);
    }

    @GetMapping
    public RestResult<User> finUserById(@RequestParam(value = "id") long id) {
        User user = this.userRepository.findById(id);
        return RestResultGenerator.genSuccessResult(user);
    }
}
