package com.pandawork.nenu.oa.common.enums.student.status;

import java.util.HashMap;
import java.util.Map;

/**
 * QualificationNowEnums
 *
 * @author wlm
 * @date 2016/9/19 15:56
 */
public enum QualificationEnums {

    Undergraduate (1, "本科专业"),
    Postgraduate(2, "研究生专业"),
    Zhuangraduate(3, "专科专业"),
    ;

    private Integer id;

    private String name;

    QualificationEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, QualificationEnums> map = new HashMap<Integer, QualificationEnums>();

    static {
        for (QualificationEnums enums : QualificationEnums.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static QualificationEnums valueOf(int id) {
        return valueOf(id, null);
    }

    public static QualificationEnums valueOf(int id, QualificationEnums defaultValue) {
        QualificationEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
