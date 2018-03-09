<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wlm
  Date: 2016/9/5
  Time: 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>修改学籍信息</title>
    <jsp:include page="../../common/link.jsp"/>
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/common/school_roll_common.css">
</head>
<body>
<jsp:include page="../../common/header.jsp"/>
<div class="container">
    <jsp:include page="../../common/Ssidebar.jsp"/>
    <script type="text/javascript">
        KISSY.use('module/sidebar', function (S) {
            S.ready(function () {
                PW.module.Sidebar();
            });
        });
    </script>
    <div class="content">
        <c:if test="${!empty correctMsg}">
            <div class="correct-tip J_tip">${correctMsg}</div>
        </c:if>
        <c:if test="${!empty errMsg}">
            <div class="fault-tip J_tip">${errMsg}</div>
        </c:if>
        <div class="remind">
            <p><span>${checkResult}</span><span>${reason}</span></p>
        </div>
        <form class="main_form" action="${website}student/info/new" method="post" name="student_alter_form">
            <h2>基本信息</h2>
            <div class="summary clearfix">
                <div class="clearfix">
                    <label class="must matter">姓名：</label><span>${studentDto.name}</span>
                    <a class="J_hint J_common_hint" href="javascript:;">提示</a>
                </div>
                <div class="clearfix">
                    <label class="must matter">身份证号：</label><span>${studentDto.idNumber}</span>
                    <a class="J_hint J_common_hint" href="javascript:;">提示</a>
                </div>
                <div class="clearfix">
                    <label class="must matter">考生号：</label><span>${studentDto.candidateNumber}</span>
                    <a class="J_hint J_num_hint" href="javascript:;">提示</a>
                </div>

                <div>
                    <label class="must matter ">学号：</label>
                    <span>${studentDto.studentNumber}</span>
                </div>
                <div class="change-sex">
                    <label class="must matter">性别：</label>
                    <input type="radio" name="sex" value="1" id="male" <c:if test="${student.sex eq 1}">checked</c:if> /><label for="male">男</label>
                    <input type="radio" name="sex" value="2" id="female" <c:if test="${student.sex eq 2}">checked</c:if> /><label for="female">女</label>
                </div>
                <div>
                    <label class="must matter">民族：</label>
                    <select class="J_notOption" name="nationCode" autocomplete="off">
                        <option value="-1">请选择</option>
                        <c:forEach items="${nationList}" var="nation">
                            <option value="${nation.nationId}"
                                    <c:if test="${student.nationCode eq nation.nationId}">selected</c:if>>${nation.nation}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label class="must matter">政治面貌：</label>
                    <select class="J_notOption" name="politicalStandCode" autocomplete="off">
                        <option value="-1">请选择</option>
                        <c:forEach items="${politicalList}" var="political">
                            <option value="${political.politicalStandId}"
                                    <c:if test="${student.politicalStandCode eq political.politicalStandId}">selected</c:if>>${political.politicalStand}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="college-name">
                    <label class="must matter">院校名称：</label>
                    <span class="must">${schoolName}</span>
                </div>
                <div>
                    <label class="matter">学制：</label>
                    <select name="stuLength" autocomplete="off">
                        <option value="-1">请选择</option>
                        <option value="1" name="one-year" <c:if test="${studentDto.stuLength eq 1}">selected</c:if>>1</option>
                        <option value="2" name="two-year" <c:if test="${studentDto.stuLength eq 2}">selected</c:if>>2</option>
                        <option value="3" name="three-year" <c:if test="${studentDto.stuLength eq 3}">selected</c:if>>3</option>
                        <option value="4" name="four-year" <c:if test="${studentDto.stuLength eq 4}">selected</c:if>>4</option>
                        <option value="5" name="five-year" <c:if test="${studentDto.stuLength eq 5}">selected</c:if>>5</option>
                        <option value="6" name="six-year" <c:if test="${studentDto.stuLength eq 6}">selected</c:if>>6</option>
                    </select>
                </div>
                <div>
                    <label class="must matter">学历：</label>
                    <select class="J_notOption" name="qualificationCode" autocomplete="off">
                        <option value="-1">请选择</option>
                        <c:forEach items="${qualificationList}" var="qualification">
                            <option value="${qualification.qualificationId}"
                                    name="undergraduate"
                                    <c:if test="${student.qualificationCode eq qualification.qualificationId}">selected</c:if>>${qualification.qualification}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label class="must matter">所在院系/分院：</label>
                    <select class="J_notOption" name="collegeCode" autocomplete="off">
                        <option value="-1">请选择</option>
                        <c:forEach items="${collegeList}" var="college">
                            <option value="${college.collegeId}"
                                    <c:if test="${student.collegeCode eq college.collegeId}">selected</c:if>>${college.college}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label class="must matter">所在年级：</label>
                    <select class="J_notOption" name="grade" autocomplete="off">
                        <option value="-1">请选择</option>
                        <option value="2010" <c:if test="${studentDto.grade eq 2010}">selected</c:if>>2010</option>
                        <option value="2011" <c:if test="${studentDto.grade eq 2011}">selected</c:if>>2011</option>
                        <option value="2012" <c:if test="${studentDto.grade eq 2012}">selected</c:if>>2012</option>
                        <option value="2013" <c:if test="${studentDto.grade eq 2013}">selected</c:if>>2013</option>
                        <option value="2014" <c:if test="${studentDto.grade eq 2014}">selected</c:if>>2014</option>
                        <option value="2015" <c:if test="${studentDto.grade eq 2015}">selected</c:if>>2015</option>
                        <option value="2016" <c:if test="${studentDto.grade eq 2016}">selected</c:if>>2016</option>
                    </select>
                </div>
                <span class="major-name">
						<label class="must no-matter">专业名称：</label>
                        <select name="majorQualification" class="J_qualification J_notOption" autocomplete="off">
							<option value="-1">请选择</option>
                            <option value="1" <c:if test="${major.qualification eq '本科专业'}"> selected </c:if>>本科专业</option>
							<option value="2" <c:if test="${major.qualification eq '研究生专业'}"> selected </c:if>>研究生专业</option>
						</select>
						<label>------</label>
						<select class="J_majorClass J_notOption" autocomplete="off">
							<option value="-1">请选择专业大类</option>
                            <option value="${major.majorDivisionId}" selected>${major.majorDivision}</option>
						</select>
						<label>------</label>
						<select class="J_majorMinorClass J_notOption" autocomplete="off">
							<option value="-1">请选择专业中类</option>
                            <option value="${major.majorClassId}" selected>${major.majorClass}</option>
						</select>
						<label>------</label>
						<select name="majorCode" class="J_majorName J_notOption" autocomplete="off">
							<option value="-1">请选择专业</option>
                            <option value="${major.majorId}" selected>${major.majorName}</option>
						</select>
					</span>
                <div class="where">
                    <label class="must matter">拟毕业去向：</label>
                    <select class="J_notOption" name="intendWhereaboutsCode" autocomplete="off">
                        <option value="-1">请选择</option>
                        <c:forEach items="${whereAboutGoList}" var="whereAboutGo">
                            <option value="${whereAboutGo.whereAboutGoId}"
                                    <c:if test="${student.intendWhereaboutsCode eq whereAboutGo.whereAboutGoId}">selected</c:if>>${whereAboutGo.whereAboutGo}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="other-major">
                    <label class="must matter">请填写专业名称：</label>
                    <input type="text" name="otherMajor" disabled="true"/>
                </div>
                <div class="other-major"></div>
                <div class="other-major"></div>
                <div class="other-major"></div>
                <div class="kind">
                    <label class="must matter">困难生类别：</label>
                    <select name="difficultyCode" class="kind J_notOption" autocomplete="off">
                        <option value="-1">请选择</option>
                        <c:forEach items="${difficultyList}" var="difficulty">
                            <option value="${difficulty.difficultyId}" <c:if test="${student.difficultyCode eq difficulty.difficultyId}">selected</c:if>>${difficulty.difficultyMode}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="student-class clearfix">
                    <label class="must matter">师范生类别：</label>
                    <span>${studentDto.normalStu}</span>
                    <a class="J_hint J_kind_hint" href="javascript:;">提示</a>
                </div>

                <div class="location clearfix">
                    <label class="must matter">生源所在地：</label>
                    <span>${studentDto.originPlace}</span>
                    <a class="J_hint J_local_hint" href="javascript:;">提示</a>
                </div>

                <div class="clearfix">
                    <label class="must matter">培养方式：</label>
                    <span class="train-way">${studentDto.trainingMode}</span>
                    <a class="J_hint J_train_hint" href="javascript:;">提示</a>
                </div>
                <div></div>
                <div class="train-unit J_orientation clearfix">
                    <label class="must matter">定向/委培单位：</label>
                    <span>${studentDto.weipeiUnit}</span>
                    <a class="J_hint J_unit_hint" href="javascript:;">提示</a>
                </div>
                <div class="unit-addr J_orientation">
                    <label class="must matter">定向/委培单位地址：</label>
                    <input class="area-id-unit" type="hidden" name="areaId">
                    <input id="J_unitAreaHolder" name="weipeiUnitPlace" class="J_areaHolder J_notNull" type="text" autocomplete="off"
                        value="${student.weipeiUnitPlace}"/>
                    <ul class="none areaFidle" id="J_areaFidle_unit"></ul>
                    <p class="unitTip"></p>
                </div>
                <div class="clearfix J_orientation">
                    <label class="must matter">地区代码：</label>
                    <input type="text" class="unit-code J_unit_code" onfocus="this.blur()" name="weipeiUnitPlaceCode" autocomplete="off"
                           value="${student.weipeiUnitPlaceCode}"/>
                </div>
            </div>

            <h2>入学信息</h2>
            <div class="summary clearfix">
                <div>
                    <label class="must matter">入学日期：</label>
                    <input class="J_date J_notNull J_sDate" type="text" name="registDate" value="${studentDto.registDate}"/>
                    <p class="J_date_error">结束日期不能小于开始日期</p>
                </div>
                <div>
                    <label class="must matter">毕业日期：</label>
                    <input class="J_date J_notNull J_eDate" type="text" name="graduationDate"
                           value="${studentDto.graduationDate}"/>
                </div>
                <!-- <div>
                    <label class="must matter">入学方式：</label>
                    <select name="entranceWay" autocomplete="off">
                        <option value="-1">请选择</option>
                        <option value="1" name="no">1</option>
                        <option value="2" name="normal">2</option>
                        <option value="3" name="emphasis">4</option>
                        <option value="4" name="especially">3</option>
                    </select>
                </div> -->
                <%--<div>--%>
                    <%--<label class="matter">入学前户口所在地：</label><input name="preHukouLocation" type="text"--%>
                                                                  <%--value="${studentDto.preHukouLocation}"/>--%>
                <%--</div>--%>
                <div class="residence">
                    <label class="matter">户口是否转入学校：</label>
                    <input type="radio" name="isHukouIntoSchool" value="1" id="already"
                           <c:if test="${student.isHukouIntoSchool eq 1}">checked</c:if> /><label
                        for="already">是</label>
                    <input type="radio" name="isHukouIntoSchool" value="0" id="without"
                           <c:if test="${student.isHukouIntoSchool eq 0}">checked</c:if> /><label
                        for="without">否</label>
                </div>
                <%--<div>--%>
                    <%--<label class="matter">入学前档所在单位：</label><input name="preArchiveLocation" type="text"--%>
                                                                  <%--value="${studentDto.preArchiveLocation}"/>--%>
                <%--</div>--%>
                <div class="record-file">
                    <label class="matter">档案是否转入学校：</label>
                    <input type="radio" name="isArchiveIntoSchool" value="1" id="yes"
                           <c:if test="${student.isArchiveIntoSchool eq 1}">checked</c:if> /><label for="yes">是</label>
                    <input type="radio" name="isArchiveIntoSchool" value="0" id="no"
                           <c:if test="${student.isArchiveIntoSchool eq 0}">checked</c:if> /><label for="no">否</label>
                </div>
            </div>
            <h2>联系方式</h2>
            <div class="summary clearfix">
                <div>
                    <label class="must matter">手机号码：</label>
                    <input type="text" name="cellphone" class="J_phone J_notNull" value="${studentDto.cellphone}"/>
                    <p class="J_phone_error">请输入正确的格式</p>
                </div>
                <div>
                    <label class="matter">手机号码（备用）：</label>
                    <input type="text" name="cellphoneBak" value="${studentDto.cellphoneBak}"/>
                </div>
                <div>
                    <label class="must matter">QQ号码：</label>
                    <input type="text" name="qq" class="j_QQNum J_notNull" value="${studentDto.qq}"/>
                    <p class="J_QQNum_error">请输入正确的格式</p>
                </div>
                <div>
                    <label class="must matter">常用电子邮箱：</label>
                    <input type="text" name="email" class="J_email J_notNull" value="${studentDto.email}"/>
                    <p class="J_email_error">请输入正确的格式</p>
                </div>
                <div>
                    <label class="matter">家庭电话：</label>
                    <input type="text" name="homePhone" class="J_homePhone" value="${studentDto.homePhone}"/>
                    <a class="phone-hint J_hint J_phone_hint" href="javascript:;">提示</a>
                    <p class="J_homePhone_error">格式为"区号-号码"或手机号</p>
                </div>

                <div>
                    <label class="must matter">紧急联系方式：</label>
                    <input type="text" name="relativePhone" value="${studentDto.relativePhone}" class="J_notNull"/>
                </div>
                <div>
                    <label class="matter">微信号：</label>
                    <input type="text" name="weixin" value="${studentDto.weixin}"/>
                </div>
                <div>
                    <label class="must matter">家庭住址：</label>
                    <input type="text" name="homeAddress" class="J_address J_notNull"
                           value="${studentDto.homeAddress}"/>
                    <p class="J_address_error">请输入正确的格式</p>
                </div>
                <div>
                    <label class="matter">寝室楼：</label>
                    <input type="text" name="dormitory" value="${studentDto.dormitory}" />
                </div>
                <div>
                    <label class="matter">寝室号：</label>
                    <input type="text" name="dormitoryNum" value="${studentDto.dormitoryNum}" />
                </div>
            </div>
            <h2>材料信息</h2>
            <div class="material">
                <ul class="clearfix">
                    <c:forEach items="${materialList}" var="material">
                        <li>
                            <img src="${uploadWebsite}${material.materialUrl}">
                            <span>${material.materialName}</span>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="btnbox">
                <input type="button" class="save J_save" value="保&nbsp;&nbsp;&nbsp;存" />
                <input type="submit" class="submit-complete-form" value="下一步"/>
            </div>
        </form>
    </div>
</div>
<div class="scene none"></div>
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
<div class="pop-pic none J_pop_pic">
    <img src=""/>
    <a href="javascript:;" class="J_shut">关闭</a>
</div>
<script type="text/javascript">
    KISSY.use('page/student_page/school_roll/alterInfo', function (S) {
        S.ready(function () {
            PW.page.student_page.school_roll.alterInfo();
        });
    });
</script>
</body>
</html>
