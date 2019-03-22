package com.swing.orange.utils;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class AppPush {
    private static String kAppId = "XotSLiKHSX7iswSsQlJir8";
    private static String kAppKey = "ZjzdJP5fNH9BSWg8MMHek";
    private static String kMasterSecret = "ZgBzZA69ad6PfwJ4XKonl9";
    private static String kHost = "http://sdk.open.api.igexin.com/apiex.htm";

    public static void pushMessageToApp(String clientId, String body) {
        AppPush.pushMessageToApp(clientId, "", "", body);
    }

    public static void pushMessageToApp(String clientId, String title, String body) {
        AppPush.pushMessageToApp(clientId, title, "", body);

    }

    public static void pushMessageToApp(String clientId, String title, String subtitle, String body) {
        IGtPush push = new IGtPush(kHost, kAppKey, kMasterSecret);
        TransmissionTemplate template = getTemplate(title, subtitle, body);

        SingleMessage message = new SingleMessage();
        message.setData(template);
        message.setOffline(true);
        message.setOfflineExpireTime(24 * 1000 * 3600);

        Target target = new Target();
        target.setAppId(kAppId);
        target.setClientId(clientId);
        IPushResult ret = push.pushMessageToSingle(message, target);
        System.out.println(clientId + "  " + ret.getResponse().toString());
    }

    public static TransmissionTemplate getTemplate(String title, String subtitle, String body) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(kAppId);
        template.setAppkey(kAppKey);
        // 透传内容，不支持转义字符
        template.setTransmissionContent("透传内容");
        // 收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动
        template.setTransmissionType(2);

        APNPayload payload = new APNPayload();

        // 设置角标，还可以实现显示数字的自动增减，如"+1"、"-1"、"1"等
        payload.setAutoBadge("+1");

        // 推送直接带有透传数据
        payload.setContentAvailable(1);

        // sound支持Dictionary类型，可以控制“警告性质的推送”，仅支持iOS 12.0以上版本
        // payload.setSound("default");

        // 在客户端通知栏触发特定的action和button显示
        payload.setCategory("$由客户端定义");

        // 增加自定义的数据,Key-Value形式
        payload.addCustomMsg("payload", "payload");

        //简单模式APNPayload.SimpleMsg
        //payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));

        //字典模式使用APNPayload.DictionaryAlertMsg
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setTitle(title);
        alertMsg.setSubtitle(subtitle);
        alertMsg.setBody(body);
        payload.setAlertMsg(alertMsg);

        template.setAPNInfo(payload);
        return template;
    }

}
