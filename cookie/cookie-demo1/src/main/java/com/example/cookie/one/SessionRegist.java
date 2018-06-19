package com.example.cookie.one;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SessionRegist {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "session_regist";
    }

    @RequestMapping(value = "/sessionregist",method = RequestMethod.POST)
//    public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception{
    public String regist(HttpServletRequest request ,@RequestParam("username") String username, @RequestParam("password") String password) throws Exception{
        request.setCharacterEncoding("UTF-8");

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");

        //获得session对象
        HttpSession session = request.getSession();
        //设置session的属性，在session中的属性在整次会话（浏览器不关闭）都有作用
        session.setAttribute("username",username);
        session.setAttribute("password",password);

        //注销session

//        session.invalidate();
        return "session_regist1";
    }

    @RequestMapping(value = "/sessionSecond",method = RequestMethod.POST)
    public void registSecond(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //获取性别
        String gender = request.getParameter("gender");
        //获得职位
        String job = request.getParameter("job");


        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String genderText = "";
        if("1".equals(gender)){
            genderText = "男";
        }else if("2".equals(gender)){
            genderText = "女";
        }

        String jobText = "";
        if("1".equals(job)){
            jobText = "讲师";
        }else if("2".equals(job)){
            jobText = "架构师";
        }

        //根据request来获取session
        HttpSession session = request.getSession();
        //从session中获取信息
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        //获得session的id，session利用id和浏览器的cookie进行绑定用来区分会话
        String sessionId = session.getId();

        response.getWriter().println("<h1>注册成功<h1>");
        response.getWriter().println("<h1>");
        response.getWriter().println(" 用户名： " + username);
        response.getWriter().println(" 密码： " + password);
        response.getWriter().println(" 性别： " + genderText);
        response.getWriter().println(" sessionId： " + sessionId);
        response.getWriter().println(" 职位： " + jobText);
        response.getWriter().println("</h1>");
    }
}
