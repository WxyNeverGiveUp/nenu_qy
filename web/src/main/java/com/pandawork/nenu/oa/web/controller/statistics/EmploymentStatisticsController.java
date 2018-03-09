package com.pandawork.nenu.oa.web.controller.statistics;

import com.pandawork.nenu.oa.common.dto.history.College;
import com.pandawork.nenu.oa.common.dto.statistics.EmploymentStatisticsInfoDto;
import com.pandawork.nenu.oa.common.util.URLConstants;
import com.pandawork.nenu.oa.web.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 实时就业率统计Controller
 * @author Lw
 * @time 2017/8/9 16:20
 */
@Controller
@RequestMapping(value = URLConstants.ADMIN_STATISTICS_URL)
public class EmploymentStatisticsController extends AbstractController {


    @RequestMapping(value = "statistics_type", method = RequestMethod.POST)
    public String chooseMethod(@RequestParam("statisticsType") int statisticsType) {
        if(statisticsType == 0 || statisticsType == 1 || statisticsType == 2 || statisticsType == 6 || statisticsType == 7) {
            return "redirect:qualification/" + statisticsType;
        } else if(statisticsType == 3 || statisticsType == 4 || statisticsType == 5) {
            return "redirect:normal/" + statisticsType;
        }
        return "500";
    }

    /**
     * 获取就业率相关信息
     * 和毕业学历有关
     * 并跳往就业率页面
     *
     * @param qualificationCode
     * @return
     */
    @RequestMapping(value = "qualification/{qualificationCode}", method = RequestMethod.GET)
    public String getInfoAboutQualification(@PathVariable("qualificationCode") int qualificationCode, Model model) {
        try {
            //获取session中的graduateYear
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            int year = (int) ((ServletRequestAttributes) ra).getRequest().getSession(true).getAttribute("graduateYear");
            List<EmploymentStatisticsInfoDto> employmentStatisticsInfoDtoList = new ArrayList<>();

            List<College> collegeList = new ArrayList<>();
            if (qualificationCode == 1) {
                //本科生
                collegeList = collegeListService.getUnderCollegeList();
            } else if (qualificationCode == 2 || qualificationCode == 0 || qualificationCode == 6 || qualificationCode == 7) {
                //研究生或获取全部
                collegeList = collegeListService.getPostCollegeList();
            }

            int index = 1;

            for (College college : collegeList) {

                //获取该学院的相关信息
                EmploymentStatisticsInfoDto employmentStatisticsInfoDto = new EmploymentStatisticsInfoDto();
                employmentStatisticsInfoDto = employmentStatisticsService.getEmploymentStatisticsInfoAboutQualification(college.getCollegeCode(), year, qualificationCode);
                employmentStatisticsInfoDto.setCollege(college.getCollegeName());
                employmentStatisticsInfoDtoList.add(employmentStatisticsInfoDto);

            }

            Collections.sort(employmentStatisticsInfoDtoList);
            for(EmploymentStatisticsInfoDto employmentStatisticsInfoDto : employmentStatisticsInfoDtoList) {
                employmentStatisticsInfoDto.setId(index++);
            }

            model.addAttribute("employmentStatisticsInfo", employmentStatisticsInfoDtoList);

            //获取总计信息
            EmploymentStatisticsInfoDto allInfo = employmentStatisticsService.getAllInfo(employmentStatisticsInfoDtoList);

            model.addAttribute("code", qualificationCode);
            model.addAttribute("allInfo", allInfo);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "admin/history/employment_statistics";
    }

    @RequestMapping(value = "normal/{normalType}", method = RequestMethod.GET)
    public String getInfoAboutNormalType(@PathVariable("normalType") int normalType, Model model) {

        //获取session中的graduateYear
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        int year = (int) ((ServletRequestAttributes) ra).getRequest().getSession(true).getAttribute("graduateYear");
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

        for(EmploymentStatisticsInfoDto employmentStatisticsInfoDto : employmentStatisticsInfoDtoList) {
            employmentStatisticsInfoDto.setId(index++);
        }
        model.addAttribute("employmentStatisticsInfo", employmentStatisticsInfoDtoList);

        //获取总计信息
        EmploymentStatisticsInfoDto allInfo = employmentStatisticsService.getAllInfo(employmentStatisticsInfoDtoList);

        model.addAttribute("code", normalType);
        model.addAttribute("allInfo", allInfo);

        return "admin/history/employment_statistics";

    }

}


