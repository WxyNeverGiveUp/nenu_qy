<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wlm
  Date: 2017/5/14
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>就业意向调查</title>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/student_page/school_roll/intention_survey.css">
    <jsp:include page="../../common/link.jsp"/>
    <jsp:include page="../../common/script.jsp"/>
</head>
<body>
<jsp:include page="../../common/header.jsp"/>
<div class="container">
    <jsp:include page="../../common/Ssidebar.jsp"/>
    <script type="text/javascript">
        KISSY.use('module/sidebar',function(S){
            S.ready(function(){
                PW.module.Sidebar();
            });
        });
    </script>
    <div class="content">
        <form class="J_form" action="${webSite}intentionSurvey/new" method="post" name="survey_form">
            <input type="hidden" name="statusInfoId" value="${statusInfoId}" />
            <h1 class="center f30 padding-top-20">2018届毕业生就业意向调查</h1>
            <div class="summary clearfix questionnaire">
                <div class="clearfix J_pk1 questionnaire">
                    <p>1.&nbsp;&nbsp;您的就业意向是_____。（单选题）</p>
                    <label><input type="radio" name="pk1" value="1">A.&nbsp;&nbsp;就业</label>
                    <label><input type="radio" name="pk1" value="2">B.&nbsp;&nbsp;考公务员</label>
                    <label><input type="radio" name="pk1" value="3">C.&nbsp;&nbsp;考研升学</label>
                    <label><input type="radio" name="pk1" value="4">D.&nbsp;&nbsp;保研升学</label>
                    <label><input type="radio" name="pk1" value="5">E.&nbsp;&nbsp;出国、出境</label>
                    <label><input type="radio" name="pk1" value="6">F.&nbsp;&nbsp;自主创业</label>
                    <label><input type="radio" name="pk1" value="7">G.&nbsp;&nbsp;参军入伍</label>
                    <label><input type="radio" name="pk1" value="8">H.&nbsp;&nbsp;不就业</label>
                </div>
                <div class="clearfix J_pk2 questionnaire">
                    <p>2.&nbsp;&nbsp;本科院校所属_____。（单选题）</p>
                    <label><input type="radio" name="pk2" value="1">A.&nbsp;&nbsp;东北师范大学</label>
                    <label><input type="radio" name="pk2" value="2">B.&nbsp;&nbsp;"985工程"院校</label>
                    <label><input type="radio" name="pk2" value="3">C.&nbsp;&nbsp;"211工程"院校</label>
                    <label><input type="radio" name="pk2" value="4">D.&nbsp;&nbsp;其他</label>
                    <div class="hide J_schoolName">
                        <span>选BCD请填写本科院校名称</span>
                        <input type="text" name="undergraduate" class="txt w180 J_schoolNames" maxlength="50">
                    </div>
                </div>
                <div class="clearfix J_pk3 questionnaire">
                    <label><p>3.&nbsp;&nbsp;您希望在求职过程中获得哪些方面的就业指导？_____（多选）</p>
                        <label><input type="checkbox" name="pk3" value="1">A.&nbsp;&nbsp;信息获取</label>
                        <label><input type="checkbox" name="pk3" value="2">B.&nbsp;&nbsp;简历制作</label>
                        <label><input type="checkbox" name="pk3" value="3">C.&nbsp;&nbsp;网申技巧</label>
                        <label><input type="checkbox" name="pk3" value="4">D.&nbsp;&nbsp;求职礼仪</label>
                        <label><input type="checkbox" name="pk3" value="5">E.&nbsp;&nbsp;面试技巧</label>
                        <label><input type="checkbox" name="pk3" value="6">F.&nbsp;&nbsp;就业心理咨询</label>
                        <label><input type="checkbox" name="pk3" value="7">G.&nbsp;&nbsp;教师应聘指导</label>
                        <label><input type="checkbox" name="pk3" value="8">H.&nbsp;&nbsp;教师技能训练</label>
                        <label><input type="checkbox" name="pk3" value="9">I.&nbsp;&nbsp;公考指导</label>
                        <label><input type="checkbox" name="pk3" value="10">J.&nbsp;&nbsp;基层项目指导</label>
                        <label><input type="checkbox" name="pk3" value="11">K.&nbsp;&nbsp;政策解读</label>
                        <label><input type="checkbox" name="pk3" value="12">L.&nbsp;其他</label>
                        <input type="text" name="otherGuidance" class="txt w180 hide J_other" maxlength="50">
                </div>
                <div class="hide J_pk4 questionnaire">
                    <p class="f16">就业意向选A的同学请继续，其他选项不再答题</p>
                    <p class="margin-top-20">4.&nbsp;&nbsp;您第一就业意向的地点在
                        <select name="pk4" id="J_selectProvince_1">
                            <option value="0">------请选择省份------</option>
                            <c:forEach var="province" items="${provinces}">
                                <option name="pk4Option" value="${province.id}">${province.name}</option>
                            </c:forEach>
                        </select>
                        省(市、自治区)
                        <select name="pk4" id="J_selectCity_1">
                            <option value="0" selected="selected">------请选择城市------</option>
                        </select>
                        &nbsp;市；（必填）
                    </p>
                    <p class="margin-top-20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您第二就业意向的地点在
                        <select name="pk4" id="J_selectProvince_2">
                            <option value="0">------请选择省份------</option>
                            <c:forEach var="province" items="${provinces}">
                                <option name="pk4Option" value="${province.id}">${province.name}</option>
                            </c:forEach>
                        </select>
                        省(市、自治区)
                        <select name="pk4" id="J_selectCity_2">
                            <option value="0" selected="selected">------请选择城市------</option>
                        </select>
                        &nbsp;市；
                    </p>
                    <p class="margin-top-20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您第三就业意向的地点在
                        <select name="pk4" id="J_selectProvince_3">
                            <option value="0">------请选择省份------</option>
                            <c:forEach var="province" items="${provinces}">
                                <option name="pk4Option" value="${province.id}">${province.name}</option>
                            </c:forEach>
                        </select>
                        省(市、自治区)
                        <select name="pk4" id="J_selectCity_3">
                            <option value="0" selected="selected">------请选择城市------</option>
                        </select>
                        &nbsp;市。
                    </p>
                </div>
                <div class="hide J_pk5 questionnaire">
                    <p>5.&nbsp;&nbsp;对第一份工作的预期月薪：
                        <input type="text" name="pk5" class="txt" maxlength="20">
                        元。
                    </p>
                </div>
                <div class="hide J_pk6 questionnaire">
                    <p>6.&nbsp;&nbsp;就业意向所属行业_____。（单选题）</p>
                    <label><input type="radio" name="pk6" value="1">A.&nbsp;&nbsp;教育</label>
                    <label><input type="radio" name="pk6" value="2">B.&nbsp;&nbsp;农、林、牧、渔业</label>
                    <label><input type="radio" name="pk6" value="3">C.&nbsp;&nbsp;采矿业</label>
                    <label><input type="radio" name="pk6" value="4">D.&nbsp;&nbsp;制造业</label>
                    <label><input type="radio" name="pk6" value="5">E.&nbsp;&nbsp;电力、热力、燃气及水生产和供应业</label>
                    <label><input type="radio" name="pk6" value="6">F.&nbsp;&nbsp;建筑业</label>
                    <label><input type="radio" name="pk6" value="7">G.&nbsp;&nbsp;批发和零售业</label>
                    <label><input type="radio" name="pk6" value="8">H.&nbsp;&nbsp;交通运输、仓储和邮政业</label>
                    <label><input type="radio" name="pk6" value="9">I.&nbsp;&nbsp;住宿和餐饮业</label>
                    <label><input type="radio" name="pk6" value="10">J.&nbsp;&nbsp;信息传输、软件和信息技术服务业</label>
                    <label><input type="radio" name="pk6" value="11">K.&nbsp;&nbsp;金融业</label>
                    <label><input type="radio" name="pk6" value="12">L.&nbsp;&nbsp;房地产业</label>
                    <label><input type="radio" name="pk6" value="13">M.&nbsp;&nbsp;租赁和商业服务业</label>
                    <label><input type="radio" name="pk6" value="14">N.&nbsp;&nbsp;科学研究和技术服务业</label>
                    <label><input type="radio" name="pk6" value="15">O.&nbsp;&nbsp;水利、环境和公共设施管理业</label>
                    <label><input type="radio" name="pk6" value="16">P.&nbsp;&nbsp;居民服务、修理和其他服务业</label>
                    <label><input type="radio" name="pk6" value="17">Q.&nbsp;&nbsp;卫生和社会工作</label>
                    <label><input type="radio" name="pk6" value="18">R.&nbsp;&nbsp;文化、体育和娱乐业</label>
                    <label><input type="radio" name="pk6" value="19">S.&nbsp;&nbsp;公共管理、社会保障和社会组织</label>
                    <label><input type="radio" name="pk6" value="20">T.&nbsp;&nbsp;国际组织</label>
                </div>
                <div class="hide J_pk7 questionnaire">
                    <p class="f16">就业意向所属行业选A的回答</p>
                    <p>7.&nbsp;&nbsp;如选择教育行业，您更倾向于_____？（多选题）</p>
                    <label><input type="checkbox" name="pk7" value="1">A.&nbsp;&nbsp;学前教育</label>
                    <label><input type="checkbox" name="pk7" value="2">B.&nbsp;&nbsp;小学</label>
                    <label><input type="checkbox" name="pk7" value="3">C.&nbsp;&nbsp;初中</label>
                    <label><input type="checkbox" name="pk7" value="4">D.&nbsp;&nbsp;高中</label>
                    <label><input type="checkbox" name="pk7" value="5">E.&nbsp;&nbsp;职业学校</label>
                    <label><input type="checkbox" name="pk7" value="6">F.&nbsp;&nbsp;大中专学校</label>
                    <label><input type="checkbox" name="pk7" value="7">G.&nbsp;&nbsp;高校</label>
                    <label><input type="checkbox" name="pk7" value="8">H.&nbsp;&nbsp;科研机构</label>
                    <label><input type="checkbox" name="pk7" value="9">I.&nbsp;&nbsp;培训学校</label>
                </div>
                <div class="hide J_pk8 questionnaire">
                    <p class="f16">就业意向所属行业选B-S的回答</p>
                    <p>7.&nbsp;&nbsp;在您选择的行业中，您更倾向于_____？（单选题）</p>
                    <label><input type="radio" name="pk8" value="1">A.&nbsp;&nbsp;国有企业</label>
                    <label><input type="radio" name="pk8" value="2">B.&nbsp;&nbsp;中央企业</label>
                    <label><input type="radio" name="pk8" value="3">C.&nbsp;&nbsp;民营企业</label>
                    <label><input type="radio" name="pk8" value="4">D.&nbsp;&nbsp;中外合资经营企业</label>
                    <label><input type="radio" name="pk8" value="5">E.&nbsp;&nbsp;中外合作经营企业</label>
                    <label><input type="radio" name="pk8" value="6">F.&nbsp;&nbsp;外商投资企业</label>
                    <label><input type="radio" name="pk8" value="7">G.&nbsp;&nbsp;联营企业</label>
                    <label><input type="radio" name="pk8" value="8">H.&nbsp;&nbsp;集体所有制企业</label>
                    <label><input type="radio" name="pk8" value="9">I.&nbsp;&nbsp;其他企业</label>
                    <label><input type="radio" name="pk8" value="10">J.&nbsp;&nbsp;事业单位</label>
                </div>
                <div class="hide J_pk9 questionnaire">
                    <p class="change-title-one">8.&nbsp;&nbsp;您择业时会选择哪些途径获取招聘信息？_____（多选题）</p><label>
                    <label><input type="checkbox" name="pk9" value="1">A.&nbsp;&nbsp;学校就业网</label>
                    <label><input type="checkbox" name="pk9" value="2">B.&nbsp;&nbsp;单位网站和宣传册</label>
                    <label><input type="checkbox" name="pk9" value="3">C.&nbsp;&nbsp;亲戚朋友介绍</label>
                    <label><input type="checkbox" name="pk9" value="4">D.&nbsp;&nbsp;招聘网站</label>
                    <label><input type="checkbox" name="pk9" value="5">E.&nbsp;&nbsp;导师推荐</label>
                    <label><input type="checkbox" name="pk9" value="6">F.&nbsp;&nbsp;朋辈引荐和内推</label>
                    <label><input type="checkbox" name="pk9" value="7">G.&nbsp;&nbsp;其他</label>
                </div>
                <div class="hide J_pk10 questionnaire">
                    <p class="change-title-two">9.&nbsp;&nbsp;您的就业意向单位（我们优先走访）</p>
                    <span class="margin-left-20">第一意向单位名称</span>
                    <input type="text" name="pk10" class="txt w180" maxlength="50">。</br>
                    <span class="margin-left-20">第二意向单位名称</span>
                    <input type="text" name="pk10" class="txt w180" maxlength="50">。</br>
                    <span class="margin-left-20">第三意向单位名称</span>
                    <input type="text" name="pk10" class="txt w180" maxlength="50">。
                </div>
                <div class="margin-top-30">
                    <a href="javascript:;" class="J_submit btn" type="button">确认提交</a>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    KISSY.use('page/student_page/school_roll/intentionSurvey',function(S){
        S.ready(function(){
            PW.page.student_page.school_roll.intentionSurvey();
        });
    });
</script>
<script type="text/javascript">
    $("#J_selectProvince_1").change(function(){
        var provinceId = $("#J_selectProvince_1").find("option:selected").val();
        console.log(provinceId);
        $.ajax({
            url: "${webSite}intentionSurvey/ajax/getCities",
            type: 'GET',
            data: {provinceId: provinceId},
            dataType: "json",
            success: function(res){
                $("#J_selectCity_1").empty();
                var initOption = '<option value="0" selected="selected">------请选择城市------</option>';
                $("#J_selectCity_1").append(initOption);
                $.each(res.data, function(k,p){
                    var option = "<option name='pk4Option' value='" + p.id + "'>" + p.name + "</option>";
                    $("#J_selectCity_1").append(option);
                });
            },
            error: function(err){
                console.log(err);
            }
        });
    });

    $("#J_selectProvince_2").change(function(){
        var provinceId = $("#J_selectProvince_2").find("option:selected").val();
        $.ajax({
            url: "${webSite}intentionSurvey/ajax/getCities",
            type: 'GET',
            data: {provinceId: provinceId},
            dataType: "json",
            success: function(res){
                $("#J_selectCity_2").empty();
                var initOption = '<option  value="0" selected="selected">------请选择城市------</option>';
                $("#J_selectCity_2").append(initOption);
                $.each(res.data, function(k,p){
                    var option = "<option name='pk4Option' value='" + p.id + "'>" + p.name + "</option>";
                    $("#J_selectCity_2").append(option);
                });
            },
            error: function(err){
                console.log(err);
            }
        });
    });

    $("#J_selectProvince_3").change(function(){
        var provinceId = $("#J_selectProvince_3").find("option:selected").val();
        $.ajax({
            url: "${webSite}intentionSurvey/ajax/getCities",
            type: 'GET',
            data: {provinceId: provinceId},
            dataType: "json",
            success: function(res){
                $("#J_selectCity_3").empty();
                var initOption = '<option value="0" selected="selected">------请选择城市------</option>';
                $("#J_selectCity_3").append(initOption);
                $.each(res.data, function(k,p){
                    var option = "<option name='pk4Option' value='" + p.id + "'>" + p.name + "</option>";
                    $("#J_selectCity_3").append(option);
                });
            },
            error: function(err){
                console.log(err);
            }
        });
    });
</script>
</body>
</html>
