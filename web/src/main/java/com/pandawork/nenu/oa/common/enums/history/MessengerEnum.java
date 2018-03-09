package com.pandawork.nenu.oa.common.enums.history;

import java.util.HashMap;
import java.util.Map;

/**
 * 信使枚举类
 * @author Lw
 * @time 2017/7/25 14:40
 */
public enum MessengerEnum {

    IsMessenger(1, "是"), NotMessenger(2, "否");

    int id;
    String value;

    MessengerEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    private static Map<Integer, MessengerEnum> map = new HashMap<Integer, MessengerEnum>();

    static {
        for (MessengerEnum enums : MessengerEnum.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static MessengerEnum valueOf(int id) {
        return valueOf(id, null);
    }

    public static MessengerEnum valueOf(int id, MessengerEnum defaultValue) {
        MessengerEnum enums = map.get(id);
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
