package com.pandawork.nenu.oa.service.api.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.entity.data.MajorCode;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.api.MajorTypeMapper;
import com.pandawork.nenu.oa.service.api.MajorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * MajorTypeServiceImpl
 * api中专业三级联动的实现
 *
 * Created by zy on 2017/8/7.
 */
@Service("majorTypeService")
public class MajorTypeServiceImpl implements MajorTypeService{
    @Autowired
    MajorTypeMapper majorTypeMapper;

    @Override
    public List<MajorCode> listQualification() throws SSException{
        try{
            return majorTypeMapper.listQualification();
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MajorQueryFailed,e);
        }
    }

    @Override
    public List<MajorCode> listMajorDivisionByQualification(String qualification) throws SSException{
        try{
            return majorTypeMapper.listMajorDivisionByQualification(qualification);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MajorQueryFailed,e);
        }
    }

    @Override
    public List<MajorCode> listMajorClassByMajorDivision(String majorDivision) throws SSException{
        try{
            return majorTypeMapper.listMajorClassByMajorDivision(majorDivision);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MajorQueryFailed,e);
        }
    }

    @Override
    public List<String> listMajorsByCondition(String qualification,String majorClass) throws SSException{
        try{
            HashSet<String> hashSet = new HashSet<>(majorTypeMapper.listMajorsByCondition(qualification, majorClass));
            List<String> majorList = new ArrayList<>();
            majorList.addAll(hashSet);
            return majorList;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.MajorQueryFailed,e);
        }
    }
}
