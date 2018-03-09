package com.pandawork.nenu.oa.web.controller.student.intentionSurvey;


import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.nenu.oa.common.entity.general.CityProperty;
import com.pandawork.nenu.oa.common.entity.general.ProvinceProperty;
import com.pandawork.nenu.oa.common.entity.student.intentionSurvey.IntentionSurvey;
import com.pandawork.nenu.oa.common.entity.student.status.StuStatusInfo;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * IntentionSurveyController
 * 就业意向调查controller
 *
 * @author chenwy
 * @date 2017/5/13 11:01
 */
@Controller
@RequestMapping(value = URLConstants.STUDENT_INTENTIONSURVEY_URL)
public class IntentionSurveyController extends AbstractController{

    /**
     * 进入就业意向调查填写页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String IntentionSurvey(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes){
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();
        try{
            StuStatusInfo stuStatusInfo = stuStatusInfoService.queryByStuNumber(username);
            //若就业调查已填写，直接跳转到详情页
            if(stuStatusInfo.getIntentionSurveyStatus() == 1){
                redirectAttributes.addFlashAttribute("notSave", true);
                return "redirect:/" +  URLConstants.STUDENT_INTENTIONSURVEY_URL + "/detail";
            }else{
                List<ProvinceProperty> provinces = provinceService.listAll();
                model.addAttribute("statusInfoId",stuStatusInfo.getId());
                model.addAttribute("provinces",provinces);
                return "student/intentionSurvey/new";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "500";
        }
    }

    /**
     * 就业意向调查信息保存
     *
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newIntentionSurvey(IntentionSurvey intentionSurvey, Model model, RedirectAttributes redirectAttributes){

        try{
            intentionSurveyService.newIntentionSurvey(intentionSurvey);
            int intentionSurveyStatus = 1;//设置填写就业意向调查状态为1-已填写
            stuStatusInfoService.updateIntentionSurveyStatus(Integer.parseInt(intentionSurvey.getStatusInfoId()),intentionSurveyStatus);
        }catch (SSException e) {
            e.printStackTrace();
            return "500";
        }
        //返回就业意向调查信息详情页
        redirectAttributes.addFlashAttribute("isSave", true);
        return "redirect:/"  + URLConstants.STUDENT_INTENTIONSURVEY_URL + "/detail";
    }

    /**
     * 就业意向调查信息更新
     *
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateIntentionSurvey(IntentionSurvey intentionSurvey, Model model, RedirectAttributes redirectAttributes){

        try{
            if(intentionSurvey.getPk6() ==  null){
                intentionSurvey.setPk6("");
            }
            if(intentionSurvey.getPk7() ==  null){
                intentionSurvey.setPk7("");
            }
            if(intentionSurvey.getPk8() ==  null){
                intentionSurvey.setPk8("");
            }
            if(intentionSurvey.getPk9() ==  null){
                intentionSurvey.setPk9("");
            }
            intentionSurveyService.updateIntentionSurvey(intentionSurvey);
            int intentionSurveyStatus = 1;//设置填写就业意向调查状态为1-已填写
            stuStatusInfoService.updateIntentionSurveyStatus(Integer.parseInt(intentionSurvey.getStatusInfoId()),intentionSurveyStatus);
        }catch (SSException e) {
            e.printStackTrace();
            return "500";
        }
        //返回就业意向调查信息详情页
        redirectAttributes.addFlashAttribute("isSave", true);
        return "redirect:/"  + URLConstants.STUDENT_INTENTIONSURVEY_URL + "/detail";
    }

    /**
     * 进入就业意向调查详情页
     *
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public String IntentionSurveyDetail(HttpServletRequest request,Model model, RedirectAttributes redirectAttributes){
        IntentionSurvey intentionSurvey = new IntentionSurvey();
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();
        try{
            StuStatusInfo stuStatusInfo = stuStatusInfoService.queryByStuNumber(username);
            intentionSurvey = intentionSurveyService.getIntentionSurvey(stuStatusInfo.getId());
            List<ProvinceProperty> provinces = provinceService.listAll();
            List<CityProperty> cities = cityService.listAll();
            model.addAttribute("provinces",provinces);
            model.addAttribute("cities",cities);
            model.addAttribute("statusInfoId",stuStatusInfo.getId());
            model.addAttribute("intentionSurvey",intentionSurvey);
            List<String> pk3List = StrTransToListStr(intentionSurvey.getPk3());
            List<String> pk4List = StrTransToListStr(intentionSurvey.getPk4());
            List<String> pk7List = StrTransToListStr(intentionSurvey.getPk7());
            List<String> pk9List = StrTransToListStr(intentionSurvey.getPk9());
            List<String> pk10List = StrTransToListStr(intentionSurvey.getPk10());
            model.addAttribute("pk3List",pk3List);
            model.addAttribute("pk4List",pk4List);
            model.addAttribute("pk7List",pk7List);
            model.addAttribute("pk9List",pk9List);
            model.addAttribute("pk10List",pk10List);
            model.addAttribute("notSave", true);
        }catch (Exception e){
            e.printStackTrace();
            return "500";
        }
        return "student/intentionSurvey/detail";
    }

    /**
     * 根据省份id获取该省的市列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/ajax/getCities", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getCities(HttpServletRequest request){
        int provinceId = Integer.parseInt(request.getParameter("provinceId"));
        List<CityProperty> cityProperties = Collections.emptyList();
        JSONObject result = new JSONObject();
        int code = 0;
        String errMsg = "";
        try {
            if(Assert.isNotNull(provinceId)){
                cityProperties = cityService.listByProvinceId(provinceId);
                code = 1;
            }
        }catch (Exception e){
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            errMsg = "error";
        }
        result.put("code",code);
        result.put("data",cityProperties);
        result.put("errMsg",errMsg);
        return result;
    }

    /**
     * 用“，”分割的String类型转换为List<String>类型
     *
     * @param str
     * @return
     * @throws Exception
     */
    private List<String> StrTransToListStr(String str) throws Exception{
        List<String> stringList = Collections.emptyList();
        try{
            if(Assert.isNotNull(str)){
                stringList = Arrays.asList(str.split(","));
            }
        }catch (Exception e){
            throw new Exception(e);
        }
        return stringList;
    }

}
