package com.pandawork.nenu.oa.service.general;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.entity.data.PlaceCode;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * PlaceService
 * 地区service
 *
 * @author wlm
 * @date 2016/7/27 17:22
 */
public interface PlaceService {

    /**
     * 根据id查询学历
     *
     * @param id
     * @return
     * @throws SSException
     */
    public PlaceCode queryById(int id) throws SSException;

    /**
     * 根据code查询学历
     *
     * @param code
     * @return
     * @throws SSException
     */
    public PlaceCode queryByCode(String code) throws SSException;

    /**
     * 查询所有学历
     *
     * @return
     * @throws SSException
     */
    public List<PlaceCode> listAll() throws SSException;

//    public void updateById(PlaceCode placeCode) throws SSException;
}
