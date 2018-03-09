package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.general.CompanyTrade;

import java.util.List;

/**
 * Description:单位所属行业service
 * Author:luowanli
 * Date:2016/7/23
 * Time:20:10
 */
public interface CompanyTradeService {

    /**
     * 根据id查询单位所属行业代码
     * @param id
     * @return
     * @throws SSException
     */
    public CompanyTrade queryById(int id) throws SSException;

    /**
     * 根据code查询单位所属行业代码
     * @param tradeId
     * @return
     * @throws SSException
     */
    public CompanyTrade queryByCode(int tradeId) throws SSException;

    /**
     * 查询所有单位所属行业代码
     * @return
     * @throws SSException
     */
    public List<CompanyTrade> listAll() throws SSException;
}
