package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.general.ProvinceProperty;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.ProvincePropertyMapper;
import com.pandawork.nenu.oa.service.general.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProvinceServiceImpl
 * 省份service实现
 *
 * @author wlm
 * @date 2016/7/27 15:40
 */

@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    ProvincePropertyMapper provincePropertyMapper;

    @Override
    public ProvinceProperty queryById(int id) throws SSException {

        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(ProvinceProperty.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ProvinceQueryFailed, e);
        }
    }

    @Override
    public ProvinceProperty queryByCode(String code) throws SSException {
        try {
            if (Assert.isNull(code) || code.equals("")) {
                throw SSException.get(OaException.ProvinceCodeNotNull);
            }
            return provincePropertyMapper.queryByCode(code);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ProvinceQueryFailed, e);
        }
    }

    @Override
    public List<ProvinceProperty> listAll() throws SSException {
        try {
            return provincePropertyMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ProvinceQueryFailed, e);
        }
    }

    @Override
    public String queryByName(String name) throws SSException {
        try {
            return provincePropertyMapper.queryByName(name);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ProvinceQueryFailed, e);
        }
    }

    @Override
    public String queryNameById(int id) throws SSException {
        try {
            return provincePropertyMapper.queryNameById(id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ProvinceQueryFailed, e);
        }
    }
}
