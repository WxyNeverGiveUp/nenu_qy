package com.pandawork.nenu.oa.service.user.impl;

import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.entity.user.User;
import com.pandawork.nenu.oa.common.entity.user.UserRole;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.user.UserMapper;
import com.pandawork.nenu.oa.mapper.user.UserRoleMapper;
import com.pandawork.nenu.oa.service.user.UserRoleService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chenyt
 * Date: 14-9-25
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    CommonDao commonDao;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void addRelationRole(int userId, List<Integer> roleIds) throws SSException {
        if (roleIds==null||roleIds.size()==0){
            return;
        }
        //检测数据库中是否已经存在该用户——角色关联
        List<UserRole> listUserRole = new ArrayList<UserRole >();
        for(int i=0;i<listUserRole .size();i++){
            for (int j=0;j<roleIds.size();j++) {
                if (listUserRole .get(i).getId() == userId &&
                        listUserRole .get(i).getRoleId().equals(roleIds.get(j))) {
                    throw SSException.get(OaException.UserRoleExist);
                }
            }
        }
        //将用户角色联系插入数据库
        for (int i=0;i<roleIds.size();i++) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleIds.get(i));
            try {
                commonDao.insert(userRole);
            } catch (Exception e) {
                LogClerk.errLog.error(e);
                throw SSException.get(OaException.SystemException, e);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void delRelationRole(List<Integer> userIds)throws SSException{
        try {
            for (int i = 0; i<userIds.size(); i++) {
                List<Integer> ids = userRoleMapper.listUserRoleIdByUserId(userIds.get(i));
                for (int id:ids){
                    commonDao.deleteById(UserRole.class,id);
                }
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }
    @Override
    public Set<String> listRoles(String username) throws SSException {
        try {
            return userRoleMapper.listRoles(username);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException,e);
        }
    }

    @Override
    public List<Integer> listRoleByUserId(int id) throws SSException {
        try {
            return userRoleMapper.listRoleByUserId(id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException,e);
        }
    }

    @Override
    public List<User> listUsersByRoleId(int roleId) throws SSException {
        try {
            return userRoleMapper.listUsersByRoleId(roleId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException,e);
        }
    }

    @Override
    public Integer getIdByEnum(Subject subject) throws SSException {
        Integer a = 0;
        if (subject.hasRole("admin"))
            a=1;
        else if (subject.hasRole("director"))
            a=2;
        else if (subject.hasRole("branch"))
            a=3;
        else if (subject.hasRole("layout"))
            a=4;
        else if (subject.hasRole("complex"))
            a=5;
        else if (subject.hasRole("employ"))
            a=6;
        else if (subject.hasRole("work-lay"))
            a=7;
        else if (subject.hasRole("work-comp"))
            a=8;
        else if (subject.hasRole("comp-info"))
            a=9;
        else if (subject.hasRole("communicate"))
            a=10;

        return a;
    }

    @Override
    public List<Integer> getRolesByUsername(String username) throws SSException {
        try {
            return userRoleMapper.getRolesByUsername(username);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException,e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void delRelation(int userId) throws SSException {
        try {
            List<Integer> ids = userRoleMapper.listUserRoleIdByUserId(userId);
            for (int id:ids){
                commonDao.deleteById(UserRole.class,id);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException,e);
        }
    }

    @Override
    public List<Integer> queryUserIdByRoleIds(int[] roles) throws SSException {
        if(roles != null) {
            //默认将管理员身份加入其中
            StringBuilder sb = new StringBuilder("(1,");

            for(int i=0;i<roles.length;i++){
                sb.append(roles[i]);
                if(i != roles.length -1){
                    sb.append(",");
                }
            }
            sb.append(")");
            try {
                return userRoleMapper.queryUserIdByRoleIds(sb.toString());
            } catch (Exception e) {
                LogClerk.errLog.error(e);
                throw SSException.get(OaException.SystemException, e);
            }
        }else{
            return null;
        }
    }

    @Override
    public Integer RoleIdsByUserId(int userId) throws SSException {
            try {
                return userRoleMapper.RoleIdsByUserId(userId);
            } catch (Exception e) {
                LogClerk.errLog.error(e);
                throw SSException.get(OaException.SystemException, e);
            }
    }
}
