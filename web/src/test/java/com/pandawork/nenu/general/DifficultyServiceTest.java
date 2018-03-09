package com.pandawork.nenu.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractControllerTestCase;
import com.pandawork.nenu.oa.common.entity.data.DifficultyCode;
import com.pandawork.nenu.oa.service.general.DifficultyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * description:
 * 困难生测试
 * user:qiulan
 * date:2016/7/28
 * time:9:55
 */
public class DifficultyServiceTest extends AbstractControllerTestCase {
    @Autowired
    DifficultyService difficultyService;

    @Test
    public void testQueryByCode() throws SSException{
        DifficultyCode difficultyCode = difficultyService.queryByDifficultyId(7);
        System.out.println(difficultyCode);
    }

    @Test
    public void testQueryById() throws SSException{
       DifficultyCode difficultyCode = difficultyService.queryById(1);
        System.out.println(difficultyCode);
    }

    @Test
    public void testListAll() throws SSException{
       List<DifficultyCode> difficultyCodeList = difficultyService.listAll();
        for (DifficultyCode d : difficultyCodeList){
            System.out.println(d);
        }
    }
}
