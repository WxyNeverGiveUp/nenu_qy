package com.pandawork.nenu.oa.service.data;

import com.google.common.util.concurrent.AbstractService;
import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.data.VarifyQueryDto;
import org.springframework.stereotype.Service;

import javax.validation.constraints.DecimalMin;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Hao Zhang on 2015/5/12.
 */

public interface VerifyInfoService {
    /**
     * From t_college_code
     * @return All colleges.
     */
    public List<String> getAllColleges();

    /**
     *
     * @return Get the numbers of graduation students of the same qualification of one college
     */
    public int numsOfGraduation(VarifyQueryDto varifyQueryDto);

    /**
     *
     * @return Get the numbers of students which are still verifying.
     */
    public int numsOfVerifying(VarifyQueryDto varifyQueryDto);

    /**
     *
     * @return
     */
    public List<String> verifyer(HashMap map)throws SSException;


    public Date verifyCompleteTime(int id);

    public void updateVerifyCompleteTime(int id, Date date) throws SSException;


    public void updateLastLoginTime(int id, Date date) throws SSException;
    public Date lastLoginTime(int id) throws SSException;



}
