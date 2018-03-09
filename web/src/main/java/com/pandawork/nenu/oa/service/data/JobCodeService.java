package com.pandawork.nenu.oa.service.data;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.data.JobCode;

import java.util.List;

/**
 * Description:工作类别service
 * Author:luowanli
 * Date:2016/7/23
 * Time:20:10
 */

public interface JobCodeService {

    /**
     * 根据id查询工作类别
     * @param id
     * @return
     * @throws SSException
     */
    public JobCode queryById(int id) throws SSException;

    /**
     * 根据code查询工作类别
     * @param jobId
     * @return
     * @throws SSException
     */
    public JobCode queryByCode(int jobId) throws SSException;

    /**
     * 查询所有工作类别
     * @return
     * @throws SSException
     */
    public List<JobCode> listAll() throws SSException;
}
