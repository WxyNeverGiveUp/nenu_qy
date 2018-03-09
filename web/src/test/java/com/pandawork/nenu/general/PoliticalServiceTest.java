package com.pandawork.nenu.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractControllerTestCase;
import com.pandawork.nenu.oa.common.entity.data.PoliticalCode;
import com.pandawork.nenu.oa.service.general.PoliticalService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * description:
 * 政治面貌测试
 * user:qiulan
 * date:2016/7/28
 * time:9:55
 */
public class PoliticalServiceTest extends AbstractControllerTestCase {
    @Autowired
    PoliticalService politicalService;

    @Test
    public void testQueryByCode() throws SSException{
        PoliticalCode politicalCode = politicalService.queryByPoliticalStandId("10");
        System.out.println(politicalCode);
    }

    @Test
    public void testQueryById() throws SSException{
       PoliticalCode politicalCode = politicalService.queryById(2);
        System.out.println(politicalCode);
    }

    @Test
    public void testListAll() throws SSException{
       List<PoliticalCode> politicalCodeList = politicalService.listAll();
        for (PoliticalCode p : politicalCodeList){
            System.out.println(p);
        }
    }
}
