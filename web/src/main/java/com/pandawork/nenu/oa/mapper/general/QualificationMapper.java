package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.entity.data.QualificationCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * QualificationMapper
 * 学历mapper
 *
 * @author wlm
 * @date 2016/7/27 16:21
 */
public interface QualificationMapper {

    /**
     * 根据code查询学院信息
     *
     * @param code
     * @return
     * @throws Exception
     */
    public QualificationCode queryByCode(@Param("code") String code) throws Exception;

    /**
     * 查询全部学院信息
     *
     * @return
     * @throws Exception
     */
    public List<QualificationCode> listAll() throws Exception;
}
