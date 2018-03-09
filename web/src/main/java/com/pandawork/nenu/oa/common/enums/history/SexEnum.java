package com.pandawork.nenu.oa.common.enums.history;

import java.util.HashMap;
import java.util.Map;

/**
 * 性别枚举类
 * @author Lw
 * @time 2017/7/25 14:54
 */
public enum SexEnum {
    Female(2, "女"), Male(1, "男");

    int id;
    String value;

    SexEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    private static Map<Integer, SexEnum> map = new HashMap<Integer, SexEnum>();

    static {
        for (SexEnum enums : SexEnum.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static SexEnum valueOf(int id) {
        return valueOf(id, null);
    }

    public static SexEnum valueOf(int id, SexEnum defaultValue) {
        SexEnum enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueById(int id) {
        return valueOf(id).getValue();
    }
}
