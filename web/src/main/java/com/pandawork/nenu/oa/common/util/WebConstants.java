package com.pandawork.nenu.oa.common.util;

import java.util.HashMap;
import java.util.Map;

public final class WebConstants {

    public static Map<String, Integer> modelTypeMap = new HashMap<>();

    static {
        modelTypeMap.put("/user/toUserList", 3);
        modelTypeMap.put("/student/info/new", 5);
        modelTypeMap.put("/student/info/update", 6);
        modelTypeMap.put("/student/info/detail", 7);
    }

    // 错误码
    public final static String sysErrorCode = "500";

    public final static String accessErrorCode = "admin/403";

    public final static String notFoundErrorCode = "admin/404";

    //下载目录
    public final static String downloadFolder = "/download/";

    //本地开发配置
    public final static String website = "http://jysj.dsjyw.local.net/";    //网站地址
    public final static String staticWebsite = "http://jysj.dsjyw.local.net/resources/";    //静态资源站点
    public final static String puiWebsite = "http://jysj.dsjyw.local.net/resources/pui/";    //前端组件库地址
    public static final String uploadWebsite = "http://jysj.dsjyw.local.net/upload";    //上传图片网址

    //服务器开发配置
//    public final static String website = "http://jysj.dev.net/";
//    public final static String staticWebsite = "http://jysj.dev.net/resources/";
//    public final static String puiWebsite = "http://jysj.dev.net/resources/pui/";
//    public static final String uploadWebsite = "http://jysj.dev.net/upload/";


    //测试配置
//    public final static String website = "http://jysjtest.dsjyw.net/";
//    public final static String staticWebsite = "http://jysjtest.dsjyw.net/resources/";
//    public final static String puiWebsite = "http://jysjtest.dsjyw.net/resources/pui/";
//    public static final String uploadWebsite = "http://jysjtest.dsjyw.net/upload/";


    //正式服务器配置
//    public final static String website = "http://qy.dsjyw.net/";
//    public final static String staticWebsite = "http://qy.dsjyw.net/resources/";
//    public final static String puiWebsite = "http://qy.dsjyw.net/resources/pui/";
//    public static final String uploadWebsite = "http://qy.dsjyw.net/upload/";


    public final static String webFullName = "东北师范大学就业公平台";
    public final static String webName = "oa";

    public final static int schoolCode = 10200;
    public final static String school = "东北师范大学";

    /**
     * 短信服务http地址
     */
    public static final String url = "http://www.qf106.com/sms.aspx";

    /**
     * 短信用户ID
     */
    public static final String userid = "12456";

    /**
     * 短信用户名
     */
    public static final String account = "zhuoyin";

    /**
     * 短信密码
     */
    public static final String password = "123456";

    public static final String apiKey = "woshihuairen";

    /**
     * 最大发送次数
     */
    public static final int MAX_SEND_TIMES = 5;


    /**
     * 学籍信息审核失败（短信模板）
     */
    public static final String SMSOFXUEJI = "同学，您的学籍信息有误，未通过审核。请您尽快与辅导员联系。";
    public static final String SMSOFXUEJI2 = "同学，您的学籍信息有误，未通过审核，原因是修改师范生类型不通过，证明材料不充分。请您尽快与辅导员联系。";
    public static final String SMSOFXUEJI3 = "同学，您的学籍信息有误，未通过审核，原因是修改生源地城市不通过，证明材料不充分。请您尽快与辅导员联系。";
    public static final String SMSOFXUEJI4 = "同学，您的学籍信息有误，未通过审核，原因是修改委培方式不通过，证明材料不充分。请您尽快与辅导员联系。";
    public static final String SMSOFXUEJI5 = "同学，您的学籍信息有误，未通过审核，原因是修改定向委培单位不通过，证明材料不充分。请您尽快与辅导员联系。";
    public static final String SMSOFXUEJI6 = "同学，您的学籍信息有误，未通过审核，原因是个人信息有错误。请您尽快与辅导员联系。";
    public static final String SMSOFXUEJI99 = "同学，您的学籍信息有误，未通过审核，原因是";
    public static final String SMSOFXUEJI99ADD ="。请您尽快与辅导员联系。";

    /**
     * 业务办理审核失败（短信模版）
     */
    public static final String PCMSG1 = "同学您好，您办理的";
    public static final String PCMSG1ADD1 = "业务已经通过审核，请携带相关材料于";
    public static final String PCMSG1ADD2 = "，到";
    public static final String PCMSG1ADD3 = "地点办理";
    //就是想空个行
    public static final String PCMSG = "同学您好，您办理的";
    public static final String PCMSG2 = "未通过审核，原因是修改师范生类型不通过，证明材料不充分。请您尽快与辅导员联系。";
    public static final String PCMSG3 = "未通过审核，原因是修改生源地城市不通过，证明材料不充分。请您尽快与辅导员联系。";
    public static final String PCMSG4 = "未通过审核，原因是修改委培方式不通过，证明材料不充分。请您尽快与辅导员联系。";
    public static final String PCMSG5 = "未通过审核，原因是修改定向委培单位不通过，证明材料不充分。请您尽快与辅导员联系。";
    public static final String PCMSG6 = "未通过审核，原因是个人信息有错误。请您尽快与辅导员联系。";
    public static final String PCMSG7 = "未通过审核，原因是材料不规范。请您尽快与辅导员联系。";
    public static final String PCMSG8 = "未通过审核，原因是材料不齐全。请您尽快与辅导员联系。";
    public static final String PCMSG99 = "未通过审核，原因是";
    public static final String PCMSG99ADD = "。请您尽快与辅导员联系。";
}
