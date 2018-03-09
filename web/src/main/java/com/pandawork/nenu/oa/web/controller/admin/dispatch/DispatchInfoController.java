package com.pandawork.nenu.oa.web.controller.admin.dispatch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchQueryDto;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchUpdateDto;
import com.pandawork.nenu.oa.common.dto.student.status.StuStatusInfoDto;
import com.pandawork.nenu.oa.common.dto.user.UserInfoDto;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import com.pandawork.nenu.oa.common.entity.data.*;
import com.pandawork.nenu.oa.common.entity.dispatch.*;
import com.pandawork.nenu.oa.common.entity.general.CompanyProperty;
import com.pandawork.nenu.oa.common.entity.general.CompanyTrade;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.entity.student.status.StuStatusInfo;
import com.pandawork.nenu.oa.common.entity.user.User;
import com.pandawork.nenu.oa.common.enums.general.CheckReasonEnums;
import com.pandawork.nenu.oa.common.enums.general.CheckResultEnums;
import com.pandawork.nenu.oa.common.enums.general.CheckStatusEnums;
import com.pandawork.nenu.oa.common.enums.general.MaterialTypeEnums;
import com.pandawork.nenu.oa.common.util.CacheUtil;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.common.util.WebConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * R
 * Created by qiuxiao on 2016/7/14.
 */
@Controller
@RequestMapping(URLConstants.ADMIN_DISPATCH_URL)
public class DispatchInfoController extends AbstractController {
    private CacheUtil cacheUtil = new CacheUtil();
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 修改管理员派遣信息
     *
     * @param id
     * @throws SSException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam(value = "id", required = true) Integer id,
                         DispatchInfo dispatchInfo,
                         RedirectAttributes redirectAttributes) throws SSException {
        try {
            int alterByWho = 0;//被除学生外的人修改
            dispatchUpdateInfoService.whereIsAltered(dispatchInfo,alterByWho);
            dispatchInfoService.updateDispatchInfo(dispatchInfo);
            String successUrl = "/" + URLConstants.ADMIN_DISPATCH_URL + "/list";
            redirectAttributes.addFlashAttribute("msg", UPDATE_SUCCESS_MSG);
            return "redirect:" + successUrl;
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.ADMIN_DISPATCH_URL + "/alter/" + dispatchInfo.getId() + "/" + dispatchInfo.getStatusInfoId();
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    //跳转到管理员派遣信息更新页面
    @RequestMapping(value = "/alter/{id}/{stuId}", method = RequestMethod.GET)
    public String getUpdateInfo(@PathVariable("id") Integer id, @PathVariable("stuId") Integer stuId, Model model,RedirectAttributes redirectAttributes) throws SSException {
        DispatchQueryDto dispatchQueryDto = dispatchInfoService.getDetail(id);
        Subject subject = SecurityUtils.getSubject();
        /*如果该学生辅导员已经审核过，那么辅导员不能对该学生的信息进行修改，辅导员审核结果=2，则审核通过*/
        if (subject.hasRole("counsellor") && dispatchQueryDto.getCounsellorCheckResult() == 2) {
            String failUrl = "/" + URLConstants.ADMIN_DISPATCH_URL + "/list";
            redirectAttributes.addFlashAttribute("errMsg", UPDATE_ERROR_MSG);
            return "redirect:" + failUrl;
        }
            /*如果该学生副书记已经审核过，那么副书记不能对该学生的信息进行修改，副书记审核结果=2，则审核通过*/
        if (subject.hasRole("college-secretary") && dispatchQueryDto.getDeputySecretaryCheckResult() == 2){
            String failUrl = "/" + URLConstants.ADMIN_DISPATCH_URL + "/list";
            redirectAttributes.addFlashAttribute("errMsg", UPDATE_ERROR_MSG);
            return "redirect:" + failUrl;
        }
        List<WhereAboutGoCode> whereAboutGoCodes = whereAboutGoService.listAll();
        List<CompanyProperty> companyProperties = companyPropertyService.listAll();
        List<CompanyTrade> companyTrades = companyTradeService.listAll();
        List<Material> materials = materialService.listByStatusInfoIdAndType(stuId, MaterialTypeEnums.Dispatch.getId());
        List<JobCode> jobCodes = jobCodeService.listAll();
        List<ReportCode> reportCodes = reportCodeService.listAll();
        DispatchExtendItem dispatchExtendItem = dispatchExtendItemService.queryByDispatchId(id);
        List<DispatchAdminRemark> dispatchAdminRemarkList = dispatchAdminRemarkService.queryByDispatchId(id);
        StuStatusInfo statusInfo = stuStatusInfoService.queryById(stuId);
        //sjz添加
        PlaceCode placeCode = placeService.queryByCode(String.valueOf(statusInfo.getOriginPlaceCode()));
        model.addAttribute("wc", whereAboutGoCodes);
        model.addAttribute("cp", companyProperties);
        model.addAttribute("ct", companyTrades);
        model.addAttribute("jc", jobCodes);
        model.addAttribute("mt", materials);
        model.addAttribute("rc", reportCodes);
        model.addAttribute("dh", dispatchQueryDto);
        model.addAttribute("dispatchExtendItem",dispatchExtendItem);
        model.addAttribute("dispatchAdminRemarkList",dispatchAdminRemarkList);
        model.addAttribute("placeCode",placeCode);
        return "admin/dispatch/alter";

    }


    /**
     * 跳转到管理员派遣信息详情页面
     * @param stuId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/dtl/{stuId}", method = RequestMethod.GET)
    public String getDetail(@PathVariable("stuId") int stuId, Model model) throws Exception {
        int studentId = stuId;
        Subject subject = SecurityUtils.getSubject();
        String loginName = subject.getPrincipal().toString();
        JSONObject statusInfoCache = (JSONObject) cacheUtil.get(loginName);
        List<Material> materialList = new ArrayList<>();
        Integer curId = dispatchInfoService.getIdByStuId(stuId);
        StuStatusInfoDto stuStatusInfoDto = stuStatusInfoService.queryDtoById(stuId);
        DispatchInfo dispatchInfo = dispatchInfoService.getDetail(curId);
        List<DispatchUpdateDto> dispatchUpdateDtoList = Collections.emptyList();
        List<UserInfoDto> userInfoDtoList = Collections.emptyList();
        int canCheck = 0; // 是否可以审核 0-不可以；1-可以
        if (Assert.isNull(statusInfoCache)) {
            System.out.println("缓存出错");
            return "redirect:/admin/dispatch/list";
        }
        try {

//            List<Change> changeList = Collections.emptyList();
//            changeList = changeService.queryAlterRecord(stuId);
//            String changeTypeName = null;
//            List<Protocol> protocolList = Collections.emptyList();
//            protocolList = protocolService.queryProtocolRecord(stuId);
//            String protocolTypeName = null;
//            List<Protocol> protocolAdminList = protocolAdminService.queryProtocolByStatusInfoId(stuId);
//            if(changeList == null || changeList.size() == 0){
//                changeTypeName = "暂无该业务";
//                model.addAttribute("changeTypeName", changeTypeName);
//            }else{
//                model.addAttribute("changeList", changeList);
//            }

//            List<Material> materialChangeList = Collections.emptyList();
//            materialChangeList = materialService.listAllByStatusInfoIdAndType(stuId, MaterialTypeEnums.IdentityChange.getId());
//            model.addAttribute("materialChangeList", materialChangeList);

//            if(protocolList == null || protocolList.size() == 0){
//                protocolTypeName = "暂无该业务";
//                model.addAttribute("protocolTypeName", protocolTypeName);
//            }else{
//                model.addAttribute("protocolList", protocolList);
//            }
//            if(protocolAdminList != null || protocolAdminList.size() != 0) {
//                for (Protocol protocol : protocolAdminList){
//                    if (protocol.getProtocolType() == 8){
//                        String freeTeacherProvince = "";
//                        freeTeacherProvince = provinceService.queryByCode(protocol.getFreeTeacherProvince()).getName();
//                        model.addAttribute("freeTeacherProvince",freeTeacherProvince);
//                    }
//                }
//            }

//            List<Material> materialProtocolList = Collections.emptyList();
//            materialProtocolList = materialService.listAllByStatusInfoIdAndType(stuId,
//                    MaterialTypeEnums.ApplyNewAgreement.getId());
//            model.addAttribute("materialProtocolList", materialProtocolList);
            //上面的代码先注释掉 应该没什么用了。

            List<Protocol> protocolList = Collections.emptyList();
            protocolList = protocolAdminService.queryProtocolByStatusInfoId(stuId);
            String protocolTypeName = null;

            if(protocolList == null || protocolList.size() == 0){
                protocolTypeName = "暂无该业务";
                model.addAttribute("protocolTypeName", protocolTypeName);
            }else{
                model.addAttribute("protocolList", protocolList);
            }


            DispatchQueryDto dispatchQueryDto = dispatchInfoService.getDetail(curId);
            materialList = materialService.listByStatusInfoIdAndType(stuId, MaterialTypeEnums.Dispatch.getId());
            int  isStudent = 0;//是否是学生，是学生的话只查看其他人给他修改的地方，1为学生，0为其他人
            dispatchUpdateDtoList = dispatchUpdateInfoService.listLatestDtoByDispatchInfo(curId,isStudent);
            List<Integer> idList = JSONArray.toList(statusInfoCache.getJSONArray("idList"), Integer.class);
            int curNo = idList.indexOf(stuId);
            int curPage = statusInfoCache.getInt("curPage");
            JSONObject conditions = statusInfoCache.getJSONObject("conditions");
            int stuType, whereaboutgoId, depsOrMajors, normalStu, counsellorCheckResult, deputySecretaryCheckResult, schoolCheckResult, provinceId, trainingModeCode, showUncommitted, sex, politicalStand, reportMode;
            String qualificationId, keyWord, nation;
            System.out.println(conditions);

            List<String> colleges = null;
            String majorQualification = "";

            // 查询学生信息
//            StuStatusInfoDto stuStatusInfo = stuStatusInfoService.queryDtoById(curId); //单继重修改 查询对象没有用到且curId应该为stuId
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
                if (stuStatusInfoDto.getCheckStatus() != 0
                        && stuStatusInfoDto.getCheckStatus() != 1) {
                    // 判断辅导员是否可以审核该学生
                    for (UserInfoDto userInfoDto : userInfoDtoList) {
                        if (userInfoDto.getMajorQualification().equals(stuStatusInfoDto.getMajorQualification())
                                && userInfoDto.getMajorCode().equals(stuStatusInfoDto.getMajorCode())) {
                            canCheck = 1; // 学生专业与辅导员专业相同，可以审核
                        }
                    }
                }
                // 获取登陆者的专业层次
                majorQualification = userInfoDtoList.get(0).getMajorQualification();
            }
            //取出判断条件
            if (Assert.isNull(conditions) || conditions.size() == 0) {
                stuType = -1;
                depsOrMajors = -1;
                normalStu = -1;
                counsellorCheckResult = -1;
                deputySecretaryCheckResult = -1;
                schoolCheckResult = -1;
                provinceId = -1;
                trainingModeCode = -1;
                whereaboutgoId = 0;
                qualificationId = null;
                keyWord = null;
                showUncommitted = -1;
                nation = null;
                sex = -1;
                politicalStand = -1;
                reportMode = -1;
            } else {
                if (conditions.has("stuType")) {
                    stuType = conditions.getInt("stuType");
                } else {
                    stuType = -1;
                }
                if (conditions.has("depsOrMajors")) {
                    depsOrMajors = conditions.getInt("depsOrMajors");
                } else {
                    depsOrMajors = -1;
                }
                if (conditions.has("normalStuId")) {
                    normalStu = conditions.getInt("normalStuId");
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
                if (conditions.has("provinceId")) {
                    provinceId = conditions.getInt("provinceId");
                } else {
                    provinceId = -1;
                }
                if (conditions.has("whereaboutgoId")) {
                    whereaboutgoId = conditions.getInt("whereaboutgoId");
                } else {
                    whereaboutgoId = -1;
                }
                if (conditions.has("trainingModeCode")) {
                    trainingModeCode = conditions.getInt("trainingModeCode");
                } else {
                    trainingModeCode = -1;
                }
                if (conditions.has("qualificationId")) {
                    qualificationId = conditions.getString("qualificationId");
                } else {
                    qualificationId = null;
                }
                if (conditions.has("keyWord")) {
                    keyWord = conditions.getString("keyWord");
                } else {
                    keyWord = null;
                }
                if (conditions.has("showUncommitted")) {
                    showUncommitted = conditions.getInt("showUncommitted");
                } else {
                    showUncommitted = -1;
                }
                if (conditions.has("nation")) {
                    nation = conditions.getString("nation");
                } else {
                    nation = null;
                }
                if (conditions.has("sex")) {
                    sex = conditions.getInt("sex");
                }else {
                    sex = -1;
                }
                if (conditions.has("politicalStand")){
                    politicalStand = conditions.getInt("politicalStand");
                }else {
                    politicalStand = -1;
                }
                if (conditions.has("reportMode")){
                    reportMode = conditions.getInt("reportMode");
                }else {
                    reportMode = -1;
                }
            }
            DispatchQueryDto conditionDto = new DispatchQueryDto();
            conditionDto.setpCurrent(curPage);
            conditionDto.setpSize(pageSize);
            conditionDto.setStuType(stuType);
            conditionDto.setDepsOrMajors(depsOrMajors);
            conditionDto.setNormalStuId(normalStu);
            conditionDto.setQualificationId(qualificationId);
            conditionDto.setCounsellorCheckResult(counsellorCheckResult);
            conditionDto.setSchoolCheckResult(schoolCheckResult);
            conditionDto.setDeputySecretaryCheckResult(deputySecretaryCheckResult);
            conditionDto.setKeyWord(keyWord);
            conditionDto.setTrainingModeCode(trainingModeCode);
            conditionDto.setProvinceId(provinceId);
            conditionDto.setWhereaboutgoId(whereaboutgoId);
            conditionDto.setShowUncommitted(showUncommitted);
            conditionDto.setNation(nation);
            conditionDto.setSex(sex);
            conditionDto.setPoliticalStand(politicalStand);
            conditionDto.setReportModeId(reportMode);
            User user = userService.queryByUsername(loginName);
            Integer userId = user.getId();
            int count = dispatchInfoService.getAdminDispatchersCount(userId, conditionDto);
            //如果当前id在list中查不到，说明在前一页，则下一个id取第一个即可
            if (curNo != -1) {
                curNo += 1;
            } else {
//                    curPage -= 1;
//                    Pagination page = new Pagination(count, pageSize, curPage);
//                    idList = dispatchInfoService.listIdByCondition(userId, conditionDto, page);
                curNo = 0;
            }
            if (curNo < idList.size()) {
                stuId = idList.get(curNo);
                curNo += 1;
            } else if (idList.size() < pageSize) {
                //判断是否最后一页，如果是最后一页则令id = 0
                stuId = 0;
            } else {
                //判断当页有15条数据的情况下是否最后一页，如果是最后一页则令id = 0，如果不是则重置curNo，curPage+1
                int totalPage = count / pageSize + 1;
                if (curPage < totalPage) {
                    curPage += 1;
                    Pagination page = new Pagination(count, pageSize, curPage);
                    idList = dispatchInfoService.listIdByCondition(userId, conditionDto, page);
                    stuId = idList.get(0);
                    curNo = 0;
                } else {
                    stuId = 0;
                }
            }

            statusInfoCache.put("id", stuId);
            statusInfoCache.put("idList", idList);
            statusInfoCache.put("curNo", curNo);
            statusInfoCache.put("curPage", curPage);

            cacheUtil.put(loginName, statusInfoCache);

            DispatchExtendItem dispatchExtendItem = dispatchExtendItemService.queryByDispatchId(dispatchQueryDto.getId());
            List<DispatchAdminRemark> dispatchAdminRemarkList = dispatchAdminRemarkService.queryByDispatchId(dispatchQueryDto.getId());


            model.addAttribute("statusInfoCache", statusInfoCache);
            model.addAttribute("mt", materialList);
            model.addAttribute("dh", dispatchQueryDto);//详情
            model.addAttribute("minority", 0);
            model.addAttribute("stuInfo", stuStatusInfoDto);
            model.addAttribute("canCheck", canCheck);
            model.addAttribute("dispatchUpdateDtoList",dispatchUpdateDtoList);
            //下sjz
            model.addAttribute("stuId",studentId);
            model.addAttribute("dispatchExtendItem",dispatchExtendItem);
            model.addAttribute("dispatchAdminRemarkList",dispatchAdminRemarkList);
            model.addAttribute("stuRemark",dispatchInfo.getStuRemark());
            return "admin/dispatch/detail";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return WebConstants.sysErrorCode;
        }

    }

    //跳转到管理员派遣信息列表
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String dispatchList(Model model) throws SSException {
        ModelAndView modelAndView = new ModelAndView();
        List<WhereAboutGoCode> whereAboutGoCodes = whereAboutGoService.listAll();
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Subject subject = SecurityUtils.getSubject();

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

        User user = userService.queryByUsername(username);
        Integer userId = user.getId();
        model.addAttribute("wc", whereAboutGoCodes);
        model.addAttribute("dm", dispatchInfoService.getDepartmentsOrMajors(userId));
        model.addAttribute("provinces", provinceService.listAll());
        return "admin/dispatch/list";
    }

    //跳转到管理员派遣信息列表
    @RequestMapping(value = "check/{id}")
    public String dispatchCheck(@PathVariable(value = "id") Integer id, Model model) throws SSException {
        DispatchQueryDto dispatchQueryDto = dispatchInfoService.getDetail(id);
        Integer stuId = dispatchQueryDto.getStatusInfoId();
        List<WhereAboutGoCode> whereAboutGoCodes = whereAboutGoService.listAll();
        for (int i = 0; i < whereAboutGoCodes.size(); i++) {
            WhereAboutGoCode whereAboutGoCode = whereAboutGoCodes.get(i);
            if (whereAboutGoCode.getWhereAboutGoId() == dispatchQueryDto.getWhereaboutgoId()) {
                model.addAttribute("whereaboutgo", whereAboutGoCode.getWhereAboutGo());
                break;
            }
        }
        List<CompanyProperty> companyProperties = companyPropertyService.listAll();
        for (int i = 0; i < companyProperties.size(); i++) {
            CompanyProperty companyProperty = companyProperties.get(i);
            if (companyProperty.getPropertyCode() == dispatchQueryDto.getPropertyId()) {
                model.addAttribute("property", companyProperty.getPropertyName());
                break;
            }
        }
        List<CompanyTrade> companyTrades = companyTradeService.listAll();
        for (int i = 0; i < companyTrades.size(); i++) {
            CompanyTrade companyTrade = companyTrades.get(i);
            if (companyTrade.getTradeId() == dispatchQueryDto.getTradeId()) {
                model.addAttribute("companyTrade", companyTrade.getTradeName());
                break;
            }
        }
        List<Material> materials = materialService.listByStatusInfoIdAndType(stuId, 2);
        model.addAttribute("mt", materials);
        List<JobCode> jobCodes = jobCodeService.listAll();
        for (int i = 0; i < jobCodes.size(); i++) {
            JobCode jobCode = jobCodes.get(i);
            if (jobCode.getJobId() == dispatchQueryDto.getJobId()) {
                model.addAttribute("job", jobCode.getJob());
                break;
            }
        }
        List<ReportCode> reportCodes = reportCodeService.listAll();
        for (int i = 0; i < reportCodes.size(); i++) {
            ReportCode reportCode = reportCodes.get(i);
            if (reportCode.getReportModeId() == dispatchQueryDto.getReportModeId()) {
                model.addAttribute("reportCode", reportCode.getReportMode());
                break;
            }
        }
        model.addAttribute("dh", dispatchQueryDto);
        return "admin/dispatch/checkDetail";
    }

    /**
     * 分页获取派遣列表数据
     *
     * @param currentPage
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "ajax/query/{currentPage}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryList(@PathVariable Integer currentPage,
                                @RequestParam(value = "stuType", required = false, defaultValue = "-1") int stuType,
                                @RequestParam(value = "college", required = false, defaultValue = "-1") int depsOrMajors,
                                @RequestParam(value = "majorCode" , required = false, defaultValue = "-1") int majorCode,
                                @RequestParam(value = "whereaboutgoId", required = false, defaultValue = "-1") int whereaboutgoId,
                                @RequestParam(value = "normalStuId", required = false, defaultValue = "-1") int normalStuId,
                                @RequestParam(value = "qualificationId", required = false) String qualificationId,
                                @RequestParam(value = "counsellorCheckResult", required = false, defaultValue = "-1") int counsellorCheckResult,
                                @RequestParam(value = "deputySecretaryCheckResult", required = false, defaultValue = "-1") int deputySecretaryCheckResult,
                                @RequestParam(value = "schoolCheckResult", required = false, defaultValue = "-1") int schoolCheckResult,
                                @RequestParam(value = "provinceId", required = false, defaultValue = "-1") int provinceId,
                                @RequestParam(value = "provinceInId",required = false,defaultValue = "-1") int provinceInId,//就业所在省
                                @RequestParam(value = "trainingModeCode", required = false, defaultValue = "-1") Integer trainingModeCode,
                                @RequestParam(value = "keyWord", required = false) String keyword,
                                @RequestParam(value = "qualificationNow", required = false, defaultValue = "-1") int qualificationNow,
                                @RequestParam(value = "nation", required = false) String nation,
                                @RequestParam(value = "sex", required = false, defaultValue = "-1") int sex,
                                @RequestParam(value = "politicalStand", required = false, defaultValue = "-1") int politicalStand,
                                @RequestParam(value = "reportModeId", required = false, defaultValue = "-1") int reportMode,
                                @RequestParam(value = "showUncommitted", required = false, defaultValue = "-1") int showUncommitted) throws SSException {

        DispatchQueryDto dispatchQueryDto = new DispatchQueryDto();
        dispatchQueryDto.setStuType(stuType);
        dispatchQueryDto.setDepsOrMajors(depsOrMajors);
        dispatchQueryDto.setNormalStuId(normalStuId);
        dispatchQueryDto.setCounsellorCheckResult(counsellorCheckResult);
        dispatchQueryDto.setDeputySecretaryCheckResult(deputySecretaryCheckResult);
        dispatchQueryDto.setSchoolCheckResult(schoolCheckResult);
        dispatchQueryDto.setProvinceId(provinceId);
        dispatchQueryDto.setQualificationId(qualificationId);
        dispatchQueryDto.setTrainingModeCode(trainingModeCode);
        dispatchQueryDto.setKeyWord(keyword);
        dispatchQueryDto.setWhereaboutgoId(whereaboutgoId);
        dispatchQueryDto.setQualificationNow(qualificationNow);
        dispatchQueryDto.setNation(nation);
        dispatchQueryDto.setSex(sex);
        dispatchQueryDto.setPoliticalStand(politicalStand);
        dispatchQueryDto.setReportModeId(reportMode);
        dispatchQueryDto.setProvinceInId(provinceInId);
        dispatchQueryDto.setShowUncommitted(showUncommitted);
        dispatchQueryDto.setMajorId(majorCode);//sjz 添加
        Integer dataCount = 0;
        JSONArray jsonArray = new JSONArray();
        currentPage = (currentPage == null ? 1 : currentPage);
        // 获取User ID
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();
        User user = userService.queryByUsername(username);

        Integer userId = user.getId();
        dispatchQueryDto.setUserId(userId);

        dataCount = dispatchInfoService.getCount(dispatchQueryDto);
        Pagination page = new Pagination(dataCount, pageSize, currentPage);
        dispatchQueryDto.setpCurrent(page.getCurrentFristPosition());
        dispatchQueryDto.setpSize(page.getPageSize());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<DispatchQueryDto> dispatchQueryDtos = dispatchInfoService.queryDispatchList(dispatchQueryDto);

        int index = (currentPage - 1) * pageSize + 1;
        for (DispatchQueryDto tmp : dispatchQueryDtos) {
            DispatchExtendItem dispatchExtendItem = dispatchExtendItemService.queryDetailByDispatchId(tmp.getDispatchId());
            StuStatusInfoDto stuStatusInfoDto = stuStatusInfoService.queryDtoById(tmp.getStatusInfoId());
            List<DispatchAdminRemark> adminRemarkList = dispatchAdminRemarkService.queryByDispatchId(tmp.getDispatchId());
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("index",index++);
            jsonObj.put("id", tmp.getId());
            if(tmp.getSex() == 1){
                jsonObj.put("sex","男");
            } else if(tmp.getSex() == 2){
                jsonObj.put("sex","女");
            }
            jsonObj.put("name",tmp.getName());
            jsonObj.put("grade",stuStatusInfoDto.getGrade());
            jsonObj.put("qualification",stuStatusInfoDto.getQualification());
            jsonObj.put("nation",stuStatusInfoDto.getNation());
            jsonObj.put("school",stuStatusInfoDto.getSchool());
            jsonObj.put("stuLength", stuStatusInfoDto.getStuLength());
            jsonObj.put("studentName", tmp.getName());
            jsonObj.put("studentNum", tmp.getStudentNumber());
            jsonObj.put("idNumber", tmp.getIdNumber());//身份证号
            jsonObj.put("canditateNum",tmp.getCandidateNumber());//考生号
            jsonObj.put("studentCollege", stuStatusInfoDto.getCollege());//学院
            jsonObj.put("studentMajor", stuStatusInfoDto.getMajor());//专业
            jsonObj.put("companyName", tmp.getCompanyName());
            jsonObj.put("fullAddress", tmp.getCityName());//就业所在地
            jsonObj.put("dispatchUnitName", tmp.getCompanyName());//签约单位名称
            jsonObj.put("dispatchUnitAddress", tmp.getReportAddressName());//报到证迁往单位所在地
            jsonObj.put("stuStatusInfoId", tmp.getStatusInfoId());
            jsonObj.put("counsellorCheckResult", CheckResultEnums.valueOf(tmp.getCounsellorCheckResult()).getName());
            jsonObj.put("deputySecretaryCheckResult", CheckResultEnums.valueOf(tmp.getDeputySecretaryCheckResult()).getName());
            jsonObj.put("schoolCheckResult", CheckResultEnums.valueOf(tmp.getSchoolCheckResult()).getName());
            jsonObj.put("whereAboutToGo", tmp.getWhereaboutgo());//毕业去向
            jsonObj.put("reportCompany",tmp.getReportCompany());
            jsonObj.put("reportCompanyAddress",tmp.getReportCompanyAddress());
            jsonObj.put("reportMode",tmp.getReportMode());//报到证签发类别
            jsonObj.put("schoolCheckReason",tmp.getSchoolCheckReason());
            jsonObj.put("stuLength",tmp.getStuLength());
//            jsonObj.put("orientationCancelContract",dispatchExtendItem.getOrientationCancelContract());
//            jsonObj.put("freeNormalCancelContract",dispatchExtendItem.getFreeNormalCancelContract());
//            jsonObj.put("freeNormalTransProvincial",dispatchExtendItem.getFreeNormalTransProvincial());
//            jsonObj.put("enterBeijing",dispatchExtendItem.getEnterBeijing());
//            jsonObj.put("enterTianjin",dispatchExtendItem.getEnterTianjin());
//            jsonObj.put("enterShanghai",dispatchExtendItem.getEnterShanghai());
            if(Assert.isNull(dispatchExtendItem)){
                jsonObj.put("orientationCancelContract","");
                jsonObj.put("freeNormalCancelContract","");
                jsonObj.put("freeNormalTransProvincial","");
                jsonObj.put("enterBeijing","");
                jsonObj.put("enterTianjin","");
                jsonObj.put("enterShanghai","");
            } else{
                jsonObj.put("orientationCancelContract",dispatchExtendItem.getOrientationCancelContract());
                jsonObj.put("freeNormalCancelContract",dispatchExtendItem.getFreeNormalCancelContract());
                jsonObj.put("freeNormalTransProvincial",dispatchExtendItem.getFreeNormalTransProvincial());
                jsonObj.put("enterBeijing",dispatchExtendItem.getEnterBeijing());
                jsonObj.put("enterTianjin",dispatchExtendItem.getEnterTianjin());
                jsonObj.put("enterShanghai",dispatchExtendItem.getEnterShanghai());
            }
            if(adminRemarkList.size() == 0){
                jsonObj.put("adminRemark","无");
            } else{
                String adminRemark = "";
                for(DispatchAdminRemark dispatchAdminRemark : adminRemarkList){
                    adminRemark = adminRemark + ";" +  dispatchAdminRemark.getRemark();
                }
                adminRemark = adminRemark.substring(1);
                jsonObj.put("adminRemark",adminRemark);
            }
            if (!Assert.isNull(tmp.getSchoolCheckTime())) {
                jsonObj.put("schoolCheckTime", sdf.format(tmp.getSchoolCheckTime()));
            } else {
                jsonObj.put("schoolCheckTime", "无");
            }
            if (!Assert.isNull(tmp.getStuRemark())) {
                jsonObj.put("stuRemark", tmp.getStuRemark());
            } else {
                jsonObj.put("stuRemark", "无");
            }
            if (!Assert.isNull(stuStatusInfoDto.getPoliticalStand())) {
                jsonObj.put("politicalStand", stuStatusInfoDto.getPoliticalStand());
            } else {
                jsonObj.put("politicalStand", "无");//政治面貌
            }
            if (!Assert.isNull(stuStatusInfoDto.getNormalStu())) {
                jsonObj.put("normalStu", stuStatusInfoDto.getNormalStu());
            } else {
                jsonObj.put("normalStu", "无");//师范生类别
            }
            if (!Assert.isNull(stuStatusInfoDto.getDifficulty())) {//困难生类别
                jsonObj.put("difficulty", stuStatusInfoDto.getDifficulty());
            } else {
                jsonObj.put("difficulty", "无");
            }
            if (!Assert.isNull(stuStatusInfoDto.getTrainingMode())) {//培养方式
                jsonObj.put("trainingMode", stuStatusInfoDto.getTrainingMode());
            } else {
                jsonObj.put("trainingMode", "无");
            }
            if (!Assert.isNull(stuStatusInfoDto.getWeipeiUnit())) {
                jsonObj.put("weipeiUnit", stuStatusInfoDto.getWeipeiUnit());//定向委培单位
            } else {
                jsonObj.put("weipeiUnit", "无");
            }
            if (!Assert.isNull(stuStatusInfoDto.getOriginPlace())) {//学生自己填写的生源地
                jsonObj.put("originPlace", stuStatusInfoDto.getOriginPlace());
            } else {
                jsonObj.put("originPlace", "无");
            }
            if (!stuStatusInfoDto.getProtocolType().isEmpty()) {
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
                List<Integer> protocolList = stuStatusInfoDto.getProtocolType();
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
                jsonObj.put("protocolType", protocolTypeStr);
            } else {
                System.out.println("空");
                jsonObj.put("protocolType", "无");
            }
            jsonArray.add(jsonObj);
        }
        return sendJsonArray(jsonArray, dataCount);
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
        // System.out.println("缓存success");
        Subject subject = SecurityUtils.getSubject();
        String loginName = subject.getPrincipal().toString();
        cacheUtil.remove(loginName);
        if (Assert.isNull(curPage)) {
            curPage = 1;
        }

        JSONObject conditions = JSONObject.fromObject(conditions1);

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
     * ajax上传材料
     *
     * @param file
     * @param id
     * @return
     */
    @RequestMapping(value = "ajax/upload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(@RequestParam("file") PandaworkMultipartFile file, @RequestParam(value = "id", required = true) int id) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            int statusInfoId = dispatchInfoService.getStatusInfoIdById(id);
            Material material = new Material();
            material.setStatusInfoId(statusInfoId);
            material.setMaterialName(file.getOriginalFilename());
            material.setMaterialType(MaterialTypeEnums.Dispatch.getId());
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
     * ajax修改审核结果
     *
     * @param level
     * @param checkResult
     * @param checkReason
     * @param otherReason
     * @return
     */
    @RequestMapping(value = "ajax/check", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject doCheck(@RequestParam("stuId") int stuId,
                              @RequestParam("checkLevel") String level,
                              @RequestParam("checkResult") int checkResult,
                              @RequestParam("checkReason") int checkReason,
                              @RequestParam(value = "otherReason", required = false) String otherReason) throws Exception {
        Integer id = dispatchInfoService.getIdByStuId(stuId);
        DispatchInfo dispatchInfo = new DispatchInfo();
        dispatchInfo.setId(id);
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
            DispatchQueryDto oldInfo = dispatchInfoService.getDetail(id);
            switch (level) {
                case "counsellor":
                    if (!subject.hasRole("admin") && !subject.hasRole("director") && !subject.hasRole("counsellor")) {
                        return sendErrMsgAndErrCode("没有权限进行该操作!");
                    }
                    dispatchInfo.setCounsellorCheckResult(checkResult);
                    dispatchInfo.setCounsellorCheckReason(reason);
                    dispatchInfo.setCounsellorCheckTime(date);
                    dispatchInfo.setCounsellorCheckOperator(realName);
                    //如果辅导员审核通过
                    if (checkResult == 2) {
                        //审核状态置为辅导员审核通过
                        dispatchInfo.setCheckStatus(CheckStatusEnums.CounsellorChecked.getId());
                        //如果辅导员审核未通过待修改
                    } else if (checkResult == 3) {
                        dispatchInfo.setCheckStatus(CheckStatusEnums.CounsellorNotThrough.getId());
                    } else if (checkResult == 4) {
                        dispatchInfo.setCheckStatus(CheckStatusEnums.CounsellorModified.getId());
                    }
                    break;
                case "deputySecretary":
                    if (!subject.hasRole("admin") && !subject.hasRole("director") && !subject.hasRole("college-secretary")) {
                        return sendErrMsgAndErrCode("没有权限进行该操作!");
                    }
                    dispatchInfo.setDeputySecretaryCheckResult(checkResult);
                    dispatchInfo.setDeputySecretaryCheckReason(reason);
                    dispatchInfo.setDeputySecretaryCheckTime(date);
                    dispatchInfo.setDeputySecretaryCheckOperator(realName);
                    //如果副书记审核通过
                    if (checkResult == 2) {
                        //审核状态置为副书记审核通过
                        dispatchInfo.setCheckStatus(CheckStatusEnums.DeputySecretaryChecked.getId());
                        //如果副书记审核未通过待修改
                    } else if (checkResult == 3) {
                        dispatchInfo.setCheckStatus(CheckStatusEnums.DeputySecretaryNotThrough.getId());
                        dispatchInfo.setCounsellorCheckResult(checkResult);
                        dispatchInfo.setCounsellorCheckReason(reason);
                        dispatchInfo.setCounsellorCheckTime(date);
                    } else if (checkResult == 4) {
                        dispatchInfo.setCheckStatus(CheckStatusEnums.DeputySecretaryModified.getId());
                    }
                    break;
                case "school":
                    if (!subject.hasRole("admin") && !subject.hasRole("director") && !subject.hasRole("employ")) {
                        return sendErrMsgAndErrCode("没有权限进行该操作!");
                    }
                    dispatchInfo.setCheckStatus(0);
                    dispatchInfo.setCounsellorCheckResult(1);
                    dispatchInfo.setCounsellorCheckReason("");
                    dispatchInfo.setCounsellorCheckTime(null);
                    dispatchInfo.setCounsellorCheckOperator("");
                    dispatchInfo.setDeputySecretaryCheckResult(1);
                    dispatchInfo.setDeputySecretaryCheckReason("");
                    dispatchInfo.setDeputySecretaryCheckTime(null);
                    dispatchInfo.setDeputySecretaryCheckOperator("");
                    dispatchInfo.setSchoolCheckResult(1);
                    dispatchInfo.setSchoolCheckReason("");
                    dispatchInfo.setSchoolCheckTime(null);
                    dispatchInfo.setSchoolCheckOperator("");
                    //如果学校审核通过
//                    if (checkResult == 2) {
//                        //如果辅导员或副书记未审核，说明是学校直接越级审核
//                        if (oldInfo.getCounsellorCheckResult() == 1 || oldInfo.getDeputySecretaryCheckResult() == 1) {
//                            dispatchInfo.setCheckStatus(CheckStatusEnums.SchoolDirectChecked.getId());
//                        } else {
//                            dispatchInfo.setCheckStatus(CheckStatusEnums.SchoolChecked.getId());
//                        }
//                        //如果学校审核未通过待修改
//                    } else if (checkResult == 3) {
//                        if (oldInfo.getCounsellorCheckResult() == 1 || oldInfo.getDeputySecretaryCheckResult() == 1) {
//                            dispatchInfo.setCheckStatus(CheckStatusEnums.SchoolDirectNotThrough.getId());
//                        } else {
//                            dispatchInfo.setCheckStatus(CheckStatusEnums.SchoolNotThrough.getId());
//                        }
//                    } else if (checkResult == 4) {
//                        if (oldInfo.getCounsellorCheckResult() == 1 || oldInfo.getDeputySecretaryCheckResult() == 1) {
//                            dispatchInfo.setCheckStatus(CheckStatusEnums.SchoolDirectModified.getId());
//                        } else {
//                            dispatchInfo.setCheckStatus(CheckStatusEnums.SchoolModified.getId());
//                        }
//                    }
                    break;
            }
            dispatchInfoService.updateById(dispatchInfo);
            return sendJsonObject(AJAX_SUCCESS_CODE);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 从学籍信息跳转到对应的派遣信息
     *
     * @param stuId
     * @param model
     * @return
     */
    @RequestMapping(value = "detail/{stuId}", method = RequestMethod.GET)
    public String toInfo(@PathVariable("stuId") int stuId, Model model) {
        //手动清理缓存
        Subject subject = SecurityUtils.getSubject();
        String loginName = subject.getPrincipal().toString();
        cacheUtil.remove(loginName);
        System.out.println("缓存清理成功");
        try {
            Integer curId = dispatchInfoService.getIdByStuId(stuId);
            List<Material> materialList = materialService.listByStatusInfoIdAndType(stuId, MaterialTypeEnums.StatusInfo.getId());
            JSONObject statusInfoCache = new JSONObject();
            DispatchQueryDto dispatchQueryDto = dispatchInfoService.getDetail(curId);
            StuStatusInfoDto stuStatusInfo = stuStatusInfoService.queryDtoById(stuId);
            model.addAttribute("materialList", materialList);
            model.addAttribute("dh", dispatchQueryDto);//详情

            statusInfoCache.put("id", 0);
            model.addAttribute("statusInfoCache", statusInfoCache);
            model.addAttribute("stuInfo", stuStatusInfo);
            return "admin/dispatch/detail";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 派遣列表查看审核状态
     *
     * @param dispatchId
     * @return
     */
    @RequestMapping(value = "ajax/check-status", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getCheckStatus(@RequestParam("dispatchId") int dispatchId) {
        try {
            DispatchQueryDto dispatchQueryDto = dispatchInfoService.getDetail(dispatchId);
            JSONObject jsonObject = new JSONObject();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String counsellorCheckResultStr = null;
            if (dispatchQueryDto.getCounsellorCheckResult() == CheckResultEnums.Unchecked.getId()) {
                counsellorCheckResultStr = CheckResultEnums.Unchecked.getName();
            } else if (dispatchQueryDto.getCounsellorCheckResult() == CheckResultEnums.Checked.getId()) {
                counsellorCheckResultStr = CheckResultEnums.Checked.getName();
            } else if (dispatchQueryDto.getCounsellorCheckResult() == CheckResultEnums.NotThrough.getId()) {
                counsellorCheckResultStr = CheckResultEnums.NotThrough.getName();
            } else if (dispatchQueryDto.getCounsellorCheckResult() == CheckResultEnums.Modified.getId()) {
                counsellorCheckResultStr = CheckResultEnums.Modified.getName();
            } else {
                counsellorCheckResultStr = CheckResultEnums.Unchecked.getName();
            }
            jsonObject.put("counsellorCheckResult", counsellorCheckResultStr);
            jsonObject.put("counsellorCheckOperator", dispatchQueryDto.getCounsellorCheckOperator());
            String counsellorCheckTime = null;
            try {
                counsellorCheckTime = sdf.format(dispatchQueryDto.getCounsellorCheckTime());
            } catch (Exception e) {
                counsellorCheckTime = "";
            }

            jsonObject.put("counsellorCheckTime", counsellorCheckTime);
            String deputySecretaryCheckResultStr = null;
            if (dispatchQueryDto.getDeputySecretaryCheckResult() == CheckResultEnums.Unchecked.getId()) {
                deputySecretaryCheckResultStr = CheckResultEnums.Unchecked.getName();
            } else if (dispatchQueryDto.getDeputySecretaryCheckResult() == CheckResultEnums.Checked.getId()) {
                deputySecretaryCheckResultStr = CheckResultEnums.Checked.getName();
            } else if (dispatchQueryDto.getDeputySecretaryCheckResult() == CheckResultEnums.NotThrough.getId()) {
                deputySecretaryCheckResultStr = CheckResultEnums.NotThrough.getName();
            } else if (dispatchQueryDto.getDeputySecretaryCheckResult() == CheckResultEnums.Modified.getId()) {
                deputySecretaryCheckResultStr = CheckResultEnums.Modified.getName();
            } else {
                deputySecretaryCheckResultStr = CheckResultEnums.Unchecked.getName();
            }
            jsonObject.put("deputySecretaryCheckResult", deputySecretaryCheckResultStr);
            jsonObject.put("deputySecretaryCheckOperator", dispatchQueryDto.getDeputySecretaryCheckOperator());
            String deputySecretaryCheckTime = null;
            try {
                deputySecretaryCheckTime = sdf.format(dispatchQueryDto.getDeputySecretaryCheckTime());
            } catch (Exception e) {
                deputySecretaryCheckTime = "";
            }
            jsonObject.put("deputySecretaryCheckTime", deputySecretaryCheckTime);
            String schoolCheckResultStr = null;
            if (dispatchQueryDto.getSchoolCheckResult() == CheckResultEnums.Unchecked.getId()) {
                schoolCheckResultStr = CheckResultEnums.Unchecked.getName();
            } else if (dispatchQueryDto.getSchoolCheckResult() == CheckResultEnums.Checked.getId()) {
                schoolCheckResultStr = CheckResultEnums.Checked.getName();
            } else if (dispatchQueryDto.getSchoolCheckResult() == CheckResultEnums.NotThrough.getId()) {
                schoolCheckResultStr = CheckResultEnums.NotThrough.getName();
            } else if (dispatchQueryDto.getSchoolCheckResult() == CheckResultEnums.Modified.getId()) {
                schoolCheckResultStr = CheckResultEnums.Modified.getName();
            } else {
                schoolCheckResultStr = CheckResultEnums.Unchecked.getName();
            }
            jsonObject.put("schoolCheckResult", schoolCheckResultStr);
            jsonObject.put("schoolCheckOperator", dispatchQueryDto.getSchoolCheckOperator());
            String schoolCheckTime = null;
            try {
                schoolCheckTime = sdf.format(dispatchQueryDto.getSchoolCheckTime());
            } catch (Exception e) {
                schoolCheckTime = "";
            }
            jsonObject.put("schoolCheckTime", schoolCheckTime);
            return sendJsonObject(jsonObject);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 去办公信息页
     *
     * @param statusId
     * @param model
     * @return
     */
    @RequestMapping(value = "comments/{statusId}", method = RequestMethod.GET)
    public String toComments(@PathVariable("statusId") int statusId, Model model) {
        try {
            Comments comments = commentsService.queryByStatusId(statusId);
            //如果数据库中没有，则新增一条
            if (Assert.isNull(comments) || Assert.isNull(comments.getId())) {
                Comments tmp = new Comments();
                tmp.setStatusInfoId(statusId);
                comments = commentsService.newComments(tmp);
            }

            //报到证
            ReportCard reportCard = reportCardService.queryByStatusId(statusId);
            if (Assert.isNotNull(reportCard) && Assert.isNotNull(reportCard.getId())) {
                model.addAttribute("isPrinted", "是");
            } else {
                model.addAttribute("isPrinted", "否");
            }

            Integer dispatchId = dispatchInfoService.getIdByStuId(statusId);
            if (Assert.isNotNull(dispatchId) && !Assert.lessOrEqualZero(dispatchId)) {
                model.addAttribute("dispatchId", dispatchId);
            } else {
                model.addAttribute("dispatchId", 0);
            }

            model.addAttribute("comments", comments);
            model.addAttribute("reportCard", reportCard);
            model.addAttribute("statusId", statusId);
            return "admin/dispatch/comments";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 更新办公信息
     *
     * @param comments
     * @return
     */
    @RequestMapping(value = "comments", method = RequestMethod.POST)
    public String updateComments(Comments comments, RedirectAttributes redirectAttributes) {
        int statusId = comments.getStatusInfoId();

        try {
            comments.setStatusInfoId(null);
            commentsService.updateById(comments);
            String successUrl = "/" + URLConstants.ADMIN_DISPATCH_URL + "/comments/" + statusId;
            redirectAttributes.addFlashAttribute("correctMsg", NEW_SUCCESS_MSG);
            return "redirect:" + successUrl;
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.ADMIN_DISPATCH_URL + "/comments/" + statusId;
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    /**
     * 对管理员备注进行操作
     * 单继重
     * @param dispatchId 派遣表ID
     * @param dispatchItemId 派遣备注扩展项ID
     * @param remarks 手填备注
     * @param remarkIds 手填备注ID
     * @param stuId 学籍表ID
     * @param dispatchExtendItem 派遣备注扩展项
     * @param redirectAttributes 转发属性
     * @return 详情页
     * @throws SSException SS异常
     */
    @RequestMapping(value = "remark" , method = RequestMethod.POST)
    public String updateRemark(@RequestParam(value = "dispatchId")Integer dispatchId,
                               @RequestParam(value = "dispatchItemId")Integer dispatchItemId,
                               @RequestParam(value = "remark")List<String> remarks,
                               @RequestParam(value = "remarkId")List<Integer>remarkIds,
                               @RequestParam(value = "stuId")Integer stuId,
                               DispatchExtendItem dispatchExtendItem,
                               RedirectAttributes redirectAttributes) throws SSException {
        try {
            List<DispatchAdminRemark> dispatchAdminRemarkList = new ArrayList<>();
            for (int i = 0; i < remarks.size(); i++) {
                DispatchAdminRemark dispatchAdminRemark = new DispatchAdminRemark();
//                if (remarks.get(i).equals("")) {
//                    continue;
//                } else if(remarks.get(i).equals("delete")){ //如果删除，前台将备注内容替换为delete
//                    dispatchAdminRemark.setId(remarkIds.get(i));
//                    dispatchAdminRemark.setRemark(remarks.get(i));
//                    dispatchAdminRemarkService.deleteById(dispatchAdminRemark);
//                    continue;
//                }
                String remark = remarks.get(i);
                dispatchAdminRemark.setRemark(remark);
//                if(i >= 10){
//                    if (i >= remarkIds.size() || remarkIds.get(i) == -2) {
//                        dispatchAdminRemark.setId(null);
//                    } else {
//                        Integer remarkId = remarkIds.get(i);
//                        dispatchAdminRemark.setId(remarkId);
//                    }
//                } else {
//                    dispatchAdminRemark.setId(null);
//                }

                //修改为固定十个备注 故原代码暂时弃用
                if (i >= remarkIds.size() || remarkIds.get(i) == -2) {
                    dispatchAdminRemark.setId(null);
                } else {
                    Integer remarkId = remarkIds.get(i);
                    dispatchAdminRemark.setId(remarkId);
                }

                dispatchAdminRemark.setDispatchId(dispatchId);
                dispatchAdminRemarkList.add(dispatchAdminRemark);
            }
            dispatchAdminRemarkService.newOrUpdateRemarks(dispatchAdminRemarkList);
            dispatchExtendItem.setId(dispatchItemId);
            dispatchExtendItemService.newOrUpdate(dispatchExtendItem);
            String successUrl = "/" + URLConstants.ADMIN_DISPATCH_URL + "/dtl/" + stuId;
            redirectAttributes.addFlashAttribute("msg", UPDATE_SUCCESS_MSG);
            return "redirect:" + successUrl;
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.ADMIN_DISPATCH_URL + "/dtl/" + stuId;
            redirectAttributes.addFlashAttribute("msg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    /**
     * 旧协议假删除（待完成）
     * @param dispatchInfoId 派遣表ID
     * @param page 当前页
     * @return 地址
     */
    @RequestMapping(value = "ajax/delete")
    @ResponseBody
    public String delByDisId(@RequestParam("id")int dispatchInfoId,
                                @RequestParam("page")int page){
        try {
            dispatchInfoService.deleteById(dispatchInfoId);
            return "/" + URLConstants.ADMIN_DISPATCH_URL + "/list/" + page;
        } catch (SSException e) {
            e.printStackTrace();
        }
        return null;
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
            if (dispatchInfoService.batchAudit(ids, date, realName)) {
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
     * @param ids 前台JS数组
     * @return JSON对象
     */
    @RequestMapping(value = "ajax/repulse", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject batchRepulse (@RequestParam("id[]") List<Integer> ids) {
        try {
            for(int i=0; i<ids.size(); i++){
                DispatchInfo dispatchInfo = new DispatchInfo();
                int id;
                id = dispatchInfoService.getIdByStuId(ids.get(i));
                dispatchInfo.setId(id);
                dispatchInfo.setCounsellorCheckResult(1);
                dispatchInfo.setCounsellorCheckReason("");
                dispatchInfo.setCounsellorCheckTime(null);
                dispatchInfo.setCounsellorCheckOperator("");
                dispatchInfo.setDeputySecretaryCheckResult(1);
                dispatchInfo.setDeputySecretaryCheckReason("");
                dispatchInfo.setDeputySecretaryCheckTime(null);
                dispatchInfo.setDeputySecretaryCheckOperator("");
                dispatchInfo.setSchoolCheckResult(1);
                dispatchInfo.setSchoolCheckReason("");
                dispatchInfo.setSchoolCheckTime(null);
                dispatchInfo.setSchoolCheckOperator("");
                dispatchInfo.setCheckStatus(0);
                dispatchInfoService.updateDispatchInfo(dispatchInfo);
            }
            return sendJsonObject(AJAX_SUCCESS_CODE);
        }catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode("操作失败！");
        }
    }
}