package com.pandawork.nenu.oa.mapper.dispatch;

import com.pandawork.nenu.oa.common.entity.dispatch.ReportCard;

/**
 * ReportCardMapper
 *
 * @author wlm
 * @date 2017/1/8 20:16
 */
public interface ReportCardMapper {

    /**
     * 根据学籍id查询报到证
     * @param statusId
     * @return
     * @throws Exception
     */
    public ReportCard queryByStatusId(int statusId) throws Exception;

}
