package com.pandawork.nenu.oa.mapper.student.intentionSurvey;

import org.apache.ibatis.annotations.Param;
import com.pandawork.nenu.oa.common.entity.student.intentionSurvey.IntentionSurvey;

/**
 * IntentionSurveyMapper
 * 就业意向调查mapper
 *
 * @author chenwy
 * @date 2017/5/12 15:51
 */
public interface IntentionSurveyMapper {

    /**
     * 根据学籍id获取就业意向调查结果
     *
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public IntentionSurvey queryByStatusInfoId(@Param("statusInfoId") int statusInfoId)throws Exception;

}
