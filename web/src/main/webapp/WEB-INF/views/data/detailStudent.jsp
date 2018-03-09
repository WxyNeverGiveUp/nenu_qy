<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset = "utf-8"/>
  <title>东北师范大学网上签约管理系统 -- 就业管理 -- 查看学生</title>
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
      <div class="block-header">
        <h3>学生查看</h3>
      </div>
    <div class="block">
      <div class="block-content clearfix">
        <input type="hidden" class="textTheme" data-valid-rule="notNull" name="id" value="${student.id}"  />
        <div class="control-area control-area-short">
          <label>姓名</label>
          <span>${student.name}</span>
        </div>
        <div class="control-area control-area-short">
          detailStudent.jsp
        </div>
        <div class="control-area control-area-short">
          <label>出生日期</label>
          <span>${student.dob}</span>
        </div>
        <div class="control-area control-area-short">
          <label>考生号</label>
          <span>${student.examId}</span>
        </div>
        <div class="control-area control-area-short">
          <label>手机</label>
          <span>${student.tele}</span>
        </div>
        <div class="control-area control-area-short">
          <label>政治面貌</label>
          <span>${student.politicalStand}</span>
        </div>
        <div class="control-area control-area-short">
          <label>学号</label>
          <span>${student.studentId}</span>
        </div>
        <div class="control-area control-area-short">
          <label>性别</label>
          <span>${student.sex}</span>
        </div>
        <div class="control-area control-area-short">
          <label>学历</label>
          <span>${student.qualification}</span>
        </div>
        <div class="control-area control-area-short">
          <label>所在院系</label>
          <span>${student.collegeName}</span>
        </div>
        <div class="control-area control-area-short">
          <label>班级</label>
          <span>${student.studentClass}</span>
        </div>
        <div class="control-area control-area-short">
          <label>民族</label>
          <span>${student.nation}</span>
        </div>
        <div class="control-area control-area-short">
          <label>生源地 市/区</label>
          <span>${student.fromPlace}</span>
        </div>
        <div class="control-area control-area-short">
          <label>学科</label>
          <span>${student.majorDevision}</span>

        </div>
        <div class="control-area control-area-short">
          <label>专业</label>
          <span>${student.major}</span>

        </div>
        <div class="control-area control-area-short">
          <label>入学日期</label>
          <span>${student.registTime}</span>

        </div>
        <div class="control-area control-area-short">
          <label>毕业日期</label>
          <span>${student.graduationTime}</span>

        </div>
        <div class="control-area control-area-short">
          <label>学制</label>
          <span>${student.stuLength}</span>

        </div>
        <div class="control-area control-area-short">
          <label>培养方式</label>
          <span>${student.trainingMode}</span>

        </div>
<%--        <div class="control-area control-area-short">
          <label>困难生类别</label>
          <span>${student.difficultyMode}</span>

        </div>--%>
        <div class="control-area control-area-short">
          <label>师范生类别</label>
          <span>${student.normalStu}</span>

        </div>
        <div class="control-area control-area-long">
          <label>定向/委培单位</label>
          <span>${student.weipeiInsitution}</span>

        </div>
        <div class="control-area control-area-long">
          <label>档案转入学校</label>
          <span>${student.documentIn}</span>

        </div>
        <div class="control-area control-area-long">
          <label>户口转入学校</label>
          <span>${student.hukouIn}</span>

        </div>
        <div class="control-area control-area-long">
          <label>档案所在单位</label>
          <span>${student.documentIn}</span>

        </div>
        <div class="control-area control-area-long">
          <label>档案所在派出所</label>
          <span>${student.hukouIns}</span>

        </div>
<%--        <div class="control-area control-area-short">
          <label>拟毕业去向</label>
          <span>${student.whereAboutGo}</span>

        </div>--%>
        <div class="control-area control-area-short">
          <label>拟就业单位 </label>
          <span>${student.institutionName}</span>

        </div>
        <div class="control-area control-area-short">
          <label>就业困难类别</label>
          <span>${student.workDifficultyMode}</span>

        </div>
      </div>
  </div>
    </div>
</div>
<script type="text/javascript">
  KISSY.use('page/employment/add-student,widget/calendar',function(S){
    S.ready(function(){
      PW.page.addStudent();
    });
  });
</script>
</body>
</html>