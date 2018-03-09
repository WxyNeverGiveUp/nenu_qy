package com.pandawork.nenu.oa.common.entity.user;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色实体
 * user:ouym
 * date: 2014/9/12
 * time: 10:22
 */
@Entity
@Table(name = "t_sys_roles")
public class Role extends AbstractEntity{
    private static final long serialVersionUID = -7303711492594847437L;
    //唯一标识
    @Id
    private Integer id;

    //角色标识符
    private String role;

    //描述
    private String description;

    //是否可用
    private Integer available;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Role) {
            Role role = (Role) obj;
            if (this.id.equals(role.getId())) {
                return true;
            }
        }
        return false;
    }
}
