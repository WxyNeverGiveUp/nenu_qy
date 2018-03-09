package com.pandawork.nenu.oa.service.api;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.api.TalentRecruitmentDto;

import java.util.List;

/**
 * Created by zy on 2017/8/10.
 */
public interface TalentRecruitmentService {

    /**
     * 查询符合条件的学生（人才邀约）
     * @param qualification
     * @param provinceCode
     * @param normalStu
     * @param majors
     * @return
     * @throws SSException
     */
    public List<TalentRecruitmentDto> listByCondition(String qualification,
                                                      int provinceCode,
                                                      int normalStu,
                                                      List<String> majors) throws SSException;

    /**
     * 查询符合条件的学生数量
     * @param qualification
     * @param provinceCode
     * @param normalStu
     * @param majors
     * @return
     * @throws SSException
     */
    public Integer countByCondition(String qualification,
                                    int provinceCode,
                                    int normalStu,
                                    List<String> majors) throws SSException;
}
