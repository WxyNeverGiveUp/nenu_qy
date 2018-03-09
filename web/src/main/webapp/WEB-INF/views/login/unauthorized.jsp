<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>东北师范大学网上签约管理系统</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8"/>
    <meta http-equiv="keywords" conten="keyword,keyword"/>
    <jsp:include page="../common/link.jsp"/>
    <jsp:include page="../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/login.css"/>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="continer">
    <jsp:include page="../common/sidebar_bak.jsp"/>
    <div class="content">
        <%@include file="../common/crumbs.jsp"%>
        <div class="page-content">
            <div class="tool-content">
                <p class="tool-tip no-permisition"><span>401</span>抱歉，您当前没有该操作的权限！</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>