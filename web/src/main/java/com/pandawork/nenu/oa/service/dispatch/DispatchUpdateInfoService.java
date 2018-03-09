package com.pandawork.nenu.oa.service.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchUpdateDto;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchUpdateInfo;

import java.util.List;

/**
 * DispatchUpdateInfoService
 * 派遣信息修改Service
 * Created by ZhangBiLai on 2017/9/25.
 */
public interface DispatchUpdateInfoService {

    /**
     * 新增派遣修改信息
     * @param dispatchUpdateInfo
     * @return
     * @throws SSException
     */
    public DispatchUpdateInfo newDispatchUpdateInfo (DispatchUpdateInfo dispatchUpdateInfo) throws SSException;

    /**
     * 修改派遣修改信息
     * @param dispatchUpdateInfo
     * @throws SSException
     */
    public void updateById (DispatchUpdateInfo dispatchUpdateInfo) throws SSException;

    /**
     * 根据id查询派遣修改信息
     * @param id
     * @return
     * @throws SSException
     */
    public DispatchUpdateInfo queryById (int id) throws SSException;

    /**
     * 根据派遣id和isStudent查询派遣最新修改信息
     * @param dispatchInfoId
     * @param isStudent
     * @return
     * @throws SSException
     */
    public List<DispatchUpdateInfo> listLatestByDispatchInfoId (int dispatchInfoId,int isStudent) throws SSException;

    /**
     * 根据派遣id查询所有派遣修改信息
     * @param dispatchInfoId
     * @return
     * @throws SSException
     */
    public List<DispatchUpdateInfo> listByDispatchInfoId (int dispatchInfoId) throws SSException;

    /**
     * 根据派遣id和修改类型查询修改信息
     * @param dispatchInfoId
     * @param type
     * @return
     * @throws SSException
     */
    public List<DispatchUpdateInfo> listByDispatchInfoIdAndType (int dispatchInfoId,int type) throws SSException;

    /**
     * 根据派遣id查询所有修改dto
     * @param dispatchInfoId
     * @return
     * @throws SSException
     */
    public List<DispatchUpdateDto> listDtoByDispatchInfoId (int dispatchInfoId) throws SSException;

    /**
     * 根据派遣id和isStudent查询派遣最新修改信息dto
     * @param dispatchInfoId
     * @return
     * @throws SSException
     */
    public List<DispatchUpdateDto> listLatestDtoByDispatchInfo (int dispatchInfoId,int isStudent) throws SSException;

    /**
     * 检查学生学籍的信息是否修改，如果修改则在t_dispatch_update_info表中插入一条数据
     * @param dispatchInfo
     * @param alterByWho 是被谁修改的，1为被学生修改的，0为其他人修改的
     * @throws SSException
     */
    public void whereIsAltered(DispatchInfo dispatchInfo, int alterByWho) throws SSException;
}
