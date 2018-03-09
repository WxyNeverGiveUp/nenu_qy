<%--
  Created by IntelliJ IDEA.
  User: zhaixm
  Date: 14-9-28
  Time: 上午9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>东北师范大学网上签约管理系统--修改个人信息</title>
    <%@include file="../common/link.jsp" %>
    <%@include file="../common/script.jsp" %>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/system.css"/>
</head>
<body>
<%@include file="../common/header.jsp" %>
<div class="continer">
    <%@include file="../common/Asidebar.jsp" %>
    <script type="text/javascript">
        KISSY.use('module/sidebar', function (S) {
            S.ready(function () {
                PW.module.Sidebar();
            });
        });
    </script>
    <div class="content">
        <%--<%@include file="../common/crumbs.jsp"%>--%>
        <c:if test="${!empty correctMsg}">
            <div class="correct-tip J_tip">${correctMsg}</div>
        </c:if>
        <c:if test="${!empty errMsg}">
            <div class="fault-tip J_tip">${errMsg}</div>
        </c:if>
        <div class="page-content">
            <div class="block">
                <div class="block-header">
                    <h3>修改个人信息</h3>
                </div>
                <form:form action="${website}admin/user/update" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <div class="block-content">
                        <div class="content-input">
                            <label>真实姓名</label>
                            <input type="text" name="realName" value="${user.realName}" class="inputText"
                                   data-valid-rule="notNull" maxLength="50" placeholder="用户名小于50个字符">
                        </div>
                        <div class="content-input">
                            <label class="conten-lable">用户名</label>
                            <input type="text" name="username" readonly="readonly" value="${user.username}"
                                   class="inputText" placeholder="以英文开头的6位以上的英文或数字"
                                   data-valid-rule="notNull&isCommonUser" value="${username}">
                        </div>
                        <div class="content-input">
                            <label>手机</label>
                            <input type="text" name="cellphone" class="inputText" data-valid-rule=
                                    "notNull&isMobile" value="${user.cellphone}">
                        </div>
                        <div class="block">
                            <div class="block-header">
                                <h3>角色选择</h3>
                            </div>
                            <div class="block-content clearfix">
                                <c:forEach items="${roles}" var="roleIds" varStatus="state">
                                    <div class="content-select">
                                        <td><input type="checkbox" name="roleIds"
                                                   <c:if test="${isChecked[state.index]}">checked="true"</c:if>
                                                   value="${roleIds.id}"><label>${roleIds.description}</label>
                                            <c:if test="${roleIds.description =='学院副书记'}">
                                                <select name="secretaryCollege">
                                                    <c:forEach items="${colleges}" var="colleges">
                                                        <option value="${colleges.collegeId}" <c:if
                                                                test="${collegeSec eq colleges.collegeId}"> selected </c:if> > ${colleges.college}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleIds.description =='辅导员'}">
                                                <select name="counselorCollege">
                                                    <c:forEach items="${colleges}" var="colleges">
                                                        <option value="${colleges.collegeId}" <c:if
                                                                test="${collegeCoun eq colleges.collegeId}"> selected </c:if> > ${colleges.college}</option>
                                                    </c:forEach>
                                                </select>
                                                <!-- <select name="counselorQualification">
                                                <option value="本科">本科</option>
                                                <option value="硕士研究生">硕士研究生</option>
                                                <option value="博士研究生">博士研究生</option>
                                                </select> -->
                                                <input type="button" value="添加专业" class="add-linkage">
                                                <br/>
                                            </c:if>
                                        </td>
                                    </div>
                                </c:forEach>
                                <c:forEach items="${majors}" var="major">
                                    <div class="existing-major">
                                        <input type="text" name="qualification" readonly="readonly"
                                               <c:choose>
                                                <c:when test="${major.qualification eq '本科专业'}">
                                                    value="1"
                                                </c:when>
                                                <c:when test="${major.qualification eq '研究生专业'}">
                                                    value="2"
                                                </c:when>
                                               </c:choose>>
                                        <em class="existing-big">${major.qualification}</em>
                                        <label>------</label>
                                        <em class="existing-big">${major.majorDivision}</em>
                                        <label>------</label>
                                        <em class="existing-minor">${major.majorClass}</em>
                                        <label>------</label>
                                        <input type="text" name="majorCode" readonly="readonly"
                                               value="${major.majorId}"/>
                                        <em>${major.majorName}</em>
                                        <span class='del-existing'>删除</span>
                                    </div>
                                </c:forEach>
                                <div class="add-box">

                                </div>

                            </div>
                        </div>
                        <div class="content-input">
                            <button type="button" class="btn btn-margin-left btn-margin-right J_submitBtn">确定</button>
                            <button type="reset" class="btn">取消</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    KISSY.use('page/system/mod-user', function (S) {
        S.ready(function () {
            PW.page.modUser();
        });
    });
</script>
</body>
</html>
