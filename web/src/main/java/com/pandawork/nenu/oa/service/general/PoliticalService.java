package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.data.PoliticalCode;

import java.util.List;

/**
 * PoliticalService
 * 政治面貌service
 *
 * @author wlm
 * @date 2016/7/27 15:34
 */
public interface PoliticalService {

    /**
     * 根据id查询政治面貌
     *
     * @param id
     * @return
     * @throws SSException
     */
    public PoliticalCode queryById(int id) throws SSException;

    /**
     * 根据代码查询政治面貌
     *
     * @param politicalStandId
     * @return
     * @throws SSException
     */
    public PoliticalCode queryByPoliticalStandId(String politicalStandId) throws SSException;

    /**
     * 查询所有政治面貌
     *
     * @return
     * @throws SSException
     */
    public List<PoliticalCode> listAll() throws SSException;
}
