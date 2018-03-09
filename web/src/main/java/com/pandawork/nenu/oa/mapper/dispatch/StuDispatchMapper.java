package com.pandawork.nenu.oa.mapper.dispatch;

import com.pandawork.nenu.oa.common.dto.dispatch.DispatchRemarksDto;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import org.apache.ibatis.annotations.Param;

/**
 * DispatchMapper
 * user: luowanli
 * date 16/7/12
 * time 上午16:57
 */

public interface StuDispatchMapper {

    /**
     * 通过学籍信息表Id查询学生派遣信息
     * @param stuStatusInfoId
     * @return
     * @throws Exception
     */
    public  DispatchInfo queryDispatchByStuId(@Param("statusInfoId") Integer stuStatusInfoId) throws Exception;

    /**
     * 添加派遣备注信息
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public DispatchRemarksDto  queryRemarksByStuId(@Param("statusInfoId") Integer statusInfoId) throws Exception;
}
