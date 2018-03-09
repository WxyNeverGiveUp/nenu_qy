package com.pandawork.nenu.oa.common.entity.business;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * description:
 * 协议详情实体
 * user:qiulan
 * date:2016/7/13
 * time:13:32
 */
@Entity
@Table(name = "t_protocol")
public class Protocol extends AbstractEntity {
    //审核协议id
    @Id
    private int id;

    //审核结果 1—未审核，2—审核通过，3—审核未通过待修改， 4—审核未通过已修改
    @Column(name = "check_protocol_result")
    private Integer checkProtocolResult;

    //审核理由
    @Column(name = "check_protocol_reason")
    private String checkProtocolReason;

    //操纵人
    @Column(name = "check_protocol_operator")
    private String checkProtocolOperator;

    //审核时间
    @Column(name = "check_protocol_time")
    private Date checkProtocolTime;

    //领取地点
    @Column(name = "fetch_place")
    private String fetchPlace;

    //领取时间
    @Column(name = "fetch_time")
    private Date fetchTime;

    //免师跨省师范生跨入省
    @Column(name = "free_teacher_province")
    private String freeTeacherProvince;

    //1-初始状态，（申领新协议：2-协议污损，3-协议丢失，4-协议解约领新协议），（毕业去向变更领协议：5-放弃考研/博领协议，6-放弃出国/出境领协议），（免费师范生业务，7-未改变，8-免师解约，9-免师跨省）,(定向委培业务，10-定向生领协议，11-委培生解约领协议，12-定向生解约)
    @Column(name = "protocol_type")
    private Integer protocolType;

    //学生姓名
    @Transient
    private String name;

    //学生学号
    @Transient
    private String studentNumber;

    //学籍id
    @Column(name = "status_info_id")
    private Integer statusInfoId;

    //申请时间
    @Column(name = "create_time")
    private Date createTime;

    //审核时间
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCheckProtocolResult() {
        return checkProtocolResult;
    }

    public void setCheckProtocolResult(Integer checkProtocolResult) {
        this.checkProtocolResult = checkProtocolResult;
    }

    public String getCheckProtocolReason() {
        return checkProtocolReason;
    }

    public void setCheckProtocolReason(String checkProtocolReason) {
        this.checkProtocolReason = checkProtocolReason;
    }

    public String getCheckProtocolOperator() {
        return checkProtocolOperator;
    }

    public void setCheckProtocolOperator(String checkProtocolOperator) {this.checkProtocolOperator = checkProtocolOperator;}
    public String getName() {
        return name;
    }

    public Date getCheckProtocolTime() {
        return checkProtocolTime;
    }

    public void setCheckProtocolTime(Date checkProtocolTime) {
        this.checkProtocolTime = checkProtocolTime;
    }

    public String getFetchPlace() {
        return fetchPlace;
    }

    public void setFetchPlace(String fetchPlace) {
        this.fetchPlace = fetchPlace;
    }

    public Date getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(Date fetchTime) {
        this.fetchTime = fetchTime;
    }

    public String getFreeTeacherProvince() {
        return freeTeacherProvince;
    }

    public void setFreeTeacherProvince(String freeTeacherProvince) {
        this.freeTeacherProvince = freeTeacherProvince;
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
    public Integer getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(Integer protocolType) {
        this.protocolType = protocolType;
    }
    
    public Integer getStatusInfoId() {
        return statusInfoId;
    }

    public void setStatusInfoId(Integer statusInfoId) {
        this.statusInfoId = statusInfoId;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setId(Integer integer) {}

    @Override
    public String toString() {
        return "Protocol{" +
                "id=" + id +
                ", checkProtocolResult=" + checkProtocolResult +
                ", checkProtocolReason='" + checkProtocolReason + '\'' +
                ", checkProtocolOperator='" + checkProtocolOperator + '\'' +
                ", checkProtocolTime='" + checkProtocolTime + '\'' +
                ", fetchPlace='" + fetchPlace + '\'' +
                ", fetchTime=" + fetchTime +
                ", freeTeacherProvince=" + freeTeacherProvince +
                ", protocolType=" + protocolType +
                ", name='" + name + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", statusInfoId=" + statusInfoId +
                ", createTime=" + createTime +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}
