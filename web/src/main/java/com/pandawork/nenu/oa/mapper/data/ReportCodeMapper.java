package com.pandawork.nenu.oa.mapper.data;

import com.pandawork.nenu.oa.common.entity.data.ReportCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:报到证类别Mapper
 * Author:luowanli
 * Date:2016/7/27
 * Time:11:46
 */

public interface ReportCodeMapper {

    /**
     * 根据code查询报到证类别
     * @param reportModeId
     * @return
     * @throws Exception
     */
    public ReportCode queryByCode(@Param("reportModeId") int reportModeId) throws Exception;

    /**
     * 查询全部报到证类别
     * @return
     * @throws Exception
     */
    public List<ReportCode> listAll() throws Exception;
}
