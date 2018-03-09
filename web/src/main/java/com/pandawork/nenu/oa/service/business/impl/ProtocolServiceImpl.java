package com.pandawork.nenu.oa.service.business.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.dto.business.NewProtocolInfoDto;
import com.pandawork.nenu.oa.common.dto.business.ProtocolQueryDto;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.enums.other.FileUploadPathEnums;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.DateUtils;
import com.pandawork.nenu.oa.mapper.business.ProtocolAdminMapper;
import com.pandawork.nenu.oa.mapper.business.ProtocolMapper;
import com.pandawork.nenu.oa.service.business.ProtocolService;
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
 * description:协议业务service实现层
 * user:qiulan
 * date:2016/7/21
 * time:14:36
 */
@Service(value = "protocolService")
public class ProtocolServiceImpl implements ProtocolService {
    @Autowired
    ProtocolMapper protocolMapper;

    @Autowired
    ProtocolAdminMapper protocolAdminMapper;

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
    public void newProtocol(Protocol protocol) throws SSException {
        try {
            if (Assert.isNotNull(protocol.getProtocolType())) {
                protocolMapper.updateCurrentStatus(protocol);//先将前一条插入的记录的是否为当前状态置为0
                protocolMapper.newProtocol(protocol);
            } else {
                return;
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateProtocolResult(Protocol protocol) throws SSException {
        try {
            if (Assert.isNull(protocol))
                return ;
            else{
                protocolMapper.updateProtocolResult(protocol);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UpdateAlterCheckException);
        }
    }

    @Override
    public List<Protocol> queryCheckStatus(int statusInfoId) throws SSException {
        try{
            if(Assert.lessOrEqualZero(statusInfoId)){
                return null;
            } else {
                return protocolMapper.queryCheckStatus(statusInfoId);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.QueryProtocolCheckStatusFailed, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public String uploadMaterial(PandaworkMultipartFile image, Material material) throws SSException {
        Date date = new Date();
        String extPath = DateUtils.formatDate(date, "yyyyMMdd");
        Material material1 = materialService.uploadMaterial(material, image, FileUploadPathEnums.StuApplyForNewProtocol, extPath);
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
    public List<Protocol> listCheckProtocolByCondition(ProtocolQueryDto protocolQueryDto, Pagination page) throws SSException {
        try {
            int pCurrent = 0, pSize = 15;
            if (page != null) {
                pCurrent = page.getCurrentFristPosition();
                pSize = page.getPageSize();
                protocolQueryDto.setpCurrent(pCurrent);
                protocolQueryDto.setpSize(pSize);
            }
            return protocolMapper.listCheckProtocolByCondition(protocolQueryDto);
        }catch (Exception ee){
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.QueryNewProtocolInfoException);
        }
    }


    @Override
    public NewProtocolInfoDto queryNewProtocolInfo(int statusInfoId) throws SSException {
        try{
            if(Assert.lessOrEqualZero(statusInfoId))
                return null;
            else {
                return protocolMapper.queryNewProtocolInfo(statusInfoId);
            }
        }
        catch (Exception e ){
            System.out.println( e.getMessage() );
            throw SSException.get(OaException.QueryNewProtocolInfoException);
        }
    }

    @Override
    public int countProtocolByCondition(ProtocolQueryDto protocolQueryDto) throws SSException {
        try {
            return protocolMapper.countProtocolByCondition(protocolQueryDto);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CountCheckProtocolException);

        }
    }
    @Override
    public List<Material> queryMaterialById(int statusInfoId) throws SSException {
        try{
            if(statusInfoId == 0){
                return null;
            }
            else{
                return protocolMapper.queryMaterialById(statusInfoId);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw SSException.get(OaException.QueryNewProtocolMaterialException);
        }
    }

    @Override
    public List<Protocol> queryCheckResult(int statusInfoId) throws SSException {
        try{
            if(statusInfoId == 0){
                return null;
            }
            else{
                return protocolAdminMapper.queryProtocolByStatusInfoId(statusInfoId);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw SSException.get(OaException.QueryNewProtocolCheckResultException);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateCheckResult(Protocol protocol) throws SSException {
        try {
            if (Assert.isNull(protocol))
                return ;
            else{
                protocolMapper.updateCheckResult(protocol);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UpdateNewProtocolCheckException);
        }
    }

    @Override
    public boolean isExistAgreement(String agreementNumber) throws SSException {
        try{
            return protocolMapper.countByAgreement(agreementNumber) >=1 ? false:true;
        }catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.IsExistAgreementException);
        }
    }

    @Override
    public boolean isExistDispatchRecord(int statusInfoId) throws SSException {
        try{
            return protocolMapper.countDispatchRecord(statusInfoId) >=1 ? true:false;
        }catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.IsExistDispatchException);
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
//                dispatchInfoService.updateCurrentAgreement(dispatchInfo);
                dispatchInfoService.newAgreement(dispatchInfo);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.NewProtocolException);
        }
    }

    @Override
    public int nextStuForProtocol(int statusInfoId) throws SSException {
        try{
           if(Assert.lessOrEqualZero(statusInfoId)){
               return 0;
           }
           else if(statusInfoId == protocolMapper.maxStuForProtocol()){
               System.out.println("当前已是最后一个学生信息");
               return statusInfoId;
           }
           else if(statusInfoId > protocolMapper.maxStuForProtocol()){
               System.out.println("当前id已大于最大符合条件id，请重新输入id");
               return protocolMapper.maxStuForProtocol();
           }
           else{
               return protocolMapper.nextStuForProtocol(statusInfoId);
           }
        }
        catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.NextStudentException);
        }
    }

    @Override
    public int maxStuForProtocol() throws SSException {
       try{
           return protocolMapper.maxStuForProtocol();
       }
       catch (Exception e){
           LogClerk.errLog.error(e);
           throw SSException.get(OaException.NextStudentException);
       }
    }

    @Override
    public List<Integer> listIdByCondition(int checkProtocolResult, int protocolType, String keyWord, String beginTime, String endTime, Pagination page) throws SSException {
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
            idList = protocolMapper.listIdByCondition( beginTime, endTime, name, studentNumber, checkProtocolResult, protocolType, pCurrent, pSize);

        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.NextStudentException, e);
        }
        return idList;
    }

    @Override
    public String queryAgreementById(int statusInfoId) throws SSException {
        try{
            return protocolMapper.queryAgreementById(statusInfoId);
        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.NextStudentException, e);
        }
    }

    @Override
    public List<Protocol> queryProtocolRecord(int statusInfoId) throws SSException {
        try {
            List<Protocol> protocolList = protocolMapper.queryProtocolRecord(statusInfoId);
            return protocolList;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchQueryFaild, e);
        }
    }
}
