package com.jxd.servlet;

import com.jxd.model.Message;
import com.jxd.service.IMessageService;
import com.jxd.service.impl.MessageServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/GetMsgByAddresseeServlet")
public class GetMsgByAddresseeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IMessageService messageService = new MessageServiceImpl();

        //获取当前用户名
        HttpSession httpSession = request.getSession();
        String uname = (String)httpSession.getAttribute("uname");

        //获取消息列表
        List<Message> list = messageService.getMsgByAddressee(uname);

        //将数据响应至前台
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
