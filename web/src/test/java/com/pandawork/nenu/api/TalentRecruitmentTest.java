package com.pandawork.nenu.api;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.dto.api.TalentRecruitmentDto;
import com.pandawork.nenu.oa.service.api.MajorTypeService;
import com.pandawork.nenu.oa.service.api.TalentRecruitmentService;
import com.pandawork.nenu.oa.service.general.CityService;
import com.pandawork.nenu.oa.service.general.ProvinceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zy on 2017/8/10.
 */
public class TalentRecruitmentTest extends AbstractTestCase {

    @Autowired
    TalentRecruitmentService talentRecruitmentService;
    @Autowired
    MajorTypeService majorTypeService;

    @Autowired
    ProvinceService provinceService;

    @Autowired
    CityService cityService;


    @Test
    public void testListByCondition() throws SSException{
         List<String> majorList = majorTypeService.listMajorsByCondition("本科专业","计算机类");
         List<TalentRecruitmentDto> stuList = talentRecruitmentService.listByCondition("",-1,-1,majorList);
         for (TalentRecruitmentDto t:stuList){
             System.out.println(t.getName());
             System.out.println(t.getStuNumber());
         }
         int count = talentRecruitmentService.countByCondition("",-1,-1,majorList);
        System.out.println(count);
     }

    @Test
    public void testFirstPlace() throws SSException{
         String provinceName= provinceService.queryNameById(22);
         System.out.println(provinceName);

         String cityName = cityService.queryCityById(73);
         System.out.println(cityName);
     }
}
