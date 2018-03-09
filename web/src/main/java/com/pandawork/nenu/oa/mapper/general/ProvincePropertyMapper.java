package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.entity.general.ProvinceProperty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description:
 * 省份mapper
 * user:qiulan
 * date:2016/7/27
 * time:16:53
 */
public interface ProvincePropertyMapper {

    /**
     * 根据省份代码查询
     * @param code
     * @return
     * @throws Exception
     */
    public ProvinceProperty queryByCode(@Param("code") String code) throws Exception;

    /**
     * 查询全部省份信息
     *
     * @return
     * @throws Exception
     */
    public List<ProvinceProperty> listAll() throws Exception;

    /**
     * 根据省份名称查询省份代码
     * @param name
     * @return
     * @throws Exception
     */
    public String queryByName(@Param("name")String name)throws Exception;

    /**
     * 根据省份id查询省份名称(api)
     * @param id
     * @return
     * @throws Exception
     */
    public String queryNameById(@Param("id") int id ) throws Exception;
}
