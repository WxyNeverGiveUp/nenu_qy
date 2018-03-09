package com.pandawork.nenu.oa.common.dto.company;

/**
 * 根据输入匹配单位名称时用
 * Created by erdan on 2014/9/25.
 */
public class CompanyDto {

    private Integer id;

    //单位的中文名称
    private String cnName;

    //单位的首字母缩写
    private String enName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }
}
