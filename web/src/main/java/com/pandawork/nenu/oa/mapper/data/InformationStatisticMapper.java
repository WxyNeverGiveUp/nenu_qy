package com.pandawork.nenu.oa.mapper.data;

import com.pandawork.nenu.oa.common.dto.data.InformationStatisticDTO;
import com.pandawork.nenu.oa.common.entity.data.InformationStatistic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * user: lishicao
 * date 15/4/22
 * time 下午5:48
 */
public interface InformationStatisticMapper {
    /**
     * 插入
     * @param informationStatistic
     * @throws Exception
     */
    public void add( InformationStatistic informationStatistic ) throws Exception;

    /**
     * @param informationStatistic
     * @throws Exception
     */
    public void update( InformationStatistic informationStatistic ) throws Exception;

    /**
     * 从国家系统导入
     * @param informationStatistic
     * @throws Exception
     */
    public void updateFromNation( InformationStatistic informationStatistic ) throws Exception;

    /**
     * 删除
     * @param  examId
     * @throws Exception
     */
    public void delete( @Param("examId")String examId ) throws Exception;

    /**
     * 生源结构
     * @param dimension1
     * @param dimension2
     * @param college
     * @param qualification
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public List<InformationStatisticDTO> studentStructure
            ( @Param("dimension1")String dimension1 , @Param("dimension2")String dimension2 ,
              @Param("college")String college , @Param("qualification")String qualification , @Param("start")int start , @Param("end")int end ) throws Exception;


    /**
     * 就业率
     * @param dimension1
     * @param dimension2
     * @param college
     * @param qualification
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public List<InformationStatisticDTO> employmentRate
            ( @Param("dimension1")String dimension1 , @Param("dimension2")String dimension2 ,
              @Param("college")String college , @Param("qualification")String qualification , @Param("start")int start , @Param("end")int end ) throws Exception;

    /**
     * 拟就业率
     * @param dimension1
     * @param dimension2
     * @param college
     * @param qualification
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public List<InformationStatisticDTO> planEmploymentRate
            ( @Param("dimension1")String dimension1 , @Param("dimension2")String dimension2 ,
              @Param("college")String college , @Param("qualification")String qualification , @Param("start")int start , @Param("end")int end ) throws Exception;

    /**
     * 就业结构
     * @param dimension1
     * @param dimension2
     * @param college
     * @param qualification
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public List<InformationStatisticDTO> employStructure
            ( @Param("dimension1")String dimension1 , @Param("dimension2")String dimension2 ,
              @Param("college")String college , @Param("qualification")String qualification , @Param("start")int start , @Param("end")int end ) throws Exception;
}
