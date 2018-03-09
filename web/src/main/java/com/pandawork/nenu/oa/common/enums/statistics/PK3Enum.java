package com.pandawork.nenu.oa.common.enums.statistics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zuosy on 2017/9/16.
 */
public enum PK3Enum {

    pk3_NULL(0, ""),
    pk3_A(1, "信息获取"),
    pk3_B(2, "简历制作"),
    pk3_C(3, "网申技巧"),
    pk3_D(4, "求职礼仪"),
    pk3_E(5, "面试技巧"),
    pk3_F(6, "就业心理咨询"),
    pk3_G(7, "教师应聘指导"),
    pk3_H(8, "教师技能训练"),
    pk3_I(9, "公考指导"),
    pk3_J(10, "基层项目指导"),
    pk3_K(11, "政策解读"),
    pk3_L(12, "其他")
    ;

    private Integer id;
    private String name;

    PK3Enum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private static Map<Integer, PK3Enum> map = new HashMap<>();

    static{
        for(PK3Enum enums : PK3Enum.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static PK3Enum valueOf(int id) {
        return valueOf(id, null);
    }

    public static PK3Enum valueOf(int id, PK3Enum defaultValue) {
        PK3Enum enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }
}
