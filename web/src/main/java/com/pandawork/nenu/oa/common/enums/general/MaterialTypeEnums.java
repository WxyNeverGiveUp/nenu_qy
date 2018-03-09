package com.pandawork.nenu.oa.common.enums.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MaterialTypeEnums
 *
 * @author wlm
 * @date 2016/7/21 14:50
 */
public enum  MaterialTypeEnums {
    StatusInfo(1, "学籍"),
    Dispatch(2, "派遣"),
    IdentityChange(3, "身份变更"),
    ApplyNewAgreement(4, "申领新协议"),
    Graduate(5,"毕业去向变更协议"),
    FreeTeacher(6,"免费师范生业务"),
    Directional(7,"定向、委培业务"),
    DoctorBusiness(8,"博士生领协议业务")

    ;

    private Integer id;

    private String name;

    MaterialTypeEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, MaterialTypeEnums> map = new HashMap<Integer, MaterialTypeEnums>();

    static {
        for (MaterialTypeEnums enums : MaterialTypeEnums.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static MaterialTypeEnums valueOf(int id) {
        return valueOf(id, null);
    }

    public static MaterialTypeEnums valueOf(int id, MaterialTypeEnums defaultValue) {
        MaterialTypeEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public static List<MaterialTypeEnums> getModelList(){
        List<MaterialTypeEnums> modelList = new ArrayList<MaterialTypeEnums>();
        for (MaterialTypeEnums enums : MaterialTypeEnums.values()){
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
