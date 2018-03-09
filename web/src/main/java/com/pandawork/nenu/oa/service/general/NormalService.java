package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.data.NormalCode;

import java.util.List;

/**
 * NormalService
 * 师范生service
 *
 * @author zhaiaxin
 * @time: 2017/4/7 14:55.
 */
public interface NormalService {

    /**
     * 根据normalStuId查询，师范生类别
     *
     * @param normalStuId
     * @return
     * @throws SSException
     */
    public NormalCode queryByNormalStuId(int normalStuId) throws SSException;

    /**
     * 查询所有师范生类别
     *
     * @return
     * @throws SSException
     */
    public List<NormalCode> listAll() throws SSException;
}
