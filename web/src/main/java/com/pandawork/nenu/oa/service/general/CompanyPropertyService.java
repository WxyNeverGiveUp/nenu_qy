package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.general.CompanyProperty;

import java.util.List;

/**
 * Description:单位性质service
 * Author:luowanli
 * Date:2016/7/23
 * Time:20:10
 */
public interface CompanyPropertyService {

    /**
     * 根据id查询单位性质代码
     * @param id
     * @return
     * @throws SSException
     */
    public CompanyProperty queryById(int id) throws SSException;

    /**
     * 根据code查询单位性质代码
     *
     * @param propertyCode
     * @return
     * @throws SSException
     */
    public CompanyProperty queryByCode(int propertyCode) throws SSException;

    /**
     * 查询所有单位性质代码
     *
     * @return
     * @throws SSException
     */
    public List<CompanyProperty> listAll() throws SSException;
}
