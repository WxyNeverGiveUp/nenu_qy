<%--
  User: hp
  Date: 2014/9/25
  Time: 11:30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--crumbs start-->
<div class="crumbs">
    <ul>
        <li class="home">
            <i></i>
            <a href="${website}">首页</a>
        </li>
        <c:if test="${modelType==1}">
        <li>
            <a href="javaScript:;" class="active">新增单位信息</a>
        </li>
        </c:if>
        <c:if test="${modelType==2}">
            <li>
                <a href="javaScript:;" class="active">联系人管理</a>
            </li>
        </c:if>
        <c:if test="${modelType==3}">
            <li>
                <a href="javaScript:;" class="active">单位信息管理</a>
            </li>
        </c:if>
        <c:if test="${modelType==4}">
            <li>
                <a href="javaScript:;" class="active">场地预订情况</a>
            </li>
        </c:if>
        <c:if test="${modelType==5}">
            <li>
                <a href="javaScript:;" class="active">招聘会日历</a>
            </li>
        </c:if>
        <c:if test="${modelType==6}">
            <li>
                <a href="javaScript:;" class="active">招聘信息管理</a>
            </li>
        </c:if>
        <c:if test="${modelType==7}">
            <li>
                <a href="javaScript:;" class="active">车辆管理</a>
            </li>
        </c:if>
        <c:if test="${modelType==8}">
            <li>
                <a href="javaScript:;" class="active">宾馆管理</a>
            </li>
        </c:if>
        <c:if test="${modelType==9}">
            <li>
                <a href="javaScript:;" class="active">宴请安排</a>
            </li>
        </c:if>
        <c:if test="${modelType==11}">
            <li>
                <a href="javaScript:;" class="active">消息提醒</a>
            </li>
        </c:if>
        <%--<c:if test="${modelType==12}">--%>
            <%--<li>--%>
                <%--<a href="javaScript:;" class="active">wiki</a>--%>
            <%--</li>--%>
        <%--</c:if>--%>
        <%--<c:if test="${modelType==13}">--%>
            <%--<li>--%>
                <%--<a href="javaScript:;" class="active">通讯录</a>--%>
            <%--</li>--%>
        <%--</c:if>--%>
        <c:if test="${modelType==14}">
            <li>
                <a href="javaScript:;" class="active">用户管理</a>
            </li>
        </c:if>
        <c:if test="${modelType==15}">
            <li>
                <a href="javaScript:;" class="active">场地管理</a>
            </li>
        </c:if>
        <c:if test="${modelType==16}">
            <li>
                <a href="javaScript:;" class="active">友情链接</a>
            </li>
        </c:if>
        <c:if test="${modelType==17}">
            <li>
                <a href="javaScript:;" class="active">单位详情</a>
            </li>
        </c:if>
        <c:if test="${modelType==18}">
            <li>
                <a href="javaScript:;" class="active">招聘会详情</a>
            </li>
        </c:if>
        <c:if test="${modelType==19}">
            <li>
                <a href="javaScript:;" class="active">添加招聘会</a>
            </li>
        </c:if>
        <c:if test="${modelType==20}">
            <li>
                <a href="javaScript:;" class="active">更新招聘会</a>
            </li>
        </c:if>
        <c:if test="${modelType==28}">
            <li>
                <a href="javaScript:;" class="active">修改单位信息</a>
            </li>
        </c:if>

        <c:if test="${modelType==30}">
            <li>
                <a href="javaScript:;" class="active">审核状态</a>
            </li>
        </c:if>
        <c:if test="${modelType==31}">
            <li>
                <a href="javaScript:;" class="active">学生列表</a>
            </li>
        </c:if>
        <c:if test="${modelType==32}">
            <li>
                <a href="javaScript:;" class="active">生源结构</a>
            </li>
        </c:if>
        <c:if test="${modelType==33}">
            <li>
                <a href="javaScript:;" class="active">就业率</a>
            </li>
        </c:if>
        <c:if test="${modelType==34}">
            <li>
                <a href="javaScript:;" class="active">拟就业率</a>
            </li>
        </c:if>
        <c:if test="${modelType==35}">
            <li>
                <a href="javaScript:;" class="active">就业结构</a>
            </li>
        </c:if>
        <c:if test="${modelType==36}">
            <li>
                <a href="javaScript:;" class="active">数据导入</a>
            </li>
        </c:if>
        <c:if test="${modelType==37}">
            <li>
                <a href="javaScript:;" class="active">导出上传</a>
            </li>
        </c:if>

        <c:if test="${modelType==38}">
            <li>
                <a href="javaScript:;" class="active">企划发展室</a>
            </li>
        </c:if>
        <c:if test="${modelType==39}">
            <li>
                <a href="javaScript:;" class="active">信息服务室</a>
            </li>
        </c:if>
        <c:if test="${modelType==40}">
            <li>
                <a href="javaScript:;" class="active">咨询指导室</a>
            </li>
        </c:if>
        <c:if test="${modelType==41}">
            <li>
                <a href="javaScript:;" class="active">综合办公室</a>
            </li>
        </c:if>
    </ul>
</div>
<!--crumbs end-->
