package com.pandawork.nenu.oa.service.statistics;

import com.pandawork.nenu.oa.common.dto.history.College;

import java.util.List;

/**
 * 获取学院列表Service
 * @author Lw
 * @time 2017/8/10 10:43
 */
public interface CollegeListService {

    /**
     * 获取本科生需要进行就业率统计的所有学院
     * @return
     */
    public List<College> getUnderCollegeList();

    /**
     * 获取研究生需要进行就业率统计的所有学院
     * @return
     */
    public List<College> getPostCollegeList();

    /**
     * 总体就业率的学院以研究生为准
     * 师范生的学院全部以本科生为准
     */

}
