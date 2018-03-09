package com.pandawork.nenu.oa.service.student.intentionSurvey.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.student.intentionSurvey.IntentionSurvey;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.student.intentionSurvey.IntentionSurveyMapper;
import com.pandawork.nenu.oa.service.student.intentionSurvey.IntentionSurveyService;
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * QuestionnaireServiceImpl
 * 调查问卷Service实现
 *
 * @author chenwy
 * @date 2017/5/13 10:19
 */
@Service("IntentionSurveyService")
public class IntentionSurveyServiceImpl implements IntentionSurveyService {

    @Autowired
    private IntentionSurveyMapper intentionSurveyMapper;

    @Autowired
    private CommonDao commonDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void newIntentionSurvey(IntentionSurvey intentionSurvey) throws SSException {
        try {
            if (Assert.isNotNull(intentionSurvey)) {
                commonDao.insert(intentionSurvey);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.IntentionSurveyInsertFailed, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateIntentionSurvey(IntentionSurvey intentionSurvey) throws SSException {
        try {
            if (Assert.isNotNull(intentionSurvey)) {
                commonDao.update(intentionSurvey);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.IntentionSurveyUpdateFailed, e);
        }
    }

    @Override
    public IntentionSurvey getIntentionSurvey(int statusInfoId)throws SSException{
        IntentionSurvey intentionSurvey = new IntentionSurvey();
      try{
          if(Assert.isNotNull(statusInfoId)){
              intentionSurvey = intentionSurveyMapper.queryByStatusInfoId(statusInfoId);
          }
      }catch (Exception e){
          LogClerk.errLog.error(e);
          throw SSException.get(OaException.QueryIntentionSurveyByStuStatusIdFailed, e);
      }
        return intentionSurvey;
    }

}
