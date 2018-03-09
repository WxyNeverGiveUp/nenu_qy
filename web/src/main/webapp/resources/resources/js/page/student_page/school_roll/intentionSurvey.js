/*-----------------------------------------------------------------------------
* @DescriSion:  学籍信息——调查问卷
* @Version: 	V1.0.0
* @author: 		Lily
* @date			2017.05.13
* ==NOTES:=============================================
* v1.0.0(2017.05.13):
* 	初始生成 
* ---------------------------------------------------------------------------*/
KISSY.add('page/student_page/school_roll/intentionSurvey', function(S, Core){
	PW.namespace('page.student_page.school_roll.intentionSurvey');
	PW.page.student_page.school_roll.intentionSurvey = function(param){
		new Core(param);
	}
},{
	requires:[
		'intentionSurvey/survey'
	]
});
/*---------------------------------调查问卷----------------------------------------*/
KISSY.add('intentionSurvey/survey',function(S){
	var 
		// $ = S.all ,
		on = S.Event.on ,
		DOM = S.DOM,
		get = DOM.get,
		delegate = S.Event.delegate,
		Dialog = PW.mod.Dialog,
		that = this,
		// intentionSurveyIO = PW.io.student_page.school_roll.intentionSurvey,
		el = {
			//就业意向A
			J_pk1El: '.J_pk1',
			//毕业学校：其他
			J_pk2El: '.J_pk2',
			//就业指导：其他
			J_pk3El: '.J_pk3',
			//就业意向行业A
			J_pk6El: '.J_pk6',
			//选择省份
			selectProvinceEl: '.J_selectProvince',
			//选择城市
			selectCityEl: '.J_selectCity',
			//就业意向选择T则改变题号
			changeTitleOne: '.change-title-one',
			//就业意向选择T则改变题号
			changeTitleTwo: '.change-title-two',
			//本科学校
			schoolNameEl: '.J_schoolNames',
			//表单
			form: '.J_form',
			//提交表单
			submitTrigger: '.J_submit'
		};

	function Core(){
		this.init();
	}

	S.augment(Core, {
		init: function(){
			this._addEventListener();
		},
		_addEventListener: function(){
			var
				that = this;

		 	on(el.J_pk1El, 'change', function(){
		 		that._selectOneA();
		 	});

		 	on(el.J_pk2El, 'change', function(){
		 		that._selectTwoD();
		 	});

		 	on(el.J_pk3El, 'change', function(){
		 		that._selectThreeL();
		 	});

		 	on(el.J_pk6El, 'change', function(){
		 		that._selectSixA();
		 	});

		 	// on(el.selectProvinceEl, 'change', function(){
		 	// 	that._getCityId();
		 	// });

			on(el.submitTrigger, 'click', function(){
				that._valid();
				return false;
			})

		},
		/**
		 * ajax发送省份ID
		 * @return {[type]} [description]
		 */
		// _getCityId: function(){
		// 	var
		// 		that = this,
		// 		provinceid;

		// 	provinceid = $('.J_selectProvince option:selected').val();
		// 	console.log(provinceid);
		// 	intentionSurveyIO.getCityList({provinceid: provinceid}, function(rs, data, errMsg){
		// 		if(rs){
		// 			that._getCityData(data);
		// 			console.log(data);
		// 		}else{
		// 			Dialog.alert(errMsg);
		// 		}
		// 	});

		// },
		/**
		 * 刷该ID对应的城市数据
		 * @param  {[type]} list [description]
		 * @return {[type]}      [description]
		 */
		// _getCityData: function(data){
  //           var 
  //               that = this,
  //               options = '<option value="0" selected="selected">------请选择城市------</option>';
  //               // options = '<option value="-1" selected="selected">------请选择城市------</option>';
  //           console.log(data);
  //           S.each(data, function(city){

  //           	// options = options + "<option value='" + city.id + "'>" + city.name + "</option>";
  //           	// options = options + '<option value = "' + item.id + '"  storageUnit = "' + item.storageUnit + '"  total = "'+ item.total +'" >' + item.specification + '</option>';
  //           });            
  //           $(el.selectCityEl).empty();
  //           $(el.selectCityEl).append(options);
  //       },
		/**
		 * 第一题选择A,则继续回答4至10题
		 * @return {[type]} [description]
		 */
		_selectOneA: function(){
			var
				that = this,
				pk1El,
				pk1Value;

			pk1El =  document.getElementsByName("pk1");
			for(var i=0; i < pk1El.length; i++){
		        if(pk1El[i].checked){
		            pk1Value = pk1El[i].value;
		        }
		    }
		    if(pk1Value == 1){
		    	DOM.removeClass('.J_pk4', 'hide');
		    	DOM.removeClass('.J_pk5', 'hide');
		    	DOM.removeClass('.J_pk6', 'hide');
		    }else{
		    	DOM.addClass('.J_pk4', 'hide');
		    	DOM.addClass('.J_pk5', 'hide');
		    	DOM.addClass('.J_pk6', 'hide');
                DOM.val('input[name="pk5"]', '');
		    	DOM.removeAttr('option[name="pk4Option"]', 'selected', '');
		    	DOM.removeAttr('input[name="pk6"]', 'checked', '');
		    	DOM.removeAttr('input[name="pk7"]', 'checked', '');
		    	DOM.removeAttr('input[name="pk8"]', 'checked', '');
		    	DOM.removeAttr('input[name="pk9"]', 'checked', '');
		    	DOM.val('input[name="pk10"]', '');
		    	DOM.addClass('.J_pk7', 'hide');
		    	DOM.addClass('.J_pk8', 'hide');
		    	DOM.addClass('.J_pk9', 'hide');
		    	DOM.addClass('.J_pk10', 'hide');
		    }
		},
		/**
		 * 第二题选择D,则需填写学校名称
		 * @return {[type]} [description]
		 */
		_selectTwoD: function(){
			var
				that = this,
				pk2El,
				pk2Value;

			pk2El =  document.getElementsByName("pk2");
			for(var i=0; i < pk2El.length; i++){
		        if(pk2El[i].checked){
		            pk2Value = pk2El[i].value;
		        }
		    }
		    if(pk2Value != 1){
		    	DOM.removeClass('.J_schoolName', 'hide');
		    }else{
		    	DOM.addClass('.J_schoolName', 'hide');
		    	DOM.val('input[name="undergraduate"]', '');
		    }
		},
		/**
		 * 第三题选择有L,则需填内容
		 * @return {[type]} [description]
		 */
		_selectThreeL: function(){
			var
				that = this,
				pk3El,
				pk3Value;

			pk3El =  document.getElementsByName("pk3");
			for(var i=0; i < pk3El.length; i++){
		        if(pk3El[i].checked){
		            pk3Value = pk3El[i].value;
		        }
		    }
		    if(pk3Value == 12){
		    	DOM.removeClass('.J_other', 'hide');
		    }else{
		    	DOM.addClass('.J_other', 'hide');
		    	DOM.val('input[name="otherGuidance"]', '');
		    }
		},
		/**
		 * 第六题选择A,填写第七题；选择B-S选项则填写第八题；选择T则直接填写第九题
		 * @return {[type]} [description]
		 */
		_selectSixA: function(){
			var
				that = this,
				pk6El,
				pk6Value;

			pk6El =  document.getElementsByName("pk6");
			for(var i=0; i < pk6El.length; i++){
		        if(pk6El[i].checked){
		            pk6Value = pk6El[i].value;
		        }
		    }
		    if(pk6Value == 1){
		    	DOM.removeClass('.J_pk7', 'hide');
		    	DOM.addClass('.J_pk8', 'hide');
		    	DOM.removeClass('.J_pk9', 'hide');
		    	DOM.removeClass('.J_pk10', 'hide');
		    	DOM.html(el.changeTitleOne, '8.&nbsp;&nbsp;您择业时会选择哪些途径获取招聘信息？_____（多选题）');
		    	DOM.html(el.changeTitleTwo, '9.&nbsp;&nbsp;您的就业意向单位（我们优先走访）');
		    	DOM.removeAttr('input[name="pk7"]', 'checked', '');
		    	DOM.removeAttr('input[name="pk9"]', 'checked', '');
		    	DOM.val('input[name="pk10"]', '');
		    }else  if(pk6Value == 20){
		    	DOM.removeClass('.J_pk9', 'hide');
		    	DOM.removeClass('.J_pk10', 'hide');
		    	DOM.addClass('.J_pk7', 'hide');
		    	DOM.addClass('.J_pk8', 'hide');
		    	DOM.html(el.changeTitleOne, '7.&nbsp;&nbsp;您择业时会选择哪些途径获取招聘信息？_____（多选题）');
		    	DOM.html(el.changeTitleTwo, '8.&nbsp;&nbsp;您的就业意向单位（我们优先走访）');
		    	DOM.removeAttr('input[name="pk9"]', 'checked', '');
		    	DOM.val('input[name="pk10"]', '');
		    }else{
		    	DOM.removeClass('.J_pk8', 'hide');
		    	DOM.addClass('.J_pk7', 'hide');
		    	DOM.removeClass('.J_pk9', 'hide');
		    	DOM.removeClass('.J_pk10', 'hide');
		    	DOM.html(el.changeTitleOne, '8.&nbsp;&nbsp;您择业时会选择哪些途径获取招聘信息？_____（多选题）');
		    	DOM.html(el.changeTitleTwo, '9.&nbsp;&nbsp;您的就业意向单位（我们优先走访）');
		    	DOM.removeAttr('input[name="pk8"]', 'checked', '');
		    	DOM.removeAttr('input[name="pk9"]', 'checked', '');
		    	DOM.val('input[name="pk10"]', '');
		    }
		},
		/**
		 * 提交表单
		 * @return {[type]} [description]
		 */
		_valid: function(){
			var
				that = this,
				pk1El,
				pk1Value,
				pk2El,
				pk2Value,
				pk3El,
				pk3Value,
				form = get(el.form),
				schoolName = DOM.val(el.schoolNameEl);

			// console.log(schoolName);
			pk1El =  document.getElementsByName("pk1");
			pk2El =  document.getElementsByName("pk2");
			pk3El =  document.getElementsByName("pk3");
			for(var i=0; i < pk1El.length; i++){
		        if(pk1El[i].checked){
		            pk1Value = pk1El[i].value;
		        }
		    }
		    for(var i=0; i < pk2El.length; i++){
		        if(pk2El[i].checked){
		            pk2Value = pk2El[i].value;
		        }
		    }
		    for(var i=0; i < pk3El.length; i++){
		        if(pk3El[i].checked){
		            pk3Value = pk3El[i].value;
		        }
		    }
		    if(pk1Value = undefined || pk2Value == undefined || pk3Value == undefined){
		    	Dialog.alert('前三题为必选题，请填写完整！');
		    	return;
		    }
		    if(pk2Value != 1 && schoolName == ''){
		    	Dialog.alert('前三题为必选题，请填写完整！');
		    	return;
		    }
		    form.submit();
            // Dialog.alert('提交成功！');
		}
	});
	return Core;
},{
	requires:[
		// 'io/student_page/school_roll/intentionSurvey',
		'mod/dialog'
	]
});