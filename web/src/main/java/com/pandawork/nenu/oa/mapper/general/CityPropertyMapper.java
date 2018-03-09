package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.entity.general.CityProperty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* CityMapper
* 市mapper
*
* @author chenwy
* @date 2017/5/15 15:51
*/
public interface CityPropertyMapper {

    /**
     * 根据省份id获取该省的市列表
     *
     * @param provinceId
     * @return
     * @throws Exception
     */
    public List<CityProperty> listByProvinceId(@Param("provinceId") int provinceId ) throws Exception;

    /**
     * 获取所有市列表
     *
     * @return
     * @throws Exception
     */
    public List<CityProperty> listAll() throws Exception;

    /**
     * 根据市ID查找市名(api)
     * @param id
     * @return
     * @throws Exception
     */
    public String queryCityById(@Param("id") int id) throws Exception;

}
