package com.pandawork.nenu.oa.service.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.dispatch.Comments;

/**
 * CommentsService
 * 办公信息Service接口
 *
 * @author wlm
 * @date 2016/10/24 10:15
 */
public interface CommentsService {

    /**
     * 根据id查询办公信息
     *
     * @param id
     * @return
     * @throws SSException
     */
    public Comments queryById(int id) throws SSException;

    /**
     * 根据学籍id查询办公信息
     *
     * @param statusId
     * @return
     * @throws SSException
     */
    public Comments queryByStatusId(int statusId) throws SSException;

    /**
     * 新增办公信息
     *
     * @param comments
     * @return
     * @throws SSException
     */
    public Comments newComments(Comments comments) throws SSException;

    /**
     * 根据id修改办公信息
     *
     * @param comments
     * @throws SSException
     */
    public void updateById(Comments comments) throws SSException;
}
