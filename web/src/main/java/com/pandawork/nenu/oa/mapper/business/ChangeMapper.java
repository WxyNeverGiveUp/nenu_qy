package com.pandawork.nenu.oa.mapper.business;


import com.pandawork.nenu.oa.common.dto.business.ChangeInfoDto;
import com.pandawork.nenu.oa.common.dto.business.ChangeQueryDto;
import com.pandawork.nenu.oa.common.entity.business.Change;
import com.pandawork.nenu.oa.common.entity.general.Material;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**

 * description:身边变更业务mapper层
 * user:qiulan
 * date:2016/7/21
 * time:14:27
 */
public interface ChangeMapper {

    /**
     * 身份变更
     * @param change
     * @throws Exception
     */
    public void newIdentity(Change change) throws Exception;

    /**
     * 修改完后更新当前状态
     * @param change
     * @throws Exception
     */
    public void updateCurrentStatus(Change change) throws Exception;

    /**
     *更新审核结果
     * @param change
     * @throws Exception
     */
    public void updateCheckResult(Change change) throws Exception;

    /**
     * 获取变更业务详情
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public List<Change> queryCheckStatus(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 条件查询身份变更信息，分页
     * @param changeQueryDto
     * @return
     * @throws Exception
     */
    public List<Change> listCheckChangeByCondition(ChangeQueryDto changeQueryDto) throws Exception;

    /**
     * 计数
     * @param changeQueryDto
     * @return
     * @throws Exception
     */
    public int countChangeByCondition (ChangeQueryDto changeQueryDto) throws Exception;

    /**
     * 获取变更详情学生基本信息
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public ChangeInfoDto queryAlterInfo(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 获取变更业务材料
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public List<Material> queryMaterialById(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 获取变更业务审核结果
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public Change queryCheckResult(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 修改变更业务审核结果
     * @param change
     * @return
     * @throws Exception
     */
    public void updateChangeResult(Change change) throws Exception;

    /**
     * 分页查询符合条件的业务变更idList
     * @param beginTime
     * @param name
     * @param studentNumber
     * @param checkChangeResult
     * @param changeType
     * @param pCurrent
     * @param pSize
     * @return
     * @throws Exception
     */
    public List<Integer> listIdByCondition(@Param("beginTime") String beginTime, @Param("endTime") String endTime,
                                           @Param("name") String name, @Param("studentNumber") String studentNumber,
                                           @Param("checkChangeResult") int checkChangeResult,@Param("changeType") int changeType,
                                           @Param("pCurrent")int pCurrent, @Param("pSize")int pSize) throws Exception;

    /**
     * 获取业务变更所有符合条件学生id
     * @return
     * @throws Exception
     */
    public List<ChangeInfoDto> allStudentForChange() throws Exception;

    /**
     * 获取变更业务下一个学生id
     * @return
     * @throws Exception
     */
    public int nextStuForChange(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 获取变更业务最大的学生id
     * @return
     * @throws Exception
     */
    public int maxStuForChange() throws Exception;

    /**
     * 根据学籍id查询该生申请的所有变更业务
     * @param statusInfoId
     * @return
     * @throws Exception
     */
    public List<Change> queryAlterRecord(@Param("statusInfoId") int statusInfoId) throws Exception;


    //List<Integer> listIdByCondition(Date createTime, String name, String studentNumber, int checkChangeResult, int changeType, int pCurrent, int pSize);
}
