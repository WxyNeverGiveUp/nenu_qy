/*-----------------------------------------------------------------------------
 * @Description:     历史数据查询列表ajax相关js
 * @Version:         1.0.0
 * @author:          wuxiaoyang(15290491277@163.com)
 * @date             2017.7.25
 * ==NOTES:=============================================
 * vn.n.n(2017.7.25):
 初始生成
 * ---------------------------------------------------------------------------*/
KISSY.add('io/admin_page/students_management/historical_data/dataList' , function(S) {
    var urls;
    try {
        urls = PW.Env.url.admin_page.students_management.historical_data.dataList;
    }catch(e) {
        S.log('地址信息错误');
        return;
    }

    PW.namespace('io.admin_page.students_management.historical_data.dataList');

    S.mix(PW.io.admin_page.students_management.historical_data.dataList, {
        conn: urls,
        /*加载学生信息*/
        showStuIO: function(data,callback) {
            S.IO({
                url: urls.showStu,
                type: 'get',
                data: data,
                dataType: 'json',
                success: function(rs) {
                    callback(
                        rs.code == 0,
                        rs.data,
                        rs.errMsg
                    );
                },
                error: function(rs) {
                    callback(
                        false,
                        null,
                        PW.Env.msg[0]
                    );
                }
            });
        },

    });
});
