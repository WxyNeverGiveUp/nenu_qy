package com.pandawork.nenu.oa.common.dto.student.status;

import java.util.Date;
import java.util.List;

/**
 * StuStatusInfoDto
 * 学籍信息查询dto
 *
 * @author wlm
 * @date 2016/8/24 20:50
 */
public class StuStatusInfoDto {

    //学籍信息id
    private Integer id;

    //学生姓名
    private String name;

    //身份证号
    private String idNumber;

    //考生号
    private String candidateNumber;

    //学号
    private String studentNumber;

    //性别
    private String sex;

    //民族
    private String nation;

    //政治面貌
    private String politicalStand;

    //高校
    private String school;

    //学制
    private Double stuLength;

    //学历
    private String qualification;

    //学院
    private String college;

    //年级
    private Integer grade;

    //专业层次
    private String majorQualification;

    //其他专业
    private String otherMajor;

    //专业
    private String major;

    //专业代码
    private String MajorCode;

    //拟毕业去向
    private String intendWhereabouts;

    //困难生类别
    private String difficulty;

    //师范生类别
    private String normalStu;

    //学生填写的生源所在地
    private String originPlace;

    //生源所在地(系统导入)
    private String originPlaceImport;
    //生源所在地类别
    private String originPlaceType;
    //培养方式
    private String trainingMode;
    //定向/委培单位
    private String weipeiUnit;
    //定向/委培单位地区
    private String weipeiUnitPlace;
    //入学日期
    private String registDate;
    //毕业日期
    private String graduationDate;
    //入学前户口所在地
    private String preHukouLocation;
    //户口是否转入学校 0-否,1-是
    private String isHukouIntoSchool;
    //入学器档案所在地
    private String preArchiveLocation;
    //档案是否转入学校 0-否,1-是
    private String isArchiveIntoSchool;
    //手机号
    private String cellphone;
    //备用手机号
    private String cellphoneBak;
    //qq
    private String qq;
    //email
    private String email;
    //家庭电话
    private String homePhone;
    //亲属联系方式
    private String relativePhone;
    //微信
    private String weixin;
    //家庭住址
    private String homeAddress;
    //宿舍楼
    private String dormitory;
    //宿舍号
    private String dormitoryNum;
    //审核状态
    private Integer checkStatus;
    //辅导员审核结果,1—未审核，2—审核通过，3—审核未通过待修改，4—审核未通过已修改
    private String counsellorCheckResult;
    //辅导员审核理由
    private String counsellorCheckReason;
    //辅导员审核操作人
    private String counsellorCheckOperator;
    //辅导员审核时间
    private String counsellorCheckTime;
    //副书记审核结果,1—未审核，2—审核通过，3—审核未通过待修改，4—审核未通过已修改
    private String deputySecretaryCheckResult;
    //副书记审核理由
    private String deputySecretaryCheckReason;
    //副书记审核操作人
    private String deputySecretaryCheckOperator;
    //副书记审核时间
    private String deputySecretaryCheckTime;
    //学校审核结果,1—未审核，2—审核通过，3—审核未通过待修改，4—审核未通过已修改
    private String schoolCheckResult;
    //学校审核理由
    private String schoolCheckReason;
    //学校审核操作人
    private String schoolCheckOperator;
    //学校审核时间
    private String schoolCheckTime;
    //填写就业意向调查状态 0-否 1-是
    private int intentionSurveyStatus;
    //创建时间
    private Date createdTime;
    //最后修改时间
    private Date lastModifiedTime;
    //办理过的业务
    private List<Integer> protocolType;

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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(String candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPoliticalStand() {
        return politicalStand;
    }

    public void setPoliticalStand(String politicalStand) {
        this.politicalStand = politicalStand;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Double getStuLength() {
        return stuLength;
    }

    public void setStuLength(Double stuLength) {
        this.stuLength = stuLength;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getMajorQualification() {
        return majorQualification;
    }

    public void setMajorQualification(String majorQualification) {
        this.majorQualification = majorQualification;
    }

    public String getOtherMajor() {
        return otherMajor;
    }

    public void setOtherMajor(String otherMajor) {
        this.otherMajor = otherMajor;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajorCode() {
        return MajorCode;
    }

    public void setMajorCode(String majorCode) {
        MajorCode = majorCode;
    }

    public String getIntendWhereabouts() {
        return intendWhereabouts;
    }

    public void setIntendWhereabouts(String intendWhereabouts) {
        this.intendWhereabouts = intendWhereabouts;
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

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getOriginPlaceType() {
        return originPlaceType;
    }

    public void setOriginPlaceType(String originPlaceType) {
        this.originPlaceType = originPlaceType;
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

    public String getWeipeiUnitPlace() {
        return weipeiUnitPlace;
    }

    public void setWeipeiUnitPlace(String weipeiUnitPlace) {
        this.weipeiUnitPlace = weipeiUnitPlace;
    }

    public String getRegistDate() {
        return registDate;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getPreHukouLocation() {
        return preHukouLocation;
    }

    public void setPreHukouLocation(String preHukouLocation) {
        this.preHukouLocation = preHukouLocation;
    }

    public String getIsHukouIntoSchool() {
        return isHukouIntoSchool;
    }

    public void setIsHukouIntoSchool(String isHukouIntoSchool) {
        this.isHukouIntoSchool = isHukouIntoSchool;
    }

    public String getPreArchiveLocation() {
        return preArchiveLocation;
    }

    public void setPreArchiveLocation(String preArchiveLocation) {
        this.preArchiveLocation = preArchiveLocation;
    }

    public String getIsArchiveIntoSchool() {
        return isArchiveIntoSchool;
    }

    public void setIsArchiveIntoSchool(String isArchiveIntoSchool) {
        this.isArchiveIntoSchool = isArchiveIntoSchool;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCellphoneBak() {
        return cellphoneBak;
    }

    public void setCellphoneBak(String cellphoneBak) {
        this.cellphoneBak = cellphoneBak;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getRelativePhone() {
        return relativePhone;
    }

    public void setRelativePhone(String relativePhone) {
        this.relativePhone = relativePhone;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getCounsellorCheckResult() {
        return counsellorCheckResult;
    }

    public void setCounsellorCheckResult(String counsellorCheckResult) {
        this.counsellorCheckResult = counsellorCheckResult;
    }

    public String getCounsellorCheckReason() {
        return counsellorCheckReason;
    }

    public void setCounsellorCheckReason(String counsellorCheckReason) {
        this.counsellorCheckReason = counsellorCheckReason;
    }

    public String getCounsellorCheckOperator() {
        return counsellorCheckOperator;
    }

    public void setCounsellorCheckOperator(String counsellorCheckOperator) {
        this.counsellorCheckOperator = counsellorCheckOperator;
    }

    public String getCounsellorCheckTime() {
        return counsellorCheckTime;
    }

    public void setCounsellorCheckTime(String counsellorCheckTime) {
        this.counsellorCheckTime = counsellorCheckTime;
    }

    public String getDeputySecretaryCheckResult() {
        return deputySecretaryCheckResult;
    }

    public void setDeputySecretaryCheckResult(String deputySecretaryCheckResult) {
        this.deputySecretaryCheckResult = deputySecretaryCheckResult;
    }

    public String getDeputySecretaryCheckReason() {
        return deputySecretaryCheckReason;
    }

    public void setDeputySecretaryCheckReason(String deputySecretaryCheckReason) {
        this.deputySecretaryCheckReason = deputySecretaryCheckReason;
    }

    public String getDeputySecretaryCheckOperator() {
        return deputySecretaryCheckOperator;
    }

    public void setDeputySecretaryCheckOperator(String deputySecretaryCheckOperator) {
        this.deputySecretaryCheckOperator = deputySecretaryCheckOperator;
    }

    public String getDeputySecretaryCheckTime() {
        return deputySecretaryCheckTime;
    }

    public void setDeputySecretaryCheckTime(String deputySecretaryCheckTime) {
        this.deputySecretaryCheckTime = deputySecretaryCheckTime;
    }

    public String getSchoolCheckResult() {
        return schoolCheckResult;
    }

    public void setSchoolCheckResult(String schoolCheckResult) {
        this.schoolCheckResult = schoolCheckResult;
    }

    public String getSchoolCheckReason() {
        return schoolCheckReason;
    }

    public void setSchoolCheckReason(String schoolCheckReason) {
        this.schoolCheckReason = schoolCheckReason;
    }

    public String getSchoolCheckOperator() {
        return schoolCheckOperator;
    }

    public void setSchoolCheckOperator(String schoolCheckOperator) {
        this.schoolCheckOperator = schoolCheckOperator;
    }

    public String getSchoolCheckTime() {
        return schoolCheckTime;
    }

    public void setSchoolCheckTime(String schoolCheckTime) {
        this.schoolCheckTime = schoolCheckTime;
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

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getDormitoryNum() {
        return dormitoryNum;
    }

    public void setDormitoryNum(String dormitoryNum) {
        this.dormitoryNum = dormitoryNum;
    }

    public int getIntentionSurveyStatus() {
        return intentionSurveyStatus;
    }

    public void setIntentionSurveyStatus(int intentionSurveyStatus) {
        this.intentionSurveyStatus = intentionSurveyStatus;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public List<Integer> getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(List<Integer> protocolType) {
        this.protocolType = protocolType;
    }
}
