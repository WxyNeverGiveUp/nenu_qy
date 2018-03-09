package com.pandawork.nenu.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractControllerTestCase;
import com.pandawork.nenu.oa.common.entity.general.ProvinceProperty;
import com.pandawork.nenu.oa.service.general.ProvinceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * description:
 * 省份测试
 * user:qiulan
 * date:2016/7/28
 * time:9:55
 */
public class ProvinceServiceTest extends AbstractControllerTestCase {
    @Autowired
    ProvinceService provinceService;

    @Test
    public void testQueryByCode() throws SSException{
        ProvinceProperty provinceProperty = provinceService.queryByCode("11");
        System.out.println(provinceProperty);
    }

    @Test
    public void testQueryById() throws SSException{
      ProvinceProperty provinceProperty = provinceService.queryById(5);
        System.out.println(provinceProperty);
    }

    @Test
    public void testListAll() throws SSException{
       List<ProvinceProperty> provincePropertyList = provinceService.listAll();
        for (ProvinceProperty pp : provincePropertyList){
            System.out.println(pp);
        }
    }
}
