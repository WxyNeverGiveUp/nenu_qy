package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.data.MajorDivision;
import com.pandawork.nenu.oa.common.dto.data.MajorDto;
import com.pandawork.nenu.oa.common.dto.data.MajorMiddleDto;
import com.pandawork.nenu.oa.common.dto.user.UserInfoDto;
import com.pandawork.nenu.oa.common.entity.data.MajorCode;

import java.util.List;

/**
 * MajorService
 * 专业service
 *
 * @author wlm
 * @date 2016/7/28 14:52
 */
public interface MajorService {

    /**
     * 列出所有专业大类
     *
     * @return
     * @throws SSException
     * @param qualification
     */
    public List<MajorDivision> listBigAll(String qualification) throws SSException;

    /**
     * 根据专业大类代码列出专业中类
     *
     * @param code
     * @param qualification
     * @return
     * @throws SSException
     */
    public List<MajorMiddleDto> listMiddleByBigCode(String code, String qualification) throws SSException;

    /**
     * 根据专业中类代码列出专业小类
     *
     * @param code
     * @param qualification
     * @return
     * @throws SSException
     */
    public List<MajorDto> listSmallByMiddleCode(String code, String qualification) throws SSException;

    /**
     * 根据id查询专业信息
     *
     * @param id
     * @return
     * @throws SSException
     */
    public MajorCode queryById(int id) throws SSException;

    /**
     * 根据专业代码查询专业信息
     *
     * @param code
     * @param qualification
     * @return
     * @throws SSException
     */
    public MajorCode queryByCode(String code, String qualification) throws SSException;

    /**
     * 列出所有专业名字
     * @return
     * @throws SSException
     */
    public List<MajorCode> listAll()throws SSException;

    /**
     * 根据名字查找MajorId
     * @param major
     * @return
     * @throws SSException
     */
    public MajorCode queryByName(String major) throws SSException;

    /**
     * 根据学院查找专业名字
     * @param collegeCode
     * @return
     * @throws SSException
     */
    public List<UserInfoDto> queryMajorsByCollege(String collegeCode) throws SSException;
}
