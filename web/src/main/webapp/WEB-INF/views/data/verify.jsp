<%--
  Created by IntelliJ IDEA.
  User: lishicao
  Date: 15/5/20
  Time: 下午6:37
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset = "utf-8"/>
  <title>东北师范大学网上签约管理系统 -- 审核状态</title>
  <jsp:include page="../common/link.jsp" />
  <jsp:include page="../common/script.jsp"/>
  <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/employment.css">
</head>
<body>
<jsp:include page="../common/header.jsp" />
<div class="continer">
  <jsp:include page="../common/sidebar_bak.jsp" />
  <div class="content">
    <%@include file="../common/crumbs.jsp"%>
    <form class="block J_searchForm">
      <div class="block-content clearfix">
        <div class="control-area control-area-short">
          <label>毕业届次</label>
          <select class="textTheme" autocomplete="off" name="grade">
            <option value="2015">2015</option>
            <option value="2016">2016</option>
            <option value="2017">2017</option>
          </select>
        </div>
        <div class="control-area control-area-short">
          <label>状态</label>
          <select class="textTheme" autocomplete="off" name="verifyStatus">
            <option value="">全部</option>
            <option value="审核完毕">审核完毕</option>
            <option value="审核中">审核中</option>
          </select>
        </div>
        <div class="btn-right">
          <button type="button" class="btn J_searchBtn">查询</button>
        </div>
      </div>
    </form>
    <div class="block">
      <div class="block-header">
        <h3>查询结果</h3>
      </div>
      <div class="block-content clearfix">
        <table>
          <thead>
          <tr>
            <th>学 院</th>
            <th>学 历</th>
            <th>辅导员审核状态</th>
            <th>毕业生人数</th>
            <th>未审核数量</th>
            <th>审核人</th>
          </tr>
          </thead>
          <script id="tpl" type="text/template">
            {@each data as d}
            <tr>
              <td>&{d.college}</td>
              <td>&{d.qualification}</td>
              <td>&{d.status}</td>
              <td>&{d.gruNum}</td>
              <td>&{d.exNum}</td>
              <td>&{d.exPerson}</td>
            </tr>
            {@/each}
          </script>
          <tbody id="J_template">

          </tbody>
        </table>
        <div id="J_pagination"></div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
  KISSY.use('page/employment/student',function(S){
    S.ready(function(){
      PW.page.student({
        renderTo: '#J_pagination',//分页显示指向
        juicerRender: '#tpl',//模版渲染指向
        dataRender: '#J_template',
        pageSize: 10,//每页显示的记录数
        url:'${website}data/ajax/verify/query',//必选，必须指定ajax的url
        configUrl:function(url,page,me,prevdata){
          return url;
        },
        type:'get'
      });
    });
  });
</script>
</body>
</html>