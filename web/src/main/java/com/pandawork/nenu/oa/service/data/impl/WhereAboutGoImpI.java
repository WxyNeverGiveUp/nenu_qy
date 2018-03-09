package com.pandawork.nenu.oa.service.data.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.data.WhereAboutGoCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.data.WhereAboutGoMapper;
import com.pandawork.nenu.oa.service.data.WhereAboutGoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:毕业去向service实现
 * Author:luowanli
 * Date:2016/7/23
 * Time:20:10
 */

@Service("whereAboutGoService")
public class WhereAboutGoImpI implements WhereAboutGoService {
    @Autowired
    WhereAboutGoMapper whereAboutGoMapper;

    @Autowired
    CommonDao commonDao;

    @Override
    public WhereAboutGoCode queryById(int id) throws SSException {
        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(WhereAboutGoCode.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.WhereAboutGoQueryFailed, e);
        }
    }

    @Override
    public WhereAboutGoCode queryByCode(int whereAboutGoId) throws SSException {
        try {
            if (Assert.isNull(whereAboutGoId) || whereAboutGoId==0) {
                throw SSException.get(OaException.WhereAboutGoCodeNotNull);
            }
            return whereAboutGoMapper.queryByCode(whereAboutGoId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.WhereAboutGoQueryFailed, e);
        }
    }

    @Override
    public List<WhereAboutGoCode> listAll() throws SSException {
        try {
            return whereAboutGoMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.WhereAboutGoQueryFailed, e);
        }
    }
}
