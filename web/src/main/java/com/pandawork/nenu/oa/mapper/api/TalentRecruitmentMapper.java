package com.pandawork.nenu.oa.mapper.api;

import com.pandawork.nenu.oa.common.dto.api.TalentRecruitmentDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zy on 2017/8/10.
 */
public interface TalentRecruitmentMapper {

    /**
     * 查询符合条件的学生（人才邀约api）
     * @param qualification
     * @param provinceCode
     * @param normalStu
     * @param majors
     * @return
     * @throws Exception
     */
    public List<TalentRecruitmentDto> listByCondition(@Param("qualification") String  qualification,
                                                      @Param("provinceCode") int provinceCode,
                                                      @Param("normalStu") int normalStu,
                                                      @Param("majors")List<String> majors) throws Exception;

    /**
     * 查询符合条件的学生数量(api)
     * @param qualification
     * @param provinceCode
     * @param normalStu
     * @param majors
     * @return
     * @throws Exception
     */
    public Integer countByCondition(@Param("qualification") String  qualification,
                                    @Param("provinceCode") int provinceCode,
                                    @Param("normalStu") int normalStu,
                                    @Param("majors")List<String> majors) throws Exception;
}
