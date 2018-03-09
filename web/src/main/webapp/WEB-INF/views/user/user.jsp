<%--
  Created by IntelliJ IDEA.
  User: chenyt
  Date: 14-9-23
  Time: 下午2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>添加用户</title>
    <meta http-equiv="keywords" content="keyword,keyword"/>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/adduser.css"/>
</head>
<body>
<form action="${website}admin/user" method="post">
    <!--输入框的整体布局-->
    <div class="divstyle1">

        <!--真实姓名的布局-->
        <div class="divstyle2">
            <span class="spanstyle1">真实姓名:</span>

            <input type="text" name="realName" /><span class="spanstyle2">*</span>
        </div>

        <!--用户名的布局-->
        <div class="divstyle2">
            <span class="spanstyle1">用户名:</span><input type="text" name="username" placeholder="6位以上的英文或数字" /><span class="spanstyle3">唯一</span><span class="spanstyle2">*</span>
        </div>

        <!--电话的布局-->
        <div class="divstyle2">
            <span class="spanstyle1">电话:</span><input type="text" name="cellphone" /><span class="spanstyle2">*</span>
        </div>

        <!--密码的布局-->
        <div class="divstyle2">
            <span class="spanstyle1">密码:</span><input type="text" name="password" placeholder="6位以上的英文或数字"/><span class="spanstyle2">*</span>
        </div>

        <!--确认密码的布局-->
        <div class="divstyle2">
            <span class="spanstyle1">重复密码:</span><input type="text" name="secondPwd"/><span class="spanstyle2">*</span>
        </div>
    </div>

    <!--角色的布局-->
    <div class="divstyle3">
        <!--角色的标题-->
        <span class="spanstyle4">角色选择：</span>
        <div>
            <table>
                <tr>
                    <c:forEach items="${roles}" var="roles">
                        <td><input type="checkbox" name="roleIds"  value="${roles.id}"><span>${roles.role}</span></td>
                     </c:forEach>
                </tr>
            </table>
        </div>
    </div>

    <!--确认、重置按钮-->
    <div class="divstyle4">
        <ul class="ulstyle1">
            <li><input type="submit" value="确认"/></li>
            <li><input type="reset" value="重置"/></li>
        </ul>
    </div>
</form>
</body>
</html>