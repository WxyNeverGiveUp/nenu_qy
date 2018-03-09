package com.pandawork.nenu.oa.common.enums.general;

import java.util.HashMap;
import java.util.Map;

/**
 * TrueFalseEnums
 * 通用是否枚举
 *
 * @author wlm
 * @date 2016/4/18 15:08
 */
public enum TrueFalseEnums {

    True(1, "是"),
    False(0, "否");

    private Integer id;

    private String name;

    TrueFalseEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, TrueFalseEnums> map = new HashMap<Integer, TrueFalseEnums>();

    static {
        for (TrueFalseEnums enums : TrueFalseEnums.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static TrueFalseEnums valueOf(int id) {
        return valueOf(id, null);
    }

    public static TrueFalseEnums valueOf(int id, TrueFalseEnums defaultValue) {
        TrueFalseEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
