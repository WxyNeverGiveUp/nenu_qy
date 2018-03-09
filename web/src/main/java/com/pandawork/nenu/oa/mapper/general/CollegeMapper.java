package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.entity.data.CollegeCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CollegeMapper
 * 学院mapper
 *
 * @author wlm
 * @date 2016/7/27 15:51
 */
public interface CollegeMapper {

    /**
     * 根据code查询学院信息
     *
     * @param code
     * @return
     * @throws Exception
     */
    public CollegeCode queryByCode(@Param("code") String code) throws Exception;

    /**
     * 查询全部学院信息
     *
     * @return
     * @throws Exception
     */
    public List<CollegeCode> listAll() throws Exception;
}
