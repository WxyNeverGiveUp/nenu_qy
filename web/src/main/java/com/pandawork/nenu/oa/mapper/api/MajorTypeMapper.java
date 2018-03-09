package com.pandawork.nenu.oa.mapper.api;

import com.pandawork.nenu.oa.common.entity.data.MajorCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zy on 2017/8/7.
 */
public interface MajorTypeMapper {

    /**
     * 专业层次
     * @return
     * @throws Exception
     */
    public List<MajorCode> listQualification() throws Exception;


    /**
     * 专业大类
     * @return
     * @throws Exception
     */
    public List<MajorCode> listMajorDivisionByQualification(@Param("qualification") String qualification) throws Exception;


    /**
     * 专业中类
     * @return
     * @throws Exception
     */
    public List<MajorCode> listMajorClassByMajorDivision(@Param("majorDivision") String majorDivision) throws Exception;

    /**
     * 根据专业层次和专业中类查出专业(api)
     * @param qualification
     * @param majorClass
     * @return
     * @throws Exception
     */
    public List<String> listMajorsByCondition(@Param("qualification")String qualification,@Param("majorClass") String majorClass) throws Exception;
}
