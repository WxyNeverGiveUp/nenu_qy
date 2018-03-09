package com.pandawork.nenu.oa.common.enums.history;

import java.util.HashMap;
import java.util.Map;

/**
 * 毕业去向枚举类
 * @author Lw
 * @time 2017/7/25 14:57
 */
public enum WhereaboutGoEnum {

    WaitingForEmployment(1, "待就业"), HigherSchool(2, "升学"), SignEmploymentForm(3, "签就业协议形式就业"),
    FlexibleEmployment(4, "灵活就业"), SignLaborContract(5, "签劳动合同形式就业"), OtherFormEmployment(6, "其他录用形式就业"),
    FreeEmployment(7, "自由职业"), GoAbroad(8, "出国、出境"), SelfEmployed(9, "自主创业"), LocalProject(10, "地方基层项目"),
    OtherTemporaryEmployment(11, "其他暂不就业"), NotEntranceOfEmployment(12, "不就业拟升学"), NationalProject(13, "国家基层项目"),
    ForConscripts(14, "应征义务兵"), ResearchAssistant(15, "科研助理");

    int id;
    String value;

    WhereaboutGoEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }


    private static Map<Integer, WhereaboutGoEnum> map = new HashMap<Integer, WhereaboutGoEnum>();

    static {
        for (WhereaboutGoEnum enums : WhereaboutGoEnum.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static WhereaboutGoEnum valueOf(int id) {
        return valueOf(id, null);
    }

    public static WhereaboutGoEnum valueOf(int id, WhereaboutGoEnum defaultValue) {
        WhereaboutGoEnum enums = map.get(id);
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
