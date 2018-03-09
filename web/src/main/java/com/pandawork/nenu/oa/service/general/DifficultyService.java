package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.data.DifficultyCode;

import java.util.List;

/**
 * DifficultyService
 * 困难生类别service
 *
 * @author wlm
 * @date 2016/7/27 15:34
 */
public interface DifficultyService {

    /**
     * 根据id查询困难生
     *
     * @param id
     * @return
     * @throws SSException
     */
    public DifficultyCode queryById(int id) throws SSException;

    /**
     * 根据代码查询困难生
     *
     * @param difficultyId
     * @return
     * @throws SSException
     */
    public DifficultyCode queryByDifficultyId(int difficultyId) throws SSException;

    /**
     * 查询所有困难生
     *
     * @return
     * @throws SSException
     */
    public List<DifficultyCode> listAll() throws SSException;
}
