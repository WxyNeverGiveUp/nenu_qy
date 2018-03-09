<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wlm
  Date: 2016/9/5
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>修改学籍信息</title>
    <jsp:include page="../../common/link.jsp"/>
    <jsp:include page="../../common/script.jsp"/>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/common/school_roll_common.css">
    <link rel="stylesheet" href="${staticWebsite}upload/css/zyupload-1.0.0.css " type="text/css">
    <script type="text/javascript" src="${staticWebsite}upload/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${staticWebsite}upload/js/zyupload-1.0.0.js"></script>
    <script type="text/javascript" src="${staticWebsite}js/module/dispatch/del.js"></script>
</head>
<body>
<jsp:include page="../../common/header.jsp"/>
<div class="container">
    <jsp:include page="../../common/Asidebar.jsp"/>
    <script type="text/javascript">
        KISSY.use('module/sidebar',function(S){
            S.ready(function(){
                PW.module.Sidebar();
            });
        });
    </script>
    <div class="content">
        <div class="remind">
        </div>
        <c:if test="${!empty errMsg}">
            <div class="fault-tip J_tip">${errMsg}</div>
        </c:if>
        <form class="main_form" action="${website}admin/status/info/update" method="post" name="admin_alter_form">
            <input type="text" name="id" value="${student.id}" class="none school-roll"/>
            <h2>基本信息</h2>
            <div class="summary clearfix">
                <div class="clearfix">
                    <label class="must matter">姓名：</label>
                    <input type="text" name="name" class="J_name J_notNull" value="${studentDto.name}" />
                    <p class="J_name_error">请输入正确的格式</p>
                </div>
                <div class="clearfix">
                    <label class="must matter">身份证号：</label>
                    <input type="text" name="idNumber" class="J_id J_notNull" value="${studentDto.idNumber}" />
                    <p class="J_id_error">请输入正确的身份证号</p>
                </div>
                <div class="clearfix">
                    <label class="must matter">考生号：</label>
                    <input type="text" name="candidateNumber" class="J_examNo J_notNull" value="${studentDto.candidateNumber}" />
                    <a class="J_hint J_num_hint small-position" href="javascript:;">提示</a>
                    <p class="J_examNo_error">请输入正确的考生号</p>
                </div>

                <div>
                    <label class="must matter">学号：</label>
                    <input type="text" name="studentNumber" value="${studentDto.studentNumber}" class="J_stuNum J_notNull" />
                    <p class="J_stuNum_error">请输入正确的学号</p>
                </div>
                <div class="change-sex">
                    <label class="must matter">性别：</label>
                    <input type="radio" name="sex" id="male" value="1" <c:if test="${student.sex eq 1}">checked</c:if> /><label for="male">男</label>
                    <input type="radio" name="sex" id="female" value="2" <c:if test="${student.sex eq 2}">checked</c:if> /><label for="female">女</label>
                </div>
                <div>
                    <label class="must matter">民族：</label>
                    <select class="J_notOption" name="nationCode" autocomplete="off">
                        <option value="-1">请选择</option>
                        <c:forEach items="${nationList}" var="nation">
                            <option value="${nation.nationId}"
                                    <c:if test="${student.nationCode eq nation.nationId}">selected</c:if>>${nation.nation}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label class="must matter">政治面貌：</label>
                    <select class="J_notOption" name="politicalStandCode" autocomplete="off">
                        <option value="-1">请选择</option>
                        <c:forEach items="${politicalList}" var="political">
                            <option value="${political.politicalStandId}"
                                    <c:if test="${student.politicalStandCode eq political.politicalStandId}">selected</c:if>>${political.politicalStand}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="college-name">
                    <label class="must matter">院校名称：</label>
                    <span class="must">${schoolName}</span>
                </div>
                <div>
                    <label class="matter">学制：</label>
                    <select name="stuLength" autocomplete="off">
                        <option value="-1">请选择</option>
                        <option value="1" <c:if test="${studentDto.stuLength eq 1}">selected</c:if>>1</option>
                        <option value="2" <c:if test="${studentDto.stuLength eq 2}">selected</c:if>>2</option>
                        <option value="3" <c:if test="${studentDto.stuLength eq 3}">selected</c:if>>3</option>
                        <option value="4" <c:if test="${studentDto.stuLength eq 4}">selected</c:if>>4</option>
                        <option value="5" <c:if test="${studentDto.stuLength eq 5}">selected</c:if>>5</option>
                        <option value="6" <c:if test="${studentDto.stuLength eq 6}">selected</c:if>>6</option>
                    </select>
                </div>
                <div>
                    <label class="must matter">学历：</label>
                    <select class="J_notOption" name="qualificationCode" autocomplete="off">
                        <option value="-1">请选择</option>
                        <c:forEach items="${qualificationList}" var="qualification">
                            <option value="${qualification.qualificationId}"
                                    name="undergraduate"
                                    <c:if test="${student.qualificationCode eq qualification.qualificationId}">selected</c:if>>${qualification.qualification}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label class="must matter">所在院系/分院：</label>
                    <select class="J_notOption" name="collegeCode" autocomplete="off">
                        <option value="-1">请选择</option>
                        <c:forEach items="${collegeList}" var="college">
                            <option value="${college.collegeId}"
                                    <c:if test="${student.collegeCode eq college.collegeId}">selected</c:if>>${college.college}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label class="must matter">所在年级：</label>
                    <select class="J_notOption" name="grade" autocomplete="off">
                        <option value="-1">请选择</option>
                        <option value="2010" <c:if test="${studentDto.grade eq 2010}">selected</c:if>>2010</option>
                        <option value="2011" <c:if test="${studentDto.grade eq 2011}">selected</c:if>>2011</option>
                        <option value="2012" <c:if test="${studentDto.grade eq 2012}">selected</c:if>>2012</option>
                        <option value="2013" <c:if test="${studentDto.grade eq 2013}">selected</c:if>>2013</option>
                        <option value="2014" <c:if test="${studentDto.grade eq 2014}">selected</c:if>>2014</option>
                        <option value="2015" <c:if test="${studentDto.grade eq 2015}">selected</c:if>>2015</option>
                        <option value="2016" <c:if test="${studentDto.grade eq 2016}">selected</c:if>>2016</option>
                    </select>
                </div>
                <span class="major-name">
						<label class="must no-matter">专业名称：</label>
                        <select name="majorQualification" class="J_qualification J_notOption" autocomplete="off">
							<option value="-1">请选择</option>
							<option value="1" <c:if test="${major.qualification eq '本科专业'}"> selected </c:if>>本科专业</option>
							<option value="2" <c:if test="${major.qualification eq '研究生专业'}"> selected </c:if>>研究生专业</option>
						</select>
						<label>------</label>
						<select class="J_majorClass J_notOption" autocomplete="off">
							<option value="-1">请选择专业大类</option>
							<option value="${major.majorDivisionId}" selected>${major.majorDivision}</option>
						</select>
						<label>------</label>
						<select class="J_majorMinorClass J_notOption" autocomplete="off">
							<option value="-1">请选择专业中类</option>
                            <option value="${major.majorClassId}" selected>${major.majorClass}</option>
						</select>
						<label>------</label>
						<select name="majorCode" class="J_majorName J_notOption" autocomplete="off">
							<option value="-1">请选择专业</option>
                            <option value="${major.majorId}" selected>${major.majorName}</option>
						</select>
					</span>
                <div class="where">
                    <label class="must matter">拟毕业去向：</label>
                    <select class="J_notOption" name="intendWhereaboutsCode" autocomplete="off">
                        <option value="-1">请选择</option>
                        <c:forEach items="${whereAboutGoIntendCodeList}" var="whereAboutGoIntend">
                            <option value="${whereAboutGoIntend.whereAboutGoId}"
                                    <c:if test="${student.intendWhereaboutsCode eq whereAboutGoIntend.whereAboutGoId}">selected</c:if>>${whereAboutGoIntend.whereAboutGo}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="other-major">
                    <label class="must matter">请填写专业名称：</label>
                    <input type="text" name="otherMajor" disabled="true" />
                </div>
                <div class="other-major"></div>
                <div class="other-major"></div>
                <div class="other-major"></div>

                <div class="kind">
                    <label class="must matter">困难生类别：</label>
                    <select name="difficultyCode" class="kind J_notOption" autocomplete="off">
                        <option value="-1">请选择</option>
                        <c:forEach items="${difficultyList}" var="difficulty">
                            <option value="${difficulty.difficultyId}" <c:if test="${student.difficultyCode eq difficulty.difficultyId}">selected</c:if>>${difficulty.difficultyMode}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="clearfix">
                    <label class="must matter">师范生类别：</label>
                    <select class="clickShow selectClass none" name="normalStuCode" disabled="true">
                        <option value="-1">请选择</option>
                        <option value="1" <c:if test="${student.normalStuCode eq 1}">selected</c:if>>普通师范生</option>
                        <option value="12" <c:if test="${student.normalStuCode eq 12}">selected</c:if>>免费师范生</option>
                        <option value="2" <c:if test="${student.normalStuCode eq 2}">selected</c:if>>非师范生</option>
                    </select>
                    <span>${studentDto.normalStu}</span>
                    <a class="J_hint J_kind_hint" href="javascript:;">提示</a>
                    <em class="alter J_alter J_alterClass">修改</em>
                </div>

                <div class="clearfix update-parent">
                    <label class="must matter">生源所在地：</label>
                    <input class="area-id-local" type="hidden" name="areaId">
                    <input type="text" name="originPlace" autocomplete="off" class="clickShow none J_areaHolder J_notNull" id="J_localAreaHolder" value="${studentDto.originPlace}"/>
                    <ul class="none areaFidle" id="J_areaFidle_local"></ul>
                    <span>${studentDto.originPlace}</span>
                    <em class="alter J_alter J_alterLocal">修改</em>
                    <a class="J_hint J_local_stu_hint" href="javascript:;">提示</a>
                    <p class="localTip"></p>
                </div>
                <div class="clearfix">
                    <label class="must matter">地区代码：</label>
                    <input type="text" name="originPlaceCode" class="local-code J_local_code" onfocus="this.blur()" value="${student.originPlaceCode}"/>
                </div>
                <div class="clearfix">
                    <label class="must matter">培养方式：</label>
                    <select name="trainingModeCode" class="clickShow selectWay none J_notOption" disabled="true">
                        <option value='0'>请选择</option>
                        <c:forEach items="${trainingModeList}" var="trainingMode1">
                            <option value="${trainingMode1.trainingModeId}" <c:if test="${student.trainingModeCode eq trainingMode1.trainingModeId}">selected</c:if> >${trainingMode1.trainingMode}</option>
                        </c:forEach>
                    </select>
                    <span class="train-way">${studentDto.trainingMode}</span>
                    <a class="J_hint J_train_hint" href="javascript:;">提示</a>
                    <em class="alter J_alter J_alterWay">修改</em>
                </div>
                <div class="train-unit J_orientation clearfix">
                    <label class="must matter">定向/委培单位：</label>
                    <input type="text" name="weipeiUnit" class="clickShow unit none J_notNull" disabled="true" value="${studentDto.weipeiUnit}"/>
                    <span>${studentDto.weipeiUnit}</span>
                    <em class="alter J_alter J_alterUnit">修改</em>
                    <a class="J_hint J_unit_hint update-a" href="javascript:;">提示</a>
                </div>
                <div class="unit-addr J_orientation">
                    <label class="must matter">定向/委培单位地址：</label>
                    <input class="area-id-unit" type="hidden" name="areaId">
                    <input id="J_unitAreaHolder" name="weipeiUnitPlace" class="J_areaHolder J_notNull" type="text" autocomplete="off"
                           value="${student.weipeiUnitPlace}" />
                    <ul class="none areaFidle" id="J_areaFidle_unit"></ul>
                    <p class="unitTip"></p>
                </div>
                <div class="clearfix J_orientation">
                    <label class="must matter">地区代码：</label>
                    <input type="text" class="unit-code J_unit_code" onfocus="this.blur()" name="weipeiUnitPlaceCode" value="${student.weipeiUnitPlaceCode}" />
                </div>
            </div>

            <h2>入学信息</h2>
            <div class="summary clearfix">
                <div>
                    <label class="must matter">入学日期：</label>
                    <input class="J_date J_notNull J_sDate" type="text" name="registDate" value="${studentDto.registDate}" />
                </div>
                <div>
                    <label class="must matter">毕业日期：</label>
                    <input class="J_date J_notNull J_eDate" type="text" name="graduationDate" value="${studentDto.graduationDate}" />
                    <a class="J_hint J_date_hint" href="javascript:;">提示</a>
                </div>
                <%--<div>--%>
                    <%--<label class="matter">入学前户口所在地：</label><input type="text"  name="preHukouLocation"--%>
                                                                  <%--value="${studentDto.preHukouLocation}"/>--%>
                <%--</div>--%>
                <%--<div class="residence">--%>
                    <%--<label class="matter">户口是否转入学校:<p>(无需填写)</p></label>--%>
                    <%--<input type="radio" name="isHukouIntoSchool" value="1" id="already" <c:if test="${student.isHukouIntoSchool eq 1}">checked</c:if>  /><label for="already">是</label>--%>
                    <%--<input type="radio" name="isHukouIntoSchool" value="0" id="without" <c:if test="${student.isHukouIntoSchool eq 0}">checked</c:if>  /><label for="without">否</label>--%>
                <%--</div>--%>
                <%--<div>--%>
                    <%--<label class="matter">入学前档所在单位：</label><input type="text" name="preArchiveLocation"--%>
                                                                  <%--value="${studentDto.preArchiveLocation}" />--%>
                <%--</div>--%>
                <%--<div class="record-file">--%>
                    <%--<label class="matter">档案是否转入学校:<p>(无需填写)</p></label>--%>
                    <%--<input type="radio" name="isArchiveIntoSchool" value="1" id="yes"--%>
                           <%--<c:if test="${student.isArchiveIntoSchool eq 1}">checked</c:if> /><label for="yes">是</label>--%>
                    <%--<input type="radio" name="isArchiveIntoSchool" value="0" id="no"--%>
                           <%--<c:if test="${student.isArchiveIntoSchool eq 0}">checked</c:if> /><label for="no">否</label>--%>
                <%--</div>--%>
            </div>
            <h2>联系方式</h2>
            <div class="summary clearfix">
                <div>
                    <label class="must matter">手机号码：</label>
                    <input type="text" name="cellphone" class="J_phone J_notNull" value="${studentDto.cellphone}" />
                    <p class="J_phone_error">请输入正确的格式</p>
                </div>
                <div>
                    <label class="matter">手机号码（备用）：</label>
                    <input type="text" name="cellphoneBak" value="${studentDto.cellphoneBak}" />
                </div>
                <div>
                    <label class="must matter">QQ号码：</label>
                    <input type="text" name="qq" class="j_QQNum J_notNull" value="${studentDto.qq}" />
                    <p class="J_QQNum_error">请输入正确的格式</p>
                </div>
                <div>
                    <label class="must matter">常用电子邮箱：</label>
                    <input type="text" name="email" class="J_email J_notNull" value="${studentDto.email}" />
                    <p class="J_email_error">请输入正确的电子邮箱格式</p>
                </div>
                <div>
                    <label class="matter">家庭电话：</label>
                    <input type="text" name="homePhone" class="J_homePhone" value="${studentDto.homePhone}" />
                    <p class="J_homePhone_error">格式为"区号-号码"或手机号</p>
                </div>

                <div>
                    <label class="must matter">紧急联系方式：</label>
                    <input type="text" name="relativePhone" class="J_notNull" value="${studentDto.relativePhone}" />
                </div>
                <div>
                    <label class="matter">微信号：</label>
                    <input type="text" name="weixin" value="${studentDto.weixin}" />
                </div>
                <div>
                    <label class="must matter">家庭住址：</label>
                    <input type="text" name="homeAddress" class="J_address J_notNull" value="${studentDto.homeAddress}" />
                    <p class="J_address_error">请输入正确的格式</p>
                </div>
                <div>
                    <label class="matter">寝室楼：</label>
                    <input type="text" name="dormitory" value="${studentDto.dormitory}" />
                </div>
                <div>
                    <label class="matter">寝室号：</label>
                    <input type="text" name="dormitoryNum" value="${studentDto.dormitoryNum}" />
                </div>
                <input type="hidden" name="counsellorCheckReason" value="${studentDto.counsellorCheckReason}"/>
                <input type="hidden" name="deputySecretaryCheckReason" value="${studentDto.deputySecretaryCheckReason}"/>
            </div>
            <h2>材料信息</h2>
            <ul class="clearfix material">
                <c:forEach items="${materialList}" var="material">
                    <li>
                        <img src="${uploadWebsite}${material.materialUrl}" alt="${material.materialName}" data-id="${material.id}" class="J_thumbnail uploaded-img" />
                        <input type="text" maxlength="12" value="${material.materialName}" style="display:none;width:136px;">
                        <a href="javascript:;" class="delete"></a>
                    </li>
                </c:forEach>
            </ul>
            <!-- 材料信息表单start -->
            <div class="clearfix">
                <div id="zyupload" class="zyupload">
                    <p>材料文件格式为“.jpg”“.png”“.jpeg”“.bmp”。上传图片必须清晰！</p>
                </div>
            </div>
            <!-- 材料信息表单end -->
            <div class="btnbox">
                <!-- <input type="button" class="save J_save" value="保存" /> -->
                <input type="submit" class="submit-complete-form" value="确认修改" />
            </div>
        </form>
    </div>
</div>
<div class="pop-pic none J_pop_pic">
    <img src=""/>
    <a href="javascript:;" class="J_shut">关闭</a>
</div>
<div class="scene none"></div>
<div class="hint-box J_change none">
    <div class="hint clearfix">提示信息
        <span class="J_close">X</span>
    </div>
    <div class="J_msg_box msg-box">
        <p class="J_msg"></p>
        <p class="J_doc_hint"></p>
        <p class="J_hint1"></p>
        <p class="J_hint2"></p>
    </div>

    <span class="close-btn J_close">关闭</span>
</div>
<div class="delete-box  none">
    <div class="delete-info clearfix">
        <p>确认要删除此图片吗？</p>
        <span class="close">X</span>
    </div>
    <span class="close-button confirm">确认</span>
    <span class="close-button close">取消</span>
</div>
<script type="text/javascript">
    KISSY.use('page/admin_page/students_management/school_roll/alterInfo',function(S){
        S.ready(function(){
            PW.page.admin_page.students_management.school_roll.alterInfo();
        });
    });
</script>
<script type="text/javascript">
    $(function(){
        // 初始化插件
        $("#zyupload").zyUpload({
            width            :   "100%",                 // 宽度
            height           :   "350px",                 // 宽度
            itemWidth        :   "140px",                 // 文件项的宽度
            itemHeight       :   "115px",                 // 文件项的高度
            url              :   "/admin/status/info/ajax/upload",  // 上传文件的路径
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
</body>
</html>