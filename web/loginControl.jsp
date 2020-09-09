<%--
  Created by IntelliJ IDEA.
  User: liangyurj
  Date: 2020/8/24
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${empty sessionScope.uname}">
    <c:redirect url="index.jsp"></c:redirect>
</c:if>
