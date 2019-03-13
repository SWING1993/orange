package com.swing.orange.entity;

import java.io.Serializable;

public class Status implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private long uid;
    // 状态类型 0 = 图文  1 = 视频
    private int type;
    private String avatar;
    private String avatarUrl;
    private String content;
    private String imageUrls;
    private String vedioUrl;
    private long created;
    private String fromDevice;

    public Status() {
        super();
    }

    public Status(long uid, int type, String avatar, String avatarUrl, String content, String imageUrls, String vedioUrl, long created, String fromDevice) {
        this.uid = uid;
        this.type = type;
        this.avatar = avatar;
        this.avatarUrl = avatarUrl;
        this.content = content;
        this.imageUrls = imageUrls;
        this.vedioUrl = vedioUrl;
        this.created = created;
        this.fromDevice = fromDevice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getVedioUrl() {
        return vedioUrl;
    }

    public void setVedioUrl(String vedioUrl) {
        this.vedioUrl = vedioUrl;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public String getFromDevice() {
        return fromDevice;
    }

    public void setFromDevice(String fromDevice) {
        this.fromDevice = fromDevice;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", uid=" + uid +
                ", type=" + type +
                ", avatar='" + avatar + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", content='" + content + '\'' +
                ", imageUrls='" + imageUrls + '\'' +
                ", vedioUrl='" + vedioUrl + '\'' +
                ", created=" + created +
                ", fromDevice='" + fromDevice + '\'' +
                '}';
    }
}

/*

CREATE TABLE `status_tbl` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `type` int unsigned DEFAULT NULL,
  `avatar` varchar(40) DEFAULT NULL,
  `avatarUrl` varchar(120) DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `imageUrls` varchar(400) DEFAULT NULL,
  `vedioUrl` varchar(255) DEFAULT NULL,
  `fromDevice` varchar(50) DEFAULT NULL,
  `created` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
 */
