package com.swing.orange.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String phone;

    @JsonIgnore
    private String password;

    private String email;

    private Date created;

    private Date updated;

    private String nickname;

    private Byte sex;

    private String avatarUrl;

    private String userDesc;

    private String token;

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

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
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