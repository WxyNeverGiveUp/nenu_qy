package com.pandawork.nenu.business;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import com.pandawork.nenu.oa.common.enums.general.CheckResultEnums;
import com.pandawork.nenu.oa.service.business.ProtocolStuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ProtocolService测试
 *
 * @author zhaiaxin
 * @time: 2017/7/20 9:30.
 */
public class ProtocolStuServiceTest extends AbstractTestCase {

    @Autowired
    ProtocolStuService protocolStuService;

//    @Test
//    public void testNewBusiness() throws SSException {
//        Protocol protocol = new Protocol();
//        protocol.setProtocolType(1);
//        protocol.setStatusInfoId(2015012015);
//        protocolStuService.newBusiness(protocol);
//    }

    @Test
    public void testListBusiness() throws SSException {
        System.out.println(protocolStuService.queryProvince("61"));
    }



    @Test
    public void testUpdateBusiness() throws SSException{
        Protocol protocol = new Protocol();
        protocol.setId(1);
        protocol.setProtocolType(1);
        protocol.setCheckProtocolReason("test");
        protocol.setStatusInfoId(2015012015);
        protocolStuService.updateBusiness(protocol);
    }
}
