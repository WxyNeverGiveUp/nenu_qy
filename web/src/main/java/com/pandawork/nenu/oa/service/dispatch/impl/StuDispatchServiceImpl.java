package com.pandawork.nenu.oa.service.dispatch.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchRemarksDto;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.enums.other.FileUploadPathEnums;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.DateUtils;
import com.pandawork.nenu.oa.mapper.dispatch.StuDispatchMapper;
import com.pandawork.nenu.oa.service.dispatch.StuDispatchService;
import com.pandawork.nenu.oa.service.general.MaterialService;
import com.pandawork.nenu.oa.service.other.FileUploadService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Description:派遣学生service实现
 * Author: luowanli
 * Date: 2016/7/13
 * Time: 9:44
 */

@Service(value ="stuDispatchService")
public class StuDispatchServiceImpl implements StuDispatchService {
    @Autowired
    CommonDao commonDao;

    @Autowired
    StuDispatchMapper stuDispatchMapper;

    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    MaterialService materialService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { SSException.class, Exception.class, RuntimeException.class })
    public DispatchInfo newDispatch(DispatchInfo dispatchInfo) throws SSException {
        try {
            DispatchInfo stuDispatch = stuDispatchMapper.queryDispatchByStuId(dispatchInfo.getId());
            if(stuDispatch == null) {
                throw SSException.get(OaException.StuDispatchInfoIsNotNull) ;
            }
            else {
                return commonDao.insert(dispatchInfo);
            }
        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    public void updateDispatch(DispatchInfo dispatchInfo) throws SSException {
        try {
            commonDao.update(dispatchInfo);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuDispatchUpdateFailed, e);
        }
    }

    @Override
    public DispatchInfo queryDispatchByStuId(Integer statusInfoId) throws SSException {
        if (Assert.lessOrEqualZero(statusInfoId)) {
            throw SSException.get(OaException.IdIllegal);
        }
        try {
            return stuDispatchMapper.queryDispatchByStuId(statusInfoId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuDispatchQueryFailed, e);
        }
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
//    public String uploadMaterial(PandaworkMultipartFile image, Material material, Integer statusInfoId) throws SSException {
//        String path = "";
//        Date date = new Date();
//        String uploadTime = DateUtils.formatDate(date, "yyyyMMdd");
//        path = fileUploadService.uploadFile(image, FileUploadPathEnums.StuDispatchInformation, uploadTime);
//        String fileName = image.getOriginalFilename();
//        material.setMaterialType(2);
//        material.setMaterialUrl(path);
//        material.setStatusInfoId(statusInfoId);
//        material.setMaterialName(fileName);
//        materialService.newMaterial(material);
//        return path;
//    }

    @Override
    public DispatchRemarksDto queryRemarksByStuId(Integer statusInfoId) throws SSException{
        if (Assert.lessOrEqualZero(statusInfoId)) {
            throw SSException.get(OaException.IdIllegal);
        }
        try {
            return stuDispatchMapper.queryRemarksByStuId(statusInfoId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuDispatchQueryFailed, e);
        }
    }

}
