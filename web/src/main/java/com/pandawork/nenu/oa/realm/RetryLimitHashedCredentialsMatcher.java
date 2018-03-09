package com.pandawork.nenu.oa.realm;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.util.LoginUtil;
import com.pandawork.nenu.oa.service.user.StudentUserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>User: root
 * <p>Date: 14-10-01
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    StudentUserService studentUserService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String userName = token.getPrincipal().toString();
        String passWord = token.getCredentials().toString();
        boolean isNum = userName.matches("[0-9]+");
        if (isNum) {
            try {
            boolean login = LoginUtil.checkPassword(userName, passWord);
                boolean a = studentUserService.isUserNameExist(userName);
//                return studentUserService.isUserNameExist(userName);
            return LoginUtil.isUserExist(userName);
            } catch (SSException e) {
                e.printStackTrace();
                LogClerk.errLog.error(e);
                return false;
            }
        } else {
            boolean matches = super.doCredentialsMatch(token, info);
            return matches;
        }
    }
}
