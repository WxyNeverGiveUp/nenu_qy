package com.pandawork.nenu.oa.common.enums.statistics;

import java.util.HashMap;
import java.util.Map;

/**20个 就业意向所属行业枚举类
 * Created by Zuosy on 2017/9/16.
 */
public enum PK6Enum {

    pk6_A(1, "教育"),
    pk6_B(2, "农、林、牧、渔业"),
    pk6_C(3, "采矿业"),
    pk6_D(4, "制造业"),
    pk6_E(5, "电力、热力、燃气及水生产和供应业"),
    pk6_F(6, "建筑业"),
    pk6_G(7, "批发和零售业"),
    pk6_H(8, "交通运输、仓储和邮政业"),
    pk6_I(9, "住宿和餐饮业"),
    pk6_J(10, "信息传输、软件和信息技术服务业"),
    pk6_K(11, "金融业"),
    pk6_L(12, "房地产业"),
    pk6_M(13, "租赁和商业服务业"),
    pk6_N(14, "科学研究和技术服务业"),
    pk6_O(15, "水利、环境和公共设施管理业"),
    pk6_P(16, "居民服务、修理和其他服务业"),
    pk6_Q(17, "卫生和社会工作"),
    pk6_R(18, "文化、体育和娱乐业"),
    pk6_S(19, "公共管理、社会保障和社会组织"),
    pk6_T(20, "国际组织")
    ;



    private Integer id;
    private String name;

    PK6Enum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private static Map<Integer, PK6Enum> map = new HashMap<>();

    static{
        for(PK6Enum enums : PK6Enum.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static PK6Enum valueOf(int id) {
        return valueOf(id, null);
    }

    public static PK6Enum valueOf(int id, PK6Enum defaultValue) {
        PK6Enum enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }
}
