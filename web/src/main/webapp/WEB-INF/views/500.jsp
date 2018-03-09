<%--
  User: erdan
  Date: 2014/9/18
  Time: 21:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>东北师范大学网上签约管理系统</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8"/>
    <meta http-equiv="keywords" conten="keyword,keyword"/>
    <jsp:include page="common/link.jsp"/>
    <jsp:include page="common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/login.css"/>
</head>
<body>
<jsp:include page="common/header.jsp"/>
<div class="container">
    <jsp:include page="common/Ssidebar.jsp"/>
    <script type="text/javascript">
        KISSY.use('module/sidebar', function (S) {
            S.ready(function () {
                PW.module.Sidebar();
            });
        });
    </script>
    <div class="content">
        <div class="page-content">
            <div class="tool-content">
                <p class="tool-tip no-permisition"><span>500</span>抱歉，页面出错！</p>
            </div>
            <div class="tool-content">
                <p class="tool-tip no-permisition">
                    <c:out value="${errMsg}">${errMsg}</c:out>
                </p>
            </div>
            <a href="javascript:;" class="back" onclick="window.history.go(-1)">返回上一页</a>
        </div>
    </div>
</div>
</body>
</html>
