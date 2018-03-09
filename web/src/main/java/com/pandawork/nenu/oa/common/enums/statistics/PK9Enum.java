package com.pandawork.nenu.oa.common.enums.statistics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zuosy on 2017/9/16.
 */
public enum PK9Enum {

    pk9_NULL(0, ""),
    pk9_A(1, "学校就业网"),
    pk9_B(2, "单位网站和宣传册"),
    pk9_C(3, "亲戚朋友介绍"),
    pk9_D(4, "招聘网站"),
    pk9_E(5, "导师推荐"),
    pk9_F(6, "朋辈引荐和内推"),
    pk9_G(7, "其他")
    ;


    private Integer id;
    private String name;

    PK9Enum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private static Map<Integer, PK9Enum> map = new HashMap<>();

    static{
        for(PK9Enum enums : PK9Enum.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static PK9Enum valueOf(int id) {
        return valueOf(id, null);
    }

    public static PK9Enum valueOf(int id, PK9Enum defaultValue) {
        PK9Enum enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }
}
