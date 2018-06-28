package top.gjp0609.webtools.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@Controller
//public class ErrorController extends AbstractErrorController {
//
//    public ErrorController(ErrorAttributes errorAttributes) {
//        super(errorAttributes);
//    }
//
//    public ErrorController(ErrorAttributes errorAttributes, List<ErrorViewResolver> errorViewResolvers) {
//        super(errorAttributes, errorViewResolvers);
//    }
//
//    @GetMapping("/error")
//    public String getErrorPath() {
//
//        return null;
//    }
//}
