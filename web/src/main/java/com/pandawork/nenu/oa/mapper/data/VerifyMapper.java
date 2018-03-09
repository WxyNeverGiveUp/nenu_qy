package com.pandawork.nenu.oa.mapper.data;

import com.pandawork.nenu.oa.common.dto.data.VarifyQueryDto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hao Zhang on 2015/5/12.
 */
public interface VerifyMapper {
    /**
     * From t_college_code
     * @return All colleges.
     */
    public List<String> getAllColleges();

    /**
     *
     * @param map
     * @return Get the numbers of graduation students of the same qualification of one college
     */
    public int numsOfGraduation(VarifyQueryDto varifyQueryDto);

    /**
     *
     * @param map : college and qualification
     * @return Get the numbers of students which are still verifying.
     */
    public int numsOfVerifying(VarifyQueryDto varifyQueryDto);

    /**
     *
     * @return
     */
    public List<String> verifyer(HashMap map);

    /**
     *
     * @param id
     * @return
     */
    public Date verifyCompleteTime(int id);

    /**
     *
     * @param id
     * @return
     */
    public Date lastLoginTime(int id);

    /**
     *
     * @param id
     * @param date
     */
    public void updateLastLoginTime(int id, Date date);

    /**
     *
     * @param id
     * @param date
     */
    public void updateVerifyCompleteTime(int id, Date date);
}
