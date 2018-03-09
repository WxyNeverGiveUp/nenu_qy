package com.pandawork.nenu.oa.mapper.user;

import com.pandawork.nenu.oa.common.entity.user.StudentUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * StudentUserRoleMapper
 *
 * @author wlm
 * @date 2016/7/30 10:58
 */
public interface StudentUserRoleMapper {

    /**
     * 根据用户id列出角色
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public List<Integer> listRolesByUserId(@Param("userId") int userId) throws Exception;

    /**
     * 根据用户名查找角色(realm用)
     *
     * @param username
     * @return
     * @throws Exception
     */
    public Set<String> listRoles(@Param("username") String username) throws Exception;

    /**
     * 根据用户名查找角色(Interceptor用)
     *
     * @param username
     * @return
     * @throws Exception
     */
    public List<Integer> listRolesByUsername(@Param("username") String username)throws Exception;

    /**
     * 根据用户id查找所有角色记录
     * @param studentUserId
     * @return
     * @throws Exception
     */
    public List<StudentUserRole> listUsersRolesByStudentUserId(@Param("studentUserId") Integer studentUserId) throws Exception;
}
