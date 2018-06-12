package top.gjp0609.webtools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.gjp0609.webtools.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/")
    public ModelAndView login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("LOGIN_USER");
        if (user == null) {
            return new ModelAndView("login");
        }
        return new ModelAndView("index").addObject("user", user);
    }
}
