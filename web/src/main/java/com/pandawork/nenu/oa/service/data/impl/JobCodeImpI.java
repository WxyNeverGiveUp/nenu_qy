package com.pandawork.nenu.oa.service.data.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.data.JobCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.data.JobCodeMapper;
import com.pandawork.nenu.oa.service.data.JobCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:工作类别service实现
 * Author:luowanli
 * Date:2016/7/23
 * Time:20:10
 */

@Service("jobCodeService")
public class JobCodeImpI implements JobCodeService {
    @Autowired
    JobCodeMapper jobCodeMapper ;

    @Autowired
    CommonDao commonDao;

    @Override
    public JobCode queryById(int id) throws SSException {
        try {
            if (Assert.lessOrEqualZero(id)){
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(JobCode.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.JobCodeQueryFailed, e);
        }
    }

    @Override
    public JobCode queryByCode(int jobId) throws SSException {
        try {
            if (Assert.isNull(jobId) || jobId==0) {
                throw SSException.get(OaException.JobCodeCodeNotNull);
            }
            return jobCodeMapper.queryByCode(jobId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.JobCodeQueryFailed, e);
        }
    }

    @Override
    public List<JobCode> listAll() throws SSException {
        try {
            return jobCodeMapper.listAll();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.JobCodeQueryFailed, e);
        }
    }
}
