package com.pandawork.nenu.oa.service.dispatch.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.dto.dispatch.*;
import com.pandawork.nenu.oa.common.entity.data.CollegeCode;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchAdminRemark;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchExtendItem;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.dispatch.DispatchExtendItemMapper;
import com.pandawork.nenu.oa.mapper.dispatch.DispatchInfoMapper;
import com.pandawork.nenu.oa.mapper.general.CollegeMapper;
import com.pandawork.nenu.oa.mapper.general.MaterialMapper;
import com.pandawork.nenu.oa.service.dispatch.DispatchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by qiuxiao on 2016/7/13.
 */
@Service("dispatchInfoService")
public class DispatchInfoServiceImpl implements DispatchInfoService {
    @Autowired
    CommonDao commonDao;

    @Autowired
    DispatchInfoMapper dispatchInfoMapper;

    @Autowired
    MaterialMapper matrialMapper;

    @Autowired
    DispatchExtendItemMapper dispatchExtendItemMapper;

    @Autowired
    CollegeMapper collegeMapper;

    /**
     * 修改派遣信息
     *
     * @param dispatchInfo
     * @throws SSException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateDispatchInfo(DispatchInfo dispatchInfo) throws SSException {
        if (dispatchInfo.getOrganizationCode().equals("") || dispatchInfo.getOrganizationCode() == null){
            dispatchInfo.setOrganizationCode("0");
        }
        if(dispatchInfo.getReportModeId() == 6) {
            dispatchInfo.setReportCompany("");
            dispatchInfo.setReportCompanyAddress(0);
        }
        if (Assert.isNotNull(dispatchInfo.getId()) && !Assert.lessOrEqualZero(dispatchInfo.getId())) {
            try {
                commonDao.update(dispatchInfo);
            } catch (Exception e) {
                LogClerk.errLog.error(e);
                throw SSException.get(OaException.DispatchUpdateFailed, e);
            }
        } else {
            throw SSException.get(OaException.DispatchQueryModelAtttributeError, new Exception());
        }
    }

    /**
     * 待条件查询返回列表
     *
     * @param dispatchQueryDto
     * @return
     * @throws SSException
     */
    //zy 改
    @Override
    public List<DispatchQueryDto> queryDispatchList(DispatchQueryDto dispatchQueryDto) throws SSException {
        if (Assert.isNotNull(dispatchQueryDto.getUserId()) && !Assert.lessOrEqualZero(dispatchQueryDto.getUserId())) {
            try {
                if (dispatchInfoMapper.getUserRole(dispatchQueryDto.getUserId()) != null && hasRole(dispatchQueryDto.getUserId(), "college-secretary")) {    //zy 学院副书记
                    return dispatchInfoMapper.queryDepartmentTypeList(dispatchQueryDto);//返回学院级别的派遣信息列表
                } else if (dispatchInfoMapper.getUserRole(dispatchQueryDto.getUserId()) != null && hasRole(dispatchQueryDto.getUserId(), "counsellor")) {   //zy 辅导员
                    return dispatchInfoMapper.queryDepartmentTypeList(dispatchQueryDto);//返回学院辅导员级别的列表
                } else if (dispatchInfoMapper.getUserRole(dispatchQueryDto.getUserId()) != null && !hasRole(dispatchQueryDto.getUserId(), "student")) {  //zy  学生？！
                    return dispatchInfoMapper.querySchoolTypeList(dispatchQueryDto);//学校级别的派遣信息列表
                } else {
                    throw SSException.get(OaException.DispatchQueryFaild, new Exception());
                }
            } catch (Exception e) {
                LogClerk.errLog.error(e);
                throw SSException.get(OaException.DispatchQueryFaild, e);
            }
        } else {
            throw SSException.get(OaException.DispatchQueryFaild, new Exception());
        }
    }

    /**
     * 获取指定id的派遣信息详情（已测）
     *
     * @param id
     * @return
     * @throws SSException
     */
    @Override
    public DispatchQueryDto getDetail(Integer id) throws SSException {
        if (Assert.isNotNull(id) && !Assert.lessOrEqualZero(id)) {
            try {
                return dispatchInfoMapper.getDetail(id);
            } catch (Exception e) {
                LogClerk.errLog.error(e);
                throw SSException.get(OaException.DispatchQueryFaild, e);
            }
        } else {
            throw SSException.get(OaException.DispatchQueryModelAtttributeError, new Exception());
        }
    }

    /**
     * 返回管理员对应的所有的学院或者专业信息
     *
     * @param userId
     * @return
     * @throws SSException
     */
    @Override
    public List<EnumDto> getDepartmentsOrMajors(Integer userId) throws SSException {
        Map<String, String> map = new HashMap<>();
        List<EnumDto> enumDtos = new ArrayList<>();
        try {
            if (hasRole(userId, "counsellor")) {
                enumDtos = dispatchInfoMapper.getAllMajors(userId);
            } else if (hasRole(userId, "college-secretary")) {
                enumDtos = dispatchInfoMapper.getAllDepartments();
            } else if (!hasRole(userId, "student")) {
                List<CollegeCode> collegeCodes = collegeMapper.listAll();

                for (int i = 0; i < collegeCodes.size(); i++) {
                    CollegeCode collegeCode = collegeCodes.get(i);
                    EnumDto tmp = new EnumDto();
                    tmp.setO1(collegeCode.getCollegeId());
                    tmp.setO2(collegeCode.getCollege());
                    enumDtos.add(tmp);


                }
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchQueryFaild, e);
        }
        return enumDtos;
    }

    /**
     * 是否有指定的角色
     *
     * @param role
     * @return
     * @throws SSException
     */
    @Override
    public Boolean hasRole(Integer userId, String role) throws SSException {
        List<String> roles = new ArrayList<>();
        Boolean flag = false;
        try {
            roles = dispatchInfoMapper.getUserRole(userId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchQueryFaild, e);
        }
        for (String s : roles) {
            if (s.equals(role.trim())) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    public Integer getCount(DispatchQueryDto dispatchQueryDto) throws SSException {
        List<DispatchQueryDto> list = new ArrayList<>();
        if (Assert.isNotNull(dispatchQueryDto.getUserId()) && !Assert.lessOrEqualZero(dispatchQueryDto.getUserId())) {
            try {
                if (dispatchInfoMapper.getUserRole(dispatchQueryDto.getUserId()) != null && hasRole(dispatchQueryDto.getUserId(), "college-secretary")) {
                    return dispatchInfoMapper.getDepartmentCount(dispatchQueryDto);

                } else if (dispatchInfoMapper.getUserRole(dispatchQueryDto.getUserId()) != null && hasRole(dispatchQueryDto.getUserId(), "counsellor")) {
                    return dispatchInfoMapper.getDepartmentCount(dispatchQueryDto);

                } else if (dispatchInfoMapper.getUserRole(dispatchQueryDto.getUserId()) != null && !hasRole(dispatchQueryDto.getUserId(), "student")) {
                    return dispatchInfoMapper.getSchoolCount(dispatchQueryDto);

                } else {
                    throw SSException.get(OaException.DispatchQueryFaild, new Exception());

                }
            } catch (Exception e) {
                LogClerk.errLog.error(e);
                throw SSException.get(OaException.DispatchQueryFaild, e);
            }
        } else {
            throw SSException.get(OaException.DispatchQueryFaild, new Exception());
        }
    }

    /**
     * 查询管理员对应的派遣信息列表
     *
     * @param dispatchQueryDto
     * @param page
     * @return
     * @throws SSException
     */
    //zy 改
    public List<Integer> listIdByCondition(Integer userId, DispatchQueryDto dispatchQueryDto, Pagination page) throws SSException {
        List<Integer> idList = null;
        Integer curPage = null;
        Integer pageSize = null;
        if (!Assert.isNull(page)) {
            curPage = page.getCurrentFristPosition();
            pageSize = page.getPageSize();
        }
        dispatchQueryDto.setpCurrent(curPage);
        dispatchQueryDto.setpSize(pageSize);
        try {
            if (hasRole(userId, "counsellor")) {   //zy 辅导员
                dispatchQueryDto.setUserId(userId);
                idList = dispatchInfoMapper.getCounsellorIdList(dispatchQueryDto);
            } else if (hasRole(userId, "college-secretary")) {  //zy 学院副书记
                idList = dispatchInfoMapper.getDepartmentIdList(dispatchQueryDto);
            } else if (!hasRole(userId, "student")) {
                idList = dispatchInfoMapper.getSchoolIdList(dispatchQueryDto);
            } else {
                throw SSException.get(OaException.DispatchQueryFaild, new Exception());
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchQueryFaild, e);
        }
        return idList;
    }


    /**
     * @param dispatchQueryDto
     * @return
     * @throws Exception
     */
    public Integer getAdminDispatchersCount(Integer userId, DispatchQueryDto dispatchQueryDto) throws SSException {
        Integer count = null;
        try {
            if (hasRole(userId, "counsellor")) {
                dispatchQueryDto.setUserId(userId);
                count = dispatchInfoMapper.getCounsellorCount(dispatchQueryDto);
            } else if (hasRole(userId, "college-secretary")) {
                count = dispatchInfoMapper.getDepartmentCount(dispatchQueryDto);
            } else if (!hasRole(userId, "student")) {
                count = dispatchInfoMapper.getSchoolCount(dispatchQueryDto);
            } else {
                throw SSException.get(OaException.DispatchQueryFaild, new Exception());
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchQueryFaild, e);
        }
        return count;
    }

    /**
     * 根据学生stu_status_info_id查询派遣信息id
     *
     * @param stuId
     * @return
     */
    public Integer getIdByStuId(Integer stuId) throws SSException {
        if (Assert.isNotNull(stuId) && !Assert.lessOrEqualZero(stuId)) {
            return dispatchInfoMapper.getIdByStuId(stuId);
        } else {
            throw SSException.get(OaException.DispatchQueryFaild, new Exception());
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateById(DispatchInfo dispatchInfo) throws SSException {
        try {
            if (Assert.isNull(dispatchInfo.getId()) || Assert.lessOrEqualZero(dispatchInfo.getId())) {
                throw SSException.get(OaException.IdIllegal);
            }
            commonDao.update(dispatchInfo);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchUpdateFailed, e);
        }
    }

    /**
     * 根据派遣信息id获取学生信息id
     *
     * @param id
     * @return
     */
    public int getStatusInfoIdById(int id) throws SSException {
        Integer statusInfoIdById = null;
        try {
            statusInfoIdById = dispatchInfoMapper.getStatusInfoIdById(id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchQueryFaild, e);
        }
        if (statusInfoIdById != null) {
            return statusInfoIdById;
        } else {
            NullPointerException e = new NullPointerException();
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchQueryFaild, e);
        }
    }

    @Override
    public int countMinorityByCondition(MinorityQueryDto minorityQueryDto) throws SSException {
        try {
            return dispatchInfoMapper.countMinorityByCondition(minorityQueryDto);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchQueryFaild, e);
        }
    }

    @Override
    public List<DispatchListDto> listMinorityByCondition(MinorityQueryDto minorityQueryDto, Pagination page) throws SSException {
        List<DispatchListDto> dispatchInfoList = Collections.emptyList();
        int curPage = 0, pageSize = 15;

        if (!Assert.isNull(page)) {
            curPage = page.getCurrentFristPosition();
            pageSize = page.getPageSize();
        }
        minorityQueryDto.setCurPage(curPage);
        minorityQueryDto.setPageSize(pageSize);

        try {
            dispatchInfoList = dispatchInfoMapper.listMinorityByCondition(minorityQueryDto);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchQueryFaild, e);
        }

        return dispatchInfoList;
    }

    @Override
    public List<Integer> listMinorityIdByCondition(MinorityQueryDto minorityQueryDto, Pagination page) throws SSException {
        return null;
    }

    @Override
    public List<DispatchExportDto> listAllExportInfo(int grade) throws SSException {
        try {
            List<DispatchExportDto> list = dispatchInfoMapper.listAllExportInfo(grade);
            return list;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchQueryFaild, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateCurrentAgreement(int statusInfoId) throws SSException {
        try {
            dispatchInfoMapper.updateCurrentAgreement(statusInfoId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.updateCurrentAgreement, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void newAgreement(DispatchInfo dispatchInfo) throws SSException {
        try {
            dispatchInfoMapper.newAgreement(dispatchInfo);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.newAgreement, e);
        }
    }

    @Override
    public List<DispatchInfo> queryDispatchByStuStatusInfoId(int stuStatusInfoId) throws SSException{
        List<DispatchInfo> dispatchInfoList = Collections.emptyList();
        try{
            if (Assert.lessOrEqualZero(stuStatusInfoId)){
                throw SSException.get(OaException.IdIllegal);
            }
            dispatchInfoList = dispatchInfoMapper.queryDispatchByStuStatusInfoId(stuStatusInfoId);
            return dispatchInfoList;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchQueryFaild, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void deleteDispatchByStuStatusInfoId(int stuStatusInfoId) throws SSException{
        List<DispatchInfo> dispatchInfoList = Collections.emptyList();
        try{
            if (Assert.lessOrEqualZero(stuStatusInfoId)){
                throw SSException.get(OaException.IdIllegal);
            }
            dispatchInfoList = this.queryDispatchByStuStatusInfoId(stuStatusInfoId);
            commonDao.deleteAll(dispatchInfoList);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DeleteDispatchInfoFailed, e);
        }
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public boolean batchAudit(List<Integer> id ,Date date, String realName) throws SSException{
        try{
            for(int i = 0; i < id.size(); i++){
                DispatchInfo dispatchInfo = dispatchInfoMapper.queryDispatchInfoByStuStatusInfoId(id.get(i));
                id.set(i,dispatchInfoMapper.getIdByStuId(id.get(i)));
                if(dispatchInfo.getCounsellorCheckResult() != 2){
                    return false;
                }
            }

            dispatchInfoMapper.batchAudit(id,date,realName);
            return true;
        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchInfoBatchAuditFailed,e);
        }
    }
    @Override
    public void deleteById(int dispatchInfoId) throws SSException {
        try{
            if(Assert.lessOrEqualZero(dispatchInfoId)){
                throw SSException.get(OaException.IdIllegal);
            }
            dispatchInfoMapper.deleteById(dispatchInfoId);
            matrialMapper.updateDispatchByDispatchInfoId(dispatchInfoId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DeleteOriginDispatchInfoException, e);
        }
    }

    @Override
    public List<ExportAllDto> exportAll(DispatchQueryDto dispatchQueryDto) throws SSException {
        try {
            List<ExportAllDto> list = dispatchInfoMapper.exportAll(dispatchQueryDto);
            for(int i = 0;i < list.size();i ++){
                ExportAllDto exportAllDto = list.get(i);
                List<DispatchAdminRemark> remarkList = new ArrayList<>(10);
                List<DispatchAdminRemark> remarkList1 = dispatchInfoMapper.queryAdminRemark(exportAllDto.getDispatchId());
                remarkList.addAll(remarkList1);
                if(remarkList1.size() < 10){
                    for(int k = remarkList1.size(); k < 10;k ++){
                        remarkList.add(null);
                    }
                }
                if(remarkList.get(0) == null){
                    exportAllDto.setAdminRemark1("");
                } else{
                    exportAllDto.setAdminRemark1(remarkList.get(0).getRemark());
                }
                if(remarkList.get(1) == null){
                    exportAllDto.setAdminRemark2("");
                } else{
                    exportAllDto.setAdminRemark2(remarkList.get(1).getRemark());
                }
                if(remarkList.get(2) == null){
                    exportAllDto.setAdminRemark3("");
                } else{
                    exportAllDto.setAdminRemark3(remarkList.get(2).getRemark());
                }
                if(remarkList.get(3) == null){
                    exportAllDto.setAdminRemark4("");
                } else{
                    exportAllDto.setAdminRemark4(remarkList.get(3).getRemark());
                }
                if(remarkList.get(4) == null){
                    exportAllDto.setAdminRemark5("");
                } else{
                    exportAllDto.setAdminRemark5(remarkList.get(4).getRemark());
                }
                if(remarkList.get(5) == null){
                    exportAllDto.setAdminRemark6("");
                } else{
                    exportAllDto.setAdminRemark6(remarkList.get(5).getRemark());
                }
                if(remarkList.get(6) == null){
                    exportAllDto.setAdminRemark7("");
                } else{
                    exportAllDto.setAdminRemark7(remarkList.get(6).getRemark());
                }
                if(remarkList.get(7) == null){
                    exportAllDto.setAdminRemark8("");
                } else{
                    exportAllDto.setAdminRemark8(remarkList.get(7).getRemark());
                }if(remarkList.get(8) == null){
                    exportAllDto.setAdminRemark9("");
                } else{
                    exportAllDto.setAdminRemark9(remarkList.get(8).getRemark());
                }
                if(remarkList.get(9) == null){
                    exportAllDto.setAdminRemark10("");
                } else{
                    exportAllDto.setAdminRemark10(remarkList.get(9).getRemark());
                }
                DispatchExtendItem dispatchExtendItem = dispatchExtendItemMapper.queryByDispatchId(Integer.parseInt(exportAllDto.getDispatchId()));
                if(Assert.isNull(dispatchExtendItem)){
                    exportAllDto.setEnterBeijing("");
                    exportAllDto.setEnterTianjin("");
                    exportAllDto.setEnterShanghai("");
                    exportAllDto.setOrientationCancelContract("");
                    exportAllDto.setFreeNormalCancelContract("");
                    exportAllDto.setFreeNormalTransProvincial("");
                }else {
                    if (dispatchExtendItem.getEnterBeijing() != null && dispatchExtendItem.getEnterBeijing().equals("1")) {
                        exportAllDto.setEnterBeijing("有进京函");
                    } else if (dispatchExtendItem.getEnterBeijing() == null) {
                        exportAllDto.setEnterBeijing("");
                    } else if (dispatchExtendItem.getEnterBeijing().equals("0")) {
                        exportAllDto.setEnterBeijing("无进京函");
                    }

                    if (dispatchExtendItem.getEnterTianjin() != null && dispatchExtendItem.getEnterTianjin().equals("1")) {
                        exportAllDto.setEnterTianjin("有进津函");
                    } else if (dispatchExtendItem.getEnterTianjin() == null) {
                        exportAllDto.setEnterTianjin("");
                    } else if (dispatchExtendItem.getEnterTianjin().equals("0")) {
                        exportAllDto.setEnterTianjin("无进津函");
                    }

                    if (dispatchExtendItem.getEnterShanghai() != null && dispatchExtendItem.getEnterShanghai().equals("1")) {
                        exportAllDto.setEnterShanghai("有进沪函");
                    } else if (dispatchExtendItem.getEnterShanghai() == null) {
                        exportAllDto.setEnterShanghai("");
                    } else if (dispatchExtendItem.getEnterShanghai().equals("0")) {
                        exportAllDto.setEnterShanghai("无进沪函");
                    }

                    if (dispatchExtendItem.getOrientationCancelContract() != null && dispatchExtendItem.getOrientationCancelContract().equals("1")) {
                        exportAllDto.setOrientationCancelContract("有定向解约材料");
                    } else if (dispatchExtendItem.getOrientationCancelContract() == null) {
                        exportAllDto.setOrientationCancelContract("");
                    } else if (dispatchExtendItem.getOrientationCancelContract().equals("0")) {
                        exportAllDto.setOrientationCancelContract("无定向解约材料");
                    }

                    if (dispatchExtendItem.getFreeNormalCancelContract() != null && dispatchExtendItem.getFreeNormalCancelContract().equals("1")) {
                        exportAllDto.setFreeNormalCancelContract("有免师解约材料");
                    } else if (dispatchExtendItem.getFreeNormalCancelContract() == null) {
                        exportAllDto.setFreeNormalCancelContract("");
                    } else if (dispatchExtendItem.getFreeNormalCancelContract().equals("0")) {
                        exportAllDto.setFreeNormalCancelContract("无免师解约材料");
                    }

                    if (dispatchExtendItem.getFreeNormalTransProvincial() != null && dispatchExtendItem.getFreeNormalTransProvincial().equals("1")) {
                        exportAllDto.setFreeNormalTransProvincial("有免师跨省材料");
                    } else if (dispatchExtendItem.getFreeNormalTransProvincial() == null) {
                        exportAllDto.setFreeNormalTransProvincial("");
                    } else if (dispatchExtendItem.getFreeNormalTransProvincial().equals("0")) {
                        exportAllDto.setFreeNormalTransProvincial("无免师跨省材料");
                    }
                }
                list.set(i,exportAllDto);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ExportAllFailed, e);
        }
    }

    @Override
    public List<ExportAllCounsellorDto> exportAllCounsellor(DispatchQueryDto dispatchQueryDto) throws SSException {
        List<ExportAllCounsellorDto> list = null;
        try {
            list = dispatchInfoMapper.exportAllCounsellor(dispatchQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.ExportAllFailed, e);
        }
        return list;
    }
}
