/*-----------------------------------------------------------------------------
* @DescriSion:  信息详情的相关ajax
* @Version: 	V1.0.0
* @author: 		liaoyueyun
* @date			2016.07.10
* ==NOTES:=============================================
* v1.0.0(2016.07.10):
* 	初始生成 
* ---------------------------------------------------------------------------*/
KISSY.add('io/module/detail',function(S){
	var urls;
	try {
		urls = PW.Env.url.module.detail;
	}catch(e){
		S.log("地址信息错误");
		return;
	}

	PW.namespace('io.module.detail');

	S.mix(PW.io.module.detail,{
		conn:urls,
		DetailCheck:function(data,callback){
			 S.IO({
	                url:urls.DetailCheck,
	                type:'get',
	                data:data,
	                dataType:'json',
	                success: function(rs){
                    callback(
                        rs.code,
                        rs.data,
                        rs.errMsg
	                    );
	                },
	                error: function(err){
	                    callback(
	                        false,
	                        null,
	                        PW.Env.msg[0]
	                    );
	                }
            	});
		}
	})
})