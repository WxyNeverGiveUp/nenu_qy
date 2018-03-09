<%--
  Created by IntelliJ IDEA.
  User: Joiner-Axin
  Date: 2017/7/19
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>申请新协议</title>
    <jsp:include page="../../common/link.jsp" />
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/student_page/business_handling/applyList.css">
</head>
<body>
<jsp:include page="../../common/header.jsp" />
<!-- 侧栏start -->
<jsp:include page="../../common/Ssidebar.jsp"/>
<!-- 侧栏end -->
<script type="text/javascript">
    KISSY.use('module/sidebar',function(S){
        S.ready(function(){
            PW.module.Sidebar();
        });
    });
</script>

<div class="content">
    <div class="alertWrapper">
        <c:if test="${!empty correctMsg}">
            <div class="correct-tip J_tip">${correctMsg}</div>
        </c:if>
        <c:if test="${!empty errMsg}">
            <div class="fault-tip J_tip">${errMsg}</div>
        </c:if>
    </div>
    <p class="applylist_title">协议列表</p>
    <p class="applylist_new">
        <span>+</span>
        <span>
            <a href="${website}student/business/to/newBusiness/${type}" class="applylist_a">添加新申请</a>
        </span>
    </p>
    <div class="query_result">
        <table>
            <thead>

            <th>序号</th>
            <th style="display:none;">数据库ID</th>
            <th>变更类型</th>
            <c:if test="${freeTeacherProvince == true}">
            <th>跨入省份</th>
            </c:if>
            <th>学生提交时间</th>
            <th>学校审核</th>
            <th>审核理由</th>
            <th>审核时间</th>
            <th>操作</th>
            </thead>
            <tbody>
            <C:forEach items="${protocolStuDtoList}" var="protocol" varStatus="status">

            <tr>
                    <td class="stuorder">${status.count}</td>
                    <td style="display:none;" class="stuId">${protocol.statusInfoId}</td>
                    <td>${protocol.protocolType}</td>
                    <c:if test="${freeTeacherProvince == true}">
                        <c:if test="${protocol.freeTeacherProvince == null}"><td>无</td></c:if>
                        <c:if test="${protocol.freeTeacherProvince != null}"><td>${protocol.freeTeacherProvince}</td></c:if>
                    </c:if>
                    <td>${protocol.createTime}</td>
                    <td>${protocol.checkProtocolResult}</td>
                    <td>${protocol.checkProtocolReason}</td>
                    <td>${protocol.checkProtocolTime}</td>
                    <td>
                        <a href="${website}student/business/to/businessDetail/${type}/${protocol.id}" class="view">查看</a>
                        <c:if test="${protocol.checkProtocolResult == '审核未通过待修改'}"><a href="${website}student/business/to/updateBusiness/${type}/${protocol.id}" class="view">修改</a></c:if>
                    </td>
                </tr>
            </C:forEach>
            </tbody>
        </table>

    </div>
</div>

    <script type="text/javascript">
        window.onload = function(){
            function getElementsClass(classnames){
                var classobj= new Array();//定义数组
                var classint=0;//定义数组的下标
                var tags=document.getElementsByTagName("*");//获取HTML的所有标签

                for(var i in tags){//对标签进行遍历

                    if(tags[i].nodeType==1){//判断节点类型

                        if(tags[i].getAttribute("class") == classnames)//判断和需要CLASS名字相同的，并组成一个数组
                        {
                            classobj[classint]=tags[i];
                            classint++;
                        }

                    }

                }
                return classobj;//返回组成的数组
            }

            var stuOrder = getElementsClass("stuorder");
            for (var i = 0; i < stuOrder.length; i++) {
                var j = i+1;
                stuOrder[i].innerHTML = j;
            };
        }
    </script>
    </body>
    </html>