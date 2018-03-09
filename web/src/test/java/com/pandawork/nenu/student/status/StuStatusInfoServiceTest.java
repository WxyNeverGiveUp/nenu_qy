package com.pandawork.nenu.student.status;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.service.student.status.StuStatusInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生学籍测试类
 *
 * @Author: chenyuting
 * @DateTime: 2017/5/3  13:57
 */
public class StuStatusInfoServiceTest extends AbstractTestCase{
    @Autowired
    StuStatusInfoService stuStatusInfoService;

    @Test
    public void deleteStudentInfo() throws SSException {
        int id = 14681;
        stuStatusInfoService.deleteStudentInfo(id);
        System.out.println("success");
    }
}