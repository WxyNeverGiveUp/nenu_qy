<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>东北师范大学网上签约管理系统</title>
    <%@include file="../common/link.jsp" %>
    <%@include file="../common/script.jsp" %>
    <link rel="stylesheet" content="text/css" href="${staticWebsite}css/page/system.css"/>
</head>
<body>
<%@include file="../common/header.jsp" %>
<div class="continer">
    <%@include file="../common/Asidebar.jsp" %>
    <div class="content">
        <%@include file="../common/crumbs.jsp" %>
        <div class="page-content">
            <div class="block">
                <div class="block-header">
                    <h3>角色列表</h3>
                    <a class="btn btn-right" href="${website}admin/user/role/toAdd">添加</a>
                </div>
                <div class="block-content">
                    <table>
                        <thead>
                        <tr>
                            <th>角色名称</th>
                            <th>描述</th>
                            <th>是否可用</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${roleList}" var="role" varStatus="st">
                            <tr>
                                <td>
                                    ${role.role}
                                </td>
                                <td>
                                    ${role.description}
                                </td>
                                <c:choose>
                                    <c:when test="${role.available==1}">
                                        <td>
                                            可用
                                        </td>
                                    </c:when>
                                    <c:when test="${role.available==0}">
                                        <td>
                                            不可用
                                        </td>
                                    </c:when>
                                </c:choose>
                                <td>
                                    <a class="mod" href="${website}user/role/toUpdate/${role.id}" title="编辑">
                                        <i></i>
                                    </a>
                                    <a class="share-role" href="${website}user/role/toAssignPermission/${role.id}" title="编辑">分配权限</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>