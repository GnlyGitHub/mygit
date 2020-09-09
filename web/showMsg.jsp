<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liangyurj
  Date: 2020/8/23
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>阅读短消息</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js"></script>
    <style>
        table{
            border: 1px solid rgb(210,210,210);
            width: 700px;
        }
        td{
            padding: 10px;
        }
        body{
            background-color: rgba(81, 188, 255, 0.09);
        }
    </style>
</head>
<body>
<%@ include file="loginControl.jsp"%>
<div align="center">
    <div style="width: 700px">
        <h2 align="left" style="position: relative;left: 0px;top: 50px">消息详情</h2>
        <div align="right" style="margin: 50px 0 20px 0">
            <p>当前用户:<span style="color: #1E9FFF">${sessionScope.uname}</span>&nbsp;&nbsp;
                <a href="#" onclick="window.history.back()">返回</a>
            </p>
        </div>
        <div>
            <table>
                <tr style="height: 30px">
                    <td colspan="2" style="background-color: rgb(242,242,242)">来自:${requestScope.fromer} --> 发给:${requestScope.addressee}</td>
                </tr>
                <tr style="height: 30px">
                    <td>标题</td><td>${requestScope.title}</td>
                </tr>
                <tr style="height: 150px" valign="top">
                    <td>内容</td><td width="620">${requestScope.content}</td>
                </tr>
                <tr>
                    <td></td><td align="right">${requestScope.time}</td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
