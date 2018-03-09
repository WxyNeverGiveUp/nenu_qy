<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhaixiaomin
  Date: 14-9-28
  Time: 下午4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>东北师范大学网上签约管理系统</title>
    <%@include file="../common/link.jsp"%>
    <%@include file="../common/script.jsp"%>
    <link rel="stylesheet" content="text/css" href="${staticWebsite}css/page/system.css"/>
    <link rel="stylesheet" content="text/css" href="${staticWebsite}css/page/admin_page/students_management/dispatch/studentList.css"/>
    <script type="text/javascript">
        KISSY.use('page/system/user-list',function(S){
            S.ready(function(){
                PW.page.userList({
                    renderTo: '#J_pagination',//分页显示指向
                    juicerRender: '#tpl',//模版渲染指向
                    dataRender: '#J_template',
                    pageSize: 15,//每页显示的记录数
                    url:'${website}admin/user/list',//必选，必须指定ajax的url
                    configUrl:function(url,page,me,prevdata){
                        var url = url+'/'+page;
                        return url;
                    },
                    type:'get'
                });
            });
        });
    </script>
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
        <div class="stu_query">
            <h2 class="query_name">查询条件</h2>
            <form class="query_selects clearfix" action="/admin/export/studentNew" onkeydown="if(event.keyCode==13)return false;">
                <!-- 以下选项框默认选中第一个 -->
                <div class="each_query">
                    <span class="query_text">学院</span>
                    <select class="query_select" id="college">
                        <option value="-1" selected="selected">请选择</option>
                        <c:forEach items="${colleges}" var="college">
                            <option value="${college.college}">${college.college}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="each_query">
                    <span class="query_text">角色</span>
                    <select class="query_select" id="role">
                        <option value="-1" selected="selected">请选择</option>
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.id}">${role.description}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="inputs">
                    <input type="button" value="查询" class="query btn" id="querybtn" />
                </div>
            </form>
        </div>
        <div class="page-content">
            <div class="block">
                <div class="block-header">
                    <h3>用户列表</h3>
                    <a class="btn btn-right" href="${website}admin/user/toUser">添加</a>
                </div>
                <div class="block-content">
                    <%--<input type="hidden" name="id" value="${id}">--%>
                    <script type="text/template" id="tpl">
                        {@each data as d}
                        <tr data-id="&{d.id}">
                            <td><input type="checkbox" data-id="&{d.id}"></td>
                            <td>&{d.realName}</td>
                            <td>&{d.username}</td>
                            <td>&{d.cellphone}</td>
                            <td class="J_lockHolder">
                                {@if d.locked == 1}
                                封锁
                                {@else if d.locked == 0}
                                解锁
                                {@else}
                                {@/if}
                            </td>
                            <td>&{d.description}</td>
                            <td>&{d.college}</td>
                            <td>&{d.qualification}</td>
                            <td>
                                {@if d.locked == 1}
                                <a href="javaScript:;" class="unlock" title="解锁">
                                    <i></i>
                                    </a>
                                    <a href="javaScript:;" class="lcok none" title="封锁">
                                        <i></i>
                                    </a>
                                    <a href="javaScript:;" class="add-mod none" title="修改密码">
                                        修改密码
                                    </a>
                                    <a href="javaScript:;" class="add-mod none" title="修改信息">
                                        修改信息
                                    </a>
                                {@else if d.locked == 0}
                                <a href="javaScript:;" class="unlock none" title="解锁">
                                    <i></i>
                                </a>
                                <a href="javaScript:;" class="lcok" title="封锁">
                                    <i></i>
                                </a>
                                <a href="toUpdatePwdNext/&{d.id}" class="add-mod" title="修改密码">
                                    修改密码
                                </a>
                                <a href="toUpdateUser/&{d.id}" class="add-mod" title="修改信息">
                                    修改信息
                                </a>
                                {@else}
                                {@/if}
                            </td>
                        </tr>
                        {@/each}
                    </script>
                    <table>
                        <thead>
                        <tr>
                            <th></th>
                            <th>真实姓名</th>
                            <th>用户名</th>
                            <th>电话</th>
                            <th>状态</th>
                            <th>角色</th>
                            <th>学院</th>
                            <th>所带学历</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="J_template"></tbody>
                    </table>
                    <div class="del-opr clearfix">
                        <div class="select-all">
                            <input type="checkbox" id="J_selectAll"/>
                            <label>全选</label>
                        </div>
                        <a href="javaScript:;" class="del J_delBatchBtn">删除</a>
                    </div>
                    <div id="J_pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>