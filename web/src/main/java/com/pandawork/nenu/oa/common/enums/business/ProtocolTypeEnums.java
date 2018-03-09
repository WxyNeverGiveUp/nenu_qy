package com.pandawork.nenu.oa.common.enums.business;

import java.util.HashMap;
import java.util.Map;

/**
 * description:申请新协议理由枚举类
 * user: lusi
 * date:2016/7/15
 * time:15:18
 */
public enum ProtocolTypeEnums {

    initialStatus(1,"无变更"),

    //申领新协议
    protocolStained(2, "协议污损"),
    protocolLost(3, "协议丢失"),
    TerminationOfAgreement (4 , "协议解约领新协议"),
    DoctorPostgraduate(12,"博士研究生申请新协议"),

    //毕业去向变更领协议
    postgraduateDoctorAbandon(5, "放弃考研/博领协议"),
    abroadAbandon(6,"放弃出国/出境领协议"),

    //免费师范生业务
    teacherBreak(7,"免师解约"),
    teacherProvince(8,"免师跨省"),

    //定向委培业务
    direction(9,"定向生领协议"),
    sentBreak(10,"委培生解约领协议"),
    directionBreak(11,"定向生解约"),

    //博士申领协议
    doctor(12,"申领协议")
    ;

    private Integer id;

    private String name;

    ProtocolTypeEnums(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private static Map<Integer, ProtocolTypeEnums> map = new HashMap<Integer, ProtocolTypeEnums>();

    static {
        for (ProtocolTypeEnums enums : ProtocolTypeEnums.values()) {
            map.put(enums.getId(), enums);
        }
    }

    public static ProtocolTypeEnums valueOf(int id) {
        return valueOf(id, null);
    }

    public static ProtocolTypeEnums valueOf(int id, ProtocolTypeEnums defaultValue) {
        ProtocolTypeEnums enums = map.get(id);
        return enums == null ? defaultValue : enums;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
