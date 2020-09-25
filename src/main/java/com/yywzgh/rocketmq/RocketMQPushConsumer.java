package com.yywzgh.rocketmq;

import java.util.List;

import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.AccessChannel;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.RPCHook;

public class RocketMQPushConsumer {

	// 设置为您在阿里云 RocketMQ 控制台上创建的 GID, 以及替换为您的 AccessKeyId 和 AccessKeySecret
	private static RPCHook getAclRPCHook() {
		return new AclClientRPCHook(new SessionCredentials("RocketMQ", "12345678"));
	}

	public static void main(String[] args) throws MQClientException {
		// 设置为您在阿里云 RocketMQ 控制台上创建的 GID, 以及替换为您阿里云账号的 AccessKeyId 和 AccessKeySecret
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("task-group", getAclRPCHook(), new AllocateMessageQueueAveragely());
		// 设置为阿里云 RocketMQ 实例的接入点
		consumer.setNamesrvAddr("192.168.1.1:9876");
		// 阿里云上消息轨迹需要设置为 CLOUD 方式
		consumer.setAccessChannel(AccessChannel.CLOUD);
		// 设置为您在阿里云 RocketMQ 控制台上创建的 Topic
		consumer.subscribe("topic-test", "test");
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.printf("Receive New Messages: %s %n", msgs);
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();
	}

}
