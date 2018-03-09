<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wlm
  Date: 2016/9/5
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>东北师范大学网上签约管理系统——首页</title>
    <jsp:include page="../../common/link.jsp"/>
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/student_page/login/successLogin.css">
</head>
<body>
<jsp:include page="../../common/header.jsp"/>
<!-- 侧栏start -->
<jsp:include page="../../common/Ssidebar.jsp"/>
<!-- 侧栏end -->
<div class="sucTotal">
    <c:if test="${!empty correctMsg}">
        <div class="correct-tip J_tip">${correctMsg}</div>
    </c:if>
    <c:if test="${!empty errMsg}">
        <div class="fault-tip J_tip">${errMsg}</div>
    </c:if>
    <div class="sucLogin">
        <h1>系统使用说明</h1>
        <p>
            1.请同学们在系统规定时间内如实填写系统中的各项信息，红色为必填项，黑色为选填项。
        </p>
        <p>
            2.部分信息项旁边有提示按钮，可以参考提示填写，请勿乱填。
        </p>
        <p>
            3.学籍信息中：姓名，身份证号，考生号，性别、民族、师范生类别，培养方式，生源地，定向委培单位等信息为系统锁定项，如有错误，请根据系统提示将相关材料提交给辅导员，由辅导员拍照并上传至系统后方可修改错误信息。
        </p>
        <p>
            4.信息确认提交后不可更改，请确保信息无误后再提交。
        </p>
        <p>
            5.请随时注意查看自己的信息审核状态，如果审核未通过，请及时与辅导员联系完善个人信息。
        </p>
        <p>
            6.学生就业指导服务中心电话：85098826
        </p>
    </div>
</div>
<script type="text/javascript">
    KISSY.use('module/sidebar',function(S){
        S.ready(function(){
            PW.module.Sidebar();
        });
    });
</script>
</body>
</html>