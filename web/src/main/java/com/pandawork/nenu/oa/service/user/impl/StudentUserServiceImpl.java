package com.pandawork.nenu.oa.service.user.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.user.StudentUser;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.user.StudentUserMapper;
import com.pandawork.nenu.oa.service.user.StudentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * StudentUserServiceImpl
 *
 * @author wlm
 * @date 2016/7/29 17:31
 */

@Service("studentUserService")
public class StudentUserServiceImpl implements StudentUserService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    StudentUserMapper studentUserMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public StudentUser newStudentUser(StudentUser studentUser) throws SSException {
        if (!checkBeforeSave(studentUser)){
            return null;
        }
        if (this.isUserNameExist(studentUser.getStuNumber())) {
            return this.queryByStuNumber(studentUser.getStuNumber());
        }
        try {
            return commonDao.insert(studentUser);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentUserInsertFailed, e);
        }
    }

    @Override
    public void updateById(StudentUser studentUser) throws SSException {
        if (Assert.isNull(studentUser.getId()) || Assert.lessOrEqualZero(studentUser.getId()) || !checkBeforeSave(studentUser)) {
            return;
        }
        try {
            commonDao.update(studentUser);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentUserUpdateFailed, e);
        }
    }

    @Override
    public boolean isUserNameExist(String username) throws SSException {
        try {
            if (Assert.isNull(username) || username.equals("")) {
                throw SSException.get(OaException.StuNumberNotNull);
            }
            return studentUserMapper.countByStuNumber(username) > 0;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentUserQueryFailed, e);
        }
    }

    @Override
    public StudentUser queryById(int id) throws SSException {
        if (Assert.lessOrEqualZero(id)) {
            throw SSException.get(OaException.IdIllegal);
        }
        try {
            return commonDao.queryById(StudentUser.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentUserQueryFailed, e);
        }
    }

    @Override
    public StudentUser queryByStuNumber(String stuNumber) throws SSException {
        if (Assert.isNull(stuNumber) || stuNumber.equals("")) {
            throw SSException.get(OaException.StuNumberNotNull);
        }
        try {
            return studentUserMapper.queryByStuNumber(stuNumber);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentUserQueryFailed, e);
        }
    }

    @Override
    public String queryNameByStuNumber(String stuNumber) throws SSException {
        if (Assert.isNull(stuNumber) || stuNumber.equals("")) {
            throw SSException.get(OaException.StuNumberNotNull);
        }
        try {
            return studentUserMapper.queryNameByStuNumber(stuNumber);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentUserQueryFailed, e);
        }
    }

    @Override
    public String queryNameById(int id) throws SSException {
        if (Assert.lessOrEqualZero(id)) {
            throw SSException.get(OaException.IdIllegal);
        }
        try {
            return studentUserMapper.queryNameById(id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentUserQueryFailed, e);
        }
    }

    private boolean checkBeforeSave(StudentUser studentUser) throws SSException {
        if (Assert.isNull(studentUser.getStuNumber()) || studentUser.getStuNumber().equals("")) {
            return false;
        }
        if (Assert.isNull(studentUser.getName()) || studentUser.getName().equals("")) {
            return false;
        }
        return true;
    }
}
