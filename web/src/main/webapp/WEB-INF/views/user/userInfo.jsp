<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>系统管理——用户信息</title>
    <jsp:include page="../common/link.jsp"/>
    <jsp:include page="../common/script.jsp"/>
<link rel="stylesheet" type="text/css" href="${staticWebsite}/css/base/reset.css">
<link rel="stylesheet" type="text/css" href="${staticWebsite}/css/common/common.css">
<link rel="stylesheet" type="text/css" href="${staticWebsite}/css/page/admin_page/system_management/userInfo.css">
	<script src="${staticWebsite}/js/site-config.js"></script>
<!-- <script src="http://pui.dev.net/1.1/lib/kissy/seed.js"></script> -->
<script src="${staticWebsite}/js/pui/trunk/1.1/lib/kissy/seed.js"></script>

	<link rel="stylesheet" type="text/css" href="${staticWebsite}/css/page/admin_page/system_management/importData.css">
	<script type="text/javascript" src="/nenu_jysj_front/resources/upload/js/jquery-1.7.2.js"></script>
</head>
<body>
<div class="page">
    <jsp:include page="../common/header.jsp"/>
    <jsp:include page="../common/Asidebar.jsp"/>
              
    <div class="content">
        <div class="info1">
            <table>
                <p class="p">用户信息</p>
                 <tr><td>真实姓名</td><td>辅导员</td></tr>
                 <tr><td>用户名</td><td>${user.realName}</td></tr>
                 <tr><td>手机</td><td>${user.cellphone}</td></tr>
            </table>
            </div>
            
            <div class="info2">
              <table>
            <p class="p"><label>辅导员</label>——<label>${user.college}</label>——<label>专业信息</label></p>
                <tr><th>专业层次</th><th>专业大类</th><th>专业中类</th><th>专业名称</th></tr>
                  <c:forEach items="${majors}" var="major">
                      <tr><td>${major.qualification}</td><td>${major.majorDivision}</td><td>${major.majorClass}</td><td>${major.majorName}</td></tr>
                  </c:forEach>
            </table>
            </div>
    </div>
</div>

	<script type="text/javascript">
	    KISSY.use('module/sidebar',function(S){
	        S.ready(function(){
	            PW.module.Sidebar();
	        });
	    });
	</script>	
	
</body>
<script type="text/javascript" src="/nenu_jysj_front/resources/js/page/admin_page/system_management/importData.js"></script>   
</html>