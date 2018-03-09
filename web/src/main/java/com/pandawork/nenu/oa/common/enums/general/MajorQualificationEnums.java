package com.pandawork.nenu.oa.common.enums.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MajorQualificationEnums
 * 专业层次枚举
 *
 * @author wlm
 * @date 2016/12/27 15:04
 */
public enum MajorQualificationEnums {
    Undergraduate(1, "本科专业"),
    Graduate(2, "研究生专业"),
    Specialty(3, "专科专业")
    ;

    private Integer id;

    private String name;

    MajorQualificationEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, MajorQualificationEnums> map = new HashMap<Integer, MajorQualificationEnums>();

    static {
        for (MajorQualificationEnums enums : MajorQualificationEnums.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static MajorQualificationEnums valueOf(int id) {
        return valueOf(id, null);
    }

    public static MajorQualificationEnums valueOf(int id, MajorQualificationEnums defaultValue) {
        MajorQualificationEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public static List<MajorQualificationEnums> getModelList(){
        List<MajorQualificationEnums> modelList = new ArrayList<MajorQualificationEnums>();
        for (MajorQualificationEnums enums : MajorQualificationEnums.values()){
            modelList.add(enums);
        }
        return modelList;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
