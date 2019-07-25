package com.zwl.mall.rabbitmq;

//import com.alibaba.fastjson.JSONObject;
//import com.rabbitmq.client.Channel;
//import com.xc.mall.db.model.Goods;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.messaging.handler.annotation.Headers;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.util.Map;

/**
 * @author 二师兄
 * @Title: 消费优惠券
 * @ProjectName mall-parent
 * @Description: TODO
 * @date 2019/5/914:20
 */
//@Component
//@Slf4j
//public class CouponConsumer {
//
//    @RabbitListener(queues = "coupon_queue")
//    @Transactional
//    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
//        String messageId = message.getMessageProperties().getMessageId();
//        String msg = new String(message.getBody(), "UTF-8");
//        log.info(">>>messageId:{},msg:{}", messageId, msg);
//        JSONObject jsonObject = JSONObject.parseObject(msg);
//        Long uid = jsonObject.getLong("uid");
//        String orderSn = jsonObject.getString("orderSn");
//        JSONObject goodsStr = jsonObject.getJSONObject("goods");
//        Goods goods = JSONObject.toJavaObject(goodsStr, Goods.class);
//        /**执行发送优惠券开始**/
//        boolean sendFlag = needSend(goods, uid);
//        if (sendFlag) {
//            //DB 操作
//        }
//        /**执行发送优惠券结束**/
//        log.info("发送优惠券" + uid + "成功,订单号为：：" + orderSn);
//    }
//
//    private boolean needSend(Goods goods, Long uid) {
////        ----------------------------------------
////        ----------------------------------------
////        ----------------------------------------
////        ----------------------------------------
////        ----------------------------------------
////        ----------------------------------------
////        ----------------------------------------
////        经过一系列判断最终组装需要发送优惠券的必要参数
//
//        return true;
//    }
//}
