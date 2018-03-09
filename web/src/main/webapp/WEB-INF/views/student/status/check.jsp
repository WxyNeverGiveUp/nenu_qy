<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wlm
  Date: 2016/9/1
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--学生的学籍查看详情页 廖月云--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>检查填写信息</title>
    <jsp:include page="../../common/link.jsp"/>
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/common/detail.css">
</head>
<body>
<div class="page">
    <jsp:include page="../../common/header.jsp"/>
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
        <div class="infomation">
            <table>
                <caption>
                    <span></span>学籍信息表
                </caption>
                <tr>
                    <td>姓名</td>
                    <td>${student.name}</td>
                    <td>性别</td>
                    <td>${student.sex}</td>
                    <td>民族</td>
                    <td>${student.nation}</td>
                    <td>政治面貌</td>
                    <td>${student.politicalStand}</td>
                </tr>
                <tr>
                    <td>考生号</td>
                    <td>${student.candidateNumber}</td>
                    <td>身份证号</td>
                    <td>${student.idNumber}</td>
                    <td>学号</td>
                    <td>${student.studentNumber}</td>
                    <td>学校</td>
                    <td>${student.school}</td>
                </tr>
                <tr>
                    <td>学制</td>
                    <td>${student.stuLength}</td>
                    <td>学历</td>
                    <td>${student.qualification}</td>
                    <td>所在院系/分院</td>
                    <td>${student.college}</td>
                    <td>所在年级</td>
                    <td>${student.grade}</td>
                </tr>
                <tr>
                    <td>师范生类别</td>
                    <td>${student.normalStu}</td>
                    <td>培养方式</td>
                    <td>${student.trainingMode}</td>
                    <td>入学日期</td>
                    <td>${student.registDate}</td>
                    <td>毕业日期</td>
                    <td>${student.graduationDate}</td>
                </tr>
                <tr>
                    <td>拟毕业去向</td>
                    <td>${student.intendWhereabouts}</td>
                    <td>困难生类别</td>
                    <td>${student.difficulty}</td>
                    <%--<td>档案是否转入学校</td>--%>
                    <%--<td>${student.isArchiveIntoSchool}</td>--%>
                    <%--<td>户口是否转入学校</td>--%>
                    <%--<td>${student.isHukouIntoSchool}</td>--%>
                </tr>
                <tr>
                    <td>手机号码1</td>
                    <td>${student.cellphone}</td>
                    <td>手机号码2</td>
                    <td>${student.cellphoneBak}</td>
                    <td>QQ号码</td>
                    <td>${student.qq}</td>
                    <td>微信号</td>
                    <td>${student.weixin}</td>
                </tr>
                <tr>
                    <td>家庭电话</td>
                    <td>${student.homePhone}</td>
                    <td>紧急联系方式</td>
                    <td>${student.relativePhone}</td>
                    <td>生源地类别</td>
                    <td>${student.originPlaceType}</td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>寝室楼</td>
                    <td>${student.dormitory}</td>
                    <td>寝室号</td>
                    <td>${student.dormitoryNum}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>专业名称</td>
                    <td colspan="3">${student.major}</td>
                    <td>生源所在地</td>
                    <td colspan="3">${student.originPlace}</td>
                </tr>
                <%--<tr>--%>
                    <%--<td>入学前档案所在单位</td>--%>
                    <%--<td colspan="3">${student.preArchiveLocation}</td>--%>
                    <%--<td>入学前户口所在地</td>--%>
                    <%--<td colspan="3">${student.preHukouLocation}</td>--%>
                <%--</tr>--%>
                <tr>
                    <td>常用电子邮箱</td>
                    <td colspan="3">${student.email}</td>
                    <td>家庭地址</td>
                    <td colspan="3">${student.homeAddress}</td>
                </tr>
                <tr>
                    <td>定向委培单位地址</td>
                    <td colspan="3">${student.weipeiUnitPlace}</td>
                    <td>定向委培单位</td>
                    <td colspan="3">${student.weipeiUnit}</td>
                </tr>
            </table>
        </div>
        <div>
            <table>
                <caption>
                    参考项修改信息详情
                </caption>
                <tr>
                    <td>序号</td>
                    <td>修改项</td>
                    <td>原信息</td>
                    <td>改后信息</td>
                    <td>修改时间</td>
                </tr>
                <c:forEach items="${updateInfoList}" var="updateInfo" varStatus="no">
                    <tr>
                        <td>${no.count}</td>
                        <td>${updateInfo.updateType}</td>
                        <td>${updateInfo.beforeUpdate}</td>
                        <td>${updateInfo.afterUpdate}</td>
                        <td>${updateInfo.updateTime}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div>
            <table>
                <caption>
                    审核状态
                </caption>
                <tr>
                <tr>
                    <td>级别</td>
                    <td>审核结果</td>
                    <td>理由</td>
                    <td>时间</td>
                    <td>操作人</td>
                </tr>
                <tr id="counsellor">
                    <td class="check-level">辅导员</td>
                    <td class="check-result" value="weishenhe">${student.counsellorCheckResult}</td>
                    <td>${student.counsellorCheckReason}</td>
                    <c:choose>
                        <c:when test="${student.checkStatus == 0}">
                            <td></td>
                        </c:when>
                        <c:otherwise>
                            <td>${student.counsellorCheckTime}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${student.counsellorCheckOperator}</td>
                </tr>
                <tr id="deputySecretary">
                    <td class="check-level">副书记</td>
                    <td class="check-result" value="weishenhe">${student.deputySecretaryCheckResult}</td>
                    <td>${student.deputySecretaryCheckReason}</td>
                    <c:choose>
                        <c:when test="${student.checkStatus == 0}">
                            <td></td>
                        </c:when>
                        <c:otherwise>
                            <td>${student.deputySecretaryCheckTime}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${student.deputySecretaryCheckOperator}</td>
                </tr>
                </tr>
            </table>
        </div>
        <div class="material clearfix">
            <strong class="material-show">材料展示</strong>
            <ul class="material-pic clearfix">
                <c:forEach items="${materialList}" var="material">
                    <li>
                        <img src=${uploadWebsite}${material.materialUrl}>
                        <span>${material.materialName}</span>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="confirm">
            <c:if test="${checkStatus eq 1 or checkStatus eq 3 or checkStatus eq 6 or checkStatus eq 9 or checkStatus eq 12}">
                <a href="${website}student/info/new">返回修改</a>
                <c:choose>
                    <c:when test="${student.nation != null && student.politicalStand != null && student.stuLength != -1
                && student.qualification != null && student.college != null && student.grade != -1 && student.major != null && student.intendWhereabouts != null &&
                student.difficulty != null && student.registDate  != null  && student.graduationDate != null && student.cellphone != ''
                && student.qq != '' && student.email != '' && student.relativePhone != ''&& student.homeAddress != ''}">
                        <%--<a href="${website}student/info/submit" class="submit-ready">确认提交</a>--%>
                        <a href="javascript:;" class="submit-ready J_confirmSubmit">确认提交</a>(信息提交后不可修改，请仔细检查后再提交)
                    </c:when>
                    <c:otherwise>
                        信息不完全，如想提交，请输入完整信息。
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
        <div class="pop-pic">
            <img src="">
            <a href="javascript:;">关闭</a>
        </div>
    </div>
</div>
<script type="text/javascript">
    KISSY.use('page/admin_page/students_management/school_roll/checkDetail', function (S) {
        S.ready(function () {
            PW.page.admin_page.students_management.school_roll.checkDetail();
        })
    })
</script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    $(function () {
        $(".J_confirmSubmit").click(function () {
            if (${student.intentionSurveyStatus == 0}) {
                alert("请填写就业意向调查！");
                window.location.href = "/student/intentionSurvey";
            } else {
                window.location.href = "${website}student/info/submit";
            }
        });
    });
</script>

</body>
</html>
