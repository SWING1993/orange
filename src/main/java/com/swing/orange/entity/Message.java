package com.swing.orange.entity;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    // 消息id
    private long id;
    // 用户id
    private long uid;
    // 类型
    private int type;
    // 内容
    private String content;
    // 标题
    private String title;
    // 创建时间
    private long created;

    public Message(long uid, int type, String title, String content, long created) {
        this.uid = uid;
        this.type = type;
        this.title = title;
        this.content = content;
        this.created = created;
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

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
