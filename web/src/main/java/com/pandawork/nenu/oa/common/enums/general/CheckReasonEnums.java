package com.pandawork.nenu.oa.common.enums.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 审核理由枚举类
 * User: fujia
 * Date: 2016/8/28
 * Time: 14:38
 */
public enum CheckReasonEnums {

    Authentic(1, "信息属实"),
    NormalError(2, "修改师范生类型不通过，证明材料不充分"),
    NativePlaceError(3, "修改生源地城市不通过，证明材料不充分"),
    WeipeiTypeError(4, "修改委培方式不通过，证明材料不充分"),
    WeipeiUnitError(5, "修改定向委培单位不通过，证明材料不充分"),
    InformationError(6, "个人信息有错误"),
    MaterialError(7, "材料不规范"),
    MaterialNotComplete(8, "材料不齐全"),
    ElseReason(99, "其他");

    private Integer id;

    private String name;

    CheckReasonEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, CheckReasonEnums> map = new HashMap<Integer, CheckReasonEnums>();

    static {
        for (CheckReasonEnums enums : CheckReasonEnums.values()) {
            map.put(enums.getId(), enums);
        }
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
            for(CheckReasonEnums enums : CheckReasonEnums.values()){
                if(enums.getName().equals(string)){
                    id = enums.getId();
                }
            }
            return id;
        }

    }


    public static CheckReasonEnums valueOf(int id) {
        return valueOf(id, null);
    }

    public static CheckReasonEnums valueOf(int id, CheckReasonEnums defaultValue) {
        CheckReasonEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public static List<CheckReasonEnums> getModelList(){
        List<CheckReasonEnums> modelList = new ArrayList<CheckReasonEnums>();
        for (CheckReasonEnums enums : CheckReasonEnums.values()){
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
