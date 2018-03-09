package com.pandawork.nenu.oa.web.controller.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.bean.StaticAutoWire;
import com.pandawork.nenu.oa.common.dto.data.MajorDivision;
import com.pandawork.nenu.oa.common.dto.data.MajorDto;
import com.pandawork.nenu.oa.common.dto.data.MajorMiddleDto;
import com.pandawork.nenu.oa.common.dto.history.Major;
import com.pandawork.nenu.oa.common.dto.user.UserInfoDto;
import com.pandawork.nenu.oa.common.entity.data.MajorCode;
import com.pandawork.nenu.oa.common.enums.general.MajorQualificationEnums;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * MajorController
 *
 * @author wlm
 * @date 2016/8/28 16:41
 */

@Controller
@RequestMapping(value = URLConstants.GENERAL_MAJOR)
public class MajorController extends AbstractController {

    @RequestMapping(value = "middle", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getMiddleList(@RequestParam("majorCode") String majorCode, @RequestParam("qualification") int qualification) {
        try {
            String qualificationName = MajorQualificationEnums.valueOf(qualification).getName();
            List<MajorMiddleDto> middleList = majorService.listMiddleByBigCode(majorCode, qualificationName);
            JSONArray jsonArray = new JSONArray();
            for (MajorMiddleDto majorMiddleDto : middleList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("majorCode", majorMiddleDto.getCode());
                jsonObject.put("majorName", majorMiddleDto.getName());
                jsonArray.add(jsonObject);
            }
            return sendJsonArray(jsonArray);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    @RequestMapping(value = "small", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getSmallList(@RequestParam("majorCode") String majorCode, @RequestParam("qualification") int qualification) {
        try {
            String qualificationName = MajorQualificationEnums.valueOf(qualification).getName();
            List<MajorDto> smallList = majorService.listSmallByMiddleCode(majorCode, qualificationName);
            JSONArray jsonArray = new JSONArray();
            for (MajorDto majorDto : smallList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("majorCode", majorDto.getCode());
                jsonObject.put("majorName", majorDto.getName());
                jsonArray.add(jsonObject);
            }
            if (!majorCode.equals("0000")) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("majorCode", "000000");
                jsonObject.put("majorName", "其他");
                jsonArray.add(jsonObject);
            }
            return sendJsonArray(jsonArray);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    @RequestMapping(value = "big", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getBigList(@RequestParam("qualification") int qualification) {
        try {
            String qualificationName = MajorQualificationEnums.valueOf(qualification).getName();
            List<MajorDivision> bigList = majorService.listBigAll(qualificationName);
            JSONArray jsonArray = new JSONArray();
            for (MajorDivision majorDivision : bigList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("majorCode", majorDivision.getMajorDivisionCode());
                jsonObject.put("majorName", majorDivision.getMajorDivisionName());
                jsonArray.add(jsonObject);
            }
            return sendJsonArray(jsonArray);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    //校级专业联动
    @RequestMapping(value = "ajax/all",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getCollegeMajorName(@RequestParam(value = "college",required = false,defaultValue = "-1") String college) {
        try{
            List<UserInfoDto> userInfoDtoList = majorService.queryMajorsByCollege(college);
            JSONArray jsonArray = new JSONArray();
            for (UserInfoDto userInfoDto :userInfoDtoList){
                String majorName = "";
                MajorCode majorCode = majorService.queryByCode(userInfoDto.getMajorCode(),userInfoDto.getMajorQualification());
                if (Assert.isNotNull(majorCode)){
                    majorName = majorCode.getMajorName();
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("majorCode",userInfoDto.getMajorCode());
                jsonObject.put("majorName",userInfoDto.getMajorQualification() + "-" +majorName);
                jsonArray.add(jsonObject);
            }
            JSONObject jsonObjectNull = new JSONObject();
            jsonObjectNull.put("majorCode",-2);
            jsonObjectNull.put("majorName","专业为空");
            jsonArray.add(jsonObjectNull);
            return sendJsonArray(jsonArray);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    //校级历史数据专业联动
    @RequestMapping(value = "ajax/history",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getHistoryMajorName(@RequestParam(value = "college",required = false, defaultValue = "") String college) throws SSException{
        JSONArray jsonArray = new JSONArray();
        try{
            List<Major> majorList = majorCodeService.getMajorListByCollegeCode(college);
            for (Major major:majorList){
                JSONObject jsonObject = new JSONObject();
                String majorQualification = "";
                int i = major.getMajorQualification();
                if(i == 1) {
                    majorQualification = "研究生专业";
                }else {
                    majorQualification = "本科生专业";
                }
                jsonObject.put("majorName",majorQualification+"-"+major.getMajorName());//专业层次-专业名
                jsonObject.put("majorCode",major.getMajorName());//专业名
                jsonArray.add(jsonObject);
            }
            return sendJsonArray(jsonArray);
        }catch (SSException e){
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }

    @RequestMapping(value = "ajax/list", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getMajorName(){
        JSONArray jsonArray = new JSONArray();
        try{
            List<MajorCode> majorCodeList = majorService.listAll();
            for (MajorCode majorCode:majorCodeList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("majorCode",majorCode.getMajorId());
                jsonObject.put("majorName",majorCode.getMajorName());
                jsonArray.add(jsonObject);
            }
            return sendJsonArray(jsonArray);

        }catch (SSException e){
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }
}
