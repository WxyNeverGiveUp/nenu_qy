<%--
  User: dujuan
  Date: 2014/9/28
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <jsp:include page="../common/link.jsp"/>
    <jsp:include page="../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/index.css">
    <title>东北师范大学网上签约管理系统</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="continer">
    <jsp:include page="../common/sidebar_bak.jsp"/>
    <div class="content">
        <%@include file="../common/crumbs.jsp"%>
        <div class="page-content">
            <!--page-header start-->
            <%--<div class="page-header">--%>
                <%--<h1>--%>
                    <%--<span>${weather}</span>--%>
                    <%--<span>${nowWeek}，</span>--%>
                    <%--<span>${nowTime}，</span>--%>
                    <%--<i></i>--%>
                <%--</h1>--%>
            <%--</div>--%>
            <!--page-header end-->
            <div class="page-list clearfix">
                <div class="page-box">
                    <h1>
                        <a>招聘信息</a>
                    </h1>
                    <div class="page-box-body">
                        <%--<h2>自<input class="textTheme date">&nbsp;起，</h2>--%>
                        <ul>
                            <li>
                                <a href="${website}company/toList">共有<span class="J_companyInfo high-light">${companyCount}</span>家单位来我校招聘</a>
                            </li>
                            <li>
                                <a href="${website}recruit/toList">累计发布招聘信息<span class="J_recruitInfo high-light">${recruitCount}</span>条</a>
                            </li>
                            <li>
                                <a href="${website}recruit/lecture/toQueryList">召开宣讲会<span class="J_lectureInfo high-light">${meetCount}</span>场</a>
                            </li>
                            <li>
                                <a href="${website}recruit/space/toQueryList?examType=1">面试<span class="J_interViewInfo high-light">${interview}</span>场</a>
                            </li>
                            <li>
                                <a href="${website}recruit/space/toQueryList?examType=2">笔试<span class="J_writtenInfo high-light">${writtenCount}</span>场</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="page-box">
                    <h1>
                        <a>2015届学生生源信息</a>
                    </h1>
                    <div class="page-box-body">
                        <ul>
                            <li>我校2016届本科生和硕士研究生共有6049人。</li>
                            <li>本科生3653人，硕士研究生2396人。</li>
                            <li>公费师范生1171人，非师范及自费师范生2482人。</li>
                            <li><a class="link" href="http://careers.nenu.edu.cn/index.php?r=employer/studentsDetail/id/1">本科生生源情况</a></li>
                            <li><a class="link" href="http://careers.nenu.edu.cn/index.php?r=employer/studentsDetail/id/52">硕士研究生生源情况</a></li>
                        </ul>
                    </div>
                </div>
                <div class="page-box">
                    <h1>
                        <a>招聘会日历</a>
                    </h1>
                    <div class="page-box-body">
                        <div class="calendar-holder">
                            <div class="calendar-header J_canlendarHeader"></div>
                            <div class="calendar-body">
                                <div class="calendar-wkd clearfix">
                                    <span>日</span>
                                    <span>一</span>
                                    <span>二</span>
                                    <span>三</span>
                                    <span>四</span>
                                    <span>五</span>
                                    <span>六</span>
                                </div>
                                <div class="calendar-day clearfix J_calendarDay">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="page-box">
                    <h1>
                        <a>消息提醒</a>
                        <a href="${website}message" class="more">查看更多</a>
                    </h1>
                    <div class="page-box-body">
                        <ul class="message-list">
                            <c:forEach items="${messageList}" var="message">
                                <li><a href="${website}${message.url}">${message.content}
                                    </a><span class="time">${message.reminderTime}</span></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="block">
                <div class="block-header">
                    <h3><a href="link/list" class="friend-link-header">友情链接</a></h3>
                </div>
                <div class="block-content friend-link">
                    <c:forEach var="links" items="${linkList}">
                        <a href="${links.url}"  target="_blank">${links.title}</a>
                        <span>|</span>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    KISSY.use('page/index/index',function(S){
        S.ready(function(){
            PW.page.Index({
                lectureListUrl:'${website}recruit/toList'
            });
        });
    });
</script>
</body>
</html>
