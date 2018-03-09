package com.pandawork.nenu.oa.service.dispatch.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.entity.dispatch.Comments;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.dispatch.CommentsMapper;
import com.pandawork.nenu.oa.service.dispatch.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CommentsServiceImpl
 *
 * @author wlm
 * @date 2016/10/24 10:17
 */

@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    CommentsMapper commentsMapper;

    @Override
    public Comments queryById(int id) throws SSException {
        try {
            if (Assert.lessOrEqualZero(id)) {
                throw SSException.get(OaException.IdIllegal);
            }
            return commonDao.queryById(Comments.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CommentsQueryFailed, e);
        }
    }

    @Override
    public Comments queryByStatusId(int statusId) throws SSException {
        try {
            if (Assert.lessOrEqualZero(statusId)) {
                throw SSException.get(OaException.IdIllegal);
            }
            return commentsMapper.queryByStatusId(statusId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CommentsQueryFailed, e);
        }
    }

    @Override
    public Comments newComments(Comments comments) throws SSException {
        if (!this.checkBeforeSave(comments)) {
            throw SSException.get(OaException.CommentsInsertFailed);
        }
        try {
            return commonDao.insert(comments);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CommentsInsertFailed, e);
        }
    }

    @Override
    public void updateById(Comments comments) throws SSException {
        if (!Assert.isNull(comments)) {
            if (Assert.isNull(comments.getId()) || Assert.lessOrEqualZero(comments.getId())) {
                throw SSException.get(OaException.CommentsUpdateFailed);
            }
        }
        try {
            commonDao.update(comments);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.CommentsUpdateFailed, e);
        }
    }

    /**
     * 检查实体及必要字段是否为空
     *
     * @param comments
     * @return
     * @throws SSException
     */
    private boolean checkBeforeSave(Comments comments) throws SSException {
        if (Assert.isNull(comments)) {
            return false;
        }
        if (Assert.isNull(comments.getStatusInfoId()) || Assert.lessOrEqualZero(comments.getStatusInfoId())){
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        return true;
    }
}
