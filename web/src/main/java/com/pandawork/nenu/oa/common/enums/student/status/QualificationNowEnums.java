package com.pandawork.nenu.oa.common.enums.student.status;

import java.util.HashMap;
import java.util.Map;

/**
 * QualificationNowEnums
 *
 * @author wlm
 * @date 2016/9/19 15:56
 */
public enum QualificationNowEnums {

    Undergraduate (1, "本专科生"),
    Postgraduate(2, "研究生"),
    ;

    private Integer id;

    private String name;

    QualificationNowEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, QualificationNowEnums> map = new HashMap<Integer, QualificationNowEnums>();

    static {
        for (QualificationNowEnums enums : QualificationNowEnums.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static QualificationNowEnums valueOf(int id) {
        return valueOf(id, null);
    }

    public static QualificationNowEnums valueOf(int id, QualificationNowEnums defaultValue) {
        QualificationNowEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
