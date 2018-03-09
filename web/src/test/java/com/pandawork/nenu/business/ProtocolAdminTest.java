package com.pandawork.nenu.business;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.dto.business.ProtocolAdminListDto;
import com.pandawork.nenu.oa.common.dto.business.ProtocolExportDto;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import com.pandawork.nenu.oa.service.business.ProtocolAdminService;
import org.dom4j.tree.AbstractText;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by Zuosy on 2017/7/21.
 */
public class ProtocolAdminTest extends AbstractTestCase {
    @Autowired
    ProtocolAdminService protocolAdminService;

    @Test
    public void testNewProtocol() {
        try{
            Protocol protocol = new Protocol();
            protocol.setStatusInfoId(111);
            protocol.setProtocolType(2);
            protocolAdminService.newProtocol(protocol);
        }catch(SSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateCheckResultAndCheckOperator () {
        try{
            Protocol protocol = new Protocol();
            protocol.setStatusInfoId(2147483647);
            protocol.setCheckProtocolResult(2);
            protocol.setCheckProtocolOperator("Admin");
            protocolAdminService.updateCheckResultAndCheckOperator(protocol);
        }catch (SSException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateCheckReason () {
        try{
            Protocol protocol = new Protocol();
            protocol.setStatusInfoId(110);
            protocol.setCheckProtocolReason("我要去青青草原抓羊去了。");
            protocolAdminService.updateCheckReason(protocol);
        }catch(SSException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testupdateFetchPlaceAndFetchTime () {
        try{
            Protocol protocol = new Protocol();
            protocol.setStatusInfoId(110);
            //protocol.setFetchPlace("青青草原");
            //protocol.setFetchTime(new Date(117,7,21,8,45,59));
            protocolAdminService.updateFetchPlaceAndFetchTime(protocol);
        }catch(SSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testqueryProtocolAdminListDto () {
        try{
            List<ProtocolAdminListDto> list = protocolAdminService.queryProtocolAdminListDto();
            System.out.println("************************\n************************");
            for (ProtocolAdminListDto p : list) {
                System.out.println(p.toString());
            }
            System.out.println("************************\n************************");
        }catch(SSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testqueryProtocolByStatusInfoId () {
        try{
            int statusInfoId = 111;
            List<Protocol> list = protocolAdminService.queryProtocolByStatusInfoId(statusInfoId);
            System.out.println("**************************\n**************************");
            for (Protocol protocol:list) {
                System.out.println(protocol.toString());
            }
            System.out.println("**************************\n**************************");
        }catch(SSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testqueryLastProtocolByStatusInfoId() {
        try {
            int id = 0;
            id = protocolAdminService.queryLastProtocolByStatusInfoId(111);
            System.out.print(id);
            System.out.print(id);
            System.out.println(id);

        }catch(SSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testqueryByProtocolId() {
        try {
            Protocol protocol = protocolAdminService.queryByProtocolId(1);
            System.out.println("**************************************");
            System.out.println(protocol.toString());
            System.out.println("**************************************");
        } catch(SSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testcountByStatusInfoId() {
        try{
            int counter = 0;
            counter = protocolAdminService.countByStatusInfoId(111);
            System.out.println(counter);
        }catch (SSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testqueryProtocolAdminListDtoById() {
        try{
            ProtocolAdminListDto p = protocolAdminService.queryProtocolAdminListDtoById(1);
            System.out.println("**************************************");
            System.out.println(p.toString());
            System.out.println("**************************************");
        }catch(SSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testqueryProtocolAdminListDtoByStatusInfoId() {
        try{
            List<ProtocolAdminListDto> list = protocolAdminService.queryProtocolAdminListDtoByStatusInfoId(110);
            System.out.println("**************************************");
            for(ProtocolAdminListDto p : list ) {
                System.out.println(p.toString());
            }
            System.out.println("**************************************");
        }catch(SSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testqueryProtocolAdminListDtoByConditions() {
        /*try{
            //有待测试
            List<ProtocolAdminListDto> list = protocolAdminService.queryProtocolAdminListDtoByConditions(
                    "","","","",0,0,
                    "",0,0,"",0,0,0,0);
            System.out.println("**************************************");
            for(ProtocolAdminListDto p : list) {
                System.out.println(p.toString());
            }
            System.out.println("**************************************");
        }catch(SSException e) {
            e.printStackTrace();
        }
        */
    }

    @Test
    public void testcountByCondition() {
       /* try{
            int counter = 0;
            counter = protocolAdminService.countByCondition( "","","","",0,0,
                    "",0,0,"",0,0,0,0);
            System.out.println("**************************************");
            System.out.println(counter);
            System.out.println("**************************************");
        }catch (SSException e) {
            e.printStackTrace();
        }*/
    }

    @Test
    public void testqueryAgreementNumberExist() {
        try{
            boolean exist = false;
            String agreementNumber = "10000001";
            exist = protocolAdminService.queryAgreementNumberExist(agreementNumber);
            if(exist){
                System.out.println("编号已存在");
            }else {
                System.out.println("不存在的");
            }
        }catch (SSException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void testExportByType() {
//        try{
//            List<ProtocolExportDto> list = protocolAdminService.exportByType(1);
//            for(ProtocolExportDto p : list) {
//                System.out.print(p.getReason() + " " + p.getProtocolType());
//            }
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
}
