package com.pandawork.nenu.oa.common.dto.data;

/**
 * user: lishicao
 * date 15/4/23
 * time 下午11:34
 */
public class Province {
    private int provinceCode;
    private String provinceName;

    public Province( int code , String Name ) {
        provinceCode = code ;
        provinceName = Name ;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
