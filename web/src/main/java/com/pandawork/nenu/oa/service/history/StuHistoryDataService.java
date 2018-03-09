package com.pandawork.nenu.oa.service.history;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.oa.common.dto.history.StuHistoryDataDto;
import com.pandawork.nenu.oa.common.entity.history.StuHistoryData;

import java.util.List;

/**
 * Created by zy on 2017/7/26.
 */
public interface StuHistoryDataService {

    /**
     * 查询符合条件的历史数据数量
     * @param year
     * @param messenger
     * @param sex
     * @param majorName
     * @param originPlace
     * @param whereAboutGo
     * @param unitProvince
     * @param unitProperty
     * @param unitIndustry
     * @param college
     * @param colleges
     * @param majorQualification
     * @return
     * @throws SSException
     */
    public int countByCondition(String year,
                                    int messenger,
                                    int sex,
                                    String majorName,
                                    String originPlace,
                                    String whereAboutGo,
                                    String unitProvince,
                                    String unitProperty,
                                    String unitIndustry,
                                    String college,
                                    List<String> colleges,
                                    String  majorQualification)throws SSException;

    /**
     * 查询符合条件的学生列表（历史数据）
     * @param year
     * @param messenger
     * @param sex
     * @param majorName
     * @param originPlace
     * @param whereAboutGo
     * @param unitProvince
     * @param unitProperty
     * @param unitIndustry
     * @param college
     * @param colleges
     * @param majorQualification
     * @param page
     * @return
     * @throws SSException
     */
    public List<StuHistoryDataDto> listStuHistoryDataByCondition(String year,
                                                                 int messenger,
                                                                 int sex,
                                                                 String majorName,
                                                                 String originPlace,
                                                                 String whereAboutGo,
                                                                 String unitProvince,
                                                                 String unitProperty,
                                                                 String unitIndustry,
                                                                 String college,
                                                                 List<String> colleges,
                                                                 String majorQualification,
                                                                 Pagination page) throws SSException;
}
