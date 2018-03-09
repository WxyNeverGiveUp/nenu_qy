package com.pandawork.nenu.oa.mapper.business;

import com.pandawork.nenu.oa.common.dto.business.ProtocolAdminListDto;
import com.pandawork.nenu.oa.common.dto.business.ProtocolExportDto;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Zuosy on 2017/7/20.
 */
public interface ProtocolAdminMapper {

    /**因为是管理员，所以不涉及到增加数据
     *update 和 select
     *更改的话涉及到：
     * 审核状态的修改（应该加上审核人）、未通过-审核理由的修改、通过-修改领取地点和领取时间
     */

    /**
     * 添加新协议
     * 为了方便测试 模拟学生申请协议时，在数据中添加protocolType 、 status_info_id
     * @param protocol
     * @throws Exception
     */
    public void newProtocol(Protocol protocol) throws Exception;

    /**
     * 修改审核结果和审核人
     * @param protocol
     * @throws Exception
     */
    public void updateCheckResultAndCheckOperator(Protocol protocol) throws Exception;

    /**
     * 修改审核理由
     * 如果审核未通过须添加审核理由
     * @param protocol
     * @throws Exception
     */


    public void updateCheckReason(Protocol protocol) throws Exception;

    /**
     * 修改领取地点和领取时间
     * 审核通过了，就告诉学生领取地点和领取时间
     * @param protocol
     * @throws Exception
     */
    public void updateFetchPlaceAndFetchTime(Protocol protocol) throws Exception;

    /**
     * 查询涉及到：
     * 查询ProtocolAdminListDto里面的全部内容、
     * 按照筛选条件查询ProtocolAdminListDto（筛选条件：）
     * 根据学籍ID查看Protocol显示按照时间降序排列
     */

    /**
     * 查询PtotocolAdminListDto的全部内容
     * @return
     * @throws Exception
     */
    public List<ProtocolAdminListDto> queryProtocolAdminListDto() throws Exception;

    /**
     * 根据条件查询ProtocolAdminListDto
     * @param stuType
     * @param sex
     * @param college
     * @param qualification
     * @param normalStu
     * @param originPlace
     * @param stuLength
     * @param isRegistered
     * @param trainingMode
     * @param protocolType
     * @param checkProtocolResult
     * @param createTime
     * @param name
     * @param stuNumber
     * @return
     * @throws Exception
     */
    public List<ProtocolAdminListDto> queryProtocolAdminListDtoByConditions (@Param("stuType") int stuType,
                                                                             @Param("sex") int sex,
                                                                             @Param("college") String college,
                                                                             @Param("major") String major,
                                                                             @Param("qualification") String qualification,
                                                                             @Param("normalStu") int normalStu,
                                                                             @Param("originPlace") int originPlace,
                                                                             @Param("stuLength") double stuLength,
                                                                             @Param("isRegistered") int isRegistered,
                                                                             @Param("trainingMode") int trainingMode,
                                                                             @Param("protocolType") int protocolType,
                                                                             @Param("checkProtocolResult") int checkProtocolResult,
                                                                             @Param("beginCreateTime") Date beginCreateTime,
                                                                             @Param("endCreateTime") Date endCreateTime,
                                                                             @Param("name") String name,
                                                                             @Param("stuNumber") String stuNumber,
                                                                             @Param("curPage") int curPage,
                                                                             @Param("pageSize") int pageSize,
                                                                             @Param("type") String type) throws Exception;

    /**
     * 根据学籍ID查看Protocol信息（按createTime降序排序）
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public List<Protocol> queryProtocolByStatusInfoId(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 根据学籍的ID查找 该学生最新申请协议的ID
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public int queryLastProtocolByStatusInfoId(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 根据协议ID查看协议信息
     * @param id
     * @return
     * @throws Exception
     */
    public Protocol queryByProtocolId(@Param("id") int id) throws Exception;

    /**
     * CountByStatusInfoId
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public Integer countByStatusInfoId(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * CountByCondition 可能跟分页有关
     * @param name
     * @param idNumber
     * @param candidateNumber
     * @param stuNumber
     * @param sex
     * @param stuLength
     * @param qualification
     * @param college
     * @param
     * @param major
     * @param normalStu
     * @param originPlace
     * @param trainingMode
     * @param checkProtocolResult
     * @return
     * @throws Exception
     */
    public int countByCondition(@Param("stuType") int stuType,
                                @Param("sex") int sex,
                                @Param("college") String college,
                                @Param("major") String major,
                                @Param("qualification") String qualification,
                                @Param("normalStu") int normalStu,
                                @Param("originPlace") int originPlace,
                                @Param("stuLength") double stuLength,
                                @Param("isRegistered") int isRegistered,
                                @Param("trainingMode") int trainingMode,
                                @Param("protocolType") int protocolType,
                                @Param("checkProtocolResult") int checkProtocolResult,
                                @Param("beginCreateTime") Date beginCreateTime,
                                @Param("endCreateTime") Date endCreateTime,
                                @Param("name") String name,
                                @Param("stuNumber") String stuNumber,
                                @Param("type") String type) throws Exception;


    /**
     * 根据协议ID查询ProtocolAdminListDto
     * @param id
     * @return
     * @throws Exception
     */
    public ProtocolAdminListDto queryProtocolAdminListDtoById (@Param("id") int id) throws Exception;

    /**
     * 根据学籍ID查询ProtocolAdminListDto
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public List<ProtocolAdminListDto> queryProtocolAdminListDtoByStatusInfoId (@Param("statusInfoId") int statusInfoId ) throws Exception;

    /**
     * 协议编号在数据库中有木有
     * @param agreementNumber
     * @return
     * @throws Exception
     */
    public int queryAgreementNumberExist(@Param("agreementNumber") String agreementNumber) throws Exception;

    /**
     * 根据协议的类型查找协议
     * @param type
     * @return
     * @throws Exception
     */
    public List<ProtocolExportDto> queryProtocolByType(@Param("type") Integer type) throws Exception;
}