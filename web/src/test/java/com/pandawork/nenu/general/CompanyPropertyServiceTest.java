package com.pandawork.nenu.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.entity.general.CompanyProperty;
import com.pandawork.nenu.oa.service.general.CompanyPropertyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description:
 * Author:luowanli
 * Date:2016/7/23
 * Time:20:43
 */
public class CompanyPropertyServiceTest extends AbstractTestCase {

    @Autowired
    CompanyPropertyService companyPropertyService;

    @Test
    public void testQueryCompanyPropertyCodes()throws SSException {
        List<CompanyProperty> l = companyPropertyService.listAll();
        for(CompanyProperty s: l){
            System.out.println(s);
        }
    }
}
