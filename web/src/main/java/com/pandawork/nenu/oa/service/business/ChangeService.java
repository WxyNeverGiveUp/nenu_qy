package com.pandawork.nenu.oa.service.business;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.dto.business.ChangeInfoDto;
import com.pandawork.nenu.oa.common.dto.business.ChangeQueryDto;
import com.pandawork.nenu.oa.common.entity.business.Change;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.entity.general.Material;

import java.util.List;

/**
 * description:身份变更业务service层
 * user:qiulan
 * date:2016/7/21
 * time:14:34
 */
public interface ChangeService {

    /**
     * 学生身份变更
     * @param change
     * @throws SSException
     */
    public void newIdentity(Change change) throws SSException;

    /**
     * 修改变更审核结果
     * @param change
     * @throws SSException
     */
    public void updateChangeResult(Change change) throws SSException;

    /**
     * 查询变更业务中的审核状态
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public List<Change> queryCheckStatus(int statusInfoId) throws SSException;

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
     * 变更信息查询，分页
     * @param changeQueryDto
     * @param page
     * @return
     * @throws SSException
     */
    public List<Change> listCheckChangeByCondition(ChangeQueryDto changeQueryDto, Pagination page) throws SSException;

    /**
     * 变更信息计数
     * @param changeQueryDto
     * @return
     * @throws SSException
     */
    public int countChangeByCondition(ChangeQueryDto changeQueryDto) throws SSException;

    /**
     * 查询变更信息学生基本信息
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public ChangeInfoDto queryAlterInfo(int statusInfoId) throws SSException;

    /**
     * 查询变更业务材料
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public List<Material> queryMaterialById (int statusInfoId) throws SSException;

    /**
     * 查询变更业务审核结果
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public Change queryCheckResult(int statusInfoId) throws SSException;

    /**
     * 修改变更业务审核结果
     * @param change
     * @return
     * @throws SSException
     */
    public void updateCheckResult(Change change) throws SSException;

    /**
     * 发放身份变更新协议
     * @param dispatchInfo
     * @return
     * @throws SSException
     */
    public void newAgreement(DispatchInfo dispatchInfo) throws SSException;

    /**
     * 获取业务变更所有符合条件学生id
     * @return
     * @throws SSException
     */
    public List<ChangeInfoDto> allStudentForChange() throws SSException;

    /**
     * 分页查询符合条件的变更业务idList
     * @param checkChangeResult
     * @param changeType
     * @param keyWord
     * @param beginTime
     * @param endTime
     * @param page
     * @return
     * @throws SSException
     */
    public List<Integer> listIdByCondition(int checkChangeResult, int changeType, String keyWord, String beginTime, String endTime, Pagination page) throws SSException;

    /**
     * 根据学籍id查询该生申请的所有变更业务
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public List<Change> queryAlterRecord(int statusInfoId) throws SSException;

}
