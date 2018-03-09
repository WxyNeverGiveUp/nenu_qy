<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>签约信息详情</title>
    <jsp:include page="../../common/link.jsp"/>
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/common/detail.css">
    <script type="text/javascript" src="${staticWebsite}upload/js/jquery-1.7.2.js"></script>

</head>
<body>
<jsp:include page="../../common/header.jsp"/>
<jsp:include page="../../common/Asidebar.jsp"/>
<div class="page">
    <script type="text/javascript">
        KISSY.use('module/sidebar', function (S) {
            S.ready(function () {
                PW.module.Sidebar();
            });
        });
    </script>
    <div class="content">
        <div class="infomation">
            <table>
                <caption>
                    个人信息
                </caption>
                <tr>
                    <td>姓名</td>
                    <td>${stuInfo.name}</td>
                    <td>性别</td>
                    <td>${stuInfo.sex}</td>
                    <td>学院</td>
                    <td>${stuInfo.college}</td>
                    <td>专业</td>
                    <td>${stuInfo.major}</td>
                </tr>
                <tr>
                    <td>身份证号</td>
                    <td>${stuInfo.idNumber}</td>
                    <td>学号</td>
                    <td>${stuInfo.studentNumber}</td>
                    <td>培养方式</td>
                    <td>${stuInfo.trainingMode}</td>
                    <td>师范生类别</td>
                    <td>${stuInfo.normalStu}</td>
                </tr>
                <tr>
                    <td>生源所在地</td>
                    <td>${stuInfo.originPlace}</td>
                    <td>定向委培单位</td>
                    <td>${stuInfo.weipeiUnit}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
            <table>
                <caption>
                    <span>${dh.name}同学</span>签约信息表
                    <br/>
                    协议编号：<span>${dh.agreementNumber}</span>
                </caption>
                <tr>
                    <td>毕业去向</td>
                    <td>${dh.whereaboutgo}</td>
                    <td>签约单位名称</td>
                    <td>${dh.companyName}</td>
                    <td>单位组织机构代码</td>
                    <td>${dh.organizationCode}</td>
                    <td>单位所在地</td>
                    <td>${dh.cityName}</td>
                </tr>
                <tr>
                    <td>单位性质</td>
                    <td>${dh.propertyName}</td>
                    <td>单位行业</td>
                    <td>${dh.tradeName}</td>
                    <td>单位隶属部门</td>
                    <td>${dh.subordinateDepartment}</td>
                    <td>工作职位类别</td>
                    <td>${dh.job}</td>
                </tr>
                <tr>
                    <td>报道证签发类别</td>
                    <td>${dh.reportMode}</td>
                    <td>报到证迁往单位名称</td>
                    <td>${dh.reportCompany}</td>
                    <td>报到证迁往单位所在地</td>
                    <td>${dh.reportAddressName}</td>
                    <td></td>
                    <td></td>
                    <%--<td>档案转寄单位</td>--%>
                    <%--<td>${dh.fileCompany}</td>--%>
                </tr>
                <%--<tr>--%>
                    <%--<td>档案转寄单位地址</td>--%>
                    <%--<td>${dh.fileCompanyAddressName}</td>--%>
                    <%--<td>档案转寄单位邮编</td>--%>
                    <%--<td>${dh.fileCompanyPostcode}</td>--%>
                    <%--<td>是否接受户口</td>--%>
                    <%--<td>--%>
                        <%--<c:choose>--%>
                            <%--<c:when test="${dh.acceptHukou==0}">--%>
                                <%--否--%>
                            <%--</c:when>--%>
                            <%--<c:when test="${dh.acceptHukou==1}">--%>
                                <%--是--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--是--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                    <%--</td>--%>
                    <%--<td>是否接受档案</td>--%>
                    <%--<td>--%>
                        <%--<c:choose>--%>
                            <%--<c:when test="${dh.acceptFile==0}">--%>
                                <%--否--%>
                            <%--</c:when>--%>
                            <%--<c:when test="${dh.acceptFile==1}">--%>
                                <%--是--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--是--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>
                    <td>户口签转地</td>
                    <td>${dh.hukouTransferAddress}</td>
                    <td>档案接收部门</td>
                    <td>${dh.acceptFileDepartment}</td>
                    <td>档案接收联系人</td>
                    <td>${dh.acceptFilePerson}</td>
                    <td>档案接收联系人电话</td>
                    <td>${dh.acceptFileTele}</td>
                </tr>--%>
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
                <%--<tr>
                    <td>联系人传真</td>
                    <td>${dh.contactPersonFax}</td>
                    <td>单位地址</td>
                    <td>${dh.fullAddress}</td>
                    <td>单位邮编</td>
                    <td>${dh.companyPostcode}</td>
                    <td>备注</td>
                    <c:choose>
                        <c:when test="${freeTeacherProvince != null}">
                            <td>免师跨省</td>
                            <td>${freeTeacherProvince}</td>
                        </c:when>
                        <c:otherwise>
                            <td colspan="2"></td>
                        </c:otherwise>
                    </c:choose>
                </tr>--%>
                <tr>
                    <td>备注</td>
                    <td colspan="7">${stuRemark}</td>
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
                    <td>操作人</td>
                    <td>操作</td>
                </tr>
                <tr id="counsellor">
                    <td class="check-level">辅导员</td>
                    <td class="check-result" value="weishenhe">
                        <c:choose>
                            <c:when test="${dh.counsellorCheckResult=='1' }">
                                未审核
                            </c:when>
                            <c:when test="${dh.counsellorCheckResult=='2' }">
                                审核通过
                            </c:when>
                            <c:when test="${dh.counsellorCheckResult=='3' }">
                                审核未通过待修改
                            </c:when>
                            <c:when test="${dh.counsellorCheckResult=='4' }">
                                审核未通过已修改
                            </c:when>
                        </c:choose>
                    </td>
                    <td>${dh.counsellorCheckReason}</td>
                    <c:choose>
                        <c:when test="${dh.checkStatus == 0}">
                            <td></td>
                        </c:when>
                        <c:otherwise>
                            <td><fmt:formatDate value="${dh.counsellorCheckTime}" pattern="yyyy-MM-dd"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td>${dh.counsellorCheckOperator}</td>
                    <td class="edit-result">
                        <shiro:hasAnyRoles name="admin">
                            <input type="button" value="修改结果" check-level="counsellor">
                        </shiro:hasAnyRoles>

                        <shiro:hasAnyRoles name="counsellor">
                            <c:if test="${canCheck == 1 && dh.counsellorCheckResult!='2'}">
                                <input type="button" value="审核" check-level="counsellor">
                            </c:if>
                            <c:if test="${canCheck == 0 || dh.counsellorCheckResult=='2'}">`
                                <span style="display:inline-block;background: gray; color:white;width: 100px;height: 25px;line-height: 25px;">审核</span>
                            </c:if>
                        </shiro:hasAnyRoles>
                    </td>
                </tr>
                <tr id="deputySecretary">
                    <td class="check-level">副书记</td>
                    <td class="check-result" value="weishenhe">
                        <c:choose>
                            <c:when test="${dh.deputySecretaryCheckResult=='1' }">
                                未审核
                            </c:when>
                            <c:when test="${dh.deputySecretaryCheckResult=='2' }">
                                审核通过
                            </c:when>
                            <c:when test="${dh.deputySecretaryCheckResult=='3' }">
                                审核未通过待修改
                            </c:when>
                            <c:when test="${dh.deputySecretaryCheckResult=='4' }">
                                审核未通过已修改
                            </c:when>
                        </c:choose>
                    </td>
                    <td>${dh.deputySecretaryCheckReason}</td>
                    <c:choose>
                        <c:when test="${dh.checkStatus == 0}">
                            <td></td>
                        </c:when>
                        <c:otherwise>
                            <td><fmt:formatDate value="${dh.deputySecretaryCheckTime}" pattern="yyyy-MM-dd"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td>${dh.deputySecretaryCheckOperator}</td>
                    <td class="edit-result">
                        <shiro:hasAnyRoles name="admin">
                            <input type="button" value="修改结果" check-level="deputySecretary">
                        </shiro:hasAnyRoles>

                        <c:if test="${dh.counsellorCheckResult=='2'}">
                            <shiro:hasAnyRoles name="college-secretary">
                                <input type="button" value="审核" check-level="deputySecretary">
                            </shiro:hasAnyRoles>
                        </c:if>
                    </td>
                </tr>

                <tr id="school">
                    <td class="check-level">学校</td>
                    <td class="check-result" value="weishenhe">
                        <%--<c:choose>--%>
                            <%--<c:when test="${dh.schoolCheckResult=='1' }">--%>
                                <%--未审核--%>
                            <%--</c:when>--%>
                            <%--<c:when test="${dh.schoolCheckResult=='2' }">--%>
                                <%--审核通过--%>
                            <%--</c:when>--%>
                            <%--<c:when test="${dh.schoolCheckResult=='3' }">--%>
                                <%--审核未通过待修改--%>
                            <%--</c:when>--%>
                            <%--<c:when test="${dh.schoolCheckResult=='4' }">--%>
                                <%--审核未通过已修改--%>
                            <%--</c:when>--%>
                        <%--</c:choose>--%>
                    </td>
                    <%--<td>${dh.schoolCheckReason}</td>--%>
                    <%--<td><fmt:formatDate value="${dh.schoolCheckTime}" pattern="yyyy-MM-dd"/></td>--%>
                    <%--<td>${dh.schoolCheckOperator}</td>--%>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td class="edit-result2">
                        <shiro:hasAnyRoles name="admin,employ">
                            <input type="button" value="打回" check-level="school">
                        </shiro:hasAnyRoles>
                    </td>
                </tr>

            </table>
        </div>
        <div>
            <table class="applied">
                <caption>
                    已申请业务项
                </caption>
                <tr>
                    <td>业务预约</td>
                    <td>申请原因</td>
                    <td>审核状态</td>
                    <td>时间</td>
                    <td>审核人</td>
                    <td>查看</td>
                </tr>

                <c:choose>
                    <c:when test="${protocolTypeName == '暂无该业务'}">
                        <tr>
                            <td>业务预约</td>
                            <td>暂无该业务</td>
                            <td>----</td>
                            <td>----</td>
                            <td>----</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${protocolList}" var="protocol">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${(protocol.protocolType>=2&&protocol.protocolType<=4)||protocol.protocolType==12}">
                                        申领新协议
                                    </c:when>
                                    <c:when test="${protocol.protocolType>=5&&protocol.protocolType<=6}">
                                        毕业去向变更领协议
                                    </c:when>
                                    <c:when test="${protocol.protocolType>=7&&protocol.protocolType<=8}">
                                        免费师范生业务
                                    </c:when>
                                    <c:when test="${protocol.protocolType>=9&&protocol.protocolType<=11}">
                                        定向委培业务
                                    </c:when>
                                    <c:otherwise>
                                        NULL
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${protocol.protocolType=='1' }">
                                        NULL
                                    </c:when>
                                    <c:when test="${protocol.protocolType=='2' }">
                                        协议污损
                                    </c:when>
                                    <c:when test="${protocol.protocolType=='3' }">
                                        协议丢失
                                    </c:when>
                                    <c:when test="${protocol.protocolType=='4' }">
                                        协议解约领新协议
                                    </c:when>
                                    <c:when test="${protocol.protocolType=='5'}">
                                        放弃考研/博领协议
                                    </c:when>
                                    <c:when test="${protocol.protocolType=='6'}">
                                        放弃出国/出境领协议
                                    </c:when>
                                    <c:when test="${protocol.protocolType=='7'}">
                                        免师解约
                                    </c:when>
                                    <c:when test="${protocol.protocolType=='8'}">
                                        免师跨省
                                    </c:when>
                                    <c:when test="${protocol.protocolType=='9'}">
                                        定向生领协议
                                    </c:when>
                                    <c:when test="${protocol.protocolType=='10'}">
                                        委培生解约领协议
                                    </c:when>
                                    <c:when test="${protocol.protocolType=='11'}">
                                        定向生解约
                                    </c:when>
                                    <c:when test="${protocol.protocolType=='12'}">
                                        博士研究生申请新协议
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${protocol.checkProtocolResult=='1' }">
                                        未审核
                                    </c:when>
                                    <c:when test="${protocol.checkProtocolResult=='2' }">
                                        审核通过
                                    </c:when>
                                    <c:when test="${protocol.checkProtocolResult=='3' }">
                                        审核未通过待修改
                                    </c:when>
                                    <c:when test="${protocol.checkProtocolResult=='4' }">
                                        审核未通过已修改
                                    </c:when>
                                    <c:otherwise>
                                        未审核
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><fmt:formatDate value="${protocol.lastModifiedTime}" pattern="yyyy-MM-dd"/></td>
                            <td>${protocol.checkProtocolOperator}</td>
                            <td><a href="${website}admin/protocol/new/detail/${protocol.id}">查看</a></td>
                        </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>





                <%--<c:choose>--%>
                    <%--<c:when test="${changeTypeName =='暂无该业务'}">--%>
                        <%--<tr>--%>
                            <%--<td>身份变更</td>--%>
                            <%--<td>暂无该业务</td>--%>
                            <%--<td>----</td>--%>
                            <%--<td>----</td>--%>
                            <%--<td>----</td>--%>
                        <%--</tr>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<c:forEach items="${changeList}" var="change">--%>
                            <%--<tr>--%>
                                <%--<td>身份变更</td>--%>
                                <%--<td>--%>
                                    <%--<c:choose>--%>
                                        <%--<c:when test="${change.changeType=='1' }">--%>
                                            <%--定向解约--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${change.changeType=='2' }">--%>
                                            <%--委培解约--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${change.changeType=='3' }">--%>
                                            <%--免师解约--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${change.changeType=='4' }">--%>
                                            <%--免师跨省--%>
                                        <%--</c:when>--%>
                                    <%--</c:choose>--%>
                                <%--</td>--%>
                                <%--<td>--%>
                                    <%--<c:choose>--%>
                                        <%--<c:when test="${change.checkChangeResult=='1' }">--%>
                                            <%--未审核--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${change.checkChangeResult=='2' }">--%>
                                            <%--审核通过--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${change.checkChangeResult=='3' }">--%>
                                            <%--审核未通过待修改--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${change.checkChangeResult=='4' }">--%>
                                            <%--审核未通过已修改--%>
                                        <%--</c:when>--%>
                                    <%--</c:choose>--%>
                                <%--</td>--%>
                                <%--<td><fmt:formatDate value="${change.lastModifiedTime}" pattern="yyyy-MM-dd"/></td>--%>
                                <%--<td>${change.checkChangeOperator}</td>--%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>



                <%--<c:choose>--%>
                    <%--<c:when test="${protocolTypeName =='暂无该业务'}">--%>
                        <%--<tr>--%>
                            <%--<td>申请新协议</td>--%>
                            <%--<td>暂无该业务</td>--%>
                            <%--<td>----</td>--%>
                            <%--<td>----</td>--%>
                            <%--<td>----</td>--%>
                        <%--</tr>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<c:forEach items="${protocolList}" var="protocol">--%>
                            <%--<tr>--%>
                                <%--<td>申请新协议</td>--%>
                                <%--<td>--%>
                                    <%--<c:choose>--%>
                                        <%--<c:when test="${protocol.protocolType=='1' }">--%>
                                            <%--协议污损--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${protocol.protocolType=='2' }">--%>
                                            <%--协议丢失--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${protocol.protocolType=='3' }">--%>
                                            <%--办理解约，申请新协议--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${protocol.protocolType=='4' }">--%>
                                            <%--因变更申请新协议--%>
                                        <%--</c:when>--%>
                                    <%--</c:choose>--%>
                                <%--</td>--%>
                                <%--<td>--%>
                                    <%--<c:choose>--%>
                                        <%--<c:when test="${protocol.checkProtocolResult=='1' }">--%>
                                            <%--未审核--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${protocol.checkProtocolResult=='2' }">--%>
                                            <%--审核通过--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${protocol.checkProtocolResult=='3' }">--%>
                                            <%--审核未通过待修改--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${protocol.checkProtocolResult=='4' }">--%>
                                            <%--审核未通过已修改--%>
                                        <%--</c:when>--%>
                                    <%--</c:choose>--%>
                                <%--</td>--%>
                                <%--<td><fmt:formatDate value="${protocol.lastModifiedTime}" pattern="yyyy-MM-dd"/></td>--%>
                                <%--<td>${protocol.checkProtocolOperator}</td>--%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>


            </table>
        </div>
        <div class="material clearfix">
            <strong class="material-show">签约材料</strong>
            <c:choose>
                <c:when test="${mt !=null && fn:length(mt) > 0}">
                    <ul class="material-pic clearfix">
                        <c:forEach var="m" items="${mt}">
                            <li>
                                <img src="${uploadWebsite}${m.materialUrl}">
                                <span>${m.materialName}</span>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="material-pic clearfix">
                            <li>
                                <span class="noPic">无</span>
                            </li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
        <shiro:hasAnyRoles name="admin, employ">
        <form action="${website}admin/dispatch/remark" method="post" class="note-message note-form" id="note-form">
            <div class="note-message">
                <span class="remarks" style="float:left">备注</span>
                <div class="remark-radio">
                    <p class="remarks">
                        <input type="hidden" name="stuId" value="${stuId}">
                        <input type="hidden" name="dispatchId" value="${dh.id}">
                        <input type="hidden" name="dispatchItemId" value="${dispatchExtendItem.id}">
                        <label>定向解约材料：</label>
                        <input type="radio" name="orientationCancelContract" value="1"
                               <c:if test="${dispatchExtendItem.orientationCancelContract == 1}">checked="checked"</c:if>>
                        <label>有</label>
                        <input type="radio" name="orientationCancelContract" value="0"
                               <c:if test="${dispatchExtendItem.orientationCancelContract == 0}">checked="checked"</c:if> class="radio-distance"/>
                        <label>无</label>
                    </p>
                    <p class="remarks">
                        <label>免师解约材料：</label>
                        <input type="radio" name="freeNormalCancelContract" value="1"
                               <c:if test="${dispatchExtendItem.freeNormalCancelContract == 1}">checked="checked"</c:if>>
                        <label>有</label>
                        <input type="radio" name="freeNormalCancelContract" value="0"
                               <c:if test="${dispatchExtendItem.freeNormalCancelContract == 0}">checked="checked"</c:if> class="radio-distance"/>
                        <label>无</label>
                    </p>
                    <p class="remarks">
                        <label>免师跨省材料：</label>
                        <input type="radio" name="freeNormalTransProvincial" value="1"
                               <c:if test="${dispatchExtendItem.freeNormalTransProvincial == 1}">checked="checked"</c:if>>
                        <label>有</label>
                        <input type="radio" name="freeNormalTransProvincial" value="0"
                               <c:if test="${dispatchExtendItem.freeNormalTransProvincial == 0}">checked="checked"</c:if> class="radio-distance"/>
                        <label>无</label>
                    </p>
                    <p class="remarks">
                        <label>进京函：</label>
                        <input type="radio" name="enterBeijing" value="1"
                               <c:if test="${dispatchExtendItem.enterBeijing == 1}">checked="checked"</c:if> class="area">
                        <label>有</label>
                        <input type="radio" name="enterBeijing" value="0"
                               <c:if test="${dispatchExtendItem.enterBeijing == 0}">checked="checked"</c:if> class="radio-distance"/>
                        <label>无</label>
                    </p>
                    <p class="remarks">
                        <label>进津函：</label>
                        <input type="radio" name="enterTianjin" value="1"
                               <c:if test="${dispatchExtendItem.enterTianjin == 1}">checked="checked"</c:if> class="area" />
                        <label>有</label>
                        <input type="radio" name="enterTianjin" value="0"
                               <c:if test="${dispatchExtendItem.enterTianjin == 0}">checked="checked"</c:if> class="radio-distance"/>
                        <label>无</label>
                    </p>
                    <p class="remarks">
                        <label>进沪函：</label>
                        <input type="radio" name="enterShanghai" value="1"
                               <c:if test="${dispatchExtendItem.enterShanghai == 1}">checked="checked"</c:if> class="area" />
                        <label>有</label>
                        <input type="radio" name="enterShanghai" value="0"
                               <c:if test="${dispatchExtendItem.enterShanghai == 0}">checked="checked"</c:if> class="radio-distance"/>
                        <label>无</label>
                    </p>
                </div>
                <div class="add-note">
                    <%--<div  class="add-remark" id="add-remark">添加更多备注<img src="${staticWebsite}img/admin_page/students_management/dispatch/add.jpg" alt="add.jpg" class="add-img"/></div>--%>
                    <div id="more-remarks" class="more-remarks">
                        <c:choose>
                            <c:when test="${dispatchAdminRemarkList.size() == 0}">
                                <input type="hidden" name="remarkId" value="-2">
                                <p class="remarks">
                                    <label>备注1：</label>
                                    <input type="text" name="remark" class="remark-input"/>
                                </p>
                                <p class="remarks">
                                    <label>备注2：</label>
                                    <input type="text" name="remark" class="remark-input"/>
                                </p>
                                <p class="remarks">
                                    <label>备注3：</label>
                                    <input type="text" name="remark" class="remark-input"/>
                                </p>
                                <p class="remarks">
                                    <label>备注4：</label>
                                    <input type="text" name="remark" class="remark-input"/>
                                </p>
                                <p class="remarks">
                                    <label>备注5：</label>
                                    <input type="text" name="remark" class="remark-input"/>
                                </p>
                                <p class="remarks">
                                    <label>备注6：</label>
                                    <input type="text" name="remark" class="remark-input"/>
                                </p>
                                <p class="remarks">
                                    <label>备注7：</label>
                                    <input type="text" name="remark" class="remark-input"/>
                                </p>
                                <p class="remarks">
                                    <label>备注8：</label>
                                    <input type="text" name="remark" class="remark-input"/>
                                </p>
                                <p class="remarks">
                                    <label>备注9：</label>
                                    <input type="text" name="remark" class="remark-input"/>
                                </p>
                                <p class="remarks">
                                    <label>备注10：</label>
                                    <input type="text" name="remark" class="remark-input"/>
                                </p>
                                <input type="submit" value="提交备注" class="submit-remark">
                            </c:when>
                            <c:when test="${dispatchAdminRemarkList.size() != 0}">
                                <c:forEach items="${dispatchAdminRemarkList}" var="item" varStatus="status">
                                    <p>
                                        备注${status.count}：<input type="hidden" name="remarkId" value="${item.id}">
                                        <input type="text" name="remark" class="remark-input" value="${item.remark}"/>
                                            <%--<a href="javascript:;" class="delete-input">--%>
                                            <%--<img src="${staticWebsite}img/admin_page/students_management/dispatch/delete.png" alt="delete.jpg" class="delete-img delete-input"/>删除--%>
                                            <%--</a>--%>
                                    </p>
                                </c:forEach>
                                <input type="submit" value="提交备注" class="submit-remark">
                            </c:when>
                        </c:choose>
                    </div>
                </div>
                <%--<div class="input-box">--%>
                    <%--<c:if test="${dispatchAdminRemarkList.size == 0}">--%>
                        <%--<input type="hidden" name="remarkId" value="-2">--%>
                    <%--</c:if>--%>
                    <%--<c:forEach items="${dispatchAdminRemarkList}" var="item" varStatus="status">--%>
                        <%--<p>--%>
                            <%--备注${status.count}：<input type="hidden" name="remarkId" value="${item.id}">--%>
                            <%--<input type="text" name="remark" class="remark-input" value="${item.remark}"/>--%>
                            <%--&lt;%&ndash;<a href="javascript:;" class="delete-input">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<img src="${staticWebsite}img/admin_page/students_management/dispatch/delete.png" alt="delete.jpg" class="delete-img delete-input"/>删除&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                        <%--</p>--%>
                    <%--</c:forEach>--%>
                <%--</div>--%>
            </div>
        </form>
        </shiro:hasAnyRoles>
        <%--<c:choose>--%>
            <%--<c:when test="${changeTypeName =='暂无该业务'}">--%>

            <%--</c:when>--%>
            <%--<c:otherwise>--%>
                <%--<div class="material clearfix">--%>
                    <%--<strong class="material-show">变更业务</strong>--%>
                    <%--<ul class="material-pic clearfix">--%>
                        <%--<c:forEach var="mcl" items="${materialChangeList}">--%>
                            <%--<li>--%>
                                <%--<img src="${uploadWebsite}${mcl.materialUrl}">--%>
                                <%--<span>${mcl.materialName}</span>--%>
                            <%--</li>--%>
                        <%--</c:forEach>--%>
                    <%--</ul>--%>
                <%--</div>--%>
            <%--</c:otherwise>--%>
        <%--</c:choose>--%>

        <%--<c:choose>--%>
            <%--<c:when test="${protocolTypeName =='暂无该业务'}">--%>

            <%--</c:when>--%>
            <%--<c:otherwise>--%>
                <%--<div class="material clearfix">--%>
                    <%--<strong class="material-show">协议业务</strong>--%>
                    <%--<ul class="material-pic clearfix">--%>
                        <%--<c:forEach var="mpl" items="${materialProtocolList}">--%>
                            <%--<li>--%>
                                <%--<img src="${uploadWebsite}${mpl.materialUrl}">--%>
                                <%--<span>${mpl.materialName}</span>--%>
                            <%--</li>--%>
                        <%--</c:forEach>--%>
                    <%--</ul>--%>
                <%--</div>--%>
            <%--</c:otherwise>--%>
        <%--</c:choose>--%>
        <div class="pop-check">
            <form action="javacsript:;" class="check">
                <fieldset class="clearfix" style="position:relative;">
                    <input type="hidden" name="stuId" value="${dh.statusInfoId}"/>
                    <input type="hidden" name="checkLevel"/>
                    <legend>审核状态</legend>
                    <p class="check-state-time">审核</p>
                    <span class="check-state">
							<strong>
                                审核状态
                            </strong>
							
							<input type="radio" name="checkResult" value="2" checked><label>通过</label>
							<input type="radio" name="checkResult" value="3"><label>不通过</label>
						</span>
                    <span class="check-reason">
							<strong>
                                审核理由
                            </strong>
							<select name="checkReason">
                                <option value="-1">请选择</option>
                                <option value="1" class="yes">信息属实</option>
                                <option value="2" class="not">修改师范生类型不通过，证明材料不充分</option>
                                <option value="3" class="not">修改生源地城市不通过，证明材料不充分</option>
                                <option value="4" class="not">修改委培方式不通过，证明材料不充分</option>
                                <option value="5" class="not">修改定向委培单位不通过，证明材料不充分</option>
                                <option value="6" class="not">个人信息有错误</option>
                                <option value="99">其他</option>
                            </select>
							<input type="text" name="otherReason" class="check-other-input">
						</span>

                    <div class="check-submit-line clearfix">
                        <input type="submit" value="确认修改" class="check-submit save-submit"/>
                        <input type="button" value="返回" class="check-submit back"/>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="pop-check2">
            <form action="javacsript:;" class="check2">
                <fieldset class="clearfix" style="position:relative;">
                    <input type="hidden" name="stuId" value="${dh.statusInfoId}"/>
                    <input type="hidden" name="checkLevel" />
                    <input type="hidden" name="checkResult" value="1"/>
                    <input type="hidden" name="checkReason" value="1"/>

                    <span class="check-state">
							<strong>
								确认打回吗？
							</strong>
						</span>
                    <div class="check-submit-line clearfix">
                        <input type="submit" value="确认修改" class="check-submit save-submit2">
                        <input type="button" value="返回" class="check-submit back">
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="pop-pic">
            <img src="">
            <a href="javascript:;">关闭</a>
        </div>
        <div class="more-detail">
            <%--<shiro:hasAnyRoles name="admin, director, employ">
                <a href="${website}admin/dispatch/comments/${dh.statusInfoId}">办公信息</a>
            </shiro:hasAnyRoles>--%>
            <a href="${website}admin/status/info/${dh.statusInfoId}">学籍信息</a>
            <c:if test="${statusInfoCache.get('id') ne '0'}">
                <c:choose>
                    <c:when test="${minority eq 0}">
                        <a href="${website}admin/dispatch/dtl/${statusInfoCache.get("id")}" class="next-student">下一个学生</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${website}admin/minority/dispatch/detail/${statusInfoCache.get("id")}" class="next-student">下一个学生</a>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
        <form class="parameter-hidden">
            <fieldset>
                <legend>为下一个学生设置的隐藏域</legend>
                <input type="hidden" name="id" value="${statusInfoCache.get("id")}" class="parameter"/>
            </fieldset>
        </form>
        <%--<form class="parameter-hidden">
            <fieldset>
                <legend>为下一个学生设置的隐藏域</legend>
                <input type="hidden" name="id" />
                <input type="hidden" name="curPage" />
                <input type="hidden" name="idList" />
                <input type="hidden" name="curNo" />
                <input type="hidden" name="conditions" />
            </fieldset>
        </form>--%>
    </div>
</div>
<script type="text/javascript">
    KISSY.use('page/admin_page/students_management/dispatch/infoDetail', function (S) {
        S.ready(function () {
            PW.page.admin_page.students_management.dispatch.infoDetail();
        })
    })
</script>
</body>
</html> 