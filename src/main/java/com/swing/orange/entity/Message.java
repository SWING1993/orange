package com.swing.orange.entity;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private long uid;
    private int type;
    private String content;
    private long created;

    public Message(long uid, int type, String content, long created) {
        this.uid = uid;
        this.type = type;
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
