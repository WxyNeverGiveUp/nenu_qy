package com.pandawork.nenu.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractControllerTestCase;
import com.pandawork.nenu.oa.common.entity.data.NationCode;
import com.pandawork.nenu.oa.service.general.NationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * description:
 * 民族测试
 * user:qiulan
 * date:2016/7/28
 * time:9:55
 */
public class NationServiceTest extends AbstractControllerTestCase {
    @Autowired
    NationService nationService;

    @Test
    public void testQueryByCode() throws SSException{
          NationCode nation = nationService.queryByNationId("01");
          System.out.println(nation);
    }

    @Test
    public void testQueryById() throws SSException{
        NationCode nation = nationService.queryById(1);
        System.out.println(nation);
    }

    @Test
    public void testListAll() throws SSException{
        List<NationCode> nationList = nationService.listAll();
        for (NationCode nationCodeList : nationList){
            System.out.println(nationCodeList);
        }
    }
}
