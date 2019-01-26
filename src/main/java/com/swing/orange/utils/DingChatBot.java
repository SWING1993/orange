package com.swing.orange.utils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class DingChatBot {

    public static final String WEBHOOK_TOKEN = "";

    public static int sendMsg(String msg) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");

        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \" " + msg  + "\"}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);

        HttpResponse response = httpclient.execute(httppost);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            String result= EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
        return statusCode;
    }
}
