<%--
  Created by IntelliJ IDEA.
  User: zhuoyin
  Date: 14-10-16
  Time: 下午3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>东北师范大学网上签约管理系统--修改个人信息</title>
    <%@include file="../common/link.jsp"%>
    <%@include file="../common/script.jsp"%>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/system.css"/>
</head>
<body>
<%@include file="../common/header.jsp"%>
<div class="continer">
    <%@include file="../common/Asidebar.jsp"%>
    <div class="content">
        <%@include file="../common/crumbs.jsp"%>
        <div class="page-content">
            <div class="block">
                <div class="block-header">
                    <h3>修改个人信息</h3>
                </div>
                <form:form action="${website}admin/user/update" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <div class="block-content">
                        <div class="content-input">
                            <label class="conten-lable">用户名</label>
                            <input type="text" name="username" readonly = "readonly" value="${user.username}"  class="inputText" value="${username}">
                        </div>
                        <div class="content-input">
                            <label>真实姓名</label>
                            <input type="text" name="realName"   value="${user.realName}" class="inputText" data-valid-rule="notNull" maxLength="50" placeholder="用户名小于50个字符">
                        </div>
                        <div class="content-input">
                            <label>手机</label>
                            <input type="text" name="cellphone"  class="inputText" data-valid-rule=
                                    "notNull&isMobile" value="${user.cellphone}">
                        </div>
                        <div class="content-input">
                            <button type="button" class="btn btn-margin-left btn-margin-right J_submitBtn">确定</button>
                            <button type="reset" class="btn">取消</button>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="block">
                <div class="block-header">
                    <h3>修改密码</h3>
                </div>
                <form action="${website}user/updatePwd2" method="post">
                    <div class="block-content">
                        <div class="content-input">
                            <label>原密码</label>
                            <input type="password" class="inputText" data-valid-rule="notNull&isPassword" name="password">
                        </div>
                        <div class="content-input">
                            <label>新密码</label>
                            <input type="password" id="pwd" class="inputText" data-valid-rule="notNull&isPasword" name="newPwd">
                        </div>
                        <div class="content-input">
                            <label>重复密码</label>
                            <input type="password" class="inputText" data-valid-rule=
                                    "notNull&isPassword&require(pwd)&equal(pwd)" name="secondPwd">
                        </div>
                        <div class="content-warning">
                            <p class="red-warning">${msg}</p>
                        </div>
                        <div class="content-input">
                            <button type="submit" class="btn btn-margin-left btn-margin-right">确定</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    KISSY.use('page/system/mod-user',function(S){
        S.ready(function(){
            PW.page.modUser();
        });
    });
</script>
</body>
</html>

