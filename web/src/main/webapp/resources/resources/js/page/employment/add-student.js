/*-----------------------------------------------------------------------------
* @Description: 就业管理--学生信息添加 (student-info.js)
* @Version: 	V1.0.0
* @author: 		xuyihong(597262617@qq.com)
* @date			2015.05.21
* ==NOTES:=============================================
* v1.0.0(2014.05.21):
* 	初始生成 
* ---------------------------------------------------------------------------*/
KISSY.add('page/employment/add-student',function(S,linkage, submit){
	PW.namespace('page.addStudent');
	PW.page.addStudent = function(param){
		new linkage(param);
		new submit();
	};
},{
	requires:['module/linkage','addStudent/submit']
});
/*---------------------------------表单提交---------------------------------*/
KISSY.add('addStudent/submit',function(S){
	var
		$ = S.all, on = S.Event.on, DOM = S.DOM, get = DOM.get,
		Defender = PW.mod.Defender,
		Calendar = PW.mod.Calendar,
		Dialog = PW.mod.Dialog,
		el = {
			studentForm:'.J_studentForm', //指向添加单位信息的表单
			submitBtn:'.J_submit',//指向确定提交按钮,
			J_institutionList:'#J_institutionList',
			J_degreeList : '#J_degreeList',
			J_majorList : '#J_majorList',
			J_to_company : '.J_to_company',
			J_train_way : '.J_train_way',
			J_graduate_go : '.J_graduate_go',
			J_company_code : '.J_company_code',
			J_company_name : '.J_company_name',
			J_proviceListA : '#J_proviceListA',
			J_cityListA : '#J_cityListA',
			J_company_prop : '.J_company_prop',
			J_company_industry : '.J_company_industry',
			J_job_type : '.J_job_type'
		};

	function submit(param){
		this.init();
	}

	S.augment(submit,{
		init:function(){
			this.valid = Defender.client(el.studentForm,{
				showTip:false
			});
			/*this.initCalander();*/
			this._addEventListener();
		},
		_addEventListener:function(){
			var
				that = this;
			/*点击确定按钮*/
			on(el.submitBtn,'click',function(evt){
				that._formSubmit();
			});
		},
		/*表单提交*/
		_formSubmit:function(){
			var
				that = this,
				valid = that.valid,
				result = true;
			//result = that._validCheckbox();//验证复选框是否为空
			valid.validAll(function(rs){
				if(rs){
					if($(el.J_train_way).children('option:selected').val() == 2 || $(el.J_train_way).children('option:selected').val() == 4){
						if($(el.J_to_company).val() == ""){
							Dialog.alert('定向与委培单位为必填');
						}
						else{
							if($(el.J_graduate_go).children('option:selected').val() != 70 &&
								$(el.J_graduate_go).children('option:selected').val() != 71 &&
								$(el.J_graduate_go).children('option:selected').val() != 72 &&
								$(el.J_graduate_go).children('option:selected').val() != 80 &&
								$(el.J_graduate_go).children('option:selected').val() != 85){

								if($(el.J_graduate_go).children('option:selected').val() == 10 ||
									$(el.J_graduate_go).children('option:selected').val() == 11) {
									if($(el.J_company_code).val() == ""){
										Dialog.alert('单位组织机构代码不能为空');
									}
									else if($(el.J_company_name).val() == ""){
										Dialog.alert('单位名称不能为空');
									}
									else if($(el.J_proviceListA).children('option:selected').val() == 0){
										Dialog.alert('请选择单位所在省');
									}
									else if($(el.J_cityListA).children('option:selected').val() == 0){
										Dialog.alert('请选择单位所在市');
									}
									else if($(el.J_company_prop).children('option:selected').val() == 0){
										Dialog.alert('请选择单位性质');
									}
									else if($(el.J_company_industry).children('option:selected').val() == 0){
										Dialog.alert('请选择单位行业');
									}
									else if($(el.J_job_type).children('option:selected').val() == 0){
										Dialog.alert('请选择工作类型');
									}
									else{
										get(el.studentForm).submit();
									}
								}
								else{
									if($(el.J_company_name).val() == ""){
										Dialog.alert('单位名称不能为空');
									}
									else if($(el.J_proviceListA).children('option:selected').val() == 0){
										Dialog.alert('请选择单位所在省');
									}
									else if($(el.J_cityListA).children('option:selected').val() == 0){
										Dialog.alert('请选择单位所在市');
									}
									else if($(el.J_company_prop).children('option:selected').val() == 0){
										Dialog.alert('请选择单位性质');
									}
									else if($(el.J_company_industry).children('option:selected').val() == 0){
										Dialog.alert('请选择单位行业');
									}
									else if($(el.J_job_type).children('option:selected').val() == 0){
										Dialog.alert('请选择工作类型');
									}
									else{
										get(el.studentForm).submit();
									}
								}
							}
							else{
								get(el.studentForm).submit();
							}

						}
					}
					else {
						if($(el.J_to_company).val() != ""){
							Dialog.alert('定向与委培单位不能填');
						}
						else{
							if($(el.J_graduate_go).children('option:selected').val() != 70 &&
								$(el.J_graduate_go).children('option:selected').val() != 71 &&
								$(el.J_graduate_go).children('option:selected').val() != 72 &&
								$(el.J_graduate_go).children('option:selected').val() != 80 &&
								$(el.J_graduate_go).children('option:selected').val() != 85){

								if($(el.J_graduate_go).children('option:selected').val() == 10 ||
									$(el.J_graduate_go).children('option:selected').val() == 11) {
									if($(el.J_company_code).val() == ""){
										Dialog.alert('单位组织机构代码不能为空');
									}
									else if($(el.J_company_name).val() == ""){
										Dialog.alert('单位名称不能为空');
									}
									else if($(el.J_proviceListA).children('option:selected').val() == 0){
										Dialog.alert('请选择单位所在省');
									}
									else if($(el.J_cityListA).children('option:selected').val() == 0){
										Dialog.alert('请选择单位所在市');
									}
									else if($(el.J_company_prop).children('option:selected').val() == 0){
										Dialog.alert('请选择单位性质');
									}
									else if($(el.J_company_industry).children('option:selected').val() == 0){
										Dialog.alert('请选择单位行业');
									}
									else if($(el.J_job_type).children('option:selected').val() == 0){
										Dialog.alert('请选择工作类型');
									}
									else{
										get(el.studentForm).submit();
									}
								}
								else{
									if($(el.J_company_name).val() == ""){
										Dialog.alert('单位名称不能为空');
									}
									else if($(el.J_proviceListA).children('option:selected').val() == 0){
										Dialog.alert('请选择单位所在省');
									}
									else if($(el.J_cityListA).children('option:selected').val() == 0){
										Dialog.alert('请选择单位所在市');
									}
									else if($(el.J_company_prop).children('option:selected').val() == 0){
										Dialog.alert('请选择单位性质');
									}
									else if($(el.J_company_industry).children('option:selected').val() == 0){
										Dialog.alert('请选择单位行业');
									}
									else if($(el.J_job_type).children('option:selected').val() == 0){
										Dialog.alert('请选择工作类型');
									}
									else{
										get(el.studentForm).submit();
									}
								}
							}
							else{
								get(el.studentForm).submit();
							}

						}
					}

					//if($(el.J_graduate_go).children('option:selected').val() != 70 &&
					//	$(el.J_graduate_go).children('option:selected').val() != 71 &&
					//	$(el.J_graduate_go).children('option:selected').val() != 72 &&
					//	$(el.J_graduate_go).children('option:selected').val() != 80 &&
					//	$(el.J_graduate_go).children('option:selected').val() != 85){
                    //
					//	if($(el.J_graduate_go).children('option:selected').val() == 11 ||
					//		$(el.J_graduate_go).children('option:selected').val() == 12) {
					//			if($(el.J_company_code).val() == ""){
					//				Dialog.alert('单位组织机构代码不能为空');
					//			}
					//			else if($(el.J_company_name).val() == ""){
					//				Dialog.alert('单位名称不能为空');
					//			}
					//			else if($(el.J_proviceListA).children('option:selected').val() == 0){
					//				Dialog.alert('请选择单位所在省');
					//			}
					//			else if($(el.J_cityListA).children('option:selected').val() == 0){
					//				Dialog.alert('请选择单位所在市');
					//			}
					//			else if($(el.J_company_prop).children('option:selected').val() == 0){
					//				Dialog.alert('请选择单位性质');
					//			}
					//			else if($(el.J_company_industry).children('option:selected').val() == 0){
					//				Dialog.alert('请选择单位行业');
					//			}
					//			else if($(el.J_job_type).children('option:selected').val() == 0){
					//				Dialog.alert('请选择工作类型');
					//			}
					//			else{
					//				get(el.studentForm).submit();
					//			}
					//	}
					//	else{
					//		if($(el.J_company_name).val() == ""){
					//			Dialog.alert('单位名称不能为空');
					//		}
					//		else if($(el.J_proviceListA).children('option:selected').val() == 0){
					//			Dialog.alert('请选择单位所在省');
					//		}
					//		else if($(el.J_cityListA).children('option:selected').val() == 0){
					//			Dialog.alert('请选择单位所在市');
					//		}
					//		else if($(el.J_company_prop).children('option:selected').val() == 0){
					//			Dialog.alert('请选择单位性质');
					//		}
					//		else if($(el.J_company_industry).children('option:selected').val() == 0){
					//			Dialog.alert('请选择单位行业');
					//		}
					//		else if($(el.J_job_type).children('option:selected').val() == 0){
					//			Dialog.alert('请选择工作类型');
					//		}
					//		else{
					//			get(el.studentForm).submit();
					//		}
					//	}
					//}

				}
			});
		}
		/*initCalander:function(){
			Calendar.client({
				renderTo: '.date', //默认只获取第一个
                    select: {
                        rangeSelect: false, //是否允许区间选择
                        dateFmt: 'YYYY-MM-DD',
                        showTime: false //是否显示时间
                    }
			});
		}*/
	})
	return submit;
},{
	requires:['mod/calendar','mod/defender','mod/dialog']
});