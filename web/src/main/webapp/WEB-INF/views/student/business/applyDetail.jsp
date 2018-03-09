<%--
  Created by IntelliJ IDEA.
  User: Joiner-Axin
  Date: 2017/7/26
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 变更详情页 廖月云 -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>变更详情</title>
    <jsp:include page="../../common/link.jsp" />
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/common/detail.css">
    <style type="text/css">
        .box{
            width: 200px;
            height: 200px;
            background: #ccc;
        }
    </style>
</head>
<body>
<div class="page">
    <jsp:include page="../../common/header.jsp" />
    <!-- 侧栏start -->
    <jsp:include page="../../common/Ssidebar.jsp"/>
    <!-- 侧栏end -->
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
                    变更身份信息
                </caption>
                <tr>
                    <td>姓名</td>
                    <td>${stuStatusInfoDto.name}</td>
                    <td>身份证号</td>
                    <td>${stuStatusInfoDto.idNumber}</td>
                    <td>学号</td>
                    <td>${stuStatusInfoDto.studentNumber}</td>
                    <td>所在院系/分院</td>
                    <td>${stuStatusInfoDto.college}</td>
                </tr>
                <tr>
                    <td>所在年级</td>
                    <td>${stuStatusInfoDto.grade}</td>
                    <td>师范生类别</td>
                    <td>${stuStatusInfoDto.normalStu}</td>
                    <td>培养方式</td>
                    <td>${stuStatusInfoDto.trainingMode}</td>
                    <td>申请原因</td>
                    <td>${protocolStuDto.protocolType}<c:if test="${protocolStuDto.protocolType == '免师跨省' }">(${province})</c:if> </td>

                </tr>
                <tr>
                    <td>生源所在地</td>
                    <td>${stuStatusInfoDto.originPlaceImport}</td>
                    <td>生源地类别</td>
                    <td>${stuStatusInfoDto.originPlaceType}</td>
                    <td>手机号码1</td>
                    <td>${stuStatusInfoDto.cellphone}</td>
                    <td>手机号码（备用）</td>
                    <td>${stuStatusInfoDto.cellphoneBak}</td>
                </tr>
                <tr>
                    <td>定向委培单位地址</td>
                    <td colspan="3">${stuStatusInfoDto.weipeiUnitPlace}</td>
                    <td>定向委培单位</td>
                    <td colspan="3">${stuStatusInfoDto.weipeiUnit}</td>
                </tr>
                <tr>
                    <td>已有协议编号</td>
                    <td colspan="3">${agreementNumber}</td>
                    <td></td>
                    <td colspan="3"></td>
                </tr>
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
                    <td>审核理由</td>
                    <td>审核时间</td>
                    <td>操作人</td>
                    <td class="message">备注</td>
                </tr>
                <tr id="school">
                    <td class="check-level">学校</td>
                    <td class="check-result" value="weishenhe">${protocolStuDto.checkProtocolResult}</td>
                    <td>${protocolStuDto.checkProtocolReason}</td>
                    <td>${protocolStuDto.checkProtocolTime}</td>
                    <td>${protocolStuDto.checkProtocolOperator}</td>
                    <td class="message-td">${text}</td>
                </tr>
            </table>
        </div>

        <div class="material clearfix">
            <strong class="material-show">材料展示</strong>
            <ul class="material-pic clearfix">
                <c:forEach items="${materialList}" var="material">
                    <li>
                        <img src="${uploadWebsite}${material.materialUrl}" alt="">
                        <span title="${material.materialName}">${material.materialName}</span>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="pop-pic">
            <img src="">
            <a href="javascript:;">关闭</a>
        </div>
        <div class="more-detail">
            <a href="${website}student/business/toBusiness/${type}" class="next-student">返回上一页</a>
        </div>

    </div>
</div>

<script type="text/javascript">
    KISSY.use('page/admin_page/business_handling/changeDetail',function(S){
        S.ready(function(){
            PW.page.admin_page.business_handling.changeDetail();
        })
    })
</script>
<script type="text/javascript">
    KISSY.use('page/student_page/school_roll/writeInfo',function(S){
        S.ready(function(){
            PW.page.student_page.school_roll.writeInfo();
        });
    });
</script>
</body>
</html>
