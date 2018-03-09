package com.pandawork.nenu.oa.service.user;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.user.StudentUserRole;

import java.util.List;
import java.util.Set;

/**
 * StudentUserRoleService
 * 学生-角色service
 *
 * @author wlm
 * @date 2016/7/30 9:46
 */
public interface StudentUserRoleService {

    /**
     * 新增学生-角色关系
     *
     * @param studentUserRole
     * @return
     * @throws SSException
     */
    public StudentUserRole newRoleRelation(StudentUserRole studentUserRole) throws SSException;

    /**
     * 根据用户id列出角色
     *
     * @param userId
     * @return
     * @throws SSException
     */
    public List<Integer> listRolesByUserId(int userId) throws SSException;

    /**
     * 根据用户名查找角色(realm用)
     *
     * @param username
     * @return
     * @throws SSException
     */
    public Set<String> listRoles(String username) throws SSException;

    /**
     * 根据用户名查找角色(Interceptor用)
     *
     * @param username
     * @return
     * @throws SSException
     */
    public List<Integer> listRolesByUsername(String username)throws SSException;

    /**
     * 根据用户id查找所有角色记录
     * @param studentUserId
     * @return
     * @throws SSException
     * @Author chenyuting
     */
    public List<StudentUserRole> listUsersRolesByStudentUserId(Integer studentUserId) throws SSException;
}
