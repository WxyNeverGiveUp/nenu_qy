package com.pandawork.nenu.oa.mapper.data;

import com.pandawork.nenu.oa.common.entity.data.WhereAboutGoIntendCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 拟毕业去向mapper
 *
 * @Author: chenyuting
 * @DateTime: 2017/5/31  19:07
 */
public interface WhereAboutGoIntendMapper {

    /**
     * 根据code查询拟毕业去向
     * @param whereAboutGoIntendId
     * @return
     * @throws Exception
     */
    public WhereAboutGoIntendCode queryByCode(@Param("whereAboutGoIntendId") int whereAboutGoIntendId) throws Exception;

    /**
     * 查询全部拟毕业去向
     * @return
     * @throws Exception
     */
    public List<WhereAboutGoIntendCode> listAll() throws Exception;
}
