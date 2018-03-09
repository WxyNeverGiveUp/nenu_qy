package com.pandawork.nenu.oa.common.enums.history;

import java.util.HashMap;
import java.util.Map;

/**
 * 专业层次枚举类
 * @author Lw
 * @time 2017/7/25 16:34
 */
public enum MajorQualificationEnum {
    Undergraduate(2, "本科层次"), Postgraduate(1, "研究生层次");

    int id;
    String value;

    MajorQualificationEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    private static Map<Integer, MajorQualificationEnum> map = new HashMap<Integer, MajorQualificationEnum>();

    static {
        for (MajorQualificationEnum enums : MajorQualificationEnum.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static MajorQualificationEnum valueOf(int id) {
        return valueOf(id, null);
    }

    public static MajorQualificationEnum valueOf(int id, MajorQualificationEnum defaultValue) {
        MajorQualificationEnum enums = map.get(id);
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
