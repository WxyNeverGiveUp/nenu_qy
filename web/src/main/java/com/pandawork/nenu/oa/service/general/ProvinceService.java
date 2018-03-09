package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.general.ProvinceProperty;

import java.util.List;

/**
 * ProvinceService
 * 省份service
 *
 * @author wlm
 * @date 2016/7/27 15:34
 */
public interface ProvinceService {

    /**
     * 根据id查询省份
     *
     * @param id
     * @return
     * @throws SSException
     */
    public ProvinceProperty queryById(int id) throws SSException;

    /**
     * 根据code查询省份
     *
     * @param code
     * @return
     * @throws SSException
     */
    public ProvinceProperty queryByCode(String code) throws SSException;

    /**
     * 查询所有省份
     *
     * @return
     * @throws SSException
     */
    public List<ProvinceProperty> listAll() throws SSException;

    /**
     * 根据省份名称查询省份代码
     * @param name
     * @return
     * @throws SSException
     */
    public String queryByName(String name) throws SSException;

    /**
     *根据省份id查询省份名称(api)
     * @param id
     * @return
     * @throws SSException
     */
    public String queryNameById(int id) throws SSException;

}
