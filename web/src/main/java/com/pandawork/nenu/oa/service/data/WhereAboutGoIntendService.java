package com.pandawork.nenu.oa.service.data;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.data.WhereAboutGoIntendCode;

import java.util.List;

/**
 * 拟毕业去向Service
 *
 * @Author: chenyuting
 * @DateTime: 2017/5/31  19:02
 */
public interface WhereAboutGoIntendService {
    /**
     * 根据id查询拟毕业去向代码
     * @param id
     * @return
     * @throws SSException
     */
    public WhereAboutGoIntendCode queryById(int id) throws SSException;

    /**
     * 根据code查询拟毕业去向代码
     * @param whereAboutGoId
     * @return
     * @throws SSException
     */
    public WhereAboutGoIntendCode queryByCode(int whereAboutGoId) throws SSException;

    /**
     * 查询所有拟毕业去向代码
     * @return
     * @throws SSException
     */
    public List<WhereAboutGoIntendCode> listAll() throws SSException;
}
