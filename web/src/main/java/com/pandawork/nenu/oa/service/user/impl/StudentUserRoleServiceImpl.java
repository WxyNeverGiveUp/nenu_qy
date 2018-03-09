package com.pandawork.nenu.oa.service.user.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.user.StudentUserRole;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.user.StudentUserRoleMapper;
import com.pandawork.nenu.oa.service.user.StudentUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * StudentUserRoleServiceImpl
 * 学生-角色service实现
 *
 * @author wlm
 * @date 2016/7/30 9:46
 */

@Service("studentUserRoleService")
public class StudentUserRoleServiceImpl implements StudentUserRoleService{

    @Autowired
    CommonDao commonDao;

    @Autowired
    StudentUserRoleMapper studentUserRoleMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public StudentUserRole newRoleRelation(StudentUserRole studentUserRole) throws SSException {
        if (Assert.isNull(studentUserRole)) {
            throw SSException.get(OaException.StudentUserRoleNotNull);
        }
        List<Integer> roles = this.listRolesByUserId(studentUserRole.getStuUserId());
        if (!Assert.isEmpty(roles) && roles.size() > 0) {
            return null;
        }
        try {
            return commonDao.insert(studentUserRole);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentUserRoleInsertFailed, e);
        }
    }

    @Override
    public List<Integer> listRolesByUserId(int userId) throws SSException {
        try {
            if (Assert.lessOrEqualZero(userId)){
                throw SSException.get(OaException.IdIllegal);
            }
            return studentUserRoleMapper.listRolesByUserId(userId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentUserRoleQueryFailed, e);
        }
    }

    @Override
    public Set<String> listRoles(String username) throws SSException {
        try {
            if (Assert.isNull(username) || username.equals("")) {
                throw SSException.get(OaException.UserNameNotNull);
            }
            return studentUserRoleMapper.listRoles(username);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentUserRoleQueryFailed, e);
        }
    }

    @Override
    public List<Integer> listRolesByUsername(String username) throws SSException {
        try {
            return studentUserRoleMapper.listRolesByUsername(username);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentUserRoleQueryFailed, e);
        }
    }

    @Override
    public List<StudentUserRole> listUsersRolesByStudentUserId(Integer studentUserId) throws SSException{
        List<StudentUserRole> studentUserRoleList = Collections.emptyList();
        try{
            if (Assert.lessOrEqualZero(studentUserId)){
                throw SSException.get(OaException.IdIllegal);
            }
            studentUserRoleList = studentUserRoleMapper.listUsersRolesByStudentUserId(studentUserId);
            return studentUserRoleList;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentUserRoleQueryFailed, e);
        }
    }
}
