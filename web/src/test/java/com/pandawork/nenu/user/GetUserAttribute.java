package com.pandawork.nenu.user;/*
 * Created on 2003-10-15
 */

import com.wiscom.is.IdentityFactory;
import com.wiscom.is.IdentityManager;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author tzxu
 */
public class GetUserAttribute {
    public static void main(String[] args) throws Exception {
        String path = GetUserAttribute.class.getResource("/").getPath();
        Properties p = new Properties();
        InputStream is = new FileInputStream(path + "client.properties");
        p.load(is);
        is.close();

        IdentityFactory factory = IdentityFactory.createFactory(path + "client.properties");

        IdentityManager manager = factory.getIdentityManager();

        String id = "10200200810284";
        String password = "123456";
        String attrName = "cn";

        if (manager.isUserExist(id)) {
            String[] attrs = manager.getUserAttribute(id, "cn");
            for (int i = 0; i < attrs.length; i++) {
                System.out.println(attrName + "=" + attrs[i]);
            }
            System.out.println(manager.checkPassword(id, password));
            System.out.println(manager.getUserFirstIdentity(id).getFingure());
            System.out.println(manager.getUserFirstIdentity(id).getOrgName());
            System.out.println(manager.getUserGroup(id));
            System.out.println(manager.getUserNameByID(id));
            System.out.println(manager.getLoginURL());
        } else {
            System.out.println("用户 " + id + " 不存在");
        }
//        List allGroups = manager.getGroups();
//
//        for (Iterator i = allGroups.iterator(); i.hasNext(); ) {
//            Group g = (Group) i.next();
//            System.out.println(g);
//        }

    }
}
