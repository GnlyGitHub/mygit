package com.jxd.servlet;

import com.jxd.model.Users;
import com.jxd.service.IUsersService;
import com.jxd.service.impl.UsersServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/GetAllUsersServlet")
public class GetAllUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户列表
        IUsersService usersService = new UsersServiceImpl();
        List<Users> list = usersService.getAllUsers();

        JSONArray jsonArray = JSONArray.fromObject(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","");
        jsonObject.put("count",list.size());
        jsonObject.put("data",jsonArray);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonObject);
        printWriter.flush();
        printWriter.close();
    }
}
