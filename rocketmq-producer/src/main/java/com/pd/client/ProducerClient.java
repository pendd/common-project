package com.pd.client;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProducerClient {

    private final RocketMQTemplate rocketMQTemplate;
    private final String topic = "plc_test_topic";

    public ProducerClient(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void sendMessage(String message) {
        rocketMQTemplate.convertAndSend(topic, message);
        log.info("发生消息成功!");
    }

    public void sendSyncMessage(String id, String message) {
        Message<String> strMessage = MessageBuilder.withPayload(message).setHeader(RocketMQHeaders.KEYS, id).build();
        SendResult result = rocketMQTemplate.syncSend(topic, strMessage);
        log.info("发送简单同步消息成功!返回信息为:{}", JSONUtil.toJsonStr(result));
    }

    public void sendAsyncMessage(String id, String message) {
        Message<String> strMessage = MessageBuilder.withPayload(message).setHeader(RocketMQHeaders.KEYS, id).build();
        rocketMQTemplate.asyncSend(topic, strMessage, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                    log.info("send success, topic:{}, json:{}", topic, JSONUtil.toJsonStr(sendResult));
                }
            }
            @Override
            public void onException(Throwable throwable) {
                log.error("send failure, topic:{}, json:{}", topic, throwable.getMessage());
            }
        });
    }

    public void sendOnewayMessage(String id, String message) {
        Message<String> strMessage = MessageBuilder.withPayload(message).setHeader(RocketMQHeaders.KEYS, id).build();
        rocketMQTemplate.sendOneWay(topic, strMessage);
    }
}
