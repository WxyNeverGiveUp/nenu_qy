package com.pandawork.nenu.student.status;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.entity.student.status.StuUpdateInfo;
import com.pandawork.nenu.oa.common.util.CacheUtil;
import com.pandawork.nenu.oa.common.util.LoginUtil;
import com.pandawork.nenu.oa.service.student.status.StuUpdateInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * StuUpdateInfoServiceTest
 *
 * @author wlm
 * @date 2016/7/22 11:10
 */
public class StuUpdateInfoServiceTest extends AbstractTestCase{

    @Autowired
    StuUpdateInfoService stuUpdateInfoService;

    @Test
    public void newStatusUpdate() throws SSException{
        StuUpdateInfo stuUpdateInfo = new StuUpdateInfo();
        stuUpdateInfo.setStatusInfoId(3);
        stuUpdateInfo.setBeforeUpdate("哈哈哈");
        stuUpdateInfo.setAfterUpdate("嘿嘿嘿");
        stuUpdateInfo.setUpdateType(1);
        stuUpdateInfoService.newStuUpdateInfo(stuUpdateInfo);
    }

    @Test
    public void cacheTest() throws SSException{
        CacheUtil cacheUtil = new CacheUtil();
        CacheUtil cacheUtil1 = new CacheUtil();
        System.out.println(cacheUtil == cacheUtil1);
    }

    @Test
    public void loginTest() throws SSException{
        System.out.println(LoginUtil.isUserExist("2015102791"));
        System.out.println(LoginUtil.getNameById("2015102791"));
        String group = LoginUtil.getUserGroupById("2015102791");
        System.out.println(group);
    }


}
