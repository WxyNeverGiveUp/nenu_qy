<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>查看详情</title>
	<jsp:include page="../../common/link.jsp" />
	<jsp:include page="../../common/script.jsp"/>
	<link rel="stylesheet" type="text/css" href="${staticWebsite}css/common/detail.css">
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
	<div class="page">
		<!-- <div class="header"></div> -->
		<div class="sidebar"></div>	
		<div class="content">
			<div class="infomation">
				<table>
					<caption>
						<span>${dh.name}同学</span>签约信息表
						<br/>
						协议编号：<span>${dh.agreementNumber}</span>
					</caption>
					<tr>
						<td>毕业去向</td>
						<td>${whereaboutgo}</td>
						<td>签约单位名称</td>
						<td>${dh.companyName}</td>
						<td>单位组织机构代码</td>
						<td>${dh.organizationCode}</td>
						<td>单位所在地</td>
						<td>${dh.cityName}</td>
					</tr>
					<tr>
						<td>单位性质</td>
						<td>${property}</td>
						<td>单位行业</td>
						<td>${companyTrade}</td>
						<td>单位隶属部门</td>
						<td>${dh.subordinateDepartment}</td>
						<td>工作职位类别</td>
						<td>${job}</td>
					</tr>
					<tr>
						<td>报道证签发类别</td>
						<td>${reportCode}</td>
						<td>报到证迁往单位名称</td>
						<td>${dh.reportCompany}</td>
						<td>报到证迁往单位所在地</td>
						<td>${dh.reportAddressName}</td>
						<td></td>
						<td></td>
						<%--<td>档案转寄单位</td>--%>
						<%--<td>${dh.fileCompany}</td>--%>
					</tr>
					<tr>
						<td>档案转寄单位地址</td>
						<td>${dh.fileCompanyAddressName}</td>
						<td>档案转寄单位邮编</td>
						<td>${dh.fileCompanyPostcode}</td>
						<td>是否接受户口</td>
						<td>
							<c:choose>
								<c:when test="${dh.acceptHukou==0}">
									否
								</c:when>
								<c:when test="${dh.acceptHukou==1}">
									是
								</c:when>
								<c:otherwise>
									是
								</c:otherwise>
							</c:choose>
						</td>
						<td>是否接受档案</td>
						<td>
							<c:choose>
								<c:when test="${dh.acceptFile==0}">
									否
								</c:when>
								<c:when test="${dh.acceptFile==1}">
									是
								</c:when>
								<c:otherwise>
									是
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td>户口签转地</td>
						<td>${dh.hukouTransferAddress}</td>
						<td>档案接收部门</td>
						<td>${dh.acceptFileDepartment}</td>
						<td>档案接收联系人</td>
						<td>${dh.acceptFilePerson}</td>
						<td>档案接收联系人电话</td>
						<td>${dh.acceptFileTele}</td>
					</tr>

					<tr>
						<td>单位联系人</td>
						<td>${dh.companyContactPerson}</td>
						<td>联系人电话</td>
						<td>${dh.contactPersonTele}</td>
						<td>联系人手机号码</td>
						<td>${dh.contactPersonMobile}</td>
						<td>联系人电子邮箱</td>
						<td>${dh.contactPersonEmail}</td>
					</tr>
					<tr>
						<td>联系人传真</td>
						<td>${dh.contactPersonFax}</td>
						<td>单位地址</td>
						<td>${dh.fullAddress}</td>
						<td>单位邮编</td>
						<td>${dh.companyPostcode}</td>
						<td>备注</td>
						<td></td>
					</tr>
				</table>
			</div>
			<div class="material clearfix">	
				<strong class="material-show">材料展示</strong>
				<ul class="material-pic clearfix">
					<c:forEach var = "m" items="${mt}">
						<li>
							<img src="${uploadWebsite}${m.materialUrl}" >
							<span>${m.materialName}</span>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="confirm">
				<a href="${website}admin/dispatch/alter/${dh.id}/${dh.statusInfoId}">返回修改</a>
				<a href="${website}admin/dispatch/list">确认提交</a>
			</div>
			<div class="pop-pic">
				<img src="">
				<a href="javascript:;">关闭</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		KISSY.use('page/admin_page/students_management/dispatch/checkDetail',function(S){
			S.ready(function(){
				PW.page.admin_page.students_management.dispatch.checkDetail();
			})
		}) 
	</script>
</body>
</html> 