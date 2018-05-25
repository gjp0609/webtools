package top.gjp0609.webtools.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.gjp0609.webtools.common.aop.LoggerManage;
import top.gjp0609.webtools.entity.User;
import top.gjp0609.webtools.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/user")
public class TestController {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private UserRepository userRepository;

    @Autowired
    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping(value = "/getUser/{id}")
    @LoggerManage(description = "user_getOne")
    public ModelAndView getUser(@PathVariable("id") Long id) {
        User user = userRepository.getOne(id);
        log.info(user.toString());
        return new ModelAndView("index", "user", user);
    }

    @GetMapping(value = "/getAllUsers")
    @LoggerManage(description = "user_getAll")
    public ModelAndView getAllUsers() {
        List<User> userList = userRepository.findAll();
        log.info(userList.toString());
        return new ModelAndView("index", "userList", userList);
    }

}
