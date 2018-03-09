package com.pandawork.nenu.oa.service.general.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.dto.data.MajorDivision;
import com.pandawork.nenu.oa.common.dto.data.MajorDto;
import com.pandawork.nenu.oa.common.dto.data.MajorMiddleDto;
import com.pandawork.nenu.oa.common.dto.user.UserInfoDto;
import com.pandawork.nenu.oa.common.entity.data.MajorCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.MajorMapper;
import com.pandawork.nenu.oa.service.general.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MajorServiceImpl
 * 专业service实现
 *
 * @author wlm
 * @date 2016/7/28 16:40
 */

@Service("majorService")
public class MajorServiceImpl implements MajorService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    MajorMapper majorMapper;

    @Override
    public List<MajorDivision> listBigAll(String qualification) throws SSException {
        try {
            return majorMapper.listBigAll(qualification);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MajorQueryFailed, e);
        }
    }

    @Override
    public List<MajorMiddleDto> listMiddleByBigCode(String code, String qualification) throws SSException {
        try {
            if (Assert.isNull(code)) {
                throw SSException.get(OaException.MajorCodeNotNull);
            }
            return majorMapper.listMiddleByBigCode(code, qualification);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MajorQueryFailed, e);
        }
    }

    @Override
    public List<MajorDto> listSmallByMiddleCode(String code, String qualification) throws SSException {
        try {
            if (Assert.isNull(code)) {
                throw SSException.get(OaException.MajorCodeNotNull);
            }
            return majorMapper.listSmallByMiddleCode(code, qualification);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MajorQueryFailed, e);
        }
    }

    @Override
    public MajorCode queryById(int id) throws SSException {
        try {
            return commonDao.queryById(MajorCode.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MajorQueryFailed, e);
        }
    }

    @Override
    public MajorCode queryByCode(String code, String qualification) throws SSException {
        try {
            if (Assert.isNull(code)) {
                throw SSException.get(OaException.MajorCodeNotNull);
            }
            return majorMapper.queryByCode(code, qualification);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MajorQueryFailed, e);
        }
    }
    @Override
    public List<MajorCode> listAll()throws SSException{
        try{
            return majorMapper.listAll();
        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MajorListFailed,e);
        }
    }

    @Override
    public MajorCode queryByName(String major) throws SSException {
        try{
            return majorMapper.queryByName(major);
        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MajorQueryByNameFailed,e);
        }
    }

    @Override
    public List<UserInfoDto> queryMajorsByCollege(String collegeCode) throws SSException {
        try{
            return majorMapper.queryMajorsByCollege(collegeCode);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MajorQueryByCollegeCodeFailed,e);
        }
    }

}
