<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
  <meta charset = "utf-8"/>
  <title>东北师范大学网上签约管理系统 -- 就业管理 -- 更新学生</title>
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
    <form class="block J_studentForm" action="${website}data/update/${type}" method="POST">
      <div class="block-header">
        <c:choose>
          <c:when test="${type == 0}">
            <h3>学生录入</h3>
          </c:when>
          <c:when test="${type == 1}">
            <h3>学生审核</h3>
          </c:when>
          <c:when test="${type == 2}">
            <h3>学生修改</h3>
          </c:when>
        </c:choose>

      </div>
      <div class="block-content clearfix">
        <div class="block-header">
          <h3>学生基本信息</h3>
        </div>
        <input type="hidden" class="textTheme" data-valid-rule="notNull" name="id" value="${student.id}"  />
        <div class="control-area control-area-short">
          <label>姓名</label>
          <input type="text"class="textTheme" data-valid-rule="notNull" name="name" value="${student.name}"  />
        </div>
        <div class="control-area control-area-short">
          <label>身份证号</label>
          <input type="text"  class="textTheme" data-valid-rule="isIdCard" name="idNumber" value="${student.idNumber}" />
        </div>
        <div class="control-area control-area-short">
          <label>出生日期</label>
          <input type="text"  class="textTheme" data-type="date" data-format="YYYY-MM-DD" name="dob" value="${student.dob}"  />
        </div>
        <div class="control-area control-area-short">
          <label>考生号</label>
          <input type="text"  class="textTheme" data-valid-rule="notNull" name="examId" value="${student.examId}" />
        </div>
        <div class="control-area control-area-short">
          <label>手机</label>
          <input type="text"  class="textTheme" data-valid-rule="isMobile" name="tele" value="${student.tele}" />
        </div>
        <div class="control-area control-area-short">
          <label>政治面貌</label>
          <select class="textTheme" autocomplete="off" name="politicalStandId" data-valid-rule="scale(0,10000,0)">
            <option value="0">请选择</option>
            <c:forEach var = "item" items="${politicalCodes}">
              <option value="${item.politicalStandId}"
                      <c:if test="${item.politicalStandId == student.politicalStandId}">
                        selected = "selected"
                      </c:if>>
                      ${item.politicalStand}</option>
            </c:forEach>
          </select>
        </div>
        <div class="control-area control-area-short">
          <label>学号</label>
          <input type="text"  class="textTheme" data-valid-rule="notNull" name="studentId" value="${student.studentId}" />
        </div>

        <shiro:hasAnyRoles name="complex,work-comp">
          <div class="control-area control-area-short">
            <label>协议编码</label>
            <input type="text"  class="textTheme" name="agreementNumber" value="${student.agreementNumber}" />
          </div>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name="counsellor">
          <div class="control-area control-area-short">
            <label>协议编码</label>
            <input type="text"  class="textTheme" name="agreementNumber" readonly="true" value="${student.agreementNumber}" />
          </div>
        </shiro:hasAnyRoles>

        <div class="control-area control-area-short">
          <label>性别</label>
          <select class="textTheme" autocomplete="off" name="sexId" data-valid-rule="scale(0,10000,0)">
            <option value="0">请选择</option>
            <c:forEach var = "item" items="${sexCodes}">
              <option value="${item.sexId}" <c:if test="${item.sexId == student.sexId}"> selected = 'selected'
              </c:if>>${item.sex}</option>
            </c:forEach>
          </select>
        </div>

        <shiro:hasAnyRoles name="complex,work-comp">
        <div class="control-area control-area-short">
          <label>学历</label>
          <select class="textTheme" autocomplete="off" name="qualification" id="J_degreeList">
            <%--<option value="0">请选择</option>--%>
            <c:forEach var = "item" items="${quanlificationStudyList}">
              <option value="${item}" <c:if test="${item == student.qualification}"> selected = 'selected'
              </c:if>>${item}</option>
            </c:forEach>
          </select>
        </div>


        <div class="control-area control-area-short">
          <label>所在院系</label>
          <select class="textTheme" autocomplete="off" name="collegeId" data-valid-rule="notNull" id="J_institutionList">
            <option value="0">请选择</option>
            <c:forEach var = "item" items="${collegeCodes}">
              <option value="${item.collegeId}"
               <c:if test="${item.college == student.collegeName}">
                  selected = 'selected'
              </c:if>>${item.college}</option>
            </c:forEach>
          </select>
        </div>
        </shiro:hasAnyRoles>

        <div class="control-area control-area-short">
          <label>班级</label>
          <input type="int"  class="textTheme " name="studentClass"  value="${student.studentClass}"/>
        </div>
        <div class="control-area control-area-short">
          <label>民族</label>
          <select class="textTheme" autocomplete="off" name="nationId" data-valid-rule="scale(0,10000,0)">
            <option value="0">请选择</option>
            <c:forEach var = "item" items="${nationCodes}">
              <option value="${item.nationId}" <c:if test="${item.nationId == student.nationId}"> selected = 'selected'
              </c:if>>${item.nation}</option>
            </c:forEach>
          </select>
        </div>
        <div class="control-area control-area-short">
          <label>生源所在地参考</label>
          <input type="text"  class="textTheme " name="nativePlaceRemind" readonly="true" value="${student.nativePlaceRemind}" />
        </div>
        <div class="control-area control-area-short">
          <label>生源地 省(直辖市)</label>
          <select id = "J_proviceListEmp" class="textTheme" autocomplete="off" name="fromProvince" data-valid-rule="scale(0,10000000,0)">
            <option value="0">请选择</option>
            <c:forEach var = "item" items="${provinces}">
              <option value="${item.provinceCode}"
                      <c:if test="${fn:startsWith(student.nativePlaceId,item.provinceCode)}"> selected = 'selected'
                      </c:if>
                      >${item.provinceName}</option>
            </c:forEach>
          </select>
        </div>
        <div class="control-area control-area-short">
          <label>生源地 市/区</label>
          <select id="J_cityListEmp" class="textTheme" autocomplete="off" name="nativePlaceId" data-valid-rule="scale(0,10000000,0)">
            <option value="0">请选择</option>
            <c:forEach var = "item" items="${placeCodes}">
              <option value="${item.placeId}"
                      <c:if test="${item.placeId == student.nativePlaceId}">
                        selected = "selected"
                      </c:if>>
                  ${item.showName}</option>
            </c:forEach>
          </select>
        </div>
        <div class="control-area control-area-short">
          <label>学科</label>
          <select class="textTheme" autocomplete="off" name="majorDevision">
            <%--<option value="0">请选择</option>--%>
            <c:forEach var = "item" items="${majorDivisions}">
              <option value="${item.majorDevisionName}"
                      <c:if test="${item.majorDevisionName == student.majorDevision}">
                          selected = "selected"
                      </c:if>
                      >${item.majorDevisionName}</option>
            </c:forEach>
          </select>
        </div>
        <div class="control-area control-area-short">
          <label>专业</label>
          <select class="textTheme" autocomplete="off" name="majorId" data-valid-rule="scale(0,10000000,0)" id="J_majorList">
            <option value="0">请选择</option>
            <c:forEach var = "item" items="${majors}">
              <option value="${item.majorId}"
                      <c:if test="${item.majorId == student.majorId}">
                          selected = "selected"
                      </c:if>>
                      ${item.majorName}</option>
            </c:forEach>
          </select>
        </div>
        <div class="control-area control-area-short">
          <label>入学日期</label>
          <input type="text" data-type="date" class="textTheme date" data-format="YYYY-MM-DD" name="registTime" value="${student.registTime}" data-valid-rule="notNull"/>
        </div>
        <div class="control-area control-area-short">
          <label>毕业日期</label>
          <input type="text" data-type="date" class="textTheme date" data-format="YYYY-MM-DD" name="graduationTime" value="${student.graduationTime}" data-valid-rule="notNull"/>
        </div>
        <div class="control-area control-area-short">
          <label>学制</label>
          <select class="textTheme" autocomplete="off" name="stuLength" data-valid-rule="scale(0,10000000,0)">
            <option value="0">请选择</option>
            <option value="1" <c:if test="${student.stuLength == 1}">selected = "selected"</c:if> > 1</option>
            <option value="2"<c:if test="${student.stuLength == 2}"> selected = "selected" </c:if> >2</option>
            <option value="2.5"<c:if test="${student.stuLength == 2.5}"> selected = "selected" </c:if>>2.5</option>
            <option value="3"<c:if test="${student.stuLength == 3}"> selected = "selected" </c:if>>3</option>
            <option value="3.5"<c:if test="${student.stuLength == 3.5}"> selected = "selected" </c:if>>3.5</option>
            <option value="4"<c:if test="${student.stuLength == 4}"> selected = "selected" </c:if>>4</option>
            <option value="5"<c:if test="${student.stuLength == 5}"> selected = "selected" </c:if>>5</option>
            <option value="6"<c:if test="${student.stuLength == 6}"> selected = "selected" </c:if>>6</option>
            <option value="7"<c:if test="${student.stuLength == 7}"> selected = "selected" </c:if>>7</option>
            <option value="8"<c:if test="${student.stuLength == 8}"> selected = "selected" </c:if>>8</option>
            <option value="9"<c:if test="${student.stuLength == 9}"> selected = "selected" </c:if>>9</option>

          </select>
        </div>
        
        <shiro:hasAnyRoles name="complex,work-comp">
          <div class="control-area control-area-short">
            <label>培养方式</label>
            <select class="textTheme J_train_way" autocomplete="off" name="trainingModeId" data-valid-rule="scale(0,10000000,0)">

              <option value="0">请选择</option>
              <c:forEach var = "item" items="${trainingModeCodes}">
                <option value="${item.trainingModeId}" <c:if test="${item.trainingModeId == student.trainingModeId}"> selected = 'selected'
                </c:if>>${item.trainingMode}</option>
              </c:forEach>
            </select>
            </select>
          </div>
        </shiro:hasAnyRoles>

        <shiro:hasAnyRoles name="counsellor">
          <div class="control-area control-area-short">
            <label>培养方式</label>
            <select class="textTheme J_train_way" autocomplete="off" name="trainingModeId"  disabled = "true" data-valid-rule="scale(0,10000000,0)">

              <option value="0">请选择</option>
              <c:forEach var = "item" items="${trainingModeCodes}">
                <option value="${item.trainingModeId}" <c:if test="${item.trainingModeId == student.trainingModeId}"> selected = 'selected'
                </c:if>>${item.trainingMode}</option>
              </c:forEach>
            </select>
            </select>
          </div>
        </shiro:hasAnyRoles>

        <shiro:hasAnyRoles name="complex,work-comp">
          <div class="control-area control-area-long">
            <label>定向/委培单位</label>
            <input type="text"class="textTheme J_to_company"  name="weipeiInsitution"  value="${student.weipeiInsitution}"/>
          </div>
        </shiro:hasAnyRoles>

        <shiro:hasAnyRoles name="counsellor">
          <div class="control-area control-area-long">
            <label>定向/委培单位</label>
            <input type="text"class="textTheme J_to_company" readonly = "true" name="weipeiInsitution"  value="${student.weipeiInsitution}"/>
          </div>
        </shiro:hasAnyRoles>

        <shiro:hasAnyRoles name="complex,work-comp">
          <div class="control-area control-area-short">
            <label>师范生类别</label>
            <select class="textTheme" autocomplete="off" name="normalStuId" data-valid-rule="scale(0,10000000,0)">
              <option value="0">请选择</option>
              <c:forEach var = "item" items="${normalCodes}">
                <option value="${item.normalStuId}"
                        <c:if test="${item.normalStuId == student.normalStuId}">
                            selected = "selected"
                        </c:if>>
                        ${item.normalStu}</option>
              </c:forEach>
            </select>
          </div>
        </shiro:hasAnyRoles>

        <shiro:hasAnyRoles name="counsellor">
          <div class="control-area control-area-short">
            <label>师范生类别</label>
            <select class="textTheme" autocomplete="off" name="normalStuId" disabled = "true" data-valid-rule="scale(0,10000000,0)">
              <option value="0">请选择</option>
              <c:forEach var = "item" items="${normalCodes}">
                <option value="${item.normalStuId}"
                        <c:if test="${item.normalStuId == student.normalStuId}">
                          selected = "selected"
                        </c:if>>
                    ${item.normalStu}</option>
              </c:forEach>
            </select>
          </div>
        </shiro:hasAnyRoles>


        <div class="control-area control-area-short">
          <label>困难生类别</label>
          <select class="textTheme" autocomplete="off" name="difficultyTypeId">
            <%--<option value="0">请选择</option>--%>
            <c:forEach var = "item" items="${difficultyCodes}">
              <option value="${item.difficultyId}"
                      <c:if test="${item.difficultyId == student.difficultyTypeId}">
                        selected = "selected"
                      </c:if>>
                  ${item.difficultyMode}
              </option>
            </c:forEach>
          </select>
        </div>
        <div class="control-area control-area-long">
          <label>档案转入学校</label>
          <div class="checkbox checkbox-long">
            <input type="radio" class="textTheme" name="documentIn" value="1" <c:if test="${student.documentIn == 1}"> checked = "checked" </c:if> />是
          </div>
          <div class="checkbox checkbox-long">
            <input type="radio" class="textTheme" name="documentIn" value="0" <c:if test="${student.documentIn == 0}"> checked = "checked" </c:if> />否
          </div>
        </div>
        <div class="control-area control-area-long">
          <label>户口转入学校</label>
          <div class="checkbox checkbox-long">
            <input type="radio" class="textTheme" name="hukouIn" value="1" <c:if test="${student.hukouIn == 1}"> checked = "checked" </c:if> />是
          </div>
          <div class="checkbox checkbox-long">
            <input type="radio" class="textTheme" name="hukouIn" value="0" <c:if test="${student.hukouIn == 0}"> checked = "checked" </c:if>/>否
          </div>
        </div>
        <div class="control-area control-area-long">
          <label>档案所在单位</label>
          <input type="text"class="textTheme"  name="docIns" placeholder="入学前档案所在单位" value="${student.docIns}"/>
        </div>
        <div class="control-area control-area-long">
          <label>档案所在派出所</label>
          <input type="text"class="textTheme"  name="hukouIns" placeholder="入学前档案所在派出所" value="${student.hukouIns}"/>
        </div>
        <div class="control-area control-area-short">
          <label>拟毕业去向</label>
            <select class="textTheme" autocomplete="off" name="wherewantgo">
                <option value="">请选择</option>
                <option value="工作" <c:if test="${student.wherewantgo == '工作'}"> selected = selected </c:if> >工作</option>
                <option value="升学"<c:if test="${student.wherewantgo == '升学'}"> selected = selected </c:if>>升学</option>
                <option value="出国"<c:if test="${student.wherewantgo == '出国'}"> selected = selected </c:if>>出国</option>

            </select>
        </div>
        <div class="control-area control-area-short">
          <label>拟就业单位 </label>
          <input type="text"  class="textTheme  J_danwei"  name="wherewantgoIns" value="${student.wherewantgoIns}" />
        </div>
        <div class="control-area control-area-short">
          <label>就业困难类别</label>
          <select class="textTheme" autocomplete="off" name="workDifficultyMode">
            <option value="">请选择</option>
            <option value="经济困难" <c:if test="${student.workDifficultyMode == '经济困难'}"> selected = selected </c:if> >经济困难</option>
            <option value="技能不足" <c:if test="${student.workDifficultyMode == '技能不足'}"> selected = selected </c:if>>技能不足</option>
            <option value="观念偏差" <c:if test="${student.workDifficultyMode == '观念偏差'}"> selected = selected </c:if>>观念偏差</option>
            <option value="心理障碍" <c:if test="${student.workDifficultyMode == '心理障碍'}"> selected = selected </c:if>>心理障碍</option>
            <option value="特殊需要" <c:if test="${student.workDifficultyMode == '特殊需要'}"> selected = selected </c:if>>特殊需要</option>
            <option value="全部" <c:if test="${student.workDifficultyMode == '全部'}"> selected = selected </c:if>>全部</option>
          </select>
        </div>
        <shiro:hasAnyRoles name="complex,work-comp">
          <div class="control-area control-area-short">
            <label>备注</label>
            <input type="text"class="textTheme" name="remark" value="${student.remark}"  />
          </div>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name="counsellor">
          <div class="control-area control-area-short">
            <label>备注</label>
            <input type="text"class="textTheme" name="remark" readonly="true" value="${student.remark}"  />
          </div>
        </shiro:hasAnyRoles>

        </div>

      <div class="block-content clearfix">
        <div class="block-header">
          <h3>学生毕业信息</h3>
        </div>
      <div class="control-area control-area-short">
        <label>毕业去向 </label>
        <select class="textTheme J_graduate_go" autocomplete="off" name="whereaboutgoId" data-valid-rule="scale(0,10000000,0)">
          <option value="">请选择</option>
          <c:forEach var="item" items="${whereAboutGoCodes}">
            <option value="${item.whereAboutGoId}" <c:if test="${item.whereAboutGoId == student.whereaboutgoId}"> selected = 'selected'
            </c:if>>${item.whereAboutGo}</option>
          </c:forEach>
        </select>
      </div>
      <div class="control-area control-area-short">
        <label>单位组织机构代码</label>
        <input type="text"  class="textTheme  J_company_code"  name="institutionId"  value="${student.institutionId}"/>
      </div>
      <div class="control-area control-area-short">
        <label>单位组织机构名称</label>
        <input type="text"  class="textTheme  J_company_name"  name="institutionName"  value="${student.institutionName}"/>
      </div>
        <div class="control-area control-area-short">
          <input type="hidden" name="companyId" value="${company.id}" class="company-id">
          <label>就业OA中单位</label>
          <input class="textTheme" autocomplete="off" type="text" id="J_companyHolder" value="${company.companyName}"/>
          <ul class="none company-fidle" id="J_companyFidle"></ul>
        </div>


        <div class="control-area control-area-short">
          <label>单位所在地 省(直辖市)</label>
          <select id = "J_proviceListA" class="textTheme" autocomplete="off" name="fromProvince" >
            <option value="0">请选择</option>
            <c:forEach var = "item" items="${provinces}">
              <option value="${item.provinceCode}"
                      <c:if test="${fn:startsWith(student.insPlaceId,item.provinceCode)}"> selected = 'selected'
                      </c:if>
                      >${item.provinceName}</option>
            </c:forEach>
          </select>
        </div>
      <div class="control-area control-area-short">
        <label>单位所在地</label>
        <select id = "J_cityListA" class="textTheme" autocomplete="off" name="insPlaceId" >
          <option value="0">请选择</option>
          <c:forEach var = "item" items="${placeCodes}">
            <option value="${item.placeId}"
                    <c:if test="${item.placeId == student.insPlaceId}">
                      selected = "selected"
                    </c:if>>
                ${item.showName}</option>
          </c:forEach>
        </select>
      </div>
      <div class="control-area control-area-short">
        <label>单位性质</label>
        <select class="textTheme J_company_prop" autocomplete="off" name="insModeId" >
          <option value="0">请选择</option>
          <c:forEach var = "item" items="${insModeCodes}">
            <option value="${item.insModeId}"
                    <c:if test="${item.insModeId == student.insModeId}">
                      selected = "selected"
                    </c:if>>
                ${item.insMode}</option>
          </c:forEach>
        </select>
      </div>
      <div class="control-area control-area-short">
        <label>单位行业</label>
        <select class="textTheme J_company_industry" autocomplete="off" name="insFieldId" >
          <option value="0">请选择</option>
          <c:forEach var = "item" items="${insFieldCodes}">
            <option value="${item.insFieldId}"
                    <c:if test="${item.insFieldId == student.insFieldId}">
                      selected = "selected"
                    </c:if>>
                ${item.insField}</option>
          </c:forEach>
        </select>
      </div>
        <div class="control-area control-area-short">
          <label>工作职位类别</label>
          <select class="textTheme J_job_type" autocomplete="off" name="jobId">
            <option value="0">请选择</option>
            <c:forEach var = "item" items="${jobCodes}">
              <option value="${item.jobId}"
                      <c:if test="${item.jobId == student.jobId}">
                        selected = "selected"
                      </c:if>>
                  ${item.job}</option>
            </c:forEach>
          </select>
        </div>
      <div class="control-area control-area-short">
        <label>报到证签发类别</label>
        <select class="textTheme" autocomplete="off" name="reportModeId" data-valid-rule="scale(0,10000000,0)">
          <option value="0">请选择</option>
          <c:forEach var = "item" items="${reportCodes}">
            <option value="${item.reportModeId}"
                    <c:if test="${item.reportModeId == student.reportModeId}">
                      selected = "selected"
                    </c:if>>
                ${item.reportMode}</option>
          </c:forEach>
        </select>
      </div>
        <div class="control-area control-area-short">
          <label>报到证迁往单位名称</label>
          <input type="text"  class="textTheme "  name="reportIns" data-valid-rule="notNull" value="${student.reportIns}"/>
        </div>
        <div class="control-area control-area-short">
          <label>迁往单位所在地 省(直辖市)</label>
          <select id = "J_proviceListB" class="textTheme" autocomplete="off" name="fromProvince" data-valid-rule="scale(0,10000000,0)">
            <option value="0">请选择</option>
            <c:forEach var = "item" items="${provinces}">
              <option value="${item.provinceCode}"
                      <c:if test="${fn:startsWith(student.desInsId,item.provinceCode)}"> selected = 'selected'
                      </c:if>
                      >${item.provinceName}</option>
            </c:forEach>
          </select>
        </div>
      <div class="control-area control-area-short">
        <label>迁往单位所在地</label>
        <select  id = "J_cityListB" class="textTheme" autocomplete="on" name="desInsId" data-valid-rule="scale(0,10000000,0)">
          <option value="0">请选择</option>
          <c:forEach var = "item" items="${placeCodes}">
            <option value="${item.placeId}"
                    <c:if test="${item.placeId == student.desInsId}">
                      selected = "selected"
                    </c:if>>
                ${item.showName}</option>
         </c:forEach>
        </select>
      </div>

      <div class="control-area control-area-short">
        <label>报到证编号</label>
        <input type="text"  class="textTheme "  name="reportNumId"  value="${student.reportNumId}"/>
      </div>
      <div class="control-area control-area-short">
        <label>报道起始时间</label>
        <input type="text" data-type="date" class="textTheme date" data-format="YYYY-MM-DD" name="reportStartTime" value="${student.reportStartTime}"/>
      </div>
      </div>
    </form>
    <div class="btn-center">
      <button type="button" class="btn-margin-right btn J_submit">确定</button>
      <a class="btn" href="${website}data/toListStudent" type="reset">取消</a>
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
<script type="text/javascript">
  KISSY.use('page/company/company-info',function(S){
    S.ready(function(){
      PW.page.companyInfo({
        renderTo: '#J_pagination',//分页显示指向
        juicerRender: '#tpl',//模版渲染指向
        dataRender: '#J_template',                pageSize: 10,//每页显示的记录数
        url:'${website}company/ajax/companyInfo/query',//必选，必须指定ajax的url
        configUrl:function(url,page,me,prevdata){
          var url = url+'/'+page;
          return url;
        },
        type:'get'
      });
    });
  });
</script>
</body>
</html>