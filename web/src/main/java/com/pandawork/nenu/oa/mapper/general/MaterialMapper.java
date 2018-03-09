package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.entity.general.Material;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 材料Mapper
 * MaterialMapper
 *
 * @author wlm
 * @date 2016/7/21 14:43
 */
public interface MaterialMapper {

    /**
     * 根据学籍id和材料类型查询材料
     *
     * @param statusInfoId
     * @param type
     * @return
     * @throws Exception
     */
    public List<Material> listByStatusInfoIdAndType(@Param("statusInfoId") int statusInfoId, @Param("type") int type) throws Exception;

    /**
     * 根据学籍id和材料类型查询所有材料记录
     * @param statusInfoId
     * @param type
     * @return
     * @throws Exception
     */
    public List<Material> listAllByStatusInfoIdAndType(@Param("statusInfoId") int statusInfoId, @Param("type") int type) throws Exception;


    /**
     * 根据学籍id和材料类型查询最新提交的记录
     * @param statusInfoId
     * @param type
     * @return
     * @throws Exception
     */
    public List<Material> listRecent(@Param("statusInfoId") int statusInfoId, @Param("type") int type) throws Exception;
    /**
     * 根据学籍id查询材料所有未删除记录
     * @param statusInfoId
     * @return
     * @throws Exception
     * @Author chenyuting
     */
    public List<Material> listAllByStatusInfoId(@Param("statusInfoId") int statusInfoId) throws Exception;

    /**
     * 根据协议Id查询材料所有未删除记录
     * @param protocolId
     * @return
     * @throws Exception
     */
    public List<Material> listByProtocolId(@Param("protocolId") int protocolId) throws Exception;

    /**
     * 根据学籍id和材料类型修改协议Id
     * @param protocolId
     * @param statusInfoId
     * @param type
     * @throws Exception
     */
    public void updateProtocolIdByStatusInfoIdAndType(@Param("protocolId") int protocolId,@Param("statusInfoId") int statusInfoId,
                                                      @Param("type") int type) throws Exception;


    /**
     * 根据派遣id修改派遣材料
     * @param dispatchInfoId
     * @throws Exception
     */
    public void updateDispatchByDispatchInfoId (@Param("dispatchInfoId") int dispatchInfoId) throws Exception;
}
