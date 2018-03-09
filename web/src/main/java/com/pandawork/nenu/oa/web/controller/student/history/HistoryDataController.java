package com.pandawork.nenu.oa.web.controller.student.history;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.oa.common.dto.history.Major;
import com.pandawork.nenu.oa.common.dto.history.StuHistoryDataDto;
import com.pandawork.nenu.oa.common.entity.data.CollegeCode;
import com.pandawork.nenu.oa.common.entity.general.ProvinceProperty;
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

import java.util.List;

/**
 * Created by zy on 2017/7/31.
 */
@Controller
@RequestMapping(value = URLConstants.STUDENT_HISTORY_URL)
public class HistoryDataController extends AbstractController {

    /**
     * 去历史数据列表页
     * @param model
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String toList(Model model){
        try{
            List<CollegeCode> collegeList = collegeService.listAll();
            List<ProvinceProperty> provinceList = provinceService.listAll();//省(生源所在省、单位所在省)
            //获取学生所在学院的专业
            Subject subject = SecurityUtils.getSubject();
            String studentNumber = subject.getPrincipal().toString();
            List<String> colleges = null;
            colleges = stuStatusInfoService.listCollegesByStuNumber(studentNumber);
            if (Assert.isEmpty(colleges) || colleges.size() <= 0) {
                colleges.add("0");
            }
                String collegeCode = colleges.get(0);
                List<Major> majorList = majorCodeService.getMajorListByCollegeCode(collegeCode);
                for (Major major:majorList){
                    String majorQualification = "";
                    int i = major.getMajorQualification();
                    if (i == 1){
                        majorQualification = "研究生专业";
                    }else {
                        majorQualification = "本科生专业";
                    }
                    major.setMajorCode(major.getMajorName());
                    major.setMajorName(majorQualification+"-"+major.getMajorName());
                }
                model.addAttribute("majorList",majorList);

            model.addAttribute("provinceList",provinceList);
            model.addAttribute("collegeList",collegeList);
            return "student/history/list";
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            sendErrMsg(e.getMessage());
            model.addAttribute("errMsg",e.getMessage());
            return WebConstants.sysErrorCode;
        }

    }

    /**
     * 分页获取学生历史数据列表
     * @param page
     * @param year
     * @param messenger
     * @param sex
     * @param majorName
     * @param originPlace
     * @param whereAboutGo
     * @param unitProvince
     * @param unitProperty
     * @param unitIndustry
     * @param college
     * @param majorQualification
     * @return
     */
    @RequestMapping(value = "ajax/list/{page}",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getList(
                              @PathVariable("page") int page,
                              @RequestParam(value = "year", required = false, defaultValue = "") String year,
                              @RequestParam(value = "messenger", required = false, defaultValue = "-1") int messenger,
                              @RequestParam(value = "sex", required = false, defaultValue = "-1") int sex,
                              @RequestParam(value = "majorName", required = false, defaultValue = "") String majorName,
                              @RequestParam(value = "originPlace", required = false, defaultValue = "") String originPlace,
                              @RequestParam(value = "whereAboutGo", required = false, defaultValue = "") String whereAboutGo,
                              @RequestParam(value = "unitProvince", required = false, defaultValue = "") String unitProvince,
                              @RequestParam(value = "unitProperty", required = false, defaultValue = "") String unitProperty,
                              @RequestParam(value = "unitIndustry", required = false, defaultValue = "") String unitIndustry,
                              @RequestParam(value = "college",required = false, defaultValue = "")String college,
                              @RequestParam(value = "majorQualification",required = false, defaultValue = "")String majorQualification) {
        JSONArray jsonArray =new JSONArray();
        try {
            //学生端只显示所属学院信息
            Subject subject = SecurityUtils.getSubject();
            String studentNumber = subject.getPrincipal().toString();
            List<String> colleges = null;
            colleges = stuStatusInfoService.listCollegesByStuNumber(studentNumber);
            if (Assert.isEmpty(colleges) || colleges.size() <= 0){
                    colleges.add("0");
                }
            int count = stuHistoryDataService.countByCondition(year, messenger, sex, majorName, originPlace, whereAboutGo, unitProvince, unitProperty, unitIndustry,college,colleges,majorQualification);
            Pagination pagination = new Pagination(count, pageSize, page);
            List<StuHistoryDataDto> historyDataList = stuHistoryDataService.listStuHistoryDataByCondition(year,messenger,sex, majorName, originPlace,  whereAboutGo, unitProvince,  unitProperty,  unitIndustry,college,colleges,majorQualification,
                    pagination);
            int index = (page - 1) * pageSize + 1;
            for (StuHistoryDataDto stuHistoryDataDto:historyDataList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("index",index++);
                jsonObject.put("id",stuHistoryDataDto.getId());
                jsonObject.put("year",stuHistoryDataDto.getYear());
                //非东师信使时：只显示姓，不显示名字
                String name = stuHistoryDataDto.getName();
                if (stuHistoryDataDto.getMessenger().equals("是")){
                    jsonObject.put("name",name);
                } else {
                    name = name.substring(0, 1); //从0开始，1结束。即取姓氏
                    name += "XX";
                    jsonObject.put("name", name);
                }
                jsonObject.put("messenger",stuHistoryDataDto.getMessenger());

                jsonObject.put("sex",stuHistoryDataDto.getSex());

                if (stuHistoryDataDto.getNation()==null){
                    jsonObject.put("nation","无");
                }else {
                    jsonObject.put("nation",stuHistoryDataDto.getNation());
                }

                if (stuHistoryDataDto.getMajorName()==null){
                    jsonObject.put("majorName","无");
                }else {
                    jsonObject.put("majorName",stuHistoryDataDto.getMajorName());
                }

                if (stuHistoryDataDto.getOriginPlace()==null){
                    jsonObject.put("originPlace","无");
                }else {
                    jsonObject.put("originPlace",stuHistoryDataDto.getOriginPlace());
                }

                if (stuHistoryDataDto.getWhereAboutGo()==null){
                    jsonObject.put("whereAboutGo","无");
                }else {
                    jsonObject.put("whereAboutGo",stuHistoryDataDto.getWhereAboutGo());
                }

                if (stuHistoryDataDto.getUnitName()==null){
                    jsonObject.put("unitName","无");
                }else {
                    jsonObject.put("unitName",stuHistoryDataDto.getUnitName());
                }

                if (stuHistoryDataDto.getUnitProvince()==null){
                    jsonObject.put("unitProvince","无");
                }else {
                    jsonObject.put("unitProvince",stuHistoryDataDto.getUnitProvince());
                }

                String unitCity = stuHistoryDataDto.getUnitCity();
                if (unitCity==null||unitCity.equals("上海")||unitCity.equals("天津")||unitCity.equals("北京")||unitCity.equals("重庆")){
                    jsonObject.put("unitCity","");
                }else {
                    jsonObject.put("unitCity",stuHistoryDataDto.getUnitCity());
                }

                if (stuHistoryDataDto.getUnitProperty()==null){
                    jsonObject.put("unitProperty","无");
                }else {
                    jsonObject.put("unitProperty",stuHistoryDataDto.getUnitProperty());
                }

                if (stuHistoryDataDto.getUnitIndustry()==null){
                    jsonObject.put("unitIndustry","无");
                }else {
                    jsonObject.put("unitIndustry",stuHistoryDataDto.getUnitIndustry());
                }

                if (stuHistoryDataDto.getCollege()==null){
                    jsonObject.put("college","无");
                }else {
                    jsonObject.put("college",stuHistoryDataDto.getCollege());
                }

                if (stuHistoryDataDto.getMajorQualification()==null){
                    jsonObject.put("majorQualification","无");
                }else {
                    jsonObject.put("majorQualification",stuHistoryDataDto.getMajorQualification());
                }

                if (stuHistoryDataDto.getReportMode()==null){
                    jsonObject.put("reportMode","无");
                }else {
                    jsonObject.put("reportMode",stuHistoryDataDto.getReportMode());
                }

                if (stuHistoryDataDto.getPoliticalStand()==null){
                    jsonObject.put("politicalStand","无");
                }else {
                    jsonObject.put("politicalStand",stuHistoryDataDto.getPoliticalStand());
                }

                if (stuHistoryDataDto.getNormalStu()==null){
                    jsonObject.put("normalStu","无");
                }else {
                    jsonObject.put("normalStu",stuHistoryDataDto.getNormalStu());
                }

                if (stuHistoryDataDto.getDifficultyMode()==null){
                    jsonObject.put("difficultyMode","无");
                }else {
                    jsonObject.put("difficultyMode",stuHistoryDataDto.getDifficultyMode());
                }

                if (stuHistoryDataDto.getCellphone()==null){
                    jsonObject.put("cellphone","无");
                }else {
                    jsonObject.put("cellphone",stuHistoryDataDto.getCellphone());
                }
                jsonArray.add(jsonObject);
            }
            return sendJsonArray(jsonArray,count);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
            return sendErrMsgAndErrCode(e);
        }

    }



}
