package com.pandawork.nenu.oa.common.dto.dispatch;

/**
 * DispatchImportDto
 *
 * @author wlm
 * @date 2016/9/8 10:57
 */
public class DispatchImportDto {

    //学号
    private String studentNumber;

    //派遣协议编号
    private String agreementNumber;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }
}
