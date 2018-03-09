package com.pandawork.nenu.oa.mapper.general;

import com.pandawork.nenu.oa.common.dto.data.MajorDivision;
import com.pandawork.nenu.oa.common.dto.data.MajorDto;
import com.pandawork.nenu.oa.common.dto.data.MajorMiddleDto;
import com.pandawork.nenu.oa.common.dto.user.UserInfoDto;
import com.pandawork.nenu.oa.common.entity.data.MajorCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MajorMapper
 *
 * @author wlm
 * @date 2016/7/28 16:41
 */
public interface MajorMapper {

    /**
     * 列出所有专业大类
     *
     * @return
     * @throws Exception
     * @param qualification
     */
    public List<MajorDivision> listBigAll(@Param("qualification") String qualification) throws Exception;

    /**
     * 根据专业大类代码列出专业中类
     *
     * @param code
     * @param qualification
     * @return
     * @throws Exception
     */
    public List<MajorMiddleDto> listMiddleByBigCode(@Param("code") String code, @Param("qualification") String qualification) throws Exception;

    /**
     * 根据专业中类代码列出专业小类
     *
     * @param code
     * @param qualification
     * @return
     * @throws Exception
     */
    public List<MajorDto> listSmallByMiddleCode(@Param("code") String code, @Param("qualification") String qualification) throws Exception;

    /**
     * 根据专业代码查询专业小类信息
     *
     * @param code
     * @param qualification
     * @return
     * @throws Exception
     */
    public MajorCode queryByCode(@Param("code") String code, @Param("qualification") String qualification) throws Exception;

    /**
     * 列出所有专业名字
     * @return
     * @throws Exception
     */
    public List<MajorCode> listAll()throws Exception;

    /**
     * 根据名字查找MajorId
     * @param major
     * @return
     * @throws Exception
     */
    public MajorCode queryByName(@Param("major") String major)throws Exception;

    /**
     * 根据学院查找专业名字
     * @param collegeCode
     * @return
     * @throws Exception
     */
    public List<UserInfoDto> queryMajorsByCollege(@Param("collegeCode") String collegeCode) throws Exception;
}
