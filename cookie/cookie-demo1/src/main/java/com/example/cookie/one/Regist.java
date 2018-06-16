package com.example.cookie.one;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Regist {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "regist";
    }

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception{
//    public void regist(HttpServletRequest request ,@RequestParam("username") String username, @RequestParam("password") String password) throws Exception{
//        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("用户名： " + username);
        System.out.println("密码： " + password);

        request.setAttribute("username",username);
        request.setAttribute("password",password);

        //创建cookie对象
        Cookie cookie = new Cookie("userpass",username + "#" + password);
        //设置cookie的存储时间,单位为秒
        cookie.setMaxAge(60*60);
//        cookie.setMaxAge(-1);//和默认一样
//        cookie.setMaxAge(0);//删除当前cookie
        //把cookie写入浏览器
        response.addCookie(cookie);


        return "regist1";
//        request.getRequestDispatcher("/regist1.html").forward(request,response);
    }

    @RequestMapping(value = "/registSecond",method = RequestMethod.POST)
    public void registSecond(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //获取性别
        String gender = request.getParameter("gender");
        //获得职位
        String job = request.getParameter("job");

        //从浏览器读取当前项目所有cookie
        Cookie[] cookies = request.getCookies();
        String userpassVal = null;
        for(Cookie cookie : cookies){
            String cookieName = cookie.getName();
            if("userpass".equals(cookieName)){
                userpassVal = cookie.getValue();
            }
        }

        //循环便利cookie
        String username = null;
        String password = null;
        if(userpassVal != null){
            String[] value = userpassVal.split("#");
            username = value[0];
            password = value[1];
        }


//        String username = (String) request.getAttribute("username");
//        String password = (String) request.getAttribute("password");

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
        response.getWriter().println("<h1>注册成功<h1>");
        response.getWriter().println("<h1>");
        response.getWriter().println(" 用户名： " + username);
        response.getWriter().println(" 密码： " + password);
        response.getWriter().println(" 性别： " + genderText);
        response.getWriter().println(" 职位： " + jobText);
        response.getWriter().println("</h1>");
    }
}
