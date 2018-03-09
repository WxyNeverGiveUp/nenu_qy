<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>已提交提醒</title>
    <jsp:include page="../../common/link.jsp" />
    <jsp:include page="../../common/script.jsp"/>
    <link href="${staticWebsite}css/page/student_page/login/successLogin.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="${staticWebsite}upload/css/zyupload-1.0.0.css " type="text/css">
    <script type="text/javascript" src="${staticWebsite}upload/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${staticWebsite}upload/js/zyupload-1.0.0.js"></script>
    <script type="text/javascript" src="${staticWebsite}upload/js/port.js"></script>
    <script type="text/javascript" src="${staticWebsite}js/module/dispatch/del.js"></script>
</head>
<body>
<jsp:include page="../../common/header.jsp" />
<jsp:include page="../../common/Ssidebar.jsp"/>
<script type="text/javascript">
    KISSY.use('module/sidebar',function(S){
        S.ready(function(){
            PW.module.Sidebar();
        });
    });
</script>
<div class="sucTotal">
    <div class="sucLogin">
        <h1>已提交提醒</h1>
        <h1>您已提交信息，如有错误，请联系辅导员进行修改。</h1>
        <a href = "${webSite}/student/dispatch/detail">点此可查看您的信息</a>
    </div>
</div>
</body>
</html>