package com.pandawork.nenu.oa.common.dto.user;

import com.pandawork.nenu.oa.common.entity.user.Role;
import com.pandawork.nenu.oa.common.entity.user.User;

import javax.persistence.Id;
import java.util.List;

/**
 * 用户展示Dto
 * User: chenyt
 * Date: 14-9-24
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class UserDto {
    //用户
    private User user;
    //角色名称
    private String description;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
