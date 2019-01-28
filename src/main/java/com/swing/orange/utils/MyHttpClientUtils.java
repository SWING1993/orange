package com.swing.orange.utils;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyHttpClientUtils {

    public static RestResult getHandle(String url) throws Exception {
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-Type", "application/json; charset=utf-8");
        httpGet.addHeader(Signature.SIGN_KEY, Signature.createSign(new HashMap<String, Object>()));

        HttpResponse response = httpclient.execute(httpGet);
        if (response != null) {
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            Gson gson = new Gson();
            RestResult result = gson.fromJson(content, RestResult.class);
            System.out.println("getHandle："+result);
            return result;
        }
        return RestResultGenerator.genErrorResult("xyz服务器异常");
    }

    public static RestResult postHandle(String url, List<? extends NameValuePair> parameters) throws Exception {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringBuilder paramStr = new StringBuilder();
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < parameters.size(); i ++) {
            NameValuePair nameValuePair = parameters.get(i);
            map.put(nameValuePair.getName(), nameValuePair.getValue());
            paramStr.append(nameValuePair.getName() + "=" + nameValuePair.getValue());
            if (i != parameters.size() - 1) {
                paramStr.append("&");
            }
        }
        System.out.println("postHandle paramStr :" + paramStr);
        /*
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("scope", "project"));
        parameters.add(new BasicNameValuePair("q", "java"));
        */
        // 构造一个form表单式的实体
        // UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
        // 将请求实体设置到httpPost对象中
        // httpPost.setEntity(formEntity);

        StringEntity stringEntity = new StringEntity(paramStr.toString(), "UTF-8");
        stringEntity.setContentType("application/x-www-form-urlencoded");
        httpPost.setEntity(stringEntity);

        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        httpPost.addHeader(Signature.SIGN_KEY, Signature.createSign(map));

        HttpResponse response = httpclient.execute(httpPost);
        if (response != null) {
            String content = EntityUtils.toString(response.getEntity(), "UTF-8");
            Gson gson = new Gson();
            RestResult result = gson.fromJson(content, RestResult.class);
            System.out.println("postHandle："+result);
            return result;
        }
        return RestResultGenerator.genErrorResult("xyz服务器异常");
    }
}
