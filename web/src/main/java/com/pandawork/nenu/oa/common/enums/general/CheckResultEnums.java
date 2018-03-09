package com.pandawork.nenu.oa.common.enums.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CheckResultEnums
 * 审核结果枚举类
 *
 * @author wlm
 * @date 2016/7/12 14:34
 */
public enum CheckResultEnums {

    initial(0,""),
    Unchecked(1, "未审核"),
    Checked(2, "审核通过"),
    NotThrough(3, "审核未通过待修改"),
    Modified(4, "审核未通过已修改")
    ;

    private Integer id;

    private String name;

    CheckResultEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, CheckResultEnums> map = new HashMap<Integer, CheckResultEnums>();

    static {
        for (CheckResultEnums enums : CheckResultEnums.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static CheckResultEnums valueOf(int id) {
        return valueOf(id, null);
    }


    /**
     * 根据字符串返回id
     * @param string
     * @return
     */
    public static int StringToId(String string) {
        int id = 0;
        if(string == null){
            return 0;
        }else {
            for(CheckResultEnums enums : CheckResultEnums.values()){
                if(enums.getName().equals(string)){
                    id = enums.getId();
                }
            }
            return id;
        }

    }


    public static CheckResultEnums valueOf(int id, CheckResultEnums defaultValue) {
        CheckResultEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public static List<CheckResultEnums> getModelList(){
        List<CheckResultEnums> modelList = new ArrayList<CheckResultEnums>();
        for (CheckResultEnums enums : CheckResultEnums.values()){
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
