package com.pandawork.nenu.oa.common.enums.history;

import java.util.HashMap;
import java.util.Map;

/**
 * 单位行业枚举类
 * @author Lw
 * @time 2017/7/25 16:13
 */
public enum UnitIndustryEnum {
    CultureSportsEntertainment(1, "文化、体育和娱乐业"), Education(2, "教育"), PublicAndSocial(3, "公共管理、社会保障和社会组织"),
    ScientificAndTechnical(4, "科学研究与技术服务业"), ResidentRepairsAndOthers(5, "居民服务、修理和其他服务业"),
    Manufacturing(6, "制造业"), LeasingAndBusiness(7, "租赁和商务服务业"), BankingBusiness(8, "金融业"),
    ElectricityHeatGasAndWater(9, "电力、热力、燃气及水生产和供应业"), InfoAndSoftware(10, "信息传输、软件和信息技术服务业"),
    AgricultureForestryAnimalHusbandryAndFishery(11, "农、林、牧、渔业"), TransportationStorageAndPostal(12, "交通运输、仓储和邮政业"),
    WholesalendAndRetail(13, "批发和零售业"),  RealtyIndustry(14, "房地产业"), ConstructionBusiness(15, "建筑业"),
    HealthAndSocialWork(16, "卫生和社会工作"), Mining(17, "采矿业"), AccommodationAndCatering(18, "住宿和餐饮业"),
    Troops(19, "军队"), WaterEnvironmentAndPublic(20, "水利、环境和公共设施管理业");

    int id;
    String value;

    UnitIndustryEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    private static Map<Integer, UnitIndustryEnum> map = new HashMap<Integer, UnitIndustryEnum>();

    static {
        for (UnitIndustryEnum enums : UnitIndustryEnum.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static UnitIndustryEnum valueOf(int id) {
        return valueOf(id, null);
    }

    public static UnitIndustryEnum valueOf(int id, UnitIndustryEnum defaultValue) {
        UnitIndustryEnum enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueById(int id) {
        return valueOf(id).getValue();
    }
}
