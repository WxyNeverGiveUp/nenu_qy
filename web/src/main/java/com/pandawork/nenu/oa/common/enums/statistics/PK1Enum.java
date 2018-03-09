package com.pandawork.nenu.oa.common.enums.statistics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zuosy on 2017/9/16.
 */
public enum PK1Enum {
    pk1_NULL(0,""),
    pk1_A(1,"就业"),
    pk1_B(2,"考公务员"),
    pk1_C(3,"考研升学"),
    pk1_D(4,"保研升学"),
    pk1_E(5,"出国、出境"),
    pk1_F(6,"自主创业"),
    pk1_G(7,"参军入伍"),
    pk1_H(8,"不就业")
    ;

    private Integer id;
    private String name;

    PK1Enum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private static Map<Integer, PK1Enum> map = new HashMap<>();

    static{
        for(PK1Enum enums : PK1Enum.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static PK1Enum valueOf(int id) {
        return valueOf(id, null);
    }

    public static PK1Enum valueOf(int id, PK1Enum defaultValue) {
        PK1Enum enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }
}
