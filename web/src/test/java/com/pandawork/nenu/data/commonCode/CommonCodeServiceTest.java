package com.pandawork.nenu.data.commonCode;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.dto.data.Province;
import com.pandawork.nenu.oa.common.entity.data.MajorCode;
import com.pandawork.nenu.oa.common.entity.data.StudentInfomation;
import com.pandawork.nenu.oa.common.entity.data.TrainingModeCode;
import com.pandawork.nenu.oa.service.data.CommonCodeService;
import com.pandawork.nenu.oa.service.data.StudentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Hao Zhang on 2015/4/13.
 */
public class CommonCodeServiceTest extends AbstractTestCase {
    @Autowired
    CommonCodeService commonCodeService;
    @Autowired
    StudentService studentService;

    @Test
    public void testAddStudent() throws SSException {
        StudentInfomation student = new StudentInfomation();
        student.setName("zhanghao");
        List<Province> provinces = commonCodeService.getProvinceList();
        List<TrainingModeCode> trainingModeCodes = commonCodeService.getTrainingModeCode();
        for (Province province : provinces) {
            System.out.println(province.getProvinceName());
        }
    }
    @Test
    public void testlistMajor() throws SSException {
        List<MajorCode> majors = studentService.getMajorByCollegeName("政法学院", "本科");
    }
}
