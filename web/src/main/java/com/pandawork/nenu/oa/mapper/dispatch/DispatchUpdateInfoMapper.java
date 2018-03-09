package com.pandawork.nenu.oa.mapper.dispatch;

import com.pandawork.nenu.oa.common.entity.dispatch.DispatchUpdateInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZhangBiLai on 2017/9/26.
 */
public interface DispatchUpdateInfoMapper {

    /**
     * 根据派遣id和isStudent查询派遣最新修改信息
     * @param dispatchInfoId
     * @param isStudent
     * @return
     * @throws Exception
     */
    public List<DispatchUpdateInfo> listLatestByDispatchInfoId(@Param("dispatchInfoId") int dispatchInfoId
            , @Param("isStudent") int isStudent) throws Exception;

    /**
     * 根据派遣id查询所有派遣修改信息
     * @param dispatchInfoId
     * @return
     * @throws Exception
     */
    public List<DispatchUpdateInfo> listByDispatchInfoId (@Param("dispatchInfoId") int dispatchInfoId
    ) throws Exception;

    /**
     * 根据派遣id和修改类型查询派遣修改信息
     * @param dispatchInfoId
     * @param type
     * @return
     * @throws Exception
     */
    public List<DispatchUpdateInfo> listByDispatchInfoIdAndType (@Param("dispatchInfoId") int dispatchInfoId
    , @Param("type") int type) throws Exception;
}
