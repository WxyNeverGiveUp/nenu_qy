package com.pandawork.nenu.oa.common.util;

/**
 * 错误提示常量
 * 命名规范：大模块_小模块_小模块_..._URL
 * 必须全部是大写字母
 * 两个小模块之间的地址不空行
 * 两个大模块之间的地址用两个空行隔开
 *
 * @author wlm
 * @date 2016/9/18 19:21
 */
public final class MessageInfo {

    //通用
    public static final String NO_PERMISSION = "没有权限进行该操作!";

    public static final String SUBMITTED = "你已经填写过了，请等待审核或在修改页面进行修改";

    public static final String UPDATE_SUBMITTED = "您已经提交，请等待审核！";

    public static final String NOT_FILL_IN = "你还没有填写，请填写后查看/修改";


    //用户管理
    public static final String NO_USER = "没有查询到相关用户";

    //导入管理
    public static final String IMPORT_SUCCESS = "导入成功!";

    public static final String IMPORT_CHECK_FAILED = "表格数据不符合要求，请检查后重新导入！";


    public final static String NEW_FAILED_MSG = "提交失败！";

    public final static String UPLOAD_FAILED_MSG = "上传材料不能为空，请先选择上传材料！";

    public final static String ENTER_FAILED_MSG = "进入页面失败，您已提交过材料，请联系就业中心进行修改！";

    public final static String NEW_SUCCESS_MSG = "提交成功，请等待审核或者联系就业中心进行修改！";

    public final static String ENTER_DOCTOR_FAILED_MSG = "非博士生不可办理此项业务！";


}
