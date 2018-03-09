package com.pandawork.nenu.oa.web.controller.admin.status;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.dto.data.MajorDto;
import com.pandawork.nenu.oa.common.dto.student.status.StatusInfoListDto;
import com.pandawork.nenu.oa.common.dto.student.status.StuStatusInfoDto;
import com.pandawork.nenu.oa.common.dto.student.status.UpdateInfoDto;
import com.pandawork.nenu.oa.common.dto.user.UserInfoDto;
import com.pandawork.nenu.oa.common.entity.data.*;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.entity.general.ProvinceProperty;
import com.pandawork.nenu.oa.common.entity.student.status.StuStatusInfo;
import com.pandawork.nenu.oa.common.enums.general.CheckReasonEnums;
import com.pandawork.nenu.oa.common.enums.general.CheckStatusEnums;
import com.pandawork.nenu.oa.common.enums.general.MaterialTypeEnums;
import com.pandawork.nenu.oa.common.enums.student.status.NorMalStuTypeEnums;
import com.pandawork.nenu.oa.common.enums.student.status.StuTypeEnums;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.CacheUtil;
import com.pandawork.nenu.oa.common.util.MessageInfo;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.common.util.WebConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * AdminStatusInfoController
 *
 * @author wlm
 * @date 2016/7/25 8:57
 */

@Controller
@RequestMapping(value = URLConstants.ADMIN_STU_STATUS_URL)
public class AdminStatusInfoController extends AbstractController {

    private CacheUtil cacheUtil = new CacheUtil();

    /**
     * 去学生列表页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String toList(@RequestParam(value = "college",required = false,defaultValue = "-1") String college, Model model) {
        try {
            Subject subject = SecurityUtils.getSubject();
            String username = subject.getPrincipal().toString();
            List<CollegeCode> collegeList = collegeService.listAll();
            List<QualificationCode> qualificationList = qualificationService.listAll();
            List<TrainingModeCode> trainingModeList = trainingModeService.listAll();
            List<ProvinceProperty> provinceList = provinceService.listAll();
            List<PoliticalCode> politicalList = politicalService.listAll();

            if (subject.hasRole("counsellor")) {
                List<UserInfoDto> userInfoDtoList  = userInfoService.listMajorsAndQualificationByUserName(username);
                if (Assert.isNotEmpty(userInfoDtoList) && userInfoDtoList.size() > 0){
                    for (UserInfoDto userInfoDto: userInfoDtoList){
                        String majorName = "";
                        MajorCode majorCode = majorService.queryByCode(userInfoDto.getMajorCode(),userInfoDto.getMajorQualification());
                        if (Assert.isNotNull(majorCode)){
                            majorName = majorCode.getMajorName();
                        }
                        userInfoDto.setMajorName(userInfoDto.getMajorQualification() + "-" + majorName);
                    }
                    // 添加一个专业为空
                    UserInfoDto userInfoDtoMajorNull = new UserInfoDto();
                    userInfoDtoMajorNull.setMajorCode("-2");
                    userInfoDtoMajorNull.setMajorName("专业为空");
                    userInfoDtoList.add(userInfoDtoMajorNull);
                }
                model.addAttribute("userInfoDtoList", userInfoDtoList);
            }
            else if (subject.hasRole("college-secretary")){
                List<UserInfoDto> userInfoDtoList = userInfoService.listMajorsAndQualificationByCollege(username);
                if (Assert.isNotNull(userInfoDtoList) && userInfoDtoList.size() > 0){
                    for (UserInfoDto userInfoDto: userInfoDtoList){
                        String majorname = "";
                        MajorCode majorCode = majorService.queryByCode(userInfoDto.getMajorCode(),userInfoDto.getMajorQualification());
                        if (Assert.isNotNull(majorCode)){
                            majorname = majorCode.getMajorName();
                        }
                        userInfoDto.setMajorName(userInfoDto.getMajorQualification() + "-" + majorname);
                    }
                    UserInfoDto userInfoDtoMajorNull = new UserInfoDto();
                    userInfoDtoMajorNull.setMajorCode("-2");
                    userInfoDtoMajorNull.setMajorName("专业为空");
                    userInfoDtoList.add((userInfoDtoMajorNull));
                }
                model.addAttribute("userInfoDtoList", userInfoDtoList);
            }
              else {
                System.out.println("aaa");
            }
            model.addAttribute("collegeList", collegeList);
            model.addAttribute("qualificationList", qualificationList);
            model.addAttribute("trainingModeList", trainingModeList);
            model.addAttribute("provinceList", provinceList);
            model.addAttribute("politicalList", politicalList);
            return "admin/status/list";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }
    /**
     * 分页获取学籍列表数据
     *
     * @param page
     * @param stuType
     * @param college
     * @param normalStu
     * @param counsellorCheckResult
     * @param deputySecretaryCheckResult
     * @param schoolCheckResult
     * @param province
     * @param qualification
     * @param trainingMode
     * @param keyword
     * @param isRegistered               学生是否已注册
     * @return
     */
    @RequestMapping(value = "ajax/list/{page}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getList(@PathVariable("page") int page,
                              @RequestParam(value = "stuType", required = false, defaultValue = "-1") int stuType,
                              @RequestParam(value = "college", required = false, defaultValue = "-1") int college,
                              @RequestParam(value = "normalStu", required = false, defaultValue = "-1") int normalStu,
                              @RequestParam(value = "counsellorCheckResult", required = false, defaultValue = "-1") int counsellorCheckResult,
                              @RequestParam(value = "deputySecretaryCheckResult", required = false, defaultValue = "-1") int deputySecretaryCheckResult,
                              @RequestParam(value = "schoolCheckResult", required = false, defaultValue = "-1") int schoolCheckResult,
                              @RequestParam(value = "province", required = false, defaultValue = "-1") int province,
                              @RequestParam(value = "qualification", required = false, defaultValue = "") String qualification,
                              @RequestParam(value = "trainingMode", required = false, defaultValue = "-1") int trainingMode,
                              @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                              @RequestParam(value = "sex", required = false, defaultValue = "-1") int sex,
                              @RequestParam(value = "politicalStand", required = false, defaultValue = "-1") int politicalStand,
                              @RequestParam(value = "stuLength", required = false, defaultValue = "-1") double stuLength,
                              @RequestParam(value = "majorCode", required = false, defaultValue = "") String majorCode,
                              @RequestParam(value = "qualificationNow", required = false, defaultValue = "-1") int qualificationNow,
                              @RequestParam(value = "nation", required = false, defaultValue = "") String nation,
                              //显示全部
                              @RequestParam(value = "showUncommitted", required = false, defaultValue = "1") int showUncommitted,
                              @RequestParam(value = "protocolType", required = false, defaultValue = "-1") List<Integer> protocolType,
                              @RequestParam(value = "isRegistered", required = false, defaultValue = "-1") int isRegistered) {
        JSONArray jsonArray = new JSONArray();
        try {
            List<UserInfoDto> userInfoDtoList = Collections.emptyList();
            String majorQualification = "";

            if (!Assert.isNull(qualification) && qualification.equals("-1")) {
                qualification = null;
            }
            Subject subject = SecurityUtils.getSubject();
            String username = subject.getPrincipal().toString();
            List<String> colleges = null;
            if (subject.hasRole("college-secretary")) {
                colleges = userInfoService.listCollegesByUserName(username);
                if (Assert.isEmpty(colleges) || colleges.size() <= 0) {
                    colleges.add("0");
                }
            } else if (subject.hasRole("counsellor")) {
                colleges = userInfoService.listCollegesByUserName(username);
                if (Assert.isEmpty(colleges) || colleges.size() <= 0) {
                    colleges.add("0");
                }
                /*majors = userInfoService.listMajorsByUserName(username);
                if (Assert.isEmpty(majors) || majors.size() <= 0) {
                    majors.add("0");
                }*/
                // 获取辅导员的专业列表
                userInfoDtoList  = userInfoService.listMajorsAndQualificationByUserName(username);
                majorQualification = userInfoDtoList.get(0).getMajorQualification();
            }

            // 20170619（陈玉婷）修改，将辅导员的查询条件与副书记调整为相同，都能看到自己学院的所有学生
            int count = stuStatusInfoService.countByCondition(stuType, college, normalStu, counsellorCheckResult, deputySecretaryCheckResult, schoolCheckResult,
                    province, qualification, trainingMode, keyword,
                    sex, politicalStand, stuLength, colleges, majorCode,majorQualification, null, qualificationNow, nation, showUncommitted, isRegistered);
            Pagination pagination = new Pagination(count, pageSize, page);
            List<StatusInfoListDto> statusInfoList = stuStatusInfoService.listByCondition(stuType, college, normalStu, counsellorCheckResult, deputySecretaryCheckResult,
                    schoolCheckResult, province, qualification, trainingMode, keyword, sex, politicalStand, stuLength,pagination, colleges, majorCode, majorQualification, null, qualificationNow, nation, showUncommitted, isRegistered);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int index = (page - 1) * pageSize + 1;
            for (StatusInfoListDto statusInfoListDto : statusInfoList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("index", index++);
                jsonObject.put("id", statusInfoListDto.getId());
                jsonObject.put("name", statusInfoListDto.getName());
                jsonObject.put("sex", statusInfoListDto.getSex());
                jsonObject.put("stuNumber", statusInfoListDto.getStuNumber());
                jsonObject.put("grade", statusInfoListDto.getGrade());
                if (statusInfoListDto.getMajor()==null) {
                    jsonObject.put("major", statusInfoListDto.getOtherMajor());
                }else{
                    jsonObject.put("major", statusInfoListDto.getMajor());
                }
                jsonObject.put("college", statusInfoListDto.getCollege());

                //System.out.println(statusInfoListDto.getStuLength());
                jsonObject.put("stuLength", statusInfoListDto.getStuLength());
                jsonObject.put("candidateNumber", statusInfoListDto.getCandidateNumber());
                jsonObject.put("idNumber", statusInfoListDto.getIdNumber());
                jsonObject.put("nation", statusInfoListDto.getNation());
                //stop
                jsonObject.put("school",statusInfoListDto.getSchool());
                jsonObject.put("qualification",statusInfoListDto.getQualification());
                jsonObject.put("counsellorCheckResult", statusInfoListDto.getCounsellorCheckResult());
                if (!Assert.isNull(statusInfoListDto.getCounsellorCheckTime())) {
                    jsonObject.put("counsellorCheckTime", sdf.format(statusInfoListDto.getCounsellorCheckTime()));
                } else {
                    jsonObject.put("counsellorCheckTime", "无");
                }
                jsonObject.put("deputySecretaryCheckResult", statusInfoListDto.getDeputySecretaryCheckResult());
                if (!Assert.isNull(statusInfoListDto.getDeputySecretaryCheckTime())) {
                    jsonObject.put("deputySecretaryCheckTime", sdf.format(statusInfoListDto.getDeputySecretaryCheckTime()));
                } else {
                    jsonObject.put("deputySecretaryCheckTime", "无");
                }
                jsonObject.put("schoolCheckResult", statusInfoListDto.getSchoolCheckResult());
                if (!Assert.isNull(statusInfoListDto.getSchoolCheckTime())) {
                    jsonObject.put("schoolCheckTime", sdf.format(statusInfoListDto.getSchoolCheckTime()));
                } else {
                    jsonObject.put("schoolCheckTime", "无");
                }
                //add by lw
                if (!Assert.isNull(statusInfoListDto.getTrainingMode())) {
                    jsonObject.put("trainingMode", statusInfoListDto.getTrainingMode());
                } else {
                    jsonObject.put("trainingMode", "无");
                }

                if (!Assert.isNull(statusInfoListDto.getWeipeiUnit())) {
                    jsonObject.put("weipeiUnit", statusInfoListDto.getWeipeiUnit());
                } else {
                    jsonObject.put("weipeiUnit", "无");
                }

                if (!Assert.isNull(statusInfoListDto.getOriginPlace())) {
                    jsonObject.put("originPlace", statusInfoListDto.getOriginPlace());
                } else {
                    jsonObject.put("originPlace", "无");
                }

                if (!Assert.isNull(statusInfoListDto.getPoliticalStand())) {
                    jsonObject.put("politicalStand", statusInfoListDto.getPoliticalStand());
                } else {
                    jsonObject.put("politicalStand", "无");
                }

                if (!Assert.isNull(statusInfoListDto.getNormalStu())) {
                    jsonObject.put("normalStu", statusInfoListDto.getNormalStu());
                } else {
                    jsonObject.put("normalStu", "无");
                }

                if (!statusInfoListDto.getProtocolType().isEmpty()) {
                    //System.out.println("不空" + statusInfoListDto.getProtocolType());//List<Integer>
                    Map<Integer, String> protocolMap = new HashMap<Integer, String>();
                    String protocolTypeStr = "";
                    protocolMap.put(1, "初始状态");
                    protocolMap.put(2, "协议污损");
                    protocolMap.put(3, "协议丢失");
                    protocolMap.put(4, "协议解约领新协议");
                    protocolMap.put(5, "放弃考研/博领协议");
                    protocolMap.put(6, "放弃出国/出境领协议");
                    protocolMap.put(7, "免师解约");
                    protocolMap.put(8, "免师跨省");
                    protocolMap.put(9, "定向生领协议");
                    protocolMap.put(10, "委培生解约领协议");
                    protocolMap.put(11, "定向生解约");
                    protocolMap.put(12, "博士研究生申请新协议");
                    List<Integer> protocolList = statusInfoListDto.getProtocolType();
                    int protocolCount = protocolList.size();
                    if(protocolCount == 1) {
                        if(protocolList.get(0) == 1) {
                            protocolTypeStr = "无";
                        } else {
                            protocolTypeStr = protocolMap.get(protocolList.get(0));
                        }
                    } else {
                        protocolTypeStr = protocolMap.get(protocolList.get(0));
                        for(int i=1; i<protocolCount; i++) {
                            if(protocolList.get(i) > 10)
                                continue;
                            protocolTypeStr = protocolTypeStr + ";" + protocolMap.get(protocolList.get(i));
                        }
                    }
                    jsonObject.put("protocolType", protocolTypeStr);
                } else {
                   //System.out.println("空");
                    jsonObject.put("protocolType", "无");
                }

                if (!Assert.isNull(statusInfoListDto.getDifficulty())) {
                    jsonObject.put("difficulty", statusInfoListDto.getDifficulty());
                } else {
                    jsonObject.put("difficulty", "无");
                }
                //add by lw
                jsonArray.add(jsonObject);
            }
            return sendJsonArray(jsonArray, count);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 列表页进入详情页创建缓存
     *
     * @param idList
     * @param id
     * @param curNo
     * @param curPage
     * @param conditions1
     * @return
     */
    @RequestMapping(value = "ajax/cache", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject inputToCache(@RequestParam(value = "idList[]") List<Integer> idList,
                                   @RequestParam(value = "id") int id,
                                   @RequestParam(value = "curNo") int curNo,
                                   @RequestParam(value = "curPage", required = false) Integer curPage,
                                   @RequestParam(value = "conditions", required = false) String conditions1) {

        //手动清理缓存
        System.out.println("缓存success");
        Subject subject = SecurityUtils.getSubject();
        String loginName = subject.getPrincipal().toString();
        cacheUtil.remove(loginName);
        if (Assert.isNull(curPage)) {
            curPage = 1;
        }

        JSONObject conditions = JSONObject.fromObject(conditions1);

        System.out.println(conditions);

        //登录用户名作为缓存的key
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idList", idList);
        jsonObject.put("id", id);
        jsonObject.put("curNo", curNo);
        jsonObject.put("curPage", curPage);
        jsonObject.put("conditions", conditions);
        cacheUtil.put(loginName, jsonObject);
        return sendJsonObject(AJAX_SUCCESS_CODE);
    }

    /**
     * 去详情页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String toCheck(@PathVariable("id") int curId, Model model, RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        String loginName = subject.getPrincipal().toString();
        JSONObject statusInfoCache = (JSONObject) cacheUtil.get(loginName);
        List<Material> materialList = Collections.emptyList();
        List<UpdateInfoDto> updateInfoList = Collections.emptyList();
        List<UserInfoDto> userInfoDtoList = Collections.emptyList();
        int canCheck = 0; // 是否可以审核； 1-可以； 2-学生信息没有提交不可以； 3-学生专业与辅导员所管理专业不匹配不可以
        if (Assert.isNull(statusInfoCache)) {
            String failedUrl = "/" + URLConstants.ADMIN_STU_STATUS_URL + "/list";
            redirectAttributes.addFlashAttribute("errMsg", "缓存出错");
            return "redirect:" + failedUrl;
        }
        try {
            Integer dispatchId = dispatchInfoService.getIdByStuId(curId);
            if (Assert.isNull(dispatchId)) {
                model.addAttribute("dispatchId", 0);
            } else {
                model.addAttribute("dispatchId", dispatchId);
            }
            List<String> colleges = null;
            String majorQualification = "";

            // 查询学生信息
            StuStatusInfoDto stuStatusInfo = stuStatusInfoService.queryDtoById(curId);
            if (subject.hasRole("college-secretary")) {
                colleges = userInfoService.listCollegesByUserName(loginName);
                if (Assert.isEmpty(colleges) || colleges.size() <= 0) {
                    colleges.add("0");
                }
            } else if (subject.hasRole("counsellor")) {
                colleges = userInfoService.listCollegesByUserName(loginName);
                if (Assert.isEmpty(colleges) || colleges.size() <= 0) {
                    colleges.add("0");
                }
                userInfoDtoList = userInfoService.listMajorsAndQualificationByUserName(loginName);
                // 如果该学生已经提交信息
                if (stuStatusInfo.getCheckStatus() != 0
                        && stuStatusInfo.getCheckStatus() != 1){
                    // 判断辅导员是否可以审核该学生
                    int flag = 0;
                    for (UserInfoDto userInfoDto: userInfoDtoList){
                        if (userInfoDto.getMajorQualification().equals(stuStatusInfo.getMajorQualification())
                                && userInfoDto.getMajorCode().equals(stuStatusInfo.getMajorCode())){
                            flag = 1; // 学生专业与辅导员专业相同，可以审核
                        }
                    }

                    if(flag == 1) {
                        canCheck = 1;  // 学生专业与辅导员专业相同，可以审核
                    } else {
                        canCheck = 3;  // 学生专业与辅导员专业不同，不可以审核
                    }
                } else {
                    canCheck = 2;   //学生信息没有提交，不能审核
                }
//                majors = userInfoService.listMajorsByUserName(loginName);
//                if (Assert.isEmpty(majors) || majors.size() <= 0) {
//                    majors.add("0");
//                }
                // 获取登陆者的专业层次
                majorQualification = userInfoDtoList.get(0).getMajorQualification();
            }


            materialList = materialService.listByStatusInfoIdAndType(curId, MaterialTypeEnums.StatusInfo.getId());
            int  isStudent = 0;//是否是学生，是学生的话只查看其他人给他修改的地方，1为学生，0为其他人
            updateInfoList = stuUpdateInfoService.listLatestDtoByStuStatusInfoId(curId,isStudent);
            List<Integer> idList = JSONArray.toList(statusInfoCache.getJSONArray("idList"), Integer.class);
            int id = curId;
            int curNo = idList.indexOf(curId);
            int curPage = statusInfoCache.getInt("curPage");
            JSONObject conditions = statusInfoCache.getJSONObject("conditions");
            int stuType, college, normalStu, counsellorCheckResult, deputySecretaryCheckResult, schoolCheckResult, province, trainingMode,
                    qualificationNow, showUncommitted, sex, politicalStand, isRegistered;
            double stuLength;
            String qualification, keyword, nation, majorCode;



            //取出判断条件
            if (Assert.isNull(conditions) || conditions.size() == 0) {
                stuType = -1;
                college = -1;
                normalStu = -1;
                counsellorCheckResult = -1;
                deputySecretaryCheckResult = -1;
                schoolCheckResult = -1;
                province = -1;
                trainingMode = -1;
                qualification = null;
                stuLength = -1;
                keyword = null;
                sex = -1;
                majorCode = null;
                politicalStand = -1;
                stuLength = -1;
                qualificationNow = -1;
                showUncommitted = -1;
                nation = null;
                isRegistered = -1;
            } else {
                if (conditions.has("stuType")) {
                    stuType = conditions.getInt("stuType");
                } else {
                    stuType = -1;
                }
                if (conditions.has("college")) {
                    college = conditions.getInt("college");
                } else {
                    college = -1;
                }
                if (conditions.has("normalStu")) {
                    normalStu = conditions.getInt("normalStu");
                } else {
                    normalStu = -1;
                }
                if (conditions.has("counsellorCheckResult")) {
                    counsellorCheckResult = conditions.getInt("counsellorCheckResult");
                } else {
                    counsellorCheckResult = -1;
                }
                if (conditions.has("deputySecretaryCheckResult")) {
                    deputySecretaryCheckResult = conditions.getInt("deputySecretaryCheckResult");
                } else {
                    deputySecretaryCheckResult = -1;
                }
                if (conditions.has("schoolCheckResult")) {
                    schoolCheckResult = conditions.getInt("schoolCheckResult");
                } else {
                    schoolCheckResult = -1;
                }
                if (conditions.has("province")) {
                    province = conditions.getInt("province");
                } else {
                    province = -1;
                }

                if (conditions.has("trainingMode")) {
                    trainingMode = conditions.getInt("trainingMode");
                } else {
                    trainingMode = -1;
                }
                if (conditions.has("qualification")) {
                    qualification = conditions.getString("qualification");
                } else {
                    qualification = null;
                }
                if (conditions.has("keyword")) {
                    keyword = conditions.getString("keyword");
                } else {
                    keyword = null;
                }
                if (conditions.has("sex")) {
                    sex = conditions.getInt("sex");
                } else {
                    sex = -1;
                }
                //
                if (conditions.has("majorCode")) {
                    majorCode = conditions.getString("majorCode");
                } else {
                    majorCode = null;
                }
                if (conditions.has("politicalStand")) {
                    politicalStand = conditions.getInt("politicalStand");
                } else {
                    politicalStand = -1;
                }
                if (conditions.has("stuLength")) {
                    stuLength = conditions.getDouble("stuLength");
                } else {
                    stuLength = -1;
                }
                if (conditions.has("qualificationNow")) {
                    qualificationNow = conditions.getInt("qualificationNow");
                } else {
                    qualificationNow = -1;
                }
                if (conditions.has("showUncommitted")) {
                    showUncommitted = conditions.getInt("showUncommitted");
                } else {
                    showUncommitted = 1;
                }
                if (conditions.has("nation")) {
                    nation = conditions.getString("nation");
                } else {
                    nation = null;
                }
                if (conditions.has("isRegistered")) {
                    isRegistered = conditions.getInt("isRegistered");
                } else {
                    isRegistered = -1;
                }
            }
            int count = stuStatusInfoService.countByCondition(stuType, college, normalStu, counsellorCheckResult, deputySecretaryCheckResult,
                    schoolCheckResult, province, qualification, trainingMode, keyword, sex, politicalStand,  stuLength,colleges, majorCode, majorQualification, null, qualificationNow, nation, showUncommitted, isRegistered);
            //如果当前id在list中查不到，说明在前一页，则下一个id取第一个即可
            if (curNo != -1) {
                curNo += 1;
            } else {
//                curPage -= 1;
//                Pagination page = new Pagination(count, pageSize, curPage);
//                idList = stuStatusInfoService.listIdByCondition(stuType, college, normalStu, counsellorCheckResult, deputySecretaryCheckResult,
//                        schoolCheckResult, province, qualification, trainingMode, keyword, colleges, majors, page);
                curNo = 0;
            }
            if (curNo < idList.size()) {
                id = idList.get(curNo);
                curNo += 1;
            } else if (idList.size() < pageSize) {
                //判断是否最后一页，如果是最后一页则令id = 0
                id = 0;
            } else {
                //判断当页有15条数据的情况下是否最后一页，如果是最后一页则令id = 0，如果不是则重置curNo，curPage+1
                int totalPage = count / pageSize + 1;
                if (curPage < totalPage) {
                    curPage += 1;
                    Pagination page = new Pagination(count, pageSize, curPage);
                    idList = stuStatusInfoService.listIdByCondition(stuType, college, normalStu, counsellorCheckResult, deputySecretaryCheckResult,
                            schoolCheckResult, province, qualification, trainingMode, keyword, sex, politicalStand, stuLength, colleges, majorCode, majorQualification, null, qualificationNow,
                            nation, showUncommitted, page, isRegistered);
                    id = idList.get(0);
                    curNo = 0;
                } else {
                    id = 0;
                }
            }

            statusInfoCache.put("id", id);
            statusInfoCache.put("idList", idList);
            statusInfoCache.put("curNo", curNo);
            statusInfoCache.put("curPage", curPage);

            cacheUtil.put(loginName, statusInfoCache);

            model.addAttribute("stuStatusInfo", stuStatusInfo);
            model.addAttribute("updateInfoList", updateInfoList);
            model.addAttribute("statusInfoCache", statusInfoCache);
            model.addAttribute("materialList", materialList);
            model.addAttribute("canCheck", canCheck);
            model.addAttribute("minority", 0);


//            System.out.println(stuStatusInfo.getCounsellorCheckResult()+"啦啦啦啦啦啦啦");
            return "admin/status/detail";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 从派遣信息跳转到对应的学籍信息
     *
     * @param id
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String toInfo(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        //手动清理缓存
        Subject subject = SecurityUtils.getSubject();
        String loginName = subject.getPrincipal().toString();
        cacheUtil.remove(loginName);
        System.out.println("缓存清理成功");

        try {
            StuStatusInfoDto stuStatusInfo = stuStatusInfoService.queryDtoById(id);
            List<Material> materialList = materialService.listByStatusInfoIdAndType(id, MaterialTypeEnums.StatusInfo.getId());
            int  isStudent = 0;//是否是学生，是学生的话只查看其他人给他修改的地方，1为学生，0为其他人
            List<UpdateInfoDto> updateInfoList = stuUpdateInfoService.listLatestDtoByStuStatusInfoId(id,isStudent);
            JSONObject statusInfoCache = new JSONObject();

            statusInfoCache.put("id", 0);

            model.addAttribute("stuStatusInfo", stuStatusInfo);
            model.addAttribute("statusInfoCache", statusInfoCache);
            model.addAttribute("updateInfoList", updateInfoList);
            model.addAttribute("materialList", materialList);

            return "admin/status/detail";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * ajax修改审核结果
     *
     * @param id
     * @param level
     * @param checkResult
     * @param checkReason
     * @param otherReason
     * @return
     */
    @RequestMapping(value = "ajax/check", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject doCheck(@RequestParam("id") int id, @RequestParam("checkLevel") String level, @RequestParam("checkResult") int checkResult,
                              @RequestParam("checkReason") int checkReason, @RequestParam(value = "otherReason", required = false) String otherReason) throws IOException {
        StuStatusInfo stuStatusInfo = new StuStatusInfo();
        stuStatusInfo.setId(id);
        String reason;
        Date date = new Date();
        Subject subject = SecurityUtils.getSubject();
        String realName = subject.getSession().getAttribute("realName").toString();
        if (checkReason == CheckReasonEnums.ElseReason.getId()) {
            reason = otherReason;
        } else {
            reason = CheckReasonEnums.valueOf(checkReason).getName();
        }
        try {

            StuStatusInfo oldInfo = stuStatusInfoService.queryById(id);
            switch (level) {
                case "counsellor":
                    if (!subject.hasRole("admin") && !subject.hasRole("director") && !subject.hasRole("counsellor") && !subject.hasRole("employ")) {
                        return sendErrMsgAndErrCode(MessageInfo.NO_PERMISSION);
                    }

                    stuStatusInfo.setCounsellorCheckResult(checkResult);
                    stuStatusInfo.setCounsellorCheckReason(reason);
                    stuStatusInfo.setCounsellorCheckTime(date);
                    stuStatusInfo.setCounsellorCheckOperator(realName);
                    //如果辅导员审核通过
                    if (checkResult == 2) {
                        //审核状态置为辅导员审核通过
                        stuStatusInfo.setCheckStatus(CheckStatusEnums.CounsellorChecked.getId());
                        //如果辅导员审核未通过待修改
                    } else if (checkResult == 3) {
                        //发短信
                        if (checkReason==2){
                            smsService.sendSms(oldInfo.getName() + WebConstants.SMSOFXUEJI2, oldInfo.getCellphone());
                        }else if (checkReason==3){
                            smsService.sendSms(oldInfo.getName() +WebConstants.SMSOFXUEJI3, oldInfo.getCellphone());
                        }else if (checkReason==4){
                            smsService.sendSms(oldInfo.getName() +WebConstants.SMSOFXUEJI4, oldInfo.getCellphone());
                        }else if (checkReason==5){
                            smsService.sendSms(oldInfo.getName() +WebConstants.SMSOFXUEJI5, oldInfo.getCellphone());
                        }else if (checkReason==6){
                            smsService.sendSms(oldInfo.getName() +WebConstants.SMSOFXUEJI6, oldInfo.getCellphone());
                        }else {
                            smsService.sendSms(oldInfo.getName() +WebConstants.SMSOFXUEJI99+otherReason+WebConstants.SMSOFXUEJI99ADD, oldInfo.getCellphone());
                        }
                        stuStatusInfo.setCheckStatus(CheckStatusEnums.CounsellorNotThrough.getId());
                    } else if (checkResult == 4) {
                        stuStatusInfo.setCheckStatus(CheckStatusEnums.CounsellorModified.getId());
                    }
                    break;
                case "deputySecretary":
                    if (!subject.hasRole("admin") && !subject.hasRole("director") && !subject.hasRole("college-secretary")&&!subject.hasRole("employ")) {
                        return sendErrMsgAndErrCode("没有权限进行该操作!");
                    }
                    stuStatusInfo.setDeputySecretaryCheckResult(checkResult);
                    stuStatusInfo.setDeputySecretaryCheckReason(reason);
                    stuStatusInfo.setDeputySecretaryCheckTime(date);
                    stuStatusInfo.setDeputySecretaryCheckOperator(realName);
                    //如果副书记审核通过
                    if (checkResult == 2) {
                        //审核状态置为副书记审核通过
                        stuStatusInfo.setCheckStatus(CheckStatusEnums.DeputySecretaryChecked.getId());
                        //如果副书记审核未通过待修改，把辅导员状态也设置为待修改
                    } else if (checkResult == 3) {
                        stuStatusInfo.setCheckStatus(CheckStatusEnums.DeputySecretaryNotThrough.getId());
                        stuStatusInfo.setCounsellorCheckResult(checkResult);
                        stuStatusInfo.setCounsellorCheckReason(reason);
                        stuStatusInfo.setCounsellorCheckTime(date);
                        //发短信
                        if (checkReason==2){
                            smsService.sendSms(oldInfo.getName() + WebConstants.SMSOFXUEJI2, oldInfo.getCellphone());
                        }else if (checkReason==3){
                            smsService.sendSms(oldInfo.getName() +WebConstants.SMSOFXUEJI3, oldInfo.getCellphone());
                        }else if (checkReason==4){
                            smsService.sendSms(oldInfo.getName() +WebConstants.SMSOFXUEJI4, oldInfo.getCellphone());
                        }else if (checkReason==5){
                            smsService.sendSms(oldInfo.getName() +WebConstants.SMSOFXUEJI5, oldInfo.getCellphone());
                        }else if (checkReason==6){
                            smsService.sendSms(oldInfo.getName() +WebConstants.SMSOFXUEJI6, oldInfo.getCellphone());
                        }else {
                            smsService.sendSms(oldInfo.getName() +WebConstants.SMSOFXUEJI99+otherReason+WebConstants.SMSOFXUEJI99ADD, oldInfo.getCellphone());
                        }
                    } else if (checkResult == 4) {
                        stuStatusInfo.setCheckStatus(CheckStatusEnums.DeputySecretaryModified.getId());
                    }
                    break;
                case "school":
                    if (!subject.hasRole("admin") && !subject.hasRole("director") && !subject.hasRole("employ")) {
                        return sendErrMsgAndErrCode("没有权限进行该操作!");
                    }
                    stuStatusInfo.setCounsellorCheckResult(1);
                    stuStatusInfo.setCounsellorCheckReason("");
                    stuStatusInfo.setCounsellorCheckTime(null);
                    stuStatusInfo.setCounsellorCheckOperator("");
                    stuStatusInfo.setDeputySecretaryCheckResult(1);
                    stuStatusInfo.setDeputySecretaryCheckReason("");
                    stuStatusInfo.setDeputySecretaryCheckTime(null);
                    stuStatusInfo.setDeputySecretaryCheckOperator("");
                    stuStatusInfo.setSchoolCheckResult(1);
                    stuStatusInfo.setSchoolCheckReason("");
                    stuStatusInfo.setSchoolCheckTime(null);
                    stuStatusInfo.setSchoolCheckOperator("");
                    stuStatusInfo.setCheckStatus(0);
                    //发短信
                    smsService.sendSms(oldInfo.getName() + WebConstants.SMSOFXUEJI, oldInfo.getCellphone());
//                    stuStatusInfo.setSchoolCheckResult(checkResult);
//                    stuStatusInfo.setSchoolCheckReason(reason);
//                    stuStatusInfo.setSchoolCheckTime(date);
//                    stuStatusInfo.setSchoolCheckOperator(realName);
//                    //如果学校审核通过
//                    if (checkResult == 2) {
//                        //如果辅导员或副书记未审核，说明是学校直接越级审核
//                        if (oldInfo.getCounsellorCheckResult() == 1 || oldInfo.getDeputySecretaryCheckResult() == 1) {
//                            stuStatusInfo.setCheckStatus(CheckStatusEnums.SchoolDirectChecked.getId());
//                        } else {
//                            stuStatusInfo.setCheckStatus(CheckStatusEnums.SchoolChecked.getId());
//                        }
//                        //如果学校审核未通过待修改
//                    } else if (checkResult == 3) {
//                        if (oldInfo.getCounsellorCheckResult() == 1 || oldInfo.getDeputySecretaryCheckResult() == 1) {
//                            stuStatusInfo.setCheckStatus(CheckStatusEnums.SchoolDirectNotThrough.getId());
//                        } else {
//                            stuStatusInfo.setCheckStatus(CheckStatusEnums.SchoolNotThrough.getId());
//                        }
//                    } else if (checkResult == 4) {
//                        if (oldInfo.getCounsellorCheckResult() == 1 || oldInfo.getDeputySecretaryCheckResult() == 1) {
//                            stuStatusInfo.setCheckStatus(CheckStatusEnums.SchoolDirectModified.getId());
//                        } else {
//                            stuStatusInfo.setCheckStatus(CheckStatusEnums.SchoolModified.getId());
//                        }
//                    }
                    break;
            }
            System.out.println(stuStatusInfo);
            stuStatusInfoService.updateById(stuStatusInfo);
            return sendJsonObject(AJAX_SUCCESS_CODE);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 去新增学籍页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String toNewStuStatusInfo(Model model) {
        try {
            List<NationCode> nationList = nationService.listAll();
            List<PoliticalCode> politicalList = politicalService.listAll();
            List<QualificationCode> qualificationList = qualificationService.listAll();
            List<NormalCode> normalList = normalService.listAll();
            List<CollegeCode> collegeList = collegeService.listAll();
            List<TrainingModeCode> trainingModeList = trainingModeService.listAll();

            model.addAttribute("nationList", nationList);
            model.addAttribute("politicalList", politicalList);
            model.addAttribute("qualificationList", qualificationList);
            model.addAttribute("normalList", normalList);
            model.addAttribute("collegeList", collegeList);
            model.addAttribute("trainingModeList", trainingModeList);
            return "admin/status/new";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 新增学生学籍
     *
     * @param stuStatusInfo
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String newStuStatusInfo(StuStatusInfo stuStatusInfo, RedirectAttributes redirectAttributes ,Model model) {
        try {

            /****
             *  新增校验，以下情况返回500.jsp:
             *  1、生源地不为空，但生源地代码为空或0；
             *  2、培养方式代码为0
             *  3、委培单位不为空，但委培单位地址为空
             */
            if(stuStatusInfo != null) {
                String originPlace = stuStatusInfo.getOriginPlace();
                int originPlaceCode = stuStatusInfo.getOriginPlaceCode();

                String weipeiUnitPlace = stuStatusInfo.getWeipeiUnitPlace();
                int weipeiUnitPlaceCode = stuStatusInfo.getWeipeiUnitPlaceCode();

                int trainingModeCode = stuStatusInfo.getTrainingModeCode();

                if (originPlace != "" && originPlace != null && originPlaceCode == 0) {
                    return "500";
                }
                if (weipeiUnitPlace != "" && weipeiUnitPlace != null && weipeiUnitPlaceCode == 0) {
                    return "500";
                }
                if (trainingModeCode == 0) {
                    return "500";
                }
            }

            /******校验结束********/

            stuStatusInfo.setStuType(StuTypeEnums.AdminInsert.getId());
            stuStatusInfoService.newStuStatusInfo(stuStatusInfo);
            String successUrl = "/" + URLConstants.ADMIN_STU_STATUS_URL + "/list";
            redirectAttributes.addFlashAttribute("correctMsg", NEW_SUCCESS_MSG);
            return "redirect:" + successUrl;
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
//            String failedUrl = "/" + URLConstants.ADMIN_STU_STATUS_URL + "/new";
//            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
//            return "redirect:" + failedUrl;
            return "500";
        }
    }

    /**
     * 删除学生
     *
     * @param statusInfoId
     * @return
     */
    @RequestMapping(value = "ajax/delete", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject deleteStuStatusInfo(@RequestParam("id") Integer statusInfoId) {
        try {
            if (Assert.lessOrEqualZero(statusInfoId)) {
                throw SSException.get(OaException.IdIllegal);
            }
            Subject subject = SecurityUtils.getSubject();
            if (subject.hasRole("admin") || subject.hasRole("counsellor")||subject.hasRole("college-secretary")||subject.hasRole("employ")) {
                stuStatusInfoService.deleteStudentInfo(statusInfoId);
                return sendJsonObject(AJAX_SUCCESS_CODE);
            } else {
                return sendErrMsgAndErrCode("您暂无权限删除学生信息！");
            }
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 去修改页
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String toUpdate(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            StuStatusInfo stuStatusInfo = stuStatusInfoService.queryById(id);
            StuStatusInfoDto stuStatusInfoDto = stuStatusInfoService.queryDtoById(stuStatusInfo.getId());
            Subject subject = SecurityUtils.getSubject();

            /*如果该学生辅导员已经审核过，那么辅导员不能对该学生的信息进行修改，辅导员审核结果=2，则审核通过*/
            if (subject.hasRole("counsellor") && stuStatusInfo.getCounsellorCheckResult() == 2) {
//                model.addAttribute("errMsg", "该学生审核已通过，如需修改请与就业中心联系");//前端怎么去提示错误
//                redirectAttributes.addFlashAttribute("errMsg", "该学生审核已通过，如需修改请与就业中心联系");
//                return "redirect:admin/status/list";
                String failUrl = "/" + URLConstants.ADMIN_STU_STATUS_URL + "/list";
                redirectAttributes.addFlashAttribute("errMsg", UPDATE_ERROR_CON_MSG);
                return "redirect:" + failUrl;
            }
            /*如果该学生副书记已经审核过，那么副书记不能对该学生的信息进行修改，副书记审核结果=2，则审核通过*/
            if (subject.hasRole("college-secretary") && stuStatusInfo.getDeputySecretaryCheckResult() == 2){
                String failUrl = "/" + URLConstants.ADMIN_STU_STATUS_URL + "/list";
                redirectAttributes.addFlashAttribute("errMsg", UPDATE_ERROR_MSG);
                return "redirect:" + failUrl;
            }
            if (!Assert.isNull(stuStatusInfoDto)) {
                List<Material> materialList = materialService.listByStatusInfoIdAndType(stuStatusInfo.getId(), MaterialTypeEnums.StatusInfo.getId());

                List<NationCode> nationList = nationService.listAll();
                List<PoliticalCode> politicalList = politicalService.listAll();
                List<QualificationCode> qualificationList = qualificationService.listAll();
                List<CollegeCode> collegeList = collegeService.listAll();
//                List<MajorDivision> majorBigList = majorService.listBigAll();
//                List<WhereAboutGoCode> whereAboutGoList = whereAboutGoService.listAll();
                List<WhereAboutGoIntendCode> whereAboutGoIntendCodeList = whereAboutGoIntendService.listAll();
                List<DifficultyCode> difficultyList = difficultyService.listAll();
                List<TrainingModeCode> trainingModeList = trainingModeService.listAll();
                MajorCode major = null;
                if (!Assert.isNull(stuStatusInfo.getMajorCode())) {
                    major = majorService.queryByCode(stuStatusInfo.getMajorCode(), stuStatusInfo.getMajorQualification());
                }
                String normalStuType = NorMalStuTypeEnums.valueOf(stuStatusInfo.getNormalStuCode()).getName();
                // 学生填写的生源地
                String originPlace = "";
                if (!stuStatusInfo.getOriginPlaceCode().equals(0)){
                    originPlace = placeService.queryByCode(stuStatusInfo.getOriginPlaceCode().toString()).getShowName();
                }
                String originPlaceImport = stuStatusInfo.getOriginPlaceImport();
                String trainingMode = trainingModeService.queryByCode(stuStatusInfo.getTrainingModeCode()).getTrainingMode();
                String school = null;
                if (!Assert.isNull(stuStatusInfo.getSchoolCode()) && !Assert.lessOrEqualZero(stuStatusInfo.getSchoolCode())) {
                    school = schoolService.queryDtoByCode(String.valueOf(stuStatusInfo.getSchoolCode())).getName();
                } else {
                    school = WebConstants.school;
                }

                model.addAttribute("student", stuStatusInfo);
                model.addAttribute("nationList", nationList);
                model.addAttribute("politicalList", politicalList);
                model.addAttribute("qualificationList", qualificationList);
                model.addAttribute("collegeList", collegeList);
//                model.addAttribute("majorBigList", majorBigList);
                model.addAttribute("major", major);
                model.addAttribute("whereAboutGoIntendCodeList", whereAboutGoIntendCodeList);
                model.addAttribute("difficultyList", difficultyList);
                model.addAttribute("normalStuType", normalStuType);
                model.addAttribute("originPlace", originPlace);
                model.addAttribute("originPlaceImport", originPlaceImport);
                model.addAttribute("trainingMode", trainingMode);
                model.addAttribute("trainingModeList", trainingModeList);
                model.addAttribute("schoolName", school);
                model.addAttribute("studentDto", stuStatusInfoDto);
                model.addAttribute("materialList", materialList);
            }
            return "admin/status/update";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 修改学籍
     * @param stuStatusInfo
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(StuStatusInfo stuStatusInfo, RedirectAttributes redirectAttributes) throws IOException {
        try {

            /****
             *  新增校验，以下情况返回500.jsp:
             *  1、生源地不为空，但生源地代码为空或0；
             *  2、培养方式代码为0
             *  3、委培单位不为空，但委培单位地址为空
             */
            if(stuStatusInfo != null) {
                String originPlace = stuStatusInfo.getOriginPlace();
                int originPlaceCode = stuStatusInfo.getOriginPlaceCode();

                String weipeiUnitPlace = stuStatusInfo.getWeipeiUnitPlace();
                int weipeiUnitPlaceCode = stuStatusInfo.getWeipeiUnitPlaceCode();


                if (originPlace != "" && originPlace != null && originPlaceCode == 0) {
                    return "500";
                }
                if (weipeiUnitPlace != "" && weipeiUnitPlace != null && weipeiUnitPlaceCode == 0) {
                    return "500";
                }
            }
            /******校验结束********/


            Subject subject = SecurityUtils.getSubject();
            StuStatusInfo oldStuStatusInfo = stuStatusInfoService.queryById(stuStatusInfo.getId());
            if (Assert.isNull(oldStuStatusInfo.getSchoolCode()) || Assert.lessOrEqualZero(oldStuStatusInfo.getSchoolCode())) {
                stuStatusInfo.setSchoolCode(WebConstants.schoolCode);
            }
            if (stuStatusInfoService.materialUpdateById(stuStatusInfo)) {
                // stuStatusInfoService.adminUpdateById(stuStatusInfo);
                //在通过辅导员对学生信息进行修改后，审核状态变为4-辅导员审核未通过已修改
                if (subject.hasRole("counsellor")){
                    stuStatusInfo.setCounsellorCheckResult(1);
                    stuStatusInfo.setDeputySecretaryCheckResult(1);
                    stuStatusInfo.setSchoolCheckResult(1);
                    stuStatusInfo.setCheckStatus(4);
                }
                //在通过副书记段对学生信息进行修改后，审核状态变为7-副书记审核未通过已修改
                if(subject.hasRole("college-secretary")) {
                    stuStatusInfo.setCounsellorCheckResult(1);
                    stuStatusInfo.setDeputySecretaryCheckResult(1);
                    stuStatusInfo.setSchoolCheckResult(1);
                    stuStatusInfo.setCheckStatus(7);
                }

            //检查学生学籍的信息(不包括师范生类别、培养方式、定向委培单位)是否修改，如果修改则在t_stu_update_info表中插入一条数据
            int alterByWho = 0;//被除学生外的人修改
            stuUpdateInfoService.whereIsAltered(stuStatusInfo,alterByWho);
            stuStatusInfoService.adminUpdateById(stuStatusInfo);
            String successUrl = "/" + URLConstants.ADMIN_STU_STATUS_URL + "/list";
            redirectAttributes.addFlashAttribute("correctMsg", NEW_SUCCESS_MSG);
            return "redirect:" + successUrl;
            }else{
                String failedUrl = "/" + URLConstants.ADMIN_STU_STATUS_URL + "/update/" + stuStatusInfo.getId();
                redirectAttributes.addFlashAttribute("errMsg", "材料不足，请上传材料");
                return "redirect:" + failedUrl;
            }
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.ADMIN_STU_STATUS_URL + "/update/" + stuStatusInfo.getId();
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    /**
     * ajax上传材料
     *
     * @param file
     * @param id
     * @return
     */
    @RequestMapping(value = "ajax/upload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(@RequestParam("file") PandaworkMultipartFile file, @RequestParam("id") int id) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            Material material = new Material();
            material.setStatusInfoId(id);
            material.setMaterialName(file.getOriginalFilename());
            material.setMaterialType(MaterialTypeEnums.StatusInfo.getId());
            String path = stuStatusInfoService.uploadMaterial(file, material);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("path", path);
            return sendJsonObject(jsonObject);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 副书记批量审核
     *
     * @param ids 前台JS数组
     * @return JSON对象
     */
    @RequestMapping(value = "ajax/audit", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject batchAudit(@RequestParam("id[]") List<Integer> ids) {
        try {
            Subject subject = SecurityUtils.getSubject();
            String realName = subject.getSession().getAttribute("realName").toString();
            Date date = new Date();
            // 批量审核加入审核人和时间
            if (stuStatusInfoService.batchAudit(ids, date, realName)) {
                return sendJsonObject(AJAX_SUCCESS_CODE);
            } else {
                return sendErrMsgAndErrCode("部分同学尚未通过辅导员审核！");
            }

        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode("操作失败！");
        }
    }

    /**
     * 批量打回功能
     *
     * @param ids 前台JS数组
     * @return JSON对象
     */
    @RequestMapping(value = "ajax/repulse", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject batchRepulse(@RequestParam("id[]") List<Integer> ids) {
        try {
            for (int i = 0; i < ids.size(); i++) {
                StuStatusInfo stuStatusInfo = new StuStatusInfo();
                stuStatusInfo.setId(ids.get(i));
                stuStatusInfo.setCounsellorCheckResult(1);
                stuStatusInfo.setCounsellorCheckReason("");
                stuStatusInfo.setCounsellorCheckTime(null);
                stuStatusInfo.setCounsellorCheckOperator("");
                stuStatusInfo.setDeputySecretaryCheckResult(1);
                stuStatusInfo.setDeputySecretaryCheckReason("");
                stuStatusInfo.setDeputySecretaryCheckTime(null);
                stuStatusInfo.setDeputySecretaryCheckOperator("");
                stuStatusInfo.setSchoolCheckResult(1);
                stuStatusInfo.setSchoolCheckReason("");
                stuStatusInfo.setSchoolCheckTime(null);
                stuStatusInfo.setSchoolCheckOperator("");
                stuStatusInfo.setCheckStatus(0);
                stuStatusInfoService.updateById(stuStatusInfo);
            }
            return sendJsonObject(AJAX_SUCCESS_CODE);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode("操作失败！");
        }
    }

    /**
     * 批量删除功能
     *
     * @param ids 前台JS数组
     * @return JSON对象
     */
    @ResponseBody
    @RequestMapping(value = "ajax/batch/delete", method = RequestMethod.GET)
    public JSONObject batchDelete(@RequestParam("id[]") List<Integer> ids){
        try{
            for (int i= 0;i < ids.size();i++){
                stuStatusInfoService.deleteStudentInfo(ids.get(i));
            }
            return sendJsonObject(AJAX_SUCCESS_CODE);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode("操作失败！");
        }
    }
}
