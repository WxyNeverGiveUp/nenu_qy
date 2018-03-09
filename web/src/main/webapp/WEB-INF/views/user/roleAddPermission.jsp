<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>东北师范大学网上签约管理系统--角色分配权限</title>
    <%@include file="../common/link.jsp" %>
    <%@include file="../common/script.jsp" %>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/system.css"/>
</head>
<body>
<%@include file="../common/header.jsp" %>
<div class="continer">
    <%@include file="../common/Asidebar.jsp" %>
    <div class="content">
        <%@include file="../common/crumbs.jsp" %>
        <div class="page-content">
            <form action="${website}admin/user/role/assignPermission" method="post">
                <input type="hidden" name="roleId" value="${roleId}">
                <div class="block">
                    <div class="block-header">
                        <h3>角色分配权限</h3>
                    </div>
                    <div class="block-content clearfix">
                        <div class="content-input">
                            <label>角色名称</label>
                            <input type="text" class="inputText" value="${roleName}" readonly="true">
                        </div>
                        <div class="content-input-checkbox clearfix">
                            <c:forEach items="${allPermissionList}" var="permission" varStatus="st">
                                <div class="checkbox-div">

                                        <input type="checkbox" name="permissions" value="${permission.id}"  <c:if test="${permission.isSelected==1}">checked="true"</c:if>>
                                    <span>${permission.description}</span>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="content-input btn-margin-bottom">
                        <button type="submit" class="btn btn-margin-left btn-margin-right">确定</button>
                        <button type="reset" class="btn" onclick="location.href='javascript:history.go(-1);'">取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
