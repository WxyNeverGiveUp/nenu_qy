package com.pandawork.nenu.oa.web.controller.admin;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchQueryDto;
import com.pandawork.nenu.oa.common.dto.dispatch.ExportAllCounsellorDto;
import com.pandawork.nenu.oa.common.dto.dispatch.ExportAllDto;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchExportDto;
import com.pandawork.nenu.oa.common.dto.history.College;
import com.pandawork.nenu.oa.common.dto.statistics.EmploymentInfoExportDto;
import com.pandawork.nenu.oa.common.dto.statistics.EmploymentInfoParsedExportDto;
import com.pandawork.nenu.oa.common.dto.statistics.EmploymentStatisticsInfoDto;
import com.pandawork.nenu.oa.common.dto.student.status.StudentExportDto;
import com.pandawork.nenu.oa.common.dto.user.UserInfoDto;
import com.pandawork.nenu.oa.common.entity.user.User;
import com.pandawork.nenu.oa.common.util.ExcelUtil;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ExportController
 *
 * @author wlm
 * @date 2016/12/31 19:46
 */

@Controller
@RequestMapping(value = URLConstants.ADMIN_EXPORT_URL)
public class ExportController extends AbstractController {

    @RequestMapping(value = "student", method = RequestMethod.GET)
    public void exportStudentsInfo(@RequestParam(value = "grade", required = false, defaultValue = "-1") Integer grade,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            List<StudentExportDto> lists = stuStatusInfoService.listAllExportInfo(grade);

            String filename = "学生信息.xls";//设置下载时Excel的名称
            filename = ExcelUtil.encodeFilename(filename, request);//处理中文文件名
            ExcelUtil.writeExcel(lists, "recruit", filename, response);//调用Excel工具类生成Excel
        } catch (SSException e) {
            e.printStackTrace();
            LogClerk.errLog.error(e);
        }
    }

    @RequestMapping(value = "studentNew", method = RequestMethod.GET)
    public void exportStudentsInfoByCondition(@RequestParam(value = "stuType", required = false, defaultValue = "-1") int stuType,
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
                                                    @RequestParam(value = "majorCode", required = false, defaultValue = "") String major,
                                                    @RequestParam(value = "qualificationNow", required = false, defaultValue = "-1") int qualificationNow,
                                                    @RequestParam(value = "nation", required = false, defaultValue = "") String nation,
                                                    @RequestParam(value = "stuLength", required = false, defaultValue = "-1") Double stuLength,
                                                    //显示全部
                                                    @RequestParam(value = "showUncommitted", required = false, defaultValue = "1") int showUncommitted,
                                                    @RequestParam(value = "isRegistered", required = false, defaultValue = "-1") int isRegistered,
                                                    HttpServletRequest request, HttpServletResponse response) {
        try {
            List<UserInfoDto> userInfoDtoList = Collections.emptyList();
            String majorQualification = "";
            //major = major.trim();
            /*if (!Assert.isNull(major)) {
                MajorCode majorname = majorService.queryByName(major);
                major = majorname.getMajorId();
            }*/

            if (!Assert.isNull(qualification) && qualification.equals("-1")) {
                qualification = null;
            }
            Subject subject = SecurityUtils.getSubject();
            String username = subject.getPrincipal().toString();

            List<String> colleges = null;
            List<String> majors = new ArrayList<>();

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
                if (Assert.isNotNull(major)){
                    if (!major.equals("-2")){
                        majors.add(major);
                    }
                }
                // 获取辅导员的专业列表
                userInfoDtoList  = userInfoService.listMajorsAndQualificationByUserName(username);
                majorQualification = userInfoDtoList.get(0).getMajorQualification();
            }

            List<StudentExportDto> lists = stuStatusInfoService.listExportInfoByCondition(stuType, college, normalStu, counsellorCheckResult, deputySecretaryCheckResult,
                    schoolCheckResult, province, qualification, trainingMode, keyword, sex, politicalStand, colleges, major, majorQualification, majors, qualificationNow, nation, stuLength, showUncommitted, isRegistered);
            String filename = "学生信息.xls";//设置下载时Excel的名称
            filename = ExcelUtil.encodeFilename(filename, request);//处理中文文件名
            ExcelUtil.writeExcel(lists, "recruit", filename, response);//调用Excel工具类生成Excel
        } catch (SSException e) {
            e.printStackTrace();
            LogClerk.errLog.error(e);
        }
    }

    @RequestMapping(value = "dispatch", method = RequestMethod.GET)
    public void exportDispatchInfo(@RequestParam(value = "grade", required = false, defaultValue = "-1") Integer grade,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            List<DispatchExportDto> lists = dispatchInfoService.listAllExportInfo(grade);

            String filename = "派遣信息.xls";//设置下载时Excel的名称
            filename = ExcelUtil.encodeFilename(filename, request);//处理中文文件名
            ExcelUtil.writeExcel(lists, "recruit", filename, response);//调用Excel工具类生成Excel
        } catch (SSException e) {
            e.printStackTrace();
            LogClerk.errLog.error(e);
        }
    }

    /**
     * 单继重
     * 大导出
     * @param stuType 学生类型
     * @param majorCode 专业
     * @param whereaboutgoId 毕业去向
     * @param normalStuId 师范生类别
     * @param qualificationId 学历
     * @param counsellorCheckResult 辅导员审核结果
     * @param deputySecretaryCheckResult 副书记审核结果
     * @param provinceInId 就业所在省
     * @param keyword 关键字
     * @param sex 性别
     * @param politicalStand 政治面貌
     * @param reportMode 报到证签发类别
     * @param request 请求
     * @param response 相应
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public void exportAll(@RequestParam(value = "stuType", required = false, defaultValue = "-1") int stuType,
                                @RequestParam(value = "majorCode" , required = false, defaultValue = "-1") int majorCode,
                                @RequestParam(value = "whereaboutgoId", required = false, defaultValue = "-1") int whereaboutgoId,
                                @RequestParam(value = "normalStuId", required = false, defaultValue = "-1") int normalStuId,
                                @RequestParam(value = "qualificationId", required = false) String qualificationId,
                                @RequestParam(value = "counsellorCheckResult", required = false, defaultValue = "-1") int counsellorCheckResult,
                                @RequestParam(value = "deputySecretaryCheckResult", required = false, defaultValue = "-1") int deputySecretaryCheckResult,
                                @RequestParam(value = "provinceInId",required = false,defaultValue = "-1") int provinceInId,//就业所在省
                                @RequestParam(value = "keyWord", required = false) String keyword,
                                @RequestParam(value = "sex", required = false, defaultValue = "-1") int sex,
                                @RequestParam(value = "politicalStand", required = false, defaultValue = "-1") int politicalStand,
                                @RequestParam(value = "reportModeId", required = false, defaultValue = "-1") int reportMode,
                                HttpServletRequest request, HttpServletResponse response) {
        try{
            DispatchQueryDto dispatchQueryDto = new DispatchQueryDto();
            dispatchQueryDto.setStuType(stuType);
            dispatchQueryDto.setNormalStuId(normalStuId);
            dispatchQueryDto.setCounsellorCheckResult(counsellorCheckResult);
            dispatchQueryDto.setDeputySecretaryCheckResult(deputySecretaryCheckResult);
            dispatchQueryDto.setQualificationId(qualificationId);
            dispatchQueryDto.setKeyWord(keyword);
            dispatchQueryDto.setWhereaboutgoId(whereaboutgoId);
            dispatchQueryDto.setSex(sex);
            dispatchQueryDto.setPoliticalStand(politicalStand);
            dispatchQueryDto.setReportModeId(reportMode);
            dispatchQueryDto.setProvinceInId(provinceInId);
            dispatchQueryDto.setMajorId(majorCode);
            Subject subject = SecurityUtils.getSubject();
            String username = subject.getPrincipal().toString();
            User user = userService.queryByUsername(username);
            Integer userId = user.getId();
            dispatchQueryDto.setUserId(userId);
            List list = Collections.EMPTY_LIST;
            if(subject.hasRole("admin") || subject.hasRole("director") || subject.hasRole("employ")) {
                list = dispatchInfoService.exportAll(dispatchQueryDto);
                if(list.size() == 0 ){
                    list.add(new ExportAllDto());
                }
            } else if(subject.hasRole("counsellor") || subject.hasRole("college-secretary")){
                list = dispatchInfoService.exportAllCounsellor(dispatchQueryDto);
                if(list.size() == 0 ){
                    list.add(new ExportAllCounsellorDto());
                }
            }
            String filename = "所有信息.xls";
            filename = ExcelUtil.encodeFilename(filename, request);
            ExcelUtil.writeExcel(list, "recruit", filename, response);
        } catch (SSException e){
            e.printStackTrace();
            LogClerk.errLog.error(e);
        }
    }

    @RequestMapping(value = "employment", method = RequestMethod.GET)
    public void exportEmploymentInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<EmploymentInfoParsedExportDto> lists = employmentStatisticsService.listEmploymentInfo();

            String filename = "就业意向.xls";//设置下载时Excel的名称
            filename = ExcelUtil.encodeFilename(filename, request);//处理中文文件名
            ExcelUtil.writeExcel(lists, "recruit", filename, response);//调用Excel工具类生成Excel
        } catch (SSException e) {
            e.printStackTrace();
            LogClerk.errLog.error(e);
        }
    }

    @RequestMapping(value = "statisticsExport", method = RequestMethod.GET)
    public String chooseExportMethod(@RequestParam("statisticsType") int statisticsType) {

        if (statisticsType == 0 || statisticsType == 1 || statisticsType == 2 || statisticsType == 6 || statisticsType == 7) {
            return "redirect:exportQualification/" + statisticsType;
        } else if (statisticsType == 3 || statisticsType == 4 || statisticsType == 5) {
            return "redirect:exportNormalType/" + statisticsType;
        }
        return "500";
    }

    @RequestMapping(value = "exportQualification/{qualificationCode}", method = RequestMethod.GET)
    public void exportQualification(@PathVariable("qualificationCode") int qualificationCode,
                                    HttpServletRequest request, HttpServletResponse response) {
        try {
            //获取session中的graduateYear
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            int year = (int) ((ServletRequestAttributes) ra).getRequest().getSession(true).getAttribute("graduateYear");
            List<EmploymentStatisticsInfoDto> list = new ArrayList<>();

            List<College> collegeList = new ArrayList<>();
            if (qualificationCode == 1) {
                //本科生
                collegeList = collegeListService.getUnderCollegeList();
            } else if (qualificationCode == 2 || qualificationCode == 0 || qualificationCode == 6 || qualificationCode == 7) {
                //研究生或获取全部
                collegeList = collegeListService.getPostCollegeList();
            }

            int index = 1;

            List<EmploymentStatisticsInfoDto> employmentStatisticsInfoDtoList = new ArrayList<>();
            for (College college : collegeList) {

                //获取该学院的相关信息
                EmploymentStatisticsInfoDto employmentStatisticsInfoDto = new EmploymentStatisticsInfoDto();
                employmentStatisticsInfoDto = employmentStatisticsService.getEmploymentStatisticsInfoAboutQualification(college.getCollegeCode(), year, qualificationCode);
                employmentStatisticsInfoDto.setCollege(college.getCollegeName());
                employmentStatisticsInfoDtoList.add(employmentStatisticsInfoDto);
            }

            //获取总计信息
            EmploymentStatisticsInfoDto allInfo = employmentStatisticsService.getAllInfo(employmentStatisticsInfoDtoList);

            list.add(allInfo);

            Collections.sort(employmentStatisticsInfoDtoList);
            list.addAll(employmentStatisticsInfoDtoList);

            for (EmploymentStatisticsInfoDto employmentStatisticsInfoDto : list) {
                employmentStatisticsInfoDto.setId(index++);
            }

            String filename = "就业率统计信息.xls";//设置下载时Excel的名称
            filename = ExcelUtil.encodeFilename(filename, request);//处理中文文件名
            ExcelUtil.writeExcel(list, "recruit", filename, response);//调用Excel工具类生成Excel
        } catch (Exception e) {
            e.printStackTrace();
            LogClerk.errLog.error(e);
        }
    }

    @RequestMapping(value = "exportNormalType/{normalType}", method = RequestMethod.GET)
    public void exportNormalType(@PathVariable("normalType") int normalType,
                                 HttpServletRequest request, HttpServletResponse response) {
        try {
            //获取session中的graduateYear
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            int year = (int) ((ServletRequestAttributes) ra).getRequest().getSession(true).getAttribute("graduateYear");
            List<EmploymentStatisticsInfoDto> list = new ArrayList<>();

            List<EmploymentStatisticsInfoDto> employmentStatisticsInfoDtoList = new ArrayList<>();

            List<College> collegeList = collegeListService.getUnderCollegeList();

            for (College college : collegeList) {

                //获取该学院的相关信息
                EmploymentStatisticsInfoDto employmentStatisticsInfoDto = new EmploymentStatisticsInfoDto();
                employmentStatisticsInfoDto = employmentStatisticsService.getEmploymentStatisticsInfoAboutNormalType(college.getCollegeCode(), year, normalType);
                employmentStatisticsInfoDto.setCollege(college.getCollegeName());
                employmentStatisticsInfoDtoList.add(employmentStatisticsInfoDto);
            }

            Collections.sort(employmentStatisticsInfoDtoList);

            int index = 1;

            for (EmploymentStatisticsInfoDto employmentStatisticsInfoDto : employmentStatisticsInfoDtoList) {
                employmentStatisticsInfoDto.setId(index++);
            }

            //获取总计信息
            EmploymentStatisticsInfoDto allInfo = employmentStatisticsService.getAllInfo(employmentStatisticsInfoDtoList);

            list.add(allInfo);
            list.addAll(employmentStatisticsInfoDtoList);

            String filename = "就业率统计信息.xls";//设置下载时Excel的名称
            filename = ExcelUtil.encodeFilename(filename, request);//处理中文文件名
            ExcelUtil.writeExcel(list, "recruit", filename, response);//调用Excel工具类生成Excel
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
