package com.pandawork.nenu.oa.mapper.student.status;

import com.pandawork.nenu.oa.common.entity.student.status.StuUpdateInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学籍修改信息Mapper
 * StuUpdateInfoMapper
 *
 * @author wlm
 * @date 2016/7/20 17:53
 */
public interface StuUpdateInfoMapper {

    /**
     * 根据学籍id和isStudent查询学籍最新修改信息
     * @param stuStatusInfoId
     * @param isStudent
     * @return
     * @throws Exception
     */
    public List<StuUpdateInfo> listLatestByStuStatusInfoId(@Param("stuStatusInfoId") int stuStatusInfoId
    ,@Param("isStudent") int isStudent) throws Exception;

    /**
     * 根据学籍id查询所有学籍修改信息
     * @param stuStatusInfoId
     * @return
     * @throws Exception
     */
    public List<StuUpdateInfo> listByStuStatusInfoId(@Param("stuStatusInfoId") int stuStatusInfoId) throws  Exception;
    /**
     * 根据学籍id和修改类型查询修改信息
     *
     * @param statusInfoId
     * @param type
     * @return
     * @throws Exception
     */
    public List<StuUpdateInfo> listByStatusInfoIdAndType(@Param("statusInfoId") int statusInfoId, @Param("type") int type) throws Exception;
}
