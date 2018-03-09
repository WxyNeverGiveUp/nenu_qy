package com.pandawork.nenu.data;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.service.data.VerifyInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hao Zhang on 2015/5/12.
 */
public class VerifyTest extends AbstractTestCase {
    @Autowired
    VerifyInfoService verifyInfoService;
    @Test
    public void TestGetAllColleges(){
        List<String> colleges = verifyInfoService.getAllColleges();
        for (String a : colleges){
            System.out.println(a);
        }
    }
//    @Test
//    public void TestNumsOfGraduation(){
//
//        HashMap<String, String> map = new HashMap<>();
//        map.put("college","信息与软件工程学院");
//        map.put("qualification","本科");
//
//        int nums = verifyInfoService.numsOfGraduation(map);
//        System.out.println(nums);
//    }
//
//    @Test
//    public void TestNumsOfVerifying(){
//
//        HashMap<String, String> map = new HashMap<>();
//        map.put("college","信息与软件工程学院");
//        map.put("qualification","本科");
//
//        int nums = verifyInfoService.numsOfVerifying(map);
//        System.out.println(nums);
//    }

    @Test
    public void TestVerifyer(){

        HashMap<String, String> map = new HashMap<>();
        map.put("college","信息与软件工程学院");
        map.put("qualification", "本科");
        List<String> verifyer = null;
        try {
            verifyer = verifyInfoService.verifyer(map);
        } catch (SSException e) {
            e.printStackTrace();
        }
        System.out.println(verifyer);
    }

    @Test
    public void TestUpdateLastLoginTime() throws SSException {
        Date now = new Date();
        System.out.println(now);
        verifyInfoService.updateLastLoginTime(38, now);
    }

    @Test
    public void TestUpdateVerifyCompleteTime() throws SSException {
        Date now = new Date();
        System.out.println(now);
        verifyInfoService.updateVerifyCompleteTime(38, now);
    }
    @Test
    public void TestLastLoginTime() throws SSException {
        System.out.println(verifyInfoService.lastLoginTime(38));
    }

}
