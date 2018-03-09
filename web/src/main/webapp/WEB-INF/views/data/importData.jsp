<%--
  Created by IntelliJ IDEA.
  User: lishicao
  Date: 15/5/27
  Time: 下午5:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset = "utf-8"/>
  <title>东北师范大学网上签约管理系统 -- 数据管理 -- 数据导入</title>
  <%@include file="../common/link.jsp"%>
  <%@include file="../common/script.jsp"%>
  <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/employment.css">
</head>
<body>
<%@include file="../common/header.jsp"%>
<div class="continer">
  <%@include file="../common/sidebar_bak.jsp"%>
  <form class="content re-height" action="${website}data/importData" method="post" enctype="multipart/form-data">
    <%@include file="../common/crumbs.jsp"%>
    <div class="block">
      <div class="block-header">
        <h3>学信网导出的EXCEL导入</h3>
      </div>
      <div class="block-content clearfix">
        <div class="block-import">
          <input type="file" class="textTheme control-area-import" name="file" />
          <button type="submit" class="btn">导入</button>
        </div>
      </div>
    </div>
  </form>
  <form class="content re-height" action="${website}data/importNationData" method="post" enctype="multipart/form-data">
    <div class="block">
      <div class="block-header">
        <h3>国家系统导出的EXCEL导入</h3>
      </div>
      <div class="block-content clearfix">
        <div class="block-import">
          <input type="file" class="textTheme control-area-import" name="file" />
          <button type="submit" class="btn">导入</button>
        </div>
      </div>
    </div>
  </form>
  <form class="content re-height" action="${website}data/completeStudentId" method="post" enctype="multipart/form-data">
    <div class="block">
      <div class="block-header">
        <h3>国家系统数据的学号补全</h3>
      </div>
      <div class="block-content clearfix">
        <div class="block-import">
          <input type="file" class="textTheme control-area-import" name="file" />
          <button type="submit" class="btn">导入</button>
        </div>
      </div>
    </div>
  </form>
</div>
</body>
</html>
