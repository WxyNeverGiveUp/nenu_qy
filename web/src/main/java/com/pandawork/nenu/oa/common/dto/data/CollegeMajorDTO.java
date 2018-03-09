package com.pandawork.nenu.oa.common.dto.data;

import com.pandawork.nenu.oa.common.entity.data.MajorCode;
import java.util.List;

/**
 * user: lishicao
 * date 15/6/7
 * time 下午2:14
 */
public class CollegeMajorDTO {
    private String college ;
    private List<MajorCode> majorsInCollege ;

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public List<MajorCode> getMajorsInCollege() {
        return majorsInCollege;
    }

    public void setMajorsInCollege(List<MajorCode> majorsInCollege) {
        this.majorsInCollege = majorsInCollege;
    }
}
