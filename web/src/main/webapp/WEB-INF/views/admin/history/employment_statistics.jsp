<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Jarvenman
  Date: 2017/8/11
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--吴晓阳 查看历史就业数据 -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>就业率实时统计</title>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/base/reset.css">
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/common/common.css">
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/admin_page/students_management/dispatch/studentList.css">
</head>
<body>
<jsp:include page="../../common/header.jsp"/>
<jsp:include page="../../common/Asidebar.jsp"/>
<script type="text/javascript">
    KISSY.use('module/sidebar',function(S){
        S.ready(function(){
            PW.module.Sidebar();
        });
    });
</script>
<div class="content">
    <div class="remind"></div>
    <div class="stu_query">
        <h2 class="query_name">查询条件</h2>
        <form class="query_selects clearfix" action="/admin/export/statisticsExport" method="get">
            <!-- 以下选项框默认选中第一个 -->

            <%--<input type="hidden" value="${code}" class="code"/>--%>

            <div class="each_query">
                <span class="query_text">就业率类别</span>
                <select class="query_select" id="year" name="statisticsType">
                    <option value="0" selected="selected">总体就业率</option>
                    <option value="1">本科生就业率</option>
                    <option value="2">硕士研究生就业率</option>
                    <option value="6">博士研究生就业率</option>
                    <option value="7">研究生就业率</option>
                    <option value="3">普通师范生就业率(本科生)</option>
                    <option value="4">免费师范生就业率(本科生)</option>
                    <option value="5">非师范生就业率(本科生)</option>
                </select>
            </div>

            <div class="inputs">
                <input type="button" value="查询" class="query btn" id="querybtn" name="querybtn" />
                <input type="submit" value="导出Excel" class="excel btn" id="exportbtn" name="querybtn" />
            </div>
        </form>
    </div>
    <div class="query_result">
        <div class="table-box">
            <table>
                <thead>
                <tr>
                    <th rowspan="2">序号</th>
                    <th style="display:none;">数据库ID</th>
                    <th rowspan="2">所在院系</th>
                    <th colspan="11">已就业</th>
                    <th rowspan="2">未就业</th>
                    <shiro:hasAnyRoles name="director, employ, counsellor, college-secretary">
                    <th colspan="4">就业数据</th>
                    </shiro:hasAnyRoles>
                    <shiro:hasAnyRoles name="admin">
                    <c:choose>
                        <c:when test="${code eq 0}">
                            <th colspan="7">就业数据</th>
                        </c:when>
                        <c:otherwise>
                            <th colspan="4">就业数据</th>
                        </c:otherwise>
                    </c:choose>
                    </shiro:hasAnyRoles>
                </tr>
                <tr>
                    <th>签就业协议形式就业</th>
                    <th>签劳动合同形式就业</th>
                    <th>其他录用形式就业</th>
                    <th>升学</th>
                    <th>出国(境)</th>
                    <th>科研助理</th>
                    <th>应征义务兵</th>
                    <th>国家基层项目</th>
                    <th>地方基层项目</th>
                    <th>自由职业</th>
                    <th>自主创业</th>
                    <th>就业人数</th>
                    <th>总计</th>
                    <th>就业率(不含灵活)</th>
                    <th>就业率</th>
                    <shiro:hasAnyRoles name="admin">
                        <c:if test="${code eq 0}">
                        <th>就业率(包括免师)</th>
                        <th>就业率(包括委培)</th>
                        <th>就业率(包括免师和委培)</th>
                        </c:if>
                    </shiro:hasAnyRoles>
                </tr>
                </thead>
                <tbody id="J_template">
                <tr>
                    <td rowspan="2"></td>
                    <td style="display:none;">数据库ID</td>
                    <td rowspan="2">总计</td>
                    <td>${allInfo.countColumn1}</td>
                    <td>${allInfo.countColumn2}</td>
                    <td>${allInfo.countColumn3}</td>
                    <td>${allInfo.countColumn4}</td>
                    <td>${allInfo.countColumn5}</td>
                    <td>${allInfo.countColumn6}</td>
                    <td>${allInfo.countColumn7}</td>
                    <td>${allInfo.countColumn8}</td>
                    <td>${allInfo.countColumn9}</td>
                    <td>${allInfo.countColumn10}</td>
                    <td>${allInfo.countColumn11}</td>
                    <td>${allInfo.countColumn12}</td>
                    <td rowspan="2">${allInfo.countEmployment}</td>
                    <td rowspan="2">${allInfo.countAll}</td>
                    <td rowspan="2">${allInfo.statisticsEmploymentNoClever}</td>
                    <td rowspan="2">${allInfo.statisticsEmployment}</td>
                    <shiro:hasAnyRoles name="admin">
                        <c:if test="${code eq 0}">
                        <td rowspan="2">${allInfo.statisticsWithNormal}</td>
                        <td rowspan="2">${allInfo.statisticsWithWeipei}</td>
                        <td rowspan="2">${allInfo.statisticsWithNormalAndWeipei}</td>
                        </c:if>
                    </shiro:hasAnyRoles>
                </tr>
                <tr>
                    <td>${allInfo.statisticsColumn1}</td>
                    <td>${allInfo.statisticsColumn2}</td>
                    <td>${allInfo.statisticsColumn3}</td>
                    <td>${allInfo.statisticsColumn4}</td>
                    <td>${allInfo.statisticsColumn5}</td>
                    <td>${allInfo.statisticsColumn6}</td>
                    <td>${allInfo.statisticsColumn7}</td>
                    <td>${allInfo.statisticsColumn8}</td>
                    <td>${allInfo.statisticsColumn9}</td>
                    <td>${allInfo.statisticsColumn10}</td>
                    <td>${allInfo.statisticsColumn11}</td>
                    <td>${allInfo.statisticsColumn12}</td>
                </tr>
                <!-- 两个是一个 -->

                <c:forEach items="${employmentStatisticsInfo}" var="employmentStatisticsInfoDto">
                    <tr>
                        <td rowspan="2">${employmentStatisticsInfoDto.id}</td>
                        <td style="display:none;">数据库ID</td>
                        <td rowspan="2">${employmentStatisticsInfoDto.college}</td>
                        <td>${employmentStatisticsInfoDto.countColumn1}</td>
                        <td>${employmentStatisticsInfoDto.countColumn2}</td>
                        <td>${employmentStatisticsInfoDto.countColumn3}</td>
                        <td>${employmentStatisticsInfoDto.countColumn4}</td>
                        <td>${employmentStatisticsInfoDto.countColumn5}</td>
                        <td>${employmentStatisticsInfoDto.countColumn6}</td>
                        <td>${employmentStatisticsInfoDto.countColumn7}</td>
                        <td>${employmentStatisticsInfoDto.countColumn8}</td>
                        <td>${employmentStatisticsInfoDto.countColumn9}</td>
                        <td>${employmentStatisticsInfoDto.countColumn10}</td>
                        <td>${employmentStatisticsInfoDto.countColumn11}</td>
                        <td>${employmentStatisticsInfoDto.countColumn12}</td>
                        <td rowspan="2">${employmentStatisticsInfoDto.countEmployment}</td>
                        <td rowspan="2">${employmentStatisticsInfoDto.countAll}</td>
                        <td rowspan="2">${employmentStatisticsInfoDto.statisticsEmploymentNoClever}</td>
                        <td rowspan="2">${employmentStatisticsInfoDto.statisticsEmployment}</td>
                        <shiro:hasAnyRoles name="admin">
                            <c:if test="${code eq 0}">
                            <td rowspan="2">${employmentStatisticsInfoDto.statisticsWithNormal}</td>
                            <td rowspan="2">${employmentStatisticsInfoDto.statisticsWithWeipei}</td>
                            <td rowspan="2">${employmentStatisticsInfoDto.statisticsWithNormalAndWeipei}</td>
                            </c:if>
                        </shiro:hasAnyRoles>
                    </tr>
                    <tr>
                        <td>${employmentStatisticsInfoDto.statisticsColumn1}</td>
                        <td>${employmentStatisticsInfoDto.statisticsColumn2}</td>
                        <td>${employmentStatisticsInfoDto.statisticsColumn3}</td>
                        <td>${employmentStatisticsInfoDto.statisticsColumn4}</td>
                        <td>${employmentStatisticsInfoDto.statisticsColumn5}</td>
                        <td>${employmentStatisticsInfoDto.statisticsColumn6}</td>
                        <td>${employmentStatisticsInfoDto.statisticsColumn7}</td>
                        <td>${employmentStatisticsInfoDto.statisticsColumn8}</td>
                        <td>${employmentStatisticsInfoDto.statisticsColumn9}</td>
                        <td>${employmentStatisticsInfoDto.statisticsColumn10}</td>
                        <td>${employmentStatisticsInfoDto.statisticsColumn11}</td>
                        <td>${employmentStatisticsInfoDto.statisticsColumn12}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="../../../../resources/resources/upload/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
        $('#querybtn').click(function(){
            var year=$("#year").find("option:selected").val();
            $.ajax({
                dataType:"html",
                url:"/admin/statistics/statistics_type",
                type:"post",
                data:{
                    statisticsType:year
                },
                beforeSend:function (hxr) {
                    $('#J_template').html('');
                    $('.table-box').append('<p style="text-align: center;color: #cc0000;">正在查询...</p>');
                },
                success:function (html) {
                        $("html").html(html);
                        $('#year').val(year);
                },
                error:function(){
                    console.log("异常，获取JSON数据异常");
                }
            })
        })
</script>

</body>
</html>

