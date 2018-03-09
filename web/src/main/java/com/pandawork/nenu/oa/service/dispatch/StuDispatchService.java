package com.pandawork.nenu.oa.service.dispatch;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchRemarksDto;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.entity.general.Material;
import org.apache.ibatis.annotations.Param;

/**
 * Description:学生派遣service
 * Author: luowanli
 * Date: 2016/7/13
 * Time: 9:44
 */

public interface StuDispatchService {

    /**
     * 新增学生派遣信息
     * @param dispatchInfo
     * @throws SSException
     */
    public DispatchInfo newDispatch(DispatchInfo dispatchInfo) throws SSException;

    /**
     * 学生修改派遣信息
     * @param dispatchInfo
     * @throws SSException
     */
    public void updateDispatch(DispatchInfo dispatchInfo) throws SSException;

    /**
     * 学生查看派遣信息
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public DispatchInfo queryDispatchByStuId(Integer statusInfoId) throws SSException;

//    /**
//     * 文件上传
//     * @param image
//     * @param material
//     * @param statusInfoId
//     * @return
//     * @throws SSException
//     */
//    public String uploadMaterial(PandaworkMultipartFile image, Material material, Integer statusInfoId) throws SSException;

    /**
     * 添加派遣备注信息
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    public DispatchRemarksDto queryRemarksByStuId(@Param("statusInfoId") Integer statusInfoId) throws SSException;

}
