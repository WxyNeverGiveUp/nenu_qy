package com.pandawork.nenu.oa.service.api;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.data.MajorCode;

import java.util.List;

/**
 * 人才邀约api专业
 * Created by zy on 2017/8/7.
 */
public interface MajorTypeService {

    /**
     * 专业层次
     * @return
     * @throws SSException
     */
    public List<MajorCode> listQualification() throws SSException;

    /**
     * 专业大类
     * @return
     * @throws SSException
     */
    public List<MajorCode> listMajorDivisionByQualification(String qualification) throws SSException;

    /**
     * 专业中类
     * @return
     * @throws SSException
     */
    public List<MajorCode> listMajorClassByMajorDivision(String majorDivision) throws SSException;

    /**
     * 根据专业层次和专业中类查出专业(api)
     * @param qualification
     * @param majorClass
     * @return
     * @throws SSException
     */
    public List<String> listMajorsByCondition(String qualification,String majorClass) throws SSException;
}
