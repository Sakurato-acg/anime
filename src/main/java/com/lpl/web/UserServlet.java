package com.lpl.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lpl.Utils.CookieUtils;
import com.lpl.pojo.User;
import com.lpl.servlce.UserService;
import com.lpl.servlce.impl.UserServiceimpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@SuppressWarnings("all")
@WebServlet("/userServlet/*")
public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceimpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean remember = Boolean.parseBoolean(request.getParameter("remember"));
        User user = new User(username, password,null);

        User login = userService.login(user);

        if (login!=null){
         //登录成功
           if (remember){
               Cookie[] cookies = request.getCookies();
               Cookie findUsername = CookieUtils.findCookie("username", cookies);
               Cookie findPassword=CookieUtils.findCookie("password",cookies);
               if (findUsername==null && findPassword==null ){
                   Cookie usernameCookie = new Cookie("username", login.getUsername());
                   Cookie passwordCookie = new Cookie("password", login.getPassword());
                   response.addCookie(usernameCookie);
                   response.addCookie(passwordCookie);
               }

           }

            HttpSession session = request.getSession();
            session.setAttribute("user",login);
            String toJSONString = JSON.toJSONString(login);
            response.getWriter().write(toJSONString);
        }else {
            String toJSONString = JSON.toJSONString("登陆失败");
            response.getWriter().write(String.valueOf(toJSONString));
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.getWriter().write("success");
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User(username, password,email);
        boolean register = userService.register(user);
        System.out.println(register);
        if (register){
            //注册成功
            response.getWriter().write("success");
        }else {
            response.getWriter().write("false");
        }
    }

    protected void checkEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        boolean checkEmail = userService.checkEmail(email);
        if (checkEmail){
            request.getSession().setAttribute("email",email);
            response.getWriter().write("success");
        }else{
            response.getWriter().write("false");
        }

    }
    protected void reSet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String params = request.getReader().readLine();
        User user = JSON.parseObject(params, User.class);
        Object email = request.getSession().getAttribute("email");
        user.setEmail((String) email);
        request.getSession().invalidate();
        userService.reSet(user);

        response.getWriter().write("success");
    }
}
