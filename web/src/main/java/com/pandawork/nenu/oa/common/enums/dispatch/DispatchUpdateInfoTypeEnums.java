package com.pandawork.nenu.oa.common.enums.dispatch;

import java.util.HashMap;
import java.util.Map;

/**
 * DispatchUpdateInfoTypeEnums
 * 学生派遣修改项枚举类
 * Created by ZhangBiLai on 2017/9/25.
 */
public enum DispatchUpdateInfoTypeEnums {

    WhereAboutGo(1,"毕业去向"),
    CompanyName(2,"签约单位名称"),
    OrganizationCode(3,"单位组织机构代码"),
    CityName(4,"单位所在地"),
    PropertyName(5,"单位性质"),
    TradeName(6,"单位行业"),
    SubordinateDepartment(7,"单位隶属部门"),
    Job(8,"工作职位类别"),
    FullAddress(9,"单位地址"),
    CompanyPostcode(10,"单位邮编"),
    CompanyContactPerson(11,"单位联系人"),
    ContactPersonFax(12,"单位联系人传真"),
    ContactPersonTele(13,"单位联系人电话"),
    ContactPersonMobile(14,"单位联系人手机号"),
    ContactPersonEmail(15,"单位联系人邮箱号"),
    ReportMode(16,"报到证类别"),
    ReportCompany(17,"报到证迁往单位"),
    ReportAddressName(18,"报到证迁往单位地址"),
    StudentRemark(19,"学生备注"),
    ;

    private Integer id;

    public Integer getId() {
        return id;
    }

    private String name;

    public String getName() {
        return name;
    }

    DispatchUpdateInfoTypeEnums (Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, DispatchUpdateInfoTypeEnums> map = new HashMap<Integer, DispatchUpdateInfoTypeEnums>();

    static {
        for (DispatchUpdateInfoTypeEnums enums : DispatchUpdateInfoTypeEnums.values()) {
            map.put(enums.getId(),enums);
        }
    }

    public static DispatchUpdateInfoTypeEnums valueOf(int id) {
        return valueOf(id, null);
    }

    public static DispatchUpdateInfoTypeEnums valueOf(int id, DispatchUpdateInfoTypeEnums defaultValue) {
        DispatchUpdateInfoTypeEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }



}
