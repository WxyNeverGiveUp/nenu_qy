package com.pandawork.nenu.oa.service.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchAdminRemark;

import java.util.List;

/**
 * 管理员派遣备注Service
 * Created by ZhangBiLai on 2017/7/18.
 */
public interface DispatchAdminRemarkService {

    /**
     * 根据派遣id查询备注
     * @param dispatchId
     * @return
     * @throws SSException
     */
    public List<DispatchAdminRemark> queryByDispatchId (int dispatchId) throws SSException;

    /**
     * 添加或修改管理员备注
     * @param list
     * @throws SSException
     */
    public void newOrUpdateRemarks (List<DispatchAdminRemark> list) throws SSException;

    /**
     * 根据id删除备注
     * @param dispatchAdminRemark
     * @throws SSException
     */
    public void deleteById (DispatchAdminRemark dispatchAdminRemark) throws SSException;
}
