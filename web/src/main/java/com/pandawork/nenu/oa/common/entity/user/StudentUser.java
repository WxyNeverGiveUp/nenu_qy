package com.pandawork.nenu.oa.common.entity.user;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StudentUser
 *
 * @author wlm
 * @date 2016/7/29 17:13
 */

@Entity
@Table(name = "t_sys_stu_users")
public class StudentUser extends AbstractEntity {

    //学生账户id
    @Id
    private Integer id;

    //学号
    @Column(name = "stu_number")
    private String stuNumber;

    //学生姓名
    private String name;

    public StudentUser() {
    }

    public StudentUser(String stuNumber, String name) {
        this.stuNumber = stuNumber;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }


    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
