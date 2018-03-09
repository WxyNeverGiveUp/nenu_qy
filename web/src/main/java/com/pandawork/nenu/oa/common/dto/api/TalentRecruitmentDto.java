package com.pandawork.nenu.oa.common.dto.api;

/**
 * Created by zy on 2017/8/10.
 */
public class TalentRecruitmentDto {

    //学号
    private String stuNumber;

    //学生姓名
    private String name;

    //性别
    private String sex;

    //专业
    private String majorName;

    //生源所在地
    private String originPlace;

    //专业层次
    private String majorQualification;

    //预期月薪
    private String monthlyPay;

    //第一就业意向省、市
    private String place;



    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
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

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getMajorQualification() {
        return majorQualification;
    }

    public void setMajorQualification(String majorQualification) {
        this.majorQualification = majorQualification;
    }

    public String getMonthlyPay() {
        return monthlyPay;
    }

    public void setMonthlyPay(String monthlyPay) {
        this.monthlyPay = monthlyPay;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
