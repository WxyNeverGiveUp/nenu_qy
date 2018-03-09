package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.data.CollegeCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.CollegeMapper;
import com.pandawork.nenu.oa.service.general.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CollegeServiceImpl
 * 学院service实现
 *
 * @author wlm
 * @date 2016/7/27 15:40
 */

@Service("collegeService")
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    CollegeMapper collegeMapper;

    @Override
    public CollegeCode queryById(int id) throws SSException {

        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(CollegeCode.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CollegeQueryFailed, e);
        }
    }

    @Override
    public CollegeCode queryByCode(String code) throws SSException {
        try {
            if (Assert.isNull(code) || code.equals("")) {
                throw SSException.get(OaException.CollegeCodeNotNull);
            }
            return collegeMapper.queryByCode(code);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CollegeQueryFailed, e);
        }
    }

    @Override
    public List<CollegeCode> listAll() throws SSException {
        try {
            return collegeMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CollegeQueryFailed, e);
        }
    }
}
