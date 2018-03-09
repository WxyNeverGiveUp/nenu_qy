package com.pandawork.nenu.oa.common.util;

import com.pandawork.nenu.oa.common.entity.user.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
* 生成密码的加密算法
* user:ouym
* date: 2014/9/13
* time: 14:28
*/
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator =
            new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private final int hashIterations = 2;
    public void encryptPassword(User user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.retCredentialsSalt()),
                hashIterations).toHex();
        user.setPassword(newPassword);
    }


}

