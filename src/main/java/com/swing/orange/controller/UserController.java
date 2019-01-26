package com.swing.orange.controller;

import com.swing.orange.dao.UserDao;
import com.swing.orange.entity.User;
import com.swing.orange.utils.Md5;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private UserDao userRepository;

    private static final String passwdSalt = "xxzz00";

    @PostMapping("/user/register")
    public RestResult saveUser(@RequestParam(value = "phone") String phone ,@RequestParam(value = "password") String password) {
        Date date = new Date();
        userRepository.save(new User(phone, Md5.getMd5(password, passwdSalt), date));
        return RestResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/user")
    public RestResult deleteUser(@RequestParam(value = "uid") long uid){
        User user = this.userRepository.findById(uid);
        System.out.println("deleteUser: " + user);
        this.userRepository.delete(user);
        return RestResultGenerator.genSuccessResult();
    }

    @PutMapping("/user")
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

    @GetMapping("/user")
    public RestResult<User> finUserById(@RequestParam(value = "id") long id) {
        User user = this.userRepository.findById(id);
        return RestResultGenerator.genSuccessResult(user);
    }
}
