<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wlm
  Date: 2016/9/7
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--黎星宇 系统管理 上传数据 HTML-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统管理--上传文件</title>
    <jsp:include page="../../common/link.jsp"/>
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/admin_page/system_management/importData.css">
    <script type="text/javascript" src="${staticWebsite}upload/js/jquery-1.7.2.js"></script>
</head>
<body>
<jsp:include page="../../common/header.jsp"/>
<jsp:include page="../../common/Asidebar.jsp"/>
<script type="text/javascript">
    KISSY.use('module/sidebar',function(S){
        S.ready(function(){
            PW.module.Sidebar();
        });
    });
</script>
<div class="content">
    <div class="remind">
        <c:if test="${!empty correctMsg}">
            <div class="correct-tip J_tip">${correctMsg}</div>
        </c:if>
        <c:if test="${!empty errMsg}">
            <div class="fault-tip J_tip">${errMsg}</div>
        </c:if>
    </div>
    <div class="update">
        <div class="upbox">
            <span class="note">导入学籍数据：</span><br />
            <a href="${website}resources/download/xuejidaorumuban.xls" class="tip">点击这里查看学籍数据的文件模板</a>
            <span class="cover btn">
			        选择文件
	 		    </span>
            <form method="post" action="${website}admin/import/status/info" enctype="multipart/form-data" id="1">
                <input type="file" class="up" name="file" />
            </form>
        </div>
        <div class="upbox">
            <span class="note">导入派遣数据：</span><br />
            <a href="${website}resources/download/paiqiandaorumuban.xls" class="tip">点击这里查看派遣数据的文件模板</a>
            <span class="cover btn">
			        选择文件
	 		    </span>
            <form method="post" action="${website}admin/import/dispatch" enctype="multipart/form-data" id="2">
                <input type="file" class="up" name="file" />
            </form>
        </div>
        <div class="upbox">
            <span class="note">导入报到证数据：</span><br />
            <a href="${website}resources/download/baodaozhenghaodaorumuban.xls" class="tip">点击这里查看报到证数据的文件模板</a>
            <span class="cover btn">
			        选择文件
	 		    </span>
            <form method="post" action="${website}admin/import/report" enctype="multipart/form-data" id="3">
                <input type="file" class="up" name="file" />
            </form>
        </div>
        <p class="upname">
            <img src="${staticWebsite}img/admin_page/system_management/excel.jpg" class="excel">
            <span class="filename">仅支持上传xls类型文件！</span>
        </p>
        <div class="inputs">
            <input type="button" class="submit" value="确认上传" />
            <input type="button" class="delete" value="取消上传" />
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${staticWebsite}js/page/admin_page/system_management/importData.js"></script>
</html>