package com.pandawork.nenu.api;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.entity.data.MajorCode;
import com.pandawork.nenu.oa.service.api.MajorTypeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7.
 */
public class MajorTypeServiceTest extends AbstractTestCase{

    @Autowired
    MajorTypeService majorTypeService;

    @Test
    public void testQueryMajor() throws SSException{
         List<MajorCode> qualificationList = majorTypeService.listQualification();
         for (MajorCode majorQ:qualificationList){
             System.out.println(majorQ);
         }
         List<MajorCode> majorDivision = majorTypeService.listMajorDivisionByQualification("本科专业");
        System.out.println(majorDivision);
    }

    @Test
    public  void testQueryMajorsByCondition() throws SSException{
        List<String> majorList = majorTypeService.listMajorsByCondition("本科专业","哲学类");
        System.out.println(majorList);
    }
}
