package com.pandawork.nenu.oa.service.data.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.data.ReportCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.data.ReportCodeMapper;
import com.pandawork.nenu.oa.service.data.ReportCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:报到证类别service实现
 * Author:luowanli
 * Date:2016/7/23
 * Time:20:10
 */

@Service("reportCodeService")
public class ReportCodeImpI implements ReportCodeService {
    @Autowired
    ReportCodeMapper reportCodeMapper ;

    @Autowired
    CommonDao commonDao;

    @Override
    public ReportCode queryById(int id) throws SSException {
        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(ReportCode.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ReportCodeQueryFailed, e);
        }
    }

    @Override
    public ReportCode queryByCode(int reportModeId) throws SSException {
        try {
            if (Assert.isNull(reportModeId) || reportModeId==0) {
                throw SSException.get(OaException.ReportCodeNotNull);
            }
            return reportCodeMapper.queryByCode(reportModeId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ReportCodeQueryFailed, e);
        }
    }

    @Override
    public List<ReportCode> listAll() throws SSException {
        try {
            return reportCodeMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ReportCodeQueryFailed, e);
        }
    }
}
