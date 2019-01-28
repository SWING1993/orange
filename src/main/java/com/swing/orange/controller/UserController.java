package com.swing.orange.controller;

import com.swing.orange.entity.User;
import com.swing.orange.mapper.UserMapper;
import com.swing.orange.utils.Md5;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    private static final String passwdSalt = "orange_password";

    @PostMapping("/user/register")
    public RestResult saveUser(@RequestParam(value = "phone") String phone, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        userMapper.insert(new User(phone, email, Md5.getMd5(password, passwdSalt), new Date()));
        return RestResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/user")
    public RestResult deleteUser(@RequestParam(value = "uid") Long uid){
        this.userMapper.deleteById(uid);
        return RestResultGenerator.genSuccessResult();
    }

//    @PutMapping("/user")
//    public RestResult<User> updateUser(@RequestParam(value = "id") long id,
//                                 @RequestParam(value = "email", required = false) String email,
//                                 @RequestParam(value = "nickname", required = false) String nickname,
//                                 @RequestParam(value = "sex", required = false, defaultValue = "0") byte sex,
//                                 @RequestParam(value = "avatarUrl", required = false) String avatarUrl,
//                                 @RequestParam(value = "userDesc", required = false) String userDesc) {
//        User user = this.userRepository.findById(id);
//        user.setEmail(email);
//        user.setNickname(nickname);
//        user.setSex(sex);
//        user.setAvatarUrl(avatarUrl);
//        user.setUserDesc(userDesc);
//        user.setUpdated(new Date());
//        this.userRepository.save(user);
//        return RestResultGenerator.genSuccessResult(user);
//    }

    @GetMapping("/user")
    public RestResult<User> finUserById(@RequestParam(value = "id") Long id) {
        User user = this.userMapper.selectById(id);
        return RestResultGenerator.genSuccessResult(user);
    }
}
