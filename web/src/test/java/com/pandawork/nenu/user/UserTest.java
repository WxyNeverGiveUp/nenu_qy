package com.pandawork.nenu.user;

import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.entity.user.Role;
import com.pandawork.nenu.oa.common.entity.user.User;
import com.pandawork.nenu.oa.service.user.UserRoleService;
import com.pandawork.nenu.oa.service.user.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Test on 14-9-16.
 */
public class UserTest extends AbstractTestCase{
    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Test
    public void addUser() throws Exception{
        User user = new User();
        user.setUsername("jjxyyjs01");
        user.setPassword("87654321");
        user.setLocked(0);
        user.setRealName("经济学院研究生辅导员");
        user.setCellphone("2233");
        try {
            userService.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void updatePassword() throws Exception{
        userService.updatePassword(35,"87654321","123456");
        System.out.println("success!");
    }

    @Test
    public void addRelationRole() throws Exception{
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        userRoleService.addRelationRole(1,list);
    }

//    @Test
//    public void delRelationRole() throws Exception{
//        List<Integer> ids = new ArrayList<>();
//        ids.add(1);
//        ids.add(2);
//        ids.add(3);
//        userService.delRelationRole(ids);
//    }

    @Test
    public void queryByUsername() throws Exception{
        User user = userService.queryByUsername("yangzhigao");
        System.out.println(user.getSalt());
    }

    @Test
    public void testListUsers() throws Exception{
        Pagination pagination = new Pagination(1000,100,1);
        System.out.println(userService.listUsers(pagination,"5",null));
    }

//    @Test
//    public void listRoles() throws Exception{
//        Set<String> roles = userService.listRoles("yangzhigao");
//        System.out.println(roles);
//    }

//    @Test
//    public void listPermissions() throws Exception{
//        Set<String> permissions = userService.listPermissions("yangzhigao");
//        System.out.println(permissions);
//    }
}
