package com.pandawork.nenu.oa.service.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.oa.common.dto.dispatch.ExportAllDto;
import com.pandawork.nenu.oa.common.dto.dispatch.*;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by qiuxiao on 2016/7/13.
 */
public interface DispatchInfoService {


    /**
     *修改派遣信息
     * @param dispatchInfo
     * @throws SSException
     */
    void updateDispatchInfo(DispatchInfo dispatchInfo)throws SSException;

    /**
     *
     * @param dispatchQueryDto
     * @return
     * @throws SSException
     */
    List<DispatchQueryDto> queryDispatchList(DispatchQueryDto dispatchQueryDto)throws SSException;

    /**
     *获取指定id的派遣信息详情
     * @param id
     * @return
     * @throws Exception
     */
    DispatchQueryDto getDetail(Integer id)throws  SSException;
    /**
     * 返回管理员对应的所有的学院或者专业信息
     * @param id
     * @return
     * @throws SSException
     */
    List<EnumDto> getDepartmentsOrMajors(Integer id)throws SSException;

    /**
     * 是否有指定的角色
     * @param role
     * @return
     * @throws SSException
     */
    Boolean hasRole(Integer userId,String role)throws SSException;

//    /**
//     * 获取下一个的信息
//     *
//     * @return
//     * @throws SSException
//     */
//    DispatchQueryDto getNextOne(DispatchQueryDto dispatchQueryDto)throws SSException;

    /**
     *
     * @param dispatchQueryDto
     * @return
     * @throws SSException
     */
    public Integer getCount(DispatchQueryDto dispatchQueryDto) throws SSException;

    /**
     * 查询管理员对应的派遣信息列表
     * @param dispatchQueryDto
     * @param page
     * @return
     * @throws SSException
     */
    //zy 改
    public List<Integer> listIdByCondition(Integer userId,DispatchQueryDto dispatchQueryDto, Pagination page) throws SSException;

    /**
     *
     * @param dispatchQueryDto
     * @return
     * @throws Exception
     */
    public Integer getAdminDispatchersCount(Integer userId,DispatchQueryDto dispatchQueryDto) throws  SSException;

    /**
     * 根据学生stu_status_info_id查询派遣信息id
     * @param stuId
     * @return
     */
      Integer getIdByStuId(Integer stuId)throws  SSException;

    void updateById(DispatchInfo dispatchInfo)throws SSException;

    /**
     * 根据派遣信息id获取学生信息id
     * @param id
     * @return
     */
    int getStatusInfoIdById(int id)throws SSException;

    /**
     * 查询少数民族派遣信息数量
     *
     * @param minorityQueryDto
     * @return
     * @throws SSException
     */
    public int countMinorityByCondition(MinorityQueryDto minorityQueryDto) throws SSException;

    /**
     * 分页获取少数民族派遣信息
     *
     * @param minorityQueryDto
     * @param page
     * @return
     * @throws SSException
     */
    public List<DispatchListDto> listMinorityByCondition(MinorityQueryDto minorityQueryDto, Pagination page) throws SSException;

    /**
     * 分页获取少数民族派遣信息id
     *
     * @param minorityQueryDto
     * @param page
     * @return
     * @throws SSException
     */
    public List<Integer> listMinorityIdByCondition(MinorityQueryDto minorityQueryDto, Pagination page) throws SSException;

    /**
     * 查询要导出的派遣信息
     *
     * @param grade
     * @return
     * @throws SSException
     */
    public List<DispatchExportDto> listAllExportInfo(int grade) throws SSException;

    /**
     * 置派遣表中记录为非当前状态
     * @param statusInfoId
     * @throws SSException
     */
    public void updateCurrentAgreement(int statusInfoId) throws SSException;

    /**
     * 发放新协议
     * @param dispatchInfo
     * @throws SSException
     */
    public void newAgreement(DispatchInfo dispatchInfo) throws SSException;

    /**
     * 根据学籍id查询学生的派遣信息
     * @param stuStatusInfoId
     * @return
     * @throws SSException
     */
    public List<DispatchInfo> queryDispatchByStuStatusInfoId(int stuStatusInfoId) throws SSException;

    /**
     * 根据学籍id删除派遣信息（真删除，删除学籍信息时，删除学生所有信息）
     * @param stuStatusInfoId
     * @throws SSException
     * @author chenyuting
     */
    public void deleteDispatchByStuStatusInfoId(int stuStatusInfoId) throws SSException;

    /**
     * 副书记批量审核
     * @param id
     * @param date
     * @param realName
     * @return
     * @throws SSException
     */
    public boolean batchAudit(List<Integer> id, Date date, String realName) throws SSException;

    /**
     * 单继重
     * 根据派遣ID删除派遣信息（假删）
     * @param dispatchInfoId 派遣ID
     */
    public void deleteById(int dispatchInfoId) throws SSException;

    /**
     * 单继重
     * 大导出
     * @return 大导出DTO
     * @throws SSException SS异常
     * @param dispatchQueryDto 派遣信息查询项
     */
    public List<ExportAllDto> exportAll(DispatchQueryDto dispatchQueryDto) throws SSException;

    /**
     * 单继重
     * 大导出学院层面
     * @return 大导出学院层面DTO
     * @throws SSException SS异常
     * @param dispatchQueryDto 派遣信息查询项
     */
    List<ExportAllCounsellorDto> exportAllCounsellor(DispatchQueryDto dispatchQueryDto) throws SSException;
}
