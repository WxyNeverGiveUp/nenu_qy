package com.pandawork.nenu.oa.service.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.dispatch.ReportCard;

/**
 * ReportCardService
 *
 * @author wlm
 * @date 2017/1/8 19:44
 */
public interface ReportCardService {

    /**
     * 新增报到证
     *
     * @param reportCard
     * @return
     * @throws SSException
     */
    public ReportCard newReportCard(ReportCard reportCard) throws SSException;

    /**
     * 根据id修改报到证
     *
     * @param reportCard
     * @throws SSException
     */
    public void updateById(ReportCard reportCard) throws SSException;

    /**
     * 根据id查询报到证
     *
     * @param id
     * @return
     * @throws SSException
     */
    public ReportCard queryById(int id) throws SSException;

    /**
     * 根据学籍id查询报到证
     *
     * @param statusId
     * @return
     * @throws SSException
     */
    public ReportCard queryByStatusId(int statusId) throws SSException;
}
