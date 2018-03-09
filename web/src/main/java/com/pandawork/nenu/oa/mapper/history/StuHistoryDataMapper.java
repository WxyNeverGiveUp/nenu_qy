package com.pandawork.nenu.oa.mapper.history;

import com.pandawork.nenu.oa.common.dto.history.StuHistoryDataDto;
import com.pandawork.nenu.oa.common.entity.history.StuHistoryData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zy on 2017/7/25.
 */
public interface StuHistoryDataMapper {

    /**
     * 查询符合条件的学生历史数据信息数量
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
     * @throws Exception
     */
    public Integer countByCondition(@Param("year") String year,
                                    @Param("messenger") int messenger,
                                    @Param("sex") int sex,
                                    @Param("majorName") String majorName,
                                    @Param("originPlace") String originPlace,
                                    @Param("whereAboutGo") String whereAboutGo,
                                    @Param("unitProvince") String unitProvince,
                                    @Param("unitProperty") String unitProperty,
                                    @Param("unitIndustry") String unitIndustry,
                                    @Param("college") String college,
                                    @Param("colleges") List<String> colleges,
                                    @Param("majorQualification") String majorQualification
    ) throws Exception;


    /**
     * 分页查询符合条件的学生列表（历史数据）
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
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<StuHistoryDataDto> listStuHistoryDataByCondition(@Param("year") String year,
                                                                 @Param("messenger") int messenger,
                                                                 @Param("sex") int sex,
                                                                 @Param("majorName") String majorName,
                                                                 @Param("originPlace") String originPlace,
                                                                 @Param("whereAboutGo") String whereAboutGo,
                                                                 @Param("unitProvince") String unitProvince,
                                                                 @Param("unitProperty") String unitProperty,
                                                                 @Param("unitIndustry") String unitIndustry,
                                                                 @Param("college") String college,
                                                                 @Param("colleges") List<String> colleges,
                                                                 @Param("majorQualification") String majorQualification,
                                                                 @Param("curPage") int curPage,
    @Param("pageSize") int pageSize
    )throws Exception;
}
