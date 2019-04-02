package com.swing.orange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    // 用户id
    private long id;

    // 手机号码
    private String phone;

    // 密码
    @JsonIgnore
    private String password;

    // 邮箱
    private String email;

    // 创建时间
    private Date created;

    // 资料更新时间
    private Date updated;

    // 昵称
    private String nickname;

    // 性别
    private int sex;

    // 头像
    private String avatarUrl;

    // 用户描述
    private String userDesc;

    // 用户token
    private String token;

    // 推送的clientId
    private String clientId;

    // 接受外部消息的token
    private String messageToken;

    public User() {
        super();
    }

    public User(String phone, String email, String password, Date created) {
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMessageToken() {
        return messageToken;
    }

    public void setMessageToken(String messageToken) {
        this.messageToken = messageToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", userDesc='" + userDesc + '\'' +
                ", token='" + token + '\'' +
                ", clientId='" + clientId + '\'' +
                ", messageToken='" + messageToken + '\'' +
                '}';
    }
}


/*

CREATE TABLE `user_tbl` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(15) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(40) NOT NULL,
  `created` date DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `avatarUrl` varchar(255) DEFAULT NULL,
  `userDesc` varchar(255) DEFAULT NULL,
  `updated` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
 */