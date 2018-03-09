package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.entity.general.CompanyTrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:单位所属行业Mapper
 * Author:luowanli
 * Date:2016/7/27
 * Time:11:46
 */
public interface CompanyTradeMapper {

    /**
     * 根据code查询单位所属行业代码
     * @param tradeId
     * @return
     * @throws Exception
     */
    public CompanyTrade queryByCode(@Param("tradeId") int tradeId) throws Exception;

    /**
     * 查询全部单位所属行业代码
     * @return
     * @throws Exception
     */
    public List<CompanyTrade> listAll() throws Exception;
}
