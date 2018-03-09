package com.pandawork.nenu.oa.common.entity.business;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 协议实体
 *
 * @author zuoshiyu
 * @time: 2017/7/18 15:12.
 */

@Entity
@Table(name = "t_protocol_new")
public class ProtocolNew extends AbstractEntity {

    //审核协议ID
    @Id
    private Integer id;

    //审核结果
    @Column(name = "check_result")
    private Integer checkResult;

    //审核理由
    @Column(name = "check_reason")
    private String checkReason;

    //审核人
    @Column(name = "check_operator")
    private String checkOperator;

    //审核时间
    @Column(name = "check_time")
    private Date checkTime;

    //学籍id
    @Column(name = "status_info_id")
    private Integer statusInfoId;

    //申领新协议
    @Column(name = "new_protocol")
    private Integer newProtocol;

    //毕业去向变更领协议
    @Column(name = "graduate_change")
    private Integer graduateChange;

    //免费师范生业务
    @Column(name = "free_teacher")
    private Integer freeTeacher;

    //免师跨省师范生跨入省
    @Column(name = "free_teacher_province")
    private Integer freeTeacherProVince;

    //定向、委培生业务
    @Column(name = "direction")
    private Integer direction;

    //当前状态
    @Column(name = "current_status")
    private Integer currentStatus;

    //创建时间（学生提交时间）
    @Column(name = "create_time")
    private Date createTime;

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

    public Integer getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Integer checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason;
    }

    public String getCheckOperator() {
        return checkOperator;
    }

    public void setCheckOperator(String checkOperator) {
        this.checkOperator = checkOperator;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getStatusInfoId() {
        return statusInfoId;
    }

    public void setStatusInfoId(Integer statusInfoId) {
        this.statusInfoId = statusInfoId;
    }

    public Integer getNewProtocol() {
        return newProtocol;
    }

    public void setNewProtocol(Integer newProtocol) {
        this.newProtocol = newProtocol;
    }

    public Integer getGraduateChange() {
        return graduateChange;
    }

    public void setGraduateChange(Integer graduateChange) {
        this.graduateChange = graduateChange;
    }

    public Integer getFreeTeacher() {
        return freeTeacher;
    }

    public void setFreeTeacher(Integer freeTeacher) {
        this.freeTeacher = freeTeacher;
    }

    public Integer getFreeTeacherProVince() {
        return freeTeacherProVince;
    }

    public void setFreeTeacherProVince(Integer freeTeacherProVince) {
        this.freeTeacherProVince = freeTeacherProVince;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
