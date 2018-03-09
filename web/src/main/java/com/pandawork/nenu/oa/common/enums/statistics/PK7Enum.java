package com.pandawork.nenu.oa.common.enums.statistics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zuosy on 2017/9/16.
 */
public enum PK7Enum {
    pk7_NULL(0,""),
    pk7_A(1, "学前教育"),
    pk7_B(2, "小学"),
    pk7_C(3, "初中"),
    pk7_D(4, "高中"),
    pk7_E(5, "职业学校"),
    pk7_F(6, "大中专学校"),
    pk7_G(7, "高校"),
    pk7_H(8, "科研机构"),
    pk7_I(9, "培训学校")
    ;


    private Integer id;
    private String name;

    PK7Enum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private static Map<Integer, PK7Enum> map = new HashMap<>();

    static{
        for(PK7Enum enums : PK7Enum.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static PK7Enum valueOf(int id) {
        return valueOf(id, null);
    }

    public static PK7Enum valueOf(int id, PK7Enum defaultValue) {
        PK7Enum enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }
}
