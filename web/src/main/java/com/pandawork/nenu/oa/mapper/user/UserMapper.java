package com.pandawork.nenu.oa.mapper.user;

import com.pandawork.nenu.oa.common.dto.user.UserParamDto;
import com.pandawork.nenu.oa.common.entity.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 用户Mapper
 * user:
 * date:
 * time:
 */
public interface UserMapper {
    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     * @throws Exception
     */
    public User queryByUsername(@Param("username") String username) throws Exception;

    /**
     * 根据用户名查找权限
     *
     * @param username
     * @return
     * @throws Exception
     */
    public Set<String> listPermissions(@Param("username") String username) throws Exception;

    /**
     * 查询用户名在数据库中是否存在
     *
     * @param username
     * @return
     * @throws Exception
     */
    public int isExistUsername(@Param("username") String username) throws Exception;


    /**
     * 获取用户列表
     *
     * @param pCurrent
     * @param pSize
     * @param roleId
     *@param college @return
     * @throws Exception
     */
    public List<User> listUsers(@Param("pCurrent") int pCurrent, @Param("pSize") int pSize, @Param("roleId") String roleId, @Param("college") String college) throws Exception;

    /**
     * 获取用户总数
     *
     * @return
     * @throws Exception
     */
    public int listUsersCount(@Param("roleId") String roleId, @Param("college") String college) throws Exception;

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    public User queryUserById(@Param("id") int id) throws Exception;

    /**
     * 根据用户Id删除用户
     *
     * @param id
     * @throws Exception
     */
    public User delUserById(@Param("id") int id) throws Exception;

    /**
     * 获取所有的用户参数，实体中只有id和realName
     *
     * @return
     * @throws Exception
     */
    public List<UserParamDto> listUserParams() throws Exception;

    /**
     * 判断用户名是否重复，更新时用
     *
     * @param username
     * @param id
     * @return
     * @throws Exception
     */
    public int isExistUsernameById(@Param("username") String username, @Param("id") int id) throws Exception;

    /**
     * 根据id查询真实姓名
     *
     * @param id
     * @return
     * @throws Exception
     */
    public String queryRealNameById(@Param("id") int id) throws Exception;


    /**
     * 根据username获取这个用户的角色id
     *
     * @param username
     * @return
     * @throws Exception
     */
    public List<Integer> queryRoleIdByUserName(@Param("username") String username) throws Exception;

}
