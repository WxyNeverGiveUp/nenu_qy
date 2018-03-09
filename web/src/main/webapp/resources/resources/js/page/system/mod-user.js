/*-----------------------------------------------------------------------------
* @Description:     修改用户相关js
* @Version:         1.0.0
* @author:          chenke(396985267@qq.com)
* @date             2014.10.04
* ==NOTES:=============================================
* v1.0.0(2014.10.04):
     初始生成
* ---------------------------------------------------------------------------*/
KISSY.add('page/system/mod-user',function(S,core){
	PW.namespace('page.modUser');
	PW.page.modUser = function(param){
		new core;
	}
},{
	requires:['modUser/core']
});
/*-------------------------------表单提交-----------------------------*/
KISSY.add('modUser/core',function(S){
	var
		$ = S.all, on = S.Event.on, DOM = S.DOM,
		Defender = PW.mod.Defender,
		Dialog = PW.mod.Dialog,
		UserIO = PW.io.system.user,
		LinkageIO = PW.io.module.linkage,
		el = {
			submitBtn:'.J_submitBtn',//指向提交按钮
			username:'#J_username',//指向用户名表单
            qualification: '.J_qualification',//学生类别
			majorClass: '.J_majorClass',//专业大类
            majorMinorClass: '.J_majorMinorClass',//专业中类
            notOption: '.J_notOption',//专业中类
			majorName: '.J_majorName'//专业名称
		};
	function core(param){
		this.opts = S.merge(el,param);
		this.init();
	}

	S.augment(core,{
		init:function(){
			this.valid = Defender.client('form',{
				showTip:false
			});
			this._addEventListener();
		},
		_addEventListener:function(){
			var
				that = this;

			var counselor_checkBox = $(".content-select input[value=5]");
            var counselor_checked = counselor_checkBox.prop("checked");
            if(counselor_checked == true){
                $(".add-linkage").on("click",function(ev){
                    //添加级联
                    $(".J_majorClass").attr("class","confirm-majorClass J_notOption J_majorClass");
                    var linkage_html = $(
                        "<div class='content-major'>"+
                        "<span class='major-name'>"+
                        "<label class='must no-matter'>专业名称：</label>"+
                        "<select name='qualification' class='J_qualification J_notOption' autocomplete='off'>"+
                        "<option value='-1'>请选择</option><option value='1'>本科专业</option><option value='2'>研究生专业</option>" +
                        "</select>"+
                        "<label>------</label>"+
                        "<select class='J_majorClass J_notOption' autocomplete='off'>"+
                        "<option value='-1'>请选择专业大类</option>"+
                        "</select>"+
                        "<label>------</label>"+
                        "<select class='J_majorMinorClass J_notOption' autocomplete='off'>"+
                        "<option value='-1'>请选择专业中类</option>"+
                        "</select>"+
                        "<label>------</label>"+
                        "<select name='majorCode' class='J_majorName J_notOption' autocomplete='off'>"+
                        "<option value='-1'>请选择专业</option>"+
                        "</select>"+
                        "<span class='del-linkage'>删除</span>"+
                        "</span>"+
                        "</div>"
                    );
                    var add_box = $(".add-box");
                    linkage_html.appendTo(add_box);
                    var
                        qualificationId = 0,
                        majorClassId = 0,
                        majorMinorId = 0;
                    //专业联动
                    that._getClass();
                    that._getMinorClass();
                    that._getMajor();
                    //当点击专业联动的最后一个select时，移除报红的错误处理class
                    $("select[name='majorCode']").on("click",function(ev){
                        var choose_major = $("select[name='majorCode']").children("option");
                        if (choose_major[0].selected !== true){
                            $(".major-name select").removeClass("error-field");
                        }
                    });
                    //确定级联，确认之后select为disabled。在表单提交按钮被点击的时候，移除disabled属性；
                    //并且，当确认点击时，如果没有选专业，会给红色边框提示
                    // $(".confirm-linkage").on("click",function(ev){
                    //     var choose_major = $(this).parent().children("select[name='majorCode']").children("option");
                    //
                    //     if (choose_major[0].selected == true){
                    //         $(this).parent().children("select").addClass("error-field");
                    //
                    //     }else {
                    //         $(this).parent().children("select").removeClass("error-field");
                    //         $(".J_majorClass").attr("class","confirm-majorClass");
                    //         $(this).parent().attr("class","confirm-major");
                    //         $(".confirm-major select").attr("disabled","disabled");
                    //     }
                    // });
                    //删除级联
                    $(".del-linkage").on("click",function(ev){
                        $(this).parent().parent().remove();

                    });
                });
            }
			$(".del-existing").on("click",function(ev){
				$(this).parent().remove();
			});
			/*表单提交*/
			on(el.submitBtn,'click',function(e){
                // console.log($('.add-box .child').length > 0);
                e.preventDefault();
                var flag = false;

                $(".J_notOption").each(function(){
                    selectVal=$(this).children('option:selected').val();
                    if(selectVal==-1){
                        flag = true;
                        $(this).addClass("error-field");
                    }else{
                        $(this).removeClass("error-field");

                    }
                });
                if (flag == true){
                    return false;

                }else{
                    $(".add-box select").removeAttr("disabled","disabled");
                    that._submit();
                }
			});
		},
        // 专业联动，获取json中的专业大类
        _getClass:function(){
            var
                that = this;
            $(el.qualification).on('change',function(ev){
                $(".other-major").hide().children("input").attr("disabled", "true");
                var qualificationId = $(ev.currentTarget).children('option:selected').val();
                LinkageIO.majorClassIO({qualification:qualificationId} , function(rs,data,msg){
                    if(rs){
                        var
                            that = this,
                            opts = that.opts,
                            majorClassOptions = '<option value="-1">请选择专业大类</option>';
                        S.each(data,function(majorClass,o){
                            majorClassOptions = majorClassOptions + '<option value="'+majorClass.majorCode+'">'+majorClass.majorName+'</option>';
                        });
                        $currentTarget = $(ev.currentTarget).parent().children(".J_majorClass");
                        $currentTarget.html(majorClassOptions);
                        $currentTarget.val(0);
                        $(ev.currentTarget).parent().children(".J_majorMinorClass").val(0);
                        $(ev.currentTarget).parent().children(".J_majorName").val(0);
                    }
                });
            });
        },
        // 专业联动，获取json中的专业中类
        _getMinorClass:function(){
            var
                that = this;
            $(el.majorClass).on('change',function(ev){
                $(".other-major").hide().children("input").attr("disabled", "true");
                var majorClassId = $(ev.currentTarget).children('option:selected').val();
                var qualificationId = $(el.qualification).children('option:selected').val();
                LinkageIO.majorMinorClassIO({majorCode:majorClassId, qualification:qualificationId} , function(rs,data,msg){
                    if(rs){
                        var
                            that = this,
                            opts = that.opts,
                            MinorClassOptions = '<option value="-1">请选择专业中类</option>';
                        S.each(data,function(minorClass,o){
                            MinorClassOptions = MinorClassOptions + '<option value="'+minorClass.majorCode+'">'+minorClass.majorName+'</option>';
                        });
                        $currentTarget = $(ev.currentTarget).parent().children(".J_majorMinorClass");
                        $currentTarget.html(MinorClassOptions);
                        $currentTarget.val(0);
                        $(ev.currentTarget).parent().children(".J_majorName").val(0);
                    }
                });
            });
        },
        // 专业联动，获取json中的专业
        _getMajor:function(){
            var
                that = this;
            $(el.majorMinorClass).on('change',function(ev){
                $(".other-major").hide().children("input").attr("disabled", "true");
                var majorMinorClassId = $(ev.currentTarget).children('option:selected').val();
                var qualificationId = $(el.qualification).children('option:selected').val();
                LinkageIO.majorNameIO({majorCode:majorMinorClassId, qualification:qualificationId} , function(rs,data,msg){
                    if(rs){
                        var
                            that = this,
                            opts = that.opts,
                            majorNameOptions = '<option value="-1">请选择专业</option>';
                        S.each(data,function(majorName,o){
                            majorNameOptions = majorNameOptions + '<option value="'+majorName.majorCode+'">'+majorName.majorName+'</option>';
                        });
                        $currentTarget = $(ev.currentTarget).parent().children(".J_majorName");
                        $currentTarget.html(majorNameOptions);
                        $currentTarget.val(0);
                    }
                });
            });
        },
		_submit:function(){
			var
				that = this,
				v = that.valid;
			v.validAll();
            var choose_major = $("select[name='majorCode']").children("option");
            console.log(choose_major);
			if(v.getValidResult('bool')){
				var
					info = that._serialize();
				UserIO.modValid(info,function(rs,data,errMsg){
					if(rs){
						jQuery('form').submit();
					}else{
						Dialog.error(errMsg);
					}
				});
			}
		},
		/*序列化添加走访信息的表单*/
		_serialize:function(){
			var
				info = {};
			info = DOM.serialize('form');
			return info;
		}
	});

	return core;
},{
	requires:['io/system/user','mod/defender','mod/dialog','io/module/linkage']
});