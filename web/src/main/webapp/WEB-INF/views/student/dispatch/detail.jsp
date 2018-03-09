<%--学生的派遣详情页 廖月云--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>查看签约信息</title>
    <jsp:include page="../../common/link.jsp" />
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/common/detail.css">
</head>
<body>
<div class="page">
    <jsp:include page="../../common/header.jsp" />
    <jsp:include page="../../common/Ssidebar.jsp"/>
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
                    签约信息表
                    <br/>
                    协议编号：<span>${dispatchInfo.agreementNumber}</span>
                </caption>
                <tr>
                    <td>毕业去向</td>
                    <td>${dispatchInfo.whereaboutgo}</td>
                    <td>签约单位名称</td>
                    <td>${dispatchInfo.companyName}</td>
                    <td>单位组织机构代码</td>
                    <td>${dispatchInfo.organizationCode}</td>
                    <td>单位所在地</td>
                    <td>${dispatchInfo.cityName}</td>
                </tr>
                <tr>
                    <td>单位性质</td>
                    <td>${dispatchInfo.propertyName}</td>
                    <td>单位行业</td>
                    <td>${dispatchInfo.tradeName}</td>
                    <td>单位隶属部门</td>
                    <td>${dispatchInfo.subordinateDepartment}</td>
                    <td>工作职位类别</td>
                    <td>${dispatchInfo.job}</td>
                </tr>
                <tr>
                    <td>报道证签发类别</td>
                    <td>${dispatchInfo.reportMode}</td>
                    <td>报到证迁往单位名称</td>
                    <td>${dispatchInfo.reportCompany}</td>
                    <td>报到证迁往单位所在地</td>
                    <td>${dispatchInfo.reportAddressName}</td>
                    <td></td>
                    <td></td>
                    <%--<td>档案转寄单位</td>--%>
                    <%--<td>${dispatchInfo.fileCompany}</td>--%>
                </tr>
                <%--<tr>--%>
                    <%--<td>档案转寄单位地址</td>--%>
                    <%--<td>${dispatchInfo.fileAddressName}</td>--%>
                    <%--<td>档案转寄单位邮编</td>--%>
                    <%--<td>${dispatchInfo.fileCompanyPostcode}</td>--%>
                    <%--<td>是否接受户口</td>--%>
                    <%--<td>${acceptHukou}</td>--%>
                    <%--<td>是否接受档案</td>--%>
                    <%--<td>${acceptFile}</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td>户口签转地</td>--%>
                    <%--<td>${dispatchInfo.hukouTransferAddress}</td>--%>
                    <%--<td>档案接收部门</td>--%>
                    <%--<td>${dispatchInfo.acceptFileDepartment}</td>--%>
                    <%--<td>档案接收联系人</td>--%>
                    <%--<td>${dispatchInfo.acceptFilePerson}</td>--%>
                    <%--<td>档案接收联系人电话</td>--%>
                    <%--<td>${dispatchInfo.acceptFileTele}</td>--%>
                <%--</tr>--%>
                <tr>
                    <td>单位联系人</td>
                    <td>${dispatchInfo.companyContactPerson}</td>
                    <td>联系人电话</td>
                    <td>${dispatchInfo.contactPersonTele}</td>
                    <td>联系人手机号码</td>
                    <td>${dispatchInfo.contactPersonMobile}</td>
                    <td>联系人电子邮箱</td>
                    <td>${dispatchInfo.contactPersonEmail}</td>
                </tr>
                <tr>
                    <td>联系人传真</td>
                    <td>${dispatchInfo.contactPersonFax}</td>
                    <td>单位地址</td>
                    <td>${dispatchInfo.fullAddress}</td>
                    <td>单位邮编</td>
                    <td>${dispatchInfo.companyPostcode}</td>
                    <c:choose>
                        <c:when test="${freeTeacherProvince != null}">
                    <td>免师跨省</td>
                    <td>${freeTeacherProvince}</td>
                        </c:when>
                        <c:otherwise>
                            <td colspan="2"></td>
                        </c:otherwise>
                    </c:choose>
                    <%--<td>备注</td>--%>
                    <%--<td>${remarks}</td>--%>
                </tr>
                <tr>
                    <td>备注</td>
                    <td colspan="7">${dispatchInfo.stuRemark}</td>
                </tr>
            </table>
        </div>
        <div>
            <table>
                <caption>
                    参考项修改信息详情
                </caption>
                <tr>
                    <td>序号</td>
                    <td>修改项</td>
                    <td>原信息</td>
                    <td>改后信息</td>
                    <td>修改时间</td>
                </tr>
                <c:forEach items="${dispatchUpdateDtoList}" var="updateInfo" varStatus="no">
                    <tr>
                        <td>${no.count}</td>
                        <td>${updateInfo.updateType}</td>
                        <td>${updateInfo.beforeUpdate}</td>
                        <td>${updateInfo.afterUpdate}</td>
                        <td>${updateInfo.updateTime}</td>
                    </tr>
                </c:forEach>
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
                </tr>
                <tr id="counsellor">
                    <td class="check-level">学院</td>
                    <td>${counsellorCheckResult}</td>
                    <td>${dispatchInfo.counsellorCheckReason}</td>
                    <td>${counsellorCheck}</td>
                </tr>
                <tr id="deputySecretary">
                    <td class="check-level">副书记</td>
                    <td>${deputySecretaryCheckResult}</td>
                    <td>${dispatchInfo.deputySecretaryCheckReason}</td>
                    <td>${deputySecretaryCheck}</td>
                </tr>
                <%--<tr id="school">--%>
                    <%--<td class="check-level">学校</td>--%>
                    <%--<td>${schoolCheckResult}</td>--%>
                    <%--<td>${dispatchInfo.schoolCheckReason}</td>--%>
                    <%--<td>${schoolCheck}</td>--%>
                <%--</tr>--%>
            </table>
        </div>
        <div class="material clearfix">
            <strong class="material-show">材料展示</strong>
            <ul class="material-pic clearfix">
                <c:forEach var = "item" items="${list}">
                    <li>
                        <img src="${uploadWebsite}${item.materialUrl}">
                        <span>${item.materialName}</span>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="confirm">
            <c:if test="${checkStatus == 0 or checkStatus == 1 or counsellor == 3 or deputy == 3 or school == 3 }">
                <a href = "${webSite}/student/dispatch/update">返回修改</a>
                <a href = "${webSite}/student/dispatch/alterstatus">确认提交</a>
            </c:if>
        </div>
        <div class="pop-pic">
            <img src="">
            <a href="javascript:;">关闭</a>
        </div>
    </div>
</div>
<script type="text/javascript">
    KISSY.use('page/student_page/dispatch/infoDetail',function(S){
        S.ready(function(){
            PW.page.student_page.dispatch.infoDetail();
        })
    })
</script>
</body>
</html>