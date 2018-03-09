package com.pandawork.nenu.oa.common.dto.data;

/**
 * user: lishicao
 * date 15/4/24
 * time 上午9:04
 */
public class MajorDivision {
    private String majorDivisionCode;
    private String majorDivisionName;

    public MajorDivision() {
    }

    public MajorDivision(String code , String name ) {
        majorDivisionCode = code ;
        majorDivisionName = name ;
    }

    public String getMajorDivisionName() {
        return majorDivisionName;
    }

    public void setMajorDivisionName(String majorDivisionName) {
        this.majorDivisionName = majorDivisionName;
    }

    public String getMajorDivisionCode() {
        return majorDivisionCode;
    }

    public void setMajorDivisionCode(String majorDivisionCode) {
        this.majorDivisionCode = majorDivisionCode;
    }
}
