<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>东北师范大学网上签约管理系统--登录</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8"/>
    <meta http-equiv="keywords" conten="keyword,keyword"/>
    <jsp:include page="../common/link.jsp"/>
    <jsp:include page="../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/login.css"/>
</head>
<body>
<form action="${website}login" method="post">
    <div class="login-header"><h3>东北师范大学网上签约管理系统</h3></div>
    <div class="login-content">
        <div class="login-type">
            <label>类别选择</label>
            <input type="radio" name="login-type" checked="checked" class="J_type" id="admin">教师
            <input type="radio" name="login-type" class="J_type">学生
        </div>
        <div class="login-content-inputButton">
            <label>用户名</label>
            <input type="text" name="username"  class="login-content-input" />
        </div>
        <div class="login-content-inputButton">
            <label>密　码</label>
            <input type="password" name="password" class="login-content-input">
        </div>
        <div class="J_year">
            <label>毕业年份</label>
            <select class="year-option" name="graduateYear">
                <option value="2018">2018</option>
            </select>
        </div>
        <div class="J_tip">
            <p>用户名为师大学号,密码为对应密码</p>
        </div>
        <span class="error-tip">${error}</span>
        <div class="login-content-inputButton button">
            <input type="submit" value="登录" class="btn">
            <input type="reset" value="取消"  class="btn">
        </div>
    </div>
    <p class="recommended-browser">推荐使用浏览器：
        <a href="http://www.firefox.com.cn/download/" target="_blank">火狐浏览器(点击下载)</a>
        <a href="http://www.google.cn/intl/zh-CN/chrome/browser/" target="_blank">谷歌浏览器(点击下载)</a>
        <a href="https://support.microsoft.com/zh-cn/help/17621/internet-explorer-downloads" target="_blank">IE10及以上浏览器(点击下载)</a>
    </p>
</form>
<script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
    $(function () {
        // 默认隐藏学生提示
        $('.J_tip').hide();
        $('.J_type').on('change',function(ev) {
            var value = document.getElementById('admin').checked;
            if (value) {
                $('.J_year').show();
                $('.J_tip').hide();
            }
            else{
                $('.J_year').hide();
                $('.J_tip').show();

            }
        });
    })

</script>
</body>
</html>