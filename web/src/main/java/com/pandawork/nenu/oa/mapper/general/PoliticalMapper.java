package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.entity.data.PoliticalCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description:
 * 政治面貌mapper
 * user:qiulan
 * date:2016/7/27
 * time:16:53
 */
public interface PoliticalMapper {

    /**
     * 根据政治面板代码查询
      * @param politicalStandId
     * @return
     * @throws Exception
     */
    public PoliticalCode queryByPoliticalStandId(@Param("politicalStandId") String politicalStandId) throws Exception;

    /**
     * 查询全部政治面貌信息
     *
     * @return
     * @throws Exception
     */
    public List<PoliticalCode> listAll() throws Exception;
}
