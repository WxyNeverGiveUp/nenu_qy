package com.pandawork.nenu.oa.common.enums.history;

import java.util.HashMap;
import java.util.Map;

/**
 * 单位性质枚举类
 * @author Lw
 * @time 2017/7/25 15:41
 */
public enum UnitPropertyEnum {
    OtherCompany(1, "其他企业"), StateOwnedCompany(2, "国有企业"), WaitingForEmployment(3, "待就业"),
    OtherTeachingUnits(4, "其他教学单位"), OtherFlexibleEmployment(5, "其他灵活就业"), FreeEmployment(6, "自由职业"),
    GoAbroad(7, "出国、出境"), HigherSchool(8, "升学"), OtherInstitutions(9, "其他事业单位"), Organ(10, "机关"),
    Troops(11, "部队"), HigherEducationUnit(12, "高等教育单位"), Others(13, "其他"), ResearchDesignUnit(14, "科研设计单位"),
    SecondaryEducation(15, "中等教育单位"), MedicalHealthUnit(16, "医疗卫生单位"), ForeignFundedEnterprise(17, "三资企业"),
    Community(18, "城镇社区"), RuralVillageConstruction(19, "农村建制村");

    int id;
    String value;

    UnitPropertyEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }
    private static Map<Integer, UnitPropertyEnum> map = new HashMap<Integer, UnitPropertyEnum>();

    static {
        for (UnitPropertyEnum enums : UnitPropertyEnum.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static UnitPropertyEnum valueOf(int id) {
        return valueOf(id, null);
    }

    public static UnitPropertyEnum valueOf(int id, UnitPropertyEnum defaultValue) {
        UnitPropertyEnum enums = map.get(id);
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
