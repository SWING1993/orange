package com.swing.orange.service;

public interface MailService {
    void sendHtmlMail(String to, String subject, String content);
}
