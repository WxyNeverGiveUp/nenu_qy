/*-----------------------------------------------------------------------------
* @Description:     填写学籍信息页面相关js
* @Version:         1.0.0
* @author:          qiyuan
* @date             2016.7.14
* ==NOTES:=============================================
* v1.0.0(2016.7.14):
     初始生成
* ---------------------------------------------------------------------------*/
/*给它制造命名空间，专门存放各个页面js,被主页面调用*/
KISSY.add('page/student_page/school_roll/writeInfo' , function(S, schoolRoll, suggest, linkage, save){
    PW.namespace('page.student_page.school_roll.writeInfo');
    PW.page.student_page.school_roll.writeInfo = function(param){
        new schoolRoll(param);
        new suggest(param);
        new linkage(param);
        new save(param);
    }
},{
    requires:['writeInfo/suggest', 'writeInfo/schoolRoll', 'writeInfo/linkage', 'writeInfo/save']
});
/*---------------------------------------------------------------------------*/
/*开始改页面模块的js内容*/
KISSY.add('writeInfo/schoolRoll' , function(S){
    var
        schoolRoll = PW.module.schoolRoll;
    function schoolRoll(param){
        new schoolRoll(param);
    }

    return schoolRoll;
},{
    requires:['module/schoolRoll']
});
/*---------------------------------suggest------------------------------------*/
KISSY.add('writeInfo/suggest',function(S){
    var
        suggest = PW.module.suggest;
    function suggest(param){
        new suggest(param);
    }

    return suggest;
},{
    requires:['module/suggest']
});
/*---------------------------------linkage------------------------------------*/
KISSY.add('writeInfo/linkage',function(S){
    var
        linkage = PW.module.linkage;
    function linkage(param){
        new linkage(param);
    }

    return linkage;
},{
    requires:['module/linkage']
});
/*-----------------------------保存-------------------------------*/
KISSY.add('writeInfo/save', function(S){
    var
        $ = S.all, on = S.Event.on, DOM = S.DOM,
        el = {
            save: '.J_save'//保存 
        };
    function save(){
        this.init();
    }
    S.augment(save, {
        init: function(){
            this._addEventListener();
        },
        _addEventListener: function(){
            var 
                that = this;
            // 保存
            $(el.save).on('click', function(ev) {
                var flag = new Array;
                // 手机号码
                if(!(/^1[3|4|5|7|8][0-9]{9}$/.test($(".J_phone").val())) && ($(".J_phone").val().length!=0)){
                    flag.push('1');
                    $(".J_phone_error").show();
                    $(".J_phone").val("");
                }
                // QQ
                if(!(/^[0-9]+$/.test($(".j_QQNum").val())) && ($(".j_QQNum").val().length!=0)){
                    flag.push('2');
                    $(".J_QQNum_error").show();
                    $(".j_QQNum").val("");
                }
                // 邮箱
                if(!(/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test($(".J_email").val())) && ($(".J_email").val().length!=0)){
                    flag.push('3');
                    $(".J_email_error").show();
                    $(".J_email").val("");
                }
                // 家庭电话
                if(!(/^((1[3|4|5|7|8]\d{9})|((\d{3,4}\-)|)\d{7,8}(|([-\u8f6c]{1}\d{1,5})))$/.test($(".J_homePhone").val())) && ($(".J_homePhone").val().length!=0)){
                    flag.push('4');
                    $(".J_homePhone_error").show();
                    $(".J_homePhone").val("");
                }
                // 家庭住址
                if(!(/^[\u4E00-\u9FA5A-Za-z0-9-]+$/.test($(".J_address").val())) && ($(".J_address").val().length!=0)){
                    flag.push('5');
                    $(".J_address_error").show();
                    $(".J_address").val("");
                }
                // 时间验证
                var s1 = $(".J_sDate").val();
                var s2 = $(".J_eDate").val();
                var sDate = new Date(s1.replace(/\-/g,'/'));
                var eDate = new Date(s2.replace(/\-/g,'/'));
                // 如果开始时间大于结束时间
                if (sDate > eDate) {
                    flag.push('1');
                    $(".J_date_error").show();
                    $(".J_date").val("");
                }
                if($('#J_localAreaHolder').next().text() != ''){
                    flag.push('1');
                }

                if(flag.length == 0){
                    document.write_form.submit();
                }
                else{
                    alert('所填写信息不正确,请重新填写');
                }

            });
        },
    });
    return save;
},{
    requires: ['core']
})
