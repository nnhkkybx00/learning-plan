package com.example.cookie.distributed;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("login")
    public String doLogin(HttpServletRequest request, HttpSession session,String username,String password){
        System.out.println("username::: " + username);
        String sessionUsername = (String) session.getAttribute("username");
        if(!StringUtils.isEmpty(sessionUsername)){
            return "index";
        }
        if (StringUtils.isEmpty(username)){
            return "login";
        }
        session.setAttribute("username",username);
        session.setAttribute("port",request.getLocalPort());
        return "index";
    }

    @RequestMapping("/buy")
    public String buy(HttpServletRequest request,HttpSession session){
        String username = (String) session.getAttribute("username");
        if(StringUtils.isEmpty(username)){
            return "login";
        }else {
            System.out.println("买了一个机器人");
            return "index";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
}
