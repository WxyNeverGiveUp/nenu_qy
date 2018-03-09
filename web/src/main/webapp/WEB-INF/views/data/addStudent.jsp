<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset = "utf-8"/>
    <title>东北师范大学网上签约管理系统 -- 就业管理 -- 添加学生</title>
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
        <form class="block J_studentForm" action="${website}data/add" method="POST">
            <div class="block-header">
                <h3>学生添加</h3>
            </div>
            <div class="block-content clearfix">
                <div class="control-area control-area-short">
                    <label>姓名</label>
                    <input type="text"class="textTheme" data-valid-rule="notNull" name="name"  />
                </div>
                <div class="control-area control-area-short">
                    <label>身份证号</label>
                    <input type="text"  class="textTheme" data-valid-rule="isIdCard" name="idNumber"  />
                </div>
                <div class="control-area control-area-short">
                    <label>出生日期</label>
                    <input type="text"  class="textTheme date" data-type="date" data-format="YYYY-MM-DD" name="dob" data-valid-rule="notNull" />
                </div>
                <div class="control-area control-area-short">
                    <label>考生号</label>
                    <input type="text"  class="textTheme date" data-valid-rule="notNull" name="examId"  />
                </div>
                <div class="control-area control-area-short">
                    <label>手机</label>
                    <input type="text"  class="textTheme date" data-valid-rule="isMobile" name="tele"  />
                </div>
                <div class="control-area control-area-short">
                    <label>政治面貌</label>
                    <select class="textTheme" autocomplete="off" name="politicalStandId" data-valid-rule="scale(0,10000,0)">
                        <option value="0">请选择</option>
                        <c:forEach var = "item" items="${politicalCodes}">
                            <option value="${item.politicalStandId}">${item.politicalStand}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="control-area control-area-short">
                    <label>学号</label>
                    <input type="text"  class="textTheme date" data-valid-rule="notNull" name="studentId"  />
                </div>
                <div class="control-area control-area-short">
                    <label>性别</label>
                    <select class="textTheme" autocomplete="off" name="sexId" data-valid-rule="scale(0,10000,0)" >
                        <option value="0">请选择</option>
                        <c:forEach var = "item" items="${sexCodes}">
                            <option value="${item.sexId}">${item.sex}</option>
                        </c:forEach>
                    </select>
                </div>

                <shiro:hasAnyRoles name="complex,work-comp">
                    <div class="control-area control-area-short">
                        <label>所在院系</label>
                        <select id="J_institutionList" class="textTheme" autocomplete="off" name="collegeId" data-valid-rule="notNull">
                            <option value="0">请选择</option>
                            <c:forEach var = "item" items="${collegeCodes}">
                                <option value="${item.collegeId}">${item.college}</option>
                            </c:forEach>
                        </select>
                    </div>
                </shiro:hasAnyRoles>

                <shiro:hasAnyRoles name="complex,work-comp,college-secretary">
                <div class="control-area control-area-short">
                    <label>学历</label>
                    <select id="J_degreeList" class="textTheme" autocomplete="off" name="qualification" >
                        <%--<option value="0">请选择</option>--%>
                        <c:forEach var = "item" items="${quanlificationStudyList}">
                            <option value="${item}">${item}</option>
                        </c:forEach>
                    </select>
                </div>
                </shiro:hasAnyRoles>

                <div class="control-area control-area-short">
                    <label>班级</label>
                    <input type="int"  class="textTheme date" name="studentClass"  />
                </div>
                <div class="control-area control-area-short">
                    <label>民族</label>
                    <select class="textTheme" autocomplete="off" name="nationId" data-valid-rule="scale(0,10000,0)">
                    <option value="0">请选择</option>
                    <c:forEach var = "item" items="${nationCodes}">
                        <option value="${item.nationId}">${item.nation}</option>
                    </c:forEach>
                    </select>
                </div>
                <div class="control-area control-area-short">
                    <label>生源地 省(直辖市)</label>
                    <select id="J_proviceListEmp"  class="textTheme" autocomplete="off" name="fromProvince" data-valid-rule="scale(0,10000000,0)">
                        <option value="0">请选择</option>
                        <c:forEach var = "item" items="${provinces}">
                            <option value="${item.provinceCode}">${item.provinceName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="control-area control-area-short">
                    <label>生源地 市/区</label>
                    <select id="J_cityListEmp" class="textTheme" autocomplete="off" name="nativePlaceId" data-valid-rule="scale(0,10000000,0)" >
                        <option value="0">请选择</option>
                        <c:forEach var = "item" items="${placeCodes}">
                            <option value="${item.placeId}">${item.showName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="control-area control-area-short">
                    <label>学科</label>
                    <select class="textTheme" autocomplete="off" name="majorDevision" >
                        <%--<option value="0">请选择</option>--%>
                        <c:forEach var = "item" items="${majorDivisions}">
                            <option value="${item.majorDevisionName}">${item.majorDevisionName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="control-area control-area-short">
                    <label>专业</label>
                    <select id="J_majorList"  class="textTheme" autocomplete="off" name="majorId" data-valid-rule="scale(0,10000000,0)" >
                        <option value="0">请选择</option>
                        <c:forEach var = "item" items="${majors}">
                            <option value="${item.majorId}">${item.majorName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="control-area control-area-short">
                    <label>入学日期</label>
                    <input type="text" data-type="date" class="textTheme date" data-format="YYYY-MM-DD" name="registTime" data-valid-rule="notNull" />
                </div>
                <div class="control-area control-area-short">
                    <label>毕业日期</label>
                    <input type="text" data-type="date" class="textTheme date" data-format="YYYY-MM-DD" name="graduationTime" data-valid-rule="notNull"/>
                </div>
                <div class="control-area control-area-short">
                    <label>学制</label>
                    <select class="textTheme" autocomplete="off" name="stuLength" data-valid-rule="scale(0,10000000,0)">
                        <option value="0">请选择</option>
                        <option value="1.5">1.5</option>
                        <option value="2">2</option>
                        <option value="3.5">2.5</option>
                        <option value="3">3</option>
                        <option value="3.5">3.5</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                    </select>
                </div>
                <div class="control-area control-area-short">
                    <label>培养方式</label>
                    <select class="textTheme J_train_way" autocomplete="off" name="trainingModeId" data-valid-rule="scale(0,10000000,0)">
                        <option value="0">请选择</option>
                        <c:forEach var = "item" items="${trainingModeCodes}">
                            <option value="${item.trainingModeId}">${item.trainingMode}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="control-area control-area-short">
                    <label>困难生类别</label>
                    <select class="textTheme" autocomplete="off" name="difficultyTypeId">
                        <%--<option value="0">请选择</option>--%>
                        <c:forEach var = "item" items="${difficultyCodes}">
                            <option value="${item.difficultyId}">${item.difficultyMode}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="control-area control-area-short">
                    <label>师范生类别</label>
                    <select class="textTheme" autocomplete="off" name="normalStuId" data-valid-rule="scale(0,10000000,0)">
                        <option value="0">请选择</option>
                        <c:forEach var = "item" items="${normalCodes}">
                            <option value="${item.normalStuId}">${item.normalStu}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="control-area control-area-long">
                    <label>定向/委培单位</label>
                    <input type="text"class="textTheme J_to_company"  name="weipeiInsitution"  />
                </div>
                <div class="control-area control-area-long">
                    <label>档案转入学校</label>
                    <div class="checkbox checkbox-long">
                        <input type="radio" class="textTheme" name="documentIn" value="1" />是
                    </div>
                    <div class="checkbox checkbox-long">
                        <input type="radio" class="textTheme" name="documentIn" value="0" />否
                    </div>
                </div>
                <div class="control-area control-area-long">
                    <label>户口转入学校</label>
                    <div class="checkbox checkbox-long">
                        <input type="radio" class="textTheme" name="hukouIn" value="1" />是
                    </div>
                    <div class="checkbox checkbox-long">
                        <input type="radio" class="textTheme" name="hukouIn" value="0" />否
                    </div>
                </div>
                <div class="control-area control-area-long">
                    <label>档案所在单位</label>
                    <input type="text"class="textTheme"  name="docIns" placeholder="入学前档案所在单位" />
                </div>
                <div class="control-area control-area-long">
                    <label>户口所在派出所</label>
                    <input type="text"class="textTheme"  name="hukouIns" placeholder="入学前档案所在派出所" />
                </div>
                <div class="control-area control-area-short">
                    <label>拟毕业去向</label>
                    <select class="textTheme" autocomplete="off" name="wherewantgo">
                        <option value="">请选择</option>
                        <option value="工作">工作</option>
                        <option value="升学">升学</option>
                        <option value="出国">出国</option>
                        <%--<c:forEach var = "item" items="${whereAboutGoCodes}">--%>
                            <%--<option value="${item.whereAboutGo}">${item.whereAboutGo}</option>--%>
                        <%--</c:forEach>--%>
                    </select>
                </div>
                <div class="control-area control-area-short">
                    <label>拟就业单位 </label>
                    <input type="text"  class="textTheme date J_danwei"  name="wherewantgoIns" value="${student.wherewantgoIns}" />
                </div>
                <%--<div class="control-area control-area-short">--%>
                    <%--<label>拟就业单位 </label>--%>
                    <%--<input type="text"  class="textTheme date J_danwei"  name="institutionName"  />--%>
                <%--</div>--%>
                <div class="control-area control-area-short">
                    <label>就业困难类别</label>
                    <select class="textTheme" autocomplete="off" name="workDifficultyMode">
                        <option value="">请选择</option>
                        <option value="经济困难">经济困难</option>
                        <option value="技能不足">技能不足</option>
                        <option value="观念偏差">观念偏差</option>
                        <option value="心理障碍">心理障碍</option>
                        <option value="特殊需要">特殊需要</option>
                        <option value="全部">全部</option>
                    </select>
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
</body>
</html>