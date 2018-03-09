package com.pandawork.nenu.oa.common.dto.statistics;

/**
 * Created by Zuosy on 2017/9/15.
 */
public class EmploymentInfoParsedExportDto {

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

    //高校代码 不要了
    //private String schoolCode;

    //高校 不要了
    //private String school;

    //学制
    private Double stuLength;

    //在读学历 不要了
    //private String qualificationNow;

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
    private String pk3_1;
    private String pk3_2;
    private String pk3_3;
    private String pk3_4;
    private String pk3_5;
    private String pk3_6;
    private String pk3_7;
    private String pk3_8;
    private String pk3_9;
    private String pk3_10;
    private String pk3_11;
    private String pk3_12;

    //其他
    private String otherGuidance;

    //就业意向地点（三组六个）
    private String pk4_11;
    private String pk4_12;

    private String pk4_21;
    private String pk4_22;

    private String pk4_31;
    private String pk4_32;

    //预期月薪
    private String pk5;

    //就业意向所属行业
    private String pk6;

    //pk6选A 教育行业倾向（需要解析9个）
    private String pk7_1;
    private String pk7_2;
    private String pk7_3;
    private String pk7_4;
    private String pk7_5;
    private String pk7_6;
    private String pk7_7;
    private String pk7_8;
    private String pk7_9;

    //pk6选B-S 其他行业倾向于
    private String pk8;

    //pk6选T 国际组织（需要解析7个）
    private String pk9_1;
    private String pk9_2;
    private String pk9_3;
    private String pk9_4;
    private String pk9_5;
    private String pk9_6;
    private String pk9_7;

    //就业意向单位 （需要解析3个）
    private String pk10_1;
    private String pk10_2;
    private String pk10_3;


    public EmploymentInfoParsedExportDto() {

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

//    public String getSchoolCode() {
//        return schoolCode;
//    }
//
//    public void setSchoolCode(String schoolCode) {
//        this.schoolCode = schoolCode;
//    }
//
//    public String getSchool() {
//        return school;
//    }
//
//    public void setSchool(String school) {
//        this.school = school;
//    }

    public Double getStuLength() {
        return stuLength;
    }

    public void setStuLength(Double stuLength) {
        this.stuLength = stuLength;
    }

//    public String getQualificationNow() {
//        return qualificationNow;
//    }
//
//    public void setQualificationNow(String qualificationNow) {
//        this.qualificationNow = qualificationNow;
//    }

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

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getOtherMajor() {
        return otherMajor;
    }

    public void setOtherMajor(String otherMajor) {
        this.otherMajor = otherMajor;
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

    public String getPk3_1() {
        return pk3_1;
    }

    public void setPk3_1(String pk3_1) {
        this.pk3_1 = pk3_1;
    }

    public String getPk3_2() {
        return pk3_2;
    }

    public void setPk3_2(String pk3_2) {
        this.pk3_2 = pk3_2;
    }

    public String getPk3_3() {
        return pk3_3;
    }

    public void setPk3_3(String pk3_3) {
        this.pk3_3 = pk3_3;
    }

    public String getPk3_4() {
        return pk3_4;
    }

    public void setPk3_4(String pk3_4) {
        this.pk3_4 = pk3_4;
    }

    public String getPk3_5() {
        return pk3_5;
    }

    public void setPk3_5(String pk3_5) {
        this.pk3_5 = pk3_5;
    }

    public String getPk3_6() {
        return pk3_6;
    }

    public void setPk3_6(String pk3_6) {
        this.pk3_6 = pk3_6;
    }

    public String getPk3_7() {
        return pk3_7;
    }

    public void setPk3_7(String pk3_7) {
        this.pk3_7 = pk3_7;
    }

    public String getPk3_8() {
        return pk3_8;
    }

    public void setPk3_8(String pk3_8) {
        this.pk3_8 = pk3_8;
    }

    public String getPk3_9() {
        return pk3_9;
    }

    public void setPk3_9(String pk3_9) {
        this.pk3_9 = pk3_9;
    }

    public String getPk3_10() {
        return pk3_10;
    }

    public void setPk3_10(String pk3_10) {
        this.pk3_10 = pk3_10;
    }

    public String getPk3_11() {
        return pk3_11;
    }

    public void setPk3_11(String pk3_11) {
        this.pk3_11 = pk3_11;
    }

    public String getPk3_12() {
        return pk3_12;
    }

    public void setPk3_12(String pk3_12) {
        this.pk3_12 = pk3_12;
    }

    public String getOtherGuidance() {
        return otherGuidance;
    }

    public void setOtherGuidance(String otherGuidance) {
        this.otherGuidance = otherGuidance;
    }

    public String getPk4_11() {
        return pk4_11;
    }

    public void setPk4_11(String pk4_11) {
        this.pk4_11 = pk4_11;
    }

    public String getPk4_12() {
        return pk4_12;
    }

    public void setPk4_12(String pk4_12) {
        this.pk4_12 = pk4_12;
    }

    public String getPk4_21() {
        return pk4_21;
    }

    public void setPk4_21(String pk4_21) {
        this.pk4_21 = pk4_21;
    }

    public String getPk4_22() {
        return pk4_22;
    }

    public void setPk4_22(String pk4_22) {
        this.pk4_22 = pk4_22;
    }

    public String getPk4_31() {
        return pk4_31;
    }

    public void setPk4_31(String pk4_31) {
        this.pk4_31 = pk4_31;
    }

    public String getPk4_32() {
        return pk4_32;
    }

    public void setPk4_32(String pk4_32) {
        this.pk4_32 = pk4_32;
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

    public String getPk7_1() {
        return pk7_1;
    }

    public void setPk7_1(String pk7_1) {
        this.pk7_1 = pk7_1;
    }

    public String getPk7_2() {
        return pk7_2;
    }

    public void setPk7_2(String pk7_2) {
        this.pk7_2 = pk7_2;
    }

    public String getPk7_3() {
        return pk7_3;
    }

    public void setPk7_3(String pk7_3) {
        this.pk7_3 = pk7_3;
    }

    public String getPk7_4() {
        return pk7_4;
    }

    public void setPk7_4(String pk7_4) {
        this.pk7_4 = pk7_4;
    }

    public String getPk7_5() {
        return pk7_5;
    }

    public void setPk7_5(String pk7_5) {
        this.pk7_5 = pk7_5;
    }

    public String getPk7_6() {
        return pk7_6;
    }

    public void setPk7_6(String pk7_6) {
        this.pk7_6 = pk7_6;
    }

    public String getPk7_7() {
        return pk7_7;
    }

    public void setPk7_7(String pk7_7) {
        this.pk7_7 = pk7_7;
    }

    public String getPk7_8() {
        return pk7_8;
    }

    public void setPk7_8(String pk7_8) {
        this.pk7_8 = pk7_8;
    }

    public String getPk7_9() {
        return pk7_9;
    }

    public void setPk7_9(String pk7_9) {
        this.pk7_9 = pk7_9;
    }

    public String getPk8() {
        return pk8;
    }

    public void setPk8(String pk8) {
        this.pk8 = pk8;
    }

    public String getPk9_1() {
        return pk9_1;
    }

    public void setPk9_1(String pk9_1) {
        this.pk9_1 = pk9_1;
    }

    public String getPk9_2() {
        return pk9_2;
    }

    public void setPk9_2(String pk9_2) {
        this.pk9_2 = pk9_2;
    }

    public String getPk9_3() {
        return pk9_3;
    }

    public void setPk9_3(String pk9_3) {
        this.pk9_3 = pk9_3;
    }

    public String getPk9_4() {
        return pk9_4;
    }

    public void setPk9_4(String pk9_4) {
        this.pk9_4 = pk9_4;
    }

    public String getPk9_5() {
        return pk9_5;
    }

    public void setPk9_5(String pk9_5) {
        this.pk9_5 = pk9_5;
    }

    public String getPk9_6() {
        return pk9_6;
    }

    public void setPk9_6(String pk9_6) {
        this.pk9_6 = pk9_6;
    }

    public String getPk9_7() {
        return pk9_7;
    }

    public void setPk9_7(String pk9_7) {
        this.pk9_7 = pk9_7;
    }

    public String getPk10_1() {
        return pk10_1;
    }

    public void setPk10_1(String pk10_1) {
        this.pk10_1 = pk10_1;
    }

    public String getPk10_2() {
        return pk10_2;
    }

    public void setPk10_2(String pk10_2) {
        this.pk10_2 = pk10_2;
    }

    public String getPk10_3() {
        return pk10_3;
    }

    public void setPk10_3(String pk10_3) {
        this.pk10_3 = pk10_3;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
