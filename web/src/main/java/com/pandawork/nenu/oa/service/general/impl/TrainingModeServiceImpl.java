package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.data.TrainingModeCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.TrainingModeMapper;
import com.pandawork.nenu.oa.service.general.TrainingModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TrainingModeServiceImpl
 * 培养方式Service
 *
 * @author wlm
 * @date 2016/7/28 15:31
 */

@Service("trainingModeService")
public class TrainingModeServiceImpl implements TrainingModeService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    TrainingModeMapper trainingModeMapper;

    @Override
    public TrainingModeCode queryById(int id) throws SSException {
        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(TrainingModeCode.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.TrainingModeQueryFailed, e);
        }
    }

    @Override
    public TrainingModeCode queryByCode(int code) throws SSException {
        try {
            if (Assert.lessOrEqualZero(code)) {
                throw SSException.get(OaException.TrainingModeCodeNotNull);
            }
            return trainingModeMapper.queryByCode(code);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.TrainingModeQueryFailed, e);
        }
    }

    @Override
    public List<TrainingModeCode> listAll() throws SSException {
        try {
            return trainingModeMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.TrainingModeQueryFailed, e);
        }
    }
}
