package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.entity.data.NationCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description:
 * 民族mapper
 * user:qiulan
 * date:2016/7/27
 * time:16:53
 */
public interface NationMapper {

    /**
     * 根据民族代码查询
     * @param nationId
     * @return
     * @throws Exception
     */
    public NationCode queryByNationId(@Param("nationId") String nationId) throws Exception;

    /**
     * 查询全部民族信息
     *
     * @return
     * @throws Exception
     */
    public List<NationCode> listAll() throws Exception;
}
