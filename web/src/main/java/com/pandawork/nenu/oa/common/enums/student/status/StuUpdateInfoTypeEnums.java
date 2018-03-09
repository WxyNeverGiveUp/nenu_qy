package com.pandawork.nenu.oa.common.enums.student.status;

import java.util.HashMap;
import java.util.Map;

/**
 * StuUpdateInfoTypeEnums
 *
 * @author wlm
 * @date 2016/7/22 10:45
 */
public enum StuUpdateInfoTypeEnums {

    NormalStudentType(1, "师范生类别"),
    OriginPlace(2, "生源所在地"),
    TrainingMode(3, "培养方式"),
    WeipeiUnit(4, "定向委培单位"),
    Name(5,"姓名"),
    IdNumber(6,"身份证号"),
    CandidateNumber(7,"考生号"),
    StudentNumber(8,"学号"),
    Sex(9,"性别"),
    Nation(10,"民族"),
    PoliticalStand(11,"政治面貌"),
    StuLength(12,"学制"),
    Qualification(13,"学历"),
    College(14,"所在院系/分院"),
    Grade(15,"所在年级"),
    MajorQualification(16,"专业层次"),
    Major(17,"专业"),
    IntendWhereabouts(18,"拟毕业去向"),
    Difficulty(19,"困难生类别"),
    WeipeiUnitPlace(20,"定向/委培单位地址"),
    RegistDate(21,"入学日期"),
    GraduationDate(22,"毕业日期"),
    PreHukouLocation(23,"入学前户口所在地"),
    IsHukouIntoSchool(24,"户口是否转入学校"),
    PreArchiveLocation(25,"入学前档案所在地"),
    IsArchiveIntoSchool(26,"档案是否转入学校"),
    Cellphone(27,"手机号码"),
    CellphoneBak(28,"手机号码（备用）"),
    Qq(29,"QQ号码"),
    Email(30,"常用电子邮箱"),
    HomePhone(31,"家庭电话"),
    RelativePhone(32,"紧急联系方式"),
    Weixin(33,"微信号"),
    HomeAddress(34,"家庭住址"),
    Dormitory(35,"寝室楼"),
    DormitoryNum(36,"寝室号")
    ;

    private Integer id;

    private String name;

    StuUpdateInfoTypeEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, StuUpdateInfoTypeEnums> map = new HashMap<Integer, StuUpdateInfoTypeEnums>();

    static {
        for (StuUpdateInfoTypeEnums enums : StuUpdateInfoTypeEnums.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static StuUpdateInfoTypeEnums valueOf(int id) {
        return valueOf(id, null);
    }

    public static StuUpdateInfoTypeEnums valueOf(int id, StuUpdateInfoTypeEnums defaultValue) {
        StuUpdateInfoTypeEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
