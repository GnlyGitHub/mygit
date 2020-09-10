<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liangyurj
  Date: 2020/8/22
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>用户登录</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js"></script>
    <style>
      .down{
        position: relative;
        top: 50px;
        right: 30px;
      }
      body{
        background-color: rgba(81, 188, 255, 0.09);
      }
      #rememberPwd{
        width: 5px;
        height: 5px;
      }
    </style>
  </head>
  <body>
  <%
    String uname = "";
    String pwd = "";
    //获取当前web应用的所有cookie
    Cookie[] cookies = request.getCookies();

    if (cookies != null){
      //遍历cookies，取出用户名密码
      for (int i = 0; i < cookies.length; i++){
        //根据cookie的名字寻找
        if("nameCookie".equals(cookies[i].getName())){
          uname = cookies[i].getValue();
        }else if ("pwdCookie".equals(cookies[i].getName())){
          pwd = cookies[i].getValue();
        }
      }
    }
  %>
  <h2 align="center" style="margin: 30px 0px 30px 0px" class="down">登录</h2>
  <div style="display: flex; justify-content: center" class="down">
    <form class="layui-form" action="LoginServlet" method="post">
      <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
          <input type="text" name="uname" required  lay-verify="required" value="<%=uname%>" placeholder="请输入用户名" autocomplete="off" class="layui-input">
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-inline">
          <input type="password" name="pwd" required lay-verify="required" value="<%=pwd%>" placeholder="请输入密码来来来" autocomplete="off" class="layui-input">
        </div>
      </div>
      <div class="layui-form-item" style="position: relative; right: 50px">
        <div class="layui-input-block">
          <%--<input type="checkbox" value="y" name="rememberPwd" id="rememberPwd">记住密码--%>
          <input type="checkbox" name="rememberPwd" value="y" title="记住密码">
          <button class="layui-btn" lay-submit lay-filter="formDemo">登录</button>
          <button type="button" class="layui-btn layui-btn-primary" onclick="location.href='register.jsp'">注册</button>
        </div>
      </div>
    </form>
  </div>

  <script>
    layui.use('form', function(){
      var form = layui.form;

      <c:if test="${not empty requestScope.loginMsg}">
        <c:if test="${!requestScope.loginMsg}">
            layer.msg("用户名或密码错乌",{icon:5});
        </c:if>
      </c:if>
    });
  </script>
  </body>
</html>
