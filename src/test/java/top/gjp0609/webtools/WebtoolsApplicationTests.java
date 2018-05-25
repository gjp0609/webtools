package top.gjp0609.webtools;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.gjp0609.webtools.entity.User;
import top.gjp0609.webtools.repository.UserRepository;

import java.util.List;

/**
 * 缓存性能测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebtoolsApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    @Test
    @PerfTest(threads = 10, invocations = 1000)
    public void getUser() {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

}
