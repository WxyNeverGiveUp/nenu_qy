package com.pandawork.nenu.oa.mapper.data;

import com.pandawork.nenu.oa.common.entity.data.JobCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:工作类别Mapper
 * Author:luowanli
 * Date:2016/7/27
 * Time:11:46
 */

public interface JobCodeMapper {

    /**
     * 根据code查询工作类别
     * @param jobId
     * @return
     * @throws Exception
     */
    public JobCode queryByCode(@Param("jobId") int jobId) throws Exception;

    /**
     * 查询全部工作类别
     * @return
     * @throws Exception
     */
    public List<JobCode> listAll() throws Exception;
}
