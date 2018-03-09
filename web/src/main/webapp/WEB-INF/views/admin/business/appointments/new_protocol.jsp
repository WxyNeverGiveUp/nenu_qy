<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--黎星宇 业务受理 协议业务 HTML-->
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>业务预约--申领新协议</title>
	<jsp:include page="../../../common/link.jsp" />
	<jsp:include page="../../../common/script.jsp"/>
	<link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/admin_page/business_handling/protocolList.css">
</head>
<body>
<jsp:include page="../../../common/header.jsp"/>
<jsp:include page="../../../common/Asidebar.jsp"/>
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
			<form  class="query_selects clearfix" method="post" action="/admin/protocol/new/export/1">
				<div class="each_query">
					<span class="query_text">学生类型</span>
					<select class="query_select" id="stuType" name="stuType">
						<option value="-1" selected="selected">请选择</option>
						<option value="1">系统导入学生</option>
						<option value="2">管理员新增学生</option>
					</select>
				</div>
				<div class="each_query">
					<span class="query_text">性&emsp;&emsp;别</span>
					<select class="query_select" id="sex" name="sex">
						<option value="-1" selected="selected">请选择</option>
						<option value="1">男</option>
						<option value="2">女</option>
					</select>
				</div>
				<div class="each_query">
					<span class="query_text">学&emsp;&emsp;院</span>
					<select class="query_select" id="college" name="college">
						<option value="-1" selected="selected">请选择</option>
						<c:forEach items="${collegeList}" var="college">
							<option value="${college.collegeId}">${college.college}</option>
						</c:forEach>
					</select>
				</div>
				<div class="each_query linkage-major none">
					<span class="query_text">专业名称</span>
					<select class="query_select" id="majorCode" name="majorCode">
						<c:forEach items="${userInfoDtoList}" var="userInfoDto">
							<option value="${userInfoDto.majorCode}">${userInfoDto.majorName}</option>
						</c:forEach>
					</select>
				</div>
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
				<%--<div class="each_query">--%>
					<%--<span class="query_text">辅导员审核状态</span>--%>
					<%--<select class="query_select" id="counsellorCheckResult" name="counsellorCheckResult">--%>
						<%--<option value="-1" selected="selected">请选择</option>--%>
						<%--<option value="1">未审核</option>--%>
						<%--<option value="2">审核通过</option>--%>
						<%--<option value="3">审核未通过待修改</option>--%>
						<%--<option value="4">审核未通过已修改</option>--%>
					<%--</select>--%>
				<%--</div>--%>
				<%--<div class="each_query">--%>
					<%--<span class="query_text">副书记审核状态</span>--%>
					<%--<select class="query_select" id="deputySecretaryCheckResult" name="deputySecretaryCheckResult">--%>
						<%--<option value="-1" selected="selected">请选择</option>--%>
						<%--<option value="1">未审核</option>--%>
						<%--<option value="2">审核通过</option>--%>
						<%--<option value="3">审核未通过待修改</option>--%>
						<%--<option value="4">审核未通过已修改</option>--%>
					<%--</select>--%>
				<%--</div>--%>
				<div class="each_query addr">
					<span class="query_text">生源所在地</span>
					<select class="query_select" id="originPlace" name="originPlace">
						<option value="-1" selected="selected">请选择</option>
						<c:forEach items="${provinceList}" var="province">
							<option value="${province.code}">${province.name}</option>
						</c:forEach>
					</select>
				</div>

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
					<span class="query_text">变更类型</span>
					<select class="query_select" id="protocolType" name="protocolType">
						<option value="-1">请选择</option>
						<option value="2">协议污损</option>
						<option value="3">协议丢失</option>
						<option value="4">协议解约领新协议</option>
					</select>
				</div>
				<div class="each_query">
					<span class="query_text">审核状态</span>
					<select class="query_select" id="checkProtocolResult" name="checkProtocolResult">
						<option value="-1">请选择</option>
						<option value="1">未审核</option>
						<option value="2">审核通过</option>
						<option value="3">审核未通过待修改</option>
						<option value="4">审核未通过已修改</option>
					</select>
				</div>
			    <div class="each_query">
					<span class="query_text">提交时间</span>
					<input class="J_date" type="text" name="beginTime" id="beginTime" />
					<input class="J_date" type="text" name="endTime" id="endTime"/>
				</div>
				<div class="each_query">
					<span class="query_text">关键字</span>
					<input type="text" value="学号或姓名" class="keyword_input" id="keyword" name="keyword"/>
				</div>


				<div class="inputs">
					<input type="button" value="查询" class="query btn" id="querybtn" />
					<input type="submit" value="导出信息" class="excel btn" id="exportbtn"/>
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
						<input type="checkbox"  name="filter_sex" value="性别" class="filterCheck">
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
						<input type="checkbox" checked="checked" name="filter_college" value="所在学院" class="filterCheck">
						<span class="filter_title">所在学院</span>
					</div>
					<div>
						<input type="checkbox" checked="checked" name="filter_major" value="专业" class="filterCheck">
						<span class="filter_title">专业</span>
					</div>
					<div>
						<input type="checkbox"  name="filter_stuLength" value="学制" class="filterCheck">
						<span class="filter_title">学制</span>
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
						<input type="checkbox" name="filter_normalStu" checked="checked" value="师范生类别" class="filterCheck">
						<span class="filter_title">师范生类别</span>
					</div>
					<div>
						<input type="checkbox" name="filter_difficulty" value="困难生类别" class="filterCheck">
						<span class="filter_title">困难生类别</span>
					</div>
					<div>
						<input type="checkbox" checked="checked" name="filter_reason" value="变更类型" class="filterCheck">
						<span class="filter_title">变更类型</span>
					</div>
					<div>
						<input type="checkbox" checked="checked" name="filter_stuTime" value="学生提交时间" class="filterCheck">
						<span class="filter_title">学生提交时间</span>
					</div>
					<div>
						<input type="checkbox" checked="checked" name="filter_schCheck" value="审核状态" class="filterCheck">
						<span class="filter_title">审核状态</span>
					</div>
					<div>
						<input type="checkbox" checked="checked" name="filter_checkTime" value="审核时间" class="filterCheck">
						<span class="filter_title">审核时间</span>
					</div>
				</div>
				<div class="filter_footer">
					<input type="checkbox" name="checkAll" class="checkAll" id="J_filterAll">
					<span>全选</span>
					<a href="javascript:;" class="view" id="filterIt">确定</a>
				</div>
			</div>
			<div class="table-box">
				<table>
					<thead>
					    <th>序号</th>
					    <th style="display:none;">数据库ID</th>
					    <th>姓名</th>
					    <th>学号</th>
						<th class="filter_sex">性别</th>
						<th class="filter_grade">所在年级</th>
						<th class="filter_college">所在学院</th>
						<th class="filter_major">专业</th>
						<th class="filter_stuLength">学制</th>
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
						<th class="filter_reason">变更类型</th>
						<th class="filter_stuTime">学生提交时间</th>
						<th class="filter_schCheck">审核状态</th>
						<th class="filter_checkTime">审核时间</th>
					    <th>操作</th>
						<%--<th class="fixed">是否选中</th>--%>
					    
					</thead>
					<tbody id="J_template">
						
					</tbody>
				</table>
				
			</div>
			
			
			<%--<div class="bulk-box">--%>
				<%--<div class="bulk">--%>
					<%--<div class="checkAllbox">--%>
						<%--<input type="checkbox" name="checkAll" class="checkAll checkToPass" id="J_passAll" >--%>
						<%--<span>全选</span>--%>
					<%--</div>--%>
					<%--<input type="button" name="check" class="btn checkAllbox pass" value="批量通过">--%>
				<%--</div>--%>

			<%--</div>--%>

			<div id="J_pagination">

			</div>
		</div>
	</div>
	<script id="query-protocolList" type="text/template">
	{@each data as protocolList}
		<tr>
		    <td class="stuorder">&{protocolList.index}</td>
		    <td style="display:none;" class="stuId">&{protocolList.id}</td>
			<td style="display:none;" class="protocolId">&{protocolList.protocolId}</td>
			<td>&{protocolList.name}</td>
			<td>&{protocolList.stuNumber}</td>
			<td class="filter_sex">&{protocolList.sex}</td>
			<td class="filter_grade">&{protocolList.grade}</td>
			<td class="filter_college">&{protocolList.college}</td>
			<td class="filter_major">&{protocolList.major}</td>
			<td class="filter_stuLength">&{protocolList.stuLength}</td>
			<td class="filter_candidateNumber">&{protocolList.candidateNumber}</td>
			<td class="filter_idNumber">&{protocolList.idNumber}</td>
			<td class="filter_nation">&{protocolList.nation}</td>
			<td class="filter_school">&{protocolList.school}</td>
			<td class="filter_trainingMode">&{protocolList.trainingMode}</td>
			<td class="filter_weipeiUnit">&{protocolList.weipeiUnit}</td>
			<td class="filter_originPlace">&{protocolList.originPlace}</td>
			<td class="filter_politicalStand">&{protocolList.politicalStand}</td>
			<td class="filter_normalStu">&{protocolList.normalStu}</td>
			<td class="filter_difficulty">&{protocolList.difficulty}</td>
			<td class="filter_reason">&{protocolList.protocolType}</td>
			<td class="filter_stuTime">&{protocolList.createTime}</td>
			<td class="filter_schCheck">&{protocolList.checkProtocolResult}</td>
			<td class="filter_checkTime">&{protocolList.checkProtocolTime}</td>
			<td>
				<a href="javascript:;" class="viewIt">查看</a>
				<!--
				<a href="javascript:;" class="viewCheck">审核</a>
				-->
			</td>
			<%--<td class="fixed">--%>
				<%--<input type="checkbox" name="checkToPass" class="checkToPass">--%>
			<%--</td>--%>
		</tr>
	{@/each}
	</script>
<script type="text/javascript">
    KISSY.use('page/admin_page/business_handling/protocolList', function(S) {
        var urls = PW.Env.url.admin_page.business_handling.protocolList;
        var url = urls.newProtocolList;//传出来的数据
        var $ = S.all;
        console.log(url);
        S.ready(function(){
            PW.page.admin_page.business_handling.protocolList({
                renderTo: '#J_pagination', //分页显示指向
                juicerRender: '#query-protocolList', //模板渲染
                dataRender: '#J_template', //模板的数据放的地方
                pageSize:15,//每页显示的记录数
                url: url,
				/*发送ajax,必须指定ajax的url */
                configUrl:function(url,page,me,prevdata){
					var url = url + '/' + page;
                    return url;
//                     return url + page;
                },
                afterDataLoad:function(that,data,page) {
                    S.log(S.all('.stuorder'));
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
                beforeDataLoad:function(that,data,page) {
//                    S.log(S.all('.stuorder'));
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