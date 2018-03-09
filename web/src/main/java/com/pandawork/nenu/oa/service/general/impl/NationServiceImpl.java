package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.data.NationCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.NationMapper;
import com.pandawork.nenu.oa.service.general.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * NationServiceImpl
 * 民族service实现
 *
 * @author wlm
 * @date 2016/7/27 15:40
 */

@Service("nationService")
public class NationServiceImpl implements NationService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    NationMapper nationMapper;

    @Override
    public NationCode queryById(int id) throws SSException {

        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(NationCode.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.NationQueryFailed, e);
        }
    }

    @Override
    public NationCode queryByNationId(String nationId) throws SSException {
        try {
            if (Assert.isNull(nationId) || nationId.equals("")) {
                throw SSException.get(OaException.NationIdNotNull);
            }
            return nationMapper.queryByNationId(nationId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.NationQueryFailed, e);
        }
    }

    @Override
    public List<NationCode> listAll() throws SSException {
        try {
            return nationMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.NationQueryFailed, e);
        }
    }
}
