package com.jxd.servlet;

import com.jxd.model.Message;
import com.jxd.service.IMessageService;
import com.jxd.service.impl.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DelServlet")
public class DelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码方式
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //获取数据
        int msgno = Integer.parseInt(request.getParameter("msgno"));
        IMessageService messageService = new MessageServiceImpl();
        Message message = messageService.showMsg(msgno);
        int read = message.getRead();
        int del = 0;

        //执行修改操作
        Message message1 = new Message(msgno,read,del);
        request.setAttribute("delMsg",messageService.updateMsgByMsgno(message1));

        //返回修改结果
        request.getRequestDispatcher("listMsg.jsp").forward(request,response);
    }
}
