package com.pandawork.nenu.oa.common.enums.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CheckStatusEnums
 * 审核状态枚举
 *
 * @author wlm
 * @date 2016/8/25 17:59
 */
public enum CheckStatusEnums {

    Saved(1, "已保存未提交"),
    Unchecked(2, "未审核"),
    CounsellorNotThrough(3, "辅导员审核未通过待修改"),
    CounsellorModified(4, "辅导员审核未通过已修改"),
    CounsellorChecked(5, "辅导员审核通过"),
    DeputySecretaryNotThrough(6, "副书记审核未通过待修改"),
    DeputySecretaryModified(7, "副书记审核未通过已修改"),
    DeputySecretaryChecked(8, "副书记审核通过"),
    SchoolNotThrough(9, "学校审核未通过待修改"),
    SchoolModified(10, "学校审核未通过已修改"),
    SchoolChecked(11, "学校审核通过"),
    SchoolDirectNotThrough(12, "学校直接审核未通过待修改"),
    SchoolDirectModified(13, "学校直接审核未通过已修改"),
    SchoolDirectChecked(14, "学校直接审核通过"),
    ;

    private Integer id;

    private String name;

    CheckStatusEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, CheckStatusEnums> map = new HashMap<Integer, CheckStatusEnums>();

    static {
        for (CheckStatusEnums enums : CheckStatusEnums.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static CheckStatusEnums valueOf(int id) {
        return valueOf(id, null);
    }

    public static CheckStatusEnums valueOf(int id, CheckStatusEnums defaultValue) {
        CheckStatusEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public static List<CheckStatusEnums> getModelList(){
        List<CheckStatusEnums> modelList = new ArrayList<CheckStatusEnums>();
        for (CheckStatusEnums enums : CheckStatusEnums.values()){
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
