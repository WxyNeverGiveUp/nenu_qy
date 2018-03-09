package com.pandawork.nenu.business;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.dto.business.ChangeInfoDto;
import com.pandawork.nenu.oa.common.dto.business.ChangeQueryDto;
import com.pandawork.nenu.oa.common.entity.business.Change;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.service.business.ChangeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * description:测试变更业务
 * user:qiulan
 * date:2016/7/12
 * time:16:56
 */
public class ChangeTest extends AbstractTestCase {
    @Autowired
    ChangeService changeService;

    @Test
    public void testNewIdentity() {
        try {
            Change change = new Change();
            change.setChangeType(2);
            change.setStatusInfoId(1);
            changeService.newIdentity(change);
        } catch(SSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryCheckStatus() {
        try {
            System.out.println( changeService.queryCheckStatus(7));
        } catch (SSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testfenye() throws Exception{
        ChangeQueryDto c = new ChangeQueryDto();
//        c.setKeyWord("aaa");
//        c.setCheckChangeResult(2);
        int datacount = changeService.countChangeByCondition(c);
        Pagination page = new Pagination(datacount,6,9);
        List<Change> change = changeService.listCheckChangeByCondition(c,page);
        for (Change cc: change){
            System.out.println(cc);
        }
        System.out.println(datacount);
        System.out.println(page.getPageSize());
//        System.out.println(page.getPageList());
        System.out.println(page.getPageCount());
//        System.out.println(page.calculatePageCount());
    }

    //测试变更业务学生基本信息查询
    @Test
    public void TestAlterStuInfo() throws SSException{
        ChangeInfoDto stuInfo;
        stuInfo = changeService.queryAlterInfo(2);
        //System.out.println(stuInfo.getCollege());
       //System.out.println(stuInfo.getChangeType());
        System.out.println(stuInfo.getIntoProvinceName());
    }

    //测试变更业务材料证明查询
    @Test
    public void TestQueryMaterial() throws SSException{
        System.out.println(changeService.queryMaterialById(1));
    }

    //测试查询变更业务审核结果
    @Test
    public void TestQueryCheckResult() throws SSException{
        System.out.println(changeService.queryCheckResult(1));
    }

    //测试修改变更业务审核结果
    @Test
    public void TestUpdateCheckResult() throws SSException{
        Change change = new Change();
        change.setCheckChangeResult(2);
        change.setCheckChangeReason("99");
        change.setStatusInfoId(1);
        change.setCheckChangeOperator("管理员");
        changeService.updateCheckResult(change);
        System.out.println("更新成功");
    }


    //测试发放变更业务新协议
    @Test
    public void TestNewAgreement() throws SSException{
        DispatchInfo dispatchInfo = new DispatchInfo();
        dispatchInfo.setAgreementNumber("371101");
        dispatchInfo.setStatusInfoId(2);
        changeService.newAgreement(dispatchInfo);
        System.out.println("aaa");
    }

    @Test
    public void test() throws SSException{
//        String var2 = ChangeTypeEnums.valueOf(1).getName();
//        System.out.println(var2);
    }

    //测试查询业务变更所有符合条件学生id
    @Test
    public void allStudent() throws SSException{
        List<ChangeInfoDto> student = changeService.allStudentForChange();
        for (ChangeInfoDto nextStudentDto : student) {
            System.out.println(nextStudentDto.getStatusInfoId());
        }
    }

}
