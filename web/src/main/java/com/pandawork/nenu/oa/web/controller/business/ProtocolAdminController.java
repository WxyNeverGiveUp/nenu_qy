package com.pandawork.nenu.oa.web.controller.business;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.oa.common.dto.business.ProtocolAdminListDto;
import com.pandawork.nenu.oa.common.dto.business.ProtocolExportDto;
import com.pandawork.nenu.oa.common.dto.student.status.StuStatusInfoDto;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.entity.data.CollegeCode;
import com.pandawork.nenu.oa.common.entity.data.QualificationCode;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.entity.general.ProvinceProperty;
import com.pandawork.nenu.oa.common.entity.student.status.StuStatusInfo;
import com.pandawork.nenu.oa.common.enums.business.ProtocolTypeEnums;
import com.pandawork.nenu.oa.common.enums.general.CheckReasonEnums;
import com.pandawork.nenu.oa.common.enums.general.CheckResultEnums;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.CacheUtil;
import com.pandawork.nenu.oa.common.util.ExcelUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

/**
 *管理员端 业务预约
 * Created by Zuosy on 2017/7/22.
 */

@Controller
@RequestMapping(value = URLConstants.ADMIN_PROTOCOL_NEW_URL)
public class ProtocolAdminController extends AbstractController {

    /**
     * 根据学籍ID查看 协议信息
     */
    @RequestMapping(value = "detail/{protocolId}", method = RequestMethod.GET)
    public String toDetail(@PathVariable("protocolId") int protocolId, Model model) throws SSException {
        try {
            //根据协议id查找协议信息
            Protocol protocol = protocolAdminService.queryByProtocolId(protocolId);
            //协议信息里面有学籍ID
            int statusInfoId = protocol.getStatusInfoId();
            //根据学籍id查看学籍
            StuStatusInfoDto stuStatusInfoDto = stuStatusInfoService.queryDtoById(statusInfoId);

            SimpleDateFormat checkProtocolTime = new SimpleDateFormat("y年M月d日");
            String protocolType = ProtocolTypeEnums.valueOf(protocol.getProtocolType()).getName();

            String checkProtocolResult = CheckResultEnums.valueOf(protocol.getCheckProtocolResult()).getName();
            String agreementNumber = "";

            DispatchInfo dispatchInfo = stuDispatchService.queryDispatchByStuId(statusInfoId);
            if(Assert.isNull(dispatchInfo)) {
                agreementNumber = "暂无协议编号";
            }else {
                agreementNumber = dispatchInfo.getAgreementNumber();
            }

            //材料展示
            List<Material> list = Collections.emptyList();
            //材料类型枚举和协议类型枚举不兼容
            int cases = protocol.getProtocolType();

//            if(cases <= 4 || cases == 12){
                list = materialService.listByProtocolId(protocolId);
//            }else {
//                int matericalType = -1;
//                switch (cases) {
////                    case 2:
////                    case 3:
////                    case 4:
////                        matericalType = 4;
////                        break;
//                    case 5:
//                    case 6:
//                        matericalType = 5;
//                        break;
//                    case 7:
//                    case 8:
//                        matericalType = 6;
//                        break;
//                    case 9:
//                    case 10:
//                    case 11:
//                        matericalType = 7;
//                        break;
//                    default:
//                        matericalType = -1;
//                        break;
////                }
//                list = materialService.listByStatusInfoIdAndType(statusInfoId, matericalType);
//            }


            String freeTeacherProvince = protocolStuService.queryProvince(protocol.getFreeTeacherProvince());

            model.addAttribute("province",freeTeacherProvince);
            model.addAttribute("materialList",list);
            model.addAttribute("student", stuStatusInfoDto);
            model.addAttribute("protocol",protocol);
            model.addAttribute("checkProtocolTime",checkProtocolTime.format(protocol.getCheckProtocolTime()));
            model.addAttribute("protocolType",protocolType);
            model.addAttribute("checkProtocolResult",checkProtocolResult);
            model.addAttribute("oldAgreementNumber",agreementNumber);

        }catch (SSException e) {
            LogClerk.errLog.error(e);
            return "500";
        }
        return "admin/business/change_detail";
    }

    /**
     *修改结果 ajax
     * @param id 协议ID
     * @param checkProtocolResult
     * @param checkProtocolReason
     * @param checkOther
     * @param date  (fetchTime)
     * @param fetchPlace
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "ajax/check", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject Check(@RequestParam("id") int id, @RequestParam("checkProtocolResult") int checkProtocolResult,
                            @RequestParam("checkProtocolReason") int checkProtocolReason,
                            @RequestParam(value = "checkOther" ,required = false) String checkOther,
                            @RequestParam(value = "agreementNumber" ,required = false) String agreementNumber,
                            @RequestParam(value = "date", required = false) String date,
                            @RequestParam(value = "fetchPlace", required = false, defaultValue = "") String fetchPlace)
            throws IOException{

        Date fetchTime = null;
        //date转成fetchTime
        if(date != null && !date.equals("")) {
            Pattern pattern = Pattern.compile("[^0-9]");
            String[] strings = pattern.split(date);
            Integer[] integers = new Integer[strings.length];
            for (int i = 0; i < strings.length; i++) {
                integers[i] = Integer.valueOf(strings[i]);
            }
            GregorianCalendar g = new GregorianCalendar(integers[0], integers[1]-1, integers[2], integers[3], integers[4]);
            fetchTime = g.getTime();
        }

        //增加一段代码显示审核修改人 因为不太会用可能出bug
        Subject subject = SecurityUtils.getSubject();
        String realName = subject.getSession().getAttribute("realName").toString();
        //System.out.println(realName);


        Protocol protocol = new Protocol();
        String reason = null;

        //枚举审核理由
        if(checkProtocolReason == CheckReasonEnums.ElseReason.getId()) {
            reason = checkOther;
        }else{
            reason = CheckReasonEnums.valueOf(checkProtocolReason).getName();
        }


        try{
            //还要写发短信
            Protocol oldProtocol = protocolAdminService.queryByProtocolId(id);//要告诉学生 协议类型
            String protocolType = ProtocolTypeEnums.valueOf(oldProtocol.getProtocolType()).getName(); // 协议类型
            StuStatusInfo student = stuStatusInfoService.queryById(oldProtocol.getStatusInfoId());
            String mobile = student.getCellphone();
//            String mobile = "15844043832";
            String name = student.getName();
            String text = null;

            if(checkProtocolResult == CheckResultEnums.Checked.getId()) {
                //如果审核通过了 告诉学生 协议类型 时间（用date），地点
                text =  name + WebConstants.PCMSG1 + protocolType +
                        WebConstants.PCMSG1ADD1 + date + WebConstants.PCMSG1ADD2 +
                        fetchPlace + WebConstants.PCMSG1ADD3;
                smsService.sendSms(text, mobile);
            }else if (checkProtocolResult == CheckResultEnums.NotThrough.getId()) {
                //如果没通过，告诉学生 理由（短信模版）
                switch(checkProtocolReason){
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
                    case 99:
                        text = WebConstants.PCMSG99 + checkOther + WebConstants.PCMSG99ADD;
                        break;
                    default:
                        //未处理
                        break;
                }
                text = name + WebConstants.PCMSG + protocolType + text;
                smsService.sendSms(text, mobile);
            }

            protocol.setId(id);
            protocol.setCheckProtocolOperator(realName);
            protocol.setCheckProtocolResult(checkProtocolResult);
            protocol.setCheckProtocolReason(reason);
            protocol.setFetchTime(fetchTime);
            protocol.setFetchPlace(fetchPlace);
            protocolAdminService.updateById(protocol);

            //checkProtocolResult = 2 是前提 审核通过了才行
            //最后还有一个把协议号 存到派遣信息表中
            //新建一条数据 插入学籍ID、协议号。
            //旧的数据 是否是当前协议（否）、是否删除（是）

            //先判断这个学生之前有没有 未删除的 派遣协议 如果有就 假删除
            //然后插入一条新的数据

            Protocol protocol2 = protocolAdminService.queryByProtocolId(id);
            if(protocol2.getCheckProtocolResult() == 2 && agreementNumber != null) {
                Integer statusInfoId = protocol2.getStatusInfoId();

                List<DispatchInfo> oldDispatchInfoList = dispatchInfoService.queryDispatchByStuStatusInfoId(statusInfoId);

                for (DispatchInfo oldDispatchInfo : oldDispatchInfoList) {
                    if (oldDispatchInfo != null && oldDispatchInfo.getIsDeleted() != 1) {
                        //在这里把就数据删除
                        dispatchInfoService.updateCurrentAgreement(oldDispatchInfo.getStatusInfoId());
                        dispatchInfoService.deleteById(oldDispatchInfo.getId());
                    }
                }

                //新数据就插入
                DispatchInfo newDispatchInfo = new DispatchInfo();
                newDispatchInfo.setAgreementNumber(agreementNumber);
                newDispatchInfo.setStatusInfoId(statusInfoId);
                dispatchInfoService.newAgreement(newDispatchInfo);
            }
            return sendJsonObject(AJAX_SUCCESS_CODE);
        }catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    /**
     * 检查数据库中是否有这个协议号 ajax
     * @param agreementNumber
     * @return
     */
    @RequestMapping(value = "ajax/exist", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject checkAgreementNumber(@RequestParam(value = "agreementNumber") String agreementNumber){
        boolean exist = false;
        try {
            exist = protocolAdminService.queryAgreementNumberExist(agreementNumber);
        }catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
        //这部分跟前端沟通了 没问题
        if(exist){
            return sendJsonObject(AJAX_SUCCESS_CODE);
        }else {
            return sendJsonObject(AJAX_FAILURE_CODE);
        }
    }

    /**
     * 跳转到申领新协议页面
     * @param model
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "toNewProtocolList", method = RequestMethod.GET)
    public String toNewProtocolList(Model model) throws SSException{
        try {
            List<CollegeCode> collegeList = collegeService.listAll();
            List<QualificationCode> qualificationList = qualificationService.listAll();
            List<ProvinceProperty> provinceList = provinceService.listAll();

            model.addAttribute("collegeList", collegeList);
            model.addAttribute("qualificationList", qualificationList);
            model.addAttribute("provinceList", provinceList);
            return "admin/business/appointments/new_protocol";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 跳转到毕业去向变更领协议页面
     * @param model
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "toWhereaboutsgoChangeList", method = RequestMethod.GET)
    public String toWhereaboutsgoChangeList(Model model) throws SSException{
        try {
            List<CollegeCode> collegeList = collegeService.listAll();
            List<QualificationCode> qualificationList = qualificationService.listAll();
            List<ProvinceProperty> provinceList = provinceService.listAll();

            model.addAttribute("collegeList", collegeList);
            model.addAttribute("qualificationList", qualificationList);
            model.addAttribute("provinceList", provinceList);
            return "admin/business/appointments/whereaboutsgo_change";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 跳转到博士生申领协议业务
     * @param model
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "toDoctorChangeList", method = RequestMethod.GET)
    public String toDoctorChangeList(Model model) throws SSException{
        try {
            List<CollegeCode> collegeList = collegeService.listAll();
            List<ProvinceProperty> provinceList = provinceService.listAll();

            model.addAttribute("collegeList", collegeList);
            model.addAttribute("provinceList", provinceList);
            return "admin/business/appointments/doctorChange_business";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 跳转到免费师范生业务页面
     * @param model
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "toNormalBusinessList", method = RequestMethod.GET)
    public String toNormalBusinessList(Model model) throws SSException{
        try {
            List<CollegeCode> collegeList = collegeService.listAll();
            List<QualificationCode> qualificationList = qualificationService.listAll();
            List<ProvinceProperty> provinceList = provinceService.listAll();

            model.addAttribute("collegeList", collegeList);
            model.addAttribute("qualificationList", qualificationList);
            model.addAttribute("provinceList", provinceList);
            return "admin/business/appointments/normal_business";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    /**
     * 跳转到定向/委培业务页面
     * @param model
     * @return
     * @throws SSException
     */
    @RequestMapping(value = "toWeipeiBusinessList", method = RequestMethod.GET)
    public String toWeipeiBusinessList(Model model) throws SSException{
        try {
            List<CollegeCode> collegeList = collegeService.listAll();
            List<QualificationCode> qualificationList = qualificationService.listAll();
            List<ProvinceProperty> provinceList = provinceService.listAll();

            model.addAttribute("collegeList", collegeList);
            model.addAttribute("qualificationList", qualificationList);
            model.addAttribute("provinceList", provinceList);
            return "admin/business/appointments/weipei_business";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return WebConstants.sysErrorCode;
        }
    }

    //分页获取列表
    @RequestMapping(value = "ajax/protocolList/{type}/{page}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getprotocolList(
                              @PathVariable("page") int page,
                              @PathVariable("type") String type,
                              @RequestParam(value = "stuType", required = false, defaultValue = "-1") int stuType,
                              @RequestParam(value = "sex", required = false, defaultValue = "-1") int sex,
                              @RequestParam(value = "college", required = false, defaultValue = "") String college,
                              @RequestParam(value = "major", required = false, defaultValue = "") String major,
                              @RequestParam(value = "qualification", required = false, defaultValue = "") String qualification,
                              @RequestParam(value = "normalStu", required = false, defaultValue = "-1") int normalStu,
                              @RequestParam(value = "originPlace", required = false, defaultValue = "-1") int originPlace,
                              @RequestParam(value = "stuLength", required = false, defaultValue = "-1") double stuLength,
                              @RequestParam(value = "isRegistered", required = false, defaultValue = "-1") int isRegistered,
                              @RequestParam(value = "trainingMode", required = false, defaultValue = "-1") int trainingMode,
                              @RequestParam(value = "protocolType", required = false, defaultValue = "-1") int protocolType,
                              @RequestParam(value = "checkProtocolResult", required = false, defaultValue = "-1") int checkProtocolResult,
                              @RequestParam(value = "beginTime", required = false) Date beginTime,
                              @RequestParam(value = "endTime", required = false) Date endTime,
                              @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
//        System.out.println("type = " + type);
//        System.out.println("page = " + page);
//        System.out.println("collegeCode = " + college);

        if(major.equals("-1")) {
            major = "";
        }
        if(college.equals("-1")) {
            college = "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        JSONArray jsonArray = new JSONArray();
        try {
            List<ProtocolAdminListDto> protocolAdminListDtos = Collections.emptyList();
            String majorQualification = "";

            if (!Assert.isNull(qualification) && qualification.equals("-1")) {
                qualification = null;
            }
            List<String> colleges = null;

            int count = 0;

            //如果类型是博士生领新协议
//            if(type.equals("doctorBusiness") && qualification.equals(null)) {
//
//                int count1 = protocolAdminService.countByCondition(stuType, sex, college, major, "01", normalStu,
//                        originPlace, stuLength, isRegistered, trainingMode, protocolType, checkProtocolResult,
//                        beginTime, endTime, keyword, type);
//                int count2 = protocolAdminService.countByCondition(stuType, sex, college, major, "03", normalStu,
//                        originPlace, stuLength, isRegistered, trainingMode, protocolType, checkProtocolResult,
//                        beginTime, endTime, keyword, type);
//                 count = count1 + count2;
//                Pagination pagination = new Pagination(count, pageSize, page);
//                List<ProtocolAdminListDto> protocolAdminListDtos1 = protocolAdminService.queryProtocolAdminListDtoByConditions(pagination, stuType, sex, college, major, "01", normalStu,
//                        originPlace, stuLength, isRegistered, trainingMode, protocolType, checkProtocolResult,
//                        beginTime, endTime, keyword, type);
//
//                protocolAdminListDtos = protocolAdminService.queryProtocolAdminListDtoByConditions(pagination, stuType, sex, college, major, "03", normalStu,
//                        originPlace, stuLength, isRegistered, trainingMode, protocolType, checkProtocolResult,
//                        beginTime, endTime, keyword, type);
//                protocolAdminListDtos.addAll(protocolAdminListDtos1);
//
//
//            }else {
                 count = protocolAdminService.countByCondition(stuType, sex, college, major, qualification, normalStu,
                        originPlace, stuLength, isRegistered, trainingMode, protocolType, checkProtocolResult,
                        beginTime, endTime, keyword, type);
                Pagination pagination = new Pagination(count, pageSize, page);
                protocolAdminListDtos = protocolAdminService.queryProtocolAdminListDtoByConditions(pagination, stuType, sex, college, major, qualification, normalStu,
                        originPlace, stuLength, isRegistered, trainingMode, protocolType, checkProtocolResult,
                        beginTime, endTime, keyword, type);
//            }



            int index = (page - 1) * pageSize + 1;
            for (ProtocolAdminListDto protocolAdminListDto : protocolAdminListDtos) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("index", index++);
                jsonObject.put("id", protocolAdminListDto.getId());
                jsonObject.put("protocolId", protocolAdminListDto.getProtocolId());
                jsonObject.put("name", protocolAdminListDto.getName());
                jsonObject.put("stuNumber", protocolAdminListDto.getStuNumber());
                jsonObject.put("sex", protocolAdminListDto.getSex());
                jsonObject.put("grade", protocolAdminListDto.getGrade());
                jsonObject.put("college", protocolAdminListDto.getCollege());
                jsonObject.put("major", protocolAdminListDto.getMajor());
                jsonObject.put("stuLength", protocolAdminListDto.getStuLength());
                jsonObject.put("candidateNumber", protocolAdminListDto.getCandidateNumber());
                jsonObject.put("idNumber", protocolAdminListDto.getIdNumber());
                jsonObject.put("nation", protocolAdminListDto.getNation());
                jsonObject.put("school",protocolAdminListDto.getSchool());
                if (!Assert.isNull(protocolAdminListDto.getTrainingMode())) {
                    jsonObject.put("trainingMode", protocolAdminListDto.getTrainingMode());
                } else {
                    jsonObject.put("trainingMode", "无");
                }

                if (!Assert.isNull(protocolAdminListDto.getWeipeiUnit())) {
                    jsonObject.put("weipeiUnit", protocolAdminListDto.getWeipeiUnit());
                } else {
                    jsonObject.put("weipeiUnit", "无");
                }

                if (!Assert.isNull(protocolAdminListDto.getOriginPlace())) {
                    jsonObject.put("originPlace", protocolAdminListDto.getOriginPlace());
                } else {
                    jsonObject.put("originPlace", "无");
                }

                if (!Assert.isNull(protocolAdminListDto.getPoliticalStand())) {
                    jsonObject.put("politicalStand", protocolAdminListDto.getPoliticalStand());
                } else {
                    jsonObject.put("politicalStand", "无");
                }

                if (!Assert.isNull(protocolAdminListDto.getNormalStu())) {
                    jsonObject.put("normalStu", protocolAdminListDto.getNormalStu());
                } else {
                    jsonObject.put("normalStu", "无");
                }

                if (!Assert.isNull(protocolAdminListDto.getDifficulty())) {
                    jsonObject.put("difficulty", protocolAdminListDto.getDifficulty());
                } else {
                    jsonObject.put("difficulty", "无");
                }

                if (!Assert.isNull(protocolAdminListDto.getProtocolType())) {
                    jsonObject.put("protocolType", protocolAdminListDto.getProtocolType());
                } else {
                    jsonObject.put("protocolType", "无");
                }

                if (!Assert.isNull(protocolAdminListDto.getCreateTime())) {
                    jsonObject.put("createTime", sdf.format(protocolAdminListDto.getCreateTime()));
                } else {
                    jsonObject.put("createTime", "无");
                }

                if (!Assert.isNull(protocolAdminListDto.getCheckProtocolResult())) {
                    jsonObject.put("checkProtocolResult", protocolAdminListDto.getCheckProtocolResult());
                } else {
                    jsonObject.put("checkProtocolResult", "无");
                }

                if (!Assert.isNull(protocolAdminListDto.getCheckProtocolTime())) {
                    jsonObject.put("checkProtocolTime", sdf.format(protocolAdminListDto.getCheckProtocolTime()));
                } else {
                    jsonObject.put("checkProtocolTime", "无");
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
     * 列表页进入详情页创建缓存
     *
     * @param idList
     * @param id
     * @param curNo
     * @param curPage
     * @param conditions
     * @return
     */
    @RequestMapping(value = "ajax/cache", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject inputToCache(@RequestParam(value = "idList[]") List<Integer> idList,
                                   @RequestParam(value = "id") int id,
                                   @RequestParam(value = "curNo") int curNo,
                                   @RequestParam(value = "curPage", required = false) Integer curPage,
                                   @RequestParam(value = "conditions", required = false) JSONObject conditions) {

        CacheUtil cacheUtil = new CacheUtil();

        //手动清理缓存
        System.out.println("缓存success");
        Subject subject = SecurityUtils.getSubject();
        String loginName = subject.getPrincipal().toString();
        cacheUtil.remove(loginName);
        if (Assert.isNull(curPage)) {
            curPage = 1;
        }

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

    //新增功能业务预约导出 功能
    @RequestMapping(value = "export/{type}", method = RequestMethod.POST)
    public void exportByType(@PathVariable("type") Integer type, HttpServletRequest request, HttpServletResponse response) throws SSException {
        List<ProtocolExportDto> list = Collections.emptyList();
        try{

            /*
             *      根据业务预约的类型返回 查找出来的列表
             * type : 1.申领新协议
             *        2.毕业去向变更领协议
             *        3.免费师范生业务
             *        4.定向、委培生业务
             *        5.博士领协议业务
             */
            list = protocolAdminService.exportByType(type);
            String filename = "";
            switch(type) {
                case 1:
                    filename = "申领新协议";
                    break;
                case 2:
                    filename = "毕业去向变更领协议";
                    break;
                case 3:
                    filename = "免费师范生业务";
                    break;
                case 4:
                    filename = "定向、委培业务";
                    break;
                case 5:
                    filename = "博士生领协议业务";
                    break;
                default:
                    throw SSException.get(OaException.ProtocolTypeException);
            }
            filename += ".xls";
            filename = ExcelUtil.encodeFilename(filename, request);//处理中文文件名
            ExcelUtil.writeExcel(list, "recruit", filename, response);//调用Excel工具类生成Excel
        } catch(SSException e) {
            e.printStackTrace();
            LogClerk.errLog.error(e);
        }
    }
}