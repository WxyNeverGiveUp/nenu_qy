package com.pandawork.nenu.oa.service.dispatch.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.dispatch.ReportCard;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.dispatch.ReportCardMapper;
import com.pandawork.nenu.oa.service.dispatch.ReportCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ReportCardServiceImpl
 *
 * @author wlm
 * @date 2017/1/8 19:47
 */

@Service("reportCardService")
public class ReportCardServiceImpl implements ReportCardService {

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private ReportCardMapper reportCardMapper;

    @Override
    public ReportCard newReportCard(ReportCard reportCard) throws SSException {
        if (!this.checkBeforeSave(reportCard)) {
            throw SSException.get(OaException.ReportCardInsertFailed);
        }
        try {
            return commonDao.insert(reportCard);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ReportCardInsertFailed, e);
        }
    }

    @Override
    public void updateById(ReportCard reportCard) throws SSException {
        if (!this.checkBeforeSave(reportCard)) {
            throw SSException.get(OaException.ReportCardInsertFailed);
        }
        try {
            commonDao.update(reportCard);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ReportCardUpdateFailed, e);
        }
    }

    @Override
    public ReportCard queryById(int id) throws SSException {
        if (Assert.lessOrEqualZero(id)) {
            throw SSException.get(OaException.IdIllegal);
        }
        try {
            return commonDao.queryById(ReportCard.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ReportCardQueryFailed, e);
        }
    }

    @Override
    public ReportCard queryByStatusId(int statusId) throws SSException {
        if (Assert.lessOrEqualZero(statusId)) {
            throw SSException.get(OaException.IdIllegal);
        }
        try {
            return reportCardMapper.queryByStatusId(statusId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ReportCardQueryFailed, e);
        }
    }

    private boolean checkBeforeSave(ReportCard reportCard) throws SSException {
        if (Assert.isNull(reportCard)) {
            return false;
        }
        if (Assert.isNull(reportCard.getStatusInfoId()) || Assert.lessOrEqualZero(reportCard.getStatusInfoId())) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        if (Assert.isNull(reportCard.getNumber())) {
            throw SSException.get(OaException.ReportCardNumberNotNull);
        }
        return true;
    }
}
