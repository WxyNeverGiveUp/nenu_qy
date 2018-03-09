package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.entity.data.PlaceCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * PlaceMapper
 *
 * @author wlm
 * @date 2016/7/27 17:29
 */
public interface PlaceMapper {

    /**
     * 根据code查询地区信息
     *
     * @param code
     * @return
     * @throws Exception
     */
    public PlaceCode queryByCode(@Param("code") String code) throws Exception;

    /**
     * 查询全部地区信息
     *
     * @return
     * @throws Exception
     */
    public List<PlaceCode> listAll() throws Exception;
}
