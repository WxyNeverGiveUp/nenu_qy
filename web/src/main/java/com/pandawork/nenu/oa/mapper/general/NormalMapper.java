package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.entity.data.NormalCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * NormalMapper
 * 师范生mapper
 *
 * @author zhaiaxin
 * @time: 2017/4/7 14:40.
 */
public interface NormalMapper {

    /**
     * 根据normalStuId，查询师范生类别
     *
     * @param normalStuId
     * @return
     * @throws Exception
     */
    public NormalCode queryByNormalStuId(@Param("normalStuId") int normalStuId) throws Exception;

    /**
     * 查询全部师范生类别
     *
     * @return
     * @throws Exception
     */
    public List<NormalCode> listAll() throws Exception;
}
