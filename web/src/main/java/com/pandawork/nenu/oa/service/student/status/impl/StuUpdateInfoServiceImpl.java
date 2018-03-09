package com.pandawork.nenu.oa.service.student.status.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.dto.student.status.StuStatusInfoDto;
import com.pandawork.nenu.oa.common.dto.student.status.UpdateInfoDto;
import com.pandawork.nenu.oa.common.entity.data.DifficultyCode;
import com.pandawork.nenu.oa.common.entity.data.WhereAboutGoCode;
import com.pandawork.nenu.oa.common.entity.student.status.StuStatusInfo;
import com.pandawork.nenu.oa.common.entity.student.status.StuUpdateInfo;
import com.pandawork.nenu.oa.common.enums.student.status.QualificationEnums;
import com.pandawork.nenu.oa.common.enums.student.status.QualificationNowEnums;
import com.pandawork.nenu.oa.common.enums.student.status.StuUpdateInfoTypeEnums;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.DateUtils;
import com.pandawork.nenu.oa.mapper.student.status.StuUpdateInfoMapper;
import com.pandawork.nenu.oa.service.data.WhereAboutGoService;
import com.pandawork.nenu.oa.service.general.*;
import com.pandawork.nenu.oa.service.student.status.StuStatusInfoService;
import com.pandawork.nenu.oa.service.student.status.StuUpdateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * StuUpdateInfoServiceImpl
 * 学籍修改信息Service实现
 *
 * @author wlm
 * @date 2016/7/20 17:19
 */
@Service("stuUpdateInfoService")
public class StuUpdateInfoServiceImpl implements StuUpdateInfoService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    StuUpdateInfoMapper stuUpdateInfoMapper;

    @Autowired
    StuStatusInfoService stuStatusInfoService;


    @Autowired
    NationService nationService;

    @Autowired
    PoliticalService politicalService;

    @Autowired
    QualificationService qualificationService;

    @Autowired
    CollegeService collegeService;

    @Autowired
    MajorService majorService;

    @Autowired
    WhereAboutGoService whereAboutGoService;

    @Autowired
    DifficultyService difficultyService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public StuUpdateInfo newStuUpdateInfo(StuUpdateInfo stuUpdateInfo) throws SSException {
        try {
            if (!checkBeforeSave(stuUpdateInfo)) {
                return null;
            }
            return commonDao.insert(stuUpdateInfo);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuUpdateInfoInsertFailed, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateById(StuUpdateInfo stuUpdateInfo) throws SSException {
        try {
            if (stuUpdateInfo.getBeforeUpdate().equals(stuUpdateInfo.getAfterUpdate())) {
                throw SSException.get(OaException.BeforeAfterEqual);
            }
            commonDao.update(stuUpdateInfo);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuUpdateInfoUpdateFailed, e);
        }
    }

    @Override
    public StuUpdateInfo queryById(int id) throws SSException {
        if (Assert.lessOrEqualZero(id)) {
            throw SSException.get(OaException.IdIllegal);
        }
        try {
            return commonDao.queryById(StuUpdateInfo.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
    }

    @Override
    public List<StuUpdateInfo> listLatestByStuStatusInfoId(int stuStatusInfoId,int isStudent) throws SSException {
        if (Assert.lessOrEqualZero(stuStatusInfoId)) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        List<StuUpdateInfo> stuUpdateInfoList = Collections.emptyList();
        try {
            stuUpdateInfoList = stuUpdateInfoMapper.listLatestByStuStatusInfoId(stuStatusInfoId,isStudent);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
        return stuUpdateInfoList;
    }

   @Override
    public List<StuUpdateInfo> listByStuStatusInfoId(int stuStatusInfoId) throws  SSException{
        if (Assert.lessOrEqualZero(stuStatusInfoId)) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        List<StuUpdateInfo> stuUpdateInfoList = Collections.emptyList();
        try {
            stuUpdateInfoList = stuUpdateInfoMapper.listByStuStatusInfoId(stuStatusInfoId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
        return stuUpdateInfoList;
    }

    @Override
    public List<UpdateInfoDto> listDtoByStuStatusInfoId(int stuStatusInfoId) throws SSException {
        if (Assert.lessOrEqualZero(stuStatusInfoId)) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        List<StuUpdateInfo> stuUpdateInfoList = this.listByStuStatusInfoId(stuStatusInfoId);

        return this.convertEntityToDto(stuUpdateInfoList);
    }

    @Override
    public List<UpdateInfoDto> listLatestDtoByStuStatusInfoId(int stuStatusInfoId,int isStudent) throws SSException {
        if (Assert.lessOrEqualZero(stuStatusInfoId)) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        List<StuUpdateInfo> stuUpdateInfoList = this.listLatestByStuStatusInfoId(stuStatusInfoId,isStudent);

        return this.convertEntityToDto(stuUpdateInfoList);
    }

    @Override
    public List<StuUpdateInfo> listByStatusInfoIdAndType(int statusInfoId, int type) throws SSException {
        if (Assert.lessOrEqualZero(statusInfoId)) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        if (Assert.isNull(StuUpdateInfoTypeEnums.valueOf(type))) {
            throw SSException.get(OaException.StuUpdateTypeIllegal);
        }
        List<StuUpdateInfo> stuUpdateInfoList = Collections.emptyList();
        try {
            stuUpdateInfoList = stuUpdateInfoMapper.listByStatusInfoIdAndType(statusInfoId, type);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
        return stuUpdateInfoList;
    }

    @Override
    public List<UpdateInfoDto> listDtoByStatusInfoIdAndType(int statusInfoId, int type) throws SSException {
        if (Assert.lessOrEqualZero(statusInfoId)) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        if (Assert.isNull(StuUpdateInfoTypeEnums.valueOf(type))) {
            throw SSException.get(OaException.StuUpdateTypeIllegal);
        }
        List<StuUpdateInfo> stuUpdateInfoList = this.listByStatusInfoIdAndType(statusInfoId, type);

        return this.convertEntityToDto(stuUpdateInfoList);
    }

    private boolean checkBeforeSave(StuUpdateInfo stuUpdateInfo) throws SSException {
        if (Assert.isNull(stuUpdateInfo)) {
            throw SSException.get(OaException.StuUpdateInfoNotNull);
        }
        if (Assert.isNull(stuUpdateInfo.getStatusInfoId()) || Assert.lessOrEqualZero(stuUpdateInfo.getStatusInfoId())) {
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        if (stuUpdateInfo.getAfterUpdate().equals(stuUpdateInfo.getBeforeUpdate())) {
            return false;
        }
        return true;
    }

    /**
     * 将实体List转换为dtoList
     *
     * @param stuUpdateInfoList
     * @return
     * @throws SSException
     */
    private List<UpdateInfoDto> convertEntityToDto(List<StuUpdateInfo> stuUpdateInfoList) throws SSException {
        List<UpdateInfoDto> updateInfoDtoList = new ArrayList<>();
        for (StuUpdateInfo stuUpdateInfo : stuUpdateInfoList) {
            UpdateInfoDto updateInfoDto = new UpdateInfoDto();
            updateInfoDto.setBeforeUpdate(stuUpdateInfo.getBeforeUpdate());
            updateInfoDto.setAfterUpdate(stuUpdateInfo.getAfterUpdate());
            updateInfoDto.setUpdateType(StuUpdateInfoTypeEnums.valueOf(stuUpdateInfo.getUpdateType()).getName());
            updateInfoDto.setUpdateTime(DateUtils.formatDate(stuUpdateInfo.getLastModifiedTime(), "yyyy-MM-dd HH:mm:ss"));
            updateInfoDtoList.add(updateInfoDto);
        }
        return updateInfoDtoList;
    }

    @Override
    public void whereIsAltered(StuStatusInfo stuStatusInfo,int alterByWho) throws SSException{
        if(stuStatusInfo.getCounsellorCheckReason()!=null&&stuStatusInfo.getDeputySecretaryCheckReason()!=null) {
            if ("个人信息有错误".equals(stuStatusInfo.getCounsellorCheckReason()) || "个人信息有错误".equals(stuStatusInfo.getDeputySecretaryCheckReason())
                    || alterByWho == 0) {
                StuStatusInfo oldstuStatusInfo = stuStatusInfoService.queryById(stuStatusInfo.getId());
                StuStatusInfoDto oldstuStatusInfoDto = stuStatusInfoService.queryDtoById(stuStatusInfo.getId());
                StuUpdateInfo stuUpdateInfo = new StuUpdateInfo();
                stuUpdateInfo.setStatusInfoId(stuStatusInfo.getId());
                String nation = null;
                String politicalStand = null;
                String qualification = null;
                String college = null;
                String major = null;
                String intendWhereAboutGo = null;
                String difficulty = null;
                String majorQualification = null;

                // 将专业层次代码转为专业层次汉字
                String majorQualificationString = QualificationEnums.valueOf(Integer.parseInt(stuStatusInfo.getMajorQualification())).getName();
                stuStatusInfo.setMajorQualification(majorQualificationString);

                //姓名
                if (stuStatusInfo.getName() != null && oldstuStatusInfo.getName() != null) {
                    if (!oldstuStatusInfo.getName().equals(stuStatusInfo.getName())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.Name.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getName());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getName());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }
                //身份证号
                if (stuStatusInfo.getIdNumber() != null && oldstuStatusInfo.getIdNumber() != null) {
                    if (!oldstuStatusInfo.getIdNumber().equals(stuStatusInfo.getIdNumber())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.IdNumber.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getIdNumber());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getIdNumber());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //考生号
                if (stuStatusInfo.getCandidateNumber() != null && oldstuStatusInfo.getCandidateNumber() != null) {
                    if (!oldstuStatusInfo.getCandidateNumber().equals(stuStatusInfo.getCandidateNumber())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.CandidateNumber.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getCandidateNumber());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getCandidateNumber());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //学号
                if (stuStatusInfo.getStudentNumber() != null && oldstuStatusInfo.getStudentNumber() != null) {
                    if (!oldstuStatusInfo.getStudentNumber().equals(stuStatusInfo.getStudentNumber())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.StudentNumber.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getStudentNumber());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getStudentNumber());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //性别
                if (stuStatusInfo.getSex() != null && oldstuStatusInfo.getSex() != null) {
                    if (!oldstuStatusInfo.getSex().equals(stuStatusInfo.getSex())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.Sex.getId());
                        if (1 == oldstuStatusInfo.getSex()) {
                            stuUpdateInfo.setBeforeUpdate("男");
                        } else if (2 == oldstuStatusInfo.getSex()) {
                            stuUpdateInfo.setBeforeUpdate("女");
                        } else if (oldstuStatusInfo.getSex() == null) {
                            stuUpdateInfo.setBeforeUpdate("");
                        }
                        if (1 == stuStatusInfo.getSex()) {
                            stuUpdateInfo.setAfterUpdate("男");
                        } else if (2 == stuStatusInfo.getSex()) {
                            stuUpdateInfo.setAfterUpdate("女");
                        } else if (stuStatusInfo.getSex() == null) {
                            stuUpdateInfo.setAfterUpdate("");
                        }
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //民族
                if (stuStatusInfo.getNationCode() != null && oldstuStatusInfo.getNationCode() != null) {
                    if (!oldstuStatusInfo.getNationCode().equals(stuStatusInfo.getNationCode())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.Nation.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getNation());
                        nation = nationService.queryByNationId(stuStatusInfo.getNationCode()).getNation();
                        stuUpdateInfo.setAfterUpdate(nation);
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //政治面貌
                if (stuStatusInfo.getPoliticalStandCode() != null && oldstuStatusInfo.getPoliticalStandCode() != null) {
                    if (!oldstuStatusInfo.getPoliticalStandCode().equals(stuStatusInfo.getPoliticalStandCode())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.PoliticalStand.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getPoliticalStand());
                        politicalStand = politicalService.queryByPoliticalStandId(stuStatusInfo.getPoliticalStandCode()).getPoliticalStand();
                        stuUpdateInfo.setAfterUpdate(politicalStand);
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //学制
                if (stuStatusInfo.getStuLength() != null && oldstuStatusInfo.getStuLength() != null) {
                    if (!oldstuStatusInfo.getStuLength().equals(stuStatusInfo.getStuLength())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.StuLength.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getStuLength() + "");
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getStuLength() + "");
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //学历
                if (stuStatusInfo.getQualificationCode() != null && oldstuStatusInfo.getQualificationCode() != null) {
                    if (!oldstuStatusInfo.getQualificationCode().equals(stuStatusInfo.getQualificationCode())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.Qualification.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getQualification());
                        qualification = qualificationService.queryByCode(stuStatusInfo.getQualificationCode()).getQualification();
                        stuUpdateInfo.setAfterUpdate(qualification);
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //所在院系/分院
                if (stuStatusInfo.getCollegeCode() != null && oldstuStatusInfo.getCollegeCode() != null) {
                    if (!oldstuStatusInfo.getCollegeCode().equals(stuStatusInfo.getCollegeCode())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.College.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getCollege());
                        college = collegeService.queryByCode(stuStatusInfo.getCollegeCode()).getCollege();
                        stuUpdateInfo.setAfterUpdate(college);
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //所在年级
                if (stuStatusInfo.getGrade() != null && oldstuStatusInfo.getGrade() != null) {
                    if (!oldstuStatusInfo.getGrade().equals(stuStatusInfo.getGrade())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.Grade.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getGrade() + "");
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getGrade() + "");
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //专业层次
                if (stuStatusInfo.getMajorQualification() != null && oldstuStatusInfo.getMajorQualification() != null) {
                    if (!oldstuStatusInfo.getMajorQualification().equals(stuStatusInfo.getMajorQualification())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.MajorQualification.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getMajorQualification());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getMajorQualification());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //专业
                if (stuStatusInfo.getMajorCode() != null && oldstuStatusInfo.getMajorCode() != null) {
                    if (!oldstuStatusInfo.getMajorCode().equals(stuStatusInfo.getMajorCode())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.Major.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getMajor());
                        major = majorService.queryByCode(stuStatusInfo.getMajorCode(), stuStatusInfo.getMajorQualification()).getMajorName();
                        stuUpdateInfo.setAfterUpdate(major);
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //拟毕业去向
                if (stuStatusInfo.getIntendWhereaboutsCode() != null && oldstuStatusInfo.getIntendWhereaboutsCode() != null) {
                    if (!oldstuStatusInfo.getIntendWhereaboutsCode().equals(stuStatusInfo.getIntendWhereaboutsCode())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.IntendWhereabouts.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getIntendWhereabouts());
                        WhereAboutGoCode whereAboutGoCode = whereAboutGoService.queryByCode(Integer.parseInt(stuStatusInfo.getIntendWhereaboutsCode()));
                        System.out.println("whereAboutGoCode = " + whereAboutGoCode);
                        if(!Assert.isNull(whereAboutGoCode)) {
                            intendWhereAboutGo = whereAboutGoCode.getWhereAboutGo();
                            stuUpdateInfo.setAfterUpdate(intendWhereAboutGo);
                            stuUpdateInfo.setAlterByWho(alterByWho);
                            this.newStuUpdateInfo(stuUpdateInfo);
                        }
                    }
                }

                //困难生类别
                if (stuStatusInfo.getDifficultyCode() != null && oldstuStatusInfo.getDifficultyCode() != null) {
                    if (!oldstuStatusInfo.getDifficultyCode().equals(stuStatusInfo.getDifficultyCode())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.Difficulty.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getDifficulty());
                        difficulty = difficultyService.queryByDifficultyId(stuStatusInfo.getDifficultyCode()).getDifficultyMode();
                        stuUpdateInfo.setAfterUpdate(difficulty);
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }
                //生源所在地
                if (stuStatusInfo.getOriginPlace()!=null&&oldstuStatusInfo.getOriginPlace()!=null) {
                    if (!stuStatusInfo.getOriginPlace().equals(oldstuStatusInfo.getOriginPlace())){
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.OriginPlace.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getOriginPlace());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getOriginPlace());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //定向/委培单位地址
                if (oldstuStatusInfo.getWeipeiUnitPlace() != null) {
                    if (stuStatusInfo.getTrainingModeCode() != null && (stuStatusInfo.getTrainingModeCode() == 2 || stuStatusInfo.getTrainingModeCode() == 4)) {
                        if (!oldstuStatusInfo.getWeipeiUnitPlace().equals(stuStatusInfo.getWeipeiUnitPlace())) {
                            stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.WeipeiUnitPlace.getId());
                            stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getWeipeiUnitPlace());
                            stuUpdateInfo.setAfterUpdate(stuStatusInfo.getWeipeiUnitPlace());
                            stuUpdateInfo.setAlterByWho(alterByWho);
                            this.newStuUpdateInfo(stuUpdateInfo);
                        }
                    }
                }

                //入学日期
                if (stuStatusInfo.getRegistDate() != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    if (!stuStatusInfo.getRegistDate().equals(oldstuStatusInfo.getRegistDate())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.RegistDate.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getRegistDate());
                        stuUpdateInfo.setAfterUpdate(formatter.format(stuStatusInfo.getRegistDate()));
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //毕业日期
                if (stuStatusInfo.getGraduationDate() != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    if (!stuStatusInfo.getGraduationDate().equals(oldstuStatusInfo.getGraduationDate())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.GraduationDate.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getGraduationDate());
                        stuUpdateInfo.setAfterUpdate(formatter.format(stuStatusInfo.getGraduationDate()));
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }
                //入学前户口所在地
                if (stuStatusInfo.getPreHukouLocation() != null && oldstuStatusInfo.getPreHukouLocation() != null) {
                    if (!stuStatusInfo.getPreHukouLocation().equals(oldstuStatusInfo.getPreHukouLocation())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.PreHukouLocation.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getPreHukouLocation());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getPreHukouLocation());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //户口是否转入学校
                if (stuStatusInfo.getIsHukouIntoSchool() != null && oldstuStatusInfo.getIsHukouIntoSchool() != null) {
                    if (!oldstuStatusInfo.getIsHukouIntoSchool().equals(stuStatusInfo.getIsHukouIntoSchool())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.IsHukouIntoSchool.getId());
                        if ("1".equals(oldstuStatusInfoDto.getIsHukouIntoSchool())) {
                            stuUpdateInfo.setBeforeUpdate("是");
                        } else if ("0".equals(oldstuStatusInfoDto.getIsHukouIntoSchool())) {
                            stuUpdateInfo.setBeforeUpdate("否");
                        } else if ("".equals(oldstuStatusInfoDto.getIsHukouIntoSchool())) {
                            stuUpdateInfo.setBeforeUpdate("");
                        }
                        if (1 == stuStatusInfo.getIsHukouIntoSchool()) {
                            stuUpdateInfo.setAfterUpdate("是");
                        } else if (0 == stuStatusInfo.getIsHukouIntoSchool()) {
                            stuUpdateInfo.setAfterUpdate("否");
                        } else if ("".equals(stuStatusInfo.getIsHukouIntoSchool())) {
                            stuUpdateInfo.setAfterUpdate("");
                        }
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //入学前档案所在地
                if (stuStatusInfo.getPreArchiveLocation() != null && oldstuStatusInfo.getPreArchiveLocation() != null) {
                    if (!oldstuStatusInfo.getPreArchiveLocation().equals(stuStatusInfo.getPreArchiveLocation())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.PreArchiveLocation.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getPreArchiveLocation());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getPreArchiveLocation());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //档案是否转入学校
                if (stuStatusInfo.getIsArchiveIntoSchool() != null && oldstuStatusInfo.getIsArchiveIntoSchool() != null) {
                    if (!oldstuStatusInfo.getIsArchiveIntoSchool().equals(stuStatusInfo.getIsArchiveIntoSchool())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.IsArchiveIntoSchool.getId());
                        if ("1".equals(oldstuStatusInfoDto.getIsArchiveIntoSchool())) {
                            stuUpdateInfo.setBeforeUpdate("是");
                        } else if ("0".equals(oldstuStatusInfoDto.getIsArchiveIntoSchool())) {
                            stuUpdateInfo.setBeforeUpdate("否");
                        } else if ("".equals(oldstuStatusInfoDto.getIsArchiveIntoSchool())) {
                            stuUpdateInfo.setBeforeUpdate("");
                        }
                        if (1 == stuStatusInfo.getIsArchiveIntoSchool()) {
                            stuUpdateInfo.setAfterUpdate("是");
                        } else if (0 == stuStatusInfo.getIsArchiveIntoSchool()) {
                            stuUpdateInfo.setAfterUpdate("否");
                        } else if ("".equals(stuStatusInfo.getIsArchiveIntoSchool())) {
                            stuUpdateInfo.setAfterUpdate("");
                        }
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //手机号码
                if (stuStatusInfo.getCellphone() != null && oldstuStatusInfo.getCellphone() != null) {
                    if (!oldstuStatusInfo.getCellphone().equals(stuStatusInfo.getCellphone())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.Cellphone.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getCellphone());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getCellphone());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //手机号码（备用）
                if (stuStatusInfo.getCellphoneBak() != null && oldstuStatusInfo.getCellphoneBak() != null) {
                    if (!oldstuStatusInfo.getCellphoneBak().equals(stuStatusInfo.getCellphoneBak())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.CellphoneBak.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getCellphoneBak());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getCellphoneBak());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //QQ号码
                if (stuStatusInfo.getQq() != null && oldstuStatusInfo.getQq() != null) {
                    if (!oldstuStatusInfo.getQq().equals(stuStatusInfo.getQq())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.Qq.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getQq());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getQq());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //常用电子邮箱
                if (stuStatusInfo.getEmail() != null && oldstuStatusInfo.getEmail() != null) {
                    if (!oldstuStatusInfo.getEmail().equals(stuStatusInfo.getEmail())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.Email.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getEmail());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getEmail());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //家庭电话
                if (stuStatusInfo.getHomePhone() != null && oldstuStatusInfo.getHomePhone() != null) {
                    if (!oldstuStatusInfo.getHomePhone().equals(stuStatusInfo.getHomePhone())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.HomePhone.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getHomePhone());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getHomePhone());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //紧急联系方式
                if (stuStatusInfo.getRelativePhone() != null && oldstuStatusInfo.getRelativePhone() != null) {
                    if (!oldstuStatusInfo.getRelativePhone().equals(stuStatusInfo.getRelativePhone())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.RelativePhone.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getRelativePhone());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getRelativePhone());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //微信号
                if (stuStatusInfo.getWeixin() != null && oldstuStatusInfo.getWeixin() != null) {
                    if (!oldstuStatusInfo.getWeixin().equals(stuStatusInfo.getWeixin())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.Weixin.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getWeixin());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getWeixin());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //家庭住址
                if (stuStatusInfo.getHomeAddress() != null && oldstuStatusInfo.getHomeAddress() != null) {
                    if (!oldstuStatusInfo.getHomeAddress().equals(stuStatusInfo.getHomeAddress())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.HomeAddress.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getHomeAddress());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getHomeAddress());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //寝室楼
                if (stuStatusInfo.getDormitory() != null && oldstuStatusInfo.getDormitory() != null) {
                    if (!oldstuStatusInfo.getDormitory().equals(stuStatusInfo.getDormitory())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.Dormitory.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getDormitory());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getDormitory());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

                //寝室号
                if (stuStatusInfo.getDormitoryNum() != null && oldstuStatusInfo.getDormitoryNum() != null) {
                    if (!oldstuStatusInfo.getDormitoryNum().equals(stuStatusInfo.getDormitoryNum())) {
                        stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.DormitoryNum.getId());
                        stuUpdateInfo.setBeforeUpdate(oldstuStatusInfoDto.getDormitoryNum());
                        stuUpdateInfo.setAfterUpdate(stuStatusInfo.getDormitoryNum());
                        stuUpdateInfo.setAlterByWho(alterByWho);
                        this.newStuUpdateInfo(stuUpdateInfo);
                    }
                }

            } else {//对应第二层的if
                return;
            }
        }

    }
}
