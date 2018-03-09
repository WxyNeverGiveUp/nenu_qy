package com.pandawork.nenu.oa.service.user;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.user.StudentUser;

/**
 * StudentUserService
 * 学生账户Service
 *
 * @author wlm
 * @date 2016/7/29 17:12
 */
public interface StudentUserService {

    /**
     * 新增学生账户
     *
     * @param studentUser
     * @return
     * @throws SSException
     */
    public StudentUser newStudentUser(StudentUser studentUser) throws SSException;

    /**
     * 修改学生账户
     *
     * @param studentUser
     * @throws SSException
     */
    public void updateById(StudentUser studentUser) throws SSException;

    /**
     * 查询用户名是否已存在
     *
     * @param username
     * @return
     * @throws SSException
     */
    public boolean isUserNameExist(String username) throws SSException;

    /**
     * 根据Id查询学生账户
     *
     * @param id
     * @return
     * @throws SSException
     */
    public StudentUser queryById(int id) throws SSException;

    /**
     * 根据学号查询学生账户
     *
     * @param stuNumber
     * @return
     * @throws SSException
     */
    public StudentUser queryByStuNumber(String stuNumber) throws SSException;

    /**
     * 根据学号查询真实姓名
     *
     * @param stuNumber
     * @return
     * @throws SSException
     */
    public String queryNameByStuNumber(String stuNumber) throws SSException;

    /**
     * 根据id查找真实姓名
     *
     * @param id
     * @return
     * @throws SSException
     */
    public String queryNameById(int id) throws SSException;
}
