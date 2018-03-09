package com.pandawork.nenu.oa.web.controller.student.business;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.dto.business.ProtocolStuDto;
import com.pandawork.nenu.oa.common.dto.student.status.StuStatusInfoDto;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.entity.student.status.StuStatusInfo;
import com.pandawork.nenu.oa.common.enums.general.CheckReasonEnums;
import com.pandawork.nenu.oa.common.enums.general.CheckResultEnums;
import com.pandawork.nenu.oa.common.util.MessageInfo;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.common.util.WebConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 学生端，业务预约controller层
 *
 * @author zhaiaxin
 * @time: 2017/7/20 15:51.
 */
@Controller
@RequestMapping(value = URLConstants.STUDENT_BUSINESS_NEWURL)
public class StuProtocolNewController extends AbstractController{


    /**
     * 进入列表展示页（申请新协议，免师，毕业去向，定向委培）
     * @param model
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "toBusiness/{type}",method = RequestMethod.GET)
    public String toBusiness(@PathVariable("type") Integer type, Model model,RedirectAttributes redirectAttributes) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String stuNumber = subject.getPrincipal().toString();
        int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
        model.addAttribute("type",type);//业务类型

        List<ProtocolStuDto> protocolStuDtoList = null;
        if(type == 1){
            protocolStuDtoList = protocolStuService.listNewProtocol(statusInfoId);
        }else if(type == 2){
            protocolStuDtoList = protocolStuService.listGraduate(statusInfoId);
        }else if(type == 3){
            protocolStuDtoList = protocolStuService.listFreeTeacher(statusInfoId);
            Boolean freeTeacherProvince = false;
            for(ProtocolStuDto protocolStuDto : protocolStuDtoList){
                if(protocolStuDto.getFreeTeacherProvince() != null){
                    freeTeacherProvince = true;
                    break;
                }
            }
            model.addAttribute("freeTeacherProvince",freeTeacherProvince);
        }else if(type == 4){
            protocolStuDtoList = protocolStuService.listDirectional(statusInfoId);
        }else if(type == 5){
            StuStatusInfo stuStatusInfo = stuStatusInfoService.queryByStuNumber(stuNumber);
            String qualificationCode = stuStatusInfo.getQualificationCode();//得到学历的code
            if(qualificationCode.equals("01") || qualificationCode.equals("03")  ){
                protocolStuDtoList = protocolStuService.listDoctorBusiness(statusInfoId);
            }else {
                String failedUrl = "/";
                redirectAttributes.addFlashAttribute("errMsg", MessageInfo.ENTER_DOCTOR_FAILED_MSG);
                return "redirect:" + failedUrl;
            }
        }
            try {
                if (!Assert.isNull(statusInfoId) && !(protocolStuDtoList.size()==0)) {
                    model.addAttribute("protocolStuDtoList", protocolStuDtoList);
                    return "student/business/applyList";
                }else if(!Assert.isNull(statusInfoId)){
                    return "student/business/applyList";
                }else {
                    String failedUrl = "/";
                    redirectAttributes.addFlashAttribute("errMsg", MessageInfo.ENTER_FAILED_MSG);
                    return "redirect:" + failedUrl;
                }
            }catch (Exception e){
                e.printStackTrace();
                return "500";
            }
    }


    /**
     * 点击查看按钮进入详情页
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "to/businessDetail/{type}/{id}",method = RequestMethod.GET)
    public String toBusinessDetail(@PathVariable("type") Integer type,@PathVariable("id") Integer id, Model model) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String stuNumber= subject.getPrincipal().toString();

        try{
            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();

            StuStatusInfo stuStatusInfo = stuStatusInfoService.queryByStuNumber(stuNumber);
            ProtocolStuDto protocolStuDto = protocolStuService.queryBusiness(id);
            List<Material> list = materialService.listByProtocolId(id);//列出业务预约的材料

            String agreementNumber = "";

            DispatchInfo dispatchInfo = stuDispatchService.queryDispatchByStuId(statusInfoId);
            if(Assert.isNull(dispatchInfo)) {
                agreementNumber = "暂无协议编号";
            }else {
                agreementNumber = dispatchInfo.getAgreementNumber();
            }

            String name = stuStatusInfo.getName();
            String text = null;


            if(protocolStuDto.getCheckProtocolResult() == CheckResultEnums.Checked.getName()) {
                //如果审核通过了 告诉学生 协议类型 时间（用date），地点
                text =  name + WebConstants.PCMSG1 + protocolStuDto.getProtocolType() +
                        WebConstants.PCMSG1ADD1 + protocolStuDto.getFetchTime() + WebConstants.PCMSG1ADD2 +
                       protocolStuDto.getFetchPlace() + WebConstants.PCMSG1ADD3;

            }else if (protocolStuDto.getCheckProtocolResult() == CheckResultEnums.NotThrough.getName()) {
                //如果没通过，告诉学生 理由（短信模版）
                switch (CheckReasonEnums.StringToId(protocolStuDto.getCheckProtocolReason())) {
                    case 2:
                        text = WebConstants.PCMSG2;
                        break;
                    case 3:
                        text = WebConstants.PCMSG3;
                        break;
                    case 4:
                        text = WebConstants.PCMSG4;
                        break;
                    case 5:
                        text = WebConstants.PCMSG5;
                        break;
                    case 6:
                        text = WebConstants.PCMSG6;
                        break;
                    case 7:
                        text = WebConstants.PCMSG7;
                        break;
                    case 8:
                        text = WebConstants.PCMSG8;
                        break;
//                    case 99:
//                        text = WebConstants.PCMSG99 + protocolStuDto.getCheckProtocolReason() + WebConstants.PCMSG99ADD;
//                        break;
                    default:

                        text = WebConstants.PCMSG99 + protocolStuDto.getCheckProtocolReason();

                        break;
                }
                text = name + WebConstants.PCMSG + protocolStuDto.getProtocolType() + text;
            }

            StuStatusInfoDto stuStatusInfoDto = stuStatusInfoService.queryDtoById(stuStatusInfo.getId());

            model.addAttribute("stuStatusInfoDto",stuStatusInfoDto);
            model.addAttribute("protocolStuDto",protocolStuDto);
            model.addAttribute("materialList",list);
            model.addAttribute("agreementNumber",agreementNumber);
            model.addAttribute("text",text);
            model.addAttribute("type",type);
            if(protocolStuDto.getFreeTeacherProvince() != null) {
                model.addAttribute("province", protocolStuDto.getFreeTeacherProvince());
            }
            return "student/business/applyDetail";
        }catch (Exception e) {
            e.printStackTrace();
            return "500";
        }
    }
//
    /**
     * 进入申请新协议材料提交页
     * @param model
     * @param redirectAttributes
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "to/newBusiness/{type}",method = RequestMethod.GET)
    public String toNewBusiness(@PathVariable("type") Integer type, Model model,RedirectAttributes redirectAttributes) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String stuNumber= subject.getPrincipal().toString();

        try {
            Integer checkResult = 0;
            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
            List<Protocol> protocolList = protocolStuService.queryProtocol(statusInfoId,type);

            //若之前无该协议记录，则将checkResult赋值0[初始状态]
            if(protocolList.size() == 0){
                checkResult = 0;
            }else {
                Protocol protocol = protocolList.get(protocolList.size()-1);
                checkResult = protocol.getCheckProtocolResult();//获取最新一条记录的审核结果
            }

            if(checkResult == 1 || checkResult == 3 || checkResult == 4){//审核结果为 未审核 或 审核未通过待修改 或 审核未通过已修改
                //返回列表展示页
                redirectAttributes.addFlashAttribute("correctMsg", MessageInfo.UPDATE_SUBMITTED);
                return "redirect:" + "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toBusiness/"+type;

            } else {//审核结果为 初始状态 或 审核通过(再次申请)

                model.addAttribute("statusInfoId",statusInfoId);
                model.addAttribute("type",type);
                //进入材料提交页
                return "student/business/protocol";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return "500";
        }
    }
//
    /**
     * 新协议添加
     * @param protocol
     * @param redirectAttributes
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "newBusiness/{type}",method = RequestMethod.POST)
    public String newBusiness(@PathVariable("type") Integer type, Protocol protocol,RedirectAttributes redirectAttributes) throws SSException{
        Subject subject = SecurityUtils.getSubject();
        String stuNumber= subject.getPrincipal().toString();

        try{
            Integer checkResult = 0;
            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
            List<Material> list = materialService.listRecent(statusInfoId, type+3);//列出业务预约的材料
            List<Protocol> protocolList = protocolStuService.queryProtocol(statusInfoId,type);
            //若之前无该协议记录，则将checkResult赋值0[初始状态]
            if(protocolList.size() == 0){
                checkResult = 0;
            }else {
                Protocol protocol1 = protocolList.get(protocolList.size()-1);
                checkResult = protocol1.getCheckProtocolResult();//获取最新一条记录的审核结果
            }

            if(list.size() != 0){
                protocol.setCheckProtocolResult(1);//checkResult == 2 || 0
                protocolStuService.newBusiness(protocol);
                materialService.updateProtocolIdByStatusInfoIdAndType(protocol.getId(),statusInfoId,type+3);//将协议Id更新，以便调用materialService.listByProtocolId
                redirectAttributes.addFlashAttribute("correctMsg", MessageInfo.NEW_SUCCESS_MSG);
                //跳转到申请新协议列表展示页
                return "redirect:" + "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toBusiness/"+type;
            } else {
                String failedUrl = "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toBusiness/"+type;
                redirectAttributes.addFlashAttribute("errMsg", MessageInfo.UPLOAD_FAILED_MSG);
                return "redirect:" + failedUrl;
            }
        }catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.STUDENT_BUSINESS_NEWURL+"/toBusiness/"+type;
            redirectAttributes.addFlashAttribute("errMsg", MessageInfo.NEW_FAILED_MSG);
            return "redirect:" + failedUrl;
        }
    }
//
    /**
     * 进入修改页
     * @param model
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "to/updateBusiness/{type}/{id}",method = RequestMethod.GET)
    public String toUpdateBusiness(@PathVariable("type") Integer type,@PathVariable("id") Integer id, Model model,RedirectAttributes redirectAttributes) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        String stuNumber= subject.getPrincipal().toString();

        try {
            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
            List<Material> materialList = materialService.listByProtocolId(id);//列出业务预约的材料
            List<Protocol> protocolList = protocolStuService.queryProtocol(statusInfoId,type);
            Protocol protocol = protocolList.get(protocolList.size()-1);

            model.addAttribute("protocol",protocol);
            model.addAttribute("materialList",materialList);
            model.addAttribute("type",type);
            //进入申请新协议材料更改页
            return "student/business/protocol_alter";
        }catch (Exception e) {
            e.printStackTrace();
            return "500";
        }
    }
//
    /**
     * 新协议修改（审核状态为 审核未通过待修改）
     * @param protocol
     * @param redirectAttributes
     * @param model
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "updateBusiness/{type}",method = RequestMethod.POST)
    public String updateBusiness(@PathVariable("type") Integer type, Protocol protocol,RedirectAttributes redirectAttributes,Model model) throws SSException{

        Subject subject = SecurityUtils.getSubject();
        String stuNumber= subject.getPrincipal().toString();
        try{
            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
            protocol.setCheckProtocolResult(4);
            protocolStuService.updateBusiness(protocol);
            materialService.updateProtocolIdByStatusInfoIdAndType(protocol.getId(),statusInfoId,type+3);//将协议Id更新，以便调用materialService.listByProtocolId
            redirectAttributes.addFlashAttribute("correctMsg", MessageInfo.UPDATE_SUBMITTED);
            //返回申请新协议列表展示页
            return "redirect:" + "/" + URLConstants.STUDENT_BUSINESS_NEWURL+"/toBusiness/"+type;
        }catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            String failedUrl = "/" + URLConstants.STUDENT_BUSINESS_NEWURL+"/toBusiness/"+type;
            redirectAttributes.addFlashAttribute("errMsg", MessageInfo.NEW_FAILED_MSG);
            return "redirect:" + failedUrl;
        }
    }


    /**
     * 上传材料
     * @param file
     * @return
     */
    @RequestMapping(value = "ajax/upload/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(@PathVariable("id") int id,@RequestParam("file") PandaworkMultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }
        Subject subject = SecurityUtils.getSubject();
        String stuNumber = subject.getPrincipal().toString();
        try {
            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
            Material material = new Material();
            material.setStatusInfoId(statusInfoId);
            material.setMaterialName(file.getOriginalFilename());
            material.setMaterialType(id);

            String path = protocolService.uploadMaterial(file, material);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("path", path);
            return sendJsonObject(jsonObject);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }
//
//    /**
//     * 进入定向委培页面
//     * @param model
//     * @param redirectAttributes
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "toDirectional" ,method = RequestMethod.GET)
//    public String toDirectional(Model model,RedirectAttributes redirectAttributes) throws Exception{
//        Subject subject = SecurityUtils.getSubject();
//        String stuNumber = subject.getPrincipal().toString();
//        int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
//        ProtocolStuDto protocolStuDto = protocolStuService.queryDirectional(statusInfoId);
//        List<Material> list = materialService.listByStatusInfoIdAndType(statusInfoId, 7);
//
//
//
//        model.addAttribute("materials",list);
//        try {
//            if (!Assert.isNull(statusInfoId ) && !Assert.isNull(protocolStuDto) ) {
//                StuStatusInfo stuStatusInfo = stuStatusInfoService.queryByStuNumber(stuNumber);
//                String name = stuStatusInfo.getName();
//                String text = null;
//
//                if(protocolStuDto.getCheckProtocolResult() == CheckResultEnums.Checked.getName()) {
//                    //如果审核通过了 告诉学生 协议类型 时间（用date），地点
//                    text =  name + WebConstants.PCMSG1 + protocolStuDto.getProtocolType() +
//                            WebConstants.PCMSG1ADD1 + protocolStuDto.getFetchTime() + WebConstants.PCMSG1ADD2 +
//                            protocolStuDto.getFetchPlace() + WebConstants.PCMSG1ADD3;
//
//                }else if (protocolStuDto.getCheckProtocolResult() == CheckResultEnums.NotThrough.getName()) {
//                    //如果没通过，告诉学生 理由（短信模版）
//                    switch (CheckReasonEnums.StringToId(protocolStuDto.getCheckProtocolReason())) {
//                        case 2:
//                            text = WebConstants.PCMSG2;
//                            break;
//                        case 3:
//                            text = WebConstants.PCMSG3;
//                            break;
//                        case 4:
//                            text = WebConstants.PCMSG4;
//                            break;
//                        case 5:
//                            text = WebConstants.PCMSG5;
//                            break;
//                        case 6:
//                            text = WebConstants.PCMSG6;
//                            break;
//                        case 7:
//                            text = WebConstants.PCMSG7;
//                            break;
//                        case 8:
//                            text = WebConstants.PCMSG8;
//                            break;
////                        case 99:
////                            text = WebConstants.PCMSG99 + protocolStuDto.getCheckProtocolReason() + WebConstants.PCMSG99ADD;
////                            break;
//                        default:
//
//                            text = WebConstants.PCMSG99 + protocolStuDto.getCheckProtocolReason() ;
//
//                            break;
//                    }
//                    text = name + WebConstants.PCMSG + protocolStuDto.getProtocolType() + text;
//                }
//
//                model.addAttribute("text",text);
//                model.addAttribute("protocolStuDto", protocolStuDto);
//                model.addAttribute("statusInfoId",statusInfoId);
//                return "student/business/directionalNew";
//            } else if(!Assert.isNull(statusInfoId )) {
//
//
//                model.addAttribute("statusInfoId",statusInfoId);
//                return "student/business/directionalNew";
//            } else {
//                String failedUrl = "/";
//                redirectAttributes.addFlashAttribute("errMsg", MessageInfo.ENTER_FAILED_MSG);
//                return "redirect:" + failedUrl;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return "500";
//        }
//    }
//
//    /**
//     * 新增或修改定向生业务
//     * @param protocol
//     * @param redirectAttributes
//     * @return
//     * @throws SSException
//     */
//    @RequestMapping(value = "newOrUpdateDirectional",method = RequestMethod.POST)
//    public String newOrUpdateDirectional(Protocol protocol,RedirectAttributes redirectAttributes) throws SSException{
//
//        Subject subject = SecurityUtils.getSubject();
//        String stuNumber= subject.getPrincipal().toString();
//        try{
//            Integer checkResult = 0;
//            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
//            List<Material> list = materialService.listByStatusInfoIdAndType(statusInfoId, 7);//列出业务预约的材料
//            ProtocolStuDto protocolStuDto = protocolStuService.queryDirectional(statusInfoId);
//            //若之前无该协议记录，则将checkResult赋值0[初始状态]
//            if(protocolStuDto == null){
//                 checkResult = 0;
//            }else {
//                 checkResult = CheckResultEnums.StringToId(protocolStuDto.getCheckProtocolResult());//获取最新一条记录的审核结果
//            }
//
//            if(list.size() != 0 && (checkResult == 0 )){
//                protocol.setCheckProtocolResult(1);
//                protocolStuService.newBusiness(protocol);
//                redirectAttributes.addFlashAttribute("correctMsg", MessageInfo.NEW_SUCCESS_MSG);
//                //跳转到定向委培页面
//                return "redirect:" + "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toDirectional";
//            }else if(list.size() != 0 && checkResult == 3){
//                protocol.setCheckProtocolResult(4);
//                protocolStuService.updateBusiness(protocol);
//                redirectAttributes.addFlashAttribute("correctMsg", MessageInfo.NEW_SUCCESS_MSG);
//                //跳转到定向委培页面
//                return "redirect:" + "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toDirectional";
//            }
//            else if(list.size() != 0 && (checkResult == 1 || checkResult == 2 || checkResult == 4)){
//                String failedUrl = "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toDirectional";
//                redirectAttributes.addFlashAttribute("errMsg", MessageInfo.UPDATE_SUBMITTED);
//                return "redirect:" + failedUrl;
//            } else {
//                String failedUrl = "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toDirectional";
//                redirectAttributes.addFlashAttribute("errMsg", MessageInfo.UPLOAD_FAILED_MSG);
//                return "redirect:" + failedUrl;
//            }
//        }catch (SSException e) {
//            LogClerk.errLog.error(e);
//            sendErrMsg(e.getMessage());
//            String failedUrl = "/" + URLConstants.STUDENT_BUSINESS_NEWURL+"/toDirectional";
//            redirectAttributes.addFlashAttribute("errMsg", MessageInfo.NEW_FAILED_MSG);
//            return "redirect:" + failedUrl;
//        }
//
//    }
//
//    /**
//     * 进入毕业去向页面
//     * @param model
//     * @param redirectAttributes
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "toGraduate" ,method = RequestMethod.GET)
//    public String toGraduate(Model model,RedirectAttributes redirectAttributes) throws Exception{
//        Subject subject = SecurityUtils.getSubject();
//        String stuNumber = subject.getPrincipal().toString();
//        int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
//        ProtocolStuDto protocolStuDto = protocolStuService.queryGraduate(statusInfoId);
//        List<Material> list = materialService.listByStatusInfoIdAndType(statusInfoId, 5);
//
//
//        model.addAttribute("materials",list);
//        try {
//            if (!Assert.isNull(statusInfoId ) && !Assert.isNull(protocolStuDto) ) {
//                StuStatusInfo stuStatusInfo = stuStatusInfoService.queryByStuNumber(stuNumber);
//                String name = stuStatusInfo.getName();
//                String text = null;
//
//
//                if(protocolStuDto.getCheckProtocolResult() == CheckResultEnums.Checked.getName()) {
//                    //如果审核通过了 告诉学生 协议类型 时间（用date），地点
//                    text =  name + WebConstants.PCMSG1 + protocolStuDto.getProtocolType() +
//                            WebConstants.PCMSG1ADD1 + protocolStuDto.getFetchTime() + WebConstants.PCMSG1ADD2 +
//                            protocolStuDto.getFetchPlace() + WebConstants.PCMSG1ADD3;
//
//                }else if (protocolStuDto.getCheckProtocolResult() == CheckResultEnums.NotThrough.getName()) {
//                    //如果没通过，告诉学生 理由（短信模版）
//                    switch (CheckReasonEnums.StringToId(protocolStuDto.getCheckProtocolReason())) {
//                        case 2:
//                            text = WebConstants.PCMSG2;
//                            break;
//                        case 3:
//                            text = WebConstants.PCMSG3;
//                            break;
//                        case 4:
//                            text = WebConstants.PCMSG4;
//                            break;
//                        case 5:
//                            text = WebConstants.PCMSG5;
//                            break;
//                        case 6:
//                            text = WebConstants.PCMSG6;
//                            break;
//                        case 7:
//                            text = WebConstants.PCMSG7;
//                            break;
//                        case 8:
//                            text = WebConstants.PCMSG8;
//                            break;
////                        case 99:
////                            text = WebConstants.PCMSG99 + protocolStuDto.getCheckProtocolReason() + WebConstants.PCMSG99ADD;
////                            break;
//                        default:
//                            //未处理
//                            text = WebConstants.PCMSG99 + protocolStuDto.getCheckProtocolReason() ;
//
//                            break;
//                    }
//                    text = name + WebConstants.PCMSG + protocolStuDto.getProtocolType() + text;
//                }
//
//                model.addAttribute("text",text);
//                model.addAttribute("protocolStuDto", protocolStuDto);
//                model.addAttribute("statusInfoId",statusInfoId);
//                return "student/business/graduationNew";
//            } else if(!Assert.isNull(statusInfoId )) {
//                model.addAttribute("statusInfoId",statusInfoId);
//                return "student/business/graduationNew";
//            } else {
//                String failedUrl = "/";
//                redirectAttributes.addFlashAttribute("errMsg", MessageInfo.ENTER_FAILED_MSG);
//                return "redirect:" + failedUrl;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return "500";
//        }
//    }
//
//    /**
//     * 新增毕业去向业务
//     * @param protocol
//     * @param redirectAttributes
//     * @return
//     * @throws SSException
//     */
//    @RequestMapping(value = "newOrUpdateGraduate",method = RequestMethod.POST)
//    public String newOrUpdateGraduate(Protocol protocol,RedirectAttributes redirectAttributes) throws SSException{
//
//        Subject subject = SecurityUtils.getSubject();
//        String stuNumber= subject.getPrincipal().toString();
//        try{
//            Integer checkResult = 0;
//            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
//            List<Material> list = materialService.listByStatusInfoIdAndType(statusInfoId, 5);//列出业务预约的材料
//            ProtocolStuDto protocolStuDto = protocolStuService.queryGraduate(statusInfoId);
//            //若之前无该协议记录，则将checkResult赋值0[初始状态]
//            if(protocolStuDto == null){
//                checkResult = 0;
//            }else {
//                checkResult = CheckResultEnums.StringToId(protocolStuDto.getCheckProtocolResult());//获取最新一条记录的审核结果
//            }
//
//            if(list.size() != 0 && (checkResult == 0 )){
//                protocol.setCheckProtocolResult(1);
//                protocolStuService.newBusiness(protocol);
//                redirectAttributes.addFlashAttribute("correctMsg", MessageInfo.NEW_SUCCESS_MSG);
//                //跳转到定向委培页面
//                return "redirect:" + "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toGraduate";
//            }else if(list.size() != 0 && checkResult == 3){
//                protocol.setCheckProtocolResult(4);
//                protocolStuService.updateBusiness(protocol);
//                redirectAttributes.addFlashAttribute("correctMsg", MessageInfo.NEW_SUCCESS_MSG);
//                //跳转到定向委培页面
//                return "redirect:" + "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toGraduate";
//            }
//            else if(list.size() != 0 && (checkResult == 1 || checkResult == 2 || checkResult == 4)){
//                String failedUrl = "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toGraduate";
//                redirectAttributes.addFlashAttribute("errMsg", MessageInfo.UPDATE_SUBMITTED);
//                return "redirect:" + failedUrl;
//            } else {
//
//                String failedUrl = "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toGraduate";
//                redirectAttributes.addFlashAttribute("errMsg", MessageInfo.UPLOAD_FAILED_MSG);
//                return "redirect:" + failedUrl;
//            }
//        }catch (SSException e) {
//            LogClerk.errLog.error(e);
//            sendErrMsg(e.getMessage());
//            String failedUrl = "/" + URLConstants.STUDENT_BUSINESS_NEWURL+"/toGraduate";
//            redirectAttributes.addFlashAttribute("errMsg", MessageInfo.NEW_FAILED_MSG);
//            return "redirect:" + failedUrl;
//        }
//
//    }
//
//    /**
//     * 进入免费师范生页面
//     * @param model
//     * @param redirectAttributes
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "toFreeTeacher" ,method = RequestMethod.GET)
//    public String toFreeTeacher(Model model,RedirectAttributes redirectAttributes) throws Exception{
//        Subject subject = SecurityUtils.getSubject();
//        String stuNumber = subject.getPrincipal().toString();
//        int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
//        ProtocolStuDto protocolStuDto = protocolStuService.queryFreeTeacher(statusInfoId);
//        Protocol protocol = protocolStuService.queryFreeTeacherProtocol(statusInfoId);
//        List<Material> list = materialService.listByStatusInfoIdAndType(statusInfoId, 6);
//
//
//        model.addAttribute("materials",list);
//        try {
//            if (!Assert.isNull(statusInfoId ) && !Assert.isNull(protocolStuDto) ) {
//
//                StuStatusInfo stuStatusInfo = stuStatusInfoService.queryByStuNumber(stuNumber);
//                String name = stuStatusInfo.getName();
//                String text = null;
//
//
//                if(protocolStuDto.getCheckProtocolResult() == CheckResultEnums.Checked.getName()) {
//                    //如果审核通过了 告诉学生 协议类型 时间（用date），地点
//                    text =  name + WebConstants.PCMSG1 + protocolStuDto.getProtocolType() +
//                            WebConstants.PCMSG1ADD1 + protocolStuDto.getFetchTime() + WebConstants.PCMSG1ADD2 +
//                            protocolStuDto.getFetchPlace() + WebConstants.PCMSG1ADD3;
//
//                }else if (protocolStuDto.getCheckProtocolResult() == CheckResultEnums.NotThrough.getName()) {
//                    //如果没通过，告诉学生 理由（短信模版）
//                    switch (CheckReasonEnums.StringToId(protocolStuDto.getCheckProtocolReason())) {
//                        case 2:
//                            text = WebConstants.PCMSG2;
//                            break;
//                        case 3:
//                            text = WebConstants.PCMSG3;
//                            break;
//                        case 4:
//                            text = WebConstants.PCMSG4;
//                            break;
//                        case 5:
//                            text = WebConstants.PCMSG5;
//                            break;
//                        case 6:
//                            text = WebConstants.PCMSG6;
//                            break;
//                        case 7:
//                            text = WebConstants.PCMSG7;
//                            break;
//                        case 8:
//                            text = WebConstants.PCMSG8;
//                            break;
////                        case 99:
////                            text = WebConstants.PCMSG99 + protocolStuDto.getCheckProtocolReason() + WebConstants.PCMSG99ADD;
////                            break;
//                        default:
//
//                            text = WebConstants.PCMSG99 + protocolStuDto.getCheckProtocolReason() ;
//
//                            break;
//                    }
//                    text = name + WebConstants.PCMSG + protocolStuDto.getProtocolType() + text;
//                }
//
//                model.addAttribute("text",text);
//                protocolStuDto.setFreeTeacherProvince(protocolStuService.queryProvince(protocol.getFreeTeacherProvince()));
//                model.addAttribute("protocolStuDto", protocolStuDto);
//                model.addAttribute("statusInfoId",statusInfoId);
//                return "student/business/freeTeacherNew";
//            } else if(!Assert.isNull(statusInfoId )) {
//                model.addAttribute("statusInfoId",statusInfoId);
//                return "student/business/freeTeacherNew";
//            } else {
//                String failedUrl = "/";
//                redirectAttributes.addFlashAttribute("errMsg", MessageInfo.ENTER_FAILED_MSG);
//                return "redirect:" + failedUrl;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return "500";
//        }
//    }
//
//    /**
//     * 新增或修改免费师范生业务
//     * @param protocol
//     * @param redirectAttributes
//     * @return
//     * @throws SSException
//     */
//    @RequestMapping(value = "newOrUpdateFreeTeacher",method = RequestMethod.POST)
//    public String newOrUpdateFreeTeacher(Protocol protocol,RedirectAttributes redirectAttributes) throws SSException{
//
//        Subject subject = SecurityUtils.getSubject();
//        String stuNumber= subject.getPrincipal().toString();
//        try{
//            Integer checkResult = 0;
//            int statusInfoId = stuStatusInfoService.queryByStuNumber(stuNumber).getId();
//            List<Material> list = materialService.listByStatusInfoIdAndType(statusInfoId, 6);//列出业务预约的材料
//            ProtocolStuDto protocolStuDto = protocolStuService.queryFreeTeacher(statusInfoId);
//
//            //若之前无该协议记录，则将checkResult赋值0[初始状态]
//            if(protocolStuDto == null){
//                checkResult = 0;
//            }else {
//                checkResult = CheckResultEnums.StringToId(protocolStuDto.getCheckProtocolResult());//获取最新一条记录的审核结果
//            }
//
//            if(list.size() != 0 && (checkResult == 0 )){
//                protocol.setCheckProtocolResult(1);
//                protocolStuService.newBusiness(protocol);
//                redirectAttributes.addFlashAttribute("correctMsg", MessageInfo.NEW_SUCCESS_MSG);
//                //跳转到定向委培页面
//                return "redirect:" + "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toFreeTeacher";
//            }else if(list.size() != 0 && checkResult == 3){
//                protocol.setCheckProtocolResult(4);
//                protocolStuService.updateBusiness(protocol);
//                redirectAttributes.addFlashAttribute("correctMsg", MessageInfo.NEW_SUCCESS_MSG);
//                //跳转到定向委培页面
//                return "redirect:" + "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toFreeTeacher";
//            }
//            else if(list.size() != 0 && (checkResult == 1 || checkResult == 2 || checkResult == 4)){
//                String failedUrl = "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toFreeTeacher";
//                redirectAttributes.addFlashAttribute("errMsg", MessageInfo.UPDATE_SUBMITTED);
//                return "redirect:" + failedUrl;
//            } else {
//                String failedUrl = "/" + URLConstants.STUDENT_BUSINESS_NEWURL + "/toFreeTeacher";
//                redirectAttributes.addFlashAttribute("errMsg", MessageInfo.UPLOAD_FAILED_MSG);
//                return "redirect:" + failedUrl;
//            }
//        }catch (SSException e) {
//            LogClerk.errLog.error(e);
//            sendErrMsg(e.getMessage());
//            String failedUrl = "/" + URLConstants.STUDENT_BUSINESS_NEWURL+"/toFreeTeacher";
//            redirectAttributes.addFlashAttribute("errMsg", MessageInfo.NEW_FAILED_MSG);
//            return "redirect:" + failedUrl;
//        }
//
//    }

}
