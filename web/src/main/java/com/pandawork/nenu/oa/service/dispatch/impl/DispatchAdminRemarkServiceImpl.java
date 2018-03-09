package com.pandawork.nenu.oa.service.dispatch.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchAdminRemark;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.dispatch.DispatchAdminRemarkMapper;
import com.pandawork.nenu.oa.service.dispatch.DispatchAdminRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理员派遣扩展项Service实现
 * DispatchAdminRemarkServiceImpl
 *
 * Created by ZhangBiLai on 2017/7/18.
 */

@Service("DispatchAdminRemarkService")
public class DispatchAdminRemarkServiceImpl implements DispatchAdminRemarkService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    DispatchAdminRemarkMapper dispatchAdminRemarkMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public List<DispatchAdminRemark> queryByDispatchId(int dispatchId) throws SSException {
        try {
            if (Assert.lessOrEqualZero(dispatchId)) {
                throw SSException.get(OaException.DispatchIdIllegal);
            }
            return dispatchAdminRemarkMapper.queryByDispatchId(dispatchId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchIdQueryFailed, e);
        }
    }

    @Override
    public void newOrUpdateRemarks(List<DispatchAdminRemark> list) throws SSException {
        for(int i = 0; i < list.size(); i++) {
            DispatchAdminRemark dispatchAdminRemark = list.get(i);
            try {
                if (Assert.isNull(dispatchAdminRemark.getId())) {
                    commonDao.insert(dispatchAdminRemark);
                } else
                    commonDao.update(dispatchAdminRemark);
            } catch (Exception e) {
                LogClerk.errLog.error(e);
                throw SSException.get(OaException.DispatchAdminRemarkInsertFailed, e);
            }
        }


    }

    @Override
    public void deleteById(DispatchAdminRemark dispatchAdminRemark) throws SSException {
        if (!this.checkBeforeSave(dispatchAdminRemark)) {
            throw SSException.get(OaException.DispatchAdminRemarkIsNull);
        }
        try {
            commonDao.delete(dispatchAdminRemark);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchAdminRemarkDeleteFailed, e);
        }
    }

    /**
     * 检查实体及必要字段是否为空
     *
     * @param dispatchAdminRemark
     * @return
     * @throws SSException
     */
    private boolean checkBeforeSave(DispatchAdminRemark dispatchAdminRemark) throws SSException {
        if (Assert.isNull(dispatchAdminRemark)) {
            return false;
        }
//        if (Assert.isNull(dispatchAdminRemark.getDispatchId()) || Assert.lessOrEqualZero(dispatchAdminRemark.getDispatchId())){
//            throw SSException.get(OaException.DispatchIdIllegal);
//        }
        return true;
    }
}
