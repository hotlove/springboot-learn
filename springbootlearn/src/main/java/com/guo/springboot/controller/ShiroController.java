package com.guo.springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Enzo Cotter on 2018/6/24.
 */
@Controller
@RequestMapping("/shirologin")
public class ShiroController {

    @RequestMapping("/login")
    @ResponseBody
    public String shiroLogin(HttpServletRequest request, String n, String p) {

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(n, p);

        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();

            return "failure";
        }

        return "hello";
    }

    @RequestMapping("/index")
    @ResponseBody
    public String shiroIndex() {
        return "hello";
    }
}
