//package com.pandawork.nenu.oa.realm;
//
//import com.pandawork.core.common.exception.SSException;
//import com.pandawork.core.common.log.LogClerk;
//import com.pandawork.nenu.oa.service.user.StudentUserService;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * CustomCredentialsMatcher
// *
// * @author wlm
// * @date 2016/7/29 15:53
// */
//public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
//
//    @Autowired
//    StudentUserService studentUserService;
//
//    @Override
//    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
//
//        String userName = authcToken.getPrincipal().toString();
//        String passWord = authcToken.getCredentials().toString();
//        System.out.println(studentUserService == null);
//
//        try {
////            boolean login = LoginUtil.checkPassword(userName, passWord);
//            boolean a = studentUserService.isUserNameExist(userName);
//            return studentUserService.isUserNameExist(userName);
////            return LoginUtil.isUserExist(userName);
//        } catch (SSException e) {
//            e.printStackTrace();
//            LogClerk.errLog.error(e);
//            return false;
//        }
//    }
//}
