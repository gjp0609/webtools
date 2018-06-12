package top.gjp0609.webtools.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.gjp0609.webtools.common.aop.LoggerManage;
import top.gjp0609.webtools.common.validation.Groups;
import top.gjp0609.webtools.entity.User;
import top.gjp0609.webtools.repository.UserRepository;
import top.gjp0609.webtools.utils.DebugUtil;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private UserRepository userRepository;

    private int size = 10;

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

    @GetMapping(value = "/getAllUsers/{page}")
    @LoggerManage("user_getAll")
    public ModelAndView getAllUsers(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page) {
        Page<User> userList = userRepository.findAll(PageRequest.of(page, size));
        long count = userRepository.count();
        log.info(userList.toString());
        return new ModelAndView("user", "userList", userList)
                .addObject("totalPage", count / size + 1);
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

    @GetMapping(value = "/addUser/{name}/{password}")
    @LoggerManage("user_addUser")
    @ApiOperation(value = "添加用户信息", notes = "添加用户信息包括用户名和密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String", paramType = "path")
    })
    public ModelAndView addUser(@Validated({Groups.Add.class}) User user, BindingResult result) {
        StringBuilder builder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                FieldError err = (FieldError) error;
                builder.append("ERROR: ")
                        .append(err.getObjectName())
                        .append("的")
                        .append(err.getField())
                        .append("的")
                        .append(err.getDefaultMessage());
            }
        }
        log.info(DebugUtil.getFmtRefStr(user));
        return new ModelAndView("index").addObject("errMsg", builder);
    }

}
