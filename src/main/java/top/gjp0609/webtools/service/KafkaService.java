package top.gjp0609.webtools.service;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

//@Component
public class KafkaService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final JavaMailSender mailSender;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaService(
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
                    KafkaTemplate<String, String> kafkaTemplate, JavaMailSender mailSender) {
        this.kafkaTemplate = kafkaTemplate;
        this.mailSender = mailSender;
    }

    public void sendMessage(String topicName, String jsonData) {
        log.info("Kafka生产方-> 向{}推送数据: [{}]，发送中", topicName, jsonData);
        try {
            kafkaTemplate.send(topicName, jsonData);
        } catch (Exception e) {
            log.error("Kafka生产方-> 发送数据出错！！！{}{}", topicName, jsonData);
            log.error("Kafka生产方-> 发送数据出错=====>", e);
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
                log.info("Kafka生产方-> 向{}推送数据: [{}]，数据发送完毕", topicName, jsonData);
                return false;
            }
        });
    }

    @KafkaListener(topics = {"topic1"})
    public void processMessage(String content) {
        log.info("Kafka消费方-> topic1：[{}]，处理中。。。", content);
        // 此处调用服务
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("me@***.com");
        msg.setTo("user@***.com");
        msg.setSubject("Test Mail");
        msg.setText("测试邮件内容:" + content);
        mailSender.send(msg);
        log.info("Kafka消费方-> topic1：[{}]，处理完成", content);
    }
}
