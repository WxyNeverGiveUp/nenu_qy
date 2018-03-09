package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.entity.data.TrainingModeCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TrainingModeMapper
 * 培养方式mapper
 *
 * @author wlm
 * @date 2016/7/28 15:33
 */
public interface TrainingModeMapper {

    /**
     * 根据code查询培养方式
     *
     * @param code
     * @return
     * @throws Exception
     */
    public TrainingModeCode queryByCode(@Param("code") int code) throws Exception;

    /**
     * 查询全部培养方式
     *
     * @return
     * @throws Exception
     */
    public List<TrainingModeCode> listAll() throws Exception;
}
