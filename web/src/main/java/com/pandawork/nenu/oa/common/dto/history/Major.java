package com.pandawork.nenu.oa.common.dto.history;

/**
 * 专业dto
 * @author Lw
 * @time 2017/7/26 10:05
 */
public class Major {
    //专业代码
    private String majorCode;
    //专业名称
    private String majorName;
    //专业层次,1-研究生专业,2-本科生专业
    private int majorQualification;

    public Major(String majorCode, String majorName, int majorQualification) {
        this.majorCode = majorCode;
        this.majorName = majorName;
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

    public int getMajorQualification() {
        return majorQualification;
    }

    public void setMajorQualification(int majorQualification) {
        this.majorQualification = majorQualification;
    }
}
