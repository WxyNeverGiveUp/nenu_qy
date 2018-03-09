package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.data.CollegeCode;

import java.util.List;

/**
 * CollegeService
 * 学院service
 *
 * @author wlm
 * @date 2016/7/27 15:34
 */
public interface CollegeService {

    /**
     * 根据id查询学院
     *
     * @param id
     * @return
     * @throws SSException
     */
    public CollegeCode queryById(int id) throws SSException;

    /**
     * 根据code查询学院
     *
     * @param code
     * @return
     * @throws SSException
     */
    public CollegeCode queryByCode(String code) throws SSException;

    /**
     * 查询所有学院
     *
     * @return
     * @throws SSException
     */
    public List<CollegeCode> listAll() throws SSException;
}
