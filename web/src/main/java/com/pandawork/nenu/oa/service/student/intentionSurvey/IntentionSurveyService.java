package com.pandawork.nenu.oa.service.student.intentionSurvey;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.student.intentionSurvey.IntentionSurvey;

/**
 * IntentionSurveyService
 * 就业意向调查Service
 *
 * @author chenwy
 * @date 2017/5/13 10:19
 */
public interface IntentionSurveyService {

    /**
     * 新增就业意向调查记录
     *
     * @param intentionSurvey
     * @throws SSException
     */
    public void newIntentionSurvey(IntentionSurvey intentionSurvey) throws SSException;

    /**
     * 根据学籍id获取就业意向调查结果
     *
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public IntentionSurvey getIntentionSurvey(int statusInfoId)throws SSException;

    /**
     * 更新就业意向调查记录
     *
     * @param intentionSurvey
     * @throws SSException
     */
    public void updateIntentionSurvey(IntentionSurvey intentionSurvey) throws SSException ;

}
