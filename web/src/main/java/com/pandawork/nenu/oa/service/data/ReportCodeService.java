package com.pandawork.nenu.oa.service.data;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.data.ReportCode;

import java.util.List;

/**
 * Description:报到证类别service
 * Author:luowanli
 * Date:2016/7/23
 * Time:20:10
 */

public interface ReportCodeService {

    /**
     * 根据id查询报到证类别代码
     * @param id
     * @return
     * @throws SSException
     */
    public ReportCode queryById(int id) throws SSException;

    /**
     * 根据code查询报到证类别代码
     * @param reportModeId
     * @return
     * @throws SSException
     */
    public ReportCode queryByCode(int reportModeId) throws SSException;

    /**
     * 查询所有报到证类别代码
     * @return
     * @throws SSException
     */
    public List<ReportCode> listAll() throws SSException;
}
