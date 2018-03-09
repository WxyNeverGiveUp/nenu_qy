<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--sidebar start-->
<!-- 学生页面 -->
<div class="sidebar">
	<ul>
		<li class="list">
			<a href="javaScript:;">
				<i class="schoolRoll"></i>
				<span>学籍信息</span>
				<b></b>
			</a>
			<ul class="submenu none">
				<li>
					<a href="${website}student/intentionSurvey">就业意向调查</a>
				</li>
				<li>
					<a href="${website}student/info/new">采集学籍信息</a>
				</li>
				<%--<li>--%>
					<%--<a href="${website}student/info/update">修改学籍信息</a>--%>
				<%--</li>--%>
				<li>
					<a href="${website}student/info/detail">查看学籍信息</a>
				</li>
			</ul>
		</li>

		<li class="list">
			<a href="javaScript:;">
				<i class="dispatch"></i>
				<span>签约信息</span>
				<b></b>
			</a>
			<ul class="submenu none">
				<%--<li>--%>
					<%--<a href="${website}student/dispatch/add">填写签约信息</a>--%>
				<%--</li>--%>
				<%--<li>--%>
					<%--<a href="${website}student/dispatch/update">修改签约信息</a>--%>
				<%--</li>--%>
				<li>
					<a href="${website}student/dispatch/update">签约信息</a>
				</li>
				<li>
					<a href="${website}student/dispatch/detail">查看签约信息详情</a>
				</li>
			</ul>
		</li>
		</li>
		<%--<li class="list">--%>
			<%--<a href="javaScript:;">--%>
				<%--<i class="business"></i>--%>
				<%--<span>业务受理</span>--%>
				<%--<b></b>--%>
			<%--</a>--%>
			<%--<ul class="submenu none">--%>
				<%--<li>--%>
					<%--<a href="${website}student/business/change/new">身份变更</a>--%>
				<%--</li>--%>
				<%--<li>--%>
					<%--<a href="${website}student/business/protocol/new">申请新协议</a>--%>
				<%--</li>--%>
				<%--<li>--%>
					<%--<a href="${website}student/report_card/detail">报到证状态</a>--%>
				<%--</li>--%>
			<%--</ul>--%>
		<%--<li>--%>
		<li class="list">
			<a href="javaScript:;">
				<i class="business"></i>
				<span>业务预约</span>
				<b></b>
			</a>
			<ul class="submenu none">
				<li>
					<a href="${website}student/business/toBusiness/1">申领新协议</a>
				</li>
				<li>
					<a href="${website}student/business/toBusiness/2">毕业去向变更领协议</a>
				</li>
				<li>
					<a href="${website}student/business/toBusiness/3">免费师范生业务</a>
				</li>
				<li>
					<a href="${website}student/business/toBusiness/4">定向、委培生业务</a>
				</li>
				<li>
					<a href="${website}student/business/toBusiness/5">博士领协议业务</a>
				</li>
			</ul>
		</li>
		<li class="list">
			<a href="javaScript:;">
				<i class="business"></i>
				<span>常用材料下载</span>
				<b></b>
			</a>
			<ul class="submenu none">
				<li>
					<a href="${staticWebsite}../download/qianyuexinxitianxieshuoming.doc">签约信息填写说明</a>
				</li>
				<li>
					<a href="${staticWebsite}../download/wentijijiejuefangan.doc">签约管理系统数据问题及解决方案</a>
				</li>
				<li>
					<a href="${staticWebsite}../download/benkeshengtuijianbiao.docx">东北师范大学2018届本科毕业生推荐表</a>
				</li>
				<li>
					<a href="${staticWebsite}../download/yanjiushengtuijianbiao.doc">东北师范大学2018届研究生毕业推荐表</a>
				</li>
				<li>
					<a href="${staticWebsite}../download/fangqichuguo.docx">放弃出国（境）新领协议申请表</a>
				</li>
				<li>
					<a href="${staticWebsite}../download/fangqishengxue.docx">放弃升学新领协议申请表</a>
				</li>
				<li>
					<a href="${staticWebsite}../download/linghuojiuye.doc">普通高校毕业生灵活就业登记表</a>
				</li>
				<li>
					<a href="${staticWebsite}../download/weiyueshenqingbiao.docx">违约个人申请表</a>
				</li>
				<li>
					<a href="${staticWebsite}../download/xieyishenqingbiao.docx">协议丢失、污损个人申请表</a>
				</li>
				<li>
					<a href="${staticWebsite}../download/xueshengshiyongshouce.docx">东北师范大学网上签约管理系统使用手册</a>
				</li>
				<li>
					<a href="${staticWebsite}../download/liuchengtu.docx">东北师范大学网上签约服务流程图</a>
				</li>
			</ul>
		</li>
		<li class="list">
			<a href="javaScript:;">
				<i class="business"></i>
				<span>往届就业去向</span>
				<b></b>
			</a>
			<ul class="submenu none">
				<li>
					<a href="${website}student/history/data/list">往届就业去向</a>
				</li>
			</ul>
		</li>
		<li>
			<a href="${website}logout">
				<i class="logout"></i>
				<span>安全退出</span>
			</a>
		</li>
	</ul>
</div>
<!--sidebar end-->