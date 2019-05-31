package com.zwl.mall.controller;

import com.zwl.mall.system.config.wx.WxMpConfiguration;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 二师兄
 * @Title: PushController
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/5/2816:55
 */
@RestController
public class PushController {


    /*
     * 微信测试账号推送
     * */
    @GetMapping("/push")
    public void push() {
        WxMpService mpService = WxMpConfiguration.getMpServices().get("wx5f7a8f54615b48ed");

        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("o0h5h1Vvlmp7Z4leij7gnuTg65ak")//要推送的用户openid
                .templateId("LpqRcqB3CSUKYXSvQ7r4NDBEBLzIMIiSgqqe6n_6xgs")//模版id
                .url("baidu.com")//点击模版消息要访问的网址
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        //        templateMessage.addData(new WxMpTemplateData("name", "value", "#FF00FF"));
        //                templateMessage.addData(new WxMpTemplateData(name2, value2, color2));
        try {
            mpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }

    }


}
