package com.pandawork.nenu.oa.service.user;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.user.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色Service
 * user:ouym
 * date: 2014/9/13
 * time: 15:30
 */
public interface RoleService {
    /**
     * 创建一个角色
     * @param role
     * @return
     * @throws SSException
     */
    public Role addRole(Role role)throws SSException;


    /**
     * update一个角色
     * @param role
     * @return
     * @throws SSException
     */
    public void updateRole(Role role)throws SSException;

    /**
     * 根据角色id删除角色实体
     * @param roleId
     * @throws SSException
     */
    public void deleteRole(int roleId)throws SSException;



    /**
     * 根据用户id获取角色列表
     * @param userId
     * @return
     * @throws SSException
     */
    public List<Role> listRolesByUserId(int userId)throws SSException;

    /**
     * 获取角色总数
     * @return
     * @throws SSException
     */
    public int listRolesCount()throws SSException;

    /**
     * 获取所有角色列表
     * @return
     * @throws SSException
     */
    public List<Role> listRoles()throws SSException;


    /**
     * 根据角色id获取该角色名称
     * @param roleId
     * @return
     * @throws SSException
     */
    public String getRoleNameByRoleId(int roleId)throws SSException;

    /**
     * 根据角色id获取该角色
     * @param roleId
     * @return
     * @throws SSException
     */
    public Role getRoleByRoleId(Integer roleId)throws SSException;
}
