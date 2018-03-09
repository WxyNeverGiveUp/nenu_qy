package com.pandawork.nenu.oa.common.dto.user;

/**
 * UserInfoDto
 *
 * @author wlm
 * @date 2016/12/31 19:02
 */
public class UserInfoDto {

    //所属学院代码
    private String collegeCode;

    //专业层次
    private String majorQualification;

    //所属专业代码
    private String majorCode;

    // 专业名称
    private String majorName;

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
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

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}
