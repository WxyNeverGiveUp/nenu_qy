package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.data.TrainingModeCode;

import java.util.List;

/**
 * TrainingModeService
 * 培养方式Service
 *
 * @author wlm
 * @date 2016/7/28 15:29
 */
public interface TrainingModeService {

    /**
     * 根据id查询培养方式
     *
     * @param id
     * @return
     * @throws SSException
     */
    public TrainingModeCode queryById(int id) throws SSException;

    /**
     * 根据code查询培养方式
     *
     * @param code
     * @return
     * @throws SSException
     */
    public TrainingModeCode queryByCode(int code) throws SSException;

    /**
     * 查询所有培养方式
     *
     * @return
     * @throws SSException
     */
    public List<TrainingModeCode> listAll() throws SSException;
}
