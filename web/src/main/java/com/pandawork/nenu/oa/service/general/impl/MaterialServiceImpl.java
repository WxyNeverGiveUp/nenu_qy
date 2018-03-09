package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.enums.general.MaterialTypeEnums;
import com.pandawork.nenu.oa.common.enums.general.TrueFalseEnums;
import com.pandawork.nenu.oa.common.enums.other.FileUploadPathEnums;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.DateUtils;
import com.pandawork.nenu.oa.mapper.general.MaterialMapper;
import com.pandawork.nenu.oa.service.general.MaterialService;
import com.pandawork.nenu.oa.service.other.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 材料Service实现
 * MaterialServiceImpl
 *
 * @author wlm
 * @date 2016/7/21 13:57
 */

@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    MaterialMapper materialMapper;

    @Autowired
    FileUploadService fileUploadService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public Material newMaterial(Material material) throws SSException {
        if (Assert.isNull(material)) {
            throw SSException.get(OaException.MaterialNotNull);
        }
        if (Assert.isNull(material.getStatusInfoId()) || Assert.lessOrEqualZero(material.getStatusInfoId())) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        try {
            return commonDao.insert(material);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MaterialInsertFailed, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public Material uploadMaterial(Material material, PandaworkMultipartFile file, FileUploadPathEnums pathEnums, String... extendPath) throws SSException {
        String path = "";
        Date date = new Date();
        String extPath = DateUtils.formatDate(date, "yyyyMMdd");
        path = fileUploadService.uploadFile(file, pathEnums, extPath);
        if (Assert.isNull(material.getMaterialName()) || material.getMaterialName().equals("")){
            material.setMaterialName(MaterialTypeEnums.valueOf(material.getMaterialType()).getName());
        }
        material.setMaterialUrl(path);
        material.setIsDeleted(0);
        this.newMaterial(material);
        return material;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateName(int id, String name) throws SSException {
        if (Assert.lessOrEqualZero(id)) {
            throw SSException.get(OaException.IdIllegal);
        }
        Material material = new Material();
        material.setId(id);
        material.setMaterialName(name);
        try {
            commonDao.update(material);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MaterialUpdateFailed, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateProtocolIdByStatusInfoIdAndType(int protocolId,int statusInfoId, int type) throws SSException{

        if (Assert.lessOrEqualZero(statusInfoId)) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        if (Assert.isNull(MaterialTypeEnums.valueOf(type))) {
            throw SSException.get(OaException.MaterialTypeIllegal);
        }
        try {
            materialMapper.updateProtocolIdByStatusInfoIdAndType(protocolId,statusInfoId,type);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MaterialUpdateFailed, e);
        }
    }

    @Override
    public Material queryById(int id) throws SSException {
        if (Assert.lessOrEqualZero(id)) {
            throw SSException.get(OaException.IdIllegal);
        }
        try {
            Material material = commonDao.queryById(Material.class, id);
            if (material.getIsDeleted() == 1) {
                material = null;
            }
            return material;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MaterialQueryFailed, e);
        }
    }

    @Override
    public List<Material> listByStatusInfoIdAndType(int statusInfoId, int type) throws SSException {
        if (Assert.lessOrEqualZero(statusInfoId)) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        if (Assert.isNull(MaterialTypeEnums.valueOf(type))) {
            throw SSException.get(OaException.MaterialTypeIllegal);
        }

        List<Material> materialList = Collections.emptyList();
        try {
            materialList = materialMapper.listByStatusInfoIdAndType(statusInfoId, type);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MaterialQueryFailed, e);
        }
        return materialList;
    }

    @Override
    public List<Material> listAllByStatusInfoIdAndType(int statusInfoId, int type) throws SSException {
        if (Assert.lessOrEqualZero(statusInfoId)) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        if (Assert.isNull(MaterialTypeEnums.valueOf(type))) {
            throw SSException.get(OaException.MaterialTypeIllegal);
        }

        List<Material> materialList = Collections.emptyList();
        try {
            materialList = materialMapper.listAllByStatusInfoIdAndType(statusInfoId, type);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MaterialQueryFailed, e);
        }
        return materialList;
    }


    public List<Material> listRecent(int statusInfoId, int type) throws SSException {
        if (Assert.lessOrEqualZero(statusInfoId)) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        if (Assert.isNull(MaterialTypeEnums.valueOf(type))) {
            throw SSException.get(OaException.MaterialTypeIllegal);
        }

        List<Material> materialList = Collections.emptyList();
        try {
            materialList = materialMapper.listRecent(statusInfoId, type);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MaterialQueryFailed, e);
        }
        return materialList;
    }
    @Override
    public List<Material> listAllByStatusInfoId(int statusInfoId) throws SSException{
        if (Assert.lessOrEqualZero(statusInfoId)) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }

        List<Material> materialList = Collections.emptyList();
        try {
            materialList = materialMapper.listAllByStatusInfoId(statusInfoId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MaterialQueryFailed, e);
        }
        return materialList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void delById(int id) throws SSException {
        if (Assert.lessOrEqualZero(id)) {
            throw SSException.get(OaException.IdIllegal);
        }
        Material material = new Material();
        material.setId(id);
        material.setIsDeleted(TrueFalseEnums.True.getId());
        try {
            commonDao.update(material);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MaterialDeleteFailed, e);
        }
    }

    @Override
    public List<Material> listByProtocolId(int protocolId) throws SSException{
        if (Assert.lessOrEqualZero(protocolId)) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }

        List<Material> materialList = Collections.emptyList();
        try {
            materialList = materialMapper.listByProtocolId(protocolId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MaterialQueryFailed, e);
        }
        return materialList;
    }

}
