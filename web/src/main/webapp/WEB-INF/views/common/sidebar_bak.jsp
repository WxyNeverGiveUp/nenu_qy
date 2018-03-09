<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2014/10/15
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--sidebar start-->
<div class="sidebar">
    <ul>
        <c:forEach items="${menus}" var="menu">
            <c:choose>
                <c:when test="${isSelected != 0}">
                    <c:choose>
                        <c:when test="${menu.resource.url == isSelected}">
                            <li class="active open">
                                <a href="javaScript:;">
                                    <i class="${menu.resource.permission}"></i>
                                    <span>${menu.resource.name}</span>
                                    <b></b>
                                </a>
                                <ul class="submenu">
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="javaScript:;">
                                    <i class="${menu.resource.permission}"></i>
                                    <span>${menu.resource.name}</span>
                                    <b></b>
                                </a>
                                <ul class="submenu none">
                        </c:otherwise>
                        </c:choose>
                        </c:when>
                        <c:otherwise>
                        <li>
                            <a href="javaScript:;">
                                <i class="${menu.resource.permission}"></i>
                                <span>${menu.resource.name}</span>
                                <b></b>
                            </a>
                            <ul class="submenu none">
                        </c:otherwise>
            </c:choose>
            <c:forEach items="${menu.childList}" var="me" varStatus="status">
                                <li>
                                    <a href="${website}${me.url}">${me.name}</a>
                                </li>
                                <c:if test="${status.last}"></ul>
                            </c:if>
            </c:forEach>
                        </li>
    </c:forEach>
                        <%--<li>--%>
                        <%--<c:if test="${yun != null}">--%>
                        <%--<a href="${yun}">--%>
                        <%--<i class="cloud"></i>--%>
                        <%--<span>中心云盘</span>--%>
                        <%--</a>--%>
                        <%--</c:if>--%>
                        <%--</li>--%>
                        <li>
                            <a href="${website}logout">
                                <i class="logout"></i>
                                <span>安全退出</span>
                            </a>
                        </li>
                    </ul>
</div>

<!--sidebar end-->
<script type="text/javascript">
    KISSY.use('module/sidebar', function (S) {
        S.ready(function () {
            PW.module.Sidebar();
        });
    });
</script>