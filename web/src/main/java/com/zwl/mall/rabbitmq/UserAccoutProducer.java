//package com.zwl.mall.rabbitmq;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageBuilder;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.UUID;
//
///**
// * @author 二师兄
// * @Title: 发送优惠券
// * @ProjectName mall-parent
// * @Description: TODO
// * @date 2019/5/914:20
// */
//@Component
//@Slf4j
//public class CouponProducer implements RabbitTemplate.ConfirmCallback {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Transactional
//    public void send(JSONObject jsonObject) {
//        String jsonString = jsonObject.toJSONString();
//        String messAgeId = UUID.randomUUID().toString().replace("-", "");
//        // 封装消息
//        Message message = MessageBuilder.withBody(jsonString.getBytes())
//                .setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8").setMessageId(messAgeId)
//                .build();
//        // 构建回调返回的数据（消息id）
//        this.rabbitTemplate.setMandatory(true);
//        this.rabbitTemplate.setConfirmCallback(this);
//        CorrelationData correlationData = new CorrelationData(jsonString);
//        rabbitTemplate.convertAndSend("coupon_exchange", "coupon", message, correlationData);
//
//    }
//
//
//
//
//    /**
//     * 生产消息确认机制 生产者往服务器端发送消息的时候，采用应答机制
//     *
//     * @param correlationData
//     * @param ack
//     * @param cause
//     */
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        String jsonString = correlationData.getId();
////        log.info("消息id:" + correlationData.getId());
//        if (ack) {
//            log.info(">>>使用MQ消息确认机制确保消息一定要投递到MQ中成功");
//            return;
//        }
//        JSONObject jsonObject = JSONObject.parseObject(jsonString);
//        // 生产者消息投递失败的话，采用递归重试机制
//        send(jsonObject);
//        log.info(">>>使用MQ消息确认机制投递到MQ中失败");
//    }
//}
