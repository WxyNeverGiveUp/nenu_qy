package com.pandawork.nenu.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractControllerTestCase;
import com.pandawork.nenu.oa.common.entity.data.NormalCode;
import com.pandawork.nenu.oa.service.general.NormalService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 职责
 *
 * @author zhaiaxin
 * @time: 2017/4/7 16:08.
 */
public class NormalServiceTest extends AbstractControllerTestCase {
    @Autowired
    NormalService normalService;

    @Test
    public void testQueryByNormalStuId() throws SSException{
        NormalCode normalCode = normalService.queryByNormalStuId(1);
        System.out.println(normalCode);
    }

    @Test
    public void testListAll() throws SSException{
        List<NormalCode> normalCodeList = normalService.listAll();
        for(NormalCode nc : normalCodeList){
            System.out.println(nc);
        }
    }

}
