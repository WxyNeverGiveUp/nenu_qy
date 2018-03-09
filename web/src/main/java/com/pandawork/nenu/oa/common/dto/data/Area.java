package com.pandawork.nenu.oa.common.dto.data;

import java.util.List;

/**
 * user: lishicao
 * date 15/4/23
 * time 下午11:37
 */
public class Area {
    private String areaName;
    private List<Province> provincesInArea;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<Province> getProvincesInArea() {
        return provincesInArea;
    }

    public void setProvincesInArea(List<Province> provincesInArea) {
        this.provincesInArea = provincesInArea;
    }
}
