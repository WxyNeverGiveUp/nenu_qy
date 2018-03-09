package com.pandawork.nenu.oa.common.entity.menu;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色权限关联实体
 * user:lixiang
 * date: 2014/10/14
 * time: 10:47
 */
@Entity
@Table(name = "t_sys_roles_resource")
public class RoleResource extends AbstractEntity{
    private static final long serialVersionUID = 134233297682172107L;
    @Id
    private Integer id;

    //角色id
    @Column(name = "role_id")
    private Integer roleId;

    //权限id
    @Column(name = "resource_id")
    private Integer resourceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "RoleResource{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", resourceId=" + resourceId +
                '}';
    }
}
