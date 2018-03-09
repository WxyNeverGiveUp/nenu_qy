<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: wlm
  Date: 2016/8/23
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--管理员的学籍详情页 廖月云--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>学籍信息详情</title>
    <jsp:include page="../../common/link.jsp"/>
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/common/detail.css">
</head>
<body>
<div class="page">
    <jsp:include page="../../common/header.jsp"/>
    <jsp:include page="../../common/Asidebar.jsp"/>
    <script type="text/javascript">
        KISSY.use('module/sidebar', function (S) {
            S.ready(function () {
                PW.module.Sidebar();
            });
        });
    </script>
    <div class="content">
        <div class="infomation">
            <table>
                <caption>
                    <span></span>学籍信息表
                </caption>
                <tr>
                    <td>姓名</td>
                    <td>${stuStatusInfo.name}</td>
                    <td>性别</td>
                    <td>${stuStatusInfo.sex}</td>
                    <td>民族</td>
                    <td>${stuStatusInfo.nation}</td>
                    <td>政治面貌</td>
                    <td>${stuStatusInfo.politicalStand}</td>
                </tr>
                <tr>
                    <td>考生号</td>
                    <td>${stuStatusInfo.candidateNumber}</td>
                    <td>身份证号</td>
                    <td>${stuStatusInfo.idNumber}</td>
                    <td>学号</td>
                    <td>${stuStatusInfo.studentNumber}</td>
                    <td>学校</td>
                    <td>${stuStatusInfo.school}</td>
                </tr>
                <tr>
                    <td>学制</td>
                    <td>${stuStatusInfo.stuLength}</td>
                    <td>学历</td>
                    <td>${stuStatusInfo.qualification}</td>
                    <td>所在院校/分院</td>
                    <td>${stuStatusInfo.college}</td>
                    <td>所在年级</td>
                    <td>${stuStatusInfo.grade}</td>
                </tr>
                <tr>
                    <td>师范生类别</td>
                    <td>${stuStatusInfo.normalStu}</td>
                    <td>培养方式</td>
                    <td>${stuStatusInfo.trainingMode}</td>
                    <td>入学日期</td>
                    <td>${stuStatusInfo.registDate}</td>
                    <td>毕业日期</td>
                    <td>${stuStatusInfo.graduationDate}</td>
                </tr>
                <tr>
                    <td>拟毕业去向</td>
                    <td>${stuStatusInfo.intendWhereabouts}</td>
                    <td>困难生类别</td>
                    <td>${stuStatusInfo.difficulty}</td>
                    <%--<td>档案是否转入学校</td>--%>
                    <%--<td>${stuStatusInfo.isArchiveIntoSchool}</td>--%>
                    <%--<td>户口是否转入学校</td>--%>
                    <%--<td>${stuStatusInfo.isHukouIntoSchool}</td>--%>
                    <td>寝室楼</td>
                    <td>${stuStatusInfo.dormitory}</td>
                    <td>寝室号</td>
                    <td>${stuStatusInfo.dormitoryNum}</td>
                </tr>
                <tr>
                    <td>手机号码1</td>
                    <td>${stuStatusInfo.cellphone}</td>
                    <td>手机号码2</td>
                    <td>${stuStatusInfo.cellphoneBak}</td>
                    <td>QQ号码</td>
                    <td>${stuStatusInfo.qq}</td>
                    <td>微信号</td>
                    <td>${stuStatusInfo.weixin}</td>
                </tr>
                <tr>
                    <td>家庭电话</td>
                    <td>${stuStatusInfo.homePhone}</td>
                    <td>紧急联系方式</td>
                    <td>${stuStatusInfo.relativePhone}</td>
                    <td>生源地类别</td>
                    <td>${stuStatusInfo.originPlaceType}</td>
                    <td>常用电子邮箱</td>
                    <td>${stuStatusInfo.email}</td>
                </tr>
                <%--<tr>--%>
                    <%--<td>寝室楼</td>--%>
                    <%--<td>${stuStatusInfo.dormitory}</td>--%>
                    <%--<td>寝室号</td>--%>
                    <%--<td>${stuStatusInfo.dormitoryNum}</td>--%>
                    <%--<td></td>--%>
                    <%--<td></td>--%>
                    <%--<td></td>--%>
                    <%--<td></td>--%>
                <%--</tr>--%>
                <tr>
                    <td>专业名称</td>
                    <td colspan="3">${stuStatusInfo.major}</td>
                    <td>生源所在地</td>
                    <td colspan="3">${stuStatusInfo.originPlaceImport}</td>
                </tr>
                <tr>
                    <td>家庭地址</td>
                    <td colspan="3">${stuStatusInfo.homeAddress}</td>
                    <td>学生填写生源所在地</td>
                    <td colspan="3">
                       ${stuStatusInfo.originPlace}
                    </td>

                </tr>
                <%--<tr>
                    <td>入学前档案所在单位</td>
                    <td colspan="3">${stuStatusInfo.preArchiveLocation}</td>
                    <td>入学前户口所在地</td>
                    <td colspan="3">${stuStatusInfo.preHukouLocation}</td>
                </tr>--%>
                <tr>
                    <td>定向委培单位地址</td>
                    <td colspan="3">${stuStatusInfo.weipeiUnitPlace}</td>
                    <td>定向委培单位</td>
                    <td colspan="3">${stuStatusInfo.weipeiUnit}</td>
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
        <div class="show">
            <table>
                <caption>
                    审核结果
                </caption>
                <tr>
                    <td>级别</td>
                    <td>审核结果</td>
                    <td>理由</td>
                    <td>时间</td>
                    <td>操作人</td>
                    <td>操作</td>
                </tr>
                <tr id="counsellor">
                    <td class="check-level">辅导员</td>
                    <td class="check-result" value="weishenhe">${stuStatusInfo.counsellorCheckResult}</td>
                    <td>${stuStatusInfo.counsellorCheckReason}</td>
                    <c:choose>
                        <c:when test="${stuStatusInfo.checkStatus == 0}">
                            <td></td>
                        </c:when>
                        <c:otherwise>
                            <td>${stuStatusInfo.counsellorCheckTime}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${stuStatusInfo.counsellorCheckOperator}</td>
                    <td class="edit-result">
                        <shiro:hasAnyRoles name="admin">
                            <input type="button" value="审核" check-level="counsellor">
                        </shiro:hasAnyRoles>

                        <shiro:hasAnyRoles name="counsellor">
                            <c:if test="${canCheck == 1}">
                                <input type="button" value="审核" check-level="counsellor">
                            </c:if>
                            <c:if test="${canCheck == 2}">`
                                <span style="display:inline-block;background: gray; color:white;width: 100px;height: 25px;line-height: 25px;">审核</span>
                                <p>该学生信息没有提交，暂不能审核</p>
                            </c:if>
                            <c:if test="${canCheck == 3}">`
                                <span style="display:inline-block;background: gray; color:white;width: 100px;height: 25px;line-height: 25px;">审核</span>
                                <p>学生专业与辅导员所管理专业不匹配，暂不能审核</p>
                            </c:if>
                        </shiro:hasAnyRoles>
                    </td>
                </tr>
                <tr id="deputySecretary">
                    <td class="check-level">副书记</td>
                    <td class="check-result" value="weishenhe">${stuStatusInfo.deputySecretaryCheckResult}</td>
                    <td>${stuStatusInfo.deputySecretaryCheckReason}</td>
                    <c:choose>
                        <c:when test="${stuStatusInfo.checkStatus == 0}">
                            <td></td>
                        </c:when>
                        <c:otherwise>
                            <td>${stuStatusInfo.deputySecretaryCheckTime}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${stuStatusInfo.deputySecretaryCheckOperator}</td>
                    <td class="edit-result">
                        <c:if test="${stuStatusInfo.counsellorCheckResult == '审核通过'}">
                            <shiro:hasAnyRoles name="college-secretary">
                                <input type="button" value="审核" check-level="deputySecretary">
                            </shiro:hasAnyRoles>
                        </c:if>
                        <shiro:hasAnyRoles name="admin">
                            <input type="button" value="审核" check-level="deputySecretary">
                        </shiro:hasAnyRoles>
                    </td>
                </tr>
                <tr id="school">
                    <td class="check-level">学校</td>
                    <%--<td class="check-result" value="weishenhe">${stuStatusInfo.schoolCheckResult}</td>--%>
                    <%--<td>${stuStatusInfo.schoolCheckReason}</td>--%>
                    <%--<td>${stuStatusInfo.schoolCheckTime}</td>--%>
                    <%--<td>${stuStatusInfo.schoolCheckOperator}</td>--%>
                    <td class="check-result" value="weishenhe"> </td>
                    <td> </td>
                    <td> </td>
                    <td> </td>
                    <td class="edit-result2">
                        <shiro:hasAnyRoles name="admin, employ">
                            <input type="button" value="打回" check-level="school">
                        </shiro:hasAnyRoles>
                    </td>
                </tr>
            </table>
        </div>
        <div class="material clearfix">
            <strong class="material-show">材料展示</strong>
            <ul class="material-pic clearfix">
                <c:forEach items="${materialList}" var="material">
                    <li>
                        <img src="${uploadWebsite}${material.materialUrl}">
                        <span>${material.materialName}</span>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="pop-check">
            <form action="javacsript:;" class="check">
                <fieldset class="clearfix" style="position:relative;">
                    <input type="hidden" name="id" value="${stuStatusInfo.id}"/>
                    <input type="hidden" name="checkLevel"/>
                    <legend>审核状态</legend>
                    <p class="check-state-time">审核</p>
                    <span class="check-state">
							<strong>
								审核状态
							</strong>

							<input type="radio" name="checkResult" value="2" checked><label>通过</label>
							<input type="radio" name="checkResult" value="3"><label>不通过</label>
						</span>
                    <span class="check-reason">
							<strong>
								审核理由
							</strong>
							<select name="checkReason">
								<option value="-1">请选择</option>
								<option value="1" class="yes">信息属实</option>
								<option value="2" class="not">修改师范生类型不通过，证明材料不充分</option>
								<option value="3" class="not">修改生源地城市不通过，证明材料不充分</option>
								<option value="4" class="not">修改委培方式不通过，证明材料不充分</option>
								<option value="5" class="not">修改定向委培单位不通过，证明材料不充分</option>
								<option value="6" class="not">个人信息有错误</option>
								<option value="99">其他</option>
							</select>
							<input type="text" name="otherReason" class="check-other-input">
						</span>
                    <div class="check-submit-line clearfix">
                        <input type="submit" value="确认修改" class="check-submit save-submit">
                        <input type="button" value="返回" class="check-submit back">
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="pop-check2">
            <form action="javacsript:;" class="check2">
                <fieldset class="clearfix" style="position:relative;">
                    <input type="hidden" name="id" value="${stuStatusInfo.id}"/>
                    <input type="hidden" name="checkLevel" />
                    <input type="hidden" name="checkResult" value="1"/>
                    <input type="hidden" name="checkReason" value="1"/>

                    <span class="check-state">
							<strong>
								确认打回吗？
							</strong>
						</span>
                    <div class="check-submit-line clearfix">
                        <input type="submit" value="确认修改" class="check-submit save-submit2">
                        <input type="button" value="返回" class="check-submit back">
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="pop-pic">
            <img src="">
            <a href="javascript:;">关闭</a>
        </div>
        <div class="more-detail">
            <%--<shiro:hasAnyRoles name="admin, director, employ">
                <a href="${website}admin/dispatch/comments/${stuStatusInfo.id}">办公信息</a>
            </shiro:hasAnyRoles>
            <c:if test="${dispatchId ne 0}">
                <a href="${website}admin/dispatch/detail/${stuStatusInfo.id}">派遣信息</a>
            </c:if>--%>
            <c:if test="${statusInfoCache.get('id') ne '0'}">
                <c:choose>
                    <c:when test="${minority eq 0}">
                        <a href="${website}admin/status/info/detail/${statusInfoCache.get("id")}" class="next-student">下一个学生</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${website}admin/minority/status/info/detail/${statusInfoCache.get("id")}" class="next-student">下一个学生</a>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
        <form class="parameter-hidden">
            <fieldset>
                <legend>为下一个学生设置的隐藏域</legend>
                <input type="hidden" name="id" value="${statusInfoCache.get("id")}" class="parameter"/>
            </fieldset>
        </form>
    </div>
</div>
<script type="text/javascript">
    KISSY.use('page/admin_page/students_management/school_roll/infoDetail', function (S) {
        S.ready(function () {
            PW.page.admin_page.students_management.school_roll.infoDetail();
        })
    })
</script>
</body>
</html>
