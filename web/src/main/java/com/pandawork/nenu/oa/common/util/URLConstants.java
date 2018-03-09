package com.pandawork.nenu.oa.common.util;

/**
 * 全站Url地址常量
 * 命名规范：大模块_小模块_小模块_..._URL
 * 必须全部是大写字母
 * 两个小模块之间的地址不空行
 * 两个大模块之间的地址用两个空行隔开
 *
 * @author: zhangteng
 * @time: 2014/9/25 20:06
 */
public final class URLConstants {

    //首页
    public static final String INDEX_URL = "";

    //home页
    public static final String HOME_URL = "home";

    //logout
    public static final String LOGOUT_URL = "logout";


    /***********************通用**************************/
    public static final String GENERAL_MAJOR = "general/major";

    public static final String GENERAL_PLACE = "general/place";

    public static final String GENERAL_MATERIAL = "general/material";


    /**********************管理员*************************/
    public static final String ADMIN_USER_URL = "admin/user";

    public static final String ADMIN_COMMON_URL = "admin/common";

    public static final String ADMIN_IMPORT_URL = "admin/import";


    public static final String ADMIN_PROTOCOL_URL = "admin/protocol";

    public static final String ADMIN_CHANGE_URL = "admin/change";

    // 协议业务（新）
    public static final String ADMIN_PROTOCOL_NEW_URL = "admin/protocol/new";

    public static final String ADMIN_STU_STATUS_URL = "admin/status/info";

    public static final String ADMIN_STU_HISTORY_URL = "admin/history/data";

    public static final String ADMIN_MINORITY_URL = "admin/minority";

    public static final String ADMIN_EXPORT_URL = "admin/export";

    public static final String ADMIN_STATISTICS_URL = "admin/statistics";

    // 后台登录url
    public static final String ADMIN_LOGIN_URL = "admin/login";


    /**********************学生*************************/
    //学生学籍
    public static final String STUDENT_STATUS_URL = "student/info";

    //学生派遣
    public static final String STUDENT_DISPATCH_URL = "student/dispatch";

    //学生派遣
    public static final String ADMIN_DISPATCH_URL = "admin/dispatch";

    //学生业务受理
    public static final String STUDENT_BUSINESS_URL = "student/business";

    //学生身份变更业务
    public static final String STUDENT_BUSINESS_CHANGE_URL = "student/business/change";

    //学生申请新协议业务
    public static final String STUDENT_BUSINESS_PROTOCOL_URL = "student/business/protocol";

    //学生查看报到证
    public static final String STUDENT_REPORT_CARD_URL = "student/report_card";

    //学生就业意向调查
    public static final String STUDENT_INTENTIONSURVEY_URL = "student/intentionSurvey";

    //学生业务预约
    public static final String STUDENT_BUSINESS_NEWURL = "student/business";

    //历史数据
    public static final String STUDENT_HISTORY_URL = "student/history/data";

    //人才邀约api
    public static final String TALENT_RECRUITMENT_URL = "/talent/recruitment/";
}
