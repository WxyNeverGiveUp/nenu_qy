package com.pandawork.nenu.oa.service.user;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.user.User;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.subject.Subject;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chenyt
 * Date: 14-9-25
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
public interface UserRoleService {
    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     * SSException
     */
    public void addRelationRole(int userId,List<Integer> roleIds)throws SSException;

    /**
     * 根据id删除用户-角色关系
     * @param ids
     * @throws SSException
     */
    public void delRelationRole(List<Integer> ids)throws SSException;

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     * @throws SSException
     */
    public Set<String> listRoles(String username)throws SSException;

    /**
     * 根据用户Id获取角色
     * @param id
     * @return
     * @throws SSException
     */
    public List<Integer> listRoleByUserId(@Param("userId") int id) throws SSException;

    /**
     * 根据角色Id,获得用户信息
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<User> listUsersByRoleId(int roleId) throws SSException;

    public Integer getIdByEnum(Subject subject) throws SSException;

    /**
     * 
     * @param username
     * @return
     * @throws SSException
     */
    public List<Integer> getRolesByUsername(String username)throws SSException;

    /**
     * 根据用户id删除用户--角色关系
     * @param userId
     * @throws SSException
     */
    public void delRelation(int userId) throws SSException;

    /**
     * 根据角色列表，获取用户列表
     * @param roles
     * @return
     * @throws SSException
     */
    public List<Integer> queryUserIdByRoleIds(int[] roles)throws SSException;

    /**
     * 根据用户ID，获取权限ID
     * @param userId
     * @return
     * @throws SSException
     */
    public Integer RoleIdsByUserId(int userId)throws SSException;
}
