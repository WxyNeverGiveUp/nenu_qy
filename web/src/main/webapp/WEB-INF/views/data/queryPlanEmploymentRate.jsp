<%--
  Created by IntelliJ IDEA.
  User: lishicao
  Date: 15/5/30
  Time: 下午11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset = "utf-8"/>
  <title>东北师范大学网上签约管理系统 -- 数据管理 -- 拟就业率</title>
  <jsp:include page="../common/link.jsp" />
  <jsp:include page="../common/script.jsp"/>
  <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/employment.css">
</head>
<body>
<jsp:include page="../common/header.jsp" />
<div class="continer">
  <jsp:include page="../common/sidebar_bak.jsp" />
  <div class="content">
    <%--<jsp:include page="../common/crumbs.jsp" />--%>
    <%@include file="../common/crumbs.jsp"%>
    <form class="block J_searchForm" >
      <div class="block-header">
        <h3>统计选项</h3>
      </div>
      <div class="block-content clearfix">
        <div class="control-area control-area-short">
          <label>起始届次</label>
          <select class="textTheme" autocomplete="off" name="start">
            <option value="0">请选择</option>
            <option value="2010">2010</option>
            <option value="2011">2011</option>
            <option value="2012">2012</option>
            <option value="2013">2013</option>
            <option value="2014">2014</option>
            <option value="2015">2015</option>
            <option value="2016">2016</option>
            <option value="2017">2017</option>
          </select>
        </div>
        <div class="control-area control-area-short">
          <label>截止届次</label>
          <select class="textTheme" autocomplete="off" name="end">
            <option value="0">请选择</option>
            <option value="2010">2010</option>
            <option value="2011">2011</option>
            <option value="2012">2012</option>
            <option value="2013">2013</option>
            <option value="2014">2014</option>
            <option value="2015">2015</option>
            <option value="2016">2016</option>
            <option value="2017">2017</option>
          </select>
        </div>
        <div class="control-area control-area-short">
          <label>维度一</label>
          <select class="textTheme" autocomplete="off" name="dimension1">
            <c:if test="${isSecretary == false && isCounselor == false}" >
              <option value="college_name">学院（部）</option>
            </c:if>
            <c:if test="${isCounselor == false}">
              <option value="qualification_study">学历</option>
            </c:if>
            <option value="major_devision">学科</option>
            <option value="major_name">专业</option>
            <option value="normal_stu">师范类别</option>
            <option value="province">生源省份</option>
            <option value="difficulty_mode">困难类别</option>
            <option value="sex">性别</option>
            <option value="nation">民族</option>
            <option value="political_stand">政治面貌</option>
          </select>
        </div>
        <div class="control-area control-area-short">
          <label>维度二</label>
          <select class="textTheme" autocomplete="off" name="dimension2">
            <option value="0">请选择</option>
            <c:if test="${isSecretary == false && isCounselor == false}" >
              <option value="college_name">学院（部）</option>
            </c:if>
            <c:if test="${isCounselor == false}">
              <option value="qualification_study">学历</option>
            </c:if>
            <option value="major_devision">学科</option>
            <option value="major_name">专业</option>
            <option value="normal_stu">师范类别</option>
            <option value="province">生源省份</option>
            <option value="difficulty_mode">困难类别</option>
            <option value="sex">性别</option>
            <option value="nation">民族</option>
            <option value="political_stand">政治面貌</option>
          </select>
        </div>
        <div class="btn-right">
          <button type="button" class="btn J_searchBtn">查询</button>
        </div>
      </div>
    </form>
    <div class="block">
      <div class="block-header">
        <a href="exportPlanEmploymentRateExcel?id=id" class="btn-right btn J_export">导出结果</a>
      </div>
      <div class="block-content clearfix">
        <table>
          <caption>拟就业率</caption>

          <thead>
          <tr class="J_thRender">
            <th rowspan="2"></th>
          </tr>
          <script id="tpl-th" type="text/template">
            {@each data as d}
            <th data-id="&{d.id}" colspan="3">&{d.name}</th>
            {@/each}
          </script>
          <tr class="J_thInfoRender"></tr>
          <script id="tpl-thInfo" type="text/template">
            {@each data as d}
            {@if d.name == '总计'}
            <th>毕业人数</th>
            <th>就业人数</th>
            {@/if}
            {@if d.name != '总计'}
            <th>毕业人数</th>
            <th>就业人数</th>
            {@/if}
            <th>
              <div class="pad-left">
                <span>就业率</span>
                  <span>
                    <a class="a-top" href="javascript:;" data-orderby="0" data-id="&{d.id}"> </a>
                    <a class="a-bottom" href="javascript:;" data-orderby="1" data-id="&{d.id}"> </a>
                  </span>
              </div>
            </th>
            {@/each}

          </script>
          </thead>
          <script id="tpl" type="text/template">
            {@each list as l}
            <td>&{l.GraduationNum}</td>
            <td>&{l.EmpolymentNum}</td>
            <td>&{l.EmpolymentRate}</td>
            {@/each}
          </script>
          <tbody id="J_template"></tbody>
        </table>
        <div id="J_pagination"></div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
  KISSY.use('page/employment/rateNi',function(S){
    S.ready(function(){
      PW.page.rateNi({
        renderTo: '#J_pagination',//分页显示指向
        juicerRender: '#tpl-th',//模版渲染指向
        dataRender: '#J_template',
        pageSize: 10,//每页显示的记录数
        url:'${website}data/queryPlanEmploymentRate',//必选，必须指定ajax的url
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

