package com.pandawork.nenu.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.entity.data.CollegeCode;
import com.pandawork.nenu.oa.service.general.CollegeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * CollegeServiceTest
 *
 * @author wlm
 * @date 2016/7/27 16:30
 */
public class CollegeServiceTest extends AbstractTestCase {

    @Autowired
    CollegeService collegeService;

    @Test
    public void listAll() throws SSException {
        List<CollegeCode> list = collegeService.listAll();
        for (CollegeCode collegeCode : list) {
            System.out.println(collegeCode.getId() + "  " + collegeCode.getCollegeId() + "  " + collegeCode.getCollege());
        }
    }

    @Test
    public void queryById() throws SSException{
        CollegeCode collegeCode = collegeService.queryById(15);
        System.out.println(collegeCode.getId() + "  " + collegeCode.getCollegeId() + "  " + collegeCode.getCollege());
    }

    @Test
    public void queryByCode() throws SSException{
        CollegeCode collegeCode = collegeService.queryByCode("115");
        System.out.println(collegeCode.getId() + "  " + collegeCode.getCollege());
    }
}
