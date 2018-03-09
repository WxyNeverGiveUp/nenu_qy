<%--
  Created by IntelliJ IDEA.
  User: wlm
  Date: 2016/8/30
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- sidebar start-->
<!-- 管理员页面 -->
<div class="sidebar">
    <ul>
        <li class="list">
            <a href="javaScript:;">
                <i class="data"></i>
                <span>就业数据统计</span>
                <b></b>
            </a>
            <ul class="submenu none">
                <li>
                    <a href="${website}admin/statistics/qualification/0">实时统计</a>
                </li>
                <shiro:hasAnyRoles name="admin">
                    <li>
                        <a href="javascript:;">多维分析</a>
                    </li>
                </shiro:hasAnyRoles>
            </ul>
        </li>
        <li class="list">
            <a href="javaScript:;">
                <i class="data"></i>
                <span>学籍信息管理</span>
                <b></b>
            </a>
            <ul class="submenu none">
                <shiro:hasAnyRoles name="admin,college-secretary,counsellor">
                <li>
                    <a href="${website}admin/status/info/new">新增学生</a>
                </li>
                </shiro:hasAnyRoles>
                <li>
                    <a href="${website}admin/status/info/list">学生学籍列表</a>
                </li>
            </ul>
        </li>
        <shiro:hasAnyRoles name="admin,director,college-secretary,counsellor,employ">
            <li class="list">
                <a href="javaScript:;">
                    <i class="dispatch"></i>
                    <span>签约信息管理</span>
                    <b></b>
                </a>
                <ul class="submenu none">
                    <li>
                        <a href="${website}admin/dispatch/list">学生列表</a>
                    </li>
                </ul>
            </li>
        </shiro:hasAnyRoles>

        <shiro:hasAnyRoles name="admin, director">
            <li class="list">
                <a href="javaScript:;">
                    <i class="minority"></i>
                    <span>少数民族专区</span>
                    <b></b>
                </a>
                <ul class="submenu none">
                    <li>
                        <a href="${website}admin/minority/status/info/list">学籍信息列表</a>
                    </li>
                    <li>
                        <a href="${website}admin/minority/dispatch/list">签约信息列表</a>
                    </li>
                </ul>
            </li>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name="admin, director, employ">


            <li class="list">
                <a href="javaScript:;">
                    <i class="business"></i>
                    <span>业务预约</span>
                    <b></b>
                </a>
                <ul class="submenu none">
                    <li>
                        <a href="${website}admin/protocol/new/toNewProtocolList">申领新协议</a>
                    </li>
                    <li>
                        <a href="${website}admin/protocol/new/toWhereaboutsgoChangeList">毕业去向变更领协议</a>
                    </li>
                    <li>
                        <a href="${website}admin/protocol/new/toNormalBusinessList">免费师范生业务</a>
                    </li>
                    <li>
                        <a href="${website}admin/protocol/new/toWeipeiBusinessList">定向、委培业务</a>
                    </li>
                    <li>
                        <a href="${website}admin/protocol/new/toDoctorChangeList">博士生领协议业务</a>
                    </li>
                </ul>
            </li>
        </shiro:hasAnyRoles>
            <li class="list">
                <a href="javaScript:;">
                    <i class="business"></i>
                    <span>往届就业去向</span>
                    <b></b>
                </a>
                <ul class="submenu none">
                    <li>
                        <a href="${website}admin/history/data/list">往届就业去向</a>
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
                    <a href="${staticWebsite}../download/shiyongshouce.doc">东北师范大学网上签约管理系统使用手册</a>
                </li>
                <li>
                    <a href="${staticWebsite}../download/liuchengtu.docx">东北师范大学网上签约服务流程图</a>
                </li>
            </ul>
        </li>
        <li class="list">
            <a href="javaScript:;">
                <i class="system"></i>
                <span>系统管理</span>
                <b></b>
            </a>
            <ul class="submenu none">
                <shiro:hasAnyRoles name="admin">
                    <li>
                        <a href="${website}admin/import">导入初始数据</a>
                    </li>
                    <%--<li>--%>
                    <%--<a href="#">系统开放时间</a>--%>
                    <%--</li>--%>
                    <li>
                        <a href="${website}admin/user/toUserList">用户管理</a>
                    </li>
                    <li>
                        <a href="${website}admin/export/employment">导出就业意向调查</a>
                    </li>
                </shiro:hasAnyRoles>

                <shiro:hasAnyRoles name="counsellor">
                    <li>
                        <a href="${website}admin/user/toFindCounsellorUser">查看个人信息</a>
                    </li>
                </shiro:hasAnyRoles>
                <li>
                    <a href="${website}admin/user/update/pwd">修改密码</a>
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
