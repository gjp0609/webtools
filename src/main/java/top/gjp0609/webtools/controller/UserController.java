package top.gjp0609.webtools.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import top.gjp0609.webtools.common.aop.LoggerManage;
import top.gjp0609.webtools.entity.User;
import top.gjp0609.webtools.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping(value = "/getUser/{id}")
    @LoggerManage("user_getOne")
    public ModelAndView getUser(@PathVariable("id") Long id) {
        User user = userRepository.getOne(id);
        log.info(user.toString());
        return new ModelAndView("index", "user", user);
    }

    @GetMapping(value = "/getAllUsers")
    @LoggerManage("user_getAll")
    public ModelAndView getAllUsers() {
        List<User> userList = userRepository.findAll();
        log.info(userList.toString());
        return new ModelAndView("index", "userList", userList);
    }

    @GetMapping(value = "/setName/{id}/{name}")
    @LoggerManage("user_setName")
    @ResponseBody
    @ApiOperation(value = "更新用户信息", notes = "根据用户id更新用户名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String", paramType = "path")
    })
    public String setName(@PathVariable("id") Long id, @PathVariable("name") String name) {
        int num = userRepository.modifyById(name, id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", num != 0);
        return jsonObject.toJSONString();
    }

}
