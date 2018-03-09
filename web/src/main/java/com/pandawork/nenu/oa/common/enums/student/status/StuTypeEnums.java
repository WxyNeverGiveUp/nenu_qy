package com.pandawork.nenu.oa.common.enums.student.status;

import java.util.HashMap;
import java.util.Map;

/**
 * StuTypeEnums
 *
 * @author wlm
 * @date 2016/9/19 15:42
 */
public enum  StuTypeEnums {

    SystemImport(1, "系统导入"),
    AdminInsert(2, "管理员新增"),
    AdminDelete(3, "管理员删除"),
    ;

    private Integer id;

    private String name;

    StuTypeEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, StuTypeEnums> map = new HashMap<Integer, StuTypeEnums>();

    static {
        for (StuTypeEnums enums : StuTypeEnums.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static StuTypeEnums valueOf(int id) {
        return valueOf(id, null);
    }

    public static StuTypeEnums valueOf(int id, StuTypeEnums defaultValue) {
        StuTypeEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
