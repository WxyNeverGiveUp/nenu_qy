package com.pandawork.nenu.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchRemarksDto;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.service.dispatch.StuDispatchService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:学生派遣service方法测试
 * Author: luowanli
 * Date: 2016/7/14
 * Time: 11:19
 */
public class StuDispatchServiceTest extends AbstractTestCase {

    @Autowired
    StuDispatchService stuDispatchService;

    @Test
    public void testNewDispatch() throws SSException {
        DispatchInfo dispatchInfo = new DispatchInfo();
        dispatchInfo.setStatusInfoId(666);
        dispatchInfo.setExamNumber(123);
        stuDispatchService.newDispatch(dispatchInfo);
    }

    @Test
    public void testUpdateDispatch() throws SSException {
        DispatchInfo dispatchInfo = new DispatchInfo();
        dispatchInfo.setId(1);
        dispatchInfo.setStatusInfoId(66);
        dispatchInfo.setExamNumber(666);
        stuDispatchService.updateDispatch(dispatchInfo);
    }

    @Test
    public void testQueryDispatchByStuId() throws SSException {
        DispatchInfo dispatchInfo = new DispatchInfo();
        dispatchInfo.setStatusInfoId(12);
        System.out.println(stuDispatchService.queryDispatchByStuId(dispatchInfo.getStatusInfoId()));
    }

    @Test
    public void testQueryRemarksById() throws SSException {
        DispatchRemarksDto dispatchRemarksDto = new DispatchRemarksDto();
        dispatchRemarksDto.setStatusInfoId(1);
        System.out.println(stuDispatchService.queryRemarksByStuId(dispatchRemarksDto.getStatusInfoId()));
    }
}
