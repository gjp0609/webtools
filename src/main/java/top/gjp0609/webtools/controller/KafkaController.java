package top.gjp0609.webtools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.gjp0609.webtools.common.aop.LoggerManage;
import top.gjp0609.webtools.service.KafkaService;

//@Controller
//@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaService kafkaService;

    @Autowired
    public KafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @GetMapping(value = "/sendMsg/{msg}")
    @ResponseBody
    @LoggerManage("kafka_sendMsg")
    public void sendMsg(@PathVariable("msg") String msg) {
        kafkaService.sendMessage("topic1", msg);
    }
}
