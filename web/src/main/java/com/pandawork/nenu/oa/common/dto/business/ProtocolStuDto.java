package com.pandawork.nenu.oa.common.dto.business;


/**
 * 学生端，申请新协议表中所展示内容
 *
 * @author zhaiaxin
 * @time: 2017/7/18 20:06.
 */
public class ProtocolStuDto {



    private Integer id;
    //变更类型
    private String protocolType;

    //提交时间
    private String createTime;

    //免师跨省
    private String freeTeacherProvince;

    //审核结果
    private String checkProtocolResult;

    //审核理由
    private String checkProtocolReason;

    //审核时间
    private String checkProtocolTime;

    //审核人
    private String checkProtocolOperator;

    //预约时间
    private String fetchTime;

    //预约地点
    private String fetchPlace;

    //学籍id
    private Integer statusInfoId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFreeTeacherProvince() {
        return freeTeacherProvince;
    }

    public void setFreeTeacherProvince(String freeTeacherProvince) {
        this.freeTeacherProvince = freeTeacherProvince;
    }

    public String getCheckProtocolResult() {
        return checkProtocolResult;
    }

    public void setCheckProtocolResult(String checkProtocolResult) {
        this.checkProtocolResult = checkProtocolResult;
    }

    public String getCheckProtocolReason() {
        return checkProtocolReason;
    }

    public void setCheckProtocolReason(String checkProtocolReason) {
        this.checkProtocolReason = checkProtocolReason;
    }

    public String getCheckProtocolTime() {
        return checkProtocolTime;
    }

    public void setCheckProtocolTime(String checkProtocolTime) {
        this.checkProtocolTime = checkProtocolTime;
    }

    public String getCheckProtocolOperator() {
        return checkProtocolOperator;
    }

    public void setCheckProtocolOperator(String checkProtocolOperator) {
        this.checkProtocolOperator = checkProtocolOperator;
    }

    public Integer getStatusInfoId() {
        return statusInfoId;
    }

    public void setStatusInfoId(Integer statusInfoId) {
        this.statusInfoId = statusInfoId;
    }

    public String getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(String fetchTime) {
        this.fetchTime = fetchTime;
    }

    public String getFetchPlace() {
        return fetchPlace;
    }

    public void setFetchPlace(String fetchPlace) {
        this.fetchPlace = fetchPlace;
    }

    @Override
    public String toString() {
        return "ProtocolStuDto{" +
                "id=" + id +
                ", protocolType='" + protocolType + '\'' +
                ", createTime='" + createTime + '\'' +
                ", freeTeacherProvince='" + freeTeacherProvince + '\'' +
                ", checkProtocolResult='" + checkProtocolResult + '\'' +
                ", checkProtocolReason='" + checkProtocolReason + '\'' +
                ", checkProtocolTime='" + checkProtocolTime + '\'' +
                ", checkProtocolOperator='" + checkProtocolOperator + '\'' +
                ", fetchTime='" + fetchTime + '\'' +
                ", fetchPlace='" + fetchPlace + '\'' +
                ", statusInfoId=" + statusInfoId +
                '}';
    }
}
