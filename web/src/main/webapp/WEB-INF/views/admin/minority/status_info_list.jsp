<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--黎星宇 少数民族 学籍学生列表 HTML-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>少数民族专区—学籍信息列表</title>
    <jsp:include page="../../common/link.jsp"/>
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
        <form class="query_selects clearfix">
            <!-- 以下选项框默认选中第一个 -->
            <div class="each_query">
                <span class="query_text">民族</span>
                <select class="query_select" id="nation">
                    <option value="-1" selected="selected">请选择</option>
                    <c:forEach items="${nationList}" var="nation">
                        <option value="${nation.nationId}">${nation.nation}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">生源所在地</span>
                <select class="query_select" id="originPlace">
                    <option value="-1" selected="selected">请选择</option>
                    <c:forEach items="${provinceList}" var="province">
                        <option value="${province.code}">${province.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">学院</span>
                <select class="query_select" id="college">
                    <option value="-1" selected="selected">请选择</option>
                    <c:forEach items="${collegeList}" var="college">
                        <option value="${college.collegeId}">${college.college}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">在读学历</span>
                <select class="query_select" id="qualificationNow">
                    <option value="-1" selected="selected">请选择</option>
                    <option value="1">本专科生</option>
                    <option value="2">研究生</option>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">是否显示未提交学生</span>
                <select class="query_select" id="showUncommitted">
                    <option value="-1" selected="selected">否</option>
                    <option value="1">是</option>
                </select>
            </div>
            <div class="inputs">
                <input type="button" value="查询" class="query btn" id="querybtn" /><input type="button" value="导出excel" class="excel btn" />
            </div>
        </form>
    </div>
    <div class="query_result">
        <table>
            <thead>
            <th>序号</th>
            <th style="display:none;">数据库ID</th>
            <th>姓名</th>
            <th>学号</th>
            <th>所在年级</th>
            <th>专业</th>
            <th>所在学院</th>
            <th>辅导员审核</th>
            <th>辅导员审核时间</th>
            <th>副书记审核</th>
            <th>副书记审核时间</th>
            <th>学校审核</th>
            <th>学校审核时间</th>
            <th>操作</th>
            </thead>
            <tbody id="J_template">

            </tbody>
        </table>
        <div id="J_pagination">
            <!-- 分页区域 -->
        </div>
    </div>
</div>
<script id="query-stuInfo" type="text/template">
    {@each data as minorityInfo,index}
    <tr>
        <td class="stuorder">&{++index}</td>
        <td style="display:none;" class="stuId">&{minorityInfo.id}</td>
        <td>&{minorityInfo.name}</td>
        <td>&{minorityInfo.stuNumber}</td>
        <td>&{minorityInfo.grade}</td>
        <td>&{minorityInfo.major}</td>
        <td>&{minorityInfo.college}</td>
        <td>&{minorityInfo.counsellorCheckResult}</td>
        <td>&{minorityInfo.counsellorCheckTime}</td>
        <td>&{minorityInfo.deputySecretaryCheckResult}</td>
        <td>&{minorityInfo.deputySecretaryCheckTime}</td>
        <td>&{minorityInfo.schoolCheckResult}</td>
        <td>&{minorityInfo.schoolCheckTime}</td>
        <td>
            <a href="javascript:;" class="view">查看</a>
            <a href="${website}admin/status/info/update/&{minorityInfo.id}">修改</a>
        </td>
    </tr>
    {@/each}
</script>
<script type="text/javascript">
    KISSY.use('page/admin_page/students_management/minority/schoolList', function(S) {
        var urls = PW.Env.url.admin_page.students_management.minority.schoolList;
        var url = urls.showStu;//传出来的数据
        console.log(url);
        S.ready(function(){
            PW.page.admin_page.students_management.minority.schoolList({
                renderTo: '#J_pagination', //分页显示指向
                juicerRender: '#query-stuInfo', //模板渲染
                dataRender: '#J_template', //模板的数据放的地方
                pageSize:15,//每页显示的记录数
                url: url,
                /*发送ajax,必须指定ajax的url */
                configUrl:function(url,page,me,prevdata){
                    var url = url + '/' + page;//套页时使用
                    return url;
                    // return url + page;
                },
                afterDataLoad:function(that,data,page) {
                    S.log(data.dataCount);
                },
                type: 'get'
            });
            //console.log("ajaxing");
        });
    });
</script>
</body>
</html>