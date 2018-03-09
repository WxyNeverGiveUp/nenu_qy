package com.pandawork.nenu.oa.common.dto.dispatch;

/**
 * ReportCardImportDto
 *
 * @author wlm
 * @date 2017/3/2 19:31
 */
public class ReportCardImportDto {

    //学号
    private String studentNumber;

    //报到证号
    private String reportCardNumber;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getReportCardNumber() {
        return reportCardNumber;
    }

    public void setReportCardNumber(String reportCardNumber) {
        this.reportCardNumber = reportCardNumber;
    }
}
