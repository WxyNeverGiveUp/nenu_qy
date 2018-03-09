package com.pandawork.nenu.oa.common.entity.user;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * UserInfo
 * 用户信息实体
 * 保存副书记和辅导员所属学院及管理专业
 *
 * @author wlm
 * @date 2016/9/22 16:12
 */

@Entity
@Table(name = "t_sys_user_info")
public class UserInfo extends AbstractEntity{

    //用户信息id
    @Id
    private Integer id;

    //用户id
    @Column(name = "user_id")
    private Integer userId;

    //所属学院代码
    @Column(name = "college_code")
    private String collegeCode;

    //专业层次
    @Column(name = "major_qualification")
    private String majorQualification;

    //所属专业代码
    @Column(name = "major_code")
    private String majorCode;

    //创建时间
    @Column(name = "created_time")
    private Date createdTime;

    //最后修改时间
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public String getMajorQualification() {
        return majorQualification;
    }

    public void setMajorQualification(String majorQualification) {
        this.majorQualification = majorQualification;
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
