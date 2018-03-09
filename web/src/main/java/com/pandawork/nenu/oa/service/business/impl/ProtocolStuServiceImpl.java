package com.pandawork.nenu.oa.service.business.impl;


import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.dto.business.ProtocolStuDto;

import com.pandawork.nenu.oa.common.entity.business.Protocol;

import com.pandawork.nenu.oa.common.entity.general.ProvinceProperty;
import com.pandawork.nenu.oa.common.enums.business.ProtocolTypeEnums;
import com.pandawork.nenu.oa.common.enums.general.CheckResultEnums;

import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.DateUtils;
import com.pandawork.nenu.oa.mapper.business.ProtocolStuMapper;
import com.pandawork.nenu.oa.mapper.general.ProvincePropertyMapper;
import com.pandawork.nenu.oa.service.business.ProtocolStuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生端，业务预约service实现层
 *
 * @author zhaiaxin
 * @time: 2017/7/19 14:36.
 */
@Service(value = "protocolStuService")
public class ProtocolStuServiceImpl implements ProtocolStuService {

    @Autowired
    ProtocolStuMapper protocolStuMapper;


    @Autowired
    CommonDao commonDao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void newBusiness (Protocol protocol) throws SSException{
        try {
            if (Assert.isNull(protocol)) {
                return;
            } else {
                commonDao.insert(protocol);
            }
        } catch (Exception e) {
                LogClerk.errLog.error(e);
                throw SSException.get(OaException.NewBusinessException, e);
            }

    }


    public void updateBusiness(Protocol protocol) throws SSException{
        try{
            if(Assert.isNull(protocol)) {
                return;
            } else {
                commonDao.update(protocol);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.updateBusinessException, e);
        }
    }

    @Override
    public List<ProtocolStuDto> listNewProtocol (int statusInfoId) throws SSException{
        try{
            if(Assert.isNull(statusInfoId)){
                return null;
            } else {
                List<Protocol> protocolList = protocolStuMapper.listNewProtocol(statusInfoId);
                return this.convertEntityListToDto(protocolList);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ListBusinessException, e);
        }

    }

    @Override
    public List<ProtocolStuDto> listGraduate (int statusInfoId) throws SSException{
        try{
            if(Assert.isNull(statusInfoId)){
                return null;
            } else {
                List<Protocol> protocolList = protocolStuMapper.listGraduate(statusInfoId);
                return this.convertEntityListToDto(protocolList);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ListBusinessException, e);
        }

    }

    @Override
    public List<ProtocolStuDto> listFreeTeacher (int statusInfoId) throws SSException{
        try{
            if(Assert.isNull(statusInfoId)){
                return null;
            } else {
                List<Protocol> protocolList = protocolStuMapper.listFreeTeacher(statusInfoId);
                return this.convertEntityListToDto(protocolList);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ListBusinessException, e);
        }

    }

    @Override
    public List<ProtocolStuDto> listDirectional (int statusInfoId) throws SSException{
        try{
            if(Assert.isNull(statusInfoId)){
                return null;
            } else {
                List<Protocol> protocolList = protocolStuMapper.listDirectional(statusInfoId);
                return this.convertEntityListToDto(protocolList);
            }

        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ListBusinessException, e);
        }

    }


    @Override
    public List<ProtocolStuDto> listDoctorBusiness (int statusInfoId) throws SSException{
        try{
            if(Assert.isNull(statusInfoId)){
                return null;
            } else {
                List<Protocol> protocolList = protocolStuMapper.listDoctorBusiness(statusInfoId);
                return this.convertEntityListToDto(protocolList);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ListBusinessException, e);
        }

    }

    public ProtocolStuDto queryBusiness( int id) throws SSException{
        try{
            if(Assert.isNull(id)){
                return null;
            } else {
                Protocol protocol = protocolStuMapper.queryBusiness(id);
                return this.convertEntityToDto(protocol);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ListBusinessException, e);
        }
    }




    @Override
    public List<Protocol> queryProtocol (int statusInfoId,int type) throws SSException{
        try{
            if(Assert.isNull(statusInfoId)){
                return null;
            } else {
                List<Protocol> protocolList = null;
                if(type == 1){
                    protocolList = protocolStuMapper.listNewProtocol(statusInfoId);
                }else if(type == 2){
                    protocolList = protocolStuMapper.listGraduate(statusInfoId);
                }else if(type == 3){
                    protocolList = protocolStuMapper.listFreeTeacher(statusInfoId);
                }else if(type == 4){
                    protocolList = protocolStuMapper.listDirectional(statusInfoId);
                }else {
                    protocolList = protocolStuMapper.listDoctorBusiness(statusInfoId);
                }
                return protocolList;
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ListBusinessException, e);
        }

    }

//    @Override
//    public ProtocolStuDto queryFreeTeacher (int statusInfoId) throws SSException{
//        try{
//            if(Assert.isNull(statusInfoId)){
//                return null;
//            } else {
//                Protocol protocol = protocolStuMapper.queryFreeTeacher(statusInfoId);
//                return this.convertEntityToDto(protocol);
//            }
//        } catch (Exception e) {
//            LogClerk.errLog.error(e);
//            throw SSException.get(OaException.ListBusinessException, e);
//        }
//
//    }
//
//    @Override
//    public Protocol queryFreeTeacherProtocol (int statusInfoId) throws SSException{
//        try{
//            if(Assert.isNull(statusInfoId)){
//                return null;
//            } else {
//                Protocol protocol = protocolStuMapper.queryFreeTeacher(statusInfoId);
//                return protocol;
//            }
//        } catch (Exception e) {
//            LogClerk.errLog.error(e);
//            throw SSException.get(OaException.ListBusinessException, e);
//        }
//
//    }
//
//    @Override
//    public ProtocolStuDto queryGraduate (int statusInfoId) throws SSException{
//        try{
//            if(Assert.isNull(statusInfoId)){
//                return null;
//            } else {
//                Protocol protocol = protocolStuMapper.queryGraduate(statusInfoId);
//                return this.convertEntityToDto(protocol);
//            }
//        } catch (Exception e) {
//            LogClerk.errLog.error(e);
//            throw SSException.get(OaException.ListBusinessException, e);
//        }
//
//    }
//
//    @Override
//    public ProtocolStuDto queryDirectional (int statusInfoId) throws SSException{
//        try{
//            if(Assert.isNull(statusInfoId)){
//                return null;
//            } else {
//                Protocol protocol = protocolStuMapper.queryDirectional(statusInfoId);
//                return this.convertEntityToDto(protocol);
//            }
//        } catch (Exception e) {
//            LogClerk.errLog.error(e);
//            throw SSException.get(OaException.ListBusinessException, e);
//        }
//
//    }



    /**
     * 将实体转换为dto
     * @param protocol
     * @return
     * @throws SSException
     */

    private ProtocolStuDto convertEntityToDto(Protocol protocol) throws Exception {

        if(Assert.isNull(protocol)){
            return null;
        }
        ProtocolStuDto protocolStuDto = new ProtocolStuDto();
        protocolStuDto.setId(protocol.getId());
        protocolStuDto.setProtocolType(ProtocolTypeEnums.valueOf(protocol.getProtocolType()).getName());
        protocolStuDto.setFreeTeacherProvince(protocolStuMapper.queryProvince(protocol.getFreeTeacherProvince()));
        protocolStuDto.setCreateTime(DateUtils.formatDate(protocol.getCreateTime(),"yyyy-MM-dd"));
        protocolStuDto.setCheckProtocolResult(CheckResultEnums.valueOf(protocol.getCheckProtocolResult()).getName());
        protocolStuDto.setCheckProtocolReason(protocol.getCheckProtocolReason());
        protocolStuDto.setCheckProtocolOperator(protocol.getCheckProtocolOperator());
        protocolStuDto.setCheckProtocolTime(DateUtils.formatDate(protocol.getCheckProtocolTime(),"yyyy-MM-dd"));
        protocolStuDto.setFetchPlace(protocol.getFetchPlace());
        protocolStuDto.setFetchTime(DateUtils.formatDate(protocol.getFetchTime(),"yyyy-MM-dd HH:mm"));
        protocolStuDto.setStatusInfoId(protocol.getStatusInfoId());
        return protocolStuDto;
    }


    /**
     * 将实体List转换为dtoList
     *
     * @param protocolList
     * @return
     * @throws SSException
     */


    private List<ProtocolStuDto> convertEntityListToDto(List<Protocol> protocolList) throws Exception {
        List<ProtocolStuDto> protocolStuDtoList = new ArrayList<>();

        if(Assert.isNull(protocolList)){
            return null;
        }

        for (Protocol protocol: protocolList) {
            ProtocolStuDto protocolStuDto = new ProtocolStuDto();
            protocolStuDto.setId(protocol.getId());
            protocolStuDto.setProtocolType(ProtocolTypeEnums.valueOf(protocol.getProtocolType()).getName());
            protocolStuDto.setFreeTeacherProvince(protocolStuMapper.queryProvince(protocol.getFreeTeacherProvince()));
            protocolStuDto.setCreateTime(DateUtils.formatDate(protocol.getCreateTime(),"yyyy-MM-dd"));
            protocolStuDto.setCheckProtocolResult(CheckResultEnums.valueOf(protocol.getCheckProtocolResult()).getName());
            protocolStuDto.setCheckProtocolReason(protocol.getCheckProtocolReason());
            protocolStuDto.setCheckProtocolTime(DateUtils.formatDate(protocol.getCheckProtocolTime(),"yyyy-MM-dd"));
            protocolStuDto.setStatusInfoId(protocol.getStatusInfoId());
            protocolStuDtoList.add(protocolStuDto);
        }

        return protocolStuDtoList;
    }



    public String queryProvince(String code) throws SSException {

        try {
            if (Assert.isNull(code)) {
                return null;
            } else {
                String province = protocolStuMapper.queryProvince(code);
                return province;
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ListBusinessException, e);
        }
    }

}
