package com.pandawork.nenu.oa.common.enums.statistics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zuosy on 2017/9/16.
 */
public enum PK2Enum {
    pk2_A(1,"东北师范大学"),
    pk2_B(2,"\"985工程\"院校"),
    pk2_C(3,"\"211工程\"院校"),
    pk2_D(4,"其他")
    ;

    private Integer id;
    private String name;

    PK2Enum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private static Map<Integer, PK2Enum> map = new HashMap<>();

    static{
        for(PK2Enum enums : PK2Enum.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static PK2Enum valueOf(int id) {
        return valueOf(id, null);
    }

    public static PK2Enum valueOf(int id, PK2Enum defaultValue) {
        PK2Enum enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }
}
