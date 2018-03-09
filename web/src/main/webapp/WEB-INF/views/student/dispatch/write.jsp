<!-- 派遣信息填写页 -->
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>填写签约信息</title>
    <jsp:include page="../../common/link.jsp" />
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/student_page/dispatch/writeInfo.css">
    <link rel="stylesheet" href="${staticWebsite}upload/css/zyupload-1.0.0.css " type="text/css">
    <script type="text/javascript" src="${staticWebsite}upload/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${staticWebsite}upload/js/zyupload-1.0.0.js"></script>
    <script type="text/javascript" src="${staticWebsite}js/module/dispatch/del.js"></script>
</head>
<jsp:include page="../../common/header.jsp"/>
<jsp:include page="../../common/Ssidebar.jsp"/>
<script type="text/javascript">
    KISSY.use('module/sidebar',function(S){
        S.ready(function(){
            PW.module.Sidebar();
        });
    });
</script>
<body>
<div class="content">
        <form action="${website}student/dispatch/new" class="block" method="POST">
        <c:if test="${!empty msg}">
            <div class="fault-tip J_tip">${msg}</div>
        </c:if>
        <span class="attention">请同学们如实填写以下信息。红色字体为必填项，黑色字体为选填项。</span>
        <span class="attention">你的协议编号为：<span>${agreementNumber}</span></span>
        <h1>就业信息</h1>
        <div class="block-content clearfix">
            <div class="control-area control-area-short required">
                <label for="">毕业去向</label>
                <select name="whereaboutgoId"  class="textTheme J_notOption" autocomplete="off">
                    <option value="-1">请选择</option>
                    <c:forEach var = "item" items="${whereaboutgos}">
                        <option value="${item.whereAboutGoId}">${item.whereAboutGo}</option>
                    </c:forEach>
                </select>
                <span class="tip hint graduation-hint"><a href="javascript:;">提示</a></span>
            </div>
            <div class="control-area control-area-short required">
                <label for="">签约单位名称</label>
                <input type="text" name="companyName"  class="textTheme J_notNull"/>
                <span class="tip hint unit-hint"><a href="javascript:;">提示</a></span>
            </div>
            <div class="control-area control-area-short required">
                <label for="">单位组织机构代码</label>
                <input type="text" name="organizationCode" class="textTheme J_notNull"/>
                <span class="tip hint code-hint"><a href="javascript:;">提示</a></span>
            </div>
            <div class="control-area control-area-short required">
                <label for="">单位所在地</label>
                <%--<input class="area-id-unit" type="hidden" name="areaId">--%>
                <input id="J_unitAreaHolder" name="cityName" class="textTheme J_areaHolder J_notNull" type="text" autocomplete="off" />
                <ul class="none areaFidle" id="J_areaFidle_unit"></ul>
                <p></p>
            </div>
            <div class="control-area control-area-short">
                <label for="">地区代码</label>
                <input type="text" class="unit-code J_unit_code textTheme" onfocus="this.blur()" name="cityId" value="000000" data-valid-rule="notNull"/>
            </div>
            <div class="control-area control-area-short required">
                <label for="">单位性质</label>
                <select name="propertyId"class="textTheme J_notOption">
                    <option value="-1">请选择</option>
                    <c:forEach var = "item" items="${companyPropertyCodes}">
                        <option value="${item.propertyCode}">${item.propertyName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="control-area control-area-short required">
                <label for="">单位行业</label>
                <select name="tradeId" class="textTheme J_notOption">
                    <option value="-1">请选择</option>
                    <c:forEach var = "item" items="${companyTradeCodes}">
                        <option value="${item.tradeId}">${item.tradeName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="control-area control-area-short">
                <label for="">单位隶属部门</label>
                <input type="text" name="subordinateDepartment" class="textTheme"/>
            </div>
            <div class="control-area control-area-short required">
                <label for="">工作职位类别</label>
                <select name="jobId" data-valid-rule="scale(0,1000,0)" class="textTheme">
                    <option value="-1">请选择</option>
                    <c:forEach var = "item" items="${jobCodes}">
                        <option value="${item.jobId}">${item.job}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="control-area control-area-short">
                <label for="">单位地址</label>
                <input type="text" name="fullAddress" class="textTheme"/>
            </div>
            <div class="control-area control-area-short postcode-one clear">
                <label for="">单位邮编</label>
                <input type="text" name="companyPostcode" class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
            <div class="control-area control-area-short">
                <label for="">单位联系人</label>
                <input type="text" name="companyContactPerson" class="textTheme"/>
            </div>
            <div class="control-area control-area-short fax clear">
                <label for="">单位联系人传真</label>
                <input type="text" name="contactPersonFax" class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
            <div class="control-area control-area-short clear phone-one">
                <label for="">单位联系人电话</label>
                <input type="text" name="contactPersonTele" class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
            <div class="control-area control-area-short clear mobile-phone">
                <label for="">单位联系人手机</label>
                <input type="text" name="contactPersonMobile" class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
            <div class="control-area control-area-short email clear">
                <label for="">单位联系人电子邮箱</label>
                <input type="text" name="contactPersonEmail"  class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
            <div class="control-area control-area-short clear stuRemark">
                <label for="">学生备注</label>
                <input type="text" name="stuRemark" class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
        </div>
        <!-- 派遣信息表单end -->
        <h1>签约信息</h1>
        <!-- 就业信息表单start -->
        <div class="block-content jobMsg clearfix">
            <div class="control-area control-area-short required">
                <label for="">报到证发放类别</label>
                <select id="reported" name="reportModeId"  class="textTheme certificateKind J_notOption">
                    <option value="-1">请选择</option>
                    <c:forEach var = "item" items="${reportCodes}">
                        <option value="${item.reportModeId}">${item.reportMode}</option>
                    </c:forEach>
                </select>
                <span class="tip hint type-hint"><a href="javascript:;">提示</a></span>
            </div>
            <div class="control-area control-area-short required ">
                <label for="">报到证迁往单位名称</label>
                <input type="text"  name="reportCompany" class="textTheme J_notNull"/>
            </div>
            <div class="control-area control-area-short required">
                <label for="">是否接受档案</label>
                    <span class="h">否</span>
                    <input type="radio" name="acceptFile" class="radio textTheme" value="0" />
                    <span class="h">是</span>
                    <input type="radio" name="acceptFile" class="radio textTheme" value="1" checked="checked"/>
            </div>
            <div class="control-area control-area-short">
                <label for="">档案转寄单位</label>
                <input type="text"  name="fileCompany" class="textTheme"/>
            </div>
            <div class="control-area control-area-short postcode-two clear">
                <label for="">档案转寄单位邮编</label>
                <input type="text" name="fileCompanyPostcode" class="textTheme">
                <span class="valid-tip"></span></input>
            </div>
            <div class="control-area control-area-short">
                <label for="">档案接受部门</label>
                <input type="text" name="acceptFileDepartment" class="textTheme"/>
            </div>
            <div class="control-area control-area-short">
                <label for="">接收联系人</label>
                <input type="text"  name="acceptFilePerson" class="textTheme"/>
            </div>
            <div class="control-area control-area-short phone-two clear">
                <label for="">接收联系人电话</label>
                <input type="text" name="acceptFileTele" class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
            <div class="control-area control-area-short required ">
                <label for="">是否接受户口</label>
                    <span class="h">否</span>
                    <input type="radio" name="acceptHukou" class="radio textTheme" value="0" />
                    <span class="h">是</span>
                    <input type="radio" name="acceptHukou" class="radio textTheme" value="1" checked="checked"/>
            </div>
            <%--<div class="place-tip">（提示：您已经跨省，定向生、免费师范生生源地与派遣地不同）</div>--%>
            <div class="control-area control-area-short required">
                <label for="">报到证迁往单位所在地</label>
                <%--<input class="area-id-local" type="hidden" name="areaId">--%>
                <input type="text" name="reportAddressName"  autocomplete="off" class="textTheme J_areaHolder J_notNull" id="J_localAreaHolder"/>
                <ul class="none areaFidle" id="J_areaFidle_local"></ul>
                <p></p>
            </div>
            <div class="control-area control-area-short ">
                <label for="">地区代码</label>
                <input type="text" class="unit-code J_local_code textTheme" onfocus="this.blur()" name="reportCompanyAddress" value="000000"/>
            </div>
            <div class="control-area control-area-short">
                <label for="">户口迁往地址</label>
                <input type="text"  name="hukouTransferAddress" class="textTheme"/>
                <span class="valid-tip"></span>
            </div>
            <div class="control-area control-area-short required">
                <label for="">档案转寄单位所在地</label>
                <%--<input class="area-id-local" type="hidden" name="areaId">--%>
                <input type="text" name="fileAddressName"  autocomplete="off" class="textTheme J_areaHolder J_notNull" id="J_localAreaHolder" />
                <ul class="none areaFidle" id="J_areaFidle_local"></ul>
                <p></p>
            </div>
            <div class="control-area control-area-short ">
                <label for="">地区代码</label>
                <input type="text" class="unit-code J_unit_code textTheme" onfocus="this.blur()" name="fileCompanyAddress" value="000000" />
            </div>
        </div>
        <!-- 就业信息表单end -->
        <h1>材料信息</h1>
        <!-- 材料信息表单start -->
        <div class="block-content clearfix">
            <div id="zyupload" class="zyupload"></div>
            <input type="hidden" class="textTheme" data-valid-rule="notNull" name="statusInfoId" value="${statusInfoId}"/>
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
        <!-- 提示框内容end -->
        <input type="checkbox" name="" class="agree"  data-valid-rule="notNull" />
        <span class="span-agree">同意将以上信息作为本人毕业签约信息依据，并上传至省就业签约系统</span><br>
        <input type="checkbox" name="" class="agree"  data-valid-rule="notNull" />
        <span class="span-agree">确认所传材料为所需材料类型<span class="hint look-hint"><a href="javascript:;" class="look-state">查看说明</a></span> </span><br>
        <input type="submit" class="submit" name="" value="下一步"/>
    </form>
    <!-- 表单end -->
</div>
<!-- 主题内容end -->
<script type="text/javascript">
    KISSY.use('page/student_page/dispatch/writeInfo',function(S){
        S.ready(function(){
            PW.page.student_page.dispatch.writeInfo();
        })
    });
    $(function(){
        // 初始化插件
        $("#zyupload").zyUpload({
            width            :   "98.1%",                 // 宽度
            height           :   "350px",                 // 宽度
            itemWidth        :   "140px",                 // 文件项的宽度
            itemHeight       :   "115px",                 // 文件项的高度
            url              :   "/student/dispatch/ajax/upload",  // 上传文件的路径
            fileType         :   ["jpg","png","rar","zip"],// 上传文件的类型
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
                $("#uploadInf").append("<p>上传成功!</p>");
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
<!-- 报到证发放类别二级联动 -->
<script type="text/javascript">
    $("#reported").on("change",function(){
        var reportedVal = $(this).find("option:selected").val();

        $.ajax({
            type:"GET",
            url: "${website}student/dispatch/ajax/makeprovice/",//需要后台填写哦！
            dataType:"json",
            success: function(data){
                var provinceName;
                var provinceAdd;
                console.log(data);
                $.each(data,function(index,el){
                    provinceName = "<span style='float:right;'>"+el.originPlace+"</span>"
                    provinceAdd="<span style='float:right;'>"+el.reportCompany+"人社局</span>";
                });

                if(reportedVal==2){
                    $(".jobMsg .control-area:nth-child(2)").children("input").removeClass('J_notNull').hide();
                    $(".jobMsg .control-area:nth-child(10)").children("input").removeClass('J_notNull').hide();
                    $(".jobMsg .control-area:nth-child(2)").append(provinceName);
                    $(".jobMsg .control-area:nth-child(10)").append(provinceAdd);
                }else {
                    $(".jobMsg .control-area:nth-child(2)").children("input").show().addClass('J_notNull');
                    $(".jobMsg .control-area:nth-child(10)").children("input").show().addClass('J_notNull');
                    $(".jobMsg .control-area:nth-child(2)").children("span").remove();
                    $(".jobMsg .control-area:nth-child(10)").children("span").remove();
                }

            }
        })

    })









</script>
</body>
</html>