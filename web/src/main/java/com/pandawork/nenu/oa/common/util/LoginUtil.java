package com.pandawork.nenu.oa.common.util;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.wiscom.is.IdentityFactory;
import com.wiscom.is.IdentityManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * LoginUtil
 *
 * @author wlm
 * @date 2016/7/29 16:12
 */
public class LoginUtil {

    static IdentityManager manager = null;

    static {
        String path = LoginUtil.class.getResource("/").getPath();
        Properties p = new Properties();
        InputStream is = null;
        IdentityFactory factory = null;
        try {
            is = new FileInputStream(path + "client.properties");
            p.load(is);
            is.close();
            factory = IdentityFactory.createFactory(path + "client.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LogClerk.errLog.error(e);
        } catch (IOException e) {
            e.printStackTrace();
            LogClerk.errLog.error(e);
        } catch (Exception e) {
            e.printStackTrace();
            LogClerk.errLog.error(e);
        }

        manager =  factory.getIdentityManager();
    }

    public static boolean isUserExist(String username) throws SSException{
        return manager.isUserExist(username);
    }

    public static String getNameById(String username) throws SSException {
        return manager.getUserNameByID(username);
    }

    public static boolean checkPassword(String username, String password) throws SSException {
        return manager.checkPassword(username, password);
    }

    public static String getUserGroupById(String username) throws SSException {
        List groupList = manager.getUserGroup(username);
        String group = null;
        if (!Assert.isEmpty(groupList)) {
            group = groupList.get(0).toString();
        }
        return group;
    }
}
