package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.dto.data.SchoolDto;
import com.pandawork.nenu.oa.common.entity.data.SchoolCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SchoolMapper
 * 高校mapper
 *
 * @author wlm
 * @date 2016/9/1 18:38
 */
public interface SchoolMapper {

    /**
     * 根据code查询高校信息
     *
     * @param code
     * @return
     * @throws Exception
     */
    public SchoolCode queryByCode(@Param("code") String code) throws Exception;

    /**
     * 查询全部高校信息
     *
     * @return
     * @throws Exception
     */
    public List<SchoolCode> listAll() throws Exception;

    /**
     * 根据code查询部分高校信息
     *
     * @param code
     * @return
     * @throws Exception
     */
    public SchoolDto queryDtoByCode(@Param("code") String code) throws Exception;
}
