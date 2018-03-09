package com.pandawork.nenu.oa.service.api.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.nenu.oa.common.dto.api.TalentRecruitmentDto;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.api.TalentRecruitmentMapper;
import com.pandawork.nenu.oa.service.api.TalentRecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人才邀约
 * Created by zy on 2017/8/10.
 */
@Service("talentRecruitmentService")
public class TalentRecruitmentServiceImpl implements TalentRecruitmentService{

    @Autowired
    TalentRecruitmentMapper talentRecruitmentMapper;

    @Override
    public List<TalentRecruitmentDto> listByCondition(String qualification, int provinceCode, int normalStu, List<String> majors) throws SSException{
        try{
            return talentRecruitmentMapper.listByCondition(qualification, provinceCode, normalStu,majors);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentQueryFailed, e);
        }
    }

    @Override
    public Integer countByCondition(String qualification, int provinceCode, int normalStu, List<String> majors) throws SSException{
        try{
            return talentRecruitmentMapper.countByCondition(qualification, provinceCode, normalStu,majors);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StudentQueryFailed, e);
        }
    }

}
