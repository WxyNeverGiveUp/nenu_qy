package com.pandawork.nenu.oa.common.dto.data;

/**
 * user: lishicao
 * date 15/5/6
 * time 下午2:37
 */
public class InformationStatisticDTO {
    private String dimension1;
    private String dimension2;
    private int count ;

    public String getDimension1() {
        return dimension1;
    }

    public void setDimension1(String dimension1) {
        this.dimension1 = dimension1;
    }

    public String getDimension2() {
        return dimension2;
    }

    public void setDimension2(String dimension2) {
        this.dimension2 = dimension2;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
