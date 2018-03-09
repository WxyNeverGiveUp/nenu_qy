package com.pandawork.nenu.oa.common.entity.user;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户角色关联表
 * user:ouym
 * date: 2014/9/12
 * time: 10:33
 */
@Entity
@Table(name = "t_sys_users_roles")
public class UserRole extends AbstractEntity{
    private static final long serialVersionUID = -1984488762724019135L;

    @Id
    private  Integer id;

    //用户id
    @Column(name = "user_id")
    private Integer userId;

    //角色id
    @Column(name = "role_id")
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
