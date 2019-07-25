package com.zwl.mall.rabbitmq;//package com.xc.mall.web.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
///**
// *
// * RabbitmqConfig 配置
// *
// * @description:
// * @author: 二师兄
// * @contact: QQ382308664
// * @date: 2019年1月3日 下午3:03:17
// * @version V1.0
// * @Copyright
// */
//@Component
//public class RabbitmqConfig {
//
//	// 添加修改库存队列
//	public static final String MODIFY_INVENTORY_QUEUE = "dy_init_queue";
//	// 交换机名称
//	private static final String MODIFY_EXCHANGE_NAME = "dy_init_exchange";
//
//	// 1.添加交换机队列
//	@Bean
//	public Queue directModifyInventoryQueue() {
//		return new Queue(MODIFY_INVENTORY_QUEUE);
//	}
//
//	// 2.定义交换机
//	@Bean
//	DirectExchange directModifyExchange() {
//		return new DirectExchange(MODIFY_EXCHANGE_NAME);
//	}
//
//	// 3.修改库存队列绑定交换机
//	@Bean
//	Binding bindingExchangeintegralDicQueue() {
//		return BindingBuilder.bind(directModifyInventoryQueue()).to(directModifyExchange()).with("modifyRoutingKey");
//	}
//
//}
