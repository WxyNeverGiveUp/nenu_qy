package com.pandawork.nenu.oa.service.business.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.Log;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.dto.business.ProtocolAdminListDto;
import com.pandawork.nenu.oa.common.dto.business.ProtocolExportDto;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.business.ProtocolAdminMapper;
import com.pandawork.nenu.oa.service.business.ProtocolAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Zuosy on 2017/7/21.
 */
@Service("protocolAdminService")
public class ProtocolAdminServiceImpl implements ProtocolAdminService {

    @Autowired
    ProtocolAdminMapper protocolAdminMapper;

    @Autowired
    CommonDao commonDao;


    /**
     * 添加新协议
     * @param protocol
     * @throws SSException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void newProtocol(Protocol protocol) throws SSException {
        try{
            if(Assert.isNull(protocol)){
                return ;
            }else{
                protocolAdminMapper.newProtocol(protocol);
            }
        }catch(Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.newAgreement);
        }
    }

    /**
     * 修改审核结果和审核人
     * @param protocol
     * @throws SSException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateCheckResultAndCheckOperator(Protocol protocol) throws SSException{
        try{
            if(Assert.isNull(protocol)){
                return;
            }else{
                protocolAdminMapper.updateCheckResultAndCheckOperator(protocol);
            }
        }catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UpdateAlterCheckException);
        }
    }

    /**
     *
     * 修改审核原因
     * @param protocol
     * @throws SSException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateCheckReason(Protocol protocol) throws SSException {
        try{
            if(Assert.isNull(protocol)){
                return ;
            }else{
                protocolAdminMapper.updateCheckReason(protocol);
            }
        }catch(Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.updateCurrentAgreement);
        }
    }

    /**
     * 修改领取地点和领取时间
     * 审核痛过了，告诉学生领取地点和领取时间
     * @param protocol
     * @throws SSException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateFetchPlaceAndFetchTime(Protocol protocol) throws SSException {
        try{
            if(Assert.isNull(protocol)){
                return ;
            }else{
                protocolAdminMapper.updateFetchPlaceAndFetchTime(protocol);
            }
        }catch(Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.updateCurrentAgreement);
        }
    }

    /**
     * 查询ProtocolAdminListDto 的全部内容
     * @return
     * @throws SSException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public List<ProtocolAdminListDto> queryProtocolAdminListDto() throws SSException {
        List<ProtocolAdminListDto> list = Collections.emptyList();
        try{
            list = protocolAdminMapper.queryProtocolAdminListDto();
        }catch(Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.updateCurrentAgreement);
        }
        return list;
    }

    /**
     * 根据学籍ID查看Protocol信息（按createTime降序排列）
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    @Override
    public List<Protocol> queryProtocolByStatusInfoId(int statusInfoId) throws SSException {
        List<Protocol> list = Collections.emptyList();
        try{
            if(Assert.lessOrEqualZero(statusInfoId)){
                return null;
            }else{
                list = protocolAdminMapper.queryProtocolByStatusInfoId(statusInfoId);
            }
        }catch(Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.updateCurrentAgreement);
        }
        return list;
    }

    /**
     * 根据学籍ID查找该学生最新申请协议的ID
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    @Override
    public int queryLastProtocolByStatusInfoId(int statusInfoId) throws SSException {
        int id = 0;
        try {
            if (Assert.lessOrEqualZero(statusInfoId)) {
                return 0;
            } else {
                id = protocolAdminMapper.queryLastProtocolByStatusInfoId(statusInfoId);
            }
        }catch(Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.updateCurrentAgreement);
        }
        return id;
    }

    /**
     * 根据协议ID查看协议信息
     * @param id
     * @return
     * @throws SSException
     */
    @Override
    public Protocol queryByProtocolId(int id) throws SSException {
        Protocol protocol = null;
        try{
            if(Assert.lessOrEqualZero(id)) {
                return null;
            }else {
                protocol = protocolAdminMapper.queryByProtocolId(id);
            }
        }catch(Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.updateCurrentAgreement);
        }
        return protocol;
    }

    /**
     * CountByStatusInfoId
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    @Override
    public Integer countByStatusInfoId(int statusInfoId) throws SSException {
        Integer counter = 0;
        try{
            if(Assert.lessOrEqualZero(statusInfoId)) {
                return 0;
            }else {
                counter = protocolAdminMapper.countByStatusInfoId(statusInfoId);
            }
        }catch(Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.updateCurrentAgreement);
        }
        return counter;
    }

    /**
     * 数量
     * @param stuType
     * @param sex
     * @param college
     * @param major
     * @param qualification
     * @param normalStu
     * @param originPlace
     * @param stuLength
     * @param isRegistered
     * @param trainingMode
     * @param protocolType
     * @param checkProtocolResult
     * @param beginCreateTime
     * @param endCreateTime
     * @param keyword
     * @return
     * @throws SSException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public int countByCondition(int stuType, int sex, String college, String major, String qualification,
                                int normalStu, int originPlace, double stuLength, int isRegistered, int trainingMode,
                                int protocolType, int checkProtocolResult, Date beginCreateTime, Date endCreateTime,
                                String keyword, String type) throws SSException {
        //System.out.println("major = " + major);
        int count = 0;
        String name = null;
        String stuNumber = null;
        if(keyword.equals("")) {
            keyword = null;
        }
        if (!Assert.isNull(keyword)) {
            keyword = keyword.trim();
            if (keyword.equals("")) {
                //关键字为空
                keyword = null;
            }else if (keyword.matches("^\\d{9,14}$")) {
                //关键字为学号
                stuNumber = keyword;
            }
            else if (keyword.matches("[\\u4E00-\\u9FA5]{1,20}(?:·[\\u4E00-\\u9FA5]{1,20})*")) {
                //关键字为姓名
                name = keyword;
            }
        }
        try{
            count = protocolAdminMapper.countByCondition(stuType, sex, college, major, qualification, normalStu,
                    originPlace, stuLength, isRegistered, trainingMode, protocolType, checkProtocolResult,
                    beginCreateTime, endCreateTime, name, stuNumber, type);
        }catch(Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.updateCurrentAgreement);
        }
        return count;
    }

    /**
     * 根据协议ID查询ProtocolAdminListDto
     * @param id
     * @return
     * @throws SSException
     */
    @Override
    public ProtocolAdminListDto queryProtocolAdminListDtoById(int id) throws SSException {
        ProtocolAdminListDto p = null;
        try{
            if(Assert.lessOrEqualZero(id)) {
                return null;
            }else{
                p = protocolAdminMapper.queryProtocolAdminListDtoById(id);
            }
        }catch(Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.updateCurrentAgreement);
        }
        return p;
    }

    /**
     * 根据学籍ID查询ProtocolAdminListDto
     * @param statusInfoId
     * @return
     * @throws SSException
     */
    @Override
    public List<ProtocolAdminListDto> queryProtocolAdminListDtoByStatusInfoId(int statusInfoId) throws SSException {
        List<ProtocolAdminListDto> list = Collections.emptyList();
        try{
            if(Assert.lessOrEqualZero(statusInfoId)){
                return null;
            }else{
                list = protocolAdminMapper.queryProtocolAdminListDtoByStatusInfoId(statusInfoId);
            }
        }catch(Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.updateCurrentAgreement);
        }
        return list;
    }

    @Override
    public List<ProtocolAdminListDto> queryProtocolAdminListDtoByConditions(Pagination page, int stuType, int sex, String college, String major, String qualification,
                                                                            int normalStu, int originPlace, double stuLength, int isRegistered,
                                                                            int trainingMode, int protocolType, int checkProtocolResult,
                                                                            Date beginCreateTime, Date endCreateTime, String keyword, String type) throws SSException {
        List<ProtocolAdminListDto> protocolAdminListDtoList = Collections.emptyList();
        int curPage = 0, pageSize = 15;
        String name = null;
        String stuNumber = null;
        if(keyword.equals("")) {
            keyword = null;
        }
        if (!Assert.isNull(keyword)) {
            keyword = keyword.trim();
            if (keyword.equals("")) {
                //关键字为空
                keyword = null;
            } else if (keyword.matches("^\\d{9,14}$")) {
                //关键字为学号
                stuNumber = keyword;
            } else if (keyword.matches("[\\u4E00-\\u9FA5]{1,20}(?:·[\\u4E00-\\u9FA5]{1,20})*")) {
                //关键字为姓名
                name = keyword;
            }
        }
        if (!Assert.isNull(page)) {
            curPage = page.getCurrentFristPosition();
            pageSize = page.getPageSize();
        }
        try {
            protocolAdminListDtoList = protocolAdminMapper.queryProtocolAdminListDtoByConditions(stuType, sex, college, major, qualification, normalStu,
                    originPlace, stuLength, isRegistered, trainingMode, protocolType, checkProtocolResult, beginCreateTime, endCreateTime, name, stuNumber,
                    curPage, pageSize, type);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return protocolAdminListDtoList;
    }

    /**
     * update Protocol information by id
     * @param protocol
     * @throws SSException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateById(Protocol protocol) throws SSException{
        try{
            if(Assert.isNull(protocol.getId()) && Assert.lessOrEqualZero(protocol.getId())) {
                throw SSException.get(OaException.IdIllegal);
            }
            commonDao.update(protocol);
        }catch(Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoUpdateFailed, e);
        }
    }


    /**
     * 协议编号在数据库中有没有
     * @param agreementNumber
     * @return
     * @throws SSException
     */
    @Override
    public boolean queryAgreementNumberExist(String agreementNumber) throws SSException{
        if(Assert.isNull(agreementNumber)){
            throw SSException.get(OaException.updateCurrentAgreement);
        }
        boolean exists = false;
        int counter = 0;
        try{
            counter = protocolAdminMapper.queryAgreementNumberExist(agreementNumber);
            if (counter == 0){
                exists = false;
            }else{
                exists = true;
            }
        }catch(Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoUpdateFailed, e);
        }
        return exists;
    }

    /**
     * 根据业务预约的类型返回 查找出来的列表
     * type : 1.申领新协议
     *        2.毕业去向变更领协议
     *        3.免费师范生业务
     *        4.定向、委培生业务
     *        5.博士领协议业务
     * @param type
     * @return
     * @throws SSException
     */
    @Override
    public List<ProtocolExportDto> exportByType(Integer type) throws SSException{
        List<ProtocolExportDto> list = Collections.emptyList();
        try {
            //如果tpye <=0 或 type > 5 那么就抛出异常
            if (Assert.lessOrEqualZero(type) || type > 5) {
                throw SSException.get(OaException.ProtocolTypeError);
            } else {
                //就调用mapper方法
                list = protocolAdminMapper.queryProtocolByType(type);
            }
        }catch(Exception e) {
            e.printStackTrace();
            throw SSException.get(OaException.ProtocolTypeException, e);
        }
        return list;
    }
}
