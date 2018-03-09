package com.pandawork.nenu.oa.service.user.impl;

import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.oa.common.dto.user.UserDto;
import com.pandawork.nenu.oa.common.dto.user.UserParamDto;
import com.pandawork.nenu.oa.common.entity.user.Role;
import com.pandawork.nenu.oa.common.entity.user.User;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.PasswordHelper;
import com.pandawork.nenu.oa.mapper.user.UserMapper;
import com.pandawork.nenu.oa.service.user.RoleService;
import com.pandawork.nenu.oa.service.user.UserRoleService;
import com.pandawork.nenu.oa.service.user.UserService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * UserService实现
 * user:zwl
 * date:2014/9/13
 * time:14:50
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public User addUser(User user) throws SSException {
        if (isExistUsername(user.getUsername())) { //用户名不允许重复
            throw SSException.get(OaException.UsernameExist);
        }
        PasswordHelper passwordHelper = new PasswordHelper();
        try {
            passwordHelper.encryptPassword(user);   //密码加密加盐
            return commonDao.insert(user);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public String encryptPassword(User user) throws SSException {
        RandomNumberGenerator randomNumberGenerator =
                new SecureRandomNumberGenerator();
        String algorithmName = "md5";
        int hashIterations = 2;
        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.retCredentialsSalt()),
                hashIterations).toHex();
        return newPassword;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updatePassword(String username, String oldPassword, String newPassword) throws SSException {
        PasswordHelper passwordHelper = new PasswordHelper();
        User oldUser = null;
        try {
            oldUser = userMapper.queryByUsername(username);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
        /** 开始判断原密码是否正确 **/
        String algorithmName = "md5";
        int hashIterations = 2;
        String password = new SimpleHash(
                algorithmName,
                oldPassword,
                ByteSource.Util.bytes(oldUser.getUsername() + oldUser.getSalt()),
                hashIterations).toString();
        if (!password.equals(oldUser.getPassword())) {
            throw SSException.get(OaException.PwdWrong);
        }
        /** 判断原密码是否正确结束 **/

        //将新密码更新到数据库中
        User user = new User(oldUser.getId(), oldUser.getUsername(), newPassword);
        passwordHelper.encryptPassword(user);
        try {
            commonDao.update(user);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updatePassword(int id, String oldPassword, String newPassword) throws SSException {
        PasswordHelper passwordHelper = new PasswordHelper();
        User oldUser = null;
        try {
            oldUser = userMapper.queryUserById(id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
        /** 开始判断原密码是否正确 **/
        String algorithmName = "md5";
        int hashIterations = 2;
        String password = new SimpleHash(
                algorithmName,
                oldPassword,
                ByteSource.Util.bytes(oldUser.getUsername() + oldUser.getSalt()),
                hashIterations).toString();
        if (!password.equals(oldUser.getPassword())) {
            throw SSException.get(OaException.PwdWrong);
        }
        /** 判断原密码是否正确结束 **/

        //将新密码更新到数据 库中
        User user = new User(oldUser.getId(), oldUser.getUsername(), newPassword);
        passwordHelper.encryptPassword(user);
        try {
            commonDao.update(user);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }


    @Override
    public User queryByUsername(String username) throws SSException {
        try {
            return userMapper.queryByUsername(username);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }


    public boolean isExistUsername(String username) throws SSException {
        if (username == null || username.equals("")) return false;
        try {
            return userMapper.isExistUsername(username) > 0;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public boolean isExistUsername(String username, int id) throws SSException {
        try {
            return userMapper.isExistUsernameById(username, id) > 0;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public List<UserDto> listUsers(Pagination page, String roleId, String college) throws SSException {
        int pCurrent = 0, pSize = 10;
        if (page != null) {
            pCurrent = page.getCurrentFristPosition();
            pSize = page.getPageSize();
        }
        List<UserDto> dtos = new ArrayList<UserDto>();
        try {
            List<User> users = userMapper.listUsers(pCurrent, pSize , roleId,college);
            if (users != null) {
                for (User user : users) {
                    UserDto userDto = new UserDto();
                    userDto.setUser(user);
                    List<Role> roles = roleService.listRolesByUserId(user.getId());
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < roles.size(); i++) {
                        if (i > 0) sb.append(",");
                        sb.append(roles.get(i).getDescription());
                    }
                    userDto.setDescription(sb.toString());
                    dtos.add(userDto);
                }
            }
            return dtos;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }

    }


    @Override
    public int listUsersCount(String roleId,String college) throws SSException {

        try {
            return userMapper.listUsersCount(roleId,college);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateUser(User user) throws SSException {
        if (isExistUsername(user.getUsername(), user.getId())) { //用户名不允许重复
            throw SSException.get(OaException.UsernameExist);
        }
        try {
            commonDao.update(user);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public User queryUserById(int id) throws SSException {
        try {
            return userMapper.queryUserById(id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchQueryFaild, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void delUserById(int id) throws SSException {
        try {
            commonDao.deleteById(User.class, id);//删除用户表
            List<Integer> userIds = userRoleService.listRoleByUserId(id);
            userRoleService.delRelationRole(userIds);//删除用户角色关联表
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public List<UserParamDto> listUserParams() throws SSException {
        try {
            return userMapper.listUserParams();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void lock(User user) throws SSException {
        try {
            commonDao.update(user);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public String queryRealNameById(int id) throws SSException {
        try {
            return userMapper.queryRealNameById(id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public List<Integer> queryRoleIdByUserName(String username) throws SSException {
        try {
            return userMapper.queryRoleIdByUserName(username);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updatePasswordNext(int id, String newPassword) throws SSException {
        PasswordHelper passwordHelper = new PasswordHelper();
        User oldUser = null;
        try {
            oldUser = userMapper.queryUserById(id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
        //将新密码更新到数据 库中
        User user = new User(oldUser.getId(), oldUser.getUsername(), newPassword);
        passwordHelper.encryptPassword(user);
        try {
            commonDao.update(user);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }
}
