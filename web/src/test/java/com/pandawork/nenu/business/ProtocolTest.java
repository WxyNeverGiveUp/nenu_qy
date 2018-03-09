package com.pandawork.nenu.business;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.dto.business.NewProtocolInfoDto;
import com.pandawork.nenu.oa.common.dto.business.ProtocolQueryDto;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.service.business.ProtocolService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * description:测试协议业务
 * user:qiulan
 * date:2016/7/12
 * time:16:56
 */
public class ProtocolTest extends AbstractTestCase {
    @Autowired
    ProtocolService protocolService;
//
//    @Test
//    public void testCount(){
//        List<ProtocolQueryDto> protocolQueryDtos = protocolService.listIdByCondition(-1,,,,1,1);
//    }

    //申请新协议方法测试
    @Test
    public void testNewProtocol() {
        try {
            Protocol protocol = new Protocol();
            protocol.setProtocolType(3);
            protocol.setStatusInfoId(1);
            protocolService.newProtocol(protocol);
        } catch (SSException e) {
            e.printStackTrace();
        }
    }

    //查询变更结果列表失败
    @Test
    public void testQueryCheckStatus() {
        try {
            System.out.println( protocolService.queryCheckStatus(7));
        } catch (SSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCheckProtocol() throws Exception{
        ProtocolQueryDto p1 = new ProtocolQueryDto();
        p1.setCheckProtocolResult(1);
//        p1.setName("aaa");
//        p1.setKeyWord("aaa");
        int dataCount = protocolService.countProtocolByCondition(p1);
        Pagination page1 = new Pagination(dataCount,2,9);
        List<Protocol> protocols = protocolService.listCheckProtocolByCondition(p1,page1);
     for (Protocol p : protocols){
         System.out.println(p);
     }
        System.out.println(dataCount);
        System.out.println(page1.getCurrentPage());
        System.out.println(page1.getPageCount());
        System.out.println(page1.getPageSize());
    }

    //测试申请新协议学生基本信息查询
    @Test
    public void TestNewProtocolStuInfo() throws SSException{
        NewProtocolInfoDto stuInfo;
        stuInfo = protocolService.queryNewProtocolInfo(659);
        System.out.println(stuInfo.getNormalStu());
    }

    //测试申请新协议材料证明查询
    @Test
    public void TestQueryMaterial() throws SSException{
        System.out.println(protocolService.queryMaterialById(1));
    }

    //测试申请新协议审核结果查询
    @Test
    public void TestQueryCheckResult() throws SSException{
        System.out.println(protocolService.queryCheckResult(1));
    }

    //测试修改申请新协议审核结果
    @Test
    public void TestUpdateCheckResult() throws SSException{
        Protocol protocol = new Protocol();
        protocol.setStatusInfoId(1);
        protocol.setCheckProtocolResult(3);
        protocol.setCheckProtocolReason("再测试一次");
        protocol.setCheckProtocolOperator("管理员");
        protocolService.updateCheckResult(protocol);
        System.out.println("更新成功");
    }

    //测试是否存在派遣记录
    @Test
    public void TestIsExistDispatchRecord() throws SSException{
        System.out.println(protocolService.isExistDispatchRecord(7));
    }

    //测试查询协议编号
    @Test
    public void testQueryAgreementById() throws SSException{
        System.out.println(protocolService.queryAgreementById(2));
    }

//    //测试是否已存在新增的协议编号
//    @Test
//    public void TestIsExistAgreementNumber() throws SSException{
//        int agreementNumber = 1;
//        System.out.println(protocolService.isExistAgreement(agreementNumber));
//    }

//    //测试发放新协议
//    @Test
//    public void TestNewAgreement() throws SSException{
//        DispatchInfo dispatchInfo = new DispatchInfo();
//        dispatchInfo.setStatusInfoId(1);
//        dispatchInfo.setAgreementNumber(371101);
//        dispatchInfo.setStatusInfoId(12);
//        protocolService.newAgreement(dispatchInfo);
//        System.out.println("aaa");
//    }

    //测试查询下一个学生id
    @Test
    public void nextStudent() throws SSException{
        int statusInfoId = 1;
        System.out.println(protocolService.nextStuForProtocol(statusInfoId));
    }

    //测试最大学生id
    @Test
    public void maxStudent() throws SSException{
        System.out.println(protocolService.maxStuForProtocol());
    }
}
