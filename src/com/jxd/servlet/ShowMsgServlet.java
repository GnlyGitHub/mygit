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

@WebServlet("/ShowMsgServlet")
public class ShowMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码方式
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //获取消息编号
        int magno = Integer.parseInt(request.getParameter("msgno"));
        IMessageService messageService = new MessageServiceImpl();

        //根据编号从数据库获取数据
        Message message = messageService.showMsg(magno);
        String title = message.getTitle();
        String content = message.getContent();
        String time = message.getTime();
        String addressee = message.getAddressee();
        String fromer = message.getFromer();
        int del = message.getDel();

        //标记为已读
        int read = 0;
        Message message1 = new Message(magno,read,del);
        messageService.updateMsgByMsgno(message1);

        //将数据发送至前台
        request.setAttribute("title", title);
        request.setAttribute("content", content);
        request.setAttribute("time", time);
        request.setAttribute("addressee", addressee);
        request.setAttribute("fromer", fromer);
        request.getRequestDispatcher("showMsg.jsp").forward(request, response);
    }
}
