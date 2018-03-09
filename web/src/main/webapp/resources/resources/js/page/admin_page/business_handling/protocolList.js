/*-----------------------------------------------------------------------------
* @Description:     业务受理-协议业务列表相关js
* @Version:         n.n.n
* @author:          lixingyu(starsuniverseLi@gmail.com)
* @date             2016.7.14
* ==NOTES:=============================================
* vn.n.n(2016.7.14):
     初始生成
* ---------------------------------------------------------------------------*/
/*给它制造命名空间，专门存放各个页面js,被主页面调用*/
KISSY.add('page/admin_page/business_handling/protocolList', function(S, List, linkage) {
	PW.namespace('page.admin_page.business_handling.protocolList');
    PW.page.admin_page.business_handling.protocolList = function(param) {
        new List(param);
        new linkage(param);
    };
},{
    requires:[
        'protocolList/List','protocolList/linkage'
    ]
    
});
/*-------------------------------------------------------------------*/
/*开始改页面模块的js内容*/
KISSY.add('protocolList/List', function(S) {
	var $ = S.all,
        on = S.Event.on, //绑定静态节点
        delegate = S.Event.delegate, //绑定静态节点

        protocolIO = PW.io.admin_page.business_handling.protocolList, //定义一个IO层入口

        Pagination = PW.mod.Pagination,
        Calendar = PW.mod.Calendar,
        Defender = PW.mod.Defender,
        Dialog = PW.mod.Dialog,        //定义要使用的组件

        urls = PW.Env.url.admin_page.business_handling.protocolList, //定义数据入口

        el = {
           stuType:'#stuType',
           sex: '#sex',
           college: '#college',
           major: '#majorCode',
           qualification: '#qualification',
           normalStu: '#normalStu',
           originPlace: '#originPlace',
           stuLength: '#stuLength', // 学制
           isRegistered:"#isRegistered",
           trainingMode: '#trainingMode',// 新增培养方式protocolType:'#protocolType',
           protocolType: '#protocolType',
           querybtn: '#querybtn',
           beginTime:"#beginTime",
           endTime:"#endTime",
           keyword:"#keyword",
           checkProtocolResult:"#checkProtocolResult",

           querybtn: '#querybtn',
            //辅导员
           counsellorCheckResult:'#counsellorCheckResult',
           deputySecretaryCheckResult: '#deputySecretaryCheckResult',
           // 新增性别 专业 政治面貌
           linkageMajor: '.linkage-major', // 校级联动专业
           // majorCode: '.major_input',  // 传的是专业名称而不是专业的ID
           orders:'.order',
           filter:'.filter', // 查询筛选按钮
           filterCheck: '.filterCheck', // 筛选的选项
           filterAll: '#J_filterAll', // 筛选的全选选项
           filterResult: '#filterResult', // 筛选列表
           filterIt: '#filterIt', // 筛选按钮
           passAll: '#J_passAll', // 批量通过的全选选项
           checkToPass: '.checkToPass', // 批量通过每一个学生的选择选项
           pass: '.pass',  // 批量通过按钮
           repulse: '.repulse',  // 批量打回
           close:'.close',
           ids:'.stuId',
           viewbtn:'.viewIt',
           delbtn:'.delete',
           qualificationNow:'#qualificationNow',
           showUncommitted:'#showUncommitted',

        };

        function List(param) {
            this.opts = param; //分页调用的参数,刷分页，永远都要用到它
            this.pagination;
            this._init();
        }

        S.augment(List, {
            _init: function() {
                this._pagination();
                this._addEventListener();
                this._filter();
                
            },

            _pagination: function(selectParam) {
                var
                    that = this,
                    opts = that.opts;
                    that.pagination = Pagination.client(opts);
            },

            _addEventListener: function() {
                var 
                    that = this,
                    opts = that.opts;
                    that._addCalendar();

               
                /*--点击查询按钮--*/
                 $('body').delegate('click',el.querybtn,function(ev){
                    var stuType = $(el.stuType).children('option:selected').val(),
                     sex = $(el.sex).children('option:selected').val(),
                        college = $(el.college).children('option:selected').val(),
                        major = $(el.major).children('option:selected').val(),
                        qualification = $(el.qualification).children('option:selected').val(),
                        normalStu = $(el.normalStu).children('option:selected').val(),
                        originPlace = $(el.originPlace).children('option:selected').val(),
                        stuLength = $(el.stuLength).children('option:selected').val(),
                        isRegistered = $(el.isRegistered).children('option:selected').val(),
                        trainingMode = $(el.trainingMode).children('option:selected').val(),
                        protocolType = $(el.protocolType).children('option:selected').val(),
                        checkProtocolResult = $(el.checkProtocolResult).children('option:selected').val();
                    var beginTime = $(el.beginTime).val() ;
                    var keyword = $(el.keyword).val();
                    if (keyword == '学号或姓名') {
                        keyword = '';
                    };
                    var endTime = $(el.endTime).val();
                    var checkProtocolResult = $(el.checkProtocolResult).children('option:selected').val();
                    S.mix(opts, {extraParam:
                                    {
                                    stuType :stuType,
                                    sex:sex,
                                    college :college,
                                    major :major,
                                    qualification:qualification,
                                    normalStu:normalStu,
                                    originPlace:originPlace,
                                    stuLength:stuLength,
                                    isRegistered:isRegistered,
                                    trainingMode: trainingMode,
                                    protocolType:protocolType,
                                    checkProtocolResult:checkProtocolResult,
                                    beginTime:beginTime,
                                    endTime:endTime,
                                    keyword:keyword,
                                    checkProtocolResult :checkProtocolResult,
                                    }
                                });//想传给后台的数据
                    that.pagination.reload(opts);
                });

                 /*--绑定回车查看--*/
                document.onkeydown=function(event){
                    var e = event ? event :(window.event ? window.event : null);
                    if(e.keyCode==13) {
                        var stuType = $(el.stuType).children('option:selected').val(),
                            sex = $(el.sex).children('option:selected').val(),
                            college = $(el.college).children('option:selected').val(),
                            major = $(el.major).children('option:selected').val(),
                            qualification = $(el.qualification).children('option:selected').val(),
                            normalStu = $(el.normalStu).children('option:selected').val(),
                            originPlace = $(el.originPlace).children('option:selected').val(),
                            stuLength = $(el.stuLength).children('option:selected').val(),
                            isRegistered = $(el.isRegistered).children('option:selected').val(),
                            trainingMode = $(el.trainingMode).children('option:selected').val(),
                            protocolType = $(el.protocolType).children('option:selected').val(),
                            checkProtocolResult = $(el.checkProtocolResult).children('option:selected').val();
                        var beginTime = $(el.beginTime).val();
                        var keyword = $(el.keyword).val();
                        if (keyword == '学号或姓名') {
                            keyword = '';
                        }
                        ;
                        var endTime = $(el.endTime).val();
                        var checkProtocolResult = $(el.checkProtocolResult).children('option:selected').val();
                        S.mix(opts, {
                            extraParam: {
                                stuType: stuType,
                                sex: sex,
                                college: college,
                                major: major,
                                qualification: qualification,
                                normalStu: normalStu,
                                originPlace: originPlace,
                                stuLength: stuLength,
                                isRegistered: isRegistered,
                                trainingMode: trainingMode,
                                protocolType: protocolType,
                                checkProtocolResult: checkProtocolResult,
                                beginTime: beginTime,
                                endTime: endTime,
                                keyword: keyword,
                                checkProtocolResult: checkProtocolResult,
                            }
                        });//想传给后台的数据
                        that.pagination.reload(opts);
                    }
                };
                
                
                 /*--点击查看按钮--*/
                $('body').delegate('click',el.viewbtn,function(ev){
                    var $target = $(ev.target);
                    var nowpage = $(".check").text();
                    var nowStuOrder = $target.parent().parent().first().text();
                    // var lastStuOrder = $target.parent().parent().parent().last().first().text();
                    var nowStuId = $target.parent().parent().first().next().text();
                    var nowProtocolId = $target.parent().parent().first().next().next().text();
                    var beginTime = $(el.beginTime).val();
                    var keyword = $(el.keyword).val();
                    var endTime = $(el.endTime).val();
                    var checkProtocolResult = $(el.checkProtocolResult).children('option:selected').val();

                        if( keyword == '学号或姓名'){
                            keyword = '';
                        };
                        
                        if( nowpage == undefined){
                            nowpage = 1;
                        };
                    var nowStuArray = new Array();
                    var stuIds = $(".stuId");
                    var protocolId = $(".protocolId");
                    for (var i = 0; i < protocolId.length; i++) {
                        nowStuArray.push(protocolId[i].innerHTML);
                    };
                    var para = {
                                curPage:nowpage, //当前页码
                                curNo:nowStuOrder,//当前点击学生的序号
                                id: nowStuId,//当前点击学生的数据库ID
                                protocolId: protocolId, //当前协议号ID
                                idList:nowStuArray,//当前页的ID顺序列表
                                conditions:{
                                    beginTime:beginTime,
                                    endTime:endTime,
                                    keyword:keyword,
                                    checkProtocolResult :checkProtocolResult,
                                }
                        };
                    protocolIO.nowInfo(para,function(code,data,msg){
                        var protocolId = $target.parent().parent().first().next().next().text();
                            if(code == 0){
                               window.location.href="/admin/protocol/new/detail/" +protocolId;
                            }
                    });
                    //console.log(para);
                });

                 /*输入框的特效*/
                $(el.keyword).on('focusin',function(ev){
                    $(el.keyword).val("");
                });
                
                
                /*--查询条件筛选--*/
                $(el.filter).on('click', function(ev) {
                    $(el.filterResult).show();
                });
                $(el.close).on('click', function(event) {
                    $(el.filterResult).hide();
                });
                /*--点击批量通过--*/
                $('body').delegate('click',el.pass,function(ev) {
                    var $target = $(ev.target);
                    var nowStuArray = new Array(); // 增添一个数组，把选择到的id放到这个数组中
                    var nowStuId = "";
                    var nowpage = $('.check').text(); // 当前页码
                    var result = confirm("是否确认审核通过？")
                    // 先定义一下当前对应复选框所对应的学号，把多个学号作为数组传入！
                    $('.checkToPass:checked').each(function() {
                      nowStuId = this.parent().parent().first().next();
                      for (var i = 0; i < nowStuId.length; i++) {
                          nowStuArray.push(nowStuId[i].innerHTML);
                      }
                    });
                    var para = {
                      id: nowStuArray,
                      page: nowpage
                    };
                    // 如果数组为长度为0（即没有值）的话 返回false
                    if (nowStuArray.length && result == true) {
                        school_rollIO.filter(para,function(code,data,msg){
                                if(code == 0){
                                  that.pagination.reload(opts);
                                  $(el.passAll).attr('checked', false);
                                }
                                else if(code == 1) {
                                    alert(msg);
                                }
                                // console.log(code);
                        });
                        // console.log(para);
                    }
                    else{
                      return false;
                    }
                });
                /*--批量通过或者批量打回的全选功能--*/
                $('body').delegate('click',el.passAll,function(ev) {
                  // 如果全选选项选择，则给每个都添加选中
                    if ($(el.passAll).attr('checked')) {
                      $(el.checkToPass).attr('checked',true);
                    }
                    else{
                      $(el.checkToPass).attr('checked',false);
                    }
                })

                /*--仿省系统的功能--*/
                /*--全选--*/
                $('body').delegate('click',el.filterAll,function(ev) {
                    if ($(el.filterAll).attr('checked')) {
                      $(el.filterCheck).attr('checked', true);
                    }
                    else{
                      $(el.filterCheck).attr('checked', false);
                    }
                });

                /*--点击多选框时判断是否全选--*/
                $(el.filterCheck).on('click',function() {
                    var flag = true;
                    $(el.filterCheck).each(function() {
                        if(!this.checked) {
                            flag = false;
                        }
                    });
                    $(el.filterAll).attr('checked',flag);
                });


                /*--筛选--*/
                /*--将checked隐藏*/
                $('body').delegate('click',el.filterIt,function(ev) {
                  that._filter();
                  $(el.filterResult).hide();
                });
                $('body').delegate('click', '#J_pagination', function(ev) {
                  that._filter()
                });
                /*校级领导的学院-专业联动*/
                $(el.college).on('change',function(ev) { /*当选择变化的时候发生改变*/
                  var $target = $(ev.target);
                  $(el.linkageMajor).show();
                  if ($(el.college).val() == -1){
                    $(el.linkageMajor).hide();
                  }
                });
            },
            
            
            /*筛选的函数*/
            _filter: function() {
                $(el.filterCheck).each(function() {
                    if (this.attr('checked') == "checked") {
                        var checkName = this.attr('name');
                        $('.' + checkName).show();
                    }
                    else if (!(this.attr('checked') == "checked")) {
                        var notCheckName = this.attr('name');
                        $('.' + notCheckName).hide();
                    }
                });
            },
              
              

            /*添加日历*/
            _addCalendar:function(){
                S.each($(".J_date"),function(i){
                    Calendar.client({
                        renderTo: i, //默认只获取第一个
                        select: {
                            rangeSelect: false, //是否允许区间选择
                            popup:true,
                            triggerType:['click'],
                            dateFmt: 'YYYY-MM-DD',
                            showTime: false ,//是否显示时间
                            closable:true
                        }
                    });
                });
            }

        });

        return List;

},
{
	requires:['event','sizzle', 'mod/calendar','mod/pagination', 'io/admin_page/business_handling/protocolList']
});
/*---------------------------------linkage------------------------------------*/
KISSY.add('protocolList/linkage',function(S){
    var
        linkage = PW.module.linkage;
    function linkage(param){
        new linkage(param);
    }
    return linkage;
},{
    requires:['core','module/linkage']
});