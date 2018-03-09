package com.pandawork.nenu.oa.mapper.data;

import com.pandawork.nenu.oa.common.entity.data.WhereAboutGoCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:毕业去向Mapper
 * Author:luowanli
 * Date:2016/7/27
 * Time:11:46
 */

public interface WhereAboutGoMapper {

    /**
     * 根据code查询毕业去向
     * @param whereAboutGoId
     * @return
     * @throws Exception
     */
    public WhereAboutGoCode queryByCode(@Param("whereAboutGoId") int whereAboutGoId) throws Exception;

    /**
     * 查询全部毕业去向
     * @return
     * @throws Exception
     */
    public List<WhereAboutGoCode> listAll() throws Exception;
}
