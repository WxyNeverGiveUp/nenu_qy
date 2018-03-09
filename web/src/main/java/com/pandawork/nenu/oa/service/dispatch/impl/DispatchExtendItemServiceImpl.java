package com.pandawork.nenu.oa.service.dispatch.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchExtendItem;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.dispatch.DispatchExtendItemMapper;
import com.pandawork.nenu.oa.service.dispatch.DispatchExtendItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理员派遣扩展项Service实现
 * DispatchExtendItemService实现
 * Created by ZhangBiLai on 2017/7/19.
 */
@Service("DispatchExtendItemService")
public class DispatchExtendItemServiceImpl implements DispatchExtendItemService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    DispatchExtendItemMapper dispatchExtendItemMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void newOrUpdate(DispatchExtendItem dispatchExtendItem) throws SSException {
        try{
            if(Assert.isNull(dispatchExtendItem.getId())){
                commonDao.insert(dispatchExtendItem);
            } else {
                commonDao.update(dispatchExtendItem);
            }
        } catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchExtendItemUpdateFailed, e);
        }
    }

    @Override
    public DispatchExtendItem queryByDispatchId(int dispatchId) throws SSException {
        if(Assert.lessOrEqualZero(dispatchId)) {
            throw SSException.get(OaException.DispatchIdIllegal);
        }
        try {
            return dispatchExtendItemMapper.queryByDispatchId(dispatchId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchIdQueryFailed, e);
        }
    }

    @Override
    public DispatchExtendItem queryDetailByDispatchId(int dispatchId) throws SSException {
        if(Assert.lessOrEqualZero(dispatchId)) {
            throw SSException.get(OaException.DispatchIdIllegal);
        }
        try {
            DispatchExtendItem dispatchExtendItem = dispatchExtendItemMapper.queryByDispatchId(dispatchId);
            if(Assert.isNull(dispatchExtendItem)){
                return null;
            }
            if(dispatchExtendItem.getEnterBeijing() != null && dispatchExtendItem.getEnterBeijing().equals("1")){
                dispatchExtendItem.setEnterBeijing("有进京函");
            } else if(dispatchExtendItem.getEnterBeijing() == null){
                dispatchExtendItem.setEnterBeijing("");
            } else if(dispatchExtendItem.getEnterBeijing().equals("0")){
                dispatchExtendItem.setEnterBeijing("无进京函");
            }

            if(dispatchExtendItem.getEnterTianjin() != null && dispatchExtendItem.getEnterTianjin().equals("1")){
                dispatchExtendItem.setEnterTianjin("有进津函");
            } else if(dispatchExtendItem.getEnterTianjin() == null){
                dispatchExtendItem.setEnterTianjin("");
            } else if( dispatchExtendItem.getEnterTianjin().equals("0")){
                dispatchExtendItem.setEnterTianjin("无进津函");
            }

            if(dispatchExtendItem.getEnterShanghai() != null && dispatchExtendItem.getEnterShanghai().equals("1")){
                dispatchExtendItem.setEnterShanghai("有进沪函");
            } else if(dispatchExtendItem.getEnterShanghai() == null){
                dispatchExtendItem.setEnterShanghai("");
            } else if(dispatchExtendItem.getEnterShanghai().equals("0")){
                dispatchExtendItem.setEnterShanghai("无进沪函");
            }

            if(dispatchExtendItem.getOrientationCancelContract() != null && dispatchExtendItem.getOrientationCancelContract().equals("1")){
                dispatchExtendItem.setOrientationCancelContract("有定向解约材料");
            } else if(dispatchExtendItem.getOrientationCancelContract() == null){
                dispatchExtendItem.setOrientationCancelContract("");
            } else if(dispatchExtendItem.getOrientationCancelContract().equals("0")){
                dispatchExtendItem.setOrientationCancelContract("无定向解约材料");
            }

            if(dispatchExtendItem.getFreeNormalCancelContract() != null && dispatchExtendItem.getFreeNormalCancelContract().equals("1")){
                dispatchExtendItem.setFreeNormalCancelContract("有免师解约材料");
            } else if(dispatchExtendItem.getFreeNormalCancelContract() == null){
                dispatchExtendItem.setFreeNormalCancelContract("");
            } else if(dispatchExtendItem.getFreeNormalCancelContract().equals("0")){
                dispatchExtendItem.setFreeNormalCancelContract("无免师解约材料");
            }

            if(dispatchExtendItem.getFreeNormalTransProvincial()!= null && dispatchExtendItem.getFreeNormalTransProvincial().equals("1")){
                dispatchExtendItem.setFreeNormalTransProvincial("有免师跨省材料");
            } else if(dispatchExtendItem.getFreeNormalTransProvincial() == null){
                dispatchExtendItem.setFreeNormalTransProvincial("");
            } else if(dispatchExtendItem.getFreeNormalTransProvincial().equals("0")){
                dispatchExtendItem.setFreeNormalTransProvincial("无免师跨省材料");
            }

            return dispatchExtendItem;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchIdQueryFailed, e);
        }
    }

    /**
     * 检查实体及必要字段是否为空
     *
     * @param dispatchExtendItem
     * @return
     * @throws SSException
     */
    private boolean checkBeforeSave(DispatchExtendItem dispatchExtendItem) throws SSException {
        if (Assert.isNull(dispatchExtendItem)) {
            return false;
        }
        if (Assert.isNull(dispatchExtendItem.getDispatchId()) || Assert.lessOrEqualZero(dispatchExtendItem.getDispatchId())){
            throw SSException.get(OaException.DispatchIdIllegal);
        }
        return true;
    }
}
