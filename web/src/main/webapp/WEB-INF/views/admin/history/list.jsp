<!--吴晓阳 查看历史就业数据 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查看历史就业数据</title>
    <jsp:include page="../../common/link.jsp"></jsp:include>
    <jsp:include page="../../common/script.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="${staticWebsite}css/page/admin_page/students_management/dispatch/studentList.css">
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
<div class="content">
    <div class="remind"></div>
    <div class="stu_query">
        <h2 class="query_name">查询条件</h2>
        <form class="query_selects clearfix" action="" method="post" onkeydown="if(event.keyCode==13)return false;">
            <!-- 以下选项框默认选中第一个 -->
            <div class="each_query">
                <span class="query_text">性&emsp;&emsp;别</span>
                <select class="query_select" id="sex" name="sex">
                    <option value="-1" selected="selected">请选择</option>
                    <option value="1">男</option>
                    <option value="2">女</option>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">毕业年份</span>
                <select class="query_select" id="year" name="year">
                    <option value="" selected="selected">请选择</option>
                    <option value="2012">2012</option>
                    <option value="2013">2013</option>
                    <option value="2014">2014</option>
                    <option value="2015">2015</option>
                    <option value="2016">2016</option>
                    <option value="2017">2017</option>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">是否为东师信使</span>
                <select class="query_select" id="messenger" name="messenger">
                    <option value="-1" selected="selected">请选择</option>
                    <option value="1">是</option>
                    <option value="2">否</option>
                </select>
            </div>
            <shiro:hasAnyRoles name="admin,director,employ">
                <div class="each_query">
                    <span class="query_text">学&emsp;&emsp;院</span>
                    <select class="query_select" id="historical-college" name="college">
                        <option value="" selected="selected">请选择</option>
                        <c:forEach items="${collegeList}" var="college">
                            <option value="${college.collegeId}">${college.college}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="each_query linkage-major none">
                    <span class="query_text">所选学院对应的专业名称</span>
                    <select class="query_select" id="historical-college-majorCode" name="majorName">
                        <option value="" selected="selected">请选择</option>
                        <c:forEach items="${majorList}" var="major">
                            <option value="${major.majorName}">${major.majorName}</option>
                        </c:forEach>
                    </select>
                </div>
            </shiro:hasAnyRoles>
            <div class="each_query">
                <span class="query_text">专业层次</span>
                <select class="query_select" id="majorQualification" name="majorQualification">
                    <option value="" selected="selected">请选择</option>
                    <option value="2">本科生</option>
                    <option value="1">研究生</option>
                </select>
            </div>
            <div class="each_query addr">
                <span class="query_text">生源地所在省</span>
                <select class="query_select" id="originPlace" name="originPlace">
                    <option value="" selected="selected">请选择</option>
                    <c:forEach items="${provinceList}" var="originPlace">
                        <option value="${originPlace.name}">${originPlace.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="each_query">
                <span class="query_text">毕业去向</span>
                <select class="query_select" id="whereAboutGo" name="whereAboutGo">
                    <option value="" selected="selected">请选择</option>
                    <option value="1">待就业</option>
                    <option value="2">升学</option>
                    <option value="3">签就业协议形式就业</option>
                    <option value="4">灵活就业</option>
                    <option value="5">签劳动合同形式就业</option>
                    <option value="6">其他录用形式就业</option>
                    <option value="7">自由职业</option>
                    <option value="8">出国、出境</option>
                    <option value="9">自主创业</option>
                    <option value="10">地方基层项目</option>
                    <option value="11">其他暂不就业</option>
                    <option value="12">不就业拟升学</option>
                    <option value="13">国家基层项目</option>
                    <option value="14">应征义务兵</option>
                    <option value="15">科研助理</option>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">就业单位所在省</span>
                <select class="query_select" id="unitProvince" name="unitProvince">
                    <option value="" selected="selected">请选择</option>
                    <c:forEach items="${provinceList}" var="unitProvince">
                        <option value="${unitProvince.name}">${unitProvince.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="each_query">
                <span class="query_text">单位性质</span>
                <select class="query_select" id="unitProperty" name="unitProperty">
                    <option value="" selected="selected">请选择</option>
                    <option value="1">其他企业</option>
                    <option value="2">国有企业</option>
                    <option value="3">待就业</option>
                    <option value="4">其他教学单位</option>
                    <option value="5">其他灵活就业</option>
                    <option value="6">自由职业</option>
                    <option value="7">出国、出境</option>
                    <option value="8">升学</option>
                    <option value="9">其他事业单位</option>
                    <option value="10">机关</option>
                    <option value="11">部队</option>
                    <option value="12">高等教育单位</option>
                    <option value="13">其他</option>
                    <option value="14">科研设计单位</option>
                    <option value="15">中等教育单位</option>
                    <option value="16">医疗卫生单位</option>
                    <option value="17">三资企业</option>
                    <option value="18">城镇社区</option>
                    <option value="19">农村建制村</option>
                </select>
            </div>

            <div class="each_query">
                <span class="query_text">单位行业</span>
                <select class="query_select" id="unitIndustry" name="unitIndustry">
                    <option value="" selected="selected">请选择</option>
                    <option value="1">文化、体育和娱乐业</option>
                    <option value="2">教育</option>
                    <option value="3">公共管理、社会保障和社会组织</option>
                    <option value="4">科学研究与技术服务业</option>
                    <option value="5">居民服务、修理和其他服务业</option>
                    <option value="6">制造业</option>
                    <option value="7">租赁和商务服务业</option>
                    <option value="8">金融业</option>
                    <option value="9">电力、热力、燃气及水生产和供应业</option>
                    <option value="10">信息传输、软件和信息技术服务业</option>
                    <option value="11">农、林、牧、渔业</option>
                    <option value="12">交通运输、仓储和邮政业</option>
                    <option value="13">批发和零售业</option>
                    <option value="14">房地产业</option>
                    <option value="15">建筑业</option>
                    <option value="16">卫生和社会工作</option>
                    <option value="17">采矿业</option>
                    <option value="18">住宿和餐饮业</option>
                    <option value="19">军队</option>
                    <option value="20">水利、环境和公共设施管理业</option>
                </select>
            </div>
            <shiro:hasAnyRoles name="college-secretary,counsellor,student">
                <div class="each_query ">
                    <span class="query_text">专业名称</span>
                    <select class="query_select" id="historical-college-majorCode" name="majorName">
                        <option value="" selected="selected">请选择</option>
                        <c:forEach items="${majorList}" var="major">
                            <%--<option value="${major.majorName}">${major.majorCode}</option>--%>
                            <option value="${major.majorCode}">${major.majorName}</option>
                        </c:forEach>
                    </select>
                </div>
            </shiro:hasAnyRoles>

            <div class="inputs">
                <input type="button" value="查询" class="query btn" id="querybtn" name="querybtn" />
                <%--<input type="submit" value="导出excel" class="excel btn" id="excelbtn" name="excelbtn" />--%>
            </div>
        </form>
    </div>
    <div class="query_result">
        <h2 class="query_name">
            <input type="button" name="query_result" value="选择列" class="btn filter">
        </h2>
        <div class="query_filter" id="filterResult">
            <div class="filter_header">
                <h3 class="filter_name clearfix">选择显示列表项<span class="close">X</span></h3>
            </div>
            <div class="filter_main">
                <div>
                    <input type="checkbox" checked="checked" name="filter_sex" value="性别" class="filterCheck">
                    <span class="filter_title">性别</span>
                </div>
                <div>
                    <input type="checkbox" checked="checked" name="filter_year" value="毕业年份" class="filterCheck">
                    <span class="filter_title">毕业年份</span>
                </div>
                <div>
                    <input type="checkbox" checked="checked" name="filter_majorName" value="专业" class="filterCheck">
                    <span class="filter_title">专业</span>
                </div>
                <div>
                    <input type="checkbox" checked="checked" name="filter_college" value="所在学院" class="filterCheck">
                    <span class="filter_title">所在学院</span>
                </div>
                <div>
                    <input type="checkbox" checked="checked" name="filter_majorQualification" value="专业层次" class="filterCheck">
                    <span class="filter_title">专业层次</span>
                </div>
                <div>
                    <input type="checkbox" checked="checked" name="filter_unitProvince" value="就业所在地" class="filterCheck">
                    <span class="filter_title">就业所在地</span>
                </div>
                <div>
                    <input type="checkbox" checked="checked" name="filter_unitName" value="单位名称" class="filterCheck">
                    <span class="filter_title">就业单位名称</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_originPlace" value="生源所在地" class="filterCheck">
                    <span class="filter_title">生源所在地</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_messenger" value="东师信使" class="filterCheck">
                    <span class="filter_title">东师信使</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_nation" value="民族" class="filterCheck">
                    <span class="filter_title">民族</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_whereAboutGo" value="毕业去向" class="filterCheck">
                    <span class="filter_title">毕业去向</span>
                </div>
                <div>
                    <input type="checkbox"  name="filter_unitCity" value="单位所在市、州" class="filterCheck">
                    <span class="filter_title">单位所在市、州</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_unitProperty" value="单位性质" class="filterCheck">
                    <span class="filter_title">单位性质</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_unitIndustry" value="单位行业" class="filterCheck">
                    <span class="filter_title">单位行业</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_reportMode" value="报到证签发类别" class="filterCheck">
                    <span class="filter_title">报到证签发类别</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_politicalStand" value="政治面貌" class="filterCheck">
                    <span class="filter_title">政治面貌</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_normalStu" value="师范生类别" class="filterCheck">
                    <span class="filter_title">师范生类别</span>
                </div>
                <div>
                    <input type="checkbox" name="filter_difficultyId" value="困难生类别" class="filterCheck">
                    <span class="filter_title">困难生类别</span>
                </div>
                <%--<div>--%>
                    <%--<input type="checkbox" name="filter_cellphone" value="联系电话" class="filterCheck">--%>
                    <%--<span class="filter_title">联系电话</span>--%>
                <%--</div>--%>
            </div>
            <div class="filter_footer">
                <input type="checkbox" name="checkAll" class="checkAll" id="J_filterAll">
                <span>全选</span>
                <a href="javascript:;" class="view" id="filterIt">确定</a>
            </div>
        </div>
        <div class="table-box">
            <table>
                <thead>
                <tr>
                    <th>序号</th>
                    <th style="display:none;">数据库ID</th>
                    <th>姓名</th>
                    <th class="filter_sex">性别</th>
                    <th class="filter_year">毕业年份</th>
                    <th class="filter_majorName">专业</th>
                    <th class="filter_college">所在学院</th>
                    <th class="filter_majorQualification">专业层次</th>
                    <th class="filter_unitProvince">就业所在地</th>
                    <th class="filter_unitName">就业单位名称</th>
                    <th class="filter_originPlace">生源所在地</th>
                    <th class="filter_messenger">东师信使</th>
                    <th class="filter_nation">民族</th>
                    <th class="filter_whereAboutGo">毕业去向</th>
                    <%--<th class="filter_unitCity">单位所在市、州</th>--%>
                    <th class="filter_unitProperty">单位性质</th>
                    <th class="filter_unitIndustry">单位行业</th>
                    <th class="filter_reportMode">报到证签发类别</th>
                    <th class="filter_politicalStand">政治面貌</th>
                    <th class="filter_normalStu">师范生类别</th>
                    <th class="filter_difficultyId">困难生类别</th>
                    <%--<th class="filter_cellphone">联系电话</th>--%>
                </tr>
                </thead>
                <tbody id="J_template">
                </tbody>
            </table>
        </div>

        <div id="J_pagination">
            <!-- 分页区域 -->
        </div>
    </div>
</div>
<script id="query-stuInfo" type="text/template">
    {@each data as history}
    <tr>
        <td class="stuorder">&{history.index}</td>
        <td style="display:none;" class="stuId">&{history.id}</td>
        <td>&{history.name}</td>
        <td class="filter_sex">&{history.sex}</td>
        <td class="filter_year">&{history.year}</td>
        <td class="filter_majorName">&{history.majorName}</td>
        <td class="filter_college">&{history.college}</td>
        <td class="filter_majorQualification">&{history.majorQualification}</td>
        <td class="filter_unitProvince">&{history.unitProvince}&{history.unitCity}</td>
        <td class="filter_unitName">&{history.unitName}</td>
        <td class="filter_originPlace">&{history.originPlace}</td>
        <td class="filter_messenger">&{history.messenger}</td>
        <td class="filter_nation">&{history.nation}</td>
        <td class="filter_whereAboutGo">&{history.whereAboutGo}</td>
        <%--<td class="filter_unitCity">&{history.unitCity}</td>--%>
        <td class="filter_unitProperty">&{history.unitProperty}</td>
        <td class="filter_unitIndustry">&{history.unitIndustry}</td>
        <td class="filter_reportMode">&{history.reportMode}</td>
        <td class="filter_politicalStand">&{history.politicalStand}</td>
        <td class="filter_normalStu">&{history.normalStu}</td>
        <td class="filter_difficultyId">&{history.difficultyMode}</td>
        <%--difficulty--%>
        <%--<td class="filter_cellphone">&{history.cellphone}</td>--%>
    </tr>
    {@/each}
</script>
<script type="text/javascript">
    KISSY.use('page/admin_page/students_management/historical_data/dataList', function(S) {
        var urls = PW.Env.url.admin_page.students_management.historical_data.dataList;
        var url = urls.showStu;//传出来的数据
        console.log(url);
        var $ = S.all;
        S.ready(function(){
            PW.page.admin_page.students_management.historical_data.dataList({
                renderTo: '#J_pagination', //分页显示指向
                juicerRender: '#query-stuInfo', //模板渲染
                dataRender: '#J_template', //模板的数据放的地方
                pageSize:15,//每页显示的记录数
                url: url,
                /*发送ajax,必须指定ajax的url */
                configUrl:function(url,page,me,prevdata){
                    var url = url + '/' + page;//套页时使用
                    return url;
                    // return url + page;
                },
                afterDataLoad:function(that,data,page) {
                    S.log(S.all('.stuorder'));
                    S.all('.filterCheck').each(function(item) {
                        if ($(item).attr('checked') == "checked") {
                            var checkName = $(item).attr('name');
                            $('.' + checkName).show();
                        }
                        else if (!($(item).attr('checked') == "checked")) {
                            var notCheckName = $(item).attr('name');
                            $('.' + notCheckName).hide();
                        }
                    });
                },
                beforeDataLoad:function(that,data,page) {
                    S.log(S.all('.stuorder'));
                },
                type: 'get'
            });
            //console.log("ajaxing");
        });
    });
</script>
</body>
</html>
