<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="shrio" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: wlm
  Date: 2016/7/26
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>

<!--黎星宇 学籍 学生列表 HTML-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学籍——学生列表</title>
    <jsp:include page="../../common/link.jsp"/>
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/admin_page/students_management/dispatch/studentList.css">

</head>
<body>
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
    <div class="remind"></div>
    <c:if test="${!empty correctMsg}">
        <div class="correct-tip J_tip">${correctMsg}</div>
    </c:if>
    <c:if test="${!empty errMsg}">
        <div class="fault-tip J_tip">${errMsg}</div>
    </c:if>
    <div class="stu_query">
        <h2 class="query_name">查询条件</h2>
        <form class="query_selects clearfix" action="/admin/export/studentNew" onkeydown="if(event.keyCode==13)return false;">
            <!-- 以下选项框默认选中第一个 -->
            <div class="each_query">
                <span class="query_text">学生类型</span>
                <select class="query_select" id="stuType" name="stuType">
                    <option value="-1" selected="selected">请选择</option>
                    <option value="1">系统导入学生</option>
                    <option value="2">管理员新增学生</option>
                </select>
            </div>
            <shiro:hasAnyRoles name="admin, director, employ">
                <div class="each_query">
                    <span class="query_text">学&emsp;&emsp;院</span>
                    <select class="query_select" id="college" name="college">
                        <option value="-1" selected="selected">请选择</option>
                        <c:forEach items="${collegeList}" var="college">
                            <option value="${college.collegeId}">${college.college}</option>
                        </c:forEach>
                    </select>
                </div>
            </shiro:hasAnyRoles>

            <shiro:hasAnyRoles name="admin, director, employ">
            <div class="each_query linkage-major none">
                <span class="query_text">专业名称</span>
                <select class="query_select" id="majorCode" name="majorCode">
                    <option value="-1" selected="selected">请选择</option>
                    <c:forEach items="${userInfoDtoList}" var="userInfoDto">
                        <option value="${userInfoDto.majorCode}">${userInfoDto.majorName}</option>
                    </c:forEach>
                </select>
            </div>
            </shiro:hasAnyRoles>
            <%--zy  --%>
            <div class="each_query">
                <span class="query_text">学  历</span>
                <select class="query_select" id="qualification" name="qualification">
                    <option value="-1" selected="selected">请选择</option>
                    <c:forEach items="${qualificationList}" var="qualification">
                        <option value="${qualification.qualificationId}">${qualification.qualification}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">师范生类型</span>
                <select class="query_select" id="normalStu" name="normalStu">
                    <option value="-1" selected="selected">请选择</option>
                    <option value="1">普通师范生</option>
                    <option value="12">免费师范生</option>
                    <option value="2">非师范生</option>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">辅导员审核状态</span>
                <select class="query_select" id="counsellorCheckResult" name="counsellorCheckResult">
                    <option value="-1" selected="selected">请选择</option>
                    <option value="1">未审核</option>
                    <option value="2">审核通过</option>
                    <option value="3">审核未通过待修改</option>
                    <option value="4">审核未通过已修改</option>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">副书记审核状态</span>
                <select class="query_select" id="deputySecretaryCheckResult" name="deputySecretaryCheckResult">
                    <option value="-1" selected="selected">请选择</option>
                    <option value="1">未审核</option>
                    <option value="2">审核通过</option>
                    <option value="3">审核未通过待修改</option>
                    <option value="4">审核未通过已修改</option>
                </select>
            </div>
            <%--<div class="each_query">--%>
            <%--<span class="query_text">学校审核状态</span>--%>
            <%--<select class="query_select" id="schoolCheckResult">--%>
            <%--<option value="-1" selected="selected">请选择</option>--%>
            <%--<option value="1">未审核</option>--%>
            <%--<option value="2">审核通过</option>--%>
            <%--<option value="3">审核未通过待修改</option>--%>
            <%--<option value="4">审核未通过已修改</option>--%>
            <%--</select>--%>
            <%--</div>--%>
            <div class="each_query addr">
                <span class="query_text">生源所在地</span>
                <select class="query_select" id="province" name="province">
                    <option value="-1" selected="selected">请选择</option>
                    <c:forEach items="${provinceList}" var="province">
                        <option value="${province.code}">${province.name}</option>
                    </c:forEach>
                </select>
            </div>
            <%--<div class="each_query">--%>
            <%--<span class="query_text">培养方式</span>--%>
            <%--<select class="query_select" id="trainingMode">--%>
            <%--<option value="-1" selected="selected">请选择</option>--%>
            <%--<c:forEach items="${trainingModeList}" var="trainingMode">--%>
            <%--<option value="${trainingMode.trainingModeId}">${trainingMode.trainingMode}</option>--%>
            <%--</c:forEach>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--<div class="each_query">--%>
            <%--<span class="query_text">在读学历</span>--%>
            <%--<select class="query_select" id="qualificationNow">--%>
            <%--<option value="-1" selected="selected">请选择</option>--%>
            <%--<option value="1">本专科生</option>--%>
            <%--<option value="2">研究生</option>--%>
            <%--</select>--%>
            <%--</div>--%>

            <div class="each_query">
                <span class="query_text">性&emsp;&emsp;别</span>
                <select class="query_select" id="sex" name="sex">
                    <option value="-1" selected="selected">请选择</option>
                    <option value="1">男</option>
                    <option value="2">女</option>
                </select>
            </div>
            <%--<div class="each_query">--%>
                <%--<span class="query_text">政治面貌</span>--%>
                <%--<select class="query_select" id="politicalStand" name="politicalStand">--%>
                    <%--<option value="-1" selected="selected">请选择</option>--%>
                    <%--<c:forEach items="${politicalList}" var="politicalStand">--%>
                        <%--<option value="${politicalStand.politicalStandId}">${politicalStand.politicalStand}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</div>--%>
            <div class="each_query">
                <span class="query_text">学&emsp;&emsp;制</span>
                <select class="query_select" id="stuLength" name="stuLength">
                    <option value="-1" selected="selected">请选择</option>
                    <option value="2">2年</option>
                    <option value="3">3年</option>
                    <option value="4">4年</option>
                    <option value="5">5年</option>
                </select>
            </div>
            <shiro:hasAnyRoles name="counsellor,college-secretary">
                <div class="each_query">
                    <span class="query_text">专业名称</span>
                    <select class="query_select" id="majorCode" name="majorCode">
                        <option value="" selected="selected">请选择</option>
                        <c:forEach items="${userInfoDtoList}" var="userInfoDto">
                            <option value="${userInfoDto.majorCode}">${userInfoDto.majorName}</option>
                        </c:forEach>
                    </select>
                </div>
            </shiro:hasAnyRoles>
            <%--<div class="each_query">--%>
            <%--<span class="query_text">是否显示未提交学生</span>--%>
            <%--<select class="query_select" id="showUncommitted" >--%>
            <%--<option value="-1" selected="selected">否</option>--%>
            <%--<option value="1">是</option>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--<div class="each_query major-name origin">--%>
            <%--<span class="query_text major-label">专业名称</span>--%>
            <%--<input class="major-id-unit major_code" type="hidden">--%>
            <%--<input type="text" name="" class="major_input J_majorHolder" id="J_unitMajorHolder">--%>
            <%--<ul class="none majorFidle" id="J_majorFidle_unit"></ul>--%>
            <%--<p class="unitTip"></p>--%>
            <%--</div>--%>
            <div class="each_query">
                <span class="query_text">是否注册</span>
                <select class="query_select" id="isRegistered" name="isRegistered">
                    <option value = "-1" selected="selected">请选择</option>
                    <option value = "0">是</option>
                    <option value = "1">否</option>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">培养方式</span>
                <select class="query_select" id="trainingMode" name="trainingMode">
                    <option value="-1" selected="selected">请选择</option>
                    <option value="1">非定向</option>
                    <option value="2">定向</option>
                    <option value="3">自筹</option>
                    <option value="4">委培</option>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">关键字</span>
                <input type="text" placeholder="学生号、身份证号、姓名、考生号" value="" class="keyword_input" id="keyword" />
            </div>
            <div class="inputs">
                <input type="button" value="查询" class="query btn" id="querybtn" />
                <input type="submit" value="导出学籍信息" class="excel btn" id="exportbtn"/>
            </div>
        </form>
    </div>
    <div class="query_result">
        <h2 class="query_name">
            <input type="button" name="query_result" value="选择列" class="btn filter">
        </h2>
        <div class="query_filter" id="filterResult">
            <div class="filter_header">
                <h3 class="filter_name clearfix">选择显示列表项<span class="close">X</span></h3>
            </div>
            <div class="filter_main">
                <div>
                    <input type="checkbox" checked="checked" name="filter_sex" value="性别" class="filterCheck">
                    <span class="filter_title">性别</span>
                </div>
                <div>
                    <input type="checkbox" checked="checked" name="filter_stu_number" value="学号" class="filterCheck">
                    <span class="filter_title">学号</span>
                </div>
                <div>
                    <input type="checkbox" checked="checked" name="filter_grade" value="所在年级" class="filterCheck">
                    <span class="filter_title">所在年级</span>
                </div>
                <div>
                    <input type="checkbox" checked="checked" name="filter_major" value="专业" class="filterCheck">
                    <span class="filter_title">专业</span>
                </div>
                <div>
                    <input type="checkbox" checked="checked" name="filter_college" value="所在学院" class="filterCheck">
                    <span class="filter_title">所在学院</span>
                </div>
                <!-- modify 新增列表项 -->
                <div>
                    <input type="checkbox"  name="filter_stuLength" value="学制" class="filterCheck">
                    <span class="filter_title">学制</span>
                </div>
                <div>
                    <input type="checkbox"  name="filter_qualification" value="学历" class="filterCheck">
                    <span class="filter_title">学历</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_candidateNumber" value="考生号" class="filterCheck">
                    <span class="filter_title">考生号</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_idNumber" value="身份证号" class="filterCheck">
                    <span class="filter_title">身份证号</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_nation" value="民族" class="filterCheck">
                    <span class="filter_title">民族</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_school" value="院校名称" class="filterCheck">
                    <span class="filter_title">院校名称</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_trainingMode" value="培养方式" class="filterCheck">
                    <span class="filter_title">培养方式</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_weipeiUnit" value="定向委培单位" class="filterCheck">
                    <span class="filter_title">定向委培单位</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_originPlace" value="生源所在地" class="filterCheck">
                    <span class="filter_title">生源所在地</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_politicalStand" value="政治面貌" class="filterCheck">
                    <span class="filter_title">政治面貌</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_normalStu" value="师范生类别" class="filterCheck">
                    <span class="filter_title">师范生类别</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_difficulty" value="困难生类别" class="filterCheck">
                    <span class="filter_title">困难生类别</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_protocol" value="业务办理" class="filterCheck">
                    <span class="filter_title">业务办理</span>
                </div>
                <!-- /modify-->
                <div>
                    <input type="checkbox" checked="checked" name="filter_counsellor_check_result" value="学院审核" class="filterCheck">
                    <span class="filter_title">辅导员审核</span>
                </div>
                <div>
                    <input type="checkbox" checked="checked" name="filter_counsellor_check_time" value="学院审核时间" class="filterCheck">
                    <span class="filter_title">辅导员审核时间</span>
                </div>
                <div>
                    <input type="checkbox" checked="checked" name="filter_deputy_secretary_check_result" value="副书记审核" class="filterCheck">
                    <span class="filter_title">副书记审核</span>
                </div>
                <div>
                    <input type="checkbox" checked="checked" name="filter_deputy_secretary_check_time" value="副书记审核时间" class="filterCheck">
                    <span class="filter_title">副书记审核时间</span>
                </div>
            </div>

            <div class="filter_footer">
                <input type="checkbox" name="checkAll" class="checkAll" id="J_filterAll">
                <span>全选</span>
                <a href="javascript:;" class="view" id="filterIt">确定</a>
            </div>
        </div>
        <table>
            <thead>
            <th>序号</th>
            <th style="display:none;">数据库ID</th>
            <th>姓名</th>
            <th class="filter_sex">性别</th>
            <th class="filter_stu_number">学号</th>
            <th class="filter_grade">所在年级</th>
            <th class="filter_major">专业</th>
            <th class="filter_college">所在学院</th>
            <!-- modify 新增项 -->
            <th class="filter_stuLength">学制</th>
            <th class="filter_qualification">学历</th>
            <th class="filter_candidateNumber">考生号</th>
            <th class="filter_idNumber">身份证号</th>
            <th class="filter_nation">民族</th>
            <th class="filter_school">院校名称</th>
            <th class="filter_trainingMode">培养方式</th>
            <th class="filter_weipeiUnit">定向委培单位</th>
            <th class="filter_originPlace">生源所在地</th>
            <th class="filter_politicalStand">政治面貌</th>
            <th class="filter_normalStu">师范生类别</th>
            <th class="filter_difficulty">困难生类别</th>
            <th class="filter_protocol">业务办理</th>
            <!-- /modify -->
            <th class="filter_counsellor_check_result">辅导员审核</th>
            <th class="filter_counsellor_check_time">辅导员审核时间</th>
            <th class="filter_deputy_secretary_check_result">副书记审核</th>
            <th class="filter_deputy_secretary_check_time">副书记审核时间</th>
            <!-- <th>学校审核</th> -->
            <!-- <th>学校审核时间</th> -->
            <th>操作</th>
            <shiro:hasAnyRoles name="admin,college-secretary,employ,counsellor">
                <th>是否选中</th>
            </shiro:hasAnyRoles>
            </thead>
            <tbody id="J_template">

            </tbody>
        </table>
        <%--<shiro:hasAnyRoles name="college-secretary">--%>
        <div>
            <shiro:hasAnyRoles name="admin,college-secretary,employ,counsellor">
                <div class="checkAllbox">
                    <input type="checkbox" name="checkAll" class="checkAll" id="J_passAll" >
                    <span>全选</span>
                </div>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="admin,employ">
                <input type="button" name="check" class="btn repulse" value="批量打回">
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="college-secretary">
                <input type="button" name="check" class="btn pass" value="批量通过">
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="admin">
                <input type="button" name="check" class="btn deleteAll" value="批量删除"/>
            </shiro:hasAnyRoles>
        </div>
        <%--</shiro:hasAnyRoles>--%>
        <div id="J_pagination">
            <!-- 分页区域 -->
        </div>
    </div>
</div>
<script id="query-stuInfo" type="text/template">
    {@each data as sturollInfo}
    <tr>
        <td class="stuorder">&{sturollInfo.index}</td>
        <td style="display:none;" class="stuId">&{sturollInfo.id}</td>
        <td>&{sturollInfo.name}</td>
        <td class="filter_sex">&{sturollInfo.sex}</td>
        <td class="filter_stu_number">&{sturollInfo.stuNumber}</td>
        <td class="filter_grade">&{sturollInfo.grade}</td>
        <td class="filter_major which_major">&{sturollInfo.major}</td>
        <%--<td style="display:none;" class="filter_major other_major">&{sturollInfo.otherMajor}</td>--%>
        <td class="filter_college">&{sturollInfo.college}</td>

        <td class="filter_stuLength">&{sturollInfo.stuLength}</td>
        <td class="filter_qualification">&{sturollInfo.qualification}</td>
        <td class="filter_candidateNumber">&{sturollInfo.candidateNumber}</td>
        <td class="filter_idNumber">&{sturollInfo.idNumber}</td>
        <td class="filter_nation">&{sturollInfo.nation}</td>
        <td class="filter_school">&{sturollInfo.school}</td>
        <td class="filter_trainingMode">&{sturollInfo.trainingMode}</td>
        <td class="filter_weipeiUnit">&{sturollInfo.weipeiUnit}</td>
        <td class="filter_originPlace">&{sturollInfo.originPlace}</td>
        <td class="filter_politicalStand">&{sturollInfo.politicalStand}</td>
        <td class="filter_normalStu">&{sturollInfo.normalStu}</td>
        <td class="filter_difficulty">&{sturollInfo.difficulty}</td>
        <td class="filter_protocol">&{sturollInfo.protocolType}</td>

        <td class="filter_counsellor_check_result">&{sturollInfo.counsellorCheckResult}</td>
        <td class="filter_counsellor_check_time">&{sturollInfo.counsellorCheckTime}</td>
        <td class="filter_deputy_secretary_check_result">&{sturollInfo.deputySecretaryCheckResult}</td>
        <td class="filter_deputy_secretary_check_time">&{sturollInfo.deputySecretaryCheckTime}</td>
        <%--<td>&{sturollInfo.schoolCheckResult }</td>--%>
        <%--<td>&{sturollInfo.schoolCheckTime}</td>--%>
        <td>
            <a href="javascript:;" class="view">查看</a>
            <shiro:hasAnyRoles name="admin">
                <a href="${website}admin/status/info/update/&{sturollInfo.id}">修改</a>
                <a href="javascript:;" class="delete">删除</a>
            </shiro:hasAnyRoles>

        </td>
        <shiro:hasAnyRoles name="college-secretary,admin,employ,counsellor">
            <td>
                <input type="checkbox" name="id" class="checkToPass" value="&{sturollInfo.id}">
            </td>
        </shiro:hasAnyRoles>
    </tr>
    {@/each}
</script>
<script type="text/javascript">
    KISSY.use('page/admin_page/students_management/school_roll/studentList', function (S) {
        var urls = PW.Env.url.admin_page.students_management.school_roll.studentList;
        var url = urls.showStu;//传出来的数据
        var $ = S.all;
        console.log(url);
        S.ready(function () {
            PW.page.admin_page.students_management.school_roll.studentList({
                renderTo: '#J_pagination', //分页显示指向
                juicerRender: '#query-stuInfo', //模板渲染
                dataRender: '#J_template', //模板的数据放的地方
                pageSize: 15,//每页显示的记录数
                url: url,
                /*发送ajax,必须指定ajax的url */
                configUrl: function (url, page, me, prevdata) {
                    var url = url + '/' + page;//套页时使用
                    return url;
                    // return url + page;
                },
                afterDataLoad: function (that, data, page) {
                    S.log(data.dataCount);
                    S.all('.filterCheck').each(function(item) {
                        if ($(item).attr('checked') == "checked") {
                            var checkName = $(item).attr('name');
                            $('.' + checkName).show();
                        }
                        else if (!($(item).attr('checked') == "checked")) {
                            var notCheckName = $(item).attr('name');
                            $('.' + notCheckName).hide();
                        }
                    });
                },
                type: 'get'
            });
            console.log("ajaxing");
        });
    });
    function exportStu()
    {
        window.location.href = "/admin/export/student";
    }
</script>
</body>
</html>