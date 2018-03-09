package com.pandawork.nenu.oa.common.dto.student.status;

import java.util.Date;
import java.util.List;

import static com.pandawork.core.common.log.LogClerk.sysout;

/**
 * StatusInfoListDto
 * 学籍审核列表用dto
 *
 * @author wlm
 * @date 2016/7/25 16:03
 */
public class StatusInfoListDto {

    //学籍id
    private Integer id;

    //学生姓名
    private String name;

    //性别
    private String sex;

    //学号
    private String stuNumber;

    //年级
    private Integer grade;

    //专业
    private String major;

    //其他专业
    private String otherMajor;

    //学校
    private String school;

    //学历
    private String qualification;

    //学院
    private String college;

    //身份证号
    private String idNumber;

    //学制
    private Double stuLength;

    //考生号
    private String candidateNumber;

    //民族
    private String nation;

    //培养方式
    private String trainingMode;

    //定向/委培单位
    private String weipeiUnit;

    //生源所在地
    private String originPlace;

    //专业层次
    private String majorQualification;

    //专业代码
    private  String majorCode;
    //学生填写的生源所在地
    private String originPlaceImport;
    //政治面貌
    private String politicalStand;
    //困难生类别
    private String difficulty;
    //师范生类别
    private String normalStu;
    //辅导员审核结果
    private String counsellorCheckResult;
    //辅导员审核时间
    private Date counsellorCheckTime;
    //副书记审核结果
    private String deputySecretaryCheckResult;
    //副书记审核时间
    private Date deputySecretaryCheckTime;
    //学校审核结果
    private String schoolCheckResult;
    //学校审核时间
    private Date schoolCheckTime;
    //办理过的业务
    private List<Integer> protocolType;

    //新加的需求，学生生源所在地自己填写
    public String getOriginPlaceImport() {
        return originPlaceImport;
    }

    public void setOriginPlaceImport(String originPlaceImport) {
        this.originPlaceImport = originPlaceImport;
    }

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

    public  String getOtherMajor() {
        return otherMajor;
    }

    public void   setOtherMajor(String otherMajor) {
        this.otherMajor = otherMajor;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Double getStuLength() {
        return stuLength;
    }

    public void setStuLength(Double stuLength) {
        this.stuLength = stuLength;
    }

    public String getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(String candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getNormalStu() {
        return normalStu;
    }

    public void setNormalStu(String normalStu) {
        this.normalStu = normalStu;
    }


    public String getCounsellorCheckResult() {
        return counsellorCheckResult;
    }

    public void setCounsellorCheckResult(String counsellorCheckResult) {
        this.counsellorCheckResult = counsellorCheckResult;
    }

    public Date getCounsellorCheckTime() {
        return counsellorCheckTime;
    }

    public void setCounsellorCheckTime(Date counsellorCheckTime) {
        this.counsellorCheckTime = counsellorCheckTime;
    }

    public String getDeputySecretaryCheckResult() {
        return deputySecretaryCheckResult;
    }

    public void setDeputySecretaryCheckResult(String deputySecretaryCheckResult) {
        this.deputySecretaryCheckResult = deputySecretaryCheckResult;
    }

    public Date getDeputySecretaryCheckTime() {
        return deputySecretaryCheckTime;
    }

    public void setDeputySecretaryCheckTime(Date deputySecretaryCheckTime) {
        this.deputySecretaryCheckTime = deputySecretaryCheckTime;
    }

    public String getSchoolCheckResult() {
        return schoolCheckResult;
    }

    public void setSchoolCheckResult(String schoolCheckResult) {
        this.schoolCheckResult = schoolCheckResult;
    }

    public Date getSchoolCheckTime() {
        return schoolCheckTime;
    }

    public void setSchoolCheckTime(Date schoolCheckTime) {
        this.schoolCheckTime = schoolCheckTime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
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

    public List<Integer> getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(List<Integer> protocolType) {
        this.protocolType = protocolType;
    }
}
