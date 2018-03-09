package com.pandawork.nenu.oa.common.entity.student.status;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * StuStatusInfo
 * 学籍信息
 *
 * @author wlm
 * @date 2016/7/12 15:23
 */

@Entity
@Table(name = "t_stu_status_information")
public class StuStatusInfo extends AbstractEntity {

    //学籍信息id
    @Id
    private Integer id;

    //学生姓名
    private String name;

    //身份证号
    @Column(name = "id_number")
    private String idNumber;

    //考生号
    @Column(name = "candidate_number")
    private String candidateNumber;

    //学号
    @Column(name = "student_number")
    private String studentNumber;

    //性别
    private Integer sex;

    //民族代码
    @Column(name = "nation_code")
    private String nationCode;

    //政治面貌代码
    @Column(name = "political_stand_code")
    private String politicalStandCode;

    //高校代码
    @Column(name = "school_code")
    private Integer schoolCode;

    //学制
    @Column(name = "stu_length")
    private Double stuLength;

    //在读学历 1-本专科生，2-研究生
    @Column(name = "qualification_now")
    private Integer qualificationNow;

    //学历代码
    @Column(name = "qualification_code")
    private String qualificationCode;

    //学院代码
    @Column(name = "college_code")
    private String collegeCode;

    //年级
    private Integer grade;

    //专业层次
    @Column(name = "major_qualification")
    private String majorQualification;

    //专业代码
    @Column(name = "major_code")
    private String majorCode;

    //其他专业
    @Column(name = "other_major")
    private String otherMajor;

    //拟毕业去向代码
    @Column(name = "intend_whereabouts_code")
    private String intendWhereaboutsCode;

    //困难生类别代码
    @Column(name = "difficulty_code")
    private Integer difficultyCode;

    //师范生类别代码
    @Column(name = "normal_stu_code")
    private Integer normalStuCode;

    //生源所在地
    @Column(name = "origin_place")
    private String originPlace;


    //生源所在地代码
    @Column(name = "origin_place_code")
    private Integer originPlaceCode;

    //培养方式代码
    @Column(name = "training_mode_code")
    private Integer trainingModeCode;

    //定向/委培单位
    @Column(name = "weipei_unit")
    private String weipeiUnit;

    //定向/委培单位地区代码
    @Column(name = "weipei_unit_place")
    private String weipeiUnitPlace;

    //定向/委培单位地区代码
    @Column(name = "weipei_unit_place_code")
    private Integer weipeiUnitPlaceCode;

    //入学日期
    @Column(name = "regist_date")
    private Date registDate;

    //毕业日期
    @Column(name = "graduation_date")
    private Date graduationDate;

    //入学前户口所在地
    @Column(name = "pre_hukou_location")
    private String preHukouLocation;

    //户口是否转入学校 0-否,1-是
    @Column(name = "is_hukou_into_school")
    private Integer isHukouIntoSchool;

    //入学器档案所在地
    @Column(name = "pre_archive_location")
    private String preArchiveLocation;

    //档案是否转入学校 0-否,1-是
    @Column(name = "is_archive_into_school")
    private Integer isArchiveIntoSchool;

    //手机号
    private String cellphone;

    //备用手机号
    @Column(name = "cellphone_bak")
    private String cellphoneBak;

    //qq
    private String qq;

    //email
    private String email;

    //家庭电话
    @Column(name = "home_phone")
    private String homePhone;

    //亲属联系方式
    @Column(name = "relative_phone")
    private String relativePhone;

    //微信
    private String weixin;

    //家庭住址
    @Column(name = "home_address")
    private String homeAddress;

    //宿舍楼
    @Column(name = "dormitory")
    private String dormitory;

    //宿舍号
    @Column(name = "dormitory_num")
    private String dormitoryNum;

    //审核状态 1-已保存未提交，2-未审核，3-辅导员审核未通过待修改，4-辅导员审核未通过已修改，5-辅导员审核通过，
    // 6-副书记审核未通过待修改，7-副书记审核未通过已修改，8-副书记审核通过，9-学校审核未通过待修改，10-学校审核未通过已修改，
    // 11-学校审核通过，12-学校直接审核未通过待修改，13-学校直接审核未通过已修改，14-学校直接审核通过
    @Column(name = "check_status")
    private Integer checkStatus;

    //辅导员审核结果,1—未审核，2—审核通过，3—审核未通过待修改，4—审核未通过已修改
    @Column(name = "counsellor_check_result")
    private Integer counsellorCheckResult;

    //辅导员审核理由
    @Column(name = "counsellor_check_reason")
    private String counsellorCheckReason;

    //辅导员审核操作人
    @Column(name = "counsellor_check_operator")
    private String counsellorCheckOperator;

    //辅导员审核时间
    @Column(name = "counsellor_check_time")
    private Date counsellorCheckTime;

    //副书记审核结果,1—未审核，2—审核通过，3—审核未通过待修改，4—审核未通过已修改
    @Column(name = "deputy_secretary_check_result")
    private Integer deputySecretaryCheckResult;

    //副书记审核理由
    @Column(name = "deputy_secretary_check_reason")
    private String deputySecretaryCheckReason;

    //副书记审核操作人
    @Column(name = "deputy_secretary_check_operator")
    private String deputySecretaryCheckOperator;

    //副书记审核时间
    @Column(name = "deputy_secretary_check_time")
    private Date deputySecretaryCheckTime;

    //学校审核结果,1—未审核，2—审核通过，3—审核未通过待修改，4—审核未通过已修改
    @Column(name = "school_check_result")
    private Integer schoolCheckResult;

    //学校审核理由
    @Column(name = "school_check_reason")
    private String schoolCheckReason;

    //学校审核操作人
    @Column(name = "school_check_operator")
    private String schoolCheckOperator;

    //学校审核时间
    @Column(name = "school_check_time")
    private Date schoolCheckTime;

    //学生类型，1-系统导入，2-管理员新增
    @Column(name = "stu_type")
    private Integer stuType;

    //创建时间
    @Column(name = "created_time")
    private Date createdTime;

    //最后修改时间
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    //就业意向调查填写状态 0-否 1-是
    @Column(name = "intention_survey_status")
    private Integer intentionSurveyStatus;

    //生源所在地（导入）
    @Column(name = "origin_place_import")
    private String originPlaceImport;


    public Integer getId() {
        return id;
    }

    @Override
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getPoliticalStandCode() {
        return politicalStandCode;
    }

    public void setPoliticalStandCode(String politicalStandCode) {
        this.politicalStandCode = politicalStandCode;
    }

    public Integer getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(Integer schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Double getStuLength() {
        return stuLength;
    }

    public void setStuLength(Double stuLength) {
        this.stuLength = stuLength;
    }

    public Integer getQualificationNow() {
        return qualificationNow;
    }

    public void setQualificationNow(Integer qualificationNow) {
        this.qualificationNow = qualificationNow;
    }

    public String getQualificationCode() {
        return qualificationCode;
    }

    public void setQualificationCode(String qualificationCode) {
        this.qualificationCode = qualificationCode;
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
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

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getOtherMajor() {
        return otherMajor;
    }

    public void setOtherMajor(String otherMajor) {
        this.otherMajor = otherMajor;
    }

    public String getIntendWhereaboutsCode() {
        return intendWhereaboutsCode;
    }

    public void setIntendWhereaboutsCode(String intendWhereaboutsCode) {
        this.intendWhereaboutsCode = intendWhereaboutsCode;
    }

    public Integer getDifficultyCode() {
        return difficultyCode;
    }

    public void setDifficultyCode(Integer difficultyCode) {
        this.difficultyCode = difficultyCode;
    }

    public Integer getNormalStuCode() {
        return normalStuCode;
    }

    public void setNormalStuCode(Integer normalStuCode) {
        this.normalStuCode = normalStuCode;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public Integer getOriginPlaceCode() {
        return originPlaceCode;
    }

    public void setOriginPlaceCode(Integer originPlaceCode) {
        this.originPlaceCode = originPlaceCode;
    }

    public Integer getTrainingModeCode() {
        return trainingModeCode;
    }

    public void setTrainingModeCode(Integer trainingModeCode) {
        this.trainingModeCode = trainingModeCode;
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

    public Integer getWeipeiUnitPlaceCode() {
        return weipeiUnitPlaceCode;
    }

    public void setWeipeiUnitPlaceCode(Integer weipeiUnitPlaceCode) {
        this.weipeiUnitPlaceCode = weipeiUnitPlaceCode;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getPreHukouLocation() {
        return preHukouLocation;
    }

    public void setPreHukouLocation(String preHukouLocation) {
        this.preHukouLocation = preHukouLocation;
    }

    public Integer getIsHukouIntoSchool() {
        return isHukouIntoSchool;
    }

    public void setIsHukouIntoSchool(Integer isHukouIntoSchool) {
        this.isHukouIntoSchool = isHukouIntoSchool;
    }

    public String getPreArchiveLocation() {
        return preArchiveLocation;
    }

    public void setPreArchiveLocation(String preArchiveLocation) {
        this.preArchiveLocation = preArchiveLocation;
    }

    public Integer getIsArchiveIntoSchool() {
        return isArchiveIntoSchool;
    }

    public void setIsArchiveIntoSchool(Integer isArchiveIntoSchool) {
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

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getCounsellorCheckResult() {
        return counsellorCheckResult;
    }

    public void setCounsellorCheckResult(Integer counsellorCheckResult) {
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

    public Date getCounsellorCheckTime() {
        return counsellorCheckTime;
    }

    public void setCounsellorCheckTime(Date counsellorCheckTime) {
        this.counsellorCheckTime = counsellorCheckTime;
    }

    public Integer getDeputySecretaryCheckResult() {
        return deputySecretaryCheckResult;
    }

    public void setDeputySecretaryCheckResult(Integer deputySecretaryCheckResult) {
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

    public Date getDeputySecretaryCheckTime() {
        return deputySecretaryCheckTime;
    }

    public void setDeputySecretaryCheckTime(Date deputySecretaryCheckTime) {
        this.deputySecretaryCheckTime = deputySecretaryCheckTime;
    }

    public Integer getSchoolCheckResult() {
        return schoolCheckResult;
    }

    public void setSchoolCheckResult(Integer schoolCheckResult) {
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

    public Date getSchoolCheckTime() {
        return schoolCheckTime;
    }

    public void setSchoolCheckTime(Date schoolCheckTime) {
        this.schoolCheckTime = schoolCheckTime;
    }

    public Integer getStuType() {
        return stuType;
    }

    public void setStuType(Integer stuType) {
        this.stuType = stuType;
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

    public Integer getIntentionSurveyStatus() {
        return intentionSurveyStatus;
    }

    public void setIntentionSurveyStatus(Integer intentionSurveyStatus) {
        this.intentionSurveyStatus = intentionSurveyStatus;
    }

    public String getOriginPlaceImport() {
        return originPlaceImport;
    }

    public void setOriginPlaceImport(String originPlaceImport) {
        this.originPlaceImport = originPlaceImport;
    }

}
