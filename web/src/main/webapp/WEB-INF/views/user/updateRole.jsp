<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>东北师范大学网上签约管理系统--添加角色</title>
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
            <form:form action="${website}admin/user/role/update" method="put">
                <input type="hidden" name="roleId" value="${role.id}">
                <div class="block">
                    <div class="block-header">
                        <h3>修改角色</h3>
                    </div>
                    <div class="block-content">
                        <div class="content-input">
                            <label>角色名称</label>
                            <input type="text" class="inputText" name="role" value="${role.role}">
                        </div>

                        <div class="content-input">
                            <label>描述</label>
                            <input type="text" class="inputText" name="description" value="${role.description}">
                        </div>
                        <div class="content-input">
                            <label>是否可用</label>
                            <select class="selectText" name="available">
                                <option <c:if test="${role.available==1}"> selected="selected" </c:if> value="1">可用</option>
                                <option <c:if test="${role.available==0}"> selected="selected" </c:if> value="0">不可用</option>
                            </select>
                        </div>
                    </div>
                    <div class="content-input">
                        <button type="submit" class="btn btn-margin-left btn-margin-right">确定</button>
                        <button type="reset" class="btn" onclick="location.href='javascript:history.go(-1);'">取消</button>
                    </div>
                </div>
           </form:form>
        </div>
    </div>
</div>
</body>
</html>
