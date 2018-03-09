package com.pandawork.nenu.oa.service.data;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.data.WhereAboutGoCode;

import java.util.List;

/**
 * Description:毕业去向service
 * Author:luowanli
 * Date:2016/7/23
 * Time:20:10
 */

public interface WhereAboutGoService {

    /**
     * 根据id查询毕业去向代码
     * @param id
     * @return
     * @throws SSException
     */
    public WhereAboutGoCode queryById(int id) throws SSException;

    /**
     * 根据code查询毕业去向代码
     * @param whereAboutGoId
     * @return
     * @throws SSException
     */
    public WhereAboutGoCode queryByCode(int whereAboutGoId) throws SSException;

    /**
     * 查询所有毕业去向代码
     * @return
     * @throws SSException
     */
    public List<WhereAboutGoCode> listAll() throws SSException;
}
