package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.entity.general.CompanyProperty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:单位性质Mapper
 * Author:luowanli
 * Date:2016/7/27
 * Time:11:46
 */
public interface CompanyPropertyMapper {

    /**
     * 根据code查询单位性质代码
     * @param propertyCode
     * @return
     * @throws Exception
     */
    public CompanyProperty queryByCode(@Param("propertyCode") int propertyCode) throws Exception;

    /**
     * 查询全部单位性质代码
     * @return
     * @throws Exception
     */
    public List<CompanyProperty> listAll() throws Exception;
}
