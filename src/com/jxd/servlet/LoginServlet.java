package com.jxd.servlet;

import com.jxd.service.IUsersService;
import com.jxd.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码方式
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //获取数据
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        String rememberPwd = request.getParameter("rememberPwd");

        IUsersService usersService = new UsersServiceImpl();
        if (usersService.login(uname,pwd)){
            //设置session
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("uname",uname);

            //设置cookie
            if ("y".equals(rememberPwd)){
                //创建存放用户名的cookie
                Cookie nameCookie = new Cookie("nameCookie",uname);
                nameCookie.setMaxAge(60 * 30);
                response.addCookie(nameCookie);

                //创建存放密码的cookie
                Cookie pwdCookie = new Cookie("pwdCookie",pwd);
                pwdCookie.setMaxAge(60 * 30);
                response.addCookie(pwdCookie);
            }

            //重定向至短消息列表页面
            response.sendRedirect("listMsg.jsp");
        } else {
            //转发至登录页面
            request.setAttribute("loginMsg",false);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}
