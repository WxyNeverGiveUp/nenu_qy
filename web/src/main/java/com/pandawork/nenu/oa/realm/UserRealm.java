package com.pandawork.nenu.oa.realm;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.entity.user.User;
import com.pandawork.nenu.oa.common.util.LoginUtil;
import com.pandawork.nenu.oa.service.user.StudentUserRoleService;
import com.pandawork.nenu.oa.service.user.StudentUserService;
import com.pandawork.nenu.oa.service.user.UserRoleService;
import com.pandawork.nenu.oa.service.user.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>user: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private StudentUserService studentUserService;

    @Autowired
    private StudentUserRoleService studentUserRoleService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        boolean isNum = username.matches("[0-9]+");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        try {
            if (isNum) {
                if (LoginUtil.isUserExist(username)) {
                    authorizationInfo.setRoles(studentUserRoleService.listRoles(username));
                }
//                if (studentUserService.isUserNameExist(username)){
//                    authorizationInfo.setRoles(studentUserRoleService.listRoles(username));
//                }
            } else {
                authorizationInfo.setRoles(userRoleService.listRoles(username));
                //authorizationInfo.setStringPermissions(userService.listPermissions(username));
            }
        } catch (SSException e) {
            LogClerk.errLog.error(e);
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = null;
        String password = "";
        boolean isNum = username.matches("[0-9]+");

        try {
            user = userService.queryByUsername(username);
            //user.setPassword(pw);
        } catch (SSException e) {
            LogClerk.errLog.error(e);
        }
        if (user == null && !isNum) {
            throw new UnknownAccountException();//没找到帐号
        }

        if (user != null && Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }

        SimpleAuthenticationInfo authenticationInfo = null;

        try {
            if (isNum) {
//                if (studentUserService.isUserNameExist(username)) {
                if (LoginUtil.isUserExist(username)) {
//                    setCredentialsMatcher(new CustomCredentialsMatcher());
                    authenticationInfo = new SimpleAuthenticationInfo(
                            username, //用户名
                            password, //密码
                            this.getName()  //realm name
                    );
                }
            } else {
                //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
                authenticationInfo = new SimpleAuthenticationInfo(
                        user.getUsername(), //用户名
                        user.getPassword(), //密码
                        this.getName()  //realm name
                );
                authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.retCredentialsSalt()));//salt=username+salt
            }
        } catch (SSException e) {
            e.printStackTrace();
            LogClerk.errLog.error(e);
        }
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
