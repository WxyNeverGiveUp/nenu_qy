package com.pandawork.nenu.oa.service.business;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.dto.business.NewProtocolInfoDto;
import com.pandawork.nenu.oa.common.dto.business.ProtocolQueryDto;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.entity.general.Material;

import java.util.List;

/**
 * description:协议业务mapper层
 * user:qiulan
 * date:2016/7/21
 * time:14:35
 */
public interface ProtocolService {

    /**
     * 学生申请新协议
     * @param protocol
     * @throws SSException
     */
    public void newProtocol(Protocol protocol) throws SSException;

    /**
     * 修改申请新协议的审核结果
     * @param protocol
     * @throws SSException
     */
    public void updateProtocolResult(Protocol protocol) throws SSException;

    /**
     * 查询申请新协议业务中的审核状态
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public List<Protocol> queryCheckStatus(int statusInfoId) throws SSException;

    /**
     * 新增材料
     *
     * @param material
     * @return
     * @throws SSException
     */
    public Material newMaterial(Material material) throws SSException;

    /**
     * 上传证明材料
     *
     * @return
     * @throws SSException
     */
    public String uploadMaterial(PandaworkMultipartFile image, Material material) throws SSException;

    /**
     * 条件查询协议，分页
     * @param protocolQueryDto
     * @param page
     * @return
     * @throws SSException
     */
    public List<Protocol> listCheckProtocolByCondition(ProtocolQueryDto protocolQueryDto, Pagination page) throws SSException;

    /**
     * 协议计数
     * @param protocolQueryDto
     * @return
     * @throws SSException
     */
    public int countProtocolByCondition(ProtocolQueryDto protocolQueryDto) throws SSException;

    /**
     * 查询变更信息学生基本信息
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public NewProtocolInfoDto queryNewProtocolInfo(int statusInfoId) throws SSException;

    /**
     * 查询变更业务材料
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public List<Material> queryMaterialById(int statusInfoId) throws SSException;

    /**
     * 查询申请新协议审核结果
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public List<Protocol> queryCheckResult(int statusInfoId) throws SSException;

    /**
     * 修改发放新协议审核结果
     * @param protocol
     * @throws SSException
     */
    public void updateCheckResult(Protocol protocol) throws SSException;

    /**
     * 判断新协议编号是否存在
     * @param agreementNumber
     * @return
     * @throws SSException
     */
    public boolean isExistAgreement(String agreementNumber) throws SSException;

    /**
     * 根据id判断是否存在该生的派遣记录
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public boolean isExistDispatchRecord(int statusInfoId) throws SSException;

    /**
     * 发放身份变更新协议
     * @param dispatchInfo
     * @return
     * @throws SSException
     */
    public void newAgreement(DispatchInfo dispatchInfo) throws SSException;

    /**
     * 查询下一个学生id
     * @return
     * @throws SSException
     */
    public int nextStuForProtocol(int statusInfoId) throws SSException;

    /**
     * 获取最大的学生id
     * @return
     * @throws SSException
     */
    public int maxStuForProtocol() throws SSException;

    /**
     * 分页查询符合条件的新协议idList
     * @param checkProtocolResult
     * @param keyWord
     * @param beginTime
     * @param endTime
     * @param page
     * @return
     * @throws SSException
     */
     public List<Integer> listIdByCondition(int checkProtocolResult, int protocolType,String keyWord, String beginTime, String endTime, Pagination page) throws SSException;

    /**
     * 根据id查询协议编号
     * @param statusInfoId
     * @return
     * @throws SSException
     */
     public String queryAgreementById(int statusInfoId) throws SSException;

    /**
     * 根据学籍id查询该生申请的所有新协议业务
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public List<Protocol> queryProtocolRecord(int statusInfoId) throws SSException;
}
