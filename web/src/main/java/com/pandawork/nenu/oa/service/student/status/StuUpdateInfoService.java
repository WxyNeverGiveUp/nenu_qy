package com.pandawork.nenu.oa.service.student.status;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.student.status.UpdateInfoDto;
import com.pandawork.nenu.oa.common.entity.student.status.StuStatusInfo;
import com.pandawork.nenu.oa.common.entity.student.status.StuUpdateInfo;

import java.util.List;

/**
 * StuUpdateInfoService
 * 学籍修改信息Service
 *
 * @author wlm
 * @date 2016/7/20 17:00
 */
public interface StuUpdateInfoService {

    /**
     * 新增学籍修改信息
     *
     * @param stuUpdateInfo
     * @return
     * @throws Exception
     */
    public StuUpdateInfo newStuUpdateInfo(StuUpdateInfo stuUpdateInfo) throws SSException;

    /**
     * 修改学籍修改信息
     *
     * @param stuUpdateInfo
     * @throws Exception
     */
    public void updateById(StuUpdateInfo stuUpdateInfo) throws SSException;

    /**
     * 根据id查询学籍修改信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public StuUpdateInfo queryById(int id) throws SSException;

    /**
     * 根据学籍id和isStudent查询学籍最新修改信息
     * @param stuStatusInfoId
     * @param isStudent
     * @return
     * @throws SSException
     */
    public List<StuUpdateInfo> listLatestByStuStatusInfoId(int stuStatusInfoId,int isStudent) throws SSException;

    /**
     * 根据学籍id查询所有学籍修改信息
     * @param stuStatusInfoId
     * @return
     * @throws SSException
     */
    public List<StuUpdateInfo> listByStuStatusInfoId(int stuStatusInfoId) throws  SSException;

    /**
     * 根据学籍id查询学籍所有修改信息dto
     * @param stuStatusInfoId
     * @return
     * @throws SSException
     */
    public List<UpdateInfoDto> listDtoByStuStatusInfoId(int stuStatusInfoId) throws SSException;

    /**
     * 根据学籍id和isStudent查询学籍最新修改信息dto
     * @param stuStatusInfoId
     * @param isStudent
     * @return
     * @throws SSException
     */
    public List<UpdateInfoDto> listLatestDtoByStuStatusInfoId(int stuStatusInfoId,int isStudent) throws SSException;

    /**
     * 根据学籍id和修改类型查询修改信息
     *
     * @param statusInfoId
     * @param type
     * @return
     * @throws SSException
     */
    public List<StuUpdateInfo> listByStatusInfoIdAndType(int statusInfoId, int type) throws SSException;

    /**
     * 根据学籍id和修改类型查询修改信息dto
     *
     * @param statusInfoId
     * @param type
     * @return
     * @throws SSException
     */
    public List<UpdateInfoDto> listDtoByStatusInfoIdAndType(int statusInfoId, int type) throws SSException;

    /**
     * 检查学生学籍的信息是否修改，如果修改则在t_stu_update_info表中插入一条数据
     * @param stuStatusInfo
     * @param alterByWho   是被谁修改的，1为被学生修改的，0为其他人修改的
     * @throws SSException
     */
    public void whereIsAltered(StuStatusInfo stuStatusInfo,int alterByWho) throws SSException;
}
