package com.pandawork.nenu.oa.mapper.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.dispatch.ExportAllDto;
import com.pandawork.nenu.oa.common.dto.dispatch.*;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchAdminRemark;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchExtendItem;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by qiuxiao on 2016/7/13.
 */
public interface DispatchInfoMapper {
    /**
     * 获取用户的角色类型（已经校验）
     *
     * @param userId
     * @return
     * @throws SSException
     */
    public List<String> getUserRole(Integer userId) throws Exception;

    /**
     * 带条件查询学院级别的用户对应的派遣信息列表
     *
     * @param dispatchQueryDto
     * @return
     * @throws Exception
     */
    //zy 改
    public List<DispatchQueryDto> queryDepartmentTypeList(DispatchQueryDto dispatchQueryDto) throws Exception;

    /**
     * 带条件查询辅导员级别的用户对应的派遣信息列表
     *
     * @param dispatchQueryDto
     * @return
     * @throws Exception
     */
    //zy 改
    public List<DispatchQueryDto> queryCounsellorTypeList(DispatchQueryDto dispatchQueryDto) throws Exception;

    /**
     * 带条件查询学校类别的管理员对应的派遣信息列表
     *
     * @param dispatchQueryDto
     * @return
     * @throws Exception
     */
    //zy 改
    public List<DispatchQueryDto> querySchoolTypeList(DispatchQueryDto dispatchQueryDto) throws Exception;

    /**
     * @param id
     * @return
     * @throws Exception
     */
    public DispatchQueryDto getDetail(Integer id) throws Exception;

    /**
     * 返回校级管理员时的所有学院
     *
     * @return
     */
    public List<EnumDto> getAllDepartments() throws Exception;

    /**
     * 获取院级管理员对应的所有专业
     *
     * @param userId
     * @return
     * @throws SSException
     */
    public List<EnumDto> getAllMajors(Integer userId) throws Exception;

    /**
     * 获取所有的可选的所在省份
     *
     * @return
     * @throws SSException
     */
    public List<EnumDto> getProvinces() throws Exception;

    /**
     * 获取所有的市
     *
     * @param provinceId
     * @return
     * @throws SSException
     */
    public List<EnumDto> getCitys(Integer provinceId) throws Exception;

    /**
     * 获取市名下面的所有县城名
     *
     * @param cityId
     * @return
     * @throws SSException
     */
    public List<EnumDto> getCountys(Integer cityId) throws Exception;

    /**
     * 获取单位行业
     *
     * @return
     * @throws Exception
     */
    public List<EnumDto> getCompanyTrades() throws Exception;

    /**
     * 获取毕业去向
     *
     * @return
     * @throws Exception
     */
    public List<EnumDto> getWhereAboutGos() throws Exception;

    /**
     * 获取单位性质
     *
     * @return
     * @throws Exception
     */
    public List<EnumDto> getPropertys() throws Exception;

    /**
     * 返回工作类型
     *
     * @return
     * @throws Exception
     */
    public List<EnumDto> getJobs() throws Exception;

    /**
     * 返回报到证类别
     *
     * @return
     * @throws Exception
     */
    public List<EnumDto> getReportModels() throws Exception;

    /**
     * 获取当前查询条件下的最小id值
     *
     * @return
     * @throws Exception
     */
    public Integer getMinId(DispatchQueryDto dispatchQueryDto) throws Exception;

    /**
     * 获取当前查询条件下的最小id值
     *
     * @return
     * @throws Exception
     */
    public Integer getMaxId(DispatchQueryDto dispatchQueryDto) throws Exception;

//    /**
//     * 获取当前查询条件下上一个记录的id
//     * @return
//     * @throws Exception
//     */
//    public Integer getPrevious(DispatchQueryDto dispatchQueryDto)throws Exception;
//    /**
//     * 获取当前查询条件下下一个记录的所有信息
//     * @return
//     * @throws Exception
//     */
//    public DispatchQueryDto getNextOne(DispatchQueryDto dispatchQueryDto)throws Exception;

    /**
     * 获取学校类型管理员总行数
     *
     * @param dispatchQueryDto
     * @return
     * @throws Exception
     */
    public Integer getSchoolCount(DispatchQueryDto dispatchQueryDto) throws Exception;

    /**
     * 获取辅导员类型管理员总行数
     *
     * @param dispatchQueryDto
     * @return
     * @throws Exception
     */
    public Integer getCounsellorCount(DispatchQueryDto dispatchQueryDto) throws Exception;

    /**
     * 获取辅导员类型管理员总行数
     *
     * @param dispatchQueryDto
     * @return
     * @throws Exception
     */
    public Integer getDepartmentCount(DispatchQueryDto dispatchQueryDto) throws Exception;

    public List<Integer> getSchoolIdList(DispatchQueryDto dispatchQueryDto) throws Exception;

    public List<Integer> getDepartmentIdList(DispatchQueryDto dispatchQueryDto) throws Exception;

    public List<Integer> getCounsellorIdList(DispatchQueryDto dispatchQueryDto) throws Exception;

    Integer getIdByStuId(Integer stuId);

    Integer getStatusInfoIdById(int id);

    /**
     * 查询少数民族派遣信息数量
     *
     * @param minorityQueryDto
     * @return
     * @throws Exception
     */
    public Integer countMinorityByCondition(MinorityQueryDto minorityQueryDto) throws Exception;

    /**
     * 分页获取少数民族派遣信息
     *
     * @param minorityQueryDto
     * @return
     * @throws Exception
     */
    public List<DispatchListDto> listMinorityByCondition(MinorityQueryDto minorityQueryDto) throws Exception;

    /**
     * 分页获取少数民族派遣信息id
     *
     * @param minorityQueryDto
     * @return
     * @throws SSException
     */
    public List<Integer> listMinorityIdByCondition(MinorityQueryDto minorityQueryDto) throws SSException;

    /**
     * 查询要导出的派遣信息
     *
     * @param grade
     * @return
     * @throws Exception
     */
    public List<DispatchExportDto> listAllExportInfo(@Param("grade") int grade) throws Exception;

    /**
     * 置派遣表中记录为非当前状态
     * @param statusInfoId
     * @throws Exception
     */
    public void updateCurrentAgreement(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 发放身份变更新协议
     * @param dispatchInfo
     * @throws Exception
     */
    public void newAgreement(DispatchInfo dispatchInfo) throws Exception;

    /**
     * 根据学籍id查询学生的派遣信息
     * @param stuStatusInfoId
     * @return List
     * @throws Exception
     * @author chenyuting
     */
    public List<DispatchInfo> queryDispatchByStuStatusInfoId(@Param("stuStatusInfoId") int stuStatusInfoId) throws Exception;

    /**
     * 根据学籍id查询学生的派遣信息
     * @param stuStatusInfoId
     * @return dispatchInfo实体
     * @throws Exception
     */
    public DispatchInfo queryDispatchInfoByStuStatusInfoId(@Param("stuStatusInfoId") int stuStatusInfoId) throws Exception;

    /**
     * 副书记批量审核
     * @param id
     * @param date
     * @param realName
     * @throws Exception
     */
    public void batchAudit(@Param("ids") List<Integer> id, @Param("date") Date date, @Param("realName") String realName) throws Exception;

    /**
     * 根据派遣ID删除派遣信息（假删）
     * 单继重
     * @param dispatchInfoId 派遣ID
     */
    public void deleteById(@Param("dispatchId") int dispatchInfoId) throws Exception;

    /**
     * 大导出Mapper
     * 单继重
     * @return 大导出DTO
     * @throws Exception 异常
     * @param dispatchQueryDto 派遣信息查询项
     */
    public List<ExportAllDto> exportAll(DispatchQueryDto dispatchQueryDto) throws Exception;

    /**
     * 查询所有管理员备注（手写）
     * @param dispatchId 派遣表ID
     * @return 管理员备注
     * @throws Exception 异常
     */
    public List<DispatchAdminRemark> queryAdminRemark(@Param("dispatchId")String dispatchId) throws Exception;

    /**
     * 查询所有扩展项
     * @param dispatchId 派遣表ID
     * @return 扩展项
     * @throws Exception 异常
     */
    public DispatchExtendItem queryDispatchExtendItem(@Param("dispatchId")String dispatchId) throws Exception;

    /**
     * 导出所有学院层面
     * @return 学院层面大导出
     * @throws Exception 异常
     * @param dispatchQueryDto 派遣信息查询项
     */
    public List<ExportAllCounsellorDto> exportAllCounsellor(DispatchQueryDto dispatchQueryDto) throws Exception;
}
