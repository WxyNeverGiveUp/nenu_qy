<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wlm
  Date: 2016/8/30
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>新增学生</title>
    <jsp:include page="../../common/link.jsp"/>
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/admin_page/students_management/newStudent.css">
    <script type="text/javascript" src="${staticWebsite}upload/js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        KISSY.use('page/admin_page/students_management/newStudent',function(S){
            S.ready(function(){
                PW.page.admin_page.students_management.newStudent();
            })
        })
    </script>
    <script type="text/javascript">
        KISSY.use('page/admin_page/students_management/school_roll/alterInfo',function(S){
            S.ready(function(){
                PW.page.admin_page.students_management.school_roll.alterInfo();
            });
        });
    </script>
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
<div class="alertWrapper">
    <c:if test="${!empty errMsg}">
        <div class="fault-tip J_tip">${errMsg}</div>
    </c:if>
</div>
<div>
    <h2 class="headTitle">新增学生</h2>
</div>
<form class="J_complete-form pw-validation" action="${website}admin/status/info/new" method="post" name="J_complete-form">



        <div class="divCommon fontColor">
            <label  for="">姓名：</label>
            <input type="text" id="" name="name" class="studentName J_notNull"/>
            <p class="J_stuName" display="none">请输入正确的姓名</p>
        </div>

        <div class="divCommon fontColor">
            <label for="">身份证号：</label>
            <input type="text" id="" name="idNumber" class="idNumber J_notNull"/>
            <p class="J_idNumber">请输入18位身份证号</p>
        </div>

        <div class="divCommon fontColor">
            <label class="update-position">考生号：</label>
            <input type="text" id="" name="candidateNumber" class="candidateNumber J_examNo J_notNull update-position" />
            <a class="J_hint J_num_hint update-position" href="javascript:;">提示</a>
            <p class="J_candidateNum">请输入正确考生号</p>
        </div>

        <div class="divCommon fontColor">
            <label>学号：</label>
            <input type="text" id="" name="studentNumber" class="studentNumber J_notNull"/>
            <p class="J_stuNumber">请输入正确的学号</p>
        </div>

        <div class="divCommon fontColor">
            <label>民族：</label>
            <select class="nation J_notOption" name="nationCode" autocomplete="off">
                <option value="-1">请选择</option>
                <c:forEach items="${nationList}" var="nation">
                    <option value="${nation.nationId}">${nation.nation}</option>
                </c:forEach>
            </select>
        </div>

    <div class="divCommon fontColor change-sex">
        <label>性别：</label>
        <select class="sex J_notOption" name="sex" autocomplete="off">
            <option value="-1">请选择</option>
            <option value="1">男</option>
            <option value="2">女</option>
        </select>
    </div>


    <div class="divCommon fontColor">
            <label>政治面貌：</label>
            <select class="politicalStand J_notOption" name="politicalStandCode" autocomplete="off">
                <option value="-1">请选择</option>
                <c:forEach items="${politicalList}" var="political">
                    <option value="${political.politicalStandId}">${political.politicalStand}</option>
                </c:forEach>
            </select>
        </div>

        <div class="divCommon fontColor">
            <label>学制：</label>
            <select class="stuLength J_notOption" name="stuLength" autocomplete="off">
                <option value="-1">请选择</option>
                <option value="1" name="one-year">1</option>
                <option value="2" name="two-year">2</option>
                <option value="3" name="three-year">3</option>
                <option value="4" name="four-year">4</option>
                <option value="5" name="five-year">5</option>
                <option value="6" name="six-year">6</option>
            </select>
        </div>

        <div class="clearfix divCommon fontColor">
            <label>学历：</label>
            <select class="qualification J_notOption" name="qualificationCode" autocomplete="off">
                <option value="-1">请选择</option>
                <c:forEach items="${qualificationList}" var="qualification">
                    <option value="${qualification.qualificationId}">${qualification.qualification}</option>
                </c:forEach>
            </select>
        </div>

        <div class="divCommon fontColor">
            <label>所在院系：</label>
            <select class="college J_notOption" name="collegeCode" autocomplete="off">
                <option value="-1">请选择</option>
                <c:forEach items="${collegeList}" var="college">
                    <option value="${college.collegeId}">${college.college}</option>
                </c:forEach>
            </select>
        </div>

        <div class="divCommon fontColor">
            <label>所在年级：</label>
            <select class="grade J_notOption" name="grade" autocomplete="off">
                <option value="-1">请选择</option>
                <option value="2010">2010</option>
                <option value="2011">2011</option>
                <option value="2012">2012</option>
                <option value="2013">2013</option>
                <option value="2014">2014</option>
                <option value="2015">2015</option>
                <option value="2016">2016</option>
            </select>
        </div>

        <div class="divCommon fontColor">
            <label>师范生类别：</label>
            <select class="J_notOption"  name="normalStuCode" autocomplete="off">
                <option value="-1">请选择</option>
                <c:forEach items="${normalList}" var="normal">
                    <option value="${normal.normalStuId}">${normal.normalStu}</option>
                </c:forEach>
            </select>
        </div>

        <div class="divCommon fontColor clearfix">
            <label>培养方式：</label>
            <select class="J_notOption trainingMode" name="trainingModeCode" autocomplete="off">
                <option value="-1">请选择</option>
                <option value="1">非定向</option>
                <option value="2">定向</option>
                <option value="3">自筹</option>
                <option value="4">委培</option>
                <%--<option value="4">在职</option>                    就业那边说没有在职这个字段--%>
            </select>
        </div>
        <div class="fontColor divCommon update-parent">
            <label>生源所在地：</label>
            <input class="area-id-local" type="hidden" name="areaId">
            <input type="text" name="originPlace" autocomplete="off" class="J_areaHolder J_notNull" id="J_localAreaHolder"/>
            <ul class="none areaFidle" id="J_areaFidle_local"></ul>
            <p style="padding-left: 112px;"></p>
            <a class="J_hint J_local_stu_hint update-a" href="javascript:;">提示</a>
        </div>
        <div class="areaCode shengyuan">
            <label>地区代码：</label>
            <input type="text" name="originPlaceCode" class="local-code J_local_code " onfocus="this.blur()" value="222222"/>
        </div>

        <div class="company divCommon fontColor J_orientation">
            <label for="">委培/定向单位：</label>
            <input type="text" id="unit" name="weipeiUnit"  class="J_notNull"/>
        </div>

        <div class="companyAddress fontColor J_orientation divCommon">
            <label>定向/委培单位地址：</label>
            <input class="area-id-unit" type="hidden" name="areaId">
            <input id="J_unitAreaHolder" class="J_areaHolder J_notNull" type="text"  name="weipeiUnitPlace" autocomplete="off" disabled="false"/>
            <ul class="none areaFidle position" id="J_areaFidle_unit"></ul>
            <p style="padding-left: 118px;"></p>
        </div>
        <div class="areaCode J_orientation weipei">
            <label>地区代码：</label>
            <input type="text" class="unit-code J_unit_code" onfocus="this.blur()" name="weipeiUnitPlaceCode" value="000000"/>
        </div>

        <div class="major-name origin fontColor clearfix">
            <label class="major-label">专业名称：</label>
            <select  class="J_qualification J_notOption" name="majorQualification" autocomplete="off">
                <option value="-1" selected="">请选择</option>
                <option value="1">本科专业</option>
                <option value="2">研究生专业</option>
            </select>
            <label>------</label>
            <select class="J_notOption J_majorClass" autocomplete="off" name="majorClass">
                <option value="-1" selected="">请选择专业大类</option>
            </select>
            <label>------</label>
            <select class="J_notOption J_majorMinorClass" autocomplete="off" name="majorMinorClass">
                <option value="-1" selected="">请选择专业中类</option>
            </select>
            <label>------</label>
            <select class="J_notOption J_majorName" name="majorCode" autocomplete="off">
                <option value="-1" selected="">请选择专业</option>
            </select>
        </div>

        <div class="other-major fontColor divCommon">
            <label class="must matter">请填写专业名称：</label>
            <input type="text" name="otherMajor" disabled="true" />
        </div>

        <div class="submitWrapper">
            <input type="submit" class="newStudentSubmit" value="新增学生"/>
        </div>
</form>
<div class="hint-box J_change none">
    <div class="hint clearfix">提示信息
        <span class="J_close">X</span>
    </div>
    <div class="J_msg_box msg-box">
        <p class="J_msg"></p>
        <p class="J_doc_hint"></p>
        <p class="J_hint1"></p>
        <p class="J_hint2"></p>
    </div>

    <span class="close-btn J_close">关闭</span>
</div>
</body>
</html>

