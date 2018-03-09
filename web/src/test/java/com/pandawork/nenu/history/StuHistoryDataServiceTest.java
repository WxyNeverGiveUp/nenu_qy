package com.pandawork.nenu.history;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.dto.history.StuHistoryDataDto;
import com.pandawork.nenu.oa.common.entity.history.StuHistoryData;
import com.pandawork.nenu.oa.service.history.StuHistoryDataService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy on 2017/7/26.
 */
public class StuHistoryDataServiceTest extends AbstractTestCase {
    @Autowired
    StuHistoryDataService stuHistoryDataService;

//    @Test
//    public void testQueryDataByCondition() throws SSException {
//        List<StuHistoryDataDto> dataList = new ArrayList<>();
//        dataList = stuHistoryDataService.listStuHistoryDataByCondition("2015", 2, 1, "软件工程", "北京", "3", "上海", "1", "10");
//        for (StuHistoryDataDto stuHistoryDataDto:dataList){
//            System.out.println(stuHistoryDataDto.getId()+stuHistoryDataDto.getName()+stuHistoryDataDto.getCellphone()+stuHistoryDataDto.getCollege()+stuHistoryDataDto.getDifficultyMode()+stuHistoryDataDto.getSex());
//        }
//    }

//    @Test
//    public void testCountByCondition() throws SSException {
//        int count = stuHistoryDataService.countByCondition("2015",2,1,"","","","","","","");
//        System.out.println(count);
//    }
}


