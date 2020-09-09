<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liangyurj
  Date: 2020/8/23
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>回收站</title>
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
<div style="margin-bottom: 5px;">
    <ins class="adsbygoogle" style="display:inline-block;width:970px;height:90px" data-ad-client="ca-pub-6111334333458862" data-ad-slot="3820120620"></ins>
</div>

<div align="center">
    <div  style="width: 837px">
        <h2 align="left">回收站</h2>
        <%--<div class="layui-btn-group demoTable">
            <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
            <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
            <button class="layui-btn" data-type="isAll">验证是否全选</button>
        </div>--%>
        <div align="right">
            <p>当前用户:<span style="color: #1E9FFF">${sessionScope.uname}</span>&nbsp;&nbsp;
                <a href="listMsg.jsp">返回</a>
            </p>
        </div>

        <table class="layui-table" lay-data="{width: 837, height:330, url:'/RecycleBinServlet', page:false, id:'idTest'}" lay-filter="demo">
            <thead>
            <tr>
                <%--<th lay-data="{type:'checkbox', fixed: 'left'}"></th>--%>
                <th lay-data="{field:'msgno', width:10, display:none}">编号</th>
                <th lay-data="{field:'title', width:100}">标题</th>
                <th lay-data="{field:'content', width:200}">内容</th>
                <th lay-data="{field:'time', width:180, sort: true}">时间</th>
                <th lay-data="{field:'fromer', width:70}">来自</th>
                <th lay-data="{field:'addressee', width:70}">发给</th>
                <th lay-data="{fixed: 'right', width:210, align:'center', toolbar: '#barDemo'}"></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="recycle">恢复</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">彻底删除</a>
</script>

<script>
    layui.use('table', function(){
        var table = layui.table;
        //监听表格复选框选择
        table.on('checkbox(demo)', function(obj){
            console.log(obj)
        });
        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                //layer.msg('ID：'+ data.msgno + ' 的查看操作');
                location.href = "ShowMsgServlet?msgno=" + data.msgno;
            } else if(obj.event === 'del'){
                layer.confirm('删除后不可恢复，确定删除吗', function(index){
                    layer.close(index);
                    location.href = "DeleteCompletelyServlet?msgno=" + data.msgno;
                });
            } else if(obj.event === 'recycle'){
                location.href = "RecycleServlet?msgno=" + data.msgno;
            }
        });

        var $ = layui.$, active = {
            getCheckData: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            ,getCheckLength: function(){ //获取选中数目
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
            }
            ,isAll: function(){ //验证是否全选
                var checkStatus = table.checkStatus('idTest');
                layer.msg(checkStatus.isAll ? '全选': '未全选')
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        <c:if test="${not empty requestScope.recycleMsg}">
            <c:if test="${requestScope.recycleMsg}">
            layer.msg("已恢复");
            </c:if>
            <c:if test="${!requestScope.recycleMsg}">
            layer.msg("恢复失败");
            </c:if>
        </c:if>
        <c:if test="${not empty requestScope.delComMsg}">
            <c:if test="${requestScope.delComMsg}">
            layer.msg("已删除");
            </c:if>
            <c:if test="${!requestScope.delComMsg}">
            layer.msg("删除失败");
            </c:if>
        </c:if>
    });
</script>
</body>
</html>
