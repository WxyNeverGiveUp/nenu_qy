<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--黎星宇 签约 学生列表 HTML-->
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>签约——学生列表</title>
	<jsp:include page="../../common/script.jsp"/>
	<jsp:include page="../../common/link.jsp"/>
	<link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/admin_page/students_management/dispatch/studentList.css">
	<script type="text/javascript" src="${staticWebsite}js/page/admin_page/students_management/dispatch/jquery-1.12.2.min.js"></script>
</head>
<body>
<script type="text/javascript">
	KISSY.use('module/sidebar',function(S){
		S.ready(function(){
			PW.module.Sidebar();
		});
	});
</script>
<jsp:include page="../../common/header.jsp"/>
<jsp:include page="../../common/Asidebar.jsp"/>
	<div class="content">
		<div class="remind"></div>
		<c:if test="${!empty errMsg}">
			<div class="fault-tip J_tip">${errMsg}</div>
		</c:if>
		<div class="stu_query">
			<p class="query_name">查询条件</p>
			<form class="query_selects clearfix" action="/admin/export/all" onkeydown="if(event.keyCode==13) return false;">
			<!-- 以下选项框默认选中第一个 -->
			    <div class="each_query">
					<span class="query_text">学生类型</span>
					<select class="query_select" id="stuType" name="stuType">
						<option value="-1" selected="selected">请选择</option>
						<option value="1" >系统导入学生</option>
						<option value="2">管理员新增学生</option>
					</select>
				</div>
				<shiro:hasAnyRoles name="admin,derector,employ">
					<div class="each_query">
						<span class="query_text">学&emsp;院</span>
						<select class="query_select" id="college" name="college">
							<option value="-1" selected="selected">请选择</option>
							<c:forEach items="${dm}" var="d">
								<option value="${d.o1}">${d.o2}</option>
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

				<div class="each_query">
					<span class="query_text">学历</span>
					<select class="query_select" id="qualificationId" name="qualificationId">
						<option value="-1" selected="selected">请选择</option>
						<option value="01">博士生毕业</option>
						<option value="03">博士生结业</option>
						<option value="11">硕士生毕业</option>
						<option value="13">硕士生结业</option>
						<option value="25">二学位毕业</option>
						<option value="26">二学位结业</option>
						<option value="31">本科生毕业</option>
						<option value="33">本科生结业</option>
						<option value="41">专科生毕业</option>
						<option value="43">专科生结业</option>
					</select>
				</div>
				<div class="each_query">
					<span class="query_text">师范生类型</span>
					<select class="query_select" id="normalStuId" name="normalStuId">
						<option value="-1" selected="selected">请选择</option>
						<option value="1">普通师范生</option>
						<option value="12">免费师范生</option>
						<option value="2">非师范生</option>
					</select>
				</div>
				<div class="each_query">
					<span class="query_text">副书记审核状态</span>
					<select class="query_select" id="deputySecretaryCheckResult" name="counsellorCheckResult">
						<option value="-1" selected="selected">请选择</option>
						<option value="1">未审核</option>
						<option value="2">审核通过</option>
						<option value="3">审核未通过待修改</option>
						<option value="4">审核未通过已修改</option>
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
				<%--<div class="each_query">--%>
					<%--<span class="query_text">学校审核状态</span>--%>
					<%--<select class="query_select" id="schoolCheckResult" name="schoolCheckResult">--%>
						<%--<option value="-1" selected="selected">请选择</option>--%>
						<%--<option value="1">未审核</option>--%>
						<%--<option value="2">审核通过</option>--%>
						<%--<option value="3">审核未通过待修改</option>--%>
						<%--<option value="4">审核未通过已修改</option>--%>
					<%--</select>--%>
				<%--</div>--%>
				<%--<div class="each_query addr">--%>
					<%--<span class="query_text">生源所在地</span>--%>
					<%--<select class="query_select" id="provinceId" name="provinceId">--%>
						<%--<option value="-1" selected="selected">请选择</option>--%>
						<%--<c:forEach items="${provinces}" var="p">--%>
							<%--<option value="${p.code}" >${p.name}</option>--%>
						<%--</c:forEach>--%>
					<%--</select>--%>
				<%--</div>--%>
				<%--<div class="each_query">--%>
					<%--<span class="query_text">培养方式</span>--%>
					<%--<select class="query_select" id="trainingModeCode" name="trainingModeCode">--%>
						<%--<option value="-1" selected="selected">请选择</option>--%>
						<%--<option value="1">非定向</option>--%>
						<%--<option value="2">定向</option>--%>
						<%--<option value="3">在职</option>--%>
						<%--<option value="4">委培</option>--%>
						<%--<option value="5">自筹</option>--%>
					<%--</select>--%>
				<%--</div>--%>
				<div class="each_query">
					<span class="query_text">毕业去向</span>
					<select class="query_select" id="whereaboutgoId" name="whereaboutgoId">
						<option value="-1">请选择</option>
						<c:forEach var="w" items="${wc}">
							<c:choose>
								<c:when test="${dh.whereaboutgoId==w.whereAboutGoId}">
									<option value="${w.whereAboutGoId}" selected>${w.whereAboutGo}</option>
								</c:when>
								<c:otherwise>
									<option value="${w.whereAboutGoId}" >${w.whereAboutGo}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
				<%--<div class="each_query">--%>
					<%--<span class="query_text">是否显示未提交学生</span>--%>
					<%--<select class="query_select" id="showUncommitted" name="showUncommitted">--%>
						<%--<option value="-1" selected="selected">否</option>--%>
						<%--<option value="1">是</option>--%>
					<%--</select>--%>
				<%--</div>--%>
				<div class="each_query">
					<span class="query_text">性别</span>
					<select class="query_select" id="sex" name="sex">
						<option value="-1" selected="selected">请选择</option>
						<option value="1">男</option>
						<option value="2">女</option>
					</select>
				</div>
				<div class="each_query">
					<span class="query_text">政治面貌</span>
					<select class="query_select" id="politicalStand" name="politicalStand">
						<option value="-1" selected="selected">请选择</option>
						<option value="1">中共党员</option>
						<option value="2">中共预备党员</option>
						<option value="3">共青团员</option>
						<option value="4">民革会员</option>
						<option value="5">民盟盟员</option>
						<option value="6">民建会员</option>
						<option value="7">民进会员</option>
						<option value="8">农工党党员</option>
						<option value="9">致公党党员</option>
						<option value="10">九三学社社员</option>
						<option value="11">台盟盟员</option>
						<option value="12">无党派民主人士</option>
						<option value="13">群众</option>
					</select>
				</div>
				<div class="each_query">
					<span class="query_text">报道证签发类别</span>
					<select class="query_select" id="reportModeId" name="reportMode">
						<option value="-1" selected="selected">请选择</option>
						<option value="1">去就业地报到</option>
						<option value="2">回生源地报到</option>
						<option value="3">去代理/托管地报到</option>
						<option value="6">未签发报到证</option>
					</select>
				</div>

				<div class="each_query addr">
					<span class="query_text">就业所在地</span>
					<select class="query_select" id="provinceInId" name="provinceInId">
						<option value="-1" selected="selected">请选择</option>
						<c:forEach items="${provinces}" var="p">
							<option value="${p.code}" >${p.name}</option>
						</c:forEach>
					</select>
				</div>

				<div class="each_query">
					<span class="query_text">关键字</span>
					<input type="text" placeholder="学号，姓名，身份证号，考生号" class="keyword_input" id="keyword" name="keyword" />
				</div>
				<div class="inputs">
					<input type="button" value="查询" class="query btn" id="querybtn" />
					<input type="submit" value="导出签约及学籍信息" class="excel btn" id="exportbtn" />
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
						<input type="checkbox" name="filter_sex" value="性别" class="filterCheck">
						<span class="filter_title">性别</span>
					</div>
					<div>
						<input type="checkbox" name="filter_stu_number" value="学号" class="filterCheck">
						<span class="filter_title">学号</span>
					</div>
					<div>
						<input type="checkbox" name="filter_grade" value="所在年级" class="filterCheck">
						<span class="filter_title">所在年级</span>
					</div>
					<div>
						<input type="checkbox" checked="checked" name="filter_candidateNumber" value="考生号" class="filterCheck">
						<span class="filter_title">考生号</span>
					</div>
					<div>
						<input type="checkbox" checked="checked" name="filter_college" value="学院" class="filterCheck">
						<span class="filter_title">学院</span>
					</div>
					<div>
						<input type="checkbox" checked="checked" name="filter_major" value="专业" class="filterCheck">
						<span class="filter_title">专业</span>
					</div>
					<div>
						<input type="checkbox" checked="checked" name="companyName" value="签约单位名称" class="filterCheck">
						<span class="filter_title">签约单位名称</span>
					</div>
					<div>
						<input type="checkbox" checked="checked" name="cityName" value="签约单位地址" class="filterCheck">
						<span class="filter_title">签约单位地址</span>
					</div>
					<div>
						<input type="checkbox" checked="checked" name="reportCompany" value="报到证迁往单位名称" class="filterCheck">
						<span class="filter_title">报到证迁往单位名称</span>
					</div>
					<div>
						<input type="checkbox" checked="checked" name="reportAddressName" value="报到证迁往单位所在地" class="filterCheck">
						<span class="filter_title">报到证迁往单位所在地</span>
					</div>
					<div>
						<input type="checkbox"  name="filter_stuLength" value="学制" class="filterCheck">
						<span class="filter_title">学制</span>
					</div>
					<div>
						<input type="checkbox"  name="filter_qualification" value="学历" class="filterCheck">
						<span class="filter_title">学历</span>
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
						<input type="checkbox" name="filter_counsellor_check_result" value="学院审核" class="filterCheck">
						<span class="filter_title">辅导员审核</span>
					</div>
					<div>
						<input type="checkbox" name="filter_deputy_secretary_check_result" value="副书记审核" class="filterCheck">
						<span class="filter_title">副书记审核</span>
					</div>
					<div>
						<input type="checkbox" name="whereaboutgo" value="毕业去向" class="filterCheck">
						<span class="filter_title">毕业去向</span>
					</div>
					<div>
						<input type="checkbox" name="reportMode" value="报到证签发类别" class="filterCheck">
						<span class="filter_title">报到证签发类别</span>
					</div>
					<div>
						<input type="checkbox" name="stuRemark" value="学生备注" class="filterCheck">
						<span class="filter_title">学生备注</span>
					</div>
					<div>
						<input type="checkbox" name="schoolCheckResult" value="学校审核" class="filterCheck">
						<span class="filter_title">学校审核</span>
					</div>
					<div>
						<input type="checkbox" name="schoolCheckReason" value="审核理由" class="filterCheck">
						<span class="filter_title">审核理由</span>
					</div>

					<div>
						<input type="checkbox" name="reserve" value="业务预约" class="filterCheck">
						<span class="filter_title">业务预约</span>
					</div>
					<%--<div>--%>
						<%--<input type="checkbox" name="submitTime" value="学生提交时间" class="filterCheck">--%>
						<%--<span class="filter_title">学生提交时间</span>--%>
					<%--</div>--%>
					<shiro:hasAnyRoles name="admin">
						<div>
							<input type="checkbox" name="orientationCancelContract" value="定向解约材料" class="filterCheck">
							<span class="filter_title">定向解约材料</span>
						</div>
						<div>
							<input type="checkbox" name="freeNormalCancelContract" value="免师解约材料" class="filterCheck">
							<span class="filter_title">免师解约材料</span>
						</div>
						<div>
							<input type="checkbox" name="freeNormalTransProvincial" value="免师跨省材料" class="filterCheck">
							<span class="filter_title">免师跨省材料</span>
						</div>
						<div>
							<input type="checkbox" name="enterBeijing" value="进京函" class="filterCheck">
							<span class="filter_title">进京函</span>
						</div>
						<div>
							<input type="checkbox" name="enterTianjin" value="进津函" class="filterCheck">
							<span class="filter_title">进津函</span>
						</div>
						<div>
							<input type="checkbox" name="enterShanghai" value="进沪函" class="filterCheck">
							<span class="filter_title">进沪函</span>
						</div>
						<div>
							<input type="checkbox" name="adminRemark" value="备注" class="filterCheck">
							<span class="filter_title">备注</span>
						</div>
					</shiro:hasAnyRoles>

				</div>
				<div class="filter_footer">
					<input type="checkbox" name="checkAll" class="checkAll" id="J_filterAll">
					<span>全选</span>
					<a href="javascript:;" class="view" id="filterIt">筛选</a>
				</div>
			</div>
			<div class="table-box">
			<table>
				<thead>
				<th>序号</th>
				<th style="display:none;">数据库ID</th>
				<th>姓名</th>
				<th class="filter_sex">性别</th>
				<th class="filter_stu_number">学号</th>
				<th class="filter_grade">所在年级</th>
				<th class="filter_candidateNumber">考生号</th>
				<th class="filter_college">学院</th>
				<th class="filter_major">专业</th>
				<th class="companyName">签约单位名称</th>
				<th class="cityName">签约单位地址</th>
				<th class="reportCompany">报到证迁往单位名称</th>
				<th class="reportAddressName">报到证迁往单位所在地</th>
				<th class="filter_stuLength">学制</th>
				<th class="filter_qualification">学历</th>
				<th class="filter_idNumber">身份证号</th>
				<th class="filter_nation">民族</th>
				<th class="filter_school">院校名称</th>
				<th class="filter_trainingMode">培养方式</th>
				<th class="filter_weipeiUnit">定向委培单位</th>
				<th class="filter_originPlace">生源所在地</th>
				<th class="filter_politicalStand">政治面貌</th>
				<th class="filter_normalStu">师范生类别</th>
				<th class="filter_difficulty">困难生类别</th>
				<!-- /modify -->
				<th class="filter_counsellor_check_result">辅导员审核</th>
				<th class="filter_deputy_secretary_check_result">副书记审核</th>
				<th class="whereaboutgo">毕业去向</th>
				<th class="reportMode">报到证签发类别</th>
				<th class="stuRemark">学生备注</th>
				<th class="schoolCheckResult">学校审核</th>
				<th class="schoolCheckReason">审核理由</th>
				<th class="reserve">业务预约</th>
				<%--<th class="submitTime">学生提交时间</th>--%>
				<shiro:hasAnyRoles name="admin">
					<th class="orientationCancelContract">定向解约材料</th>
					<th class="freeNormalCancelContract">免师解约材料</th>
					<th class="freeNormalTransProvincial">免师跨省材料</th>
					<th class="enterBeijing">进京函</th>
					<th class="enterTianjin">进津函</th>
					<th class="enterShanghai">进沪函</th>
					<th class="adminRemark">备注</th>
				</shiro:hasAnyRoles>

				<th>操作</th>
				<shiro:hasAnyRoles name="admin,college-secretary,employ">
					<th>是否选中</th>
				</shiro:hasAnyRoles>
				</thead>
				<tbody id="J_template">

				</tbody>
			</table>
			</div>
		</div>
		<div>
			<shiro:hasAnyRoles name="admin,college-secretary,employ">
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
		</div>
			<div id="J_pagination">
				<!-- 分页区域 -->
			</div>
		</div>
	</div>
<div class="stateBox" style="display:none">
	<div class="box">
		<p class="statementTitle">审核状态</p>
		<div class="X">×</div>
		<table>
			<tr>
				<td>级别</td>
				<td>审核状态</td>
				<td>操作人</td>
				<td>审核时间</td>
			</tr>
			<tr>
				<td>辅导员</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>副书记</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<%--<tr>--%>
				<%--<td>学校</td>--%>
				<%--<td></td>--%>
				<%--<td></td>--%>
				<%--<td></td>--%>
			<%--</tr>--%>
		</table>
	</div>
</div>
<script id="query-stuInfo" type="text/template">
	{@each data as dispatchInfo,index}
	<tr>
		<td class="stuorder">&{dispatchInfo.index}</td>
		<td style="display:none;" class="stuId">&{dispatchInfo.stuStatusInfoId}</td>
		<td style="display:none;" class="realStuId">&{dispatchInfo.id}</td>
		<%--<td style="display:none;" class="nextStuId">&{dispatchInfo.nextstuStatusInfoId}</td>--%>
		<td>&{dispatchInfo.name}</td>
		<td class="filter_sex">&{dispatchInfo.sex}</td>
		<td class="filter_stu_number">&{dispatchInfo.studentNum}</td>
		<td class="filter_grade">&{dispatchInfo.grade}</td>
		<td class="filter_candidateNumber">&{dispatchInfo.canditateNum}</td>
		<td class="filter_college">&{dispatchInfo.studentCollege}</td>
		<td class="filter_major">&{dispatchInfo.studentMajor}</td>
		<td class="companyName">&{dispatchInfo.companyName}</td>
		<td class="cityName">&{dispatchInfo.fullAddress}</td>
		<td class="reportCompany">&{dispatchInfo.reportCompany}</td>
		<td class="reportAddressName">&{dispatchInfo.dispatchUnitAddress}</td>
		<td class="filter_stuLength">&{dispatchInfo.stuLength}</td>
		<td class="filter_qualification">&{dispatchInfo.qualification}</td>
		<td class="filter_idNumber">&{dispatchInfo.idNumber}</td>
		<td class="filter_nation">&{dispatchInfo.nation}</td>
		<td class="filter_school">&{dispatchInfo.school}</td>
		<td class="filter_trainingMode">&{dispatchInfo.trainingMode}</td>
		<td class="filter_weipeiUnit">&{dispatchInfo.weipeiUnit}</td>
		<td class="filter_originPlace">&{dispatchInfo.originPlace}</td>
		<td class="filter_politicalStand">&{dispatchInfo.politicalStand}</td>
		<td class="filter_normalStu">&{dispatchInfo.normalStu}</td>
		<td class="filter_difficulty">&{dispatchInfo.difficulty}</td>
		<!-- /modify -->
		<td class="filter_counsellor_check_result">&{dispatchInfo.counsellorCheckResult}</td>
		<td class="filter_deputy_secretary_check_result">&{dispatchInfo.deputySecretaryCheckResult}</td>
		<td class="whereaboutgo">&{dispatchInfo.whereAboutToGo}</td>
		<td class="reportMode">&{dispatchInfo.reportMode}</td>
		<td class="stuRemark">&{dispatchInfo.stuRemark}</td>
		<td class="schoolCheckResult">&{dispatchInfo.schoolCheckResult}</td>
		<td class="schoolCheckReason">&{dispatchInfo.schoolCheckReason}</td>
		<td class="reserve">&{dispatchInfo.protocolType}</td>
		<%--<td class="submitTime">学生提交时间</td>--%>
		<shiro:hasAnyRoles name="admin">
			<td class="orientationCancelContract">&{dispatchInfo.orientationCancelContract}</td>
			<td class="freeNormalCancelContract">&{dispatchInfo.freeNormalCancelContract}</td>
			<td class="freeNormalTransProvincial">&{dispatchInfo.freeNormalTransProvincial}</td>
			<td class="enterBeijing">&{dispatchInfo.enterBeijing}</td>
			<td class="enterTianjin">&{dispatchInfo.enterTianjin}</td>
			<td class="enterShanghai">&{dispatchInfo.enterShanghai}</td>
			<td class="adminRemark">&{dispatchInfo.adminRemark}</td>
		</shiro:hasAnyRoles>
		<td>
			<a href="javascript:;" class="view">查看</a>
			<shiro:hasAnyRoles name="admin, college-secretary, counsellor">

			<a href="alter/&{dispatchInfo.id}/&{dispatchInfo.stuStatusInfoId}">修改</a>
			<%--<a href="javascript:;" class="delete">删除</a>--%>
			<a href="javascript:;" class="state">审核状态</a>
			</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="admin,employ,college-secretary">
			<td>
				<input type="checkbox" name="id" class="checkToPass" value="">
			</td>
		</shiro:hasAnyRoles>
	</tr>
	{@/each}
	</script>
	<script type="text/javascript">
		KISSY.use('page/admin_page/students_management/dispatch/studentList', function(S) {
			var urls = PW.Env.url.admin_page.students_management.dispatch.studentList;
			var url = urls.showStu;//传出来的数据
            var $ = S.all; // 定义$
			console.log(url);
			S.ready(function(){
				PW.page.admin_page.students_management.dispatch.studentList({
					renderTo: '#J_pagination', //分页显示指向
					juicerRender: '#query-stuInfo', //模板渲染
					dataRender: '#J_template', //模板的数据放的地方
					pageSize:15,//每页显示的记录数
					url: url,
					/*发送ajax,必须指定ajax的url */
					configUrl:function(url,page,me,prevdata){
						url = url + '/' + page;//套页时使用
						return url;
						// return url + page;
					},
                    afterDataLoad:function(that,data,page) {
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
        function exportDispatch()
        {
            window.location.href = "/admin/export/all";
        }
	</script>
</body>
</html>