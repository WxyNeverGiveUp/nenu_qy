package com.pandawork.nenu.oa.mapper.dispatch;

import com.pandawork.nenu.oa.common.entity.dispatch.DispatchExtendItem;
import org.apache.ibatis.annotations.Param;

/**
 * 管理员派遣扩展项Mapper
 * Created by ZhangBiLai on 2017/7/19.
 */
public interface DispatchExtendItemMapper {

    /**
     * 根据派遣id查询管理员派遣扩展项
     * @param dispatchId
     * @return
     * @throws Exception
     */
    public DispatchExtendItem queryByDispatchId (@Param("dispatchId") int dispatchId) throws Exception;
}
