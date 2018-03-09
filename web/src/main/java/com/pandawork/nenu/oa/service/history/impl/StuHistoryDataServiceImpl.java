package com.pandawork.nenu.oa.service.history.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.oa.common.dto.history.StuHistoryDataDto;
import com.pandawork.nenu.oa.common.entity.history.StuHistoryData;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.history.StuHistoryDataMapper;
import com.pandawork.nenu.oa.service.history.StuHistoryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 学生历史数据service的实现
 * Created by zy on 2017/7/26.
 */
@Service("stuHistoryDataService")
public class StuHistoryDataServiceImpl implements StuHistoryDataService{

    @Autowired
    StuHistoryDataMapper stuHistoryDataMapper;

    @Override
    public int  countByCondition(String year, int messenger, int sex, String majorName, String originPlace, String whereAboutGo, String unitProvince, String unitProperty, String unitIndustry, String college,List<String> colleges,String majorQualification ) throws SSException{
        try {
            return stuHistoryDataMapper.countByCondition(year,messenger,sex,majorName,originPlace,whereAboutGo,unitProvince,unitProperty,unitIndustry,college,colleges,majorQualification);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuHistoryDataQueryFailed,e);
        }
    }



    @Override
    public List<StuHistoryDataDto> listStuHistoryDataByCondition(String year, int messenger, int sex, String majorName, String originPlace, String whereAboutGo, String unitProvince, String unitProperty, String unitIndustry,String college, List<String> colleges, String majorQualification,Pagination page) throws SSException {
        List<StuHistoryDataDto> historyDataList = Collections.emptyList();
        int curPage = 0, pageSize = 15;
        if (!Assert.isNull(page)) {
            curPage = page.getCurrentFristPosition();
            pageSize = page.getPageSize();
        }
        try {
            historyDataList = stuHistoryDataMapper.listStuHistoryDataByCondition(year,messenger,sex, majorName, originPlace,  whereAboutGo, unitProvince,  unitProperty,  unitIndustry, college, colleges, majorQualification, curPage, pageSize);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuHistoryDataQueryFailed,e);
        }
        return historyDataList;
    }
}
