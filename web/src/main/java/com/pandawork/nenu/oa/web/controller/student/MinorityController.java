package com.pandawork.nenu.oa.web.controller.student;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchListDto;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchQueryDto;
import com.pandawork.nenu.oa.common.dto.dispatch.MinorityQueryDto;
import com.pandawork.nenu.oa.common.dto.student.status.StatusInfoListDto;
import com.pandawork.nenu.oa.common.dto.student.status.StuStatusInfoDto;
import com.pandawork.nenu.oa.common.dto.student.status.UpdateInfoDto;
import com.pandawork.nenu.oa.common.entity.data.CollegeCode;
import com.pandawork.nenu.oa.common.entity.data.NationCode;
import com.pandawork.nenu.oa.common.entity.data.QualificationCode;
import com.pandawork.nenu.oa.common.entity.data.TrainingModeCode;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.entity.general.ProvinceProperty;
import com.pandawork.nenu.oa.common.entity.user.User;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MinorityController
 * 少数民族信息Controller
 *
 * @author wlm
 * @date 2016/11/19 15:31
 */

@Controller
@RequestMapping(value = URLConstants.ADMIN_MINORITY_URL)
public class MinorityController extends AbstractController {

    private CacheUtil cacheUtil = new CacheUtil();

    /**
     * 进入少数民族学籍信息列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "status/info/list", method = RequestMethod.GET)
    public String toStatusInfoList(Model model) {
        try {
            List<NationCode> nationList = nationService.listAll();
            NationCode han = new NationCode();
            han.setNationId("01");
            nationList.remove(han);
            List<ProvinceProperty> provinceList = provinceService.listAll();
            List<CollegeCode> collegeList = collegeService.listAll();

            model.addAttribute("collegeList", collegeList);
            model.addAttribute("nationList", nationList);
            model.addAttribute("provinceList", provinceList);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
        return "admin/minority/status_info_list";
    }

    /**
     * 分页获取少数民族学籍信息
     *
     * @param page
     * @param nation
     * @param college
     * @param province
     * @param qualificationNow
     * @param showUncommitted
     * @return
     */
    @RequestMapping(value = "status/info/ajax/list/{page}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getStatusInfoList(@PathVariable("page") int page,
                                        @RequestParam(value = "nation", required = false) String nation,
                                        @RequestParam(value = "college", required = false, defaultValue = "-1") int college,
                                        @RequestParam(value = "originPlace", required = false, defaultValue = "-1") int province,
                                        @RequestParam(value = "qualificationNow", required = false, defaultValue = "-1") int qualificationNow,
                                        @RequestParam(value = "showUncommitted", required = false, defaultValue = "-1") int showUncommitted) {
        JSONArray jsonArray = new JSONArray();

        try {
            Subject subject = SecurityUtils.getSubject();
            String username = subject.getPrincipal().toString();
            List<String> colleges = null;
            List<String> majors = null;
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
                majors = userInfoService.listMajorsByUserName(username);
                if (Assert.isEmpty(majors) || majors.size() <= 0) {
                    majors.add("0");
                }
            }
            if (!Assert.isNull(nation) && nation.equals("-1")) {
                nation = null;
            }
            int count = stuStatusInfoService.countMinorityByCondition(college, province, colleges, majors, qualificationNow, nation,
                    showUncommitted);
            Pagination pagination = new Pagination(count, pageSize, page);
            List<StatusInfoListDto> statusInfoList = stuStatusInfoService.listMinorityByCondition(college, province, pagination, colleges,
                    majors, qualificationNow, nation, showUncommitted);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (StatusInfoListDto statusInfoListDto : statusInfoList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", statusInfoListDto.getId());
                jsonObject.put("name", statusInfoListDto.getName());
                jsonObject.put("stuNumber", statusInfoListDto.getStuNumber());
                jsonObject.put("grade", statusInfoListDto.getGrade());
                jsonObject.put("major", statusInfoListDto.getMajor());
                jsonObject.put("college", statusInfoListDto.getCollege());
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
                jsonArray.add(jsonObject);
            }
            return sendJsonArray(jsonArray, count);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 创建缓存
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
     * 去少数民族学籍信息详情页
     *
     * @param curId
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "status/info/detail/{id}", method = RequestMethod.GET)
    public String toStatusInfoCheck(@PathVariable("id") int curId, Model model, RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        String loginName = subject.getPrincipal().toString();
        JSONObject statusInfoCache = (JSONObject) cacheUtil.get(loginName);
        List<Material> materialList = Collections.emptyList();
        List<UpdateInfoDto> updateInfoList = Collections.emptyList();
        if (Assert.isNull(statusInfoCache)) {
            String failedUrl = "/" + URLConstants.ADMIN_MINORITY_URL + "status/info/list";
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
            List<String> majors = null;
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
                majors = userInfoService.listMajorsByUserName(loginName);
                if (Assert.isEmpty(majors) || majors.size() <= 0) {
                    majors.add("0");
                }
            }
            StuStatusInfoDto stuStatusInfo = stuStatusInfoService.queryDtoById(curId);
            materialList = materialService.listByStatusInfoIdAndType(curId, MaterialTypeEnums.StatusInfo.getId());
            updateInfoList = stuUpdateInfoService.listDtoByStuStatusInfoId(curId);
            List<Integer> idList = JSONArray.toList(statusInfoCache.getJSONArray("idList"), Integer.class);
            int id = curId;
            int curNo = idList.indexOf(curId);
            int curPage = statusInfoCache.getInt("curPage");
            JSONObject conditions = statusInfoCache.getJSONObject("conditions");
            int college, province, qualificationNow, showUncommitted;
            String nation;
//            System.out.println(conditions);
            //取出判断条件
            if (Assert.isNull(conditions) || conditions.size() == 0) {
                college = -1;
                province = -1;
                qualificationNow = -1;
                showUncommitted = -1;
                nation = null;
            } else {
                if (conditions.has("college")) {
                    college = conditions.getInt("college");
                } else {
                    college = -1;
                }
                if (conditions.has("province")) {
                    province = conditions.getInt("province");
                } else {
                    province = -1;
                }
                if (conditions.has("qualificationNow")) {
                    qualificationNow = conditions.getInt("qualificationNow");
                } else {
                    qualificationNow = -1;
                }
                if (conditions.has("showUncommitted")) {
                    showUncommitted = conditions.getInt("showUncommitted");
                } else {
                    showUncommitted = -1;
                }
                showUncommitted = conditions.getInt("showUncommitted");
                if (conditions.has("nation")) {
                    nation = conditions.getString("nation");
                } else {
                    nation = null;
                }
            }
            int count = stuStatusInfoService.countMinorityByCondition(college, province, colleges, majors, qualificationNow, nation, showUncommitted);
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
                    idList = stuStatusInfoService.listMinorityIdByCondition(college, province, colleges, majors, qualificationNow, nation, showUncommitted, page);
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
            model.addAttribute("minority", 1);
            return "admin/status/detail";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 少数民族派遣信息列表
     *
     * @param model
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "dispatch/list", method = RequestMethod.GET)
    public String dispatchList(Model model) throws SSException {
        try {
            List<CollegeCode> collegeList = collegeService.listAll();
            List<QualificationCode> qualificationList = qualificationService.listAll();
            List<TrainingModeCode> trainingModeList = trainingModeService.listAll();
            List<ProvinceProperty> provinceList = provinceService.listAll();

            model.addAttribute("collegeList", collegeList);
            model.addAttribute("qualificationList", qualificationList);
            model.addAttribute("trainingModeList", trainingModeList);
            model.addAttribute("provinceList", provinceList);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
        return "admin/minority/dispatch_list";
    }

    /**
     * ajax获取少数民族派遣信息列表
     *
     * @param page
     * @param nation
     * @param college
     * @param province
     * @param qualificationNow
     * @param showUncommitted
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "dispatch/ajax/list/{page}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryList(@PathVariable(value = "page") Integer page,
                                @RequestParam(value = "nation", required = false) String nation,
                                @RequestParam(value = "college", required = false, defaultValue = "-1") int college,
                                @RequestParam(value = "originPlace", required = false, defaultValue = "-1") int province,
                                @RequestParam(value = "qualificationNow", required = false, defaultValue = "-1") int qualificationNow,
                                @RequestParam(value = "showUncommitted", required = false, defaultValue = "-1") int showUncommitted) throws SSException {
        JSONArray jsonArray = new JSONArray();
        try {
            Subject subject = SecurityUtils.getSubject();
            String username = subject.getPrincipal().toString();

            MinorityQueryDto minorityQueryDto = new MinorityQueryDto();
            minorityQueryDto.setCollege(college);
            minorityQueryDto.setProvince(province);
            minorityQueryDto.setQualificationNow(qualificationNow);
            minorityQueryDto.setShowUncommitted(showUncommitted);

            List<String> colleges = null;
            List<String> majors = null;
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
                majors = userInfoService.listMajorsByUserName(username);
                if (Assert.isEmpty(majors) || majors.size() <= 0) {
                    majors.add("0");
                }
            }
            if (!Assert.isNull(nation) && nation.equals("-1")) {
                minorityQueryDto.setNation(null);
            }
            minorityQueryDto.setColleges(colleges);
            minorityQueryDto.setMajors(majors);
            int count = dispatchInfoService.countMinorityByCondition(minorityQueryDto);
            Pagination pagination = new Pagination(count, pageSize, page);
            List<DispatchListDto> dispatchInfoList = dispatchInfoService.listMinorityByCondition(minorityQueryDto, pagination);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (DispatchListDto dispatchInfoListDto : dispatchInfoList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", dispatchInfoListDto.getId());
                jsonObject.put("statusInfoId", dispatchInfoListDto.getStatusInfoId());
                jsonObject.put("name", dispatchInfoListDto.getName());
                jsonObject.put("stuNumber", dispatchInfoListDto.getStuNumber());
                jsonObject.put("companyName", dispatchInfoListDto.getCompanyName());
                jsonObject.put("fullAddress", dispatchInfoListDto.getFullAddress());
                jsonObject.put("counsellorCheckResult", dispatchInfoListDto.getCounsellorCheckResult());
                if (!Assert.isNull(dispatchInfoListDto.getCounsellorCheckTime())) {
                    jsonObject.put("counsellorCheckTime", sdf.format(dispatchInfoListDto.getCounsellorCheckTime()));
                } else {
                    jsonObject.put("counsellorCheckTime", "无");
                }
                jsonObject.put("deputySecretaryCheckResult", dispatchInfoListDto.getDeputySecretaryCheckResult());
                if (!Assert.isNull(dispatchInfoListDto.getDeputySecretaryCheckTime())) {
                    jsonObject.put("deputySecretaryCheckTime", sdf.format(dispatchInfoListDto.getDeputySecretaryCheckTime()));
                } else {
                    jsonObject.put("deputySecretaryCheckTime", "无");
                }
                jsonObject.put("schoolCheckResult", dispatchInfoListDto.getSchoolCheckResult());
                if (!Assert.isNull(dispatchInfoListDto.getSchoolCheckTime())) {
                    jsonObject.put("schoolCheckTime", sdf.format(dispatchInfoListDto.getSchoolCheckTime()));
                } else {
                    jsonObject.put("schoolCheckTime", "无");
                }
                jsonArray.add(jsonObject);
            }
            return sendJsonArray(jsonArray, count);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 去少数民族信息详情页
     *
     * @param stuId
     * @param model
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "dispatch/detail/{stuId}", method = RequestMethod.GET)
    public String toDispatchDetail(@PathVariable("stuId") int stuId, Model model, RedirectAttributes redirectAttributes) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String loginName = subject.getPrincipal().toString();
        JSONObject statusInfoCache = (JSONObject) cacheUtil.get(loginName);
        List<Material> materialList = Collections.emptyList();
        int curId = dispatchInfoService.getIdByStuId(stuId);
        if (Assert.isNull(statusInfoCache)) {
            String failedUrl = "/" + URLConstants.ADMIN_MINORITY_URL + "dispatch/list";
            redirectAttributes.addFlashAttribute("errMsg", "缓存出错");
            return "redirect:" + failedUrl;
        }
        try {
            List<String> colleges = null;
            List<String> majors = null;
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
                majors = userInfoService.listMajorsByUserName(loginName);
                if (Assert.isEmpty(majors) || majors.size() <= 0) {
                    majors.add("0");
                }
            }
            DispatchQueryDto dispatchQueryDto = dispatchInfoService.getDetail(curId);
            materialList = materialService.listByStatusInfoIdAndType(stuId, MaterialTypeEnums.Dispatch.getId());
            List<Integer> idList = JSONArray.toList(statusInfoCache.getJSONArray("idList"), Integer.class);
            int curNo = idList.indexOf(stuId);
            int curPage = statusInfoCache.getInt("curPage");
            JSONObject conditions = statusInfoCache.getJSONObject("conditions");
            int college, province, qualificationNow, showUncommitted;
            String nation;
//            System.out.println(conditions);
            //取出判断条件
            if (Assert.isNull(conditions) || conditions.size() == 0) {
                college = -1;
                province = -1;
                qualificationNow = -1;
                showUncommitted = -1;
                nation = null;
            } else {
                if (conditions.has("college")) {
                    college = conditions.getInt("college");
                } else {
                    college = -1;
                }
                if (conditions.has("province")) {
                    province = conditions.getInt("province");
                } else {
                    province = -1;
                }
                if (conditions.has("qualificationNow")) {
                    qualificationNow = conditions.getInt("qualificationNow");
                } else {
                    qualificationNow = -1;
                }
                if (conditions.has("showUncommitted")) {
                    showUncommitted = conditions.getInt("showUncommitted");
                } else {
                    showUncommitted = -1;
                }
                showUncommitted = conditions.getInt("showUncommitted");
                if (conditions.has("nation")) {
                    nation = conditions.getString("nation");
                } else {
                    nation = null;
                }
            }
            MinorityQueryDto minorityQueryDto = new MinorityQueryDto();
            minorityQueryDto.setCurPage(curPage);
            minorityQueryDto.setPageSize(pageSize);
            minorityQueryDto.setNation(nation);
            minorityQueryDto.setCollege(college);
            minorityQueryDto.setProvince(province);
            minorityQueryDto.setQualificationNow(qualificationNow);
            minorityQueryDto.setShowUncommitted(showUncommitted);
            minorityQueryDto.setColleges(colleges);
            minorityQueryDto.setMajors(majors);

            User user = userService.queryByUsername(loginName);
            int count = dispatchInfoService.countMinorityByCondition(minorityQueryDto);
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
                    idList = dispatchInfoService.listMinorityIdByCondition(minorityQueryDto, page);
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

            model.addAttribute("statusInfoCache", statusInfoCache);
            model.addAttribute("mt", materialList);
            model.addAttribute("dh", dispatchQueryDto);//详情
            model.addAttribute("minority", 1);
            return "admin/dispatch/detail";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return WebConstants.sysErrorCode;
        }

    }
}
