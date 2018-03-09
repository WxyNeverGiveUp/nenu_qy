package com.pandawork.nenu.oa.service.history;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.history.Major;

import java.util.List;
import java.util.Map;

/**
 * 学院对应的专业的代码
 * @author Lw
 * @time 2017/7/26 9:57
 */
public interface MajorCodeService  {
    public Map<String, List<Major>> init()  throws SSException;

    public List<Major> getMajorListByCollegeCode(String collegeCode) throws SSException;
}
