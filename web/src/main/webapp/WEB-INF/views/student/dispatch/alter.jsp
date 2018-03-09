<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 学生管理页面签约信息修改页 -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改签约信息</title>
    <jsp:include page="../../common/link.jsp" />
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/student_page/dispatch/alterInfo.css">
    <link rel="stylesheet" href="${staticWebsite}upload/css/zyupload-1.0.0.css " type="text/css">
    <script type="text/javascript" src="${staticWebsite}upload/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${staticWebsite}upload/js/zyupload-1.0.0.js"></script>
    <script type="text/javascript" src="${staticWebsite}js/module/dispatch/del.js"></script>
    <script type="text/javascript" src="${staticWebsite}js/module/dispatch/whereaboutgoLinkage.js"></script>
</head>
<body>
<!-- 头部start -->
<!--#include file="/nenu_jysj_front/pages/common/header.html"  -->
<!-- 头部end -->
<!-- 主体内容start -->
<jsp:include page="../../common/header.jsp"/>
<div class="content">
    <!-- 侧栏start -->
    <jsp:include page="../../common/Ssidebar.jsp"/>
    <script type="text/javascript">
        KISSY.use('module/sidebar',function(S){
            S.ready(function(){
                PW.module.Sidebar();
            });
        });
    </script>
    <%--<div class="sidebar"></div>--%>
    <!-- 侧栏end -->
    <!-- 表单start -->
    <div class="remind">
    <c:choose>
        <c:when test="${stuDeputySecretaryCheckResult == 2 and stuCounsellorCheckResult == 2}">
            <p> 请同学们如实填写以下信息。红色字体为必填项，黑色字体为选填项。</p>
        </c:when>
        <c:otherwise>
            <p>学籍审核未完成或未通过，不能进行签约操作，请在学籍详情页面查看审核状态，联系辅导员或副书记进行修改。</p>
        </c:otherwise>
    </c:choose>
        <p>你的协议编号为：<span>${dispatchInfo.agreementNumber}</span></p>
    </div>
    <form action="${website}student/dispatch/update" id="form" class="block" method="post">
        <input type="text" name="id" value="${dispatchInfo.id}" class="none school-roll1"/>
        <input type="text" name="statusInfoId" value="${dispatchInfo.statusInfoId}" class="none school-roll2"/>
        <!-- 就业信息表单end -->
        <h1>就业信息</h1>
        <!-- 签约信息表单start -->
        <div class="block-content clearfix officeMsg">
            <div class="control-area control-area-short required">
                <label for="">毕业去向</label>
                <select name="whereaboutgoId"  class="textTheme  J_notOption" id="whereaboutgoId">
                    <c:forEach var = "item" items="${whereaboutgos}">
                        <option value="${item.whereAboutGoId}"<c:if test="${item.whereAboutGoId eq dispatchInfo.whereaboutgoId}">selected</c:if>>${item.whereAboutGo}</option>
                    </c:forEach>
                </select>
                <!-- <a class="tip hint graduation-hint" href="javascript:;">提示</a> -->
            </div>
            <div class="control-area control-area-short control-area1">
                <label for="">签约单位名称</label>
                <input type="text" name="companyName" value="${dispatchInfo.companyName}" data-valid-rule="notNull"
                       class="textTheme"/>
                <!-- <a class="tip hint unit-hint" href="javascript:;">提示</a> -->
            </div>
            <div class="control-area control-area-short control-area2">
                <label for="">单位组织机构代码</label>
                <input type="text" name="organizationCode" value="${dispatchInfo.organizationCode}" data-valid-rule="notNull" class="textTheme"/>
                <!-- <a class="tip hint code-hint" href="javascript:;">提示</a> -->
            </div>
            <div class="control-area control-area-short control-area3">
                <label for="">单位所在地</label>
                <%--<input class="area-id-unit" type="hidden" name="cityId">--%>
                <input id="J_unitAreaHolder" name="cityName" value="${dispatchInfo.cityName}" class="textTheme J_areaHolder"
                       type="text" autocomplete="off" data-valid-rule="notNull"/>
                <ul class="none areaFidle" id="J_areaFidle_unit"></ul>
                <p></p>
            </div>
            <div class="control-area control-area-short">
                <label for="">地区代码</label>
                <input type="text" name="cityId" value="${dispatchInfo.cityId}" class="unit-code J_unit_code textTheme"
                       onfocus="this.blur()" data-valid-rule="notNull" />
            </div>
            <div class="control-area control-area-short control-area5">
                <label for="">单位性质</label>
                <select name="propertyId" data-valid-rule="scale(0,1000,0)" class="textTheme">
                    <c:forEach var = "item" items="${companyPropertyCodes}">
                        <option value="${item.propertyCode}"<c:if test="${item.propertyCode eq dispatchInfo.propertyId}">selected</c:if>>${item.propertyName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="control-area control-area-short control-area6">
                <label for="">单位行业</label>
                <select name="tradeId" data-valid-rule="scale(0,1000,0)" class="textTheme">
                    <c:forEach var = "item" items="${companyTradeCodes}">
                        <option value="${item.tradeId}"<c:if test="${item.tradeId eq dispatchInfo.tradeId}">selected</c:if>>${item.tradeName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="control-area control-area-short">
                <label for="">单位隶属部门</label>
                <input type="text" name="subordinateDepartment" value="${dispatchInfo.subordinateDepartment}" class="textTheme"/>
            </div>
            <div class="control-area control-area-short control-area8">
                <label for="">工作职位类别</label>
                <select name="jobId" data-valid-rule="scale(0,1000,0)" class="textTheme">
                    <c:forEach var = "item" items="${jobCodes}">
                        <option value="${item.jobId}"<c:if test="${item.jobId eq dispatchInfo.jobId}">selected</c:if>>${item.job}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="control-area control-area-short">
                <label for="">单位地址</label>
                <input type="text" name="fullAddress" value="${dispatchInfo.fullAddress}" class="textTheme"/>
            </div>
            <div class="control-area control-area-short postcode-one clear">
                <label for="">单位邮编</label>
                <input type="text" name="companyPostcode" value="${dispatchInfo.companyPostcode}" class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
            <div class="control-area control-area-short">
                <label for="">单位联系人</label>
                <input type="text" name="companyContactPerson" value="${dispatchInfo.companyContactPerson}" class="textTheme"/>
            </div>
            <div class="control-area control-area-short fax clear">
                <label for="">单位联系人传真</label>
                <input type="text" name="contactPersonFax" value="${dispatchInfo.contactPersonFax}" class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
            <div class="control-area control-area-short clear phone-one">
                <label for="">单位联系人电话</label>
                <input type="text" name="contactPersonTele" value="${dispatchInfo.contactPersonTele}" class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
            <div class="control-area control-area-short clear mobile-phone">
                <label for="">单位联系人手机</label>
                <input type="text" name="contactPersonMobile" value="${dispatchInfo.contactPersonMobile}" class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
            <div class="control-area control-area-short email clear">
                <label for="">单位联系人电子邮箱</label>
                <input type="text" name="contactPersonEmail" value="${dispatchInfo.contactPersonEmail}" class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
        </div>
        <!-- 签约信息表单end -->
        <h1>派遣信息</h1>
        <!-- 就业信息表单start -->
        <div class="block-content jobMsg clearfix">
            <div class="control-area control-area-short required">
                <label for="">报到证发放类别</label>
                <select id="reportModeId" name="reportModeId"  class="textTheme certificateKind J_notOption">
                    <c:forEach var = "item" items="${reportCodes}">
                        <option value="${item.reportModeId}"<c:if test="${item.reportModeId eq dispatchInfo.reportModeId}">selected</c:if>>${item.reportMode}</option>
                    </c:forEach>
                </select>
                <!-- <a class="tip hint type-hint" href="javascript:;">提示</a> -->
            </div>

            <div class="control-area control-area-short">
                <label for="">报到证迁往单位名称</label>
                <input type="text" name="reportCompany" value="${dispatchInfo.reportCompany}" data-valid-rule="notNull"
                       class="textTheme" id="J_localAreaHolderName" />
            </div>
            <div class="control-area control-area-short">
                <label for="">报到证迁往单位所在地</label>
                <input class="area-id-local" type="hidden" name="areaId">
                <input type="text" name="reportAddressName" value="${dispatchInfo.reportAddressName}" autocomplete="off"
                       class="textTheme J_areaHolder" id="J_localAreaHolder" data-valid-rule="notNull"/>
                <ul class="none areaFidle" id="J_areaFidle_local"></ul>
                <p></p>
            </div>
            <%-- <div class="control-area control-area-short required">
                <label for="">是否接受档案</label>
                <span class="h">否</span>
				<input type="radio" name="acceptFile"class="radio textTheme" value="0" />
				<span class="h">是</span>
				<input type="radio" name="acceptFile" class="radio textTheme" value="1" checked="checked" /> -->
            <!-- <c:choose>
                    <c:when test="${dh.acceptFile==0}">
                        <span class="h">否</span>
                        <input type="radio" name="acceptFile" class="radio textTheme" value="0" checked="checked"/>
                        <span class="h">是</span>
                        <input type="radio" name="acceptFile" class="radio textTheme" value="1"/>
                    </c:when>
                    <c:when test="${dh.acceptFile==1}">
                        <span class="h">否</span>
                        <input type="radio" name="acceptFile" class="radio textTheme" value="0"/>
                        <span class="h">是</span>
                        <input type="radio" name="acceptFile" class="radio textTheme" value="1" checked="checked"/>
                    </c:when>
                    <c:otherwise>
                        <span class="h">否</span>
                        <input type="radio" name="acceptFile" class="radio textTheme" value="0"/>
                        <span class="h">是</span>
                        <input type="radio" name="acceptFile" class="radio textTheme" value="1"/>
                    </c:otherwise>
                </c:choose> -->
            <!-- </div> -->
            <!-- <div class="control-area control-area-short">
                <label for="">档案转寄单位</label>
                <input type="text" name="fileCompany" value="${dh.fileCompany}" class="textTheme"/>
            </div>
            <div class="control-area control-area-short postcode-two clear">
                <label for="">档案转寄单位邮编</label>
                <input type="text" name="fileCompanyPostcode" value="${dh.fileCompanyPostcode}" class="textTheme">
                <span class="valid-tip"></span></input>
            </div>
            <div class="control-area control-area-short">
                <label for="">档案接受部门</label>
                <input type="text" name="acceptFileDepartment" value="${dh.acceptFileDepartment}" class="textTheme"/>
            </div> -->
            <!-- <div class="control-area control-area-short">
                <label for="">接收联系人</label>
                <input type="text" name="acceptFilePerson" value="${dh.acceptFilePerson}" class="textTheme"/>
            </div>
            <div class="control-area control-area-short phone-two clear">
                <label for="">接收联系人电话</label>
                <input type="text" name="acceptFileTele" value="${dh.acceptFileTele}" class="textTheme"/>
                <span class="valid-tip"></span>
            </div> -->
            <!-- <div class="control-area control-area-short required ">
                <label for="">是否接受户口</label>
                <span class="h">否</span>
				<input type="radio" name="acceptHukou" class="radio" class="textTheme" value="0" />
				<span class="h">是</span>
				<input type="radio" name="acceptHukou" class="radio" class="textTheme" value="1" checked="checked"/> -->
            <!-- <c:choose>
                    <c:when test="${dh.acceptHukou==0}">
                        <span class="h">否</span>
                        <input type="radio" name="acceptHukou" class="radio" class="textTheme" value="0"
                               checked="checked"/>
                        <span class="h">是</span>
                        <input type="radio" name="acceptHukou" class="radio" class="textTheme" value="1"/>
                    </c:when>
                    <c:when test="${dh.acceptHukou==1}">
                        <span class="h">否</span>
                        <input type="radio" name="acceptHukou" class="radio" class="textTheme" value="0"/>
                        <span class="h">是</span>
                        <input type="radio" name="acceptHukou" class="radio" class="textTheme" value="1"
                               checked="checked"/>
                    </c:when>
                    <c:otherwise>
                        ${dh.acceptHukou}
                        <span class="h">否</span>
                        <input type="radio" name="acceptHukou" class="radio" class="textTheme" value="0"/>
                        <span class="h">是</span>
                        <input type="radio" name="acceptHukou" class="radio" class="textTheme" value="1"/>
                    </c:otherwise>
                </c:choose> -->
            <!-- </div> -->
            <!-- <div class="place-tip">（提示：您已经跨省，定向生、免费师范生生源地与签约地不同）</div> --%>



            <div class="control-area control-area-short">
                <label for="">地区代码</label>
                <input type="text" name="reportCompanyAddress"
                       class="unit-code J_local_code textTheme" onfocus="this.blur()" value="${dispatchInfo.reportCompanyAddress}"
                       data-valid-rule="notNull" id="placeCode" />
            </div>
            <div class="control-area control-area-short clear stuRemark">
                <label for="">备注</label>
                <input type="text" name="stuRemark" value="${dispatchInfo.stuRemark}"class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
            <%-- <div class="control-area control-area-short">
                <label for="">户口迁往地址</label>
                <input type="text" name="hukouTransferAddress" value="${dispatchInfo.hukouTransferAddress}" class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
            <div class="control-area control-area-short required">
                <label for="">档案转寄单位所在地</label>
                <input class="area-id-local" type="hidden" name="areaId">
                <input type="text" name="fileCompanyAddressName" value="${dispatchInfo.fileCompanyAddressName}"
                       autocomplete="off" class="textTheme J_areaHolder" id="J_localAreaHolder"
                       data-valid-rule="notNull"/>
                <ul class="none areaFidle" id="J_areaFidle_local"></ul>
                <p></p>
            </div> --%>


        </div>
        <!-- 就业信息表单end -->
        <h1>材料信息</h1>
        <!-- 材料信息表单start -->
        <div class="block-content clearfix">
            <ul class="clearfix material">
                <c:forEach var = "item" items="${list}">
                    <li>
                        <img src="${uploadWebsite}${item.materialUrl}" alt="${item.materialName}" class="J_thumbnail uploaded-img" data-id="${item.id}" />
                        <input type="text" maxlength="12" value = "${item.materialName}" style="display:none;width:136px;">
                        <a href="javascript:;" class="delete"></a>
                    </li>
                </c:forEach>
            </ul>
            <p>就业的同学可上传：就业协议、劳动合同、单位接收函、灵活就业登记表或学院证明等相关证明。</p>
            <p>升学的同学可上传：保研证明、调档函或升学学校网站上有本人姓名的录取公示名单的截图。</p>
            <p>出国出境的同学可上传：护照、学校录取通知或学院开具的相关证明。</p>
            <p>国家基层项目、地方基层项目、科研助理、暂不就业等暂时无法提供相关就业材料的同学，可由学院对个人信息进行审核，并由学院开具相关证明上传至系统。</p>
            <p>材料文件格式为“.jpg”“.png”“.jpeg”“.bmp”。上传图片必须清晰！</p>
            <div id="zyupload" class="zyupload"></div>
        </div>
        <!-- 材料信息表单end -->
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
                <p class="hint4"></p>
                <p class="hint5"></p>
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
        <%--<input type="checkbox" name="agree" class="agree"/>--%>
        <%--<span class="span-agree">同意将以上信息作为本人毕业签约信息依据，并上传至省就业签约系统</span><br>--%>
        <%--<input type="checkbox" name="agree" class="agree"/>--%>
        <%--<a href="${staticWebsite}../download/qianyuepingtaishuomingwendang.docx" style="display:block;margin: 20px 0 0 40%;">查看说明</a><br>--%>
        <c:choose>
            <c:when test="${stuDeputySecretaryCheckResult == 2 and stuCounsellorCheckResult == 2}">
                <input type="submit" class="submit" name="submit" value="下一步"/>
            </c:when>
            <c:otherwise>
                <input type="submit" class="submit" disabled = "disabled" name="submit" value="下一步"/>
            </c:otherwise>
        </c:choose>

    </form>
    <!-- 表单end -->
</div>
<!-- 主题内容end -->
<script type="text/javascript">
    KISSY.use('page/student_page/dispatch/alterInfo', function (S) {
        S.ready(function () {
            PW.page.student_page.dispatch.alterInfo();
        })
    });
</script>
<script>
    $(function () {
        // 初始化插件
        $("#zyupload").zyUpload({
            width: "100%",                 // 宽度
            height: "350px",                 // 宽度
            itemWidth: "140px",                 // 文件项的宽度
            itemHeight: "115px",                 // 文件项的高度
            url: "/student/dispatch/ajax/upload",  // 上传文件的路径
            fileType: ["jpg", "png", "jpeg", "bmp"],// 上传文件的类型
            fileSize: 5120000,                // 上传文件的大小
            multiple: true,                    // 是否可以多个文件上传
            dragDrop: true,                    // 是否可以拖动上传文件
            tailor: true,                    // 是否可以裁剪图片
            del: true,                    // 是否可以删除文件
            finishDel: false,  				  // 是否在上传文件完成后删除预览
            /* 外部获得的回调接口 */
            onSelect: function (selectFiles, allFiles) {    // 选择文件的回调方法  selectFile:当前选中的文件  allFiles:还没上传的全部文件
                console.info("当前选择了以下文件：");
                console.info(selectFiles);
            },
            onDelete: function (file, files) {              // 删除一个文件的回调方法 file:当前删除的文件  files:删除之后的文件
                console.info("当前删除了此文件：");
                console.info(file.name);
            },
            onSuccess: function (file, response) {          // 文件上传成功的回调方法
                console.info("此文件上传成功：");
                console.info(file.name);
                console.info("此文件上传到服务器地址：");
                console.info(response);
                $("#uploadInf").append("<p>上传成功!</p>");
            },
            onFailure: function (file, response) {          // 文件上传失败的回调方法
                console.info("此文件上传失败：");
                console.info(file.name);
            },
            onComplete: function (response) {           	  // 上传完成的回调方法
                console.info("文件上传完成");
                console.info(response);
            }
        });
        var input = document.createElement("input");
        input.setAttribute('value',$('.school-roll1').val());
        input.setAttribute('name','dispatchInfoId');
        input.setAttribute('type','hidden');
        $("#uploadForm").prepend(input);
    });
</script>

<%--<!-- 报到证发放类别二级联动 -->--%>
<script type="text/javascript">
    var valueName = $("#reportModeId").find("option:selected").val();
    var place = "${placeCode.showName}"
    if(valueName==2) {
        $("#J_localAreaHolder").val(place).attr("readonly","readonly");
        $("#J_localAreaHolderName").val(place+"人社局").attr("readonly","readonly");
        var placeCode = "${placeCode.placeId}";
        $("#placeCode").val(placeCode);
    }
    if(valueName==1||valueName==3) {
        $("#J_localAreaHolderName").parent().addClass("required").children("input").addClass("J_notNull");
        $("#J_localAreaHolder").parent().addClass("required");
        //报到证迁往单位所在地div含有两个input
        $("#J_localAreaHolder").addClass("J_notNull");
    }
    //自主创业
    if(valueName==7) {
        $("#J_localAreaHolderName").parent().addClass("required").children("input").addClass("J_notNull");
        $("#J_localAreaHolder").parent().addClass("required");
        $("#J_localAreaHolder").addClass("J_notNull");
        var industry = '<div class="control-area control-area-short industry required"><label for="">单位行业</label><input type="text" class="textTheme J_notNull" /></div>';
        $(".jobMsg").append(industry);
    }
    if(valueName==6) {
        $("#J_localAreaHolderName").attr("value","");
        $("#J_localAreaHolder").attr("value","");
    }
    $("#reportModeId").change(function(){
        var valueName = $(this).find("option:selected").val();
        var place = "${placeCode.showName}" ;

        $(".jobMsg").children("div").removeClass('required').children("input").removeClass('J_notNull');
        $(".jobMsg").children("div").children("select").removeClass('J_notOption');
//        $(".location").remove();
        $("#J_localAreaHolderName").show();
        $("#J_localAreaHolder").show();
        $(".newAdd").remove();
        $(".industry").remove();
        $("#reportModeId").parent().addClass("required");
        $("#J_localAreaHolder").removeAttr("readonly");
        $("#J_localAreaHolderName").removeAttr("readonly");
        if(valueName==2) {
//            $("#J_localAreaHolderName").parent().addClass("required");
//            $("#J_localAreaHolder").parent().addClass("required");
//            var location = '<div class="control-area control-area-short location required"><label for="">生源地</label><input type="text" name="" class="unit-code J_unit_code textTheme" onfocus="this.blur()" value="000000" data-valid-rule="notNull"/></div>';
//            $(".jobMsg").append(location);
            $("#J_localAreaHolder").val(place).attr("readonly","readonly");
            $("#J_localAreaHolderName").val(place+"人社局").attr("readonly","readonly");

//            $("#J_localAreaHolder").hide();
            // 后端填写数据(生源地)
//            var jobLocation = "<input style='float:right;padding:3px 10px 0 0;' name='reportAddressName' readonly='readonly'  value=" + place + " class='newAdd'>";
//            $("#J_localAreaHolder").after(jobLocation);
//            $("#J_localAreaHolderName").hide();
            // 后端填写数据(生源地所在人社局)
//            var jobLocationName = "<input style='float:right;padding:3px 20px 0 0;' name='reportCompany' readonly='readonly'  value=" + place + "人社局" + " class='newAdd'>";
//            $("#J_localAreaHolderName").after(jobLocationName);
            var placeCode = "${placeCode.placeId}";
            $("#placeCode").val(placeCode);
        }
        if(valueName==1||valueName==3) {
            $("#J_localAreaHolderName").parent().addClass("required").children("input").addClass("J_notNull");
            $("#J_localAreaHolder").parent().addClass("required");
            $("#J_localAreaHolder").addClass("J_notNull");
        }
        if(valueName==7) {
            $("#J_localAreaHolderName").parent().addClass("required").children("input").addClass("J_notNull");
            $("#J_localAreaHolder").parent().addClass("required");
            $("#J_localAreaHolder").addClass("J_notNull");
            var industry = '<div class="control-area control-area-short industry required"><label for="">单位行业</label><input type="text" class="textTheme J_notNull" /></div>';
            $(".jobMsg").append(industry);
        }
        if(valueName==6) {
            $("#J_localAreaHolderName").val("");
            $("#J_localAreaHolder").val("");
        }
    })

</script>
<script>
    // 学生填写签约信息上传材料设为必填项
    $("#form").submit(function(){
        var $file=$("#preview").children();
        if($file.length==0){
            var  img_required=false;
            $(".upload_choose").css("border","1px solid #d15b47");
        }else{
            var img_required=true;
            $(".upload_choose").css("border","1px solid #ccc");
        }
        return img_required;
    })
</script>

</body>
</html>