package com.pandawork.nenu.oa.common.entity.user;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StudentUserRole
 *
 * @author wlm
 * @date 2016/7/30 14:42
 */

@Entity
@Table(name = "t_sys_stu_users_roles")
public class StudentUserRole extends AbstractEntity{

    private static final long serialVersionUID = -248828083216069676L;

    //学生-角色表id
    @Id
    private Integer id;

    //学生用户id
    @Column(name = "stu_user_id")
    private Integer stuUserId;

    //角色id
    @Column(name = "role_id")
    private Integer roleId;

    public StudentUserRole() {
    }

    public StudentUserRole(Integer stuUserId, Integer roleId) {
        this.stuUserId = stuUserId;
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuUserId() {
        return stuUserId;
    }

    public void setStuUserId(Integer stuUserId) {
        this.stuUserId = stuUserId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
