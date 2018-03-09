package com.pandawork.nenu.oa.service.user.impl;

import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.entity.user.Role;
import com.pandawork.nenu.oa.common.entity.user.RolePermissions;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.user.RoleMapper;
import com.pandawork.nenu.oa.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;




@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role addRole(Role role) throws SSException {
        try {
            return commonDao.insert(role);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public void updateRole(Role role) throws SSException {
        try {
            commonDao.update(role);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public void deleteRole(int roleId) throws SSException {
        try {
            commonDao.deleteById(Role.class,roleId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public List<Role> listRolesByUserId(int userId) throws SSException {
        try {
            return roleMapper.listRolesByUserId(userId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public int listRolesCount() throws SSException {
        try {
            return roleMapper.listRolesCount();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public List<Role> listRoles() throws SSException {
        try {
            return roleMapper.listRoles();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public String getRoleNameByRoleId(int roleId) throws SSException {
        try {
            return roleMapper.getRoleNameByRoleId(roleId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public Role getRoleByRoleId(Integer roleId) throws SSException {
        try {
            return roleMapper.getRoleByRoleId(roleId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }
}
