<%--
  Created by IntelliJ IDEA.
  User: zhuoyin
  Date: 2016/7/13
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>修改学生申请原因</title>
    <jsp:include page="../../common/link.jsp"/>
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/student_page/business_handling/alterApply.css">
    <link rel="stylesheet" href="${staticWebsite}upload/css/zyupload-1.0.0.css " type="text/css">
    <script type="text/javascript" src="${staticWebsite}upload/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${staticWebsite}upload/js/zyupload-1.0.0.js"></script>
    <script type="text/javascript" src="${staticWebsite}js/module/dispatch/del.js"></script>
</head>
<body>
<jsp:include page="../../common/header.jsp" />
<!-- 侧栏start -->
<jsp:include page="../../common/Ssidebar.jsp"/>
<!-- 侧栏end -->
<div class="alterTotal">
    <c:if test="${!empty correctMsg}">
        <div class="correct-tip J_tip">${correctMsg}</div>
    </c:if>
    <c:if test="${!empty errMsg}">
        <div class="fault-tip J_tip">${errMsg}</div>
    </c:if>
    <form action="${website}student/business/updateBusiness/${type}" method="post">
        <input type="hidden" name="statusInfoId" value="${protocol.statusInfoId}"/>
        <input type="hidden" name="id" value="${protocol.id}"/>
        <!-- 申请原因start -->
        <div class="alterRea" style="height:260px;">
            <c:if test="${type == 1}">
                    <span>协议污损</span>
                    <input type="radio" name="protocolType" value="2"
                            <%--<c:if test="${protocol.protocolType == 2}"> checked="checked" </c:if>--%>
                    />是</br>
                    <span>协议丢失</span>
                    <input type="radio" name="protocolType" value="3"
                            <%--<c:if test="${protocol.protocolType == 3}"> checked="checked" </c:if>--%>
                    />是</br>
                    <span style="margin-right:94px;">协议解除领新协议</span>
                    <input type="radio" name="protocolType" value="4"
                            <%--<c:if test="${protocol.protocolType == 4}"> checked="checked" </c:if>--%>
                    />是</br>
            </c:if>
            <c:if test="${type == 2}">
                    <span>放弃考研/博领协议</span>
                    <input type="radio" name="protocolType" value="5"
                            <%--<c:if test="${protocol.protocolType == 5}"> checked="checked" </c:if>--%>
                           checked="checked"/>是</br>
                    <span>放弃出国出境领协议</span>
                    <input type="radio" name="protocolType" value="6"
                            <%--<c:if test="${protocol.protocolType == 6}"> checked="checked" </c:if>--%>
                    />是</br>
            </c:if>
            <c:if test="${type == 3}">
                    <span>免师解约</span>
                    <input type="radio" name="protocolType" value="7"
                    />是</br>
                    <span>免师跨省</span>
                    <input type="radio" name="protocolType" value="8"/>是</br>
                    <p class="freeProvince" style="display:none">
                        <span>跨入省份:</span>
                        <select class="otherCity" name="freeTeacherProvince">
                            <option value="1">请选择省份</option>
                            <option value="11">北京市</option>
                            <option value="12">天津市</option>
                            <option value="31">上海市</option>
                            <option value="50">重庆市</option>
                            <option value="13">河北省</option>
                            <option value="14">山西省</option>
                            <option value="71">台湾省</option>
                            <option value="21">辽宁省</option>
                            <option value="22">吉林省</option>
                            <option value="23">黑龙江省</option>
                            <option value="32">江苏省</option>
                            <option value="33">浙江省</option>
                            <option value="34">安徽省</option>
                            <option value="35">福建省</option>
                            <option value="36">江西省</option>
                            <option value="37">山东省</option>
                            <option value="41">河南省</option>
                            <option value="42">湖北省</option>
                            <option value="43">湖南省</option>
                            <option value="44">广东省</option>
                            <option value="62">甘肃省</option>
                            <option value="51">四川省</option>
                            <option value="52">贵州省</option>
                            <option value="46">海南省</option>
                            <option value="53">云南省</option>
                            <option value="63">青海省</option>
                            <option value="61">陕西省</option>
                            <option value="45">广西壮族自治区</option>
                            <option value="54">西藏自治区</option>
                            <option value="64">宁夏回族自治区</option>
                            <option value="65">新疆维吾尔自治区</option>
                            <option value="15">内蒙古自治区</option>
                            <option value="82">澳门特别行政区</option>
                            <option value="81">香港特别行政区</option>
                        </select>
                    </p>
            </c:if>
            <c:if test="${type == 4}">
                    <span>定向生领协议</span>
                    <input type="radio" name="protocolType" value="9"
                            <%--<c:if test="${protocol.protocolType == 9}"> checked="checked" </c:if>--%>
                    />是</br>
                    <span>委培生解约领协议</span>
                    <input type="radio" name="protocolType" value="10"
                            <%--<c:if test="${protocol.protocolType == 10}"> checked="checked" </c:if>--%>
                    />是</br>
                    <span>定向生解约</span>
                    <input type="radio" name="protocolType" value="11"
                            <%--<c:if test="${protocol.protocolType == 11}"> checked="checked" </c:if>--%>
                    />是</br>

            </c:if>
            <c:if test="${type == 5}">
                <%--<span>协议污损</span>--%>
                <%--<input type="radio" name="protocolType" value="2"--%>
                        <%--<c:if test="${protocol.protocolType == 2}"> checked="checked" </c:if>--%>
                <%--/>是</br>--%>
                <%--<span>协议丢失</span>--%>
                <%--<input type="radio" name="protocolType" value="3"--%>
                        <%--<c:if test="${protocol.protocolType == 3}"> checked="checked" </c:if>--%>
                <%--/>是</br>--%>
                <%--<span>协议解除领新协议</span>--%>
                <%--<input type="radio" name="protocolType" value="4"--%>
                        <%--<c:if test="${protocol.protocolType == 4}"> checked="checked" </c:if>--%>
                <%--/>是</br>--%>
                <%--<span>放弃出国出境领协议</span>--%>
                <%--<input type="radio" name="protocolType" value="6"--%>
                        <%--<c:if test="${protocol.protocolType == 6}"> checked="checked" </c:if>--%>
                <%--/>是</br>--%>
                <%--<span>定向生领协议</span>--%>
                <%--<input type="radio" name="protocolType" value="9" checked="checked"--%>
                        <%--<c:if test="${protocol.protocolType == 9}"> checked="checked" </c:if>--%>
                <%--/>是</br>--%>
                <span>申领协议</span>
                <input type="radio" name="protocolType" value="12"
                        <%--<c:if test="${protocol.protocolType == 12}"> checked="checked" </c:if>--%>
                />是</br>
            </c:if>

    </div>
        <!--申请原因end -->
        <!-- 上传文件start -->
        <div class="block-content clearfix">
            <ul class="clearfix material">
                <c:forEach items="${materialList}" var = "item">
                    <li>
                        <img src="${uploadWebsite}${item.materialUrl}"
                             alt="${item.materialName}"
                             class="J_thumbnail uploaded-img" data-id="${item.id}" />
                        <input type="text" style="display:none; width:136px;" value = "${item.materialName}">
                        <a href="javascript:;" class="delete"></a>
                    </li>
                </c:forEach>
            </ul>
            <div id="zyupload" class="zyupload"></div>
        </div>
        <!-- 上传文件end -->
        <!-- 提示框内容start -->
        <div class="scene none"></div>
        <div class="hint-box change none">
            <div class="hint-info clearfix">提示信息
                <span class="close">X</span>
            </div>
            <div class="msg_box msg-box">
                <p class="msg"></p>
                <p class="hint1"></p>
                <p class="hint2"></p>
                <p class="hint3"></p>
            </div>

            <span class="close-button close">关闭</span>
        </div>
        <div class="delete-box  none">
            <div class="delete-info clearfix">
                <p>确认要删除此图片吗？</p>
                <span class="close">X</span>
            </div>
            <span class="close-button confirm">确认</span>
            <span class="close-button close">取消</span>
        </div>
        <div class="pop-pic none J_pop_pic">
            <img src=""/>
            <a href="javascript:;" class="J_shut">关闭</a>
        </div>
        <!-- 提示框内容end -->
        <input class="alterIn" value="确认提交" type="submit" id="alterbtn"/>
    </form>
    <div class="resub">
        <p>所需上传材料:</p>
        <span></span>
        <%--<c:if test="${type == 5}">--%>
            <%--<span>--%>
                <%--（1）招生大表（在档案馆领取）<br />--%>
                <%--（2）学院开具的可毕业证明（须有导师、本人签字、学院加盖公章）或毕业证学位证复印件--%>
            <%--</span>--%>
        <%--</c:if>--%>
    </div>
</div>
<script type="text/javascript">
    KISSY.use('module/sidebar',function(S){
        S.ready(function(){
            PW.module.Sidebar();
        });
    });
    KISSY.use('page/student_page/business_handling/alterApply',function(S){
        S.ready(function(){
            PW.page.student_page.business_handling.alterApply();
        })
    });
</script>
<script>
    $(function(){
        // 初始化插件
        $("#zyupload").zyUpload({
            width            :   "100%",                 // 宽度
            height           :   "auto",                 // 宽度
            itemWidth        :   "140px",                 // 文件项的宽度
            itemHeight       :   "115px",                 // 文件项的高度
            url              :   "${website}student/business/ajax/upload/${type+3}",  // 上传文件的路径
            fileType         :   ["jpg","png","jpeg","bmp"],// 上传文件的类型
            fileSize         :   5120000,                // 上传文件的大小
            multiple         :   true,                    // 是否可以多个文件上传
            dragDrop         :   true,                    // 是否可以拖动上传文件
            tailor           :   true,                    // 是否可以裁剪图片
            del              :   true,                    // 是否可以删除文件
            finishDel        :   false,  				  // 是否在上传文件完成后删除预览
            /* 外部获得的回调接口 */
            onSelect: function(selectFiles, allFiles){    // 选择文件的回调方法  selectFile:当前选中的文件  allFiles:还没上传的全部文件
                console.info("当前选择了以下文件：");
                console.info(selectFiles);
            },
            onDelete: function(file, files){              // 删除一个文件的回调方法 file:当前删除的文件  files:删除之后的文件
                console.info("当前删除了此文件：");
                console.info(file.name);
            },
            onSuccess: function(file, response){          // 文件上传成功的回调方法
                console.info("此文件上传成功：");
                console.info(file.name);
                console.info("此文件上传到服务器地址：");
                console.info(response);
                $("#uploadInf").append("<p>材料上传成功!</p>");
            },
            onFailure: function(file, response){          // 文件上传失败的回调方法
                console.info("此文件上传失败：");
                console.info(file.name);
            },
            onComplete: function(response){           	  // 上传完成的回调方法
                console.info("文件上传完成");
                console.info(response);
            }
        });

    });
</script>
<!-- 控制跨入省份消失显示 -->
<script type="text/javascript">
    $(".alterRea input").click(function(){
        var type = $(this).val();
        if(type==2){
            $(".resub span").html("(1)《协议丢失、污损个人申请表》（辅导员、学院副书记签字并加盖学院就业专用章）<br/>(2)四联污损协议");
        } else if(type==3){
            $(".resub span").html("(1)《协议丢失、污损个人申请表》（辅导员、学院副书记签字并加盖学院就业专用章）<br>(2)学院开具的就业协议丢失公示期满证明");
        } else if(type==4) {
            $(".resub span").html("(1)解约函或未录用证明（须有单位公章）<br/>(2)违约金收据（若无违约金须在解约函中标明）<br/>(3)《违约个人申请表》（辅导员、学院副书记签字并加盖学院就业专用章）<br/>(4)原四联协议");
        } else if(type==5){
            $(".resub span").html("《放弃升学新领协议申请表》（须有本人及辅导员、副书记签字以及学院盖章）");
        } else if(type==6){
            $(".resub span").html("《放弃出国（境）新领协议申请表》（须有本人及辅导员、副书记签字以及学院盖章）");
        } else if(type==7){
            $(".freeProvince").css("display","none");
            $(".resub span").html("(1)生源省教育厅开具的解约函<br/>(2)违约金收据复印件<br/>(3)就业协议");
        } else if(type==8){
            $(".resub span").html("(1)跨省就业审批表（在生源省教育厅官网下载并按照申请表上要求盖章）<br/>(2)就业协议")
            $(".freeProvince").css("display","block");
        } else if(type==9) {
            $(".resub span").html("定向协议复印件");
        } else if(type==10){
            $(".resub span").html("(1)委培协议书复印件<br/>(2)解除委培证明、离职证明或与原单位解除劳动关系证明<br/>(3)违约金收据复印件");
        } else if(type==11){
            $(".resub span").html("(1)定向协议复印件<br/>(2)解除定向证明材料<br/>(3)违约金收据复印件");
        }
        else {
            $(".freeProvince").css("display","none");
            $(".resub span").html("(1)招生大表（在档案馆领取）<br/>(2)学院开具的可毕业证明（须有导师、本人签字、学院加盖公章）");
        }
    });
    $(".applyIn").click(function(){
        var type = $("input[name=protocolType]:checked").val();
        var province = $(".otherCity option:selected").val();
        if(province==1 && type==8){
            $(".otherCity").addClass("border");
            return false;
        }
    })
</script>
</body>
</html>
