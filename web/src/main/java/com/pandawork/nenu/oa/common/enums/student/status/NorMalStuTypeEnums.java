package com.pandawork.nenu.oa.common.enums.student.status;

import java.util.HashMap;
import java.util.Map;

/**
 * NorMalStuTypeEnums
 *
 * @author wlm
 * @date 2016/7/28 15:11
 */
public enum NorMalStuTypeEnums {

    NormalStudents(1, "普通师范生"),
    FreeNormalStudents(12, "免费师范生"),
    NotNormalStudents(2, "非师范生")
    ;

    private Integer id;

    private String name;

    NorMalStuTypeEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, NorMalStuTypeEnums> map = new HashMap<Integer, NorMalStuTypeEnums>();

    static {
        for (NorMalStuTypeEnums enums : NorMalStuTypeEnums.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static NorMalStuTypeEnums valueOf(int id) {
        return valueOf(id, null);
    }

    public static NorMalStuTypeEnums valueOf(int id, NorMalStuTypeEnums defaultValue) {
        NorMalStuTypeEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
