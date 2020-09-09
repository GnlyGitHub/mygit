package com.jxd.servlet;

import com.jxd.model.Users;
import com.jxd.service.IUsersService;
import com.jxd.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码方式
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //获取注册信息
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        String email = request.getParameter("email");

        IUsersService usersService = new UsersServiceImpl();

        //验证重名
        boolean isRepeat = false;
        List<Users> list = usersService.getAllUsers();
        for (Users users : list){
            if (users.getUname().equals(uname)){
                isRepeat = true;
            }
        }
        if (isRepeat){
            request.setAttribute("registerMsg",1);//用户名已存在
        } else {
            Users users = new Users(uname,pwd,email);//允许注册
            if (usersService.register(users)){
                request.setAttribute("registerMsg",2);//注册成功
            } else {
                request.setAttribute("registerMsg",3);//注册失败
            }
        }
        //创建用户对象
        //Users users = new Users(uname,pwd,email);
        //返回结果
        //request.setAttribute("registerMsg",usersService.register(users));
        request.getRequestDispatcher("register.jsp").forward(request,response);
    }
}
