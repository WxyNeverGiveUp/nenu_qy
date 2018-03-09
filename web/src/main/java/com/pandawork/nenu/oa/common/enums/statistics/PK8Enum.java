package com.pandawork.nenu.oa.common.enums.statistics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zuosy on 2017/9/16.
 */
public enum PK8Enum {

    pk8_A(1, "国有企业"),
    pk8_B(2, "中央企业"),
    pk8_C(3, "民营企业"),
    pk8_D(4, "中外合资经营企业"),
    pk8_E(5, "中外合作经营企业"),
    pk8_F(6, "外商投资企业"),
    pk8_G(7, "联营企业"),
    pk8_H(8, "集体所有制企业"),
    pk8_I(9, "其他企业"),
    pk8_J(10, "事业单位")
    ;


    private Integer id;
    private String name;

    PK8Enum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private static Map<Integer, PK8Enum> map = new HashMap<>();

    static{
        for(PK8Enum enums : PK8Enum.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static PK8Enum valueOf(int id) {
        return valueOf(id, null);
    }

    public static PK8Enum valueOf(int id, PK8Enum defaultValue) {
        PK8Enum enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }
}
