package com.pandawork.nenu.oa.service.business.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.dto.business.ChangeInfoDto;
import com.pandawork.nenu.oa.common.dto.business.ChangeQueryDto;
import com.pandawork.nenu.oa.common.entity.business.Change;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.enums.other.FileUploadPathEnums;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.DateUtils;
import com.pandawork.nenu.oa.mapper.business.ChangeMapper;
import com.pandawork.nenu.oa.mapper.business.ProtocolMapper;
import com.pandawork.nenu.oa.service.business.ChangeService;
import com.pandawork.nenu.oa.service.dispatch.DispatchInfoService;
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
 * description:身份变更业务service实现层
 * user:qiulan
 * date:2016/7/21
 * time:14:36
 */
@Service( value = "changeService")
public class ChangeServiceImpl implements ChangeService {

    @Autowired
    ChangeMapper changeMapper;

    @Autowired
    ProtocolMapper protocolMapper;

    @Autowired
    DispatchInfoService dispatchInfoService;

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    CommonDao commonDao;

    @Autowired
    MaterialService materialService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void newIdentity(Change change) throws SSException {
        try {
            if (Assert.isNull(change.getChangeType())) {
                return;
            } else {
                changeMapper.updateCurrentStatus(change);//先将前一条插入的记录的是否为当前状态置为0
                changeMapper.newIdentity(change);//插入新的身份变更信息
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.NewIdentityException, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateChangeResult(Change change) throws SSException {
        try {
            if (Assert.isNull(change))
                return ;
            else{
                changeMapper.updateChangeResult(change);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UpdateAlterCheckException);
        }
    }

    @Override
    public List<Change> queryCheckStatus(int statusInfoId) throws SSException {
        try{
            if(Assert.lessOrEqualZero(statusInfoId)){
                return null;
            } else {
              return changeMapper.queryCheckStatus(statusInfoId);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.QueryChangeCheckStatusFailed, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public String uploadMaterial(PandaworkMultipartFile image, Material material) throws SSException {
        Date date = new Date();
        String extPath = DateUtils.formatDate(date, "yyyyMMdd");
        Material material1 = materialService.uploadMaterial(material, image, FileUploadPathEnums.StuIdentityChange, extPath);
        return material1.getMaterialUrl();
    }

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
    public List<Change> listCheckChangeByCondition(ChangeQueryDto changeQueryDto, Pagination page) throws SSException {
        try {
            int pCurrent = 0, pSize = 15;
            if (page != null) {
                pCurrent = page.getCurrentFristPosition();
                pSize = page.getPageSize();
                changeQueryDto.setpCurrent(pCurrent);
                changeQueryDto.setpSize(pSize);
            }
            return changeMapper.listCheckChangeByCondition(changeQueryDto);

        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.QueryAlterInfoException);
        }
    }

    @Override
    public ChangeInfoDto queryAlterInfo(int statusInfoId) throws SSException {
        try{
            if (Assert.lessOrEqualZero(statusInfoId)) {
                return null;
            }
            else {
                return changeMapper.queryAlterInfo(statusInfoId);
            }
        }
        catch (Exception e ){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.QueryAlterInfoException);
        }
    }

    @Override
    public List<Material> queryMaterialById(int statusInfoId) throws SSException {
        try{
            if (Assert.lessOrEqualZero(statusInfoId)) {
                return null;
            }
            else{
                return changeMapper.queryMaterialById(statusInfoId);
            }
        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.QueryAlterMaterialException);
        }

    }

    @Override
    public Change queryCheckResult(int statusInfoId) throws SSException {
        try{

            if (Assert.lessOrEqualZero(statusInfoId)) {
                return null;
            }
            else{
                return changeMapper.queryCheckResult(statusInfoId);

            }
        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.QueryAlterCheckException);
        }
    }

    @Override
    public int countChangeByCondition(ChangeQueryDto changeQueryDto) throws SSException {
        try {
            return changeMapper.countChangeByCondition(changeQueryDto);
        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CountCheckChangeException);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateCheckResult(Change change) throws SSException {
        try {
            if (Assert.isNull(change))
                return ;
            else{
                changeMapper.updateCheckResult(change);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UpdateAlterCheckException);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void newAgreement(DispatchInfo dispatchInfo) throws SSException {
        try{
            if(Assert.isNull(dispatchInfo)){
                return ;
            }
            else{
//                dispatchInfoService.updateCurrentAgreement(dispatchInfo);//先把之前的派遣信息记录是否为当前协议置为0
                dispatchInfoService.newAgreement(dispatchInfo);//插入一条新的派遣信息记录，含有新的协议编号，当前状态为1
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.NewProtocolException);
        }
    }

    @Override
    public List<ChangeInfoDto> allStudentForChange() throws SSException {
        try{
            return changeMapper.allStudentForChange();
        }
        catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.NextStudentException);
        }
    }

    @Override
    public List<Integer> listIdByCondition(int checkChangeResult, int changeType, String keyWord, String beginTime, String endTime, Pagination page) throws SSException {
        List<Integer> idList = Collections.emptyList();
        int pCurrent = 0, pSize = 15;
        String name = null;
        String studentNumber = null;
        if (!Assert.isNull(keyWord)) {
            keyWord = keyWord.trim();
            if (keyWord.equals("")) {
                keyWord = null;
            } else if (keyWord.matches("[0-9]+")) {
                studentNumber = keyWord;
            } else {
                name = keyWord;
            }
        }

        if (!Assert.isNull(page)) {
            pCurrent = page.getCurrentFristPosition();
            pSize = page.getPageSize();
        }

        try {
            idList = changeMapper.listIdByCondition(beginTime, endTime, name, studentNumber, checkChangeResult, changeType, pCurrent, pSize);

        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.NextStudentException, e);
        }

        return idList;
    }

    @Override
    public List<Change> queryAlterRecord(int statusInfoId) throws SSException {
        try {
            List<Change> changeList = changeMapper.queryAlterRecord(statusInfoId);
            return changeList;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchQueryFaild, e);
        }
    }

}
