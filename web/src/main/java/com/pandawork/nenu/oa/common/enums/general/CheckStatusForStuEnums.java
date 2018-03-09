package com.pandawork.nenu.oa.common.enums.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CheckStatusForStuEnums
 * 给学生详情页看的审核状态枚举
 *
 * @author wlm
 * @date 2016/12/16 8:43
 */
public enum CheckStatusForStuEnums {

    Saved(1, "已保存，等待提交"),
    Unchecked(2, "等待审核"),
    CounsellorNotThrough(3, "辅导员审核未通过，等待修改"),
    CounsellorModified(4, "修改已提交，等待辅导员再次审核"),
    CounsellorChecked(5, "辅导员审核通过，等待副书记审核"),
    DeputySecretaryNotThrough(6, "副书记审核未通过，等待修改"),
    DeputySecretaryModified(7, "修改已提交，等待副书记再次审核"),
    DeputySecretaryChecked(8, "副书记审核通过，等待学校审核"),
    SchoolNotThrough(9, "学校审核未通过，等待修改"),
    SchoolModified(10, "修改已提交，等待学校再次审核"),
    SchoolChecked(11, "学校审核通过，审核已全部通过"),
    SchoolDirectNotThrough(12, "学校直接审核未通过，等待修改"),
    SchoolDirectModified(13, "修改已提交，等待学校再次直接审核"),
    SchoolDirectChecked(14, "学校直接审核通过，审核已全部通过"),
    ;

    private Integer id;

    private String name;

    CheckStatusForStuEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, CheckStatusForStuEnums> map = new HashMap<Integer, CheckStatusForStuEnums>();

    static {
        for (CheckStatusForStuEnums enums : CheckStatusForStuEnums.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static CheckStatusForStuEnums valueOf(int id) {
        return valueOf(id, null);
    }

    public static CheckStatusForStuEnums valueOf(int id, CheckStatusForStuEnums defaultValue) {
        CheckStatusForStuEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public static List<CheckStatusForStuEnums> getModelList(){
        List<CheckStatusForStuEnums> modelList = new ArrayList<CheckStatusForStuEnums>();
        for (CheckStatusForStuEnums enums : CheckStatusForStuEnums.values()){
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
