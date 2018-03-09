package com.pandawork.nenu.oa.service.data.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.dto.data.VarifyQueryDto;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.data.VerifyMapper;
import com.pandawork.nenu.oa.service.data.VerifyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hao Zhang on 2015/5/12.
 */
@Service
public class VerifyInfoServiceImpl implements VerifyInfoService {

    @Autowired
    VerifyMapper verifyMapper;
    @Override
    public List<String> getAllColleges() {
        return verifyMapper.getAllColleges();
    }

    @Override
    public int numsOfGraduation(VarifyQueryDto varifyQueryDto) {
        return verifyMapper.numsOfGraduation(varifyQueryDto);
    }

    @Override
    public int numsOfVerifying(VarifyQueryDto varifyQueryDto) {
        return verifyMapper.numsOfVerifying(varifyQueryDto);
    }

    @Override
    public List<String> verifyer(HashMap map) {
        return verifyMapper.verifyer(map);
    }

    @Override
    public Date verifyCompleteTime(int id) {
        return null;
    }

    @Override
    public void updateVerifyCompleteTime(int id, Date date) throws SSException {
        try {
            verifyMapper.updateVerifyCompleteTime(id, date);
        }
        catch(Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException);
        }
    }

    @Override
    public void updateLastLoginTime(int id, Date date) throws SSException {
        try {
            verifyMapper.updateLastLoginTime(id, date);
        }
        catch(Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException);
        }
    }

    @Override
    public Date lastLoginTime(int id) throws SSException {
        try {
            return verifyMapper.lastLoginTime(id);
        }
        catch(Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException);
        }
    }
}
