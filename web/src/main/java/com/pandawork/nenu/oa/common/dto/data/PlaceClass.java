package com.pandawork.nenu.oa.common.dto.data;

/**
 * user: lishicao
 * date 15/4/24
 * time 上午1:26
 */
public class PlaceClass {
    private int placeClassCode;
    private String placeClassName;

    public PlaceClass( int code , String name ) {
        placeClassCode = code ;
        placeClassName = name ;
    }

    public int getPlaceClassCode() {
        return placeClassCode;
    }

    public void setPlaceClassCode(int placeClassCode) {
        placeClassCode = placeClassCode;
    }

    public String getPlaceClassName() {
        return placeClassName;
    }

    public void setPlaceClassName(String placeClassName) {
        placeClassName = placeClassName;
    }
}
