package com.pandawork.nenu.oa.common.dto.dispatch;

import com.pandawork.nenu.oa.common.dto.student.status.StatusInfoListDto;

/**
 * DispatchListDto
 *
 * @author wlm
 * @date 2016/12/2 20:20
 */
public class DispatchListDto extends StatusInfoListDto{

    //用StatusInfoListDto里的id当做派遣id

    //学籍信息id
    private Integer statusInfoId;

    //签约单位名称
    private String companyName;

    //签约单位地址
    private String fullAddress;

    public Integer getStatusInfoId() {
        return statusInfoId;
    }

    public void setStatusInfoId(Integer statusInfoId) {
        this.statusInfoId = statusInfoId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}
