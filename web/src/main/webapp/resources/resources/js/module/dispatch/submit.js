/*-----------------------------------------------------------------------------
 * @Description:     submit相关js

 * @Version:         1.0.0
 * @author:          chenyz(571108675@qq.com)
 * @date             2016.7.25
 * ==NOTES:=============================================
 * v1.0.0(2016.7.25):
 初始生成
 * ---------------------------------------------------------------------------*/
KISSY.add('module/dispatch/submit' , function(S, core){
    PW.namespace('module.dispatch.submit');
    PW.module.dispatch.submit = function(param){
        new core(param);
    }
},{
    requires:['submit/core',]
});
KISSY.add('submit/core' , function(S){
        var
            $ = S.all, on = S.Event.on, DOM = S.DOM,
            Dialog = PW.mod.Dialog,
            SubmitIO = PW.io.module.submit,//定义一个io层入口
            el = {
                J_input : '.input',
                J_valid_tip : '.valid-tip',
                J_form :'.form',
                J_submit : '.submit',
                J_email : '.email',
                J_clear : '.clear',
                J_change_color : '.change-color',
                J_postcode_one : '.postcode-one',
                J_postcode_two : '.postcode-two',
                J_fax : '.fax',
                J_phone_one : '.phone-one',
                J_phone_two : '.phone-two',
                J_mobile_phone : '.mobile-phone',
                notNull : '.J_notNull',
                notOption : '.J_notOption',
                required: '.required',
            },
            myVar = {
                //定义的变量
                emailTip : '请输入正确的邮箱',
                postcodeTip : '请输入正确的邮编',
                faxTip : '请输入正确的传真',
                phoneTip : '请输入正确的电话号码',
                mobilePhoneTip : '请输入正确的手机号码',
            };

        function submit(param){
            this.init();
        }

        S.augment(submit,{
            init:function(){

                this._addEventListener();
            },
            _addEventListener:function(){
                var
                    that = this;
                that._formSubmit();
                $(".clear").on("click",function(ev){
                    $(this).one(el.J_valid_tip).text("");
                });
            },
            /*表单提交*/
            _formSubmit:function(ev){
                var
                    that = this,
                    valid = that.valid,
                    result = true;
                var flag1,flag2,flag3,flag4,flag5;
                $(el.J_submit).on("click",function(ev){
                    $(el.notOption).each(function(){
                        selectVal=$(this).children('option:selected').val();
                        if(selectVal==-1||selectVal==0){
                            flag1=true;
                            $(this).css("border","1px solid #d15b47");
                        }else{
                            $(this).css("border","1px solid #ccc");
                        }
                    });
                    $(el.notNull).each(function(){
                        textVal=$(this).val();
                        if(textVal==""){
                            flag2=true;
                            $(this).css("border","1px solid #d15b47");
                        }else{
                            $(this).css("border","1px solid #ccc");
                        }
                    });
                    $(el.notNull).on('change',function(){
                        textVal=$(this).val();
                        if(textVal==""){
                            $(this).css("border","1px solid #d15b47");
                        }else{
                            flag2=false;
                            $(this).css("border","1px solid #ccc");
                        }
                    });

                    // 电子邮箱验证
                    if(!(/^(([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5}){1,25})$/.test($('input', el.J_email).val()))&& ($('input', el.J_email).val().length!=0 )){
                        $(el.J_email).one(el.J_valid_tip).show().text(myVar.emailTip);
                        ev.preventDefault();
                    };
                    // 邮政编码验证
                    if(!(/^[1-9]\d{5}(?!\d)$/.test($('input', el.J_postcode_one).val()))&& ($('input', el.J_postcode_one).val().length!=0 )){
                        $(el.J_postcode_one).one(el.J_valid_tip).show().text(myVar.postcodeTip);
                        ev.preventDefault();
                    };

                    // 传真验证
                    if(!(/^[0-9\-]+$/.test($('input', el.J_fax).val()))&& ($('input', el.J_fax).val().length!=0 )){
                        $(el.J_fax).one(el.J_valid_tip).show().text(myVar.faxTip);
                        ev.preventDefault();
                    };
                    // 电话号码验证
                    if(!(/^\d{3}-\d{8}|\d{4}-\d{7}|\d{8}|\d{7}$/.test($('input', el.J_phone_one).val()))&& ($('input', el.J_phone_one).val().length!=0 )){
                        $(el.J_phone_one).one(el.J_valid_tip).show().text(myVar.phoneTip);
                        ev.preventDefault();
                    };
                    // 手机验证
                    if(!(/^[1][358][0-9]{9}$/.test($('input', el.J_mobile_phone).val()))&& ($('input', el.J_mobile_phone).val().length!=0 )){
                        $(el.J_mobile_phone).one(el.J_valid_tip).show().text(myVar.mobilePhoneTip);
                        ev.preventDefault();
                    };

                    if (flag1 || flag2 || flag3 || flag4){
                        return false;
                    }else{
                        return true;
                    }

                });

            }

        });
        return submit;
    },
    {
        requires:['mod/dialog','io/module/submit']
    });