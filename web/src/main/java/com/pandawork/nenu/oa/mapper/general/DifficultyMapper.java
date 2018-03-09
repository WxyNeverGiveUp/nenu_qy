package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.entity.data.DifficultyCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description:
 * 困难生类别mapper
 * user:qiulan
 * date:2016/7/27
 * time:16:53
 */
public interface DifficultyMapper {

    /**
     * 根据困难生类别查询
     * @param difficultyId
     * @return
     * @throws Exception
     */
    public DifficultyCode queryByDifficultyId(@Param("difficultyId") int difficultyId) throws Exception;


    /**
     * 查询全部信息
     * @return
     * @throws Exception
     */
    public List<DifficultyCode> listAll() throws Exception;
}
