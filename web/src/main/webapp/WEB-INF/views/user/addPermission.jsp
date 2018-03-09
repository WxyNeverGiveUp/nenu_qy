<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>东北师范大学网上签约管理系统--添加权限</title>
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
            <form action="${website}admin/user/permission/add" method="post">
                <div class="block">
                    <div class="block-header">
                        <h3>添加权限</h3>
                    </div>
                    <div class="block-content">
                        <div class="content-input">
                            <label>权限路径</label>
                            <input type="text" class="inputText" name="permission">
                        </div>

                        <div class="content-input">
                            <label>描述</label>
                            <input type="text" class="inputText" name="description">
                        </div>
                        <div class="content-input">
                            <label>是否可用</label>
                            <select class="selectText" name="available">
                                <option value="1">可用</option>
                                <option value="0">不可用</option>
                            </select>
                        </div>
                    </div>
                    <div class="content-input">
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
