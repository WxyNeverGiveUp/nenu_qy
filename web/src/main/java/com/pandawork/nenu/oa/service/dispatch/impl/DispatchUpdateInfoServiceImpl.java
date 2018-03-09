package com.pandawork.nenu.oa.service.dispatch.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchQueryDto;
import com.pandawork.nenu.oa.common.dto.dispatch.DispatchUpdateDto;
import com.pandawork.nenu.oa.common.entity.data.PlaceCode;
import com.pandawork.nenu.oa.common.entity.data.WhereAboutGoCode;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;
import com.pandawork.nenu.oa.common.entity.dispatch.DispatchUpdateInfo;
import com.pandawork.nenu.oa.common.enums.dispatch.DispatchUpdateInfoTypeEnums;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.DateUtils;
import com.pandawork.nenu.oa.mapper.dispatch.DispatchUpdateInfoMapper;
import com.pandawork.nenu.oa.service.data.JobCodeService;
import com.pandawork.nenu.oa.service.data.ReportCodeService;
import com.pandawork.nenu.oa.service.data.WhereAboutGoService;
import com.pandawork.nenu.oa.service.dispatch.DispatchInfoService;
import com.pandawork.nenu.oa.service.dispatch.DispatchUpdateInfoService;
import com.pandawork.nenu.oa.service.general.CompanyPropertyService;
import com.pandawork.nenu.oa.service.general.CompanyTradeService;
import com.pandawork.nenu.oa.service.general.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DispatchUpdateInfoServiceImpl
 *
 * 派遣修改信息Service实现
 * Created by ZhangBiLai on 2017/9/26.
 */
@Service ("DispatchUpdateInfoService")
public class DispatchUpdateInfoServiceImpl implements DispatchUpdateInfoService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    DispatchUpdateInfoMapper dispatchUpdateInfoMapper;

    @Autowired
    DispatchInfoService dispatchInfoService;

    @Autowired
    WhereAboutGoService whereAboutGoService;

    @Autowired
    PlaceService placeService;

    @Autowired
    CompanyPropertyService companyPropertyService;

    @Autowired
    CompanyTradeService companyTradeService;

    @Autowired
    JobCodeService jobCodeService;

    @Autowired
    ReportCodeService reportCodeService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public DispatchUpdateInfo newDispatchUpdateInfo(DispatchUpdateInfo dispatchUpdateInfo) throws SSException {
        try {
            if (!checkBeforeSave(dispatchUpdateInfo)) {
                return null;
            }
            return commonDao.insert(dispatchUpdateInfo);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchUpdateInfoInsertFailed, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateById(DispatchUpdateInfo dispatchUpdateInfo) throws SSException {
        try {
            if (dispatchUpdateInfo.getBeforeUpdate().equals(dispatchUpdateInfo.getAfterUpdate())) {
                throw SSException.get(OaException.DispatchBeforeAfterEqual);
            }
            commonDao.update(dispatchUpdateInfo);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchUpdateInfoUpdateFailed, e);
        }
    }

    @Override
    public DispatchUpdateInfo queryById(int id) throws SSException {
        if (Assert.lessOrEqualZero(id)) {
            throw SSException.get(OaException.IdIllegal);
        }
        try {
            return commonDao.queryById(DispatchUpdateInfo.class,id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchUpdateInfoQueryFailed, e);
        }
    }

    @Override
    public List<DispatchUpdateInfo> listLatestByDispatchInfoId(int dispatchInfoId, int isStudent) throws SSException {
        if (Assert.lessOrEqualZero(dispatchInfoId)) {
            throw SSException.get(OaException.DispatchInfoIdIllegal);
        }
        List<DispatchUpdateInfo> dispatchUpdateInfoList = Collections.emptyList();
        try {
            dispatchUpdateInfoList = dispatchUpdateInfoMapper.listLatestByDispatchInfoId(dispatchInfoId,isStudent);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchUpdateInfoQueryFailed, e);
        }
        return dispatchUpdateInfoList;
    }

    @Override
    public List<DispatchUpdateInfo> listByDispatchInfoId(int dispatchInfoId) throws SSException {
        if (Assert.lessOrEqualZero(dispatchInfoId)) {
            throw SSException.get(OaException.DispatchInfoIdIllegal);
        }
        List<DispatchUpdateInfo> dispatchUpdateInfoList = Collections.emptyList();
        try {
            dispatchUpdateInfoList = dispatchUpdateInfoMapper.listByDispatchInfoId(dispatchInfoId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DispatchUpdateInfoQueryFailed, e);
        }
        return dispatchUpdateInfoList;
    }

    @Override
    public List<DispatchUpdateInfo> listByDispatchInfoIdAndType(int dispatchInfoId, int type) throws SSException {
        if(Assert.lessOrEqualZero(dispatchInfoId)) {
            throw SSException.get(OaException.DispatchInfoIdIllegal);
        }
        if(Assert.isNull(DispatchUpdateInfoTypeEnums.valueOf(type))) {
            throw SSException.get(OaException.DispatchUpdateTypeIllegal);
        }
        List<DispatchUpdateInfo> dispatchUpdateInfoList = Collections.emptyList();
        try {
            dispatchUpdateInfoList = dispatchUpdateInfoMapper.listByDispatchInfoIdAndType(dispatchInfoId,type);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
        return dispatchUpdateInfoList;
    }

    @Override
    public List<DispatchUpdateDto> listDtoByDispatchInfoId(int dispatchInfoId) throws SSException {
        if(Assert.lessOrEqualZero(dispatchInfoId)) {
            throw SSException.get(OaException.DispatchInfoIdIllegal);
        }
        List<DispatchUpdateInfo> dispatchUpdateInfoList = this.listByDispatchInfoId(dispatchInfoId);

        return this.convertEntityToDto(dispatchUpdateInfoList);
    }

    @Override
    public List<DispatchUpdateDto> listLatestDtoByDispatchInfo(int dispatchInfoId, int isStudent) throws SSException {
        if (Assert.lessOrEqualZero(dispatchInfoId)) {
            throw SSException.get(OaException.DispatchInfoIdIllegal);
        }
        List<DispatchUpdateInfo> dispatchUpdateInfoList = this.listLatestByDispatchInfoId(dispatchInfoId,isStudent);

        return this.convertEntityToDto(dispatchUpdateInfoList);
    }

    @Override
    public void whereIsAltered(DispatchInfo dispatchInfo, int alterByWho) throws SSException {
                DispatchQueryDto oldDispatchInfo = dispatchInfoService.getDetail(dispatchInfo.getId());
                DispatchUpdateInfo dispatchUpdateInfo = new DispatchUpdateInfo();
                dispatchUpdateInfo.setDispatchInfoId(dispatchInfo.getId());
                String whereAboutGo = null;
                String cityName = null;
                String propertyName = null;
                String tradeName = null;
                String job = null;
                String reportMode = null;

                //毕业去向
                if(dispatchInfo.getWhereaboutgoId() != null && oldDispatchInfo.getWhereaboutgoId() != null) {
                    if(!oldDispatchInfo.getWhereaboutgoId().equals(dispatchInfo.getWhereaboutgoId())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.WhereAboutGo.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getWhereaboutgo());
                        WhereAboutGoCode whereAboutGoCode = whereAboutGoService.queryByCode(dispatchInfo.getWhereaboutgoId());
                        whereAboutGo = whereAboutGoCode.getWhereAboutGo();
                        dispatchUpdateInfo.setAfterUpdate(whereAboutGo);
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //签约单位名称
                if(dispatchInfo.getCompanyName() != null && oldDispatchInfo.getCompanyName() != null) {
                    if(!oldDispatchInfo.getCompanyName().equals(dispatchInfo.getCompanyName())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.CompanyName.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getCompanyName());
                        dispatchUpdateInfo.setAfterUpdate(dispatchInfo.getCompanyName());
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //单位组织机构代码
                if(dispatchInfo.getOrganizationCode() != null && oldDispatchInfo.getOrganizationCode() != null) {
                    if(!oldDispatchInfo.getOrganizationCode().equals(dispatchInfo.getOrganizationCode())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.OrganizationCode.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getOrganizationCode());
                        dispatchUpdateInfo.setAfterUpdate(dispatchInfo.getOrganizationCode());
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //单位所在地
                if(dispatchInfo.getCityId() != null && oldDispatchInfo.getCityId() != null) {
                    if(!oldDispatchInfo.getCityId().equals(dispatchInfo.getCityId())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.CityName.getId());
                        if(oldDispatchInfo.getCityName() == null) {
                            dispatchUpdateInfo.setBeforeUpdate(" ");
                        }else {
                            dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getCityName());
                        }
                        if(dispatchInfo.getCityId() == 0){
                            cityName = " ";
                        }else
                        {
                            PlaceCode placeCode = new PlaceCode();
                            placeCode = placeService.queryByCode(dispatchInfo.getCityId().toString());
                            cityName = placeCode.getShowName();
                        }
                        dispatchUpdateInfo.setAfterUpdate(cityName);
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //单位性质
                if(dispatchInfo.getPropertyId() != null && dispatchInfo.getPropertyId() != 0 && oldDispatchInfo.getPropertyId() != null) {
                    if(!oldDispatchInfo.getPropertyId().equals(dispatchInfo.getPropertyId())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.PropertyName.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getPropertyName());
                        if(dispatchInfo.getPropertyId() == null || dispatchInfo.getPropertyId() == 0 ){
                            propertyName = " ";
                        }else {
                            propertyName = companyPropertyService.queryByCode(dispatchInfo.getPropertyId()).getPropertyName();
                        }
                        dispatchUpdateInfo.setAfterUpdate(propertyName);
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //单位行业
                if(dispatchInfo.getTradeId() != null && dispatchInfo.getTradeId() != 0 && oldDispatchInfo.getTradeId() != null) {
                    if(!oldDispatchInfo.getTradeId().equals(dispatchInfo.getTradeId())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.TradeName.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getPropertyName());
                        if(dispatchInfo.getTradeId() == 0) {
                            tradeName = " ";
                        }else {
                            tradeName = companyTradeService.queryByCode(dispatchInfo.getTradeId()).getTradeName();
                        }
                        dispatchUpdateInfo.setAfterUpdate(tradeName);
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //单位隶属部门
                if(dispatchInfo.getSubordinateDepartment() != null && oldDispatchInfo.getSubordinateDepartment() != null) {
                    if(!oldDispatchInfo.getSubordinateDepartment().equals(dispatchInfo.getSubordinateDepartment())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.SubordinateDepartment.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getSubordinateDepartment());
                        dispatchUpdateInfo.setAfterUpdate(dispatchInfo.getSubordinateDepartment());
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //工作职位类别
                if(dispatchInfo.getJobId() != null && oldDispatchInfo.getJobId() != null) {
                    if(!oldDispatchInfo.getJobId().equals(dispatchInfo.getJobId())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.Job.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getJob());
                        if(dispatchInfo.getJobId() == 0){
                            job = " ";
                        }else {
                            job = jobCodeService.queryByCode(dispatchInfo.getJobId()).getJob();
                        }
                        dispatchUpdateInfo.setAfterUpdate(job);
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //单位地址
                if(dispatchInfo.getFullAddress() != null && oldDispatchInfo.getFullAddress() != null) {
                    if(!oldDispatchInfo.getFullAddress().equals(dispatchInfo.getFullAddress())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.FullAddress.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getFullAddress());
                        dispatchUpdateInfo.setAfterUpdate(dispatchInfo.getFullAddress());
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //单位邮编
                if(dispatchInfo.getCompanyPostcode() != null && oldDispatchInfo.getCompanyPostcode() != null) {
                    if(!oldDispatchInfo.getCompanyPostcode().equals(dispatchInfo.getCompanyPostcode())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.CompanyPostcode.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getCompanyPostcode());
                        dispatchUpdateInfo.setAfterUpdate(dispatchInfo.getCompanyPostcode());
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //单位联系人
                if(dispatchInfo.getCompanyContactPerson() != null && oldDispatchInfo.getCompanyContactPerson() != null) {
                    if(!oldDispatchInfo.getCompanyContactPerson().equals(dispatchInfo.getCompanyContactPerson())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.CompanyContactPerson.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getCompanyContactPerson());
                        dispatchUpdateInfo.setAfterUpdate(dispatchInfo.getCompanyContactPerson());
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //单位联系人传真
                if(dispatchInfo.getContactPersonFax() != null && oldDispatchInfo.getContactPersonFax() != null) {
                    if(!oldDispatchInfo.getContactPersonFax().equals(dispatchInfo.getContactPersonFax())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.ContactPersonFax.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getContactPersonFax());
                        dispatchUpdateInfo.setAfterUpdate(dispatchInfo.getContactPersonFax());
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //单位联系人电话
                if(dispatchInfo.getContactPersonTele() != null && oldDispatchInfo.getContactPersonTele() != null) {
                    if(!oldDispatchInfo.getContactPersonTele().equals(dispatchInfo.getContactPersonTele())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.ContactPersonTele.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getContactPersonTele());
                        dispatchUpdateInfo.setAfterUpdate(dispatchInfo.getContactPersonTele());
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //单位联系人手机
                if(dispatchInfo.getContactPersonMobile() != null && oldDispatchInfo.getContactPersonMobile() != null) {
                    if(!oldDispatchInfo.getContactPersonMobile().equals(dispatchInfo.getContactPersonMobile())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.ContactPersonMobile.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getContactPersonMobile());
                        dispatchUpdateInfo.setAfterUpdate(dispatchInfo.getContactPersonMobile());
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //单位联系人邮箱
                if(dispatchInfo.getContactPersonEmail() != null && oldDispatchInfo.getContactPersonEmail() != null) {
                    if(!oldDispatchInfo.getContactPersonEmail().equals(dispatchInfo.getContactPersonEmail())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.ContactPersonEmail.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getContactPersonEmail());
                        dispatchUpdateInfo.setAfterUpdate(dispatchInfo.getContactPersonEmail());
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //报到证发放类别
                if(dispatchInfo.getReportModeId() != null && oldDispatchInfo.getReportModeId() != null) {
                    if(!oldDispatchInfo.getReportModeId().equals(dispatchInfo.getReportModeId())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.ReportMode.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getReportMode());
                        reportMode = reportCodeService.queryByCode(dispatchInfo.getReportModeId()).getReportMode();
                        dispatchUpdateInfo.setAfterUpdate(reportMode);
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //报到证签往单位名称
                if(dispatchInfo.getReportCompany() != null && oldDispatchInfo.getReportCompany() != null) {
                    if(!oldDispatchInfo.getReportCompany().equals(dispatchInfo.getReportCompany())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.ReportCompany.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getReportCompany());
                        dispatchUpdateInfo.setAfterUpdate(dispatchInfo.getReportCompany());
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //报到证签往单位所在地
                if(dispatchInfo.getReportAddressName() != null && oldDispatchInfo.getReportAddressName() != null) {
                    if(!oldDispatchInfo.getReportAddressName().equals(dispatchInfo.getReportAddressName())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.ReportAddressName.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getReportAddressName());
                        dispatchUpdateInfo.setAfterUpdate(dispatchInfo.getReportAddressName());
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }

                //备注
                if(dispatchInfo.getStuRemark() != null && oldDispatchInfo.getStuRemark() != null) {
                    if(!oldDispatchInfo.getStuRemark().equals(dispatchInfo.getStuRemark())) {
                        dispatchUpdateInfo.setUpdateType(DispatchUpdateInfoTypeEnums.StudentRemark.getId());
                        dispatchUpdateInfo.setBeforeUpdate(oldDispatchInfo.getStuRemark());
                        dispatchUpdateInfo.setAfterUpdate(dispatchInfo.getStuRemark());
                        dispatchUpdateInfo.setAlterByWho(alterByWho);
                        this.newDispatchUpdateInfo(dispatchUpdateInfo);
                    }
                }
return;
            }


    private boolean checkBeforeSave(DispatchUpdateInfo dispatchUpdateInfo) throws SSException {
        if (Assert.isNull(dispatchUpdateInfo)) {
            throw SSException.get(OaException.DispatchUpdateInfoNotNull);
        }
        if (Assert.isNull(dispatchUpdateInfo.getDispatchInfoId()) || Assert.lessOrEqualZero(dispatchUpdateInfo.getDispatchInfoId())) {
            throw SSException.get(OaException.DispatchUpdateInfoIdIllegal);
        }
        if (dispatchUpdateInfo.getAfterUpdate().equals(dispatchUpdateInfo.getBeforeUpdate())) {
            return false;
        }
        return true;
    }

    /**
     * 实体List转化为dto List
     *
     * @param dispatchUpdateInfoList
     * @return
     * @throws SSException
     */
    public List<DispatchUpdateDto> convertEntityToDto (List<DispatchUpdateInfo> dispatchUpdateInfoList) throws SSException {
        List<DispatchUpdateDto> dispatchUpdateDtoList = new ArrayList<>();
        for(DispatchUpdateInfo dispatchUpdateInfo : dispatchUpdateInfoList) {
            DispatchUpdateDto dispatchUpdateDto = new DispatchUpdateDto();
            dispatchUpdateDto.setBeforeUpdate(dispatchUpdateInfo.getBeforeUpdate());
            dispatchUpdateDto.setAfterUpdate(dispatchUpdateInfo.getAfterUpdate());
            dispatchUpdateDto.setUpdateType(DispatchUpdateInfoTypeEnums.valueOf(dispatchUpdateInfo.getUpdateType()).getName());
            dispatchUpdateDto.setUpdateTime(DateUtils.formatDate(dispatchUpdateInfo.getLastModifiedTime(),"yyyy-MM-dd HH:mm:ss"));
            dispatchUpdateDtoList.add(dispatchUpdateDto);
        }
        return dispatchUpdateDtoList;
    }
}
