package com.pandawork.nenu.oa.common.entity.business;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * description:
 * 身份变更实体
 * user:qiulan
 * date:2016/7/14
 * time:14:43
 */
@Entity
@Table(name = "t_change")
public class Change extends AbstractEntity {

    //身份变更信息id
    @Id
    private Integer id;

    //审核结果,1—未审核，2—审核通过，3—审核未通过待修改，4—审核未通过已修改
    @Column(name = "chack_change_result")
    private Integer checkChangeResult;

    //审核理由
    @Column(name = "check_change_reason")
    private String checkChangeReason;

    //操作人
    @Column(name = "check_change_operator")
    private String checkChangeOperator;

    //变更类型，1—定向解约， 2—委培解约，3—免师解约，4—免师跨省
    @Column(name = "change_type")
    private Integer changeType;

    //申请时间
    @Column(name = "create_time")
    private Date createTime;

    //学籍id
    @Column(name = "status_info_id")
    private Integer statusInfoId;

    //审核时间
    @Column(name = "last_modified_time")
    //private Date lastModifiedTime;

    private Date lastModifiedTime;

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    //当前状态
    @Column(name = "current_status")
    private Integer currentStatus;

    //跨入省编码
    @Column(name = "into_province_code")
    private Integer intoProvinceCode;

    @Transient
    private String name;

    @Transient
    private String studentNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Date getLastModifiedTime() {return lastModifiedTime;}

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatusInfoId() {
        return statusInfoId;
    }

    public void setStatusInfoId(Integer statusInfoId) {
        this.statusInfoId = statusInfoId;
    }

    public Integer getChangeType() {return changeType;}

    public void setChangeType(Integer changeType) {this.changeType = changeType;}

    public String getCheckChangeOperator() {
        return checkChangeOperator;
    }

    public void setCheckChangeOperator(String checkChangeOperator) {
        this.checkChangeOperator = checkChangeOperator;
    }

    public String getCheckChangeReason() {
        return checkChangeReason;
    }

    public void setCheckChangeReason(String checkChangeReason) {
        this.checkChangeReason = checkChangeReason;
    }

    public Integer getCheckChangeResult() {
        return checkChangeResult;
    }

    public void setCheckChangeResult(Integer checkChangeResult) {
        this.checkChangeResult = checkChangeResult;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIntoProvinceCode() {
        return intoProvinceCode;
    }

    public void setIntoProvinceCode(Integer intoProvinceCode) {
        this.intoProvinceCode = intoProvinceCode;
    }

    @Override
    public void setId(Integer integer) {}
}
