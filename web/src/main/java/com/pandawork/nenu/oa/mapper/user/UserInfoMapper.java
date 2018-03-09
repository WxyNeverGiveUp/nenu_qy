package com.pandawork.nenu.oa.mapper.user;

import com.pandawork.nenu.oa.common.dto.user.UserInfoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserInfoMapper
 * 用户信息Mapper
 *
 * @author wlm
 * @date 2016/9/22 16:11
 */
public interface UserInfoMapper {

    /**
     * 根据userId删除用户信息
     *
     * @param userId
     * @throws Exception
     */
    public void delByUserId(@Param("userId") int userId) throws Exception;

    /**
     * 根据用户名查询用户所属学院
     *
     * @param id
     * @return
     * @throws Exception
     */
    public List<String> listCollegesById(@Param("id") int id) throws Exception;

    /**
     * 根据用户名查询所属专业
     *
     * @param id
     * @return
     * @throws Exception
     */
    public List<String> listMajorsById(@Param("id") int id) throws Exception;

    /**
     * 根据用户名查询所属专业及专业层次
     *
     * @param id
     * @return
     * @throws Exception
     */
    public List<UserInfoDto> listMajorsAndQualificationById(@Param("id") int id) throws Exception;

    /**
     * 根据学院查询所含专业及专业层次
     * @param id
     * @return
     * @throws Exception
     */
    public List<UserInfoDto> listMajorsAndQualificationByCollege(@Param("id") int id) throws Exception;
}
