package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.data.SchoolDto;
import com.pandawork.nenu.oa.common.entity.data.SchoolCode;

import java.util.List;

/**
 * SchoolService
 *
 * @author wlm
 * @date 2016/9/1 18:33
 */
public interface SchoolService {

    /**
     * 根据id查询学校
     *
     * @param id
     * @return
     * @throws SSException
     */
    public SchoolCode queryById(int id) throws SSException;

    /**
     * 根据code查询学校
     *
     * @param code
     * @return
     * @throws SSException
     */
    public SchoolCode queryByCode(String code) throws SSException;

    /**
     * 查询所有学校
     *
     * @return
     * @throws SSException
     */
    public List<SchoolCode> listAll() throws SSException;

    /**
     * 根据code查询部分高校信息
     *
     * @param code
     * @return
     * @throws SSException
     */
    public SchoolDto queryDtoByCode(String code) throws SSException;
}
