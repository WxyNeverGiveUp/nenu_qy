package com.pandawork.nenu.oa.common.entity.menu;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 实体
 * user:lixiang
 * date: 2014/10/14
 * time: 10:47
 */
@Entity
@Table(name = "t_sys_resource")
public class Resource extends AbstractEntity implements Comparable<Resource>{
    private static final long serialVersionUID = 134233297682172107L;
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "permission")
    private String permission;

    @Column(name = "available")
    private Integer available;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    //set那用于判断不同对象
    public boolean equals(Object obj) {
        if(obj instanceof Resource) {
            Resource n = (Resource)obj;
            if(name.equals(n.name)) {
                return true;
            }
            else return false;
        }
        return false;
    }
    //set那用于判断不同对象
    public int hashCode() {
        return name.hashCode();
    }

    //list排序菜单类的对象
    @Override
    public int compareTo(Resource o) {
        return this.getId().compareTo(o.getId());
    }
}
