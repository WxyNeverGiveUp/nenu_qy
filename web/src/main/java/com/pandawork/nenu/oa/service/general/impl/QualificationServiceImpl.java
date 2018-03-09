package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.data.QualificationCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.QualificationMapper;
import com.pandawork.nenu.oa.service.general.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * QualificationServiceImpl
 *
 * @author wlm
 * @date 2016/7/27 16:07
 */

@Service("qualificationService")
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    QualificationMapper qualificationMapper;

    @Override
    public QualificationCode queryById(int id) throws SSException {
        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(QualificationCode.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.QualificationQueryFailed, e);
        }
    }

    @Override
    public QualificationCode queryByCode(String code) throws SSException {
        try {
            if (Assert.isNull(code) || code.equals("")) {
                throw SSException.get(OaException.QualificationCodeNotNull);
            }
            return qualificationMapper.queryByCode(code);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.QualificationQueryFailed, e);
        }
    }

    @Override
    public List<QualificationCode> listAll() throws SSException {
        try {
            return qualificationMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.QualificationQueryFailed, e);
        }
    }
}
