package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.data.CollegeCode;
import com.pandawork.nenu.oa.common.entity.data.PoliticalCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.CollegeMapper;
import com.pandawork.nenu.oa.mapper.general.PoliticalMapper;
import com.pandawork.nenu.oa.service.general.CollegeService;
import com.pandawork.nenu.oa.service.general.PoliticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PoliticalServiceImpl
 * 政治面貌service实现
 *
 * @author wlm
 * @date 2016/7/27 15:40
 */

@Service("politicalService")
public class PoliticalServiceImpl implements PoliticalService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    PoliticalMapper politicalMapper;

    @Override
    public PoliticalCode queryById(int id) throws SSException {

        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(PoliticalCode.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.PoliticalQueryFailed, e);
        }
    }

    @Override
    public PoliticalCode queryByPoliticalStandId(String politicalStandId) throws SSException {
        try {
            if (Assert.isNull(politicalStandId) || politicalStandId.equals("")) {
                throw SSException.get(OaException.PoliticalStandIdNotNull);
            }
            return politicalMapper.queryByPoliticalStandId(politicalStandId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.PoliticalQueryFailed, e);
        }
    }

    @Override
    public List<PoliticalCode> listAll() throws SSException {
        try {
            return politicalMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.PoliticalQueryFailed, e);
        }
    }
}
