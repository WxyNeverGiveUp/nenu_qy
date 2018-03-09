package com.pandawork.nenu.oa.mapper.dispatch;

import com.pandawork.nenu.oa.common.entity.dispatch.DispatchAdminRemark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员备注Mapper
 * Created by ZhangBiLai on 2017/7/19.
 */
public interface DispatchAdminRemarkMapper {

    public List<DispatchAdminRemark> queryByDispatchId (@Param("dispatchId") int dispatchId) throws Exception;
}
