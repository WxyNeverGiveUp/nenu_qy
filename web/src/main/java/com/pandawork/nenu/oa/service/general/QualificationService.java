package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.data.QualificationCode;

import java.util.List;

/**
 * QualificationService
 *
 * @author wlm
 * @date 2016/7/27 16:06
 */
public interface QualificationService {

    /**
     * 根据id查询学历
     *
     * @param id
     * @return
     * @throws SSException
     */
    public QualificationCode queryById(int id) throws SSException;

    /**
     * 根据code查询学历
     *
     * @param code
     * @return
     * @throws SSException
     */
    public QualificationCode queryByCode(String code) throws SSException;

    /**
     * 查询所有学历
     *
     * @return
     * @throws SSException
     */
    public List<QualificationCode> listAll() throws SSException;
}
