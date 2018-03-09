package com.pandawork.nenu.oa.mapper.user;

import com.pandawork.nenu.oa.common.entity.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chenyt
 * Date: 14-9-25
 * Time: 下午4:23
 * To change this template use File | Settings | File Templates.
 */
public interface UserRoleMapper {
    /**
     * 根据用户名查找角色
     * @param username
     * @return
     * @throws Exception
     */
    public Set<String> listRoles(@Param("username") String username) throws Exception;

    /**
     * 根据用户Id查找角色
     * @param id
     * @return
     * @throws Exception
     */
    public List<Integer> listRoleByUserId(@Param("id") int id) throws Exception;

    /**
     * 根据用户id查找用户角色关联表id
     * @param userId
     * @throws Exception
     */
    public List<Integer> listUserRoleIdByUserId (@Param("userId")int userId) throws Exception;

    /**
     * 根据角色Id,获得用户信息
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<User> listUsersByRoleId(@Param("roleId")int roleId) throws Exception;

    /**
     *根据用户名获取角色id列表
     * @param username
     * @return
     * @throws Exception
     */
    public List<Integer> getRolesByUsername(@Param("username")String username)throws Exception;

    /**
     * 根据角色列表获取用户列表
     * @param roles
     * @return
     * @throws Exception
     */
    public List<Integer> queryUserIdByRoleIds(@Param("roles")String roles) throws Exception;

    /**
     * 根据角色ID获取权限ID
     * @param userId
     * @return
     * @throws Exception
     */
    public Integer RoleIdsByUserId(@Param("userId")Integer userId) throws Exception;
}
