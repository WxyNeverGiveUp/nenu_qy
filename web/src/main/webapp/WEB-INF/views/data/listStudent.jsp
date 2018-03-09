<%--
  Created by IntelliJ IDEA.
  User: lishicao
  Date: 15/5/20
  Time: 下午6:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>东北师范大学网上签约管理系统 -- 就业管理 -- 学生列表</title>
    <jsp:include page="../common/link.jsp"/>
    <jsp:include page="../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/employment.css">
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<div class="continer">
    <jsp:include page="../common/sidebar_bak.jsp"/>
    <div class="content">
        <%@include file="../common/crumbs.jsp" %>
        <form class="block J_searchForm">
            <div class="block-header">
                <h3>学生查询</h3>
                <shiro:hasAnyRoles name="complex,work-comp,counsellor,admin">
                    <a href="${website}data/add" class="btn-right btn" target="_blank">学生添加</a>
                </shiro:hasAnyRoles>
            </div>
            <div class="block-content clearfix">
                <div class="control-area control-area-short">
                    <label>毕业届次</label>
                    <select class="textTheme" autocomplete="off" name="graduateClass">
                        <option value="">请选择</option>
                        <option value="2015">2015</option>
                        <option value="2016">2016</option>
                        <option value="2017">2017</option>
                    </select>
                </div>
                <div class="control-area control-area-short">
                    <label>姓名</label>
                    <input type="text" class="textTheme" maxlength="50" name="name"/>
                </div>
                <div class="control-area control-area-short">
                    <label>学号</label>
                    <input type="number" class="textTheme" maxlength="50" name="studentId"/>
                </div>

                <shiro:hasAnyRoles name="admin,branch,work-comp,complex,college-secretary">
                    <div class="control-area control-area-short">
                        <label>学历</label>
                        <select id = "J_degreeList" class="textTheme" autocomplete="off" name="qualification">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${quanlificationStudyList}">
                                <option value="${item}">${item}</option>
                            </c:forEach>
                        </select>
                    </div>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="admin,branch,work-comp,complex">
                    <div class="control-area control-area-short">
                        <label>学院</label>
                        <select id = "J_institutionList" class="textTheme" autocomplete="off" name="collegeId">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${collegeCodes}">
                                <option value="${item.collegeId}">${item.college}</option>
                            </c:forEach>
                        </select>
                    </div>
                </shiro:hasAnyRoles>

                <div class="control-area control-area-short">
                    <label>专业</label>
                    <select id="J_majorList" class="textTheme" autocomplete="off" name="majorId">
                        <option value="">请选择</option>
                        <c:forEach var="item" items="${majors}">
                            <option value="${item.majorId}">${item.majorName}</option>
                        </c:forEach>
                    </select>
                </div>
                <shiro:hasAnyRoles name="admin,branch,work-comp,complex">
                    <div class="control-area control-area-short">
                        <label>毕业去向 </label>
                        <select class="textTheme" autocomplete="off" name="whereaboutgo">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${whereAboutGoCodes}">
                                <option value="${item.whereAboutGo}">${item.whereAboutGo}</option>
                            </c:forEach>
                        </select>
                    </div>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="admin,branch,work-comp,complex">
                    <div class="control-area control-area-short">
                        <label>性别</label>
                        <select class="textTheme" autocomplete="off" name="sex">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${sexCodes}">
                                <option value="${item.sex}">${item.sex}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="control-area control-area-short">
                        <label>民族</label>
                        <select class="textTheme" autocomplete="off" name="nation">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${nationCodes}">
                                <option value="${item.nation}">${item.nation}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="control-area control-area-short">
                        <label>困难生类别</label>
                        <select class="textTheme" autocomplete="off" name="difficultyType">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${difficultyCodes}">
                                <option value="${item.difficultyMode}">${item.difficultyMode}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="control-area control-area-short">
                        <label>政治面貌</label>
                        <select class="textTheme" autocomplete="off" name="politicalStand">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${politicalCodes}">
                                <option value="${item.politicalStand}">${item.politicalStand}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="control-area control-area-short">
                        <label>师范生类别</label>
                        <select class="textTheme" autocomplete="off" name="normalStu">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${normalCodes}">
                                <option value="${item.normalStu}">${item.normalStu}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="control-area control-area-short">
                        <label>生源省份</label>
                        <select id = "J_proviceListEmp" class="textTheme" autocomplete="off" name="fromProvince">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${provinces}">
                                <option value="${item.provinceCode}">${item.provinceName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div  class="control-area control-area-short">
                        <label>生源城市</label>
                        <select id="J_cityListEmp" class="textTheme" autocomplete="off" name="fromPlace">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${placeCodes}">
                                <option value="${item.placeId}">${item.showName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="control-area control-area-short">
                        <label>就业地区类型</label>
                        <select class="textTheme" autocomplete="off" name="goPlaceType">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${placeClasses}">
                                <option value="${item.placeClassName}">${item.placeClassName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="control-area control-area-short">
                        <label>就业区域</label>
                        <select class="textTheme" autocomplete="off" name="goArea">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${areas}">
                                <option value="${item.areaName}">${item.areaName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="control-area control-area-short">
                        <label>就业省份</label>
                        <select id = "J_proviceListA" class="textTheme" autocomplete="off" name="goProvince">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${provinces}">
                                <option value="${item.provinceCode}">${item.provinceName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="control-area control-area-short">
                        <label>就业城市</label>
                        <select id="J_cityListA" class="textTheme" autocomplete="off" name="goPlace">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${placeCodes}">
                                <option value="${item.placeId}">${item.showName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="control-area control-area-short">
                        <label>单位性质</label>
                        <select class="textTheme" autocomplete="off" name="insMode">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${insModeCodes}">
                                <option value="${item.insMode}">${item.insMode}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="control-area control-area-short">
                        <label>单位行业 </label>
                        <select class="textTheme" autocomplete="off" name="insField">
                            <option value="">请选择</option>
                            <c:forEach var="item" items="${insFieldCodes}">
                                <option value="${item.insField}">${item.insField}</option>
                            </c:forEach>
                        </select>
                    </div>
                </shiro:hasAnyRoles>

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

                <div class="control-area control-area-short">
                    <label>辅导员审核状态</label>
                    <select class="textTheme" autocomplete="off" name="verifyStatus">
                        <option value="">请选择</option>
                        <option value="已审核">已审核</option>
                        <option value="待审核">待审核</option>
                    </select>
                </div>
    <shiro:hasAnyRoles name="work-comp,complex,admin">
                <div class="control-area control-area-short">
                    <label>就业中心审核状态</label>
                    <select class="textTheme" autocomplete="off" name="checkinStatus">
                        <option value="">请选择</option>
                        <option value="已审核">已审核</option>
                        <option value="待审核">待审核</option>
                    </select>
                </div>
    </shiro:hasAnyRoles>
                <div class="btn-right">
                    <button type="button" class="btn J_searchBtn">查询</button>
                </div>
            </div>
        </form>
        <div class="block">
            <div class="block-header">
                <h3>符合查询条件共&nbsp;&nbsp;<span class="J_searchCount"></span>&nbsp;&nbsp;人</h3>
                <a class="btn-right btn J_export" href="/data/exportStudentListExcel">导出结果</a>
            </div>
            <div class="block-content clearfix">
                <table>
                    <thead>
                    <tr>
                        <shiro:hasAnyRoles name="admin,branch,complex,work-comp,college-secretary,counsellor">
                            <th>姓 名</th>
                            <th>学 号</th>
                        </shiro:hasAnyRoles>
                        <shiro:hasAnyRoles name="admin,branch">
                            <th>学院</th>
                        </shiro:hasAnyRoles>
                        <shiro:hasAnyRoles name="admin,branch,college-secretary,counsellor">
                            <th>专业</th>
                        </shiro:hasAnyRoles>
                        <shiro:hasAnyRoles name="admin,branch,college-secretary">
                            <th>学历</th>
                            <th>毕业去向</th>
                        </shiro:hasAnyRoles>
                        <shiro:hasAnyRoles name="complex,work-comp,counsellor">
                            <th>辅导员审核状态</th>
                        </shiro:hasAnyRoles>
                        <shiro:hasAnyRoles name="complex,work-comp">
                            <th>就业中心审核状态</th>
                            <th>辅导员审核人</th>
                        </shiro:hasAnyRoles>
                        <shiro:hasAnyRoles name="complex,work-comp,counsellor">
                            <th>辅导员审核时间</th>
                        </shiro:hasAnyRoles>
                        <shiro:hasAnyRoles name="complex,work-comp">
                            <th>就业中心审核时间</th>
                        </shiro:hasAnyRoles>
                        <shiro:hasAnyRoles name="admin,branch,college-secretary">
                            <th>详细信息</th>
                        </shiro:hasAnyRoles>
                        <shiro:hasAnyRoles name="complex,work-comp, counsellor">
                            <th>操作</th>
                        </shiro:hasAnyRoles>
                    </tr>
                    </thead>
                    <script id="tpl" type="text/template">
                        {@each data as d}
                        <tr data-stu-id="&{d.id}">
                            <shiro:hasAnyRoles name="admin,branch,complex,work-comp,college-secretary,counsellor">

                                <td>&{d.name}</td>
                                <td>&{d.studentId}</td>
                            </shiro:hasAnyRoles>

                            <shiro:hasAnyRoles name="admin,branch">
                                <td>&{d.collegeName}</td>
                            </shiro:hasAnyRoles>
                            <shiro:hasAnyRoles name="admin,branch,college-secretary,counsellor">

                                <td>&{d.major}</td>
                            </shiro:hasAnyRoles>
                            <shiro:hasAnyRoles name="admin,branch,college-secretary">

                                <td>&{d.qualification}</td>
                                <td>&{d.whereaboutgo}</td>
                            </shiro:hasAnyRoles>
                            <shiro:hasAnyRoles name="complex,work-comp,counsellor">

                                <td>&{d.verifyStatus}</td>
                            </shiro:hasAnyRoles>
                            <shiro:hasAnyRoles name="complex,work-comp">

                                <td>&{d.checkinStatus}</td>
                                <td>&{d.verifyer}</td>
                            </shiro:hasAnyRoles>
                            <shiro:hasAnyRoles name="complex,work-comp,counsellor">
                                <td>&{d.verifyTime}</td>
                            </shiro:hasAnyRoles>
                            <shiro:hasAnyRoles name="complex,work-comp">

                                <td>&{d.checkinTime}</td>
                            </shiro:hasAnyRoles>

                            <shiro:hasAnyRoles name="admin,branch,college-secretary">

                                <td><a class="check" href="${website}data/detail/&{d.id}" title="查看" target="_blank"><i></i></a>
                                </td>
                            </shiro:hasAnyRoles>
                            <shiro:hasAnyRoles name="complex,work-comp, counsellor">

                                <td>
                                <shiro:hasAnyRoles name="complex,work-comp">
                                <a class="add" href="${website}data/update/&{d.id}/0" target="_blank" title="审核"><i></i></a>
                                </shiro:hasAnyRoles>
                                <shiro:hasAnyRoles name="counsellor">

                                    <a class="check" href="${website}data/update/&{d.id}/1" title="审核" target="_blank"><i></i></a>
                                </shiro:hasAnyRoles>

                                    <a class="del J_del" href="javascript:;" title="删除"><i></i></a>

                                </td>
                            </shiro:hasAnyRoles>

                        </tr>
                        {@/each}
                    </script>
                    <tbody id="J_template">

                    </tbody>
                </table>
                <div id="J_pagination"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    KISSY.use('page/employment/student,page/employment/add-student', function (S) {
        S.ready(function () {
            PW.page.addStudent();
            PW.page.student({
                renderTo: '#J_pagination',//分页显示指向
                juicerRender: '#tpl',//模版渲染指向
                dataRender: '#J_template',
                pageSize: 10,//每页显示的记录数
                url: '${website}data/ajax/listStudent',//必选，必须指定ajax的url
                configUrl: function (url, page, me, prevdata) {
                    var url = url + '/' + page;
                    return url;
                },
                type: 'get'
            });
        });
    });
</script>
</body>
</html>
