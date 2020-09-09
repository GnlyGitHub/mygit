package com.jxd.servlet;

import com.jxd.service.IMessageService;
import com.jxd.service.impl.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteCompletelyServlet")
public class DeleteCompletelyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码方式
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //获取数据
        int magno = Integer.parseInt(request.getParameter("msgno"));

        //执行删除操作
        IMessageService messageService = new MessageServiceImpl();
        request.setAttribute("delComMsg",messageService.deleteMsgByMsgno(magno));
        request.getRequestDispatcher("recycle.jsp").forward(request,response);
    }
}
