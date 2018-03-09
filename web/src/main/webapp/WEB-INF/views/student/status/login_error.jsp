<%--
  Created by IntelliJ IDEA.
  User: wlm
  Date: 2016/9/5
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>登录失败</title>
    <jsp:include page="../../common/link.jsp"/>
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/student_page/login/errLogin.css">
</head>
<body>
<jsp:include page="../../common/header.jsp"/>
<div class="errLogin">
    <h1>抱歉，您目前无法登录系统。</h1>
    <p>原因1：您未到毕业时间，不能录入信息。</p>
    <p>原因2：系统未到开放时间，请在规定时间内登录。</p>
    <p>原因3：您的毕业信息未录入系统，请联系辅导员登记毕业信息后，再登录系统。</p>
    <!-- <form>
        <input class="errBtn" type="button" value="退出登录">
    </form> -->
    <a href="${website}logout">退出登录</a>
</div>
</body>
</html>