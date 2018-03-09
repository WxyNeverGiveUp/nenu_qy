package com.pandawork.nenu.oa.web.controller.student.status;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.nenu.oa.common.dto.student.status.StuStatusInfoDto;
import com.pandawork.nenu.oa.common.dto.student.status.UpdateInfoDto;
import com.pandawork.nenu.oa.common.entity.data.*;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.entity.student.status.StuStatusInfo;
import com.pandawork.nenu.oa.common.enums.general.CheckStatusEnums;
import com.pandawork.nenu.oa.common.enums.general.CheckStatusForStuEnums;
import com.pandawork.nenu.oa.common.enums.general.MaterialTypeEnums;
import com.pandawork.nenu.oa.common.enums.student.status.NorMalStuTypeEnums;
import com.pandawork.nenu.oa.common.enums.student.status.QualificationNowEnums;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.LoginUtil;
import com.pandawork.nenu.oa.common.util.MessageInfo;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.common.util.WebConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * StuStatusInfoController
 *
 * @author wlm
 * @date 2016/7/18 9:49
 */

@Controller
@RequestMapping(value = URLConstants.STUDENT_STATUS_URL)
public class StuStatusInfoController extends AbstractController {

    /**
     * 去填写学籍页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String toNew(Model model, RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();
        try {
            StuStatusInfo stuStatusInfo = stuStatusInfoService.queryByStuNumber(username);
            if (!Assert.isNull(stuStatusInfo)) {
                if (stuStatusInfo.getCheckStatus() == 1 || stuStatusInfo.getCheckStatus() == 0 ||
                        stuStatusInfo.getCheckStatus() == 3 || stuStatusInfo.getCheckStatus() == 6) {//	3-辅导员审核未通过待修改，	6-副书记审核未通过待修改
                    //副书记或导员对该学生的审核没有通过，则该学生可以重新填写学籍
                    List<NationCode> nationList = nationService.listAll();
                    List<PoliticalCode> politicalList = politicalService.listAll();
                    List<QualificationCode> qualificationList = qualificationService.listAll();
                    List<CollegeCode> collegeList = collegeService.listAll();
//                    List<MajorDivision> majorBigList = majorService.listBigAll();
                    List<WhereAboutGoIntendCode> whereAboutGoIntendCodeList = whereAboutGoIntendService.listAll();
//                    List<WhereAboutGoCode> whereAboutGoList = whereAboutGoService.listAll();
                    List<DifficultyCode> difficultyList = difficultyService.listAll();
                    MajorCode major = null;
                    if (Assert.isNotNull(stuStatusInfo.getMajorCode())){
                        major = majorService.queryByCode(stuStatusInfo.getMajorCode(), stuStatusInfo.getMajorQualification());
                    }
                    Integer normalStuTypeCode = stuStatusInfo.getNormalStuCode();
                    String normalStuType = null;
                    if (Assert.isNotNull(normalStuTypeCode)) {
                        normalStuType = NorMalStuTypeEnums.valueOf(normalStuTypeCode).getName();
                    }
                    String originPlaceCode = stuStatusInfo.getOriginPlaceCode().toString();
                    String originPlace = null;
                    if (!originPlaceCode.equals("0")) {
                        originPlace = placeService.queryByCode(originPlaceCode).getShowName();
                    }
                    Integer trainingModeCode = stuStatusInfo.getTrainingModeCode();
                    String trainingMode = null;
                    if (Assert.isNotNull(trainingModeCode)) {
                        trainingMode = trainingModeService.queryByCode(stuStatusInfo.getTrainingModeCode()).getTrainingMode();
                    }
                    String school = null;
                    if (!Assert.isNull(stuStatusInfo.getSchoolCode()) && !Assert.lessOrEqualZero(stuStatusInfo.getSchoolCode())) {
                        school = schoolService.queryDtoByCode(String.valueOf(stuStatusInfo.getSchoolCode())).getName();
                    }
                    StuStatusInfoDto stuStatusInfoDto = stuStatusInfoService.queryDtoById(stuStatusInfo.getId());

                    model.addAttribute("student", stuStatusInfo);
                    model.addAttribute("nationList", nationList);
                    model.addAttribute("politicalList", politicalList);
                    model.addAttribute("qualificationList", qualificationList);
                    model.addAttribute("collegeList", collegeList);
                    model.addAttribute("major", major);
//                    model.addAttribute("majorBigList", majorBigList);
                    model.addAttribute("whereAboutGoIntendCodeList", whereAboutGoIntendCodeList);
                    model.addAttribute("difficultyList", difficultyList);
                    model.addAttribute("normalStuType", normalStuType);
                    model.addAttribute("originPlace", originPlace);
                    model.addAttribute("trainingMode", trainingMode);
                    model.addAttribute("schoolName", school);
                    model.addAttribute("studentDto", stuStatusInfoDto);
                    if (stuStatusInfo.getSex() == 1) {
                        model.addAttribute("sex", "男");
                    } else {
                        model.addAttribute("sex", "女");
                    }
                    if (stuStatusInfo.getStuLength() % 1.0 == 0) {
                        int stuLength = (new Double(stuStatusInfo.getStuLength())).intValue();
                        model.addAttribute("stuLength", stuLength);
                    } else {
                        Double stuLength = stuStatusInfo.getStuLength();
                        model.addAttribute("stuLength", stuLength);
                    }

                    return "student/status/new";
                } else {
                    model.addAttribute("errMsg", MessageInfo.UPDATE_SUBMITTED);
                    return "student/status/index";
                }
            } else {
                model.addAttribute("errMsg", "没有查询到学籍信息，请联系管理员确认是否录入");
                return "student/status/index";
//                return "redirect:/" + URLConstants.LOGOUT_URL;
            }
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
//            String failedUrl = "/" + URLConstants.STUDENT_STATUS_URL + "/new";
            String failedUrl = "/";
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    /**
     * 填写学籍
     *
     * @param stuStatusInfo
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String newStuStatusInfo(StuStatusInfo stuStatusInfo,
                                   RedirectAttributes redirectAttributes) {
        try {
//            this.checkBeforeSave(stuStatusInfo);
            if (Assert.isNull(stuStatusInfo)) {
                throw SSException.get(OaException.StuStatusInfoNotNull);
            }
            //获得id
            StuStatusInfo stuStatusInfo1 = null;//新建一个StuStatusInfo对象，用于从数据库中取数据
            if (Assert.isNull(stuStatusInfo.getId())) {
                Subject subject = SecurityUtils.getSubject();
                String stuNumber = subject.getPrincipal().toString();
                stuStatusInfo.setId(stuStatusInfoService.queryIdByStuNumber(stuNumber));
                stuStatusInfo1 = stuStatusInfoService.queryByStuNumber(stuNumber);//从数据库中把该学生拿出来
            }
            StuStatusInfo oldStuStatusInfo = stuStatusInfoService.queryById(stuStatusInfo.getId());
            if (Assert.lessOrEqualZero(oldStuStatusInfo.getQualificationNow())) {
                String qualification = LoginUtil.getUserGroupById(oldStuStatusInfo.getStudentNumber());
                if (qualification.contains("bzks")) {
                    stuStatusInfo.setQualificationNow(QualificationNowEnums.Undergraduate.getId());
                } else {
                    stuStatusInfo.setQualificationNow(QualificationNowEnums.Postgraduate.getId());
                }
            }

            if(stuStatusInfo1.getCheckStatus() == 0 ){
            stuStatusInfo.setCheckStatus(CheckStatusEnums.Saved.getId());
            }
            //检查学生学籍的信息(不包括师范生类别、培养方式、定向委培单位)是否修改，如果修改则在t_stu_update_info表中插入一条数据
            int alterByWho = 1;//被学生修改的
            stuUpdateInfoService.whereIsAltered(stuStatusInfo,alterByWho);
            stuStatusInfoService.updateById(stuStatusInfo);


            String successUrl = "/" + URLConstants.STUDENT_STATUS_URL + "/check";
            redirectAttributes.addFlashAttribute("correctMsg", SAVE_SUCCESS_MSG);
            return "redirect:" + successUrl;
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.STUDENT_STATUS_URL + "/";
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }


    /**
     * 去检查页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "check", method = RequestMethod.GET)
    public String checkInfo(Model model, RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();
        try {
            StuStatusInfo stuStatusInfo = stuStatusInfoService.queryByStuNumber(username);

            if (!Assert.isNull(stuStatusInfo)) {
                if (Assert.lessOrEqualZero(stuStatusInfo.getCheckStatus())) {
                    model.addAttribute("errMsg", MessageInfo.NOT_FILL_IN);
                    String failedUrl = "/";
                    return "redirect:" + failedUrl;
                }
                StuStatusInfoDto stuStatusInfoDto = stuStatusInfoService.queryDtoById(stuStatusInfo.getId());
//                NationCode nation = nationService.queryByNationId(stuStatusInfoDto.ge());
//                PoliticalCode political = politicalService.queryByPoliticalStandId(stuStatusInfoDto.getPoliticalStandCode());
//                QualificationCode qualification = qualificationService.listByCode(stuStatusInfoDto.getQualificationCode());
//                CollegeCode college = collegeService.listByCode(stuStatusInfoDto.getCollegeCode());
//                MajorCode major = majorService.listByCode(stuStatusInfoDto.getMajorCode());
//                WhereAboutGoCode whereAboutGo = whereAboutGoService.listByCode(Integer.parseInt(stuStatusInfoDto.getIntendWhereaboutsCode()));
//                DifficultyCode difficulty = difficultyService.queryByDifficultyId(stuStatusInfoDto.getDifficultyCode());
//                String normalStuType = NorMalStuTypeEnums.valueOf(stuStatusInfoDto.getNormalStuCode()).getName();
//                String originPlace = placeService.listByCode(stuStatusInfoDto.getOriginPlaceCode().toString()).getShowName();
//                String trainingMode = trainingModeService.listByCode(stuStatusInfoDto.getTrainingModeCode()).getTrainingMode();
//                String school = schoolService.queryDtoByCode(String.valueOf(stuStatusInfoDto.getSchoolCode())).getName();
                List<Material> materialList = materialService.listByStatusInfoIdAndType(stuStatusInfo.getId(), MaterialTypeEnums.StatusInfo.getId());
                int checkStatus = stuStatusInfo.getCheckStatus();
                int  isStudent = 1;//是否是学生，是学生的话只查看其他人给他修改的地方，1为学生，0为其他人
                List<UpdateInfoDto> updateInfoList = stuUpdateInfoService.listLatestDtoByStuStatusInfoId(stuStatusInfo.getId(),isStudent);

                //审核状态 1-已保存未提交，2-未审核，3-辅导员审核未通过待修改，4-辅导员审核未通过已修改，5-辅导员审核通过，
                // 6-副书记审核未通过待修改，7-副书记审核未通过已修改，8-副书记审核通过，9-学校审核未通过待修改，10-学校审核未通过已修改，
                // 11-学校审核通过，12-学校直接审核未通过待修改，13-学校直接审核未通过已修改，14-学校直接审核通过
                if(checkStatus == 1) {
                    model.addAttribute("checkResult", "已保存未提交");
                } else if(checkStatus == 2) {
                    model.addAttribute("checkResult", "未审核");
                } else if(checkStatus == 3) {
                    model.addAttribute("checkResult", "辅导员审核未通过待修改");
                } else if(checkStatus == 4) {
                    model.addAttribute("checkResult", "辅导员审核未通过已修改");
                } else if(checkStatus == 5) {
                    model.addAttribute("checkResult", "辅导员审核通过");
                } else if(checkStatus == 6) {
                    model.addAttribute("checkResult", "副书记审核未通过待修改");
                } else if(checkStatus == 7) {
                    model.addAttribute("checkResult", "副书记审核未通过已修改");
                } else if(checkStatus == 8) {
                    model.addAttribute("checkResult", "副书记审核通过");
                } else if(checkStatus == 9) {
                    model.addAttribute("checkResult", "学校审核未通过待修改");
                } else if(checkStatus == 10) {
                    model.addAttribute("checkResult", "学校审核未通过已修改");
                } else if(checkStatus == 11) {
                    model.addAttribute("checkResult", "学校审核通过");
                } else if(checkStatus == 12) {
                    model.addAttribute("checkResult", "学校直接审核未通过待修改");
                } else if(checkStatus == 13) {
                    model.addAttribute("checkResult", "学校直接审核未通过已修改");
                } else if(checkStatus == 14) {
                    model.addAttribute("checkResult", "学校直接审核通过");
                } else {
                    model.addAttribute("checkResult", "暂无数据");
                }

//                if (stuStatusInfo.getCheckStatus() == CheckStatusEnums.SchoolDirectNotThrough.getId() ||
//                        stuStatusInfo.getCheckStatus() == CheckStatusEnums.SchoolNotThrough.getId()) {
//                    model.addAttribute("checkResult", "学校审核未通过");
//                    model.addAttribute("reason", stuStatusInfoDto.getSchoolCheckReason());
//                } else if (stuStatusInfo.getCheckStatus() == CheckStatusEnums.DeputySecretaryNotThrough.getId()) {
//                    model.addAttribute("checkResult", "副书记审核未通过");
//                    model.addAttribute("reason", stuStatusInfoDto.getDeputySecretaryCheckReason());
//                } else if ((stuStatusInfo.getCheckStatus() == CheckStatusEnums.CounsellorNotThrough.getId())) {
//                    model.addAttribute("checkResult", "辅导员审核未通过");
//                    model.addAttribute("reason", stuStatusInfoDto.getCounsellorCheckReason());
//                } else {
//                    model.addAttribute("checkResult", CheckStatusForStuEnums.valueOf(stuStatusInfo.getCheckStatus()).getName());
//                    model.addAttribute("reason", "");
//                }

                model.addAttribute("student", stuStatusInfoDto);
                model.addAttribute("checkStatus", checkStatus);
                model.addAttribute("materialList", materialList);
                model.addAttribute("updateInfoList", updateInfoList);

//                model.addAttribute("nation", nation.getNation());
//                model.addAttribute("political", political.getPoliticalStand());
//                model.addAttribute("qualification", qualification.getQualification());
//                model.addAttribute("college", college.getCollege());
//                model.addAttribute("major", major.getMajorName());
//                model.addAttribute("whereAboutGo", whereAboutGo.getWhereAboutGo());
//                model.addAttribute("difficulty", difficulty.getDifficultyMode());
//                model.addAttribute("normalStuType", normalStuType);
//                model.addAttribute("originPlace", originPlace);
//                model.addAttribute("trainingMode", trainingMode);
//                model.addAttribute("schoolName", school);
                return "student/status/check";
            } else {
                return "student/status/index";
//                return "redirect:/" + URLConstants.LOGOUT_URL;
            }
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/";
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    /**
     * 确认提交
     *
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "submit", method = RequestMethod.GET)
    public String submitInfo(RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        String stuNumber = subject.getPrincipal().toString();
        try {
            stuStatusInfoService.updateCheckStatusByStuNumber(stuNumber);
            String successUrl = "/";
            redirectAttributes.addFlashAttribute("correctMsg", NEW_SUCCESS_MSG);
            return "redirect:" + successUrl;
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.STUDENT_STATUS_URL + "/";
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }


    /**
     * 去修改学籍页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String toUpdate(Model model, RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();

        try {
            StuStatusInfo stuStatusInfo = stuStatusInfoService.queryByStuNumber(username);

            if (!Assert.isNull(stuStatusInfo)) {
                if (stuStatusInfo.getCheckStatus() == 0) {
                    redirectAttributes.addFlashAttribute("errMsg", MessageInfo.NOT_FILL_IN);
                    String failedUrl = "/";
                    return "redirect:" + failedUrl;
                }
                if (stuStatusInfo.getCheckStatus() != 1 && stuStatusInfo.getCheckStatus() != 3 && stuStatusInfo.getCheckStatus() != 6 && stuStatusInfo.getCheckStatus() != 9 && stuStatusInfo.getCheckStatus() != 12) {
                    redirectAttributes.addFlashAttribute("errMsg", MessageInfo.UPDATE_SUBMITTED);
                    String failedUrl = "/" + URLConstants.STUDENT_STATUS_URL + "/check";
                    return "redirect:" + failedUrl;
                }
                StuStatusInfoDto stuStatusInfoDto = stuStatusInfoService.queryDtoById(stuStatusInfo.getId());
                List<Material> materialList = materialService.listByStatusInfoIdAndType(stuStatusInfo.getId(), MaterialTypeEnums.StatusInfo.getId());

                if (stuStatusInfo.getCheckStatus() == CheckStatusEnums.SchoolDirectNotThrough.getId() ||
                        stuStatusInfo.getCheckStatus() == CheckStatusEnums.SchoolNotThrough.getId()) {
                    model.addAttribute("checkResult", "学校审核未通过");
                    model.addAttribute("reason", stuStatusInfoDto.getSchoolCheckReason());
                } else if (stuStatusInfo.getCheckStatus() == CheckStatusEnums.DeputySecretaryNotThrough.getId()) {
                    model.addAttribute("checkResult", "副书记审核未通过");
                    model.addAttribute("reason", stuStatusInfoDto.getDeputySecretaryCheckReason());
                } else if ((stuStatusInfo.getCheckStatus() == CheckStatusEnums.CounsellorNotThrough.getId())) {
                    model.addAttribute("checkResult", "辅导员审核未通过");
                    model.addAttribute("reason", stuStatusInfoDto.getCounsellorCheckReason());
                } else {
                    model.addAttribute("checkResult", CheckStatusForStuEnums.valueOf(stuStatusInfo.getCheckStatus()).getName());
                    model.addAttribute("reason", "");
                }
                List<NationCode> nationList = nationService.listAll();
                List<PoliticalCode> politicalList = politicalService.listAll();
                List<QualificationCode> qualificationList = qualificationService.listAll();
                List<CollegeCode> collegeList = collegeService.listAll();
//                List<MajorDivision> majorBigList = majorService.listBigAll();
                List<WhereAboutGoCode> whereAboutGoList = whereAboutGoService.listAll();
                List<DifficultyCode> difficultyList = difficultyService.listAll();
//                String majorQualification = stuStatusInfo.getMajorQualification();
                MajorCode major = null;
                if (Assert.isNotNull(stuStatusInfo.getMajorCode())){
                    major = majorService.queryByCode(stuStatusInfo.getMajorCode(), stuStatusInfo.getMajorQualification());
                }
                String normalStuType = NorMalStuTypeEnums.valueOf(stuStatusInfo.getNormalStuCode()).getName();
                String originPlace = placeService.queryByCode(stuStatusInfo.getOriginPlaceCode().toString()).getShowName();
                String trainingMode = trainingModeService.queryByCode(stuStatusInfo.getTrainingModeCode()).getTrainingMode();
                String school = null;
                if (!Assert.isNull(stuStatusInfo.getSchoolCode()) && !Assert.lessOrEqualZero(stuStatusInfo.getSchoolCode())) {
                    school = schoolService.queryDtoByCode(String.valueOf(stuStatusInfo.getSchoolCode())).getName();
                }

                model.addAttribute("student", stuStatusInfo);
                model.addAttribute("nationList", nationList);
                model.addAttribute("politicalList", politicalList);
                model.addAttribute("qualificationList", qualificationList);
                model.addAttribute("collegeList", collegeList);
//                model.addAttribute("majorBigList", majorBigList);
                model.addAttribute("major", major);
//                model.addAttribute("majorQualification", majorQualification);
                model.addAttribute("whereAboutGoList", whereAboutGoList);
                model.addAttribute("difficultyList", difficultyList);
                model.addAttribute("normalStuType", normalStuType);
                model.addAttribute("originPlace", originPlace);
                model.addAttribute("trainingMode", trainingMode);
                model.addAttribute("schoolName", school);
                model.addAttribute("studentDto", stuStatusInfoDto);
                model.addAttribute("materialList", materialList);

                return "student/status/update";
            } else {
                String failedUrl = "/";
                redirectAttributes.addFlashAttribute("errMsg", "修改失败，请联系管理员");
                return "redirect:" + failedUrl;
            }
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            e.printStackTrace();
            sendErrMsg(e.getMessage());
            String failedUrl = "/";
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }



    /**
     * 去详情页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String toDetail(Model model) {
        return "forward:/" + URLConstants.STUDENT_STATUS_URL + "/check";
    }

    /**
     * 对不允许学生修改的字段进行检查
     *
     * @param stuStatusInfo
     * @throws SSException
     */
    private void checkBeforeSave(StuStatusInfo stuStatusInfo,Model model) throws SSException {


        Subject subject = SecurityUtils.getSubject();
        String stuNumber = subject.getPrincipal().toString();



        if (Assert.isNull(stuStatusInfo)) {
            throw SSException.get(OaException.StuStatusInfoNotNull);
        }

        if (Assert.isNull(stuStatusInfo.getId())) {

            stuStatusInfo.setId(stuStatusInfoService.queryIdByStuNumber(stuNumber));
        }
        if (!Assert.isNull(stuStatusInfo.getName())) {
            stuStatusInfo.setName(null);
        }
        if (!Assert.isNull(stuStatusInfo.getCandidateNumber())) {
            stuStatusInfo.setCandidateNumber(null);
        }
        if (!Assert.isNull(stuStatusInfo.getStudentNumber())) {
            stuStatusInfo.setStudentNumber(null);
        }
        if (!Assert.isNull(stuStatusInfo.getSchoolCode()) && !stuStatusInfo.getSchoolCode().equals(WebConstants.schoolCode)) {
            stuStatusInfo.setSchoolCode(WebConstants.schoolCode);
        }
        if (!Assert.isNull(stuStatusInfo.getNormalStuCode())) {
            stuStatusInfo.setNormalStuCode(null);
        }
        if (!Assert.isNull(stuStatusInfo.getOriginPlaceCode())) {
            stuStatusInfo.setOriginPlaceCode(null);
        }
        if (!Assert.isNull(stuStatusInfo.getTrainingModeCode())) {
            stuStatusInfo.setTrainingModeCode(null);
        }
        if (!Assert.isNull(stuStatusInfo.getWeipeiUnit())) {
            stuStatusInfo.setWeipeiUnit(null);
        }
    }

    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String toError() {
        return "student/status/login_error";
    }
}
