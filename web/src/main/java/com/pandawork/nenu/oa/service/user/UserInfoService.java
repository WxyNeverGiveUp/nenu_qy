package com.pandawork.nenu.oa.service.user;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.user.UserInfoDto;
import com.pandawork.nenu.oa.common.entity.user.UserInfo;

import java.util.List;

/**
 * UserInfoService
 * 用户信息Service
 *
 * @author wlm
 * @date 2016/9/22 16:09
 */
public interface UserInfoService {

    /**
     * 新增用户信息
     *
     * @param userInfo
     * @return
     * @throws SSException
     */
    public UserInfo newUserInfo(UserInfo userInfo) throws SSException;

    /**
     * 修改用户信息
     *
     * @param userInfo
     * @throws SSException
     */
    public void updateById(UserInfo userInfo) throws SSException;

    /**
     * 删除用户信息
     *
     * @param id
     * @throws SSException
     */
    public void delById(int id) throws SSException;

    /**
     * 根据用户id删除用户信息
     *
     * @param userId
     * @throws SSException
     */
    public void delByUserId(int userId) throws SSException;

    /**
     * 根据用户名查询用户所属学院
     *
     * @param username
     * @return
     * @throws SSException
     */
    public List<String> listCollegesByUserName(String username) throws SSException;

    /**
     * 根据用户名查询所属专业
     *
     * @param username
     * @return
     * @throws SSException
     */
    public List<String> listMajorsByUserName(String username) throws SSException;

    /**
     * 根据用户名查询所属专业及专业层次
     *
     * @param username
     * @return
     * @throws SSException
     */
    public List<UserInfoDto> listMajorsAndQualificationByUserName(String username) throws SSException;

    /**
     * 根据学院查询所含专业及专业层次
     * @param username
     * @return
     * @throws SSException
     */
    public List<UserInfoDto> listMajorsAndQualificationByCollege(String username) throws SSException;
}
