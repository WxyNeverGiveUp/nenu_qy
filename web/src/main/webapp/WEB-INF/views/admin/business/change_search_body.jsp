<!--黎星宇 业务受理 变更业务 HTML-->
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>业务受理--变更业务</title>
    <jsp:include page="../../common/link.jsp" />
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/admin_page/business_handling/protocolList.css">
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
    <div class="stu_query">
        <p class="query_name">查询条件</p>
        <div  class="query_selects clearfix">
            <div class="each_query">
                <span class="query_text">变更类型</span>
                <select class="query_select" id="changeType">
                    <option value="-1">请选择</option>
                    <option value="1">定向解约</option>
                    <option value="2">委培解约</option>
                    <option value="3">免师解约</option>
                    <option value="4">免师跨省</option>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">提交时间</span>
                <input class="J_date" type="text" name="enterDate" id="beginTime" />
                <input class="J_date" type="text" name="enterDate" id="endTime"/>
            </div>
            <div class="each_query">
                <span class="query_text">关键字</span>
                <input type="text" value="学号或姓名" class="keyWord_input" id="keyWord" />
            </div>
            <div class="each_query">
                <span class="query_text">审核状态</span>
                <select class="query_select" id="checkChangeResult">
                    <option value="-1">请选择</option>
                    <option value="1">未审核</option>
                    <option value="2">审核通过</option>
                    <option value="3">审核未通过待修改</option>
                    <option value="4">审核未通过已修改</option>
                </select>
            </div>
            <input type="button" value="查询" class="query btn" id="querybtn" />
        </div>
    </div>
    <div class="query_result">
        <table>
            <thead>
            <th>序号</th>
            <th style="display:none;">数据库ID</th>
            <th>姓名</th>
            <th>学号</th>
            <th>变更类型</th>
            <th>学生提交时间</th>
            <th>学校审核</th>
            <th>审核时间</th>
            <th>操作</th>
            </thead>
            <tbody id="J_template">

            </tbody>
        </table>
        <div id="J_pagination">
            <div id="onepage">
                <span id="J_firstPage" class="disabled">&lt;&lt;</span><span id="J_prevPage" class="disabled">上一页</span><span class="check">1</span><a id="J_nextPage" class="disabled">下一页</a><a id="J_lastPage" class="disabled">&gt;&gt;</a><span class="count">1/1</span>
                <!-- <a href="javascript:;">上一页</a>
                <a href="javascript:;" class="active">1</a>
                <a href="javascript:;">2</a>
                <a href="javascript:;">3</a>
                <a href="javascript:;">4</a>
                <a href="javascript:;">5</a>
                <a href="javascript:;">下一页</a>
                <span class="show_page">1/5</span> -->
            </div>
        </div>
    </div>
</div>
<script id="query-protocolList" type="text/template">
    {@each data as changeList,index}
    <tr>
        <td>&{++index}</td>
        <td style="display:none;" class="stuId">&{changeList.statusInfoId}</td>
        <td>&{changeList.name}</td>
        <td>&{changeList.studentNumber}</td>
        <td>&{changeList.changeType}</td>
        <td>&{changeList.createTime}</td>
        <td>&{changeList.checkChangeResult}</td>
        <td>&{changeList.lastModifiedTime}</td>
        <td>
            <a href="javascript:;" class="view">查看</a>
            <%--<a href="${website}admin/change/info/&{changeList.statusInfoId}" class="view">查看</a>--%>
        </td>
    </tr>
    {@/each}
</script>
<script type="text/javascript">
    KISSY.use('page/admin_page/business_handling/changeList', function(S) {
        var urls = PW.Env.url.admin_page.business_handling.changeList;
        var url = urls.showChangeList;//传出来的数据
        //console.log(url);
        S.ready(function(){
            PW.page.admin_page.business_handling.changeList({
                renderTo: '#J_pagination', //分页显示指向
                juicerRender: '#query-protocolList', //模板渲染
                dataRender: '#J_template', //模板的数据放的地方
                pageSize:15,//每页显示的记录数
                url: '${website}admin/change/ajax/listChange',
                /*发送ajax,必须指定ajax的url */
                configUrl:function(url,page,me,prevdata){
                    var url = url + '/' + page;
                    return url;
                    // return url + page;
                },
                afterDataLoad:function(that,data,page) {
                    S.log(data.dataCount);
                },
                type: 'get'
            });
        });
    });
</script>
</body>
</html>