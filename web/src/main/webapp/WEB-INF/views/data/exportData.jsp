<%--
  Created by IntelliJ IDEA.
  User: lishicao
  Date: 15/5/31
  Time: 上午1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset = "utf-8"/>
  <title>东北师范大学网上签约管理系统 -- 就业管理 -- 数据导入</title>
  <%@include file="../common/link.jsp"%>
  <%@include file="../common/script.jsp"%>
  <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/employment.css">
</head>
<body>
<%@include file="../common/header.jsp"%>
<div class="continer">
  <%@include file="../common/sidebar_bak.jsp"%>
  <form class="content" action="${website}data/exportData" method="get" type="">
    <%@include file="../common/crumbs.jsp"%>
    <div class="block">
      <div class="block-header">
        <h3>数据导入</h3>
      </div>
      <div class="block-content clearfix">
        <div class="block-import">
          <a class="btn-import-template" href="javascript:;">请选择毕业届次</a>
          <select class="textTheme control-area-import" autocomplete="off" data-valid-rule="notNull" name="year">
            <option value="2010">2010</option>
            <option value="2011">2011</option>
            <option value="2012">2012</option>
            <option value="2013">2013</option>
            <option value="2014">2014</option>
            <option value="2015">2015</option>
            <option value="2016">2016</option>
            <option value="2017">2017</option>
            <option value="2018">2018</option>
          </select>
          <button type="submit" class="btn">导出</button>
        </div>
      </div>
    </div>
  </form>
</div>
</body>
</html>
