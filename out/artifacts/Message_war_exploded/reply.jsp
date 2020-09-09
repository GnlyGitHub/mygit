<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liangyurj
  Date: 2020/8/23
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>回复短消息</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js"></script>
    <style>
        body{
            background-color: rgba(81, 188, 255, 0.09);
        }
    </style>
</head>
<body>
<%@ include file="loginControl.jsp"%>
<div align="center">
    <div style="width: 742px">
        <h2 align="left" style="position: relative;left: 0px;top: 50px">编辑消息</h2>
        <div align="right" style="margin: 50px 0 20px 0">
            <p>当前用户:<span style="color: #1E9FFF">${sessionScope.uname}</span>&nbsp;&nbsp;
                <a href="listMsg.jsp">返回</a>
            </p>
        </div>
        <%
            String addressee = request.getParameter("addressee");
        %>
        <div style="border: 1px solid rgb(210,210,210);padding-top: 20px">
            <form class="layui-form" action="PostServlet">
                <div class="layui-form-item">
                    <label class="layui-form-label">发送给</label>
                    <div class="layui-input-inline">
                        <select id="addressee" name="addressee" lay-verify="required">
                            <option value="<%=addressee%>"><%=addressee%></option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">标题</label>
                    <div class="layui-input-inline">
                        <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">内容</label>
                    <div class="layui-input-inline">
                        <textarea name="content" cols="80" rows="10" required  lay-verify="required" placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-inline">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">发送</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    layui.use(['form','layer'], function(){
        var form = layui.form;
        var layer = layui.layer;
        var $ = layui.$;

        /*$.ajax({
            url: '/GetAllUsersServlet',
            dataType: 'json',
            data:{'state': 0},
            type: 'post',
            success: function (data) {
                $.each(data.data, function (index, item) {
                    $('#addressee').append(new Option(item.uname, item.uname));//下拉菜单里添加元素
                });
                layui.form.render("select");
            }
        });*/

        <c:if test="${not empty requestScope.postMsg}">
        <c:if test="${requestScope.postMsg}">
        layer.msg("发送成功");
        </c:if>
        <c:if test="${!requestScope.postMsg}">
        layer.msg("发送失败");
        </c:if>
        </c:if>
    });
</script>
</body>
</html>
