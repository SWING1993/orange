## orange
1. orange是一个自用的JavaWeb项目，目前已开发的功能有登录/ASF配置（ArchiSteamFarm是Steam挂卡工具）/SSR配置(shadowsocks)/朋友圈/消息推送（向服务器发送消息可实现消息存储并转发到客户端，推送使用个推)等功能。
2. 项目基础框架采用SpringBoot2.1.2，消除了繁杂的XML配置，使得二次开发更为简单；数据访问层采用Mybatis，同时引入了通用Mapper和PageHelper插件，可快速高效的对单表进行增删改查操作，消除了大量传统XML配置SQL的代码；监控服务使用Actuator；api采用RESTful架构，使用OAuth2/JWT/参数签名为服务提供安全保障。
