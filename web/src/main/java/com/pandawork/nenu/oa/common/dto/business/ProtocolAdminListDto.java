package com.pandawork.nenu.oa.common.dto.business;

import java.util.Date;

/**
 * ProtocolAdminListDto
 * 管理员端，业务受理显示列表
 * @author zhaiaxin
 * @time: 2017/7/18 20:53.
 */
public class ProtocolAdminListDto {

    //学籍ID
    private Integer id;

    //协议ID
    private Integer protocolId;

    //学生姓名
    private String name;

    //学生性别
    private String sex;

    //学生学号
    private String stuNumber;

    //所在年级
    private Integer grade;

    //专业
    private String major;

    //所在学院
    private String college;

    //学制
    private Integer stuLength;

    //学历
    private String qualification;

    //考生号
    private String candidateNumber;

    //身份证号
    private String idNumber;

    //民族
    private String nation;

    //院校名称
    private String school;

    //培养方式
    private String trainingMode;

    //定向委培单位
    private String weipeiUnit;

    //生源所在地
    private String originPlace;

    //政治面貌
    private String politicalStand;

    //师范生类别
    private String normalStu;

    //困难生类别
    private String difficulty;

    //审核结果
    private String checkProtocolResult;

    //审核理由
    private String checkProtocolReason;

    //审核人
    private String checkProtocolOperator;

    //审核时间
    private Date checkProtocolTime;

    //协议类型
    private String protocolType;

    //免师跨省师范生跨入省
    private String freeTeacherProvince;

    //领取地点
    private String fetchPlace;

    //领取时间
    private Date fetchTime;

    //add by lw

    //提交时间
    private Date createTime;

    //学生类型
    private Integer stuType;

    //是否注册
    private Integer isRegistered;

    //add by lw

    public ProtocolAdminListDto(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getStuLength() {
        return stuLength;
    }

    public void setStuLength(Integer stuLength) {
        this.stuLength = stuLength;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(String candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTrainingMode() {
        return trainingMode;
    }

    public void setTrainingMode(String trainingMode) {
        this.trainingMode = trainingMode;
    }

    public String getWeipeiUnit() {
        return weipeiUnit;
    }

    public void setWeipeiUnit(String weipeiUnit) {
        this.weipeiUnit = weipeiUnit;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getPoliticalStand() {
        return politicalStand;
    }

    public void setPoliticalStand(String politicalStand) {
        this.politicalStand = politicalStand;
    }

    public String getNormalStu() {
        return normalStu;
    }

    public void setNormalStu(String normalStu) {
        this.normalStu = normalStu;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCheckProtocolResult() {
        return checkProtocolResult;
    }

    public void setCheckProtocolResult(String checkProtocolResult) {
        this.checkProtocolResult = checkProtocolResult;
    }

    public String getCheckProtocolReasom() {
        return checkProtocolReason;
    }

    public void setCheckProtocolReasom(String checkProtocolReason) {
        this.checkProtocolReason = checkProtocolReason;
    }

    public String getCheckProtocolOperator() {
        return checkProtocolOperator;
    }

    public void setCheckProtocolOperator(String checkProtocolOperator) {
        this.checkProtocolOperator = checkProtocolOperator;
    }

    public Date getCheckProtocolTime() {
        return checkProtocolTime;
    }

    public void setCheckProtocolTime(Date checkProtocolTime) {
        this.checkProtocolTime = checkProtocolTime;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getFreeTeacherProvince() {
        return freeTeacherProvince;
    }

    public void setFreeTeacherProvince(String freeTeacherProvince) {
        this.freeTeacherProvince = freeTeacherProvince;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckProtocolReason() {
        return checkProtocolReason;
    }

    public void setCheckProtocolReason(String checkProtocolReason) {
        this.checkProtocolReason = checkProtocolReason;
    }

    //add by lw
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStuType() {
        return stuType;
    }

    public void setStuType(Integer stuType) {
        this.stuType = stuType;
    }

    public Integer getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Integer isRegistered) {
        this.isRegistered = isRegistered;
    }
    //add by lw

    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    @Override
    public String toString() {
        return "ProtocolAdminListDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", grade=" + grade +
                ", major='" + major + '\'' +
                ", college='" + college + '\'' +
                ", stuLength=" + stuLength +
                ", qualification='" + qualification + '\'' +
                ", candidateNumber='" + candidateNumber + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", nation='" + nation + '\'' +
                ", school='" + school + '\'' +
                ", trainingMode='" + trainingMode + '\'' +
                ", weipeiUnit='" + weipeiUnit + '\'' +
                ", originPlace='" + originPlace + '\'' +
                ", politicalStand='" + politicalStand + '\'' +
                ", normalStu='" + normalStu + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", checkProtocolResult='" + checkProtocolResult + '\'' +
                ", checkProtocolReason='" + checkProtocolReason + '\'' +
                ", checkProtocolOperator='" + checkProtocolOperator + '\'' +
                ", checkProtocolTime=" + checkProtocolTime +
                ", protocolType='" + protocolType + '\'' +
                ", freeTeacherProvince='" + freeTeacherProvince + '\'' +
                ", fetchPlace='" + fetchPlace + '\'' +
                ", fetchTime=" + fetchTime +
                '}';
    }
}
