package com.pandawork.nenu.oa.common.enums.user;

/**
 * Created with IntelliJ IDEA.
 * User: wanghq
 * Date: 2014/10/15
 * Time: 18:03
 */
public enum RoleHandleState {
    Branch(3,0),
    Director(2,0),
    Complex(5,0),
    Communicate(10,0);


    private int roleId;
    private int handleState;

    RoleHandleState(int roleId, int handleState) {
        this.roleId = roleId;
        this.handleState = handleState;
    }
}
