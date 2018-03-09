package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.data.PlaceCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.PlaceMapper;
import com.pandawork.nenu.oa.service.general.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * PlaceServiceImpl
 * 地区service实现
 *
 * @author wlm
 * @date 2016/7/27 17:24
 */

@Service("placeService")
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    PlaceMapper placeMapper;

    @Override
    public PlaceCode queryById(int id) throws SSException {
        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(PlaceCode.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.PlaceQueryFailed, e);
        }
    }

    @Override
    public PlaceCode queryByCode(String code) throws SSException {
        try {
            if (Assert.isNull(code) || code.equals("")) {
                throw SSException.get(OaException.PlaceCodeNotNull);
            }
            return placeMapper.queryByCode(code);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.PlaceQueryFailed, e);
        }
    }

    @Override
    public List<PlaceCode> listAll() throws SSException {
        try {
            return placeMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.PlaceQueryFailed, e);
        }
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
//    public void updateById(PlaceCode placeCode) throws SSException{
//        try {
//            commonDao.update(placeCode);
//        } catch (Exception e) {
//            LogClerk.errLog.error(e);
//            throw SSException.get(OaException.PlaceQueryFailed, e);
//        }
//    }
}
