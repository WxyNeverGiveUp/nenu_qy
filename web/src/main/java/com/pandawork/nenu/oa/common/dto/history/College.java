package com.pandawork.nenu.oa.common.dto.history;

import java.util.List;

/**
 * 学院dto
 * @author Lw
 * @time 2017/7/26 10:04
 */
public class College {

    private String collegeCode;
    private String collegeName;
    private List<Major> majorsInCollege;

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public List<Major> getMajorsInCollege() {
        return majorsInCollege;
    }

    public void setMajorsInCollege(List<Major> majorsInCollege) {
        this.majorsInCollege = majorsInCollege;
    }
}
