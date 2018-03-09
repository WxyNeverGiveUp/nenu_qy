package com.pandawork.nenu.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchExtendItem;
import com.pandawork.nenu.oa.service.dispatch.DispatchExtendItemService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 管理员派遣扩展项Service测试
 * Created by ZhangBiLai on 2017/7/19.
 */
public class DispatchExtendItemServiceTest extends AbstractTestCase {

    @Autowired
    DispatchExtendItemService dispatchExtendItemService;

    @Test
    public void testNewDispatchExtendItem () throws SSException {
        DispatchExtendItem dispatchExtendItem1 = new DispatchExtendItem();
        dispatchExtendItem1.setDispatchId(111);
//        dispatchExtendItem1.setOrientationCancelContract(1);
//        dispatchExtendItemService.newDispatchExtendItem(dispatchExtendItem1);
    }

    @Test
    public void testQueryByDispatchId () throws SSException {
        System.out.println(dispatchExtendItemService.queryByDispatchId(111));
    }

    @Test
    public void testUpdateById () throws SSException {
        DispatchExtendItem dispatchExtendItem1 = new DispatchExtendItem();
        dispatchExtendItem1.setId(2);
        dispatchExtendItem1.setDispatchId(555);
//        dispatchExtendItem1.setOrientationCancelContract(0);
//        dispatchExtendItemService.updateById(dispatchExtendItem1);
    }

}
