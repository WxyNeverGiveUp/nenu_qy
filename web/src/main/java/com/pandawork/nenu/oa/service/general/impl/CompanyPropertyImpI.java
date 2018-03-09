package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.general.CompanyProperty;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.CompanyPropertyMapper;
import com.pandawork.nenu.oa.service.general.CompanyPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:单位性质service实现
 * Author:luowanli
 * Date:2016/7/23
 * Time:20:10
 */

@Service("companyPropertyService")
public class CompanyPropertyImpI implements CompanyPropertyService {
    @Autowired
    CompanyPropertyMapper companyPropertyMapper ;

    @Autowired
    CommonDao commonDao;

    @Override
    public CompanyProperty queryById(int id) throws SSException {

        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(CompanyProperty.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CompanyPropertyQueryFailed, e);
        }
    }

    @Override
    public CompanyProperty queryByCode(int propertyCode) throws SSException {
        try {
            if (Assert.isNull(propertyCode) || propertyCode==0) {
                throw SSException.get(OaException.CompanyPropertyCodeNotNull);
            }
            return companyPropertyMapper.queryByCode(propertyCode);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CompanyPropertyQueryFailed, e);
        }
    }

    @Override
    public List<CompanyProperty> listAll() throws SSException {
        try {
            return companyPropertyMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CompanyPropertyQueryFailed, e);
        }
    }
}
