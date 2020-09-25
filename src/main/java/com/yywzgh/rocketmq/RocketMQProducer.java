package com.yywzgh.rocketmq;

import java.util.Date;

import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.RPCHook;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class RocketMQProducer {

	// 设置为您在阿里云 RocketMQ 控制台上创建的 GID, 以及替换为您的 AccessKeyId 和 AccessKeySecret
	private static RPCHook getAclRPCHook() {
		return new AclClientRPCHook(new SessionCredentials("RocketMQ", "12345678"));
	}

	public static void main(String[] args) throws MQClientException {
		/**
		 * 创建 Producer，并开启消息轨迹 如果不想开启消息轨迹，可以按照如下方式创建： DefaultMQProducer producer = new
		 * DefaultMQProducer("YOUR GROUP ID", getAclRPCHook());
		 */
		DefaultMQProducer producer = new DefaultMQProducer("task-group", getAclRPCHook(), true, null);
		
		//DefaultMQProducer producer = new DefaultMQProducer("task-group");
		
		/**
		 * 设置使用接入方式为阿里云，在使用云上消息轨迹的时候，需要设置此项，如果不开启消息轨迹功能，则运行不设置此项.
		 */
		//producer.setAccessChannel(AccessChannel.CLOUD);
		/**
		 * 设置为您从阿里云控制台获取的接入点信息，类似“http://MQ_INST_XXXX.aliyuncs.com:80”ss
		 */
		producer.setNamesrvAddr("192.168.1.1:9876");
		producer.start();

		for (int i = 0; i < 128; i++) {
			try {
				Message msg = new Message("topic-test", "test",
						"Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
				SendResult sendResult = producer.send(msg);
				System.out.printf("%s%n", sendResult);
			} catch (Exception e) {
				// 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
				System.out.println(new Date() + " Send mq message failed.");
				e.printStackTrace();
			}
		}

		// 在应用退出前，销毁 Producer 对象
		// 注意：如果不销毁也没有问题
		producer.shutdown();
	}

}
