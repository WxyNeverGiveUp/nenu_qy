/*-----------------------------------------------------------------------------
 * @Description:     历史数据-查看历史数据相关js
 * @Version:         n.n.n
 * @author:          wuxiaoyang(15290491277@163.com)
 * @date:            2017.7.25
 * ==NOTES:=============================================
 * vn.n.n(2017.7.25):
 初始生成
 * ---------------------------------------------------------------------------*/
/*给它制造命名空间，专门存放各个页面js,被主页面调用*/
KISSY.add('page/admin_page/students_management/historical_data/dataList', function(S,List,linkage) {
    PW.namespace('page.admin_page.students_management.historical_data.dataList');
    PW.page.admin_page.students_management.historical_data.dataList = function(param) {
        new List(param);
        new linkage(param);
    };
},{
    requires:['dataList/List','dataList/linkage']
});
/*-------------------------------------------------------------------*/
/*开始改页面模块的js内容*/
KISSY.add('dataList/List', function(S) {
        var $ = S.all,
            Pagination = PW.mod.Pagination,
            Defender = PW.mod.Defender,
            Dialog = PW.mod.Dialog,        //定义要使用的组件

            urls = PW.Env.url.admin_page.students_management.historical_data.dataList, //定义数据入口

            el = {
                majorQualification: '#majorQualification', // 专业层次
                sex: '#sex', // 性别
                year: '#year',// 毕业年份
                messenger: '#messenger', // 东师信使
                college: '#historical-college', // 学院
                majorName: '#historical-college-majorCode', // 专业
                originPlace: '#originPlace', // 生源所在地
                whereAboutGo: '#whereAboutGo', //毕业去向
                unitProvince: '#unitProvince', // 就业单位所在省份
                unitProperty: '#unitProperty', // 单位性质
                unitIndustry: '#unitIndustry', // 单位行业
                linkageMajor: '.linkage-major', // 校级联动专业
                querybtn: '#querybtn', // 查询按钮
                filter:'.filter', // 查询筛选按钮
                filterCheck: '.filterCheck', // 筛选的选项
                filterAll: '#J_filterAll', // 筛选的全选选项
                filterResult: '#filterResult', // 筛选列表
                filterIt: '#filterIt', // 筛选按钮
                close:'.close',
                ids:'.stuId',
                viewbtn:'.view',
                delbtn:'.delete',
            };


        function List(param) {
            this.opts = param; //分页调用的参数,刷分页，永远都要用到它
            this.pagination;
            this._init();
        }

        S.augment(List, {
            // _init 入口函数
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
                /*--点击查询按钮--*/
                $(el.querybtn).on('click',function(ev){
                    var sex = $(el.sex).children('option:selected').val(),
                        year = $(el.year).children('option:selected').val(),
                        messenger = $(el.messenger).children('option:selected').val(),
                        college = $(el.college).children('option:selected').val(),
                        majorName = $(el.majorName).children('option:selected').val(),
                        majorQualification = $(el.majorQualification).children('option:selected').val(),
                        originPlace = $(el.originPlace).children('option:selected').val(),
                        whereAboutGo = $(el.whereAboutGo).children('option:selected').val(),
                        unitProvince = $(el.unitProvince).children('option:selected').val(),
                        unitProperty = $(el.unitProperty).children('option:selected').val(),
                        unitIndustry = $(el.unitIndustry).children('option:selected').val();

                    S.mix(opts, {extraParam:{
                        sex:sex,
                        year:year,
                        messenger:messenger,
                        college:college,
                        majorName:majorName,
                        majorQualification:majorQualification,
                        originPlace:originPlace,
                        whereAboutGo:whereAboutGo,
                        unitProvince:unitProvince,
                        unitProperty:unitProperty,
                        unitIndustry:unitIndustry,

                    }});//想传给后台的数据
                    that.pagination.reload(opts);
                });

                /*回车提交表单---与上面查看相关联*/
                document.onkeydown=function(event){
                    var e = event ? event :(window.event ? window.event : null);
                    if(e.keyCode==13){
                        var sex = $(el.sex).children('option:selected').val(),
                            year = $(el.year).children('option:selected').val(),
                            messenger = $(el.messenger).children('option:selected').val(),
                            college = $(el.college).children('option:selected').val(),
                            majorName = $(el.majorName).children('option:selected').val(),
                            majorQualification = $(el.majorQualification).children('option:selected').val(),
                            originPlace = $(el.originPlace).children('option:selected').val(),
                            whereAboutGo = $(el.whereAboutGo).children('option:selected').val(),
                            unitProvince = $(el.unitProvince).children('option:selected').val(),
                            unitProperty = $(el.unitProperty).children('option:selected').val(),
                            unitIndustry = $(el.unitIndustry).children('option:selected').val();

                        S.mix(opts, {extraParam:{
                            sex:sex,
                            year:year,
                            messenger:messenger,
                            college:college,
                            majorName:majorName,
                            majorQualification:majorQualification,
                            originPlace:originPlace,
                            whereAboutGo:whereAboutGo,
                            unitProvince:unitProvince,
                            unitProperty:unitProperty,
                            unitIndustry:unitIndustry,
                        }});//想传给后台的数据
                        that.pagination.reload(opts);
                        // console.log('成功执行');
                    }
                };

                /*阻止空表单提交*/
                $('.excel').on('click',function (ev) {
                    if($('.stuorder').length == 0){
                        alert("暂无数据可打印");
                        return false;
                    }
                });

                /*--查询条件筛选--*/
                $(el.filter).on('click', function(ev) {
                    $(el.filterResult).show();
                });
                $(el.close).on('click', function(event) {
                    $(el.filterResult).hide();
                });

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
                })


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
                    $(el.linkageMajor).removeClass('none');
                    if ($(el.college).val() == -1){
                        $(el.linkageMajor).addClass('none');
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
            }
        });

        return List;

    },
    {
        requires:['event','sizzle', 'mod/pagination', 'io/admin_page/students_management/historical_data/dataList']
    });
/*---------------------------------linkage------------------------------------*/
KISSY.add('dataList/linkage',function(S){
    var
        linkage = PW.module.linkage;
    function linkage(param){
        new linkage(param);
    }
    return linkage;
},{
    requires:['core','module/linkage']
});
