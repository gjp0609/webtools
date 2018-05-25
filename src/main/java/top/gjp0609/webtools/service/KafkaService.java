package top.gjp0609.webtools.service;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static Long sendCount = 0L;
    private static Long processCount = 0L;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName, String jsonData) {
        log.info("生产方-> 向{}推送数据: [{}]，发送中", topicName, jsonData);
        try {
            kafkaTemplate.send(topicName, jsonData);
        } catch (Exception e) {
            log.error("生产方-> 发送数据出错！！！{}{}", topicName, jsonData);
            log.error("生产方-> 发送数据出错=====>", e);
        }

        //消息发送的监听器，用于回调返回信息
        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
            }

            @Override
            public void onError(String topic, Integer partition, String key, String value, Exception exception) {
            }

            @Override
            public boolean isInterestedInSuccess() {
                log.info("生产方-> 向{}推送数据: [{}]，数据发送完毕，已发送{}", topicName, jsonData, ++sendCount);
                return false;
            }
        });
    }

    @KafkaListener(topics = {"topic1"})
    public void processMessage(String content) {
        log.info("消费方-> topic1：[{}]，处理中。。。", content);
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info("消费方-> topic1：[{}]，处理完成，已处理{}", content, ++processCount);
    }
}
