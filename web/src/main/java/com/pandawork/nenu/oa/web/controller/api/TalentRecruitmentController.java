package com.pandawork.nenu.oa.web.controller.api;
import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.dto.api.TalentRecruitmentDto;
import com.pandawork.nenu.oa.common.entity.data.MajorCode;
import com.pandawork.nenu.oa.common.entity.general.ProvinceProperty;
import com.pandawork.nenu.oa.common.util.TokenUtil;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * description：人才邀约controller
 *
 * Created by zy on 2017/8/7.
 */
@Controller
@RequestMapping(value = URLConstants.TALENT_RECRUITMENT_URL)
public class TalentRecruitmentController extends AbstractController{


    /**
     * 去列表页
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "toList", method = RequestMethod.GET)
    public JSONObject toList(){
        try{
            JSONArray array = new JSONArray();
            List<MajorCode> qualificationList = majorTypeService.listQualification();
            //专业
            for (MajorCode major: qualificationList){
                JSONObject jsonObject = new JSONObject();
                JSONArray array1 = new JSONArray();
                List<MajorCode> majorDivisionList= majorTypeService.listMajorDivisionByQualification(major.getQualification());
                int qualificationId = 0;
                if (major.getQualification().equals("本科专业")){
                    qualificationId = 1;
                }else if (major.getQualification().equals("研究生专业")){
                    qualificationId = 2;
                }
                for (MajorCode major1: majorDivisionList){
                    JSONArray array2 = new JSONArray();
                    JSONObject jsonObject1 = new JSONObject();
                    List<MajorCode> majorClassList = majorTypeService.listMajorClassByMajorDivision(major1.getMajorDivision());
                    for (MajorCode major2:majorClassList){
                        JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("majorDivisionId",major1.getMajorDivisionId());//父标志
                        jsonObject2.put("majorClassId",major2.getMajorClassId());//标志
                        jsonObject2.put("majorClass",major2.getMajorClass());
                        array2.add(jsonObject2);
                    }
                    jsonObject1.put("qualificationId",qualificationId);//父标志
                    jsonObject1.put("majorDivisionId",major1.getMajorDivisionId());//标志
                    jsonObject1.put("majorDivision",major1.getMajorDivision());
                    jsonObject1.put("majorClass",array2);
                    System.out.println("哈哈哈");
                    array1.add(jsonObject1);
                }
                jsonObject.put("qualificationId",qualificationId);//标志
                jsonObject.put("qualification",major.getQualification());
                jsonObject.put("majorDivision",array1);
                array.add(jsonObject);
            }
            //省份
            List<ProvinceProperty> provinceList = provinceService.listAll();
            for (ProvinceProperty originProvince:provinceList){
                JSONObject province = new JSONObject();
                province.put("provinceCode",originProvince.getCode());//标志
                province.put("provinceName",originProvince.getName());
                array.add(province);
            }
            return sendJsonArray(array);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }
    }


    /**
     * 人才邀约获取学生列表
     */
    @ResponseBody
    @RequestMapping(value = "ajax/list", method = RequestMethod.POST)
    public JSONObject getList(
//                              @RequestParam(value = "qualification",required = false, defaultValue = "") String qualification,
//                              @RequestParam(value = "provinceCode", required = false, defaultValue = "-1") int provinceCode,
//                              @RequestParam(value = "normalStu", required = false, defaultValue = "-1") int normalStu,
//                              @RequestParam(value = "majorClass", required = false, defaultValue = "") String majorClass
            @RequestBody JSONObject jsonObject1
    ){
        JSONArray jsonArray = new JSONArray();
        //查看接收的json信息
        System.out.println(jsonObject1);
        //System.out.println("看看专业层次qualification"+jsonObject1.getString("qualification"));
        String token = TokenUtil.getMd5Token();
        if (!token.equals(jsonObject1.getString("token"))){
            JSONObject err = new JSONObject();
            JSONObject data = new JSONObject();
            data.put("errMsg","身份验证失败");
            err.put("data",data);
            err.put("code",1);
            return err;
        }

        try{
            JSONObject jsonObject = new JSONObject();
            String qualification =jsonObject1.getJSONObject("data").getString("qualification");
            String majorClass = jsonObject1.getJSONObject("data").getString("majorClass");
            int provinceCode = jsonObject1.getJSONObject("data").getInt("provinceCode");
            int normalStu = jsonObject1.getJSONObject("data").getInt("normalStu");

            List<String> majors = majorTypeService.listMajorsByCondition(qualification,majorClass);
            List<TalentRecruitmentDto> studentList = talentRecruitmentService.listByCondition(qualification,provinceCode,normalStu,majors);
            int count = talentRecruitmentService.countByCondition(qualification,provinceCode,normalStu,majors);
            for (TalentRecruitmentDto stuList:studentList){
                jsonObject.put("stuNumber",stuList.getStuNumber());
                jsonObject.put("name",stuList.getName());
                jsonObject.put("sex",stuList.getSex());

                if (stuList.getMajorName()== null){
                    jsonObject.put("majorName","无");
                }else {
                    jsonObject.put("majorName",stuList.getMajorName());
                }

                if (stuList.getOriginPlace()== null){
                    jsonObject.put("originPlace","无");
                }else {
                    jsonObject.put("originPlace",stuList.getOriginPlace());
                }

                if (stuList.getMajorQualification()== null){
                    jsonObject.put("majorQualification","无");
                }else {
                    jsonObject.put("majorQualification",stuList.getMajorQualification());
                }

                if (stuList.getMonthlyPay()== null){
                    jsonObject.put("monthlyPay","无");
                }else {
                    jsonObject.put("monthlyPay",stuList.getMonthlyPay());
                }

                //第一就业意向省、市
                if (stuList.getPlace()==null){
                    jsonObject.put("province","无");
                    jsonObject.put("city","无");
                }else {
                    String place = stuList.getPlace();
                    String [] placeArray = place.split(",");
                    int provinceId = Integer.parseInt(placeArray[0]);
                    String province = provinceService.queryNameById(provinceId);
                    jsonObject.put("firstProvince",province);
                    int cityId = Integer.parseInt(placeArray[1]);
                    String city = cityService.queryCityById(cityId);
                    jsonObject.put("firstCity",city);
                }
                jsonArray.add(jsonObject);
            }
            return sendJsonArray(jsonArray,count);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            JSONObject err = new JSONObject();
            JSONObject data = new JSONObject();
            data.put("errMsg","查询失败"+e.getMessage());
            err.put("data",data);
            err.put("code",1);
            return err;
        }
    }
}
