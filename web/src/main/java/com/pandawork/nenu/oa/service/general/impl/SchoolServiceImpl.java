package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.dto.data.SchoolDto;
import com.pandawork.nenu.oa.common.entity.data.SchoolCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.SchoolMapper;
import com.pandawork.nenu.oa.service.general.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SchoolServiceImpl
 *
 * @author wlm
 * @date 2016/9/1 18:34
 */

@Service("schoolService")
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public SchoolCode queryById(int id) throws SSException {
        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(SchoolCode.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SchoolQueryFailed, e);
        }
    }

    @Override
    public SchoolCode queryByCode(String code) throws SSException {
        try {
            if (Assert.isNull(code) || code.equals("")) {
                throw SSException.get(OaException.SchoolCodeNotNull);
            }
            return schoolMapper.queryByCode(code);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SchoolQueryFailed, e);
        }
    }

    @Override
    public List<SchoolCode> listAll() throws SSException {
        try {
            return schoolMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SchoolQueryFailed, e);
        }
    }

    @Override
    public SchoolDto queryDtoByCode(String code) throws SSException {
        try {
            return schoolMapper.queryDtoByCode(code);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SchoolQueryFailed, e);
        }
    }
}
