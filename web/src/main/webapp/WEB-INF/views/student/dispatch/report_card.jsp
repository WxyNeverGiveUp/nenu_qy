<%--
  Created by IntelliJ IDEA.
  User: wlm
  Date: 2017/1/8
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>报到证状态</title>
    <jsp:include page="../../common/link.jsp" />
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/student_page/business_handling/cardState.css">
</head>
<body>
<jsp:include page="../../common/header.jsp" />
<!-- 侧栏start -->
<jsp:include page="../../common/Ssidebar.jsp"/>
<script type="text/javascript">
    KISSY.use('module/sidebar',function(S){
        S.ready(function(){
            PW.module.Sidebar();
        });
    });
</script>
<!-- 侧栏end -->
<div class="cardState">
    <ul>
        <li>报到证号：${reportCard.number}</li>
        <li>已打印报到证:${isPrinted}</li>
    </ul>
</div>
</body>
</html>
