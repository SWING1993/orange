package com.swing.orange.controller;

import com.swing.orange.Authentication.JWTUtil;
import com.swing.orange.entity.User;
import com.swing.orange.mapper.UserMapper;
import com.swing.orange.utils.Md5;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.swing.orange.service.MailService;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailService mailService;

    private HashMap<String, String> authCodeMap = new HashMap<>();

    private static final String passwdSalt = "orange_password";

    // 获取验证码
    @GetMapping("/user/authCode")
    public RestResult getAuthCode(@RequestParam(value = "email") String email) {
        String authCode = this.achieveCode();
        this.mailService.sendHtmlMail(email, "注册验证码", "您好，您的注册验证码为" + authCode);
        authCodeMap.put(email, authCode);
        return RestResultGenerator.genSuccessResult();
    }

    // 注册
    @PostMapping("/user/register")
    public RestResult saveUser(@RequestParam(value = "phone") String phone,
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "authCode") String authCode) {
//        if (!this.authCodeMap.get(email).equals(authCode)) {
//            return RestResultGenerator.genErrorResult("验证码错误！");
//        }
//        authCodeMap.remove(email);
        userMapper.insert(new User(phone, email, Md5.getMd5(password, passwdSalt), new Date()));
        return RestResultGenerator.genSuccessResult();
    }

    // phone登录
    @PostMapping("/user/login")
    public RestResult login(@RequestParam(value = "phone") String phone,
                            @RequestParam(value = "password") String password) {
        User user = this.userMapper.selectByPhone(phone);
        System.out.println("user:" + user);
        if (user == null) {
            return RestResultGenerator.genErrorResult("没有手机号码为" + phone + "的用户");
        }
        if (!user.getPassword().equals(Md5.getMd5(password, passwdSalt))) {
            return RestResultGenerator.genErrorResult("密码错误！");
        }
        String token = JWTUtil.sign(String.valueOf(user.getId()), user.getPassword());
        user.setToken(token);
        return RestResultGenerator.genSuccessResult(user);
    }

    // email登录
    @PostMapping("/user/loginByEmail")
    public RestResult loginByEmail(@RequestParam(value = "email") String email,
                                   @RequestParam(value = "password") String password) {
        User user = this.userMapper.selectByEmail(email);
        if (user == null) {
            return RestResultGenerator.genErrorResult("没有邮箱为" + email + "的用户");
        }
        if (!user.getPassword().equals(Md5.getMd5(password, passwdSalt))) {
            return RestResultGenerator.genErrorResult("密码错误！");
        }
        user.setToken(JWTUtil.sign(String.valueOf(user.getId()), user.getPassword()));
        return RestResultGenerator.genSuccessResult(user);
    }

    // 刷新用户token,
    @GetMapping("/user/refreshToekn")
    public RestResult refreshToekn(@RequestHeader(value = "uid") String uid) {
        User user = this.userMapper.selectById(Long.valueOf(uid));
        String token = JWTUtil.sign(uid, user.getPassword());
        HashMap map = new HashMap();
        map.put("token", token);
        return RestResultGenerator.genSuccessResult(map);
    }

    // 删除用户
//    @DeleteMapping("/user")
//    public RestResult deleteUser(@RequestParam(value = "uid") Long uid){
//        this.userMapper.deleteById(uid);
//        return RestResultGenerator.genSuccessResult();
//    }

    // 修改用户资料
    @PutMapping("/user")
    public RestResult<User> updateUser(@RequestParam(value = "id") long id,
                                       @RequestParam(value = "email", required = false) String email,
                                       @RequestParam(value = "nickname", required = false) String nickname,
                                       @RequestParam(value = "sex", required = false, defaultValue = "0") byte sex,
                                       @RequestParam(value = "avatarUrl", required = false) String avatarUrl,
                                       @RequestParam(value = "userDesc", required = false) String userDesc) {
        User user = this.userMapper.selectById(id);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setSex(sex);
        user.setAvatarUrl(avatarUrl);
        user.setUserDesc(userDesc);
        user.setUpdated(new Date());
        this.userMapper.update(user);
        return RestResultGenerator.genSuccessResult(user);
    }

    // 根据id查询用户资料
    @GetMapping("/user")
    public RestResult<User> finUserById(@RequestParam(value = "id") Long id) {
        User user = this.userMapper.selectById(id);
        return RestResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/user/updateClientId")
    public RestResult updateClientId(@RequestHeader(value = "uid") long uid,
                                     @RequestParam(value = "clientId") String clientId) {
        System.out.println("clientId:" + clientId + "  uid:" + uid);
        this.userMapper.updateClientId(clientId, uid);
        return RestResultGenerator.genSuccessResult("ok");
    }

    // 生成一个随机的4位验证码
    public String achieveCode() {
        /*
        // 由于数字1,0 和字母O,l 有时分不清，所有没有字母1 、0
        String[] beforeShuffle= new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a",
                "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z" };
        */
        String[] beforeShuffle= new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List list = Arrays.asList(beforeShuffle);
        // 将数组转换为集合
        Collections.shuffle(list);
        // 打乱集合顺序
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)); //将集合转化为字符串
        }
        return sb.toString().substring(3, 8);  //截取字符串第4到8
    }

}
