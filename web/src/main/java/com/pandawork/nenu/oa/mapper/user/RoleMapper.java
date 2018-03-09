package com.pandawork.nenu.oa.mapper.user;

import com.pandawork.nenu.oa.common.entity.user.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenyt
 * Date: 14-9-24
 * Time: 下午4:33
 * To change this template use File | Settings | File Templates.
 */
public interface RoleMapper {
    /**
     * 根据用户id获取角色列表
     * @param userId
     * @return
     * @throws Exception
     */
    public List<Role> listRolesByUserId(@Param("userId")int userId)throws Exception;

    /**
     * 获取角色总数
     * @return
     * @throws Exception
     */
    public int listRolesCount()throws Exception;

    /**
     * 获取所有角色列表
     * @return
     * @throws Exception
     */
    public List<Role> listRoles()throws Exception;



    /**
     * 根据角色id获取该角色名称
     * @return
     * @throws Exception
     */
    public String getRoleNameByRoleId(int roleId);

    /**
     * 根据角色id获取该角色
     * @return
     * @throws Exception
     */
    public Role getRoleByRoleId(Integer roleId);

    //public Integer getRoleByRoleId(String name);
}
