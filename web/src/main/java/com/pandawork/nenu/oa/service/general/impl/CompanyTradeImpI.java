package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.general.CompanyTrade;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.CompanyTradeMapper;
import com.pandawork.nenu.oa.service.general.CompanyTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:单位所属行业service实现
 * Author:luowanli
 * Date:2016/7/23
 * Time:20:10
 */

@Service("companyTradeService")
public class CompanyTradeImpI implements CompanyTradeService {
    @Autowired
    CompanyTradeMapper companyTradeMapper;

    @Autowired
    CommonDao commonDao;

    @Override
    public CompanyTrade queryById(int id) throws SSException {

        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(CompanyTrade.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CompanyTradeQueryFailed, e);
        }
    }

    @Override
    public CompanyTrade queryByCode(int tradeId) throws SSException {
        try {
            if (Assert.isNull(tradeId) || tradeId==0) {
                throw SSException.get(OaException. CompanyTradeCodeNotNull);
            }
            return companyTradeMapper.queryByCode(tradeId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CompanyTradeQueryFailed, e);
        }
    }

    @Override
    public List<CompanyTrade> listAll() throws SSException {
        try {
            return companyTradeMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CompanyTradeQueryFailed, e);
        }
    }
}
