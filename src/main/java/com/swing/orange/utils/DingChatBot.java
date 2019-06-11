package com.swing.orange.utils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

// 钉钉消息机器人，推送异常到钉钉群里，省去查日志的麻烦，也可以推送别的消息。
public class DingChatBot {

    public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=15018194c2e2025afd72ff340a438fa445608eeecb1bf62f45816f5919760580";

    public static int sendMsg(String msg) {
        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \" " + msg  + "\"}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        try {
            HttpResponse response = httpclient.execute(httppost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                String result= EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(result);
            }
            return statusCode;
        } catch (Exception e) {
            return 0;
        }


    }
}
