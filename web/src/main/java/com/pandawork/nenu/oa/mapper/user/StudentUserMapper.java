package com.pandawork.nenu.oa.mapper.user;

import com.pandawork.nenu.oa.common.entity.user.StudentUser;
import org.apache.ibatis.annotations.Param;

/**
 * StudentUserMapper
 *
 * @author wlm
 * @date 2016/7/30 9:39
 */
public interface StudentUserMapper {

    /**
     * 根据学号查询学生账户
     *
     * @param stuNumber
     * @return
     * @throws Exception
     */
    public StudentUser queryByStuNumber(@Param("stuNumber") String stuNumber) throws Exception;

    /**
     * 根据学号查询真实姓名
     *
     * @param stuNumber
     * @return
     * @throws Exception
     */
    public String queryNameByStuNumber(@Param("stuNumber") String stuNumber) throws Exception;

    /**
     * 根据id查找真实姓名
     *
     * @param id
     * @return
     * @throws Exception
     */
    public String queryNameById(@Param("id") int id) throws Exception;

    /**
     * 查询学号有几条记录
     *
     * @param stuNumber
     * @return
     * @throws Exception
     */
    public Integer countByStuNumber(@Param("stuNumber") String stuNumber) throws Exception;
}
