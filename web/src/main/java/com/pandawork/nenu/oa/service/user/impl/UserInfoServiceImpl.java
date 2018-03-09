package com.pandawork.nenu.oa.service.user.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.dto.user.UserInfoDto;
import com.pandawork.nenu.oa.common.entity.user.User;
import com.pandawork.nenu.oa.common.entity.user.UserInfo;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.user.UserInfoMapper;
import com.pandawork.nenu.oa.service.user.UserInfoService;
import com.pandawork.nenu.oa.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * UserInfoServiceImpl
 * 用户信息Service实现
 *
 * @author wlm
 * @date 2016/9/22 16:10
 */

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserService userService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public UserInfo newUserInfo(UserInfo userInfo) throws SSException {
        if (!this.checkBeforeSave(userInfo)) {
            throw SSException.get(OaException.UserInfoInsertFailed);
        }
        try {
            return commonDao.insert(userInfo);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UserInfoInsertFailed,e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateById(UserInfo userInfo) throws SSException {
        if (!this.checkBeforeSave(userInfo)) {
            throw SSException.get(OaException.UserInfoInsertFailed);
        }
        try {
            if (Assert.lessOrEqualZero(userInfo.getId())) {
                throw SSException.get(OaException.IdIllegal);
            }
            commonDao.update(userInfo);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UserInfoUpdateFailed,e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void delById(int id) throws SSException {
        try {
            if (Assert.lessOrEqualZero(id)) {
                throw SSException.get(OaException.IdIllegal);
            }
            commonDao.deleteById(UserInfo.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UserInfoDeleteFailed,e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void delByUserId(int userId) throws SSException {
        try {
            if (Assert.lessOrEqualZero(userId)) {
                throw SSException.get(OaException.IdIllegal);
            }
            userInfoMapper.delByUserId(userId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UserInfoDeleteFailed,e);
        }
    }

    @Override
    public List<String> listCollegesByUserName(String username) throws SSException {
        try {
            User user = userService.queryByUsername(username);
            HashSet<String> hashSet = new HashSet<>(userInfoMapper.listCollegesById(user.getId()));
            List<String> collegeList = new ArrayList<>();
            collegeList.addAll(hashSet);
            return collegeList;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UserInfoQueryFailed,e);
        }
    }

    @Override
    public List<String> listMajorsByUserName(String username) throws SSException {
        try {
            User user = userService.queryByUsername(username);
            HashSet<String> hashSet = new HashSet<>(userInfoMapper.listMajorsById(user.getId()));
            List<String> majorList = new ArrayList<>();
            majorList.addAll(hashSet);
            return majorList;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UserInfoQueryFailed,e);
        }
    }

    @Override
    public List<UserInfoDto> listMajorsAndQualificationByUserName(String username) throws SSException {
        try {
            User user = userService.queryByUsername(username);
            List<UserInfoDto> userInfoDtoList = userInfoMapper.listMajorsAndQualificationById(user.getId());
            return userInfoDtoList;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UserInfoQueryFailed,e);
        }
    }

    @Override
    public List<UserInfoDto> listMajorsAndQualificationByCollege(String username) throws SSException {
        try{
            User user = userService.queryByUsername(username);
            List<UserInfoDto> userInfoDtoList = userInfoMapper.listMajorsAndQualificationByCollege(user.getId());
            return userInfoDtoList;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            e.printStackTrace();
            throw SSException.get(OaException.UserInfoQueryFailed,e);
        }
    }

    /**
     * 检查必要字段
     *
     * @param userInfo
     * @return
     * @throws SSException
     */
    private boolean checkBeforeSave(UserInfo userInfo) throws SSException {
        if (Assert.isNull(userInfo)) {
            return false;
        }
        if (Assert.lessOrEqualZero(userInfo.getUserId())) {
            return false;
        }
        if (Assert.isNull(userInfo.getCollegeCode())) {
            return false;
        }
        return true;
    }
}
