<%--
  Created by IntelliJ IDEA.
  User: chenyt
  Date: 14-9-27
  Time: 下午3:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>东北师范大学网上签约管理系统--添加用户</title>

    <%@ include file="../common/link.jsp" %>
    <%@ include file="../common/script.jsp" %>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/system.css"/>
</head>
<body>

<%@ include file="../common/header.jsp" %>
<div class="continer">

    <%@ include file="../common/Asidebar.jsp" %>
    <script type="text/javascript">
        KISSY.use('module/sidebar', function (S) {
            S.ready(function () {
                PW.module.Sidebar();
            });
        });
    </script>
    <div class="content">

        <%--<%@ include file="../common/crumbs.jsp" %>--%>
        <c:if test="${!empty correctMsg}">
            <div class="correct-tip J_tip">${correctMsg}</div>
        </c:if>
        <c:if test="${!empty errMsg}">
            <div class="fault-tip J_tip">${errMsg}</div>
        </c:if>
        <div class="page-content">
            <form action="${website}admin/user" method="post">
                <div class="block">
                    <div class="block-header">
                        <h3>添加用户</h3>
                    </div>
                    <div class="block-content">
                        <div class="content-input">
                            <label>真实姓名</label>
                            <input type="text" name="realName" class="inputText" data-valid-rule="notNull"
                                   maxLength="50" placeholder="用户名小于50个字符">
                        </div>
                        <div class="content-input">
                            <label class="conten-lable">用户名</label>
                            <input type="text" id="J_username" name="username" class="inputText"
                                   placeholder="以英文开头的6位以上的英文或数字" data-valid-rule="notNull&isCommonUser">
                        </div>
                        <div class="content-input">
                            <label>手机</label>
                            <input type="text" name="cellphone" class="inputText" data-valid-rule=
                                    "notNull&isMobile">
                        </div>
                        <div class="content-input">
                            <label>密码</label>
                            <input type="password" id="pwd" name="password" class="inputText" placeholder="6位以上的英文或数字"
                                   data-valid-rule="notNull&isPassword">
                        </div>
                        <div class="content-input">
                            <label>重复密码</label>
                            <input type="password" name="secondPwd" class="inputText" data-valid-rule=
                                    "notNull&isPassword&require(pwd)&equal(pwd)">
                        </div>
                    </div>
                </div>
                <div class="block">
                    <div class="block-header">
                        <h3>角色选择</h3>
                    </div>
                    <div class="block-content">
                        <c:forEach items="${roles}" var="roles">
                            <div class="content-select">
                                    <%--<td>--%>
                                <input type="checkbox" name="roleIds" value="${roles.id}">
                                <label>
                                        ${roles.description}
                                </label>
                                <c:if test="${roles.description =='学院副书记'}">
                                    <select name="secretaryCollege">
                                        <c:forEach items="${colleges}" var="colleges">
                                            <option value="${colleges.collegeId}"> ${colleges.college}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                                <c:if test="${roles.description =='辅导员'}">
                                    <select name="counselorCollege">
                                        <c:forEach items="${colleges}" var="colleges">
                                            <option value="${colleges.collegeId}"> ${colleges.college}</option>
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
                                    <%--</td>--%>
                            </div>

                        </c:forEach>
                        <div class="add-box">

                        </div>
                    </div>
                </div>
                <div class="content-input">
                    <button type="submit" class="btn btn-margin-left btn-margin-right J_submitBtn">确定</button>
                    <a class="btn" href="${website}admin/user/toUserList">返回</a>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    KISSY.use('page/system/add-user', function (S) {
        S.ready(function () {
            PW.page.addUser();
        });
    });
</script>
</body>
</html>
