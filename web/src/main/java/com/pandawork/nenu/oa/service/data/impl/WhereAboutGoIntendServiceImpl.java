package com.pandawork.nenu.oa.service.data.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.data.WhereAboutGoCode;
import com.pandawork.nenu.oa.common.entity.data.WhereAboutGoIntendCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.data.WhereAboutGoIntendMapper;
import com.pandawork.nenu.oa.service.data.WhereAboutGoIntendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 拟毕业去向Service
 *
 * @Author: chenyuting
 * @DateTime: 2017/5/31  19:04
 */
@Service("whereAboutGoIntendService")
public class WhereAboutGoIntendServiceImpl implements WhereAboutGoIntendService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private WhereAboutGoIntendMapper whereAboutGoIntendMapper;

    @Override
    public WhereAboutGoIntendCode queryById(int id) throws SSException {
        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(WhereAboutGoIntendCode.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.WhereAboutGoIntendQueryFailed, e);
        }
    }

    @Override
    public WhereAboutGoIntendCode queryByCode(int whereAboutGoIntendId) throws SSException {
        WhereAboutGoIntendCode whereAboutGoIntendCode = null;
        try {
            if (Assert.isNull(whereAboutGoIntendId) || whereAboutGoIntendId==0) {
                throw SSException.get(OaException.WhereAboutGoIntendCodeNotNull);
            }
            whereAboutGoIntendCode = whereAboutGoIntendMapper.queryByCode(whereAboutGoIntendId);
            return whereAboutGoIntendCode;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.WhereAboutGoIntendQueryFailed, e);
        }
    }

    @Override
    public List<WhereAboutGoIntendCode> listAll() throws SSException {
        try {
            return whereAboutGoIntendMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.WhereAboutGoIntendQueryFailed, e);
        }
    }
}