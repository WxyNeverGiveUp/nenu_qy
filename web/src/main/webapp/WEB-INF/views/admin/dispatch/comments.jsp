<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wlm
  Date: 2016/10/24
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>办公信息</title>
    <jsp:include page="../../common/link.jsp" />
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/admin_page/students_management/officeInfo.css">
    <script type="text/javascript">
        KISSY.use('page/admin_page/students_management/officeInfo',function(S){
            S.ready(function(){
                PW.page.admin_page.students_management.officeInfo();
            })
        })
    </script>
</head>
<body>
<jsp:include page="../../common/header.jsp"/>
<jsp:include page="../../common/Asidebar.jsp"/>
<script type="text/javascript">
    KISSY.use('module/sidebar',function(S){
        S.ready(function(){
            PW.module.Sidebar();
        });
    });
</script>
<div class="hintBox">
    <c:if test="${!empty correctMsg}">
        <div class="correct-tip margin J_tip">${correctMsg}</div>
    </c:if>
    <c:if test="${!empty errMsg}">
        <div class="fault-tip margin J_tip">${errMsg}</div>
    </c:if>
</div>
<!-- 办公信息表单 -->

<form class="reportForm J_complete-form pw-validation" action="${website}admin/dispatch/comments" method="post">
    <div class="hiddenArea">
        <input type="hidden" name="id" value="${comments.id}" /><br/>
        <input type="hidden" name="statusInfoId" value="${comments.statusInfoId}" /><br/>
    </div>
    <h2 class="headTitle">办公信息</h2>
    <div class="tableWrapper">
        <table class="table1">
            <tr>
                <td>扩展1（领取协议）</td>
                <td><input type="radio" value="1" name="receiveAgreement" <c:if test="${comments.receiveAgreement eq 1}">checked</c:if>/> 是</td>
                <td><input type="radio" value="0" name="receiveAgreement" <c:if test="${comments.receiveAgreement eq 0}">checked</c:if>/> 否</td>
            </tr>
            <tr>
                <td>扩展2（返回协议）</td>
                <td><input type="radio" value="1" name="returnAgreement" <c:if test="${comments.returnAgreement eq 1}">checked</c:if>/> 是</td>
                <td><input type="radio" value="0" name="returnAgreement" <c:if test="${comments.returnAgreement eq 0}">checked</c:if>/> 否</td>
            </tr>
            <tr>
                <td>扩展3（已就业）</td>
                <td><input type="radio" value="1" name="alreadyEmployed" <c:if test="${comments.alreadyEmployed eq 1}">checked</c:if>/> 是</td>
                <td><input type="radio" value="0" name="alreadyEmployed" <c:if test="${comments.alreadyEmployed eq 0}">checked</c:if>/> 否</td>
            </tr>
            <tr>
                <td>扩展4（免师违约）</td>
                <td colspan=2>
                    <select class="selectBox" name="freeNorRelease">
                        <option value="-1">请选择</option>
                        <option value="1" <c:if test="${comments.freeNorRelease eq 1}">selected</c:if>>材料齐全</option>
                        <option value="0" <c:if test="${comments.freeNorRelease eq 0}">selected</c:if>>材料不全</option>
                    </select>
                </td>

            </tr>
            <tr>
                <td>扩展5（免师跨省）</td>
                <td colspan="2">
                    <select class="selectBox" name="freeNorTransProvincial">
                        <option value="-1">请选择</option>
                        <option value="1" <c:if test="${comments.freeNorTransProvincial eq 1}">selected</c:if>>材料齐全</option>
                        <option value="0" <c:if test="${comments.freeNorTransProvincial eq 0}">selected</c:if>>材料不全</option>
                    </select>
                </td>

            </tr>
            <tr>
                <td>扩展6（定向解约）</td>
                <td colspan="2">
                    <select class="selectBox" name="directionalRelease">
                        <option value="-1">请选择</option>
                        <option value="1" <c:if test="${comments.directionalRelease eq 1}">selected</c:if>>材料齐全</option>
                        <option value="0" <c:if test="${comments.directionalRelease eq 0}">selected</c:if>>材料不全</option>
                    </select>
                </td>

            </tr>
            <tr>
                <td>扩展7（春季签约）</td>
                <td colspan="2">
                    <input type="text" placeholder="春季签约时间" class="J_date spring" name="springDispatch" value="${comments.springDispatchDate}">
                </td>

            </tr>
        </table>
        <table class="table2">
            <tr>
                <td>扩展8（进京津沪深函）</td>
                <td colspan="2">
                    <select class="selectBox" name="beijingLetter">
                        <option value="-1">请选择</option>
                        <option value="1" <c:if test="${comments.beijingLetter eq 1}">selected</c:if>>材料齐全</option>
                        <option value="0" <c:if test="${comments.beijingLetter eq 0}">selected</c:if>>材料不全</option>
                    </select>
                </td>

            </tr>
            <tr>
                <td>扩展9（用人单位同意派至生源地函）</td>
                <td colspan="2">
                    <select class="selectBox" name="agreeToOriginPlace">
                        <option value="-1">请选择</option>
                        <option value="1" <c:if test="${comments.agreeToOriginPlace eq 1}">selected</c:if>>材料齐全</option>
                        <option value="0" <c:if test="${comments.agreeToOriginPlace eq 0}">selected</c:if>>材料不全</option>
                    </select>
                </td>

            </tr>
            <tr>
                <td>扩展10（解约）</td>
                <td colspan="2">
                    <select class="selectBox" name="threePartyRelease">
                        <option value="-1">请选择</option>
                        <option value="1" <c:if test="${comments.threePartyRelease eq 1}">selected</c:if>>材料齐全</option>
                        <option value="0" <c:if test="${comments.threePartyRelease eq 0}">selected</c:if>>材料不全</option>
                    </select>
                </td>

            </tr>
            <tr>
                <td>扩展11（协议丢失或污损）</td>
                <td colspan="2">
                    <select class="selectBox" name="protocolLostStained">
                        <option value="-1">请选择</option>
                        <option value="1" <c:if test="${comments.protocolLostStained eq 1}">selected</c:if>>材料齐全</option>
                        <option value="0" <c:if test="${comments.protocolLostStained eq 0}">selected</c:if>>材料不全</option>
                    </select>
                </td>

            </tr>
            <tr>
                <td>扩展12（系统上传）</td>
                <td colspan="2">
                    <select class="selectBox" name="systemUpload">
                        <option value="-1">请选择</option>
                        <option value="1" <c:if test="${comments.systemUpload eq 1}">selected</c:if>>材料齐全</option>
                        <option value="0" <c:if test="${comments.systemUpload eq 0}">selected</c:if>>材料不全</option>
                    </select>
                </td>

            </tr>
            <tr>
                <td>扩展13（资格审查漏报）</td>
                <td colspan="2">
                    <select class="selectBox" name="qualificationExaminationMissing">
                        <option value="-1">请选择</option>
                        <option value="1" <c:if test="${comments.qualificationExaminationMissing eq 1}">selected</c:if>>已上传</option>
                        <option value="0" <c:if test="${comments.qualificationExaminationMissing eq 0}">selected</c:if>>未上传</option>
                    </select>
                </td>

            </tr>
            <tr>
                <td>扩展14（协议领错）</td>
                <td colspan="2">
                    <select class="selectBox" name="protocolGetWrong">
                        <option value="-1">请选择</option>
                        <option value="1" <c:if test="${comments.protocolGetWrong eq 1}">selected</c:if>>材料齐全</option>
                        <option value="0" <c:if test="${comments.protocolGetWrong eq 0}">selected</c:if>>材料不全</option>
                    </select>
                </td>

            </tr>
        </table>
    </div>
    <!-- 报到证打印状态 -->
    <h2 class="headTitle">报到证打印状态</h2>
    <div class="reportWrapper">
        <div>
            <span>已打印报到证：${isPrinted}</span>
            <div></div>
        </div>
        <div class="reportNumber">
            报道证号: ${reportCard.number}<div></div>
            <p class="J_reportNum" >请填写正确的报到证号号</p>
        </div>
    </div>
    <!-- 按钮部分 -->
    <div>
        <div>
            <c:if test="${dispatchId ne 0}">
                <a href="${website}admin/dispatch/detail/${statusId}" class="dispatchInfo">签约信息</a>
            </c:if>
            <a href="${website}admin/status/info/detail/${statusId}" class="studentInfo">学籍信息</a>
        </div>
        <div class="submitWrapper">
            <input type="submit" class="submit-complete-form" value="提交"  />
        </div>
    </div>
</form>
</body>
</html>
