package com.pandawork.nenu.oa.web.controller;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.framework.bean.StaticAutoWire;
import com.pandawork.core.framework.web.spring.controller.Base;
import com.pandawork.nenu.oa.service.admin.importData.ImportService;
import com.pandawork.nenu.oa.service.api.MajorTypeService;
import com.pandawork.nenu.oa.service.api.TalentRecruitmentService;
import com.pandawork.nenu.oa.service.business.ChangeService;
import com.pandawork.nenu.oa.service.business.ProtocolAdminService;
import com.pandawork.nenu.oa.service.business.ProtocolService;
import com.pandawork.nenu.oa.service.business.ProtocolStuService;
import com.pandawork.nenu.oa.service.company.CompanyService;
import com.pandawork.nenu.oa.service.data.*;
import com.pandawork.nenu.oa.service.dispatch.*;
import com.pandawork.nenu.oa.service.general.*;
import com.pandawork.nenu.oa.service.history.MajorCodeService;
import com.pandawork.nenu.oa.service.history.StuHistoryDataService;
import com.pandawork.nenu.oa.service.sms.SmsService;
import com.pandawork.nenu.oa.service.statistics.CollegeListService;
import com.pandawork.nenu.oa.service.statistics.EmploymentStatisticsService;
import com.pandawork.nenu.oa.service.student.intentionSurvey.IntentionSurveyService;
import com.pandawork.nenu.oa.service.student.status.StuStatusInfoService;
import com.pandawork.nenu.oa.service.student.status.StuUpdateInfoService;
import com.pandawork.nenu.oa.service.user.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AbstractController extends Base {

    //每页的数据条数
    public static final int pageSize = 15;
    // ajax默认成功代码
    protected final static int AJAX_SUCCESS_CODE = 0;
    // ajax默认失败代码
    protected final static int AJAX_FAILURE_CODE = 1;

    //保存成功
    protected final static String SAVE_SUCCESS_MSG = "保存成功!";

    // 添加成功
    protected final static String NEW_SUCCESS_MSG = "提交成功!";

    // 修改成功
    protected final static String UPDATE_SUCCESS_MSG = "修改成功!";

    //如果辅导员审核通过之后想修改的error信息
    protected final static String UPDATE_ERROR_CON_MSG = "该学生审核已通过，暂不能修改学生信息";

    //该学生审核已通过，如需修改请与就业中心联系
    protected final static String UPDATE_ERROR_MSG = "该学生审核已通过，如需修改请与就业中心联系";
    //用户管理service
    @StaticAutoWire
    protected static UserService userService;
    //用户角色管理service
    @StaticAutoWire
    protected static UserRoleService userRoleService;
    //用户角色管理service
    @StaticAutoWire
    protected static RoleService roleService;
    @StaticAutoWire
    protected static CommonCodeService commonCodeService;
    @StaticAutoWire
    protected static InformationStatisticService informationStatisticService;
    @StaticAutoWire
    protected static StudentService studentService;
    @StaticAutoWire
    protected static StudentToExcelService studentToExcelService;
    @StaticAutoWire
    protected static VerifyInfoService verifyInfoService;
    @StaticAutoWire
    protected static CompanyService companyService;
    //学生学籍信息Service
    @StaticAutoWire
    protected static StuStatusInfoService stuStatusInfoService;
    //学籍变更信息Service
    @StaticAutoWire
    protected static StuUpdateInfoService stuUpdateInfoService;
    //派遣信息Service
    @StaticAutoWire
    protected static DispatchInfoService dispatchInfoService;
    //派遣修改信息Service
    @StaticAutoWire
    protected static DispatchUpdateInfoService dispatchUpdateInfoService;
    //学生派遣信息Service
    @StaticAutoWire
    protected static StuDispatchService stuDispatchService;
    //单位性质代码Service
    @StaticAutoWire
    protected static CompanyPropertyService companyPropertyService;
    //材料Service
    @StaticAutoWire
    protected static MaterialService materialService;
    //协议
    @StaticAutoWire
    protected static ProtocolService protocolService;
    //学生端，业务预约
    @StaticAutoWire
    protected static ProtocolStuService protocolStuService;

    //学生业务受理Service
    //身份变更
    @StaticAutoWire
    protected static ChangeService changeService;
    //学院Service
    @StaticAutoWire
    protected static CollegeService collegeService;
    //学历Service
    @StaticAutoWire
    protected static QualificationService qualificationService;
    //困难生service
    @StaticAutoWire
    protected static DifficultyService difficultyService;
    //师范生类别
    @StaticAutoWire
    protected static NormalService normalService;
    //省份service
    @StaticAutoWire
    protected static ProvinceService provinceService;
    //政治面貌service
    @StaticAutoWire
    protected static PoliticalService politicalService;
    //民族service
    @StaticAutoWire
    protected static NationService nationService;
    //地区Service
    @StaticAutoWire
    protected static PlaceService placeService;
    //单位所属行业Service
    @StaticAutoWire
    protected static CompanyTradeService companyTradeService;
    //工作职位类别Service
    @StaticAutoWire
    protected static JobCodeService jobCodeService;
    //报到证类别service
    @StaticAutoWire
    protected static ReportCodeService reportCodeService;
    //毕业去向service
    @StaticAutoWire
    protected static WhereAboutGoService whereAboutGoService;
    @StaticAutoWire
    protected static WhereAboutGoIntendService whereAboutGoIntendService;
    //培养方式Service
    @StaticAutoWire
    protected static TrainingModeService trainingModeService;
    //学生账户service
    @StaticAutoWire
    protected static StudentUserService studentUserService;
    //学生-角色service
    @StaticAutoWire
    protected static StudentUserRoleService studentUserRoleService;
    //专业service
    @StaticAutoWire
    protected static MajorService majorService;
    //高校service
    @StaticAutoWire
    protected static SchoolService schoolService;
    //数据导入Service
    @StaticAutoWire
    protected static ImportService importService;
    //用户信息Service
    @StaticAutoWire
    protected static UserInfoService userInfoService;
    //办公信息Service
    @StaticAutoWire
    protected static CommentsService commentsService;
    //报到证Service
    @StaticAutoWire
    protected static ReportCardService reportCardService;
    //就业意向调查Service
    @StaticAutoWire
    protected static IntentionSurveyService intentionSurveyService;
    //市Service
    @StaticAutoWire
    protected static CityService cityService;
    //发送短信Service
    @StaticAutoWire
    protected static SmsService smsService;
    //管理员备注扩展项
    @StaticAutoWire
    protected static DispatchExtendItemService dispatchExtendItemService;
    //管理员文字备注Service
    @StaticAutoWire
    protected static DispatchAdminRemarkService dispatchAdminRemarkService;
    //查询学生历史数据service
    @StaticAutoWire
    protected static StuHistoryDataService stuHistoryDataService;
    //历史数据专业Service
    @StaticAutoWire
    protected static MajorCodeService majorCodeService;
    //管理员业务办理Service
    @StaticAutoWire
    protected static ProtocolAdminService protocolAdminService;

    //人才邀约专业三级联动Service
    @StaticAutoWire
    protected static MajorTypeService majorTypeService;

    //人才邀约查询学生Service
    @StaticAutoWire
    protected static TalentRecruitmentService talentRecruitmentService;

    //实时就业率统计Service
    @StaticAutoWire
    protected static EmploymentStatisticsService employmentStatisticsService;

    //实时就业率统计获取学院列表Service
    @StaticAutoWire
    protected static CollegeListService collegeListService;

    public void init(HttpServletRequest request, HttpServletResponse response) {
        super.init(request, response); // 执行父类的初始化
    }

    /**
     * 发送json对象
     *
     * @param json
     * @return
     */
    public JSONObject sendJsonObject(JSONObject json) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        if (json != null) {
            jsonObject.put("data", json);
        }
        return jsonObject;
    }

    /**
     * 发送无数据与的json对象
     *
     * @param code
     * @return
     */
    protected JSONObject sendJsonObject(int code) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        return jsonObject;
    }

    /**
     * 发送json对象
     *
     * @param json
     * @return
     */
    protected JSONObject sendJsonObject(JSONObject json, int code) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        if (json != null) {
            jsonObject.put("data", json);
        }
        return jsonObject;
    }

    /**
     * 发送json数组对象
     *
     * @param jsonArray
     * @return
     */
    public
    @ResponseBody
    JSONObject sendJsonArray(JSONArray jsonArray) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    /**
     * 发送json数组对象
     *
     * @param jsonArray
     * @return
     */
    public JSONObject sendJsonArray(JSONArray jsonArray, int dataCount) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("data", jsonArray);
        jsonObject.put("dataCount", dataCount);
        return jsonObject;
    }

    // 发送ajax错误信息
    public JSONObject sendErrMsgAndErrCode(SSException e) {
        JSONObject json = new JSONObject();
        json.put("errMsg", e.getMessage());
        json.put("code", e.getCode());
        return json;
    }

    // 发送ajax错误信息(字符串)
    public JSONObject sendErrMsgAndErrCode(String e) {
        JSONObject json = new JSONObject();
        json.put("errMsg", e);
        json.put("code", 1);
        return json;
    }
}
