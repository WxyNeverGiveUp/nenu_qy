package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.data.DifficultyCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.DifficultyMapper;
import com.pandawork.nenu.oa.service.general.DifficultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DifficultyServiceImpl
 * 困难生service实现
 *
 * @author wlm
 * @date 2016/7/27 15:40
 */

@Service("difficultyService")
public class DifficultyServiceImpl implements DifficultyService{

    @Autowired
    CommonDao commonDao;

    @Autowired
    DifficultyMapper difficultyMapper;

    @Override
    public DifficultyCode queryById(int id) throws SSException {

        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(DifficultyCode.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DifficultyQueryFailed, e);
        }
    }

    @Override
    public DifficultyCode queryByDifficultyId(int difficultyId) throws SSException {
        try {
            if (Assert.lessZero(difficultyId)) {
                throw SSException.get(OaException.DifficultyCodeIllegal);
            }
            return difficultyMapper.queryByDifficultyId(difficultyId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DifficultyQueryFailed, e);
        }
    }

    @Override
    public List<DifficultyCode> listAll() throws SSException {
        try {
            return difficultyMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DifficultyQueryFailed, e);
        }
    }
}
