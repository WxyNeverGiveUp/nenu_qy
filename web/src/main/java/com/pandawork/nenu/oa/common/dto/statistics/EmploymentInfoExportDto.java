package com.pandawork.nenu.oa.common.dto.statistics;

/**
 * Created by Zuosy on 2017/9/14.
 */
public class EmploymentInfoExportDto {

    //学籍ID
    private String statusId;

    //姓名
    private String name;

    //手机
    private String cellphone;

    //身份证号
    private String idNumber;

    //考生号
    private String candidateNumber;

    //学号
    private String studentNumber;

    //性别
    private String sex;

    //民族代码
    private String nationCode;

    //民族
    private String nation;

    //政治面貌
    private String politicalStand;

    //高校代码
    private String schoolCode;

    //高校
    private String school;

    //学制
    private Double stuLength;

    //在读学历
    private String qualificationNow;

    //学历
    private String qualification;

    //学院代码
    private String collegeCode;

    //学院
    private String college;

    //年级
    private Integer grade;

    //专业层次
    private String majorQualification;

    //专业代码
    private String majorCode;

    //专业
    private String major;

    //其他专业
    private String otherMajor;

    //拟毕业去向代码
    private String intendWhereabouts;

    //困难生类别
    private String difficulty;

    //师范生类别
    private String normalStu;

    //生源所在地
    private String originPlace;

    //生源所在地代码
    private Integer originPlaceCode;

    //培养方式
    private String trainingMode;

    //就业意向
    private String pk1;

    //本科院校所属
    private String pk2;

    //其他院校
    private String underGraduate;

    //就业指导(需要解析 12个)
    private String pk3;

    //其他
    private String otherGuidance;

    //就业意向地点（三组六个）
    private String pk4;

    //预期月薪
    private String pk5;

    //就业意向所属行业
    private String pk6;

    //pk6选A 教育行业倾向（需要解析9个）
    private String pk7;

    //pk6选B-S 其他行业倾向于
    private String pk8;

    //pk6选T 国际组织（需要解析7个）
    private String pk9;

    //就业意向单位 （需要解析3个）
    private String pk10;


    public EmploymentInfoExportDto() {

    }


    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
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

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
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

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
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

    public String getQualificationNow() {
        return qualificationNow;
    }

    public void setQualificationNow(String qualificationNow) {
        this.qualificationNow = qualificationNow;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
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

    public Integer getOriginPlaceCode() {
        return originPlaceCode;
    }

    public void setOriginPlaceCode(Integer originPlaceCode) {
        this.originPlaceCode = originPlaceCode;
    }

    public String getTrainingMode() {
        return trainingMode;
    }

    public void setTrainingMode(String trainingMode) {
        this.trainingMode = trainingMode;
    }

    public String getPk1() {
        return pk1;
    }

    public void setPk1(String pk1) {
        this.pk1 = pk1;
    }

    public String getPk2() {
        return pk2;
    }

    public void setPk2(String pk2) {
        this.pk2 = pk2;
    }

    public String getUnderGraduate() {
        return underGraduate;
    }

    public void setUnderGraduate(String underGraduate) {
        this.underGraduate = underGraduate;
    }

    public String getPk3() {
        return pk3;
    }

    public void setPk3(String pk3) {
        this.pk3 = pk3;
    }

    public String getOtherGuidance() {
        return otherGuidance;
    }

    public void setOtherGuidance(String otherGuidance) {
        this.otherGuidance = otherGuidance;
    }

    public String getPk4() {
        return pk4;
    }

    public void setPk4(String pk4) {
        this.pk4 = pk4;
    }

    public String getPk5() {
        return pk5;
    }

    public void setPk5(String pk5) {
        this.pk5 = pk5;
    }

    public String getPk6() {
        return pk6;
    }

    public void setPk6(String pk6) {
        this.pk6 = pk6;
    }

    public String getPk7() {
        return pk7;
    }

    public void setPk7(String pk7) {
        this.pk7 = pk7;
    }

    public String getPk8() {
        return pk8;
    }

    public void setPk8(String pk8) {
        this.pk8 = pk8;
    }

    public String getPk9() {
        return pk9;
    }

    public void setPk9(String pk9) {
        this.pk9 = pk9;
    }

    public String getPk10() {
        return pk10;
    }

    public void setPk10(String pk10) {
        this.pk10 = pk10;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getIntendWhereabouts() {
        return intendWhereabouts;
    }

    public void setIntendWhereabouts(String intendWhereabouts) {
        this.intendWhereabouts = intendWhereabouts;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
