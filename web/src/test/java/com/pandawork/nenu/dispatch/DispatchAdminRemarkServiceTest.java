package com.pandawork.nenu.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchAdminRemark;
import com.pandawork.nenu.oa.service.dispatch.DispatchAdminRemarkService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员备注派遣信息Service方法测试
 * Created by ZhangBiLai on 2017/7/19.
 */
public class DispatchAdminRemarkServiceTest extends AbstractTestCase {

    @Autowired
    DispatchAdminRemarkService dispatchAdminRemarkService;


    @Test
    public void testNewDispatch() throws SSException{
        List<DispatchAdminRemark> dispatchAdminRemarkList = new ArrayList<>();
        DispatchAdminRemark dispatchAdminRemark1 = new DispatchAdminRemark();
        dispatchAdminRemark1.setDispatchId(123);
        dispatchAdminRemark1.setRemark("hhh");
        dispatchAdminRemarkList.add(dispatchAdminRemark1);
        dispatchAdminRemarkService.newOrUpdateRemarks(dispatchAdminRemarkList);
    }

    @Test
    public void testQueryByDispatchId () throws SSException {

        System.out.println(dispatchAdminRemarkService.queryByDispatchId(123));
    }

    @Test
    public void testDeleteByDispatchId () throws SSException {
        List<DispatchAdminRemark> dispatchAdminRemarkList = new ArrayList<>();
        DispatchAdminRemark dispatchAdminRemark1 = new DispatchAdminRemark();
        dispatchAdminRemark1.setDispatchId(888);
        dispatchAdminRemark1.setRemark("aaa");
        dispatchAdminRemarkList.add(dispatchAdminRemark1);
        dispatchAdminRemarkService.newOrUpdateRemarks(dispatchAdminRemarkList);
        dispatchAdminRemarkService.deleteById(dispatchAdminRemark1);
    }

    @Test
    public void testUpdateByDispatchId () throws SSException {
        DispatchAdminRemark dispatchAdminRemark1 = new DispatchAdminRemark();
        dispatchAdminRemark1.setId(6);
        dispatchAdminRemark1.setDispatchId(555);
        dispatchAdminRemark1.setRemark("CCC");
        dispatchAdminRemarkService.queryByDispatchId(6);
    }



}
