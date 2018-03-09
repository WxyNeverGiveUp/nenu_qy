package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.enums.other.FileUploadPathEnums;

import java.util.List;

/**
 * 材料Service
 * MaterialService
 *
 * @author wlm
 * @date 2016/7/21 13:51
 */
public interface MaterialService {

    /**
     * 新增材料
     *
     * @param material
     * @return
     * @throws SSException
     */
    public Material newMaterial(Material material) throws SSException;

    /**
     * 上传材料
     *
     *
     * @param material
     * @param file
     * @param pathEnums
     * @param extendPath
     * @return
     * @throws SSException
     */
    public Material uploadMaterial(Material material, PandaworkMultipartFile file, FileUploadPathEnums pathEnums, String... extendPath) throws SSException;

    /**
     * 修改材料名称
     *
     * @param id
     * @param name
     * @throws SSException
     */
    public void updateName(int id, String name) throws SSException;

    /**
     * 根据id查询材料
     *
     * @param id
     * @return
     * @throws SSException
     */
    public Material queryById(int id) throws SSException;

    /**
     * 根据学籍id和材料类型查询材料
     *
     * @param statusInfoId
     * @param type
     * @return
     * @throws SSException
     */
    public List<Material> listByStatusInfoIdAndType(int statusInfoId, int type) throws SSException;

    /**
     * 根据学籍id和材料类型查询所有材料记录
     * @param statusInfoId
     * @param type
     * @return
     * @throws SSException
     */
    public List<Material> listAllByStatusInfoIdAndType(int statusInfoId, int type) throws SSException;

    /**
     * 根据学籍id和材料类型查询最新材料记录
     * @param statusInfoId
     * @param type
     * @return
     * @throws SSException
     */
    public List<Material> listRecent(int statusInfoId,  int type) throws SSException;

    /**
     * 根据学籍id查询材料所有未删除记录
     * @param statusInfoId
     * @return
     * @throws SSException
     * @Author chenyuting
     */
    public List<Material> listAllByStatusInfoId(int statusInfoId) throws SSException;

    /**
     * 根据材料id删除材料(假删)
     *0
     * @param id
     * @throws SSException
     */
    public void delById(int id) throws SSException;

    /**
     * 根据协议Id查询所有未删除材料记录
     * @param protocolId
     * @return
     * @throws SSException
     */
    public List<Material> listByProtocolId(int protocolId) throws SSException;

    /**
     * 根据根据学籍id和材料类型修改协议Id
     * @param protocolId
     * @param statusInfoId
     * @param type
     * @throws SSException
     */
    public void updateProtocolIdByStatusInfoIdAndType( int protocolId,int statusInfoId, int type) throws SSException;
}
