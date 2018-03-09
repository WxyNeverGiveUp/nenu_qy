package com.pandawork.nenu.oa.common.entity.user;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by root on 14-10-14.
 */
public class RolePermissions extends AbstractEntity{

    private static final long serialVersionUID = 134233297682172107L;
    @Id
    private Integer id;

    //角色id
    @Column(name = "role_id")
    private Integer roleId;

    //权限id
    @Column(name = "permission_id")
    private Integer permissionId;

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

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "RoleResource{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                '}';
    }
}
