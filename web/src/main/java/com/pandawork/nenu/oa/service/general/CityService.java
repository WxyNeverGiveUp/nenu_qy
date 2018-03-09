package com.pandawork.nenu.oa.service.general;


import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.general.CityProperty;

import java.util.List;

/**
 * CityService
 * 市service
 *
 * @author chenwy
 * @date 2017/5/15 15:34
 */
public interface CityService {

    /**
     * 根据省份id获取市列表
     *
     * @param provinceId
     * @return
     * @throws SSException
     */
    public List<CityProperty> listByProvinceId(int provinceId) throws SSException;

    /**
     * 获取所有市列表
     *
     * @return
     * @throws Exception
     */
    public List<CityProperty> listAll() throws Exception;

    /**
     * 根据市id查找市名(api)
     * @param id
     * @return
     * @throws Exception
     */
    public String queryCityById(int id) throws SSException;
}
