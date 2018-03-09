package com.pandawork.nenu.oa.common.dto.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 学生信息
 * user: lishicao
 * date: 14-8-25
 * time: 上午8:39
 */
public class StudentQueryDto {

    //唯一表示
    private Integer id ;

    //grade
    private Integer grade;

    //学生姓名
    private String name;

    //学号
    private Integer studentId;

    //毕业去向代码
    private Integer whereaboutgoId;

    //毕业去向
    private String whereaboutgo;


    //性别代码
    private Integer sexId;

    //正在攻读的学历
    private String qualification;

    //学历代码
    private String qualificationId;

    //学历层次( 毕业的时候是怎样毕业的，或者结业的，比如博士生毕业，或者博士生结业，本科生毕业，本科生结业，二学历)
    private String graduatQualification;

    //学科
    private String majorDivision;

    private Integer collegeId;

    private String collegeName;


    //专业代码
    private String majorId;

    //专业
    private String major;

    //性别
    private String sex;

    //民族号码
    private String nationId;

    //民族
    private String nation;

    //困难生代码
    private Integer difficultyTypeId;

    //困难生类别
    private String difficultyType;

    //政治面貌代码

    private String politicalStandId;

    //政治面貌
    private String politicalStand;

    //师范生类别代码
    private Integer normalStuId;

    //师范生类别
    private String normalStu;

    private String fromProvince;

    private String fromPlace;

    private String goPlaceType;

    private String goArea;

    private String goProvince;

    private String goPlace;

    private String verifyStatus;

    private String checkinStatus;

    private String insMode;

    private String insField;

    private boolean hasPlace;

    private String workDifficultyMode;

    private Date verifyTime;

    private Integer pCurrent;

    private Integer pSize;

   //毕业届次
    private String graduateClass;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getWhereaboutgoId() {
        return whereaboutgoId;
    }

    public void setWhereaboutgoId(Integer whereaboutgoId) {
        this.whereaboutgoId = whereaboutgoId;
    }

    public String getWhereaboutgo() {
        return whereaboutgo;
    }

    public void setWhereaboutgo(String whereaboutgo) {
        this.whereaboutgo = whereaboutgo;
    }

    public Integer getSexId() {
        return sexId;
    }

    public void setSexId(Integer sexId) {
        this.sexId = sexId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getGraduatQualification() {
        return graduatQualification;
    }

    public void setGraduatQualification(String graduatQualification) {
        this.graduatQualification = graduatQualification;
    }

    public String getMajorDivision() {
        return majorDivision;
    }

    public void setMajorDivision(String majorDivision) {
        this.majorDivision = majorDivision;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationId() {
        return nationId;
    }

    public void setNationId(String nationId) {
        this.nationId = nationId;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getDifficultyTypeId() {
        return difficultyTypeId;
    }

    public void setDifficultyTypeId(Integer difficultyTypeId) {
        this.difficultyTypeId = difficultyTypeId;
    }

    public String getDifficultyType() {
        return difficultyType;
    }

    public void setDifficultyType(String difficultyType) {
        this.difficultyType = difficultyType;
    }

    public String getPoliticalStandId() {
        return politicalStandId;
    }

    public void setPoliticalStandId(String politicalStandId) {
        this.politicalStandId = politicalStandId;
    }

    public String getPoliticalStand() {
        return politicalStand;
    }

    public void setPoliticalStand(String politicalStand) {
        this.politicalStand = politicalStand;
    }

    public Integer getNormalStuId() {
        return normalStuId;
    }

    public void setNormalStuId(Integer normalStuId) {
        this.normalStuId = normalStuId;
    }

    public String getNormalStu() {
        return normalStu;
    }

    public void setNormalStu(String normalStu) {
        this.normalStu = normalStu;
    }

    public String getFromProvince() {
        return fromProvince;
    }

    public void setFromProvince(String fromProvince) {
        this.fromProvince = fromProvince;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getGoPlaceType() {
        return goPlaceType;
    }

    public void setGoPlaceType(String goPlaceType) {
        this.goPlaceType = goPlaceType;
    }

    public String getGoArea() {
        return goArea;
    }

    public void setGoArea(String goArea) {
        this.goArea = goArea;
    }

    public String getGoProvince() {
        return goProvince;
    }

    public void setGoProvince(String goProvince) {
        this.goProvince = goProvince;
    }

    public String getGoPlace() {
        return goPlace;
    }

    public void setGoPlace(String goPlace) {
        this.goPlace = goPlace;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getCheckinStatus() {
        return checkinStatus;
    }

    public void setCheckinStatus(String checkinStatus) {
        this.checkinStatus = checkinStatus;
    }

    public String getInsMode() {
        return insMode;
    }

    public void setInsMode(String insMode) {
        this.insMode = insMode;
    }

    public String getInsField() {
        return insField;
    }

    public void setInsField(String insField) {
        this.insField = insField;
    }

    public boolean isHasPlace() {
        return hasPlace;
    }

    public void setHasPlace(boolean hasPlace) {
        this.hasPlace = hasPlace;
    }

    public String getWorkDifficultyMode() {
        return workDifficultyMode;
    }

    public void setWorkDifficultyMode(String workDifficultyMode) {
        this.workDifficultyMode = workDifficultyMode;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Integer getpCurrent() {
        return pCurrent;
    }

    public void setpCurrent(Integer pCurrent) {
        this.pCurrent = pCurrent;
    }

    public Integer getpSize() {
        return pSize;
    }

    public void setpSize(Integer pSize) {
        this.pSize = pSize;
    }

    public String getGraduateClass() {
        return graduateClass;
    }

    public void setGraduateClass(String graduateClass) {
        this.graduateClass = graduateClass;
    }
}
