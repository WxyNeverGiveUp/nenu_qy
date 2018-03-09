package com.pandawork.nenu.oa.service.user;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.oa.common.dto.user.UserDto;
import com.pandawork.nenu.oa.common.dto.user.UserParamDto;
import com.pandawork.nenu.oa.common.entity.user.User;

import java.util.List;

/**
 * 用户Service
 * user:ouym
 * date: 2014/9/13
 * time: 17:09
 */
public interface UserService {
    /**
     * 创建一个用户
     *
     * @param user
     * @return
     * @throws SSException
     */
    public User addUser(User user) throws SSException;

    /**
     * 给password加密
     *
     * @param user
     * @return
     * @throws SSException
     */
    public String encryptPassword(User user) throws SSException;

    /**
     * 修改密码
     *
     * @param username
     * @param password
     * @param newPassword
     * @throws SSException
     */
    public void updatePassword(String username, String password, String newPassword) throws SSException;

    /**
     * 修改密码
     *
     * @param id
     * @param password
     * @param newPassword
     * @throws SSException
     */
    public void updatePassword(int id, String password, String newPassword) throws SSException;

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     * @throws SSException
     */
    public User queryByUsername(String username) throws SSException;

    /**
     * 判断用户名是否已存在
     *
     * @param username
     * @return
     * @throws SSException
     */
    public boolean isExistUsername(String username) throws SSException;

    /**
     * 判断用户名密码是否重复，更新时用
     *
     * @param username
     * @param id
     * @return
     * @throws SSException
     */
    public boolean isExistUsername(String username, int id) throws SSException;

    /**
     * 获取用户列表，分页
     *
     * @param page
     * @param roleId 角色ID
     * @param college 学院
     * @return
     * @throws SSException
     */
    public List<UserDto> listUsers(Pagination page, String roleId, String college) throws SSException;

    /**
     * 获取用户总数
     *
     * @return
     * @throws SSException
     */
    public int listUsersCount(String roleId,String college) throws SSException;

    /**
     * 修改用户信息
     *
     * @param user
     * @throws SSException
     */
    public void updateUser(User user) throws SSException;

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     * @throws SSException
     */
    public User queryUserById(int id) throws SSException;

    /**
     * 根据用户Id删除用户
     *
     * @param id
     * @return
     * @throws SSException
     */
    public void delUserById(int id) throws SSException;

    /**
     * 获取所有的用户参数，实体中只有id和realName
     *
     * @return
     * @throws SSException
     */
    public List<UserParamDto> listUserParams() throws SSException;

    /**
     * 加锁,解锁
     *
     * @return
     * @throws SSException
     */
    public void lock(User user) throws SSException;

    /**
     * 根据id查询真实姓名
     *
     * @param id
     * @return
     * @throws SSException
     */
    public String queryRealNameById(int id) throws SSException;

    /**
     * 根据username获取这个用户的角色id
     *
     * @param username
     * @return
     * @throws Exception
     */
    public List<Integer> queryRoleIdByUserName(String username) throws SSException;

    /**
     * 超级管理员修改其他用户密码
     *
     * @param id
     * @param newPassword
     * @throws SSException
     */
    public void updatePasswordNext(int id, String newPassword) throws SSException;
}
