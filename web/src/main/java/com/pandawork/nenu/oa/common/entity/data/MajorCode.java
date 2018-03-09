package com.pandawork.nenu.oa.common.entity.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * description:专业代码实体类
 * user: lishicao
 * date 15/4/15
 * time 上午9:55
 */
@Entity
@Table(name = "t_major_code")
public class MajorCode extends AbstractEntity {

    //唯一表示
    @Id
    private Integer id;

    //学历层次
    @Column(name = "qualification")
    private String qualification;

    //专业名称
    @Column(name = "major_name")
    private String majorName;

    //专业代码
    @Column(name = "major_id")
    private String majorId;

    //专业中类
    @Column(name = "major_class")
    private String majorClass;

    //专业中类代码
    @Column(name = "major_class_id")
    private String majorClassId;

    //专业大类
    @Column(name = "major_division")
    private String majorDivision;

    //专业大类代码
    @Column(name = "major_division_id")
    private String majorDivisionId;

    //创建时间
    @Column(name = "created_time")
    private Date createdTime;

    //最后修改时间
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    @Override

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorClass() {
        return majorClass;
    }

    public void setMajorClass(String majorClass) {
        this.majorClass = majorClass;
    }

    public String getMajorClassId() {
        return majorClassId;
    }

    public void setMajorClassId(String majorClassId) {
        this.majorClassId = majorClassId;
    }

    public String getMajorDivision() {
        return majorDivision;
    }

    public void setMajorDivision(String majorDivision) {
        this.majorDivision = majorDivision;
    }

    public String getMajorDivisionId() {
        return majorDivisionId;
    }

    public void setMajorDivisionId(String majorDivisionId) {
        this.majorDivisionId = majorDivisionId;
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