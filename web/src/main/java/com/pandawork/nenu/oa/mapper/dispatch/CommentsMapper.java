package com.pandawork.nenu.oa.mapper.dispatch;

import com.pandawork.nenu.oa.common.entity.dispatch.Comments;
import org.apache.ibatis.annotations.Param;

/**
 * CommentsMapper
 * 办公信息Mapper
 *
 * @author wlm
 * @date 2016/10/25 13:40
 */
public interface CommentsMapper {

    /**
     * 根据学籍id查询办公信息
     *
     * @param statusId
     * @return
     * @throws Exception
     */
    public Comments queryByStatusId(@Param("statusId") int statusId) throws Exception;

}
