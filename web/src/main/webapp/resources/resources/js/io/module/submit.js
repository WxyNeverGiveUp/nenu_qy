/*-----------------------------------------------------------------------------
* @Description:     填写学籍页面ajax相关js
* @Version:         2.0.0
* @author:          lijin
* @date             2017.7.22
* ==NOTES:=============================================
* v1.0.0(2017.7.22):
     初始生成
* ---------------------------------------------------------------------------*/
KISSY.add('io/module/submit',function(S) {
	var urls;
    try{
        urls = PW.Env.url.module.dispatch.submit;
    }catch(e){
        S.log('地址信息错误');
        return;
    }
    PW.namespace('io.module.submit');
    S.mix(PW.io.module.submit,{
    	conn: urls,
    	//签约信息提交时判断是否学籍信息通过
    	submitClassIO:function(data,callback){
    		S.IO({
    			url:urls.submitClass,
                type:'post',
                data:data,
                contentType: 'application/x-www-form-urlencoded; charset=utf-8',
                dataType:'json',
                success:function(rs){
                    callback(
                        rs.code == 0,
                        rs.data,
                        rs.errMsg
                        );
                },
                error:function(rs){
                    callback(
                        false,
                        null,
                        PW.Eng.msg[0]
                    );
                }
    		})
    	}

    })
})