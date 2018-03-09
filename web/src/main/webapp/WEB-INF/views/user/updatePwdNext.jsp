<%--
  Created by IntelliJ IDEA.
  User: sunz sangxinbingkuang
  Date: 17-09-25
  Time: 晚上19:43(我在玩蛇)
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>东北师范大学网上签约管理系统--修改密码</title>
    <%@include file="../common/link.jsp"%>
    <%@include file="../common/script.jsp"%>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/system.css"/>
</head>
<body>
<%@include file="../common/header.jsp"%>
<div class="continer">
    <%@include file="../common/Asidebar.jsp"%>
    <script type="text/javascript">
        KISSY.use('module/sidebar',function(S){
            S.ready(function(){
                PW.module.Sidebar();
            });
        });
    </script>
    <div class="content">
        <%@include file="../common/crumbs.jsp"%>
        <div class="page-content">
            <div class="block">
                <div class="block-header">
                    <h3>修改密码</h3>
                </div>

                <form action="${website}admin/user/updatePwdNext" method="post">
                    <input type="hidden" name="id" value="${id}">
                    <div class="block-content">

                        <div class="content-input">
                            <label>新密码</label>
                            <input type="password" id="pwd" class="inputText" data-valid-rule="notNull&isPasword" name="newPwd">
                        </div>
                        <div class="content-input">
                            <label>重复密码</label>
                            <input type="password" class="inputText" data-valid-rule=
                                    "notNull&isPassword&require(pwd)&equal(pwd)" name="secondPwd">
                        </div>
                        <div class="content-warning">
                            <p class="red-warning">${msg}</p>
                        </div>
                        <div class="content-input">
                            <button type="submit" class="btn btn-margin-left btn-margin-right">确定</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    KISSY.use('mod/defender',function(S){
        S.ready(function(){
            var
                    Defender = PW.mod.Defender;
            Defender.client('form',{
                showTip:false
            });
        });
    });
</script>
</body>
</html>
