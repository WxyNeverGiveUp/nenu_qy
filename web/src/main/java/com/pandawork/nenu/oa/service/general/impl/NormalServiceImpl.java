package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.framework.dao.CommonDao;

import com.pandawork.nenu.oa.common.entity.data.NormalCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.NormalMapper;
import com.pandawork.nenu.oa.service.general.NormalService;
import com.pandawork.core.common.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * NormalServiceImpl
 * 师范生service实现
 *
 * @author zhaiaxin
 * @time: 2017/4/7 15:40.
 */
@Service("NormalService")
public class NormalServiceImpl implements NormalService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    NormalMapper normalMapper;

    @Override
    public NormalCode queryByNormalStuId(int normalStuId) throws SSException{

        try{
            if(Assert.lessOrEqualZero(normalStuId)){
                throw SSException.get(OaException. NormalCodeIllegal);
            }
            return commonDao.queryById(NormalCode.class, normalStuId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.NormalStuIdQueryFailed);
        }
    }

    public List<NormalCode> listAll() throws SSException{
        try{
            return normalMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.NormalStuIdQueryFailed);
        }
    }

}
