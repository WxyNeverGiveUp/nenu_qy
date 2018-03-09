package com.pandawork.nenu.oa.common.enums.user;

/**
 * Created with IntelliJ IDEA.
 * User: wanghq
 * Date: 2014/10/14
 * Time: 11:10
 */
public enum RoleEnums {

    Admin(1,"超级管理员"),
    Director(2,"主管领导"),
    Branch(3,"分管领导"),
    Layout(4,"企划办公室主任"),
    Complex(5,"综合办公室主任"),
    Employ(6,"就业中心工作人员"),
    WorkLay(7,"工作-企划"),
    WorkComp(8,"工作-综合"),
    CompInfo(9,"综合-信息"),
    Communicate(10,"联络人");

    private int id;
    private String description;
    RoleEnums(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
