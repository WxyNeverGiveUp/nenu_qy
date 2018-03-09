<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: fujia
  Date: 2016/7/26
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<!-- 变更详情页 廖月云 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>变更详情</title>
	<jsp:include page="../../common/link.jsp"/>
	<jsp:include page="../../common/script.jsp"/>
	<!--#include file="/nenu_jysj_front/pages/common/link.html"  -->
	<!--#include file="/nenu_jysj_front/pages/common/script.html"  -->
	<script type="text/javascript" src="${staticWebsite}/upload/js/jquery-1.7.2.js"></script>
	<link rel="stylesheet" type="text/css" href="${staticWebsite}/css/common/detail.css">
	<link rel="stylesheet" type="text/css" href="${staticWebsite}/css/page/admin_page/business_handling/calendar.css">
	<script type="text/javascript" src="${staticWebsite}js/page/admin_page/business_handling/calendar.js"></script>
	<style type="text/css">
		.box{
			width: 200px;
			height: 200px;
			background: #ccc;
		}
	</style>
</head>
<body>
<div class="page">
	<jsp:include page="../../common/header.jsp"/>
	<jsp:include page="../../common/Asidebar.jsp"/>
	<!--#include file="/nenu_jysj_front/pages/common/header.html"  -->
	<!--#include file="/nenu_jysj_front/pages/common/Asidebar.html"  -->
	<script type="text/javascript">
        KISSY.use('module/sidebar',function(S){
            S.ready(function(){
                PW.module.Sidebar();
            });
        });
	</script>
	<div class="content">
		<div class="infomation">
			<table>
				<caption>
					变更身份信息
				</caption>
				<tr>
					<td>姓名</td>
					<td>${student.name}</td>
					<td>身份证号</td>
					<td>${student.idNumber}</td>
					<td>学号</td>
					<td>${student.studentNumber}</td>
					<td>所在院系/分院</td>
					<td>${student.college}</td>
				</tr>
				<tr>
					<td>所在年级</td>
					<td>${student.grade}</td>
					<td>师范生类别</td>
					<td>${student.normalStu}</td>
					<td>培养方式</td>
					<td>${student.trainingMode}</td>
					<td>申请原因</td>
					<td>${protocolType}<c:if test="${protocolType == '免师跨省' }">(${province})</c:if> </td>
				</tr>
				<tr>
					<td>生源所在地</td>
					<td>${student.originPlace}</td>
					<td>生源地类别</td>
					<td>${student.originPlaceType}</td>
					<td>手机号码</td>
					<td>${student.cellphone}</td>
					<td>手机号码（备用）</td>
					<td>${student.cellphoneBak}</td>
				</tr>
				<tr>
					<td>定向委培单位地址</td>
					<td colspan="3">${student.weipeiUnitPlace}</td>
					<td>定向委培单位</td>
					<td colspan="3">${student.weipeiUnit}</td>
				</tr>
				<tr>
					<td>已有协议编号</td>
					<td colspan="3">${oldAgreementNumber}</td>
					<td></td>
					<td colspan="3"></td>
				</tr>
			</table>
		</div>
		<div class="show">
			<table>
				<caption>
					审核结果
				</caption>
				<tr>
					<td>级别</td>
					<td>审核结果</td>
					<td>理由</td>
					<td>时间</td>
					<td>操作人</td>
					<td>操作</td>
				</tr>
				<tr id="school">
					<td class="check-level">学校</td>
					<td class="check-result" value="weishenhe">${checkProtocolResult}</td>
					<td>${protocol.checkProtocolReason}</td>
					<td>${checkProtocolTime}</td>
					<td>${protocol.checkProtocolOperator}</td>
					<td class="edit-result">
                    <shiro:hasAnyRoles name="admin,employ">
						<input type="button" value="修改结果" check-level="school">
                    </shiro:hasAnyRoles>
					</td>
				</tr>

			</table>
		</div>
		<div class="material clearfix">
			<strong class="material-show">材料展示</strong>
			<ul class="material-pic clearfix">

				<c:forEach items="${materialList}" var="material" varStatus="status">
				<li>
					<img src="${uploadWebsite}${material.materialUrl}">
					<span>${material.materialName}</span>
				</li>
				</c:forEach>

			</ul>
		</div>
		<div class="more-detail">
			<a href="javascript:;" onclick="window.history.go(-1)" class="btn">返回上一页</a>
		</div>
		<div class="pop-check">
			<form action="javacsript:;" class="check">
				<fieldset class="clearfix" style="position:relative;">
					<input type="hidden" name="id" value="${protocol.id}"/>
					<b></b>
					<legend>审核状态</legend>
					<p class="check-state-time">审核</p>
					<span class="check-state">
							<strong>
								审核状态
							</strong>

							<input type="radio" name="checkProtocolResult" value="2" class="pass">
							<label>通过</label>
							<input type="radio" name="checkProtocolResult" value="3">
							<label>不通过</label>
						</span>
					<span class="check-reason">
							<strong>
								审核理由
							</strong>
							<select name="checkProtocolReason">
								<option value="-1">请选择</option>
								<option value="1" class="yes">信息属实</option>
								<option value="2" class="not">修改师范生类型不通过，证明材料不充分</option>
								<option value="3" class="not">修改生源地城市不通过，证明材料不充分</option>
								<option value="4" class="not">修改委培方式不通过，证明材料不充分</option>
								<option value="5" class="not">修改定向委培单位不通过，证明材料不充分</option>
								<option value="6" class="not">个人信息有错误</option>
								<option value="7" class="not">材料不规范</option>
								<option value="8" class="not">材料不齐全</option>
								<option value="99">其他</option>
							</select>
							<input type="text" name="checkOther" class="check-other-input">
						</span>
					<br/>

					<span class="new-protocol">
						<c:if test="${ (protocol.protocolType > 1 && protocol.protocolType < 5) }">
							<span class="distribute new-or-not">
								<strong>
									是否发放新协议
								</strong>
								<input type="radio" name="distributeNew" value="2" checked ><label>是</label>
								<input type="radio" name="distributeNew" value="4" ><label>否</label>
							</span>

							<span class="distribute new-yes">
								<strong>
								新协议编号
								</strong>
								<input type="text" name="agreementNumber">
								<span class="new-protocol-tip"></span>
							</span>
						</c:if>
						<!-- <span class="appointment">
                            <strong class="must matter">
                                预约时间
                            </strong>
                            <input class="J_date J_notNull J_eDate" type="text" name="date"/>
                        </span> -->
						<span class="demo2 appointment">
							<strong class="must matter">
								预约时间
							</strong>
							<%--<input placeholder="请输入日期" class="laydate-icon" onClick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" name="fetchTime">--%>
							<p class="select-date-box">
								<input type="text" name="date" placeholder="请选择日期" class="J_selectDate select-date laydate-icon" />
							</p>

						</span>

						<span class="appointment addr">
							<strong>
								预约地点
							</strong>
							<select name="fetchPlace" class="address" style="text-align: center;">
								<option value="">请选择</option>
								<option value="本部校区就业指导服务中心204">本部校区就业指导服务中心204</option>
							</select>
							<%--<input type="text" name="fetchPlace" class="address">--%>
						</span>

					</span>
					<br/>

					<div class="check-submit-line clearfix">
						<input type="submit" value="确认修改" class="check-submit save-submit"></input>
						<input type="button" value="返回" class="check-submit back"></input>
					</div>
				</fieldset>
			</form>
		</div>
		<div class="pop-pic">
			<img src="">
			<a href="javascript:;">关闭</a>
		</div>

		<!--

		<div class="more-detail">
			<a href="javascript:;" class="next-student">下一个学生</a>
		</div>
		-->

		<!-- <form class="parameter-hidden">
            <fieldset>
                <legend>为下一个学生设置的隐藏域</legend>
                <input type="hidden" name="id" />
                <input type="hidden" name="curPage" />
                <input type="hidden" name="idList" />
                <input type="hidden" name="curNo" />
                <input type="hidden" name="conditions" />
            </fieldset>
        </form> -->
		<form class="parameter-hidden">
			<fieldset>
				<legend>为隐藏下一个学生设置的隐藏域</legend>
				<input type="hidden" name="parameter" value="" class="parameter"/>
			</fieldset>
		</form>
	</div>
</div>
<script type="text/javascript">
    KISSY.use('page/admin_page/business_handling/changeDetail',function(S){
        S.ready(function(){
            PW.page.admin_page.business_handling.changeDetail();
        })
    })
</script>
<script type="text/javascript">
    KISSY.use('page/student_page/school_roll/writeInfo',function(S){
        S.ready(function(){
            PW.page.student_page.school_roll.writeInfo();
        });
    });
</script>
</body>
</html> 