package top.gjp0609.webtools;

import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import top.gjp0609.webtools.entity.User;
import top.gjp0609.webtools.repository.UserRepository;
import top.gjp0609.webtools.utils.DebugUtil;
import top.gjp0609.webtools.utils.Font;
import top.gjp0609.webtools.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存性能测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebtoolsApplicationTests {

    @Autowired
    private UserRepository userRepository;

//    @Rule
//    public ContiPerfRule i = new ContiPerfRule();

    @Test
//    @PerfTest(threads = 10, invocations = 1000)
    public void getUser() {
        List<User> userList = userRepository.findAll();
        System.out.println(DebugUtil.getFmtRefStr(userList));
    }

    @Test
    public void add() {
        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
//            list.add(new User(StringUtil.getRandomString(5, StringUtil.RaindomType.LOWER), StringUtil.getRandomString(6, StringUtil.RaindomType.NUMBER)));
        }
        userRepository.saveAll(list);
    }

    @Test
    public void page() {
        Pageable page = PageRequest.of(0, 10);
        Page<User> all = userRepository.findAll(page);
        System.out.println(DebugUtil.getFmtRefStr(all));
    }

    @Test
    public void nameLike() {
        Pageable page = PageRequest.of(0, 5, Sort.Direction.DESC, "password");
        List<User> userList = userRepository.findAllByNameLike("z%", page);
        System.out.println(DebugUtil.getFmtRefStr(userList));
    }
}
