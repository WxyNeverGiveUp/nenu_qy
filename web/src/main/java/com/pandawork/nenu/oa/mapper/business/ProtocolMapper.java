package com.pandawork.nenu.oa.mapper.business;


import com.pandawork.nenu.oa.common.dto.business.NewProtocolInfoDto;
import com.pandawork.nenu.oa.common.dto.business.ProtocolQueryDto;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.entity.general.Material;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description:协议审核业务mapper层
 * user:qiulan
 * date:2016/7/21
 * time:14:30
 */
public interface ProtocolMapper {

    /**
     * 申请新协议
     * @param protocol
     * @throws Exception
     */
    public void newProtocol(Protocol protocol) throws Exception;

    /**
     * 修改申请新协议的审核结果
     * @param protocol
     * @throws Exception
     */
    public void updateProtocolResult(Protocol protocol) throws Exception;

    /**
     * 修改完后更新当前状态
     * @param protocol
     * @throws Exception
     */
    public void updateCurrentStatus(Protocol protocol) throws Exception;

    /**
     * 获取变更业务详情
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public List<Protocol> queryCheckStatus(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 条件查询协议，分页
     * @param protocolQueryDto
     * @return
     * @throws Exception
     */
    public List<Protocol> listCheckProtocolByCondition(ProtocolQueryDto protocolQueryDto) throws Exception;

    /**
     * 计数
     * @param protocolQueryDto
     * @return
     * @throws Exception
     */
    public int countProtocolByCondition (ProtocolQueryDto protocolQueryDto) throws Exception;

    /**
     * 分页查询符合条件的新协议idList
     * @param beginTime
     * @param name
     * @param studentNumber
     * @param checkProtocolResult
     * @param pCurrent
     * @param pSize
     * @return
     * @throws Exception
     */
    public List<Integer> listIdByCondition (@Param("beginTime") String beginTime, @Param("endTime") String endTime,
                                            @Param("name") String name, @Param("studentNumber") String studentNumber,
                                            @Param("checkProtocolResult") int checkProtocolResult,
                                            @Param("protocolType") int protocolType,
                                            @Param("pCurrent") int pCurrent,
                                            @Param("pSize") int pSize) throws Exception;

    /**
     * 获取申请新协议学生基本信息
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public NewProtocolInfoDto queryNewProtocolInfo(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 获取获取新协议材料
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public List<Material> queryMaterialById(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 查询申请新协议审核结果
     * @param statusInfo
     * @return
     * @throws Exception
     */
    public List<Protocol> queryCheckResult(@Param("statusInfoId") int statusInfo) throws Exception;

    /**
     * 修改发放新协议审核结果
     * @param protocol
     * @throws Exception
     */
    public void updateCheckResult(Protocol protocol) throws Exception;

    /**
     * 判断是否存在新增的协议编号
     * @param agreementNumber
     * @return
     * @throws Exception
     */
    public int countByAgreement(@Param("agreementNumber") String agreementNumber) throws Exception;

    /**
     * 获取申请新协议下一个学生id
     * @return
     * @throws Exception
     */
    public int nextStuForProtocol(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 获取申请新协议最大的学生id
     * @return
     * @throws Exception
     */
    public int maxStuForProtocol() throws Exception;

    /**
     * 根据Id判断是否存在该生的派遣记录
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public int countDispatchRecord(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 根据id查询协议编号
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public String queryAgreementById(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 根据学籍id查询该生申请的所有新协议业务
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public List<Protocol> queryProtocolRecord(@Param("statusInfoId") int statusInfoId) throws Exception;

}
