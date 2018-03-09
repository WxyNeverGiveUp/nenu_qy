package com.pandawork.nenu.oa.service.business;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.oa.common.dto.business.ProtocolAdminListDto;
import com.pandawork.nenu.oa.common.dto.business.ProtocolExportDto;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Zuosy on 2017/7/21.
 */
public interface ProtocolAdminService {

    /**
     * 添加新协议
     * @param protocol
     * @throws SSException
     */
    public void newProtocol(Protocol protocol) throws SSException;

    /**
     *修改审核结果和审核人
     * @param protocol
     * @throws SSException
     */
    public void updateCheckResultAndCheckOperator(Protocol protocol) throws SSException;

    /**
     * 修改审核原因
     * @param protocol
     * @throws SSException
     */
    public void updateCheckReason(Protocol protocol) throws SSException;

    /**
     * 修改领取地点和领取时间
     * 审核通过了，就告诉学生领取地点和领取时间
     * @param protocol
     * @throws SSException
     */
    public void updateFetchPlaceAndFetchTime(Protocol protocol) throws SSException;

    /**
     * 查询PtotocolAdminListDto的全部内容
     * @return
     * @throws SSException
     */
    public List<ProtocolAdminListDto> queryProtocolAdminListDto() throws SSException;

    /**
     * 根据条件查询ProtocolAdminListDto
     * @param stuType
     * @param sex
     * @param college
     * @param major
     * @param qualification
     * @param normalStu
     * @param originPlace
     * @param stuLength
     * @param isRegistered
     * @param trainingMode
     * @param protocolType
     * @param checkProtocolResult
     * @param beginCreateTime
     * @param endCreateTime
     * @param keyword
     * @return
     * @throws SSException
     */
    public List<ProtocolAdminListDto> queryProtocolAdminListDtoByConditions (Pagination page,
                                                                             @Param("stuType") int stuType,
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
                                                                             @Param("keyword") String keyword,
                                                                             @Param("type") String type) throws SSException;


    /**
     * 根据学籍ID查看Protocol信息（按createTime降序排序）
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public List<Protocol> queryProtocolByStatusInfoId(int statusInfoId) throws SSException;

    /**
     * 根据学籍ID查找 该学生最新申请协议的ID
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public int queryLastProtocolByStatusInfoId(int statusInfoId) throws SSException;

    /**
     * 根据协议ID查看协议信息
     * @param id
     * @return
     * @throws SSException
     */
    public Protocol queryByProtocolId(int id) throws SSException;

    /**
     * CountByStatusInfoId
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public Integer countByStatusInfoId(int statusInfoId) throws SSException;

    /**
     * 根据条件查询符合条件的数据的数量
     * @param stuType
     * @param sex
     * @param college
     * @param major
     * @param qualification
     * @param normalStu
     * @param originPlace
     * @param stuLength
     * @param isRegistered
     * @param trainingMode
     * @param protocolType
     * @param checkProtocolResult
     * @param beginCreateTime
     * @param endCreateTime
     * @param keyword
     * @return
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
                                @Param("keyword") String keyword,
                                @Param("type") String type) throws SSException;


    /**
     * 根据协议ID查询ProtocolAdminListDto
     * @param id
     * @return
     * @throws SSException
     */
    public ProtocolAdminListDto queryProtocolAdminListDtoById(int id) throws SSException;

    /**
     * 根据学籍ID查询ProtocolAdminListDto
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public List<ProtocolAdminListDto> queryProtocolAdminListDtoByStatusInfoId(int statusInfoId) throws SSException;

    /**
     * update Protocol information by id
     * @param protocol
     * @throws SSException
     */
    public void updateById(Protocol protocol) throws SSException;

    /**
     * 协议编号在数据库中有木有
     * @param agreementNumber
     * @return
     * @throws SSException
     */
    public boolean queryAgreementNumberExist(String agreementNumber) throws SSException;

    /**
     * 根据业务预约的类型返回 查找出来的列表
     * @param type
     * @return
     * @throws SSException
     */
    public List<ProtocolExportDto> exportByType(Integer type) throws SSException;
}