package com.swing.orange.utils;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.MultiMedia;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import java.util.ArrayList;
import java.util.List;

public class AppPush {
    private static String kAppId = "XotSLiKHSX7iswSsQlJir8";
    private static String kAppKey = "ZjzdJP5fNH9BSWg8MMHek";
    private static String kMasterSecret = "ZgBzZA69ad6PfwJ4XKonl9";
    private static String kHost = "http://sdk.open.api.igexin.com/apiex.htm";

    public static void pushMessageToApp() {

        IGtPush push = new IGtPush(kHost, kAppKey, kMasterSecret);
        TransmissionTemplate template = getTemplate();

        AppMessage message = new AppMessage();
        message.setData(template);
        message.setOffline(true);
        message.setOfflineExpireTime(24 * 1000 * 3600);

        List<String> appIdList = new ArrayList<String>();
        appIdList.add(kAppId);
        message.setAppIdList(appIdList);
        IPushResult ret = push.pushMessageToApp(message);;
        System.out.println(ret.getResponse().toString());
    }

    public static TransmissionTemplate getTemplate() {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(kAppId);
        template.setAppkey(kAppKey);
        template.setTransmissionContent("透传内容");
        template.setTransmissionType(2);
        APNPayload payload = new APNPayload();
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge("+1");
        payload.setContentAvailable(1);
        //ios 12.0 以上可以使用 Dictionary 类型的 sound
        payload.setSound("default");
        payload.setCategory("$由客户端定义");
        payload.addCustomMsg("payload", "payload");

        //简单模式APNPayload.SimpleMsg
        //payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));

        //字典模式使用APNPayload.DictionaryAlertMsg
        payload.setAlertMsg(getDictionaryAlertMsg());

        template.setAPNInfo(payload);
        return template;
    }
    private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(){
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();

        alertMsg.setTitle("😝O(∩_∩)O哈！");
        alertMsg.setTitleLocKey("TitleLocKey");
        alertMsg.addTitleLocArg("TitleLocArg");

        alertMsg.setBody("(*^__^*) 嘻嘻……");
        alertMsg.setActionLocKey("ActionLockey");
        alertMsg.setLocKey("LocKey");
        alertMsg.addLocArg("loc-args");

        return alertMsg;
    }
}
