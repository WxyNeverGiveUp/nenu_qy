package com.pandawork.nenu.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchQueryDto;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.service.dispatch.DispatchInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description:学生派遣service方法测试
 * Author: luowanli
 * Date: 2016/7/14
 * Time: 11:19
 */
public class DispatchInfoServiceTest extends AbstractTestCase {

    @Autowired
    DispatchInfoService dispatchInfoService;

   @Test
    public void testgetCount() throws SSException {

       DispatchQueryDto dispatchQueryDto=new DispatchQueryDto();
       dispatchQueryDto.setUserId(26);
       dispatchInfoService.getCount(dispatchQueryDto);
   }
   @Test
    public void testBatchRepulse () throws SSException {
       DispatchInfo dispatchInfo = new DispatchInfo();
       int id = 4;
       dispatchInfo.setId(id);
       dispatchInfo.setCounsellorCheckResult(1);
       dispatchInfo.setCounsellorCheckReason("");
       dispatchInfo.setCounsellorCheckTime(null);
       dispatchInfo.setCounsellorCheckOperator("");
       dispatchInfo.setDeputySecretaryCheckResult(1);
       dispatchInfo.setDeputySecretaryCheckReason("");
       dispatchInfo.setDeputySecretaryCheckTime(null);
       dispatchInfo.setDeputySecretaryCheckOperator("");
       dispatchInfo.setSchoolCheckResult(1);
       dispatchInfo.setSchoolCheckReason("");
       dispatchInfo.setSchoolCheckTime(null);
       dispatchInfo.setSchoolCheckOperator("");
       dispatchInfo.setCheckStatus(0);
       dispatchInfoService.updateDispatchInfo(dispatchInfo);
   }

   @Test
   public void testDeleteById() throws SSException {
       dispatchInfoService.deleteById(1);
   }


}
