/*-----------------------------------------------------------------------------
* @Description:     suggest相关js
* @Version:         1.0.0
* @author:          chenyz(571108675@qq.com)
* @date             2016.7.22
* ==NOTES:=============================================
* v1.0.0(2016.7.22):
     初始生成
* ---------------------------------------------------------------------------*/
KISSY.add('module/dispatch/suggest',function(S,core){
	PW.namespace('module.dispatch.suggest');
	PW.module.dispatch.suggest = function(param){
		new core(param);
	};
},{
	requires:['suggest/core']
});

/*----------------------------------suggest-----------------------------------*/
KISSY.add('suggest/core',function(S){
	var
		$ = S.all, on = S.Event.on, delegate = S.Event.delegate,
		AreaIO = PW.io.module.getArea,
	el = {
		unitAreaHolder: '#J_unitAreaHolder',//指向单位所在地址
		areaFidle_unit: '#J_areaFidle_unit',//指向sugget的列表
		localAreaHolder: '#J_localAreaHolder',//指向报到证迁往单位所在地址
		areaFidle_local: '#J_areaFidle_local',//指向sugget的列表
		J_area_id_unit: '.area-id-unit',
		J_area_id_local: '.area-id-local',
		local_code: '.J_local_code',//报到证迁往单位地区代码
		unit_code: '.J_unit_code',//单位地区代码
		areaHolder: '.J_areaHolder',
	};

	function core(param){
		this.opts = param;
		this.init();
	}
	S.augment(core,{
		init:function(){
			this._addEventListener();
			this._getArea();
		},
		_addEventListener:function(){
			var
				that = this;

			/*当输入单位所在地址时*/
			on(el.unitAreaHolder,'keyup',function(evt){
				var
					target = evt.target,
					val = $(target).val(),
					holder = $(target).next(el.areaFidle_unit);
				if(val == ''){
					that._hideAreaFidle(holder);
				}else{
					that._showAreaFidle(holder,val);
				}
			});
			// 必须匹配到
			on(el.areaHolder,'keyup',function(evt){
				var
					target = evt.target,
					areaInfo = that.areaInfo,
					val = $(target).val();
				for(var i=0;i<areaInfo.length;i++){

					if(val==areaInfo[i].cnName){
						$(target).next("p").hide().text('');
					}else{
						$(target).next("p").show().text('请选择正确的地址');
					}
				}
			});
			on(el.unitAreaHolder,'blur',function(evt){
				var
					target = evt.target,
					val = $(target).val();
				if($(target).parent().next().children("input").val()!=''){
                    $('.unit-tip').text('');
				}
			});
			/*点击其他地方*/
			on('body','click',function(){
				that._hideAreaFidle(el.areaFidle_unit);
			});
			//当输入报到证迁往单位地址时
			delegate(document,'input propertychange',el.localAreaHolder,function(evt){
				var
					target = evt.target,
					val = $(target).val(),
					holder = $(target).next(el.areaFidle_local),
					li = $('#J_areaFidle_local li');
				if(val == ''){
					that._hideAreaFidle(holder);
				}else{
					that._showAreaFidle(holder,val);
				}
				for (var i = 0; i < li.length; i++) {
					if(li[i].innerHTML == val){
						$(el.local_code).val(li[i].getAttribute('areacode'));
						$('.unit-tip').text('');
					}
					else{
						$('.unit-tip').text('请选择正确地址');
						$(el.local_code).val('');
					}
				}
			});
            //当输入单位地地址时自动匹配
            delegate(document,'input propertychange',el.unitAreaHolder,function(evt){
                var
                    target = evt.target,
                    val = $(target).val(),
					holder = $(target).next(el.areaFidle_unit),
                    li = $('#J_areaFidle_unit li');
                holder = $(target).next();
                if(val == ''){
                    that._hideAreaFidle(holder);
                    $('.J_unit_code').val('');
                }else{
                    that._showAreaFidle(holder,val);
                }
                for (var i = 0; i < li.length; i++) {
                    if(li[i].innerHTML == val){
						$('.J_unit_code').val(li[i].getAttribute('areacode'));
                        $('.unit-tip').text('');
                    }
                    else{
                        $('.unit-tip').text('请选择正确地址');
                        $('.J_unit_code').val('');
					}
                }
            });
		},
		/*隐藏suggest的列表*/
		_hideAreaFidle:function(target){
			$(target).hide();
		},
		/*显示suggest的列表*/
		_showAreaFidle:function(target,val){
			var
				that = this;
			that._addArea(target,val);
			$(target).show();
		},
		/*把符合输入的地址显示在suggest的列表中*/
		_addArea:function(holder,val){
			var
				that = this,
				areaInfo = that.areaInfo,
				liHtml = '',
				areas;
			S.each(areaInfo,function(i,o){
				if(that._suggest(val,i)){
					liHtml = liHtml+'<li id="'+i.id+'" areacode="'+i.areacode+'" >'+i.cnName+'</li>';
				}
			});
			$(holder).html(liHtml);
			areas = $('li',holder);
			/*点击地址名称*/
			on(areas,'click',function(evt){
				// 关联地区代码
				var	areacode = $(this).attr("areacode");
				$(this).parent().parent().next().children("input").val(areacode);
				$(this).parent().next("p").hide().text('');
				that._showSelectArea(evt.target);
			});
		},
		
		/*正则匹配*/
		_suggest:function(val,data){
			var
				regexp = RegExp(val,"i");
			if(regexp.test(data.cnName) || regexp.test(data.enName)){
				return true;
			}else{
				return false;
			}
		},

		/*把用户选择的地址显示到输入框中*/
		_showSelectArea:function(target){
			var
				that = this,
				area = $(target).html(),
				// areaId = $(target).attr('id'),
				parent = $(target).parent();
			// $(parent).prev(el.companyHolder).val(company);
			if($(parent).prev().hasClass(el.areaHolder)){
				$(parent).prev().val(area);
				// $(parent).prev().prev().val(areaId);
			}
			else if($(parent).prev().hasClass(el.J_area_id_local)){
				$(parent).prev().val(area);
				// $(parent).prev().prev().val(areaId);
			}else if($(parent).prev().hasClass(el.J_area_id_local)) {
				$(parent).prev().val(area);
				// console.log($(parent).prev().val(area));
			}
			// else{
			// 	$(parent).prev().val(area);
			// }
			that._hideAreaFidle(parent);
		},
		/*获取系统中的地址*/
		_getArea: function(){
			var
				that = this;
			AreaIO.getArea({},function(rs,data,errorMes){
				if(rs){
					that.areaInfo = data;
				}else{
					S.log(errorMes);
				}
			});
		}
	});
	return core;
},{
	requires:['core','io/module/getArea']
});