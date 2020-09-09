<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liangyurj
  Date: 2020/8/22
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js"></script>
    <style>
        a{
            text-decoration: none;
            color: black;
            position: relative;
            left: 50px;
        }
        body{
             background-color: rgba(81, 188, 255, 0.09);
        }
    </style>
</head>
<body>
<h2 align="center" style="margin: 30px 0px 30px 0px">注册</h2>
<div style="display: flex; justify-content: center">
    <form class="layui-form" action="RegisterServlet" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="uname" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="password" name="pwd" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-inline">
                <input type="password" name="repwd" required lay-verify="required|confirmPass" placeholder="请再次输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" required lay-verify="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">注册</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <a href="index.jsp" style="color: #1E9FFF">返回首页</a>
            </div>
        </div>
    </form>
</div>

<script>
    layui.use('form', function(){
        var form = layui.form;
        var $ = layui.$;

        form.verify({
            confirmPass:function (value) {
                if($('input[name=pwd]').val() !== value)
                    return '两次密码输入不一致！';
            }
        });

        <c:if test="${not empty requestScope.registerMsg}">
            <c:if test="${requestScope.registerMsg == 1}">
                layer.msg("用户名已存在",{icon:5});
            </c:if>
            <c:if test="${requestScope.registerMsg == 2}">
                layer.msg("注册成功",{icon:6});
            </c:if>
            <c:if test="${requestScope.registerMsg == 3}">
                layer.msg("注册失败，请重试",{icon:5});
            </c:if>
        </c:if>
    });
</script>
</body>
</html>
