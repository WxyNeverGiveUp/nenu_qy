package com.pandawork.nenu.oa.service.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchExtendItem;

/**
 * 管理员派遣扩展项Service
 * Created by ZhangBiLai on 2017/7/19.
 */
public interface DispatchExtendItemService {

    /**
     * 添加或修改
     * @param dispatchExtendItem 备注扩展项实体类
     * @throws SSException SS异常
     */
    public void newOrUpdate(DispatchExtendItem dispatchExtendItem) throws SSException;

    /**
     * 根据dispatchId查询扩展项
     * @param dispatchId 派遣ID
     * @return 扩展项实体类
     * @throws SSException SS异常
     */
    public DispatchExtendItem queryByDispatchId (int dispatchId) throws SSException;

    /**
     * 获取详细扩展项
     * @param dispatchId 派遣表ID
     * @return 扩展项实体类
     * @throws SSException SS异常
     */
    public DispatchExtendItem queryDetailByDispatchId(int dispatchId) throws SSException;
}
