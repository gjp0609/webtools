package top.gjp0609.webtools;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.gjp0609.webtools.service.KafkaService;

/**
 * 缓存性能测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTests {

    @Autowired
    private KafkaService kafkaService;

    @Test
    public void getUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Tom");
        jsonObject.put("age", 18);
        kafkaService.sendMessage("topic1", jsonObject.toJSONString());

    }

}
