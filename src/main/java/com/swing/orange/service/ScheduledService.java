package com.swing.orange.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledService {

    /*
    cron 通过表达式来配置任务执行时间
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){

    }

}
