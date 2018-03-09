package com.pandawork.nenu.oa.web.controller.student.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchQueryDto;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchRemarksDto;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchUpdateDto;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import com.pandawork.nenu.oa.common.entity.data.JobCode;
import com.pandawork.nenu.oa.common.entity.data.PlaceCode;
import com.pandawork.nenu.oa.common.entity.data.ReportCode;
import com.pandawork.nenu.oa.common.entity.data.WhereAboutGoCode;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.entity.general.CompanyProperty;
import com.pandawork.nenu.oa.common.entity.general.CompanyTrade;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.entity.general.ProvinceProperty;
import com.pandawork.nenu.oa.common.entity.student.status.StuStatusInfo;
import com.pandawork.nenu.oa.common.enums.general.CheckResultEnums;
import com.pandawork.nenu.oa.common.enums.general.MaterialTypeEnums;
import com.pandawork.nenu.oa.common.enums.general.TrueFalseEnums;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;


/**
 * Description:学生派遣Controller
 * Author: luowanli
 * Date: 2016/7/16
 * Time: 9:54
 */

@Controller
@RequestMapping(value = URLConstants.STUDENT_DISPATCH_URL)
public class StuDispatchController extends AbstractController {

    /**
     * 去填写派遣信息页面
     *
     * @param model
     * @param redirectAttributes
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String toAddDispatch(Model model, RedirectAttributes redirectAttributes) throws SSException {
        Subject subject = SecurityUtils.getSubject();
        String stuNumber = subject.getPrincipal().toString();
        try {
            StuStatusInfo stuStatusInfo = stuStatusInfoService.queryByStuNumber(stuNumber);
            if (Assert.isNull(stuStatusInfo)) {
                return "student/dispatch/no_number";
            }
            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
            DispatchInfo dispatchInfo = stuDispatchService.queryDispatchByStuId(statusInfoId);
            if (Assert.isNull(dispatchInfo)) {
                return "student/dispatch/no_number";
            }
            List<CompanyProperty> companyPropertyCodes = companyPropertyService.listAll();
            List<CompanyTrade> companyTradeCodes = companyTradeService.listAll();
            List<JobCode> jobCodes = jobCodeService.listAll();
            List<ReportCode> reportCodes = reportCodeService.listAll();
            List<WhereAboutGoCode> whereaboutgos = whereAboutGoService.listAll();
            String stuRemark = dispatchInfo.getStuRemark();
            if (Assert.isNull(dispatchInfo.getAgreementNumber())) {
                return "student/dispatch/no_number";
            }
            if (dispatchInfo.getCheckStatus() != 0 && dispatchInfo.getCheckStatus() != 1) {
                return "student/dispatch/alert";
            }
            String agreementNumber = dispatchInfo.getAgreementNumber();
            model.addAttribute("statusInfoId", statusInfoId);
            model.addAttribute("agreementNumber", agreementNumber);
            model.addAttribute("companyPropertyCodes", companyPropertyCodes);
            model.addAttribute("companyTradeCodes", companyTradeCodes);
            model.addAttribute("jobCodes", jobCodes);
            model.addAttribute("reportCodes", reportCodes);
            model.addAttribute("whereaboutgos", whereaboutgos);
            model.addAttribute("stuRemark",stuRemark);
            return "student/dispatch/alter";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/";
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    /**
     * 添加学生派遣信息
     *
     * @param redirectAttributes
     * @param dispatchInfo
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addDispatch(RedirectAttributes redirectAttributes, DispatchInfo dispatchInfo) throws SSException {
        Subject subject = SecurityUtils.getSubject();
        String stuNumber = subject.getPrincipal().toString();
        try {
            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
            DispatchInfo dispatch = stuDispatchService.queryDispatchByStuId(statusInfoId);
            int id = dispatch.getId();
            dispatchInfo.setId(id);
            int checkStatus = dispatch.getCheckStatus();
            if (checkStatus == 0) {
                dispatchInfo.setCheckStatus(1);
            }
            stuDispatchService.updateDispatch(dispatchInfo);
            String successUrl = "/" + URLConstants.STUDENT_DISPATCH_URL + "/detail";
            redirectAttributes.addFlashAttribute("correctMsg", NEW_SUCCESS_MSG);
            return "redirect:" + successUrl;
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.STUDENT_DISPATCH_URL + "/add";
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    /**
     * ajax实现
     * 报到证发放类别中 如选择生源地，自动生成改学生的省份
     * @return
     * @throws SSException
     */
    //后面新加的需求
    @RequestMapping(value = "ajax/makeprovice/", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject makeProvice( )throws SSException{
        Subject subject = SecurityUtils.getSubject();
        String stuNumber = subject.getPrincipal().toString();

            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
            StuStatusInfo stuStatusInfo = stuStatusInfoService.queryById(statusInfoId);
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("originPlace", stuStatusInfo.getOriginPlace());

            jsonObject.put("reportCompany", stuStatusInfo.getOriginPlace());


            return sendJsonObject(jsonObject);



    }
    /**
     * 修改提交后的审核状态
     *
     * @param dispatchInfo
     * @param redirectAttributes
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "alterstatus", method = RequestMethod.GET)
    public String alterStatus(DispatchInfo dispatchInfo, RedirectAttributes redirectAttributes) throws SSException {
        Subject subject = SecurityUtils.getSubject();
        String stuNumber = subject.getPrincipal().toString();
        try {
            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
            DispatchInfo dispatch = stuDispatchService.queryDispatchByStuId(statusInfoId);
            int id = dispatch.getId();
            dispatchInfo.setId(id);
            int checkStatus = dispatch.getCheckStatus();
            int counsellorCheckResult = dispatch.getCounsellorCheckResult();
            int deputySecretaryCheckResult = dispatch.getDeputySecretaryCheckResult();
            int schoolCheckResult = dispatch.getSchoolCheckResult();
            if (checkStatus == 1) {
                dispatchInfo.setCheckStatus(2);
            }
            if (counsellorCheckResult == 3 && deputySecretaryCheckResult == 1 && schoolCheckResult == 1) {
                dispatchInfo.setCheckStatus(4);
                dispatchInfo.setCounsellorCheckResult(4);
            }
            if (counsellorCheckResult == 2 && deputySecretaryCheckResult == 3 && schoolCheckResult == 1) {
                dispatchInfo.setCheckStatus(7);
                dispatchInfo.setDeputySecretaryCheckResult(4);
            }
            if (counsellorCheckResult == 2 && deputySecretaryCheckResult == 2 && schoolCheckResult == 3) {
                dispatchInfo.setCheckStatus(10);
                dispatchInfo.setSchoolCheckResult(4);
            }
            stuDispatchService.updateDispatch(dispatchInfo);
            String successUrl = "/";
            redirectAttributes.addFlashAttribute("correctMsg", NEW_SUCCESS_MSG);
            return "redirect:" + successUrl;
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.STUDENT_DISPATCH_URL + "/update";
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    /**
     * 上传材料
     * @param file
     * @return
     */
    @RequestMapping(value = "ajax/upload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(@RequestParam("file") PandaworkMultipartFile file ,
                             @RequestParam("dispatchInfoId") Integer dispatchInfoId) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            Subject subject = SecurityUtils.getSubject();
            String stuNumber = subject.getPrincipal().toString();
            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
            Material material = new Material();
            material.setStatusInfoId(statusInfoId);
            material.setDispatchInfoId(dispatchInfoId);
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
     * 去详情页
     *
     * @param model
     * @param redirectAttributes
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String toDetail(Model model, RedirectAttributes redirectAttributes) throws SSException {
        Subject subject = SecurityUtils.getSubject();
        String stuNumber = subject.getPrincipal().toString();
        try {
            StuStatusInfo stuStatusInfo = stuStatusInfoService.queryByStuNumber(stuNumber);
            if (Assert.isNull(stuStatusInfo)) {
                return "student/dispatch/no_number";
            }
            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
            DispatchInfo dispatchInfo = stuDispatchService.queryDispatchByStuId(statusInfoId);
            if (Assert.isNull(dispatchInfo)) {
                return "student/dispatch/no_number";
            }
            Integer id = dispatchInfoService.getIdByStuId(statusInfoId);
            DispatchQueryDto dispatchQueryDto = dispatchInfoService.getDetail(id);
            if (Assert.isNull(dispatchQueryDto)) {
                return "student/dispatch/no_number";
            }
            List<Protocol> protocolAdminList = protocolAdminService.queryProtocolByStatusInfoId(statusInfoId);
            if(protocolAdminList != null || protocolAdminList.size() != 0) {
                for (Protocol protocol : protocolAdminList){
                    if (protocol.getProtocolType() == 8){
                        String freeTeacherProvince = "";
//                        freeTeacherProvince = provinceService.queryByCode(protocol.getFreeTeacherProvince()).getName();
                        String code = protocol.getFreeTeacherProvince();
                        ProvinceProperty provinceProperty = provinceService.queryByCode(code);
                        if (provinceProperty != null){
                            freeTeacherProvince = provinceProperty.getName();
                        }
                        model.addAttribute("freeTeacherProvince",freeTeacherProvince);
                    }
                }
            }
            int checkStatus = dispatchInfo.getCheckStatus();
            List<DispatchUpdateDto> dispatchUpdateDtoList = Collections.emptyList();
            int  isStudent = 1;
            dispatchUpdateDtoList = dispatchUpdateInfoService.listLatestDtoByDispatchInfo(dispatchInfo.getId(),isStudent);
            String url = "/";
            if (checkStatus == 0) {
                return "redirect:" + url;
            }
            int counsellorCheckResult1 = dispatchInfo.getCounsellorCheckResult();
            int deputySecretaryCheckResult1 = dispatchInfo.getDeputySecretaryCheckResult();
            int schoolCheckResult1 = dispatchInfo.getSchoolCheckResult();
            List<Material> list = materialService.listByStatusInfoIdAndType(statusInfoId, 2);
            DispatchRemarksDto dispatchRemarksDto = stuDispatchService.queryRemarksByStuId(statusInfoId);
            String cityId = String.valueOf(dispatchRemarksDto.getCityId());
            String originPlaceCode = String.valueOf(dispatchRemarksDto.getOriginPlaceCode());
            String cityIdOfProvince = cityId.substring(0, 1);
            String originPlaceCodeOfProvince = originPlaceCode.substring(0, 1);
            if (cityIdOfProvince.equals(originPlaceCodeOfProvince)) {
                model.addAttribute("remarks", "回生源地就业");
            } else {
                model.addAttribute("remarks", "跨省就业");
            }
            String acceptHukou = TrueFalseEnums.valueOf(dispatchInfo.getAcceptHukou()).getName();
            String acceptFile = TrueFalseEnums.valueOf(dispatchInfo.getAcceptFile()).getName();
            String counsellorCheckResult = CheckResultEnums.valueOf(dispatchInfo.getCounsellorCheckResult()).getName();
            String deputySecretaryCheckResult = CheckResultEnums.valueOf(dispatchInfo.getDeputySecretaryCheckResult()).getName();
            String schoolCheckResult = CheckResultEnums.valueOf(dispatchInfo.getSchoolCheckResult()).getName();
            String counsellorCheck, deputySecretaryCheck, schoolCheck;
            if (Assert.isNull(dispatchInfo.getCounsellorCheckTime())) {
                counsellorCheck = "无";
            } else {
                counsellorCheck = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dispatchInfo.getCounsellorCheckTime());
            }
            if (Assert.isNull(dispatchInfo.getDeputySecretaryCheckTime())) {
                deputySecretaryCheck = "无";
            } else {
                deputySecretaryCheck = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dispatchInfo.getDeputySecretaryCheckTime());
            }
            if (Assert.isNull(dispatchInfo.getSchoolCheckTime())) {
                schoolCheck = "无";
            } else {
                schoolCheck = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dispatchInfo.getSchoolCheckTime());
            }
            model.addAttribute("counsellorCheckResult", counsellorCheckResult);
            model.addAttribute("deputySecretaryCheckResult", deputySecretaryCheckResult);
            model.addAttribute("schoolCheckResult", schoolCheckResult);
            model.addAttribute("dispatchInfo", dispatchInfo);
            model.addAttribute("list", list);
            model.addAttribute("acceptHukou", acceptHukou);
            model.addAttribute("acceptFile", acceptFile);
            model.addAttribute("checkStatus", checkStatus);
            model.addAttribute("counsellor", counsellorCheckResult1);
            model.addAttribute("deputy", deputySecretaryCheckResult1);
            model.addAttribute("school", schoolCheckResult1);
            model.addAttribute("counsellorCheck", counsellorCheck);
            model.addAttribute("deputySecretaryCheck", deputySecretaryCheck);
            model.addAttribute("schoolCheck", schoolCheck);
            model.addAttribute("dispatchUpdateDtoList",dispatchUpdateDtoList);
            return "student/dispatch/detail";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/";
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    /**
     * 去修改页
     *
     * @param model
     * @param redirectAttributes
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String toUpdate(Model model, RedirectAttributes redirectAttributes) throws SSException {
        Subject subject = SecurityUtils.getSubject();
        String stuNumber = subject.getPrincipal().toString();
        try {
            StuStatusInfo stuStatusInfo = stuStatusInfoService.queryByStuNumber(stuNumber);
            if (Assert.isNull(stuStatusInfo)) {
                return "student/dispatch/no_number";
            }
            //sjz修改
            StuStatusInfo statusInfo = stuStatusInfoService.queryByStuNumber(stuNumber);
            //sjz添加
            PlaceCode placeCode = placeService.queryByCode(String.valueOf(statusInfo.getOriginPlaceCode()));
            int statusInfoId = statusInfo.getId();
            DispatchInfo dispatchInfo = stuDispatchService.queryDispatchByStuId(statusInfoId);
            if (Assert.isNull(dispatchInfo)) {
                return "student/dispatch/no_number";
            }
            int checkStatus = dispatchInfo.getCheckStatus();
            int counsellorCheckResult = dispatchInfo.getCounsellorCheckResult();
            int deputySecretaryCheckResult = dispatchInfo.getDeputySecretaryCheckResult();
            int stuCounsellorCheckResult = statusInfo.getCounsellorCheckResult();
            int stuDeputySecretaryCheckResult = statusInfo.getDeputySecretaryCheckResult();
            int schoolCheckResult = dispatchInfo.getSchoolCheckResult();
            List<Material> list = materialService.listByStatusInfoIdAndType(statusInfoId, 2);
            List<CompanyProperty> companyPropertyCodes = companyPropertyService.listAll();
            List<CompanyTrade> companyTradeCodes = companyTradeService.listAll();
            List<JobCode> jobCodes = jobCodeService.listAll();
            List<ReportCode> reportCodes = reportCodeService.listAll();
            List<WhereAboutGoCode> whereaboutgos = whereAboutGoService.listAll();
            if (Assert.isNull(dispatchInfo.getAgreementNumber())) {
                return "student/dispatch/no_number";
            }
            if (checkStatus == 2 || counsellorCheckResult == 4 || deputySecretaryCheckResult == 4 || schoolCheckResult == 4) {
                return "student/dispatch/alert";
            }
            model.addAttribute("companyPropertyCodes", companyPropertyCodes);
            model.addAttribute("companyTradeCodes", companyTradeCodes);
            model.addAttribute("jobCodes", jobCodes);
            model.addAttribute("reportCodes", reportCodes);
            model.addAttribute("whereaboutgos", whereaboutgos);
            model.addAttribute("list", list);
            model.addAttribute("dispatchInfo", dispatchInfo);
            model.addAttribute("stuCounsellorCheckResult",stuCounsellorCheckResult);
            model.addAttribute("stuDeputySecretaryCheckResult",stuDeputySecretaryCheckResult);
            //sjz添加
            model.addAttribute("placeCode", placeCode);
            if (checkStatus == 0 || checkStatus == 1 || counsellorCheckResult == 3 || deputySecretaryCheckResult == 3 || schoolCheckResult == 3) {
                return "student/dispatch/alter";
            }
            String failedUrl = "/";
            return "redirect:" + failedUrl;
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/";
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    /**
     * 处理学生修改签约信息
     * 单继重
     * @param id
     * @param dispatchInfo
     * @param redirectAttributes
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam(value = "id", required = true) Integer id,
                         DispatchInfo dispatchInfo,
                         RedirectAttributes redirectAttributes) throws SSException {
        try {
//            StuStatusInfo stuStatusInfo = stuStatusInfoService.queryById(dispatchInfo.getStatusInfoId());
//            int counsellorCheckResult = stuStatusInfo.getCounsellorCheckResult();
//            int deputySecretaryCheckResult = stuStatusInfo.getDeputySecretaryCheckResult();
            dispatchInfo.setCheckStatus(1);
            int alterByWho = 1;//被学生修改的
            dispatchUpdateInfoService.whereIsAltered(dispatchInfo,alterByWho);
            dispatchInfoService.updateDispatchInfo(dispatchInfo);
            String successUrl = "/" + URLConstants.STUDENT_DISPATCH_URL + "/detail";
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

    /**
     * 去无协议编号提醒页
     *
     * @param model
     * @param redirectAttributes
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "alert", method = RequestMethod.POST)
    public String toNoAgreementNumber(Model model, RedirectAttributes redirectAttributes) throws SSException {
        Subject subject = SecurityUtils.getSubject();
        String stuNumber = subject.getPrincipal().toString();
        try {
            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
            if (Assert.isNotNull(statusInfoId) || Assert.lessOrEqualZero(statusInfoId))
                model.addAttribute("statusInfoId", statusInfoId);
            return "/student/dispatch/no_number";
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/";
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    /**
     * 去已提交提醒页
     *
     * @param model
     * @param redirectAttributes
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "submitalert", method = RequestMethod.GET)
    public String toSubmittedAlert(Model model, RedirectAttributes redirectAttributes) throws SSException {
        Subject subject = SecurityUtils.getSubject();
        String stuNumber = subject.getPrincipal().toString();
        try {
            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
            if (Assert.isNotNull(statusInfoId) || Assert.lessOrEqualZero(statusInfoId))
                model.addAttribute("statusInfoId", statusInfoId);
            return "/student/dispatch/alert";
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/";
            redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
            return "redirect:" + failedUrl;
        }
    }

    /**
     * 提交前检查审核情况
     * @return JSON对象
     * @throws SSException SS异常
     */
    @RequestMapping(value = "ajax/check" , method = RequestMethod.POST)
    @ResponseBody
    public JSONObject checkBeforeSubmit(@RequestParam("statusInfoId")Integer statusInfoId) throws SSException{
        StuStatusInfo stuStatusInfo = stuStatusInfoService.queryById(statusInfoId);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        if(stuStatusInfo.getCounsellorCheckResult() == 2 && stuStatusInfo.getDeputySecretaryCheckResult() ==2 ){
            jsonObject.put("submitCode",1);
            jsonArray.add(jsonObject);
        } else{
            jsonObject.put("submitCode",0);
            jsonArray.add(jsonObject);
        }
        return sendJsonArray(jsonArray);
    }
}
