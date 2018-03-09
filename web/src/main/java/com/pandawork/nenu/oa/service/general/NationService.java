package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.data.NationCode;

import java.util.List;

/**
 * NationService
 * 民族service
 *
 * @author wlm
 * @date 2016/7/27 15:34
 */
public interface NationService {

    /**
     * 根据id查询，民族
     *
     * @param id
     * @return
     * @throws SSException
     */
    public NationCode queryById(int id) throws SSException;

    /**
     * 根据代码查询民族
     *
     * @param nationId
     * @return
     * @throws SSException
     */
    public NationCode queryByNationId(String nationId) throws SSException;

    /**
     * 查询所有民族
     *
     * @return
     * @throws SSException
     */
    public List<NationCode> listAll() throws SSException;
}
