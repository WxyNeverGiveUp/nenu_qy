package com.pandawork.nenu.oa.service.student.status.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.dto.student.status.StatusInfoListDto;
import com.pandawork.nenu.oa.common.dto.student.status.StuStatusInfoDto;
import com.pandawork.nenu.oa.common.dto.student.status.StudentExportDto;
import com.pandawork.nenu.oa.common.entity.business.Change;
import com.pandawork.nenu.oa.common.entity.business.Protocol;
import com.pandawork.nenu.oa.common.entity.dispatch.Comments;
import com.pandawork.nenu.oa.common.entity.dispatch.ReportCard;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.entity.student.status.StuStatusInfo;
import com.pandawork.nenu.oa.common.entity.student.status.StuUpdateInfo;
import com.pandawork.nenu.oa.common.entity.user.StudentUser;
import com.pandawork.nenu.oa.common.entity.user.StudentUserRole;
import com.pandawork.nenu.oa.common.enums.general.CheckResultEnums;
import com.pandawork.nenu.oa.common.enums.general.CheckStatusEnums;
import com.pandawork.nenu.oa.common.enums.general.MajorQualificationEnums;
import com.pandawork.nenu.oa.common.enums.other.FileUploadPathEnums;
import com.pandawork.nenu.oa.common.enums.student.status.NorMalStuTypeEnums;
import com.pandawork.nenu.oa.common.enums.student.status.StuUpdateInfoTypeEnums;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.common.util.DateUtils;
import com.pandawork.nenu.oa.common.util.WebConstants;
import com.pandawork.nenu.oa.mapper.student.status.StuStatusInfoMapper;
import com.pandawork.nenu.oa.service.business.ChangeService;
import com.pandawork.nenu.oa.service.business.ProtocolService;
import com.pandawork.nenu.oa.service.data.WhereAboutGoService;
import com.pandawork.nenu.oa.service.dispatch.CommentsService;
import com.pandawork.nenu.oa.service.dispatch.DispatchInfoService;
import com.pandawork.nenu.oa.service.dispatch.ReportCardService;
import com.pandawork.nenu.oa.service.general.*;
import com.pandawork.nenu.oa.service.other.FileUploadService;
import com.pandawork.nenu.oa.service.sms.SmsService;
import com.pandawork.nenu.oa.service.student.status.StuStatusInfoService;
import com.pandawork.nenu.oa.service.student.status.StuUpdateInfoService;
import com.pandawork.nenu.oa.service.user.StudentUserRoleService;
import com.pandawork.nenu.oa.service.user.StudentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

/**
 * 学籍Service实现
 * StuStatusInfoServiceImpl
 *
 * @author wlm
 * @date 2016/7/14 10:39
 */

@Service("stuStatusInfoService")
public class StuStatusInfoServiceImpl implements StuStatusInfoService {

    @Autowired
    CommonDao commonDao;

    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    StuStatusInfoMapper stuStatusInfoMapper;

    @Autowired
    MaterialService materialService;

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

    @Autowired
    PlaceService placeService;

    @Autowired
    TrainingModeService trainingModeService;

    @Autowired
    StuUpdateInfoService stuUpdateInfoService;

    @Autowired
    StudentUserService studentUserService;

    @Autowired
    StudentUserRoleService studentUserRoleService;

    @Autowired
    DispatchInfoService dispatchInfoService;

    @Autowired
    ReportCardService reportCardService;

    @Autowired
    CommentsService commentsService;

    @Autowired
    ProtocolService protocolService;

    @Autowired
    ChangeService changeService;

    @Autowired
    SmsService smsService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public StuStatusInfo newStuStatusInfo(StuStatusInfo stuStatusInfo) throws SSException {
        try {
            StuStatusInfo oldStuStatusInfo = this.queryByIdNumber(stuStatusInfo.getIdNumber());
            StuStatusInfo oldInfo2 = this.queryByCandidateNumber(stuStatusInfo.getCandidateNumber());
            StuStatusInfo oldInfo3 = this.queryByStuNumber(stuStatusInfo.getStudentNumber());
            if (Assert.isNotNull(stuStatusInfo.getMajorQualification())) {
                if (stuStatusInfo.getMajorQualification().equals("1") || stuStatusInfo.getMajorQualification().equals("本科专业")) {
                    stuStatusInfo.setMajorQualification(MajorQualificationEnums.valueOf(1).getName());
                } else {
                    stuStatusInfo.setMajorQualification(MajorQualificationEnums.valueOf(2).getName());
                }
            }
            //如果身份证号相同，就覆盖那条数据
            if (!Assert.isNull(oldStuStatusInfo)) {
                stuStatusInfo.setId(oldStuStatusInfo.getId());
                if (Assert.isNull(oldStuStatusInfo.getSchoolCode()) || Assert.lessOrEqualZero(oldStuStatusInfo.getSchoolCode())) {
                    stuStatusInfo.setSchoolCode(WebConstants.schoolCode);
                }
                this.resetInfo(stuStatusInfo);
                this.updateById(stuStatusInfo);
                return this.queryById(oldStuStatusInfo.getId());
            } else {
                //如果身份证号不同，但考生号或学号相同，就提示错误
                if (!Assert.isNull(oldInfo2)) {
                    throw SSException.get(OaException.CandidateNumberExist);
                } else if (!Assert.isNull(oldInfo3)) {
                    throw SSException.get(OaException.StuNumberExist);
                }
                return commonDao.insert(stuStatusInfo);
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoInsertFailed, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateById(StuStatusInfo stuStatusInfo) throws SSException {
        try {
            if (!Assert.isNull(stuStatusInfo.getId()) && Assert.lessOrEqualZero(stuStatusInfo.getId())) {
                throw SSException.get(OaException.IdIllegal);
            }
            if (Assert.isNotNull(stuStatusInfo.getMajorQualification())) {
                if (stuStatusInfo.getMajorQualification().equals("1") || stuStatusInfo.getMajorQualification().equals("本科专业")) {
                    stuStatusInfo.setMajorQualification(MajorQualificationEnums.valueOf(1).getName());
                } else {
                    stuStatusInfo.setMajorQualification(MajorQualificationEnums.valueOf(2).getName());
                }
            }
            commonDao.update(stuStatusInfo);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoUpdateFailed, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateCheckStatusByStuNumber(String stuNumber) throws SSException {
        StuStatusInfo stuStatusInfo = this.queryByStuNumber(stuNumber);
        StuStatusInfo checkInfo = new StuStatusInfo();
        checkInfo.setId(stuStatusInfo.getId());
        if (stuStatusInfo.getCheckStatus() == 3) {
            stuStatusInfo.setCheckStatus(4);
            stuStatusInfo.setCounsellorCheckResult(4);
        } else if (stuStatusInfo.getCheckStatus() == 6) {
            stuStatusInfo.setCheckStatus(7);
            stuStatusInfo.setDeputySecretaryCheckResult(4);
            /*学校审核功能取消*/
//        } else if (stuStatusInfo.getCheckStatus() == 9) {
//            stuStatusInfo.setCheckStatus(10);
//            stuStatusInfo.setSchoolCheckResult(4);
//        } else if (stuStatusInfo.getCheckStatus() == 12) {
//            stuStatusInfo.setCheckStatus(13);
//            stuStatusInfo.setSchoolCheckResult(4);
        } else {
            stuStatusInfo.setCheckStatus(CheckStatusEnums.Unchecked.getId());
        }
        this.updateById(stuStatusInfo);
    }

    @Override
    public StuStatusInfo queryById(int id) throws SSException {
        if (Assert.lessOrEqualZero(id)) {
            throw SSException.get(OaException.IdIllegal);
        }
        try {
            return commonDao.queryById(StuStatusInfo.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
    }

    @Override
    public StuStatusInfoDto queryDtoById(int id) throws SSException {
        StuStatusInfo stuStatusInfo = this.queryById(id);
        StuStatusInfoDto stuStatusInfoDto = null;
        try {
            stuStatusInfoDto = stuStatusInfoMapper.queryDtoById(id);
            if (!Assert.isNull(stuStatusInfo.getMajorCode()) && stuStatusInfo.getMajorCode().equals("000000")){
                stuStatusInfoDto.setMajor("其他-" + stuStatusInfo.getOtherMajor());
            }
            int trainingModeCode = stuStatusInfo.getTrainingModeCode();
            if (trainingModeCode != 2 && trainingModeCode != 4) {
                stuStatusInfoDto.setWeipeiUnit(null);
                stuStatusInfoDto.setWeipeiUnitPlace(null);
            }
            if (Assert.isNull(stuStatusInfo.getCounsellorCheckTime())) {
                stuStatusInfoDto.setCounsellorCheckTime("无");
            }
            if (Assert.isNull(stuStatusInfo.getDeputySecretaryCheckTime())) {
                stuStatusInfoDto.setDeputySecretaryCheckTime("无");
            }
            if (Assert.isNull(stuStatusInfo.getSchoolCheckTime())) {
                stuStatusInfoDto.setSchoolCheckTime("无");
            }
            List<Integer> protocolType = new ArrayList<>();
            protocolType = stuStatusInfoMapper.queryProtocolTypeById(id);
            if(protocolType != null) {
                stuStatusInfoDto.setProtocolType(protocolType);
            }
            return stuStatusInfoDto;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
    }

    @Override
    public StuStatusInfo queryByIdNumber(String idNumber) throws SSException {
        try {
            return stuStatusInfoMapper.queryByIdNumber(idNumber);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
    }

    @Override
    public StuStatusInfo queryByCandidateNumber(String candidateNumber) throws SSException {
        try {
            return stuStatusInfoMapper.queryByCandidateNumber(candidateNumber);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
    }

    @Override
    public Integer queryIdByStuNumber(String stuNumber) throws SSException {
        try {
            return stuStatusInfoMapper.queryIdByStuNumber(stuNumber);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
    }

    @Override
    public StuStatusInfo queryByStuNumber(String stuNumber) throws SSException {
        if (Assert.isNull(stuNumber)) {
            throw SSException.get(OaException.StuNumberNotNull);
        }
        try {
            return stuStatusInfoMapper.queryByStuNumber(stuNumber);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public String uploadMaterial(PandaworkMultipartFile image, Material material) throws SSException {
        Date date = new Date();
        String extPath = DateUtils.formatDate(date, "yyyyMMdd");
        Material material1 = materialService.uploadMaterial(material, image, FileUploadPathEnums.StuStatusInformation, extPath);
        return material1.getMaterialUrl();
    }

    @Override
    public int countByCondition(int stuType, int college, int normalStu, int counsellorCheckResult, int deputySecretaryCheckResult,
                                int schoolCheckResult, int province, String qualification, int trainingMode, String keyword,
                                int sex , int politicalStand , double stuLength, List<String> colleges,String majorCode,String majorQualification, List<String> majors, int qualificationNow,
                                String nation, int showUncommitted,int isRegistered) throws SSException {
        //用户名
        String name = null;
        //学号
        String stuNumber = null;

        String idNumber = null;

        String candidateNumber = null;
        if(keyword.equals("")){
            keyword = null;
        }

        if (!Assert.isNull(keyword)) {
            keyword = keyword.trim();
//            System.out.println("这是处理过后的关键字： "+keyword);
            if (keyword.equals("")) {
//                System.out.println("关键字判断为空");
                keyword = null;
            }else if (keyword.matches("^2\\d{9}$")) {
//                System.out.println("关键字判断为学号");
                stuNumber = keyword;
            }else if (keyword.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$")){
//                System.out.println("关键字判断为身份证号");
                idNumber = keyword;
            }
            else if (keyword.matches("[\\u4E00-\\u9FA5]{1,20}(?:·[\\u4E00-\\u9FA5]{1,20})*")){
//                System.out.println("关键字判断为姓名");
                name = keyword;
            }
            else{
//                System.out.println("关键字判断为考生号");
                candidateNumber = keyword;
            }
        }
        try {
            return stuStatusInfoMapper.countByCondition(stuType, college, normalStu, counsellorCheckResult, deputySecretaryCheckResult,
                    schoolCheckResult, province, qualification, trainingMode, name, stuNumber,idNumber,candidateNumber,sex,politicalStand,stuLength,
                    colleges,majorCode,majorQualification, majors, qualificationNow, nation, showUncommitted,isRegistered);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
    }

    @Override
    public List<StatusInfoListDto> listByCondition(int stuType, int college, int normalStu, int counsellorCheckResult, int deputySecretaryCheckResult,
                                                   int schoolCheckResult, int province, String qualification, int trainingMode, String keyword,
                                                   int sex, int politicalStand, double stuLength, Pagination page, List<String> colleges, String majorCode, String majorQualification, List<String> majors,
                                                   int qualificationNow, String nation, int showUncommitted,int isRegistered) throws SSException {
        List<StatusInfoListDto> statusInfoList = Collections.emptyList();
        int curPage = 0, pageSize = 15;
        String name = null;
        String stuNumber = null;
        String idNumber = null;
        String candidateNumber = null;
        if(keyword.equals("")){
            keyword = null;
        }
        if (!Assert.isNull(keyword)) {
            keyword = keyword.trim();
//            System.out.println("这是处理过后的关键字： "+keyword);
            if (keyword.equals("")) {
//                System.out.println("关键字判断为空");
                keyword = null;
            }else if (keyword.matches("^\\d{9,14}$")) {
//                System.out.println("关键字判断为学号");
                stuNumber = keyword;
            }else if (keyword.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$")){
//                System.out.println("关键字判断为身份证号");
                idNumber = keyword;
            }
            else if (keyword.matches("[\\u4E00-\\u9FA5]{1,20}(?:·[\\u4E00-\\u9FA5]{1,20})*")){
//                System.out.println("关键字判断为姓名");
                name = keyword;
            }
            else{
//                System.out.println("关键字判断为考生号");
                candidateNumber = keyword;
            }
        }


        if (!Assert.isNull(page)) {
            curPage = page.getCurrentFristPosition();
            pageSize = page.getPageSize();
        }
        try {
            statusInfoList = stuStatusInfoMapper.listByCondition(stuType, college, normalStu, counsellorCheckResult, deputySecretaryCheckResult,
                    schoolCheckResult, province, qualification, trainingMode, name, stuNumber,idNumber,candidateNumber ,sex,politicalStand,stuLength,
                    colleges,majorCode,majorQualification, majors, qualificationNow, nation, showUncommitted, curPage, pageSize,isRegistered);
            for(StatusInfoListDto statusInfoListDto : statusInfoList) {
                int id = statusInfoListDto.getId();
                List<Integer> protocolType = new ArrayList<>();
                protocolType = stuStatusInfoMapper.queryProtocolTypeById(id);
                if(protocolType != null) {
                    statusInfoListDto.setProtocolType(protocolType);
                }
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }

        return statusInfoList;
    }

    @Override
    public List<Integer> listIdByCondition(int stuType, int college, int normalStu, int counsellorCheckResult, int deputySecretaryCheckResult,
                                           int schoolCheckResult, int province, String qualification, int trainingMode, String keyword,
                                           int sex,int politicalStand, double stuLength, List<String> colleges,String majorCode,String majorQualification, List<String> majors, int qualificationNow,
                                           String nation, int showUncommitted, Pagination page,int isRegistered) throws SSException {
        List<Integer> idList = Collections.emptyList();
        int curPage = 0, pageSize = 15;
        String name = null;
        String stuNumber = null;
        String idNumber = null;
        String candidateNumber = null;
        if(keyword.equals("")){
            keyword = null;
        }
        if (!Assert.isNull(keyword)) {
            keyword = keyword.trim();
            if (keyword.equals("")) {
                keyword = null;
            }else if (keyword.matches("^\\d{9,14}$")) {
                stuNumber = keyword;
            }else if (keyword.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$")){
                idNumber = keyword;
            }
            else if (keyword.matches("[\\u4E00-\\u9FA5]{1,20}(?:·[\\u4E00-\\u9FA5]{1,20})*")){
//
                name = keyword;
            }
            else{
//                System.out.println("关键字判断为考生号");
                candidateNumber = keyword;
            }
        }


        if (!Assert.isNull(page)) {
            curPage = page.getCurrentFristPosition();
            pageSize = page.getPageSize();
        }

        try {
            idList = stuStatusInfoMapper.listIdByCondition(stuType, college, normalStu, counsellorCheckResult, deputySecretaryCheckResult,
                    schoolCheckResult, province, qualification, trainingMode, name, stuNumber,idNumber,candidateNumber ,sex,politicalStand,stuLength,
                    colleges,majorCode,majorQualification, majors, qualificationNow, nation, showUncommitted, curPage, pageSize,isRegistered);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }

        return idList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void adminUpdateById(StuStatusInfo stuStatusInfo) throws SSException, IOException {
        StuStatusInfo oldStatusInfo = this.queryById(stuStatusInfo.getId());
        StuUpdateInfo stuUpdateInfo = new StuUpdateInfo();
        stuUpdateInfo.setStatusInfoId(stuStatusInfo.getId());
        Map message = new HashMap();
        // 如果学生状态为未提交状态，则修改为提交状态
        if(oldStatusInfo.getCheckStatus() == 0 || oldStatusInfo.getCheckStatus() == 1){
            stuStatusInfo.setCheckStatus(2);
        }
        if (!Assert.isNull(stuStatusInfo.getNormalStuCode())) {
            stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.NormalStudentType.getId());
            stuUpdateInfo.setBeforeUpdate(NorMalStuTypeEnums.valueOf(oldStatusInfo.getNormalStuCode()).getName());
            stuUpdateInfo.setAfterUpdate(NorMalStuTypeEnums.valueOf(stuStatusInfo.getNormalStuCode()).getName());
            if (!stuUpdateInfo.getBeforeUpdate().equals(stuUpdateInfo.getAfterUpdate())){
                message.put(NorMalStuTypeEnums.valueOf(oldStatusInfo.getNormalStuCode()).getName(),NorMalStuTypeEnums.valueOf(stuStatusInfo.getNormalStuCode()).getName());
                stuUpdateInfoService.newStuUpdateInfo(stuUpdateInfo);
            }
        }
        if (!Assert.isNull(stuStatusInfo.getOriginPlace())) {
            stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.OriginPlace.getId());
            stuUpdateInfo.setBeforeUpdate(oldStatusInfo.getOriginPlace());
            stuUpdateInfo.setAfterUpdate(stuStatusInfo.getOriginPlace());
            if (!stuUpdateInfo.getBeforeUpdate().equals(stuUpdateInfo.getAfterUpdate())){
                message.put(oldStatusInfo.getOriginPlace(),stuStatusInfo.getOriginPlace());
                stuUpdateInfoService.newStuUpdateInfo(stuUpdateInfo);
            }
        }
        if (!Assert.isNull(stuStatusInfo.getTrainingModeCode())) {
            stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.TrainingMode.getId());
            stuUpdateInfo.setBeforeUpdate(trainingModeService.queryByCode(oldStatusInfo.getTrainingModeCode()).getTrainingMode());
            stuUpdateInfo.setAfterUpdate(trainingModeService.queryByCode(stuStatusInfo.getTrainingModeCode()).getTrainingMode());
            if (!stuUpdateInfo.getBeforeUpdate().equals(stuUpdateInfo.getAfterUpdate())){
                message.put(trainingModeService.queryByCode(oldStatusInfo.getTrainingModeCode()).getTrainingMode(),trainingModeService.queryByCode(stuStatusInfo.getTrainingModeCode()).getTrainingMode());
                stuUpdateInfoService.newStuUpdateInfo(stuUpdateInfo);
            }
        }
        if (!Assert.isNull(stuStatusInfo.getWeipeiUnit())) {
            stuUpdateInfo.setUpdateType(StuUpdateInfoTypeEnums.WeipeiUnit.getId());
            stuUpdateInfo.setBeforeUpdate(oldStatusInfo.getWeipeiUnit());
            stuUpdateInfo.setAfterUpdate(stuStatusInfo.getWeipeiUnit());
            if (!stuUpdateInfo.getBeforeUpdate().equals(stuUpdateInfo.getAfterUpdate())){
                message.put(oldStatusInfo.getWeipeiUnit(),stuStatusInfo.getWeipeiUnit());
                stuUpdateInfoService.newStuUpdateInfo(stuUpdateInfo);
            }
        }

        if (message.size()>0){
            StringBuffer sms=new StringBuffer();
            int i = message.size();
            Set set = message.keySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()){
                Object k1 = iterator.next();
                Object v1 = message.get(k1);
                if (message.size()==1){
                    sms.append(k1+"”修改为“"+v1);
                }else {
                    if(i==message.size()){
                        sms.append(k1+"”修改为“"+v1);
                        i--;
                    }
                    else {
                        sms.append("”"+"、由“"+k1+"”修改为“"+v1);
                   }
                }
            }
            smsService.sendSms(oldStatusInfo.getName()+"同学，你的生源所在地（或师范生类别、培养方式）被辅导员（或学院副书记、学校就业中心）由“"+sms+"”，如有问题，请联系辅导员。",oldStatusInfo.getCellphone());
        }
        this.updateById(stuStatusInfo);
    }

    public boolean materialUpdateById(StuStatusInfo stuStatusInfo) throws SSException, IOException{
        try{
            StuStatusInfo oldStatusInfo = this.queryById(stuStatusInfo.getId());
            Integer cnt = 0;
            boolean isToWeipei = false;
            if (!Assert.isNull(stuStatusInfo.getNormalStuCode())&&!stuStatusInfo.getNormalStuCode().equals(oldStatusInfo.getNormalStuCode())){
                cnt++;
            }
            if (!Assert.isNull(stuStatusInfo.getOriginPlace())&&!stuStatusInfo.getOriginPlace().equals(oldStatusInfo.getOriginPlace())){
                // 判断生源地提交之前是否为空，如果为空说明是第一修改（添加），则不需要上传材料。
                StuStatusInfo stuStatusInfoBefore = stuStatusInfoMapper.queryByStuNumber(stuStatusInfo.getStudentNumber());
                if (Assert.isNotNull(stuStatusInfoBefore.getOriginPlace()) && stuStatusInfoBefore.getOriginPlace() != ""){
                    cnt++;
                }
            }
            if (!Assert.isNull(stuStatusInfo.getTrainingModeCode())&&!stuStatusInfo.getTrainingModeCode().equals(oldStatusInfo.getTrainingModeCode())){
                if (oldStatusInfo.getTrainingModeCode()!=4 && stuStatusInfo.getTrainingModeCode()==4){
                    isToWeipei = true;
                }
                cnt++;
            }
            if (!Assert.isNull(stuStatusInfo.getWeipeiUnit())&&!stuStatusInfo.getWeipeiUnit().equals(oldStatusInfo.getWeipeiUnit())){
                if (!isToWeipei){
                    cnt++;
                }
            }
            if (cnt<=materialService.listAllByStatusInfoId(stuStatusInfo.getId()).size()){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
    }

    @Override
    public int countMinorityByCondition(int college, int province, List<String> colleges, List<String> majors, int qualificationNow, String nation, int showUncommitted) throws SSException {
        try {
            return stuStatusInfoMapper.countMinorityByCondition(college, province, colleges, majors, qualificationNow, nation, showUncommitted);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
    }

    @Override
    public List<StatusInfoListDto> listMinorityByCondition(int college, int province, Pagination page, List<String> colleges, List<String> majors, int qualificationNow, String nation, int showUncommitted) throws SSException {
        List<StatusInfoListDto> statusInfoList = Collections.emptyList();
        int curPage = 0, pageSize = 15;

        if (!Assert.isNull(page)) {
            curPage = page.getCurrentFristPosition();
            pageSize = page.getPageSize();
        }

        try {
            statusInfoList = stuStatusInfoMapper.listMinorityByCondition(college, province, colleges, majors, qualificationNow, nation,
                    showUncommitted, curPage, pageSize);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }

        return statusInfoList;
    }

    @Override
    public List<Integer> listMinorityIdByCondition(int college, int province, List<String> colleges, List<String> majors, int qualificationNow, String nation, int showUncommitted, Pagination page) throws SSException {
        List<Integer> idList = Collections.emptyList();
        int curPage = 0, pageSize = 15;

        if (!Assert.isNull(page)) {
            curPage = page.getCurrentFristPosition();
            pageSize = page.getPageSize();
        }

        try {
            idList = stuStatusInfoMapper.listMinorityIdByCondition(college, province, colleges, majors, qualificationNow, nation,
                    showUncommitted, curPage, pageSize);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }

        return idList;
    }

    @Override
    public List<StudentExportDto> listAllExportInfo(int grade) throws SSException {
        List<StudentExportDto> lists = null;
        try {
            lists = stuStatusInfoMapper.listAllExportInfo(grade);
            for (StudentExportDto studentExportDto : lists) {
                if (studentExportDto.getMajorCode().equals("000000")) {
                    studentExportDto.setMajor(studentExportDto.getOtherMajor());
                }
            }
            return lists;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }
    }

    @Override
    public List<StudentExportDto> listExportInfoByCondition(int stuType, int college, int normalStu, int counsellorCheckResult, int deputySecretaryCheckResult,
                                                            int schoolCheckResult, int province, String qualification, int trainingMode, String keyword,
                                                            int sex, int politicalStand, List<String> colleges, String major,String majorQualification, List<String> majors,
                                                            int qualificationNow, String nation, double stuLength, int showUncommitted,int isRegistered) throws SSException {
        List<StudentExportDto> statusInfoList = null;
        String name = null;
        String stuNumber = null;
        String idNumber = null;
        String candidateNumber = null;
        if(keyword.equals("")){
            keyword = null;
        }
        if (!Assert.isNull(keyword)) {
            keyword = keyword.trim();
//            System.out.println("这是处理过后的关键字： "+keyword);
            if (keyword.equals("")) {
//                System.out.println("关键字判断为空");
                keyword = null;
            }else if (keyword.matches("^\\d{9,14}$")) {
//                System.out.println("关键字判断为学号");
                stuNumber = keyword;
            }else if (keyword.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$")){
//                System.out.println("关键字判断为身份证号");
                idNumber = keyword;
            }
            else if (keyword.matches("[\\u4E00-\\u9FA5]{2,5}(?:·[\\u4E00-\\u9FA5]{2,5})*")){
//                System.out.println("关键字判断为姓名");
                name = keyword;
            }
            else{
//                System.out.println("关键字判断为考生号");
                candidateNumber = keyword;
            }
        }

        try {
            statusInfoList = stuStatusInfoMapper.listExportInfoByCondition(stuType, college, normalStu, counsellorCheckResult, deputySecretaryCheckResult,
                    schoolCheckResult, province, qualification, trainingMode, name, stuNumber,idNumber,candidateNumber ,sex,politicalStand,
                    colleges,major,majorQualification, majors, qualificationNow, nation, stuLength, showUncommitted, isRegistered);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoQueryFailed, e);
        }

        return statusInfoList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void deleteStudentInfo(int statusInfoId) throws SSException{
        try{
            StuStatusInfo stuStatusInfo = this.queryById(statusInfoId);
            // 删除学生派遣信息
            dispatchInfoService.deleteDispatchByStuStatusInfoId(statusInfoId);
            // 删除学生登录账户信息（账户和角色）t_sys_stu_users、t_sys_stu_users_roles
            StudentUser studentUser = studentUserService.queryByStuNumber(stuStatusInfo.getStudentNumber());
            if (Assert.isNotNull(studentUser)){
                commonDao.delete(studentUser);
            }
            if (Assert.isNotNull(studentUser)){
                List<StudentUserRole> studentUserRoleList = studentUserRoleService.listUsersRolesByStudentUserId(studentUser.getId());
                commonDao.deleteAll(studentUserRoleList);
            }

            // 删除学生材料信息(假删) t_material
            List<Material> materialList = materialService.listAllByStatusInfoId(statusInfoId);
            for (Material material: materialList){
                materialService.delById(material.getId());
            }

            // 删除学生协议 t_protocol
//            Protocol protocol = protocolService.queryCheckResult(statusInfoId);
            List<Protocol> protocolList = protocolService.queryCheckResult(statusInfoId);
            if (Assert.isNotNull(protocolList)){
                for (Protocol protocol : protocolList){
                    commonDao.delete(protocol);
                }
            }

            // 删除审核身份变更 t_change
//            List<Change> changeList = changeService.queryAlterRecord(statusInfoId);
//            if (Assert.isEmpty(changeList)){
//                commonDao.deleteAll(changeList);
//            }

            // 删除学生报到证 t_report_card
            ReportCard reportCard = reportCardService.queryByStatusId(statusInfoId);
            if (Assert.isNotNull(reportCard)){
                commonDao.delete(reportCard);
            }

            // 删除学生办公信息 t_comments
            Comments comments = commentsService.queryByStatusId(statusInfoId);
            if (Assert.isNotNull(comments)){
                commonDao.delete(comments);
            }

            // 删除学生学籍、学籍修改信息
            this.deleteStudentStatusInfo(statusInfoId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoDeleteFailed, e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void deleteStudentStatusInfo(int statusInfoId) throws SSException{
        StuStatusInfo stuStatusInfo = null;
        List<StuUpdateInfo> stuUpdateInfoList = Collections.emptyList();
        try{
            if (Assert.lessOrEqualZero(statusInfoId)){
                throw SSException.get(OaException.IdIllegal);
            }
            stuStatusInfo = this.queryById(statusInfoId);

            stuUpdateInfoList = stuUpdateInfoService.listByStuStatusInfoId(statusInfoId);
            // 删除学生学籍信息
            commonDao.delete(stuStatusInfo);
            // 删除学生学籍修改信息
            commonDao.deleteAll(stuUpdateInfoList);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoDeleteFailed, e);
        }
    }

    /**
     * 将新增的学生所有信息初始化
     *
     * @param stuStatusInfo
     * @return
     * @throws SSException
     */
    private StuStatusInfo resetInfo(StuStatusInfo stuStatusInfo) throws SSException {
        stuStatusInfo.setSex(1);
        stuStatusInfo.setNationCode("");
        stuStatusInfo.setPoliticalStandCode("");
        stuStatusInfo.setSchoolCode(WebConstants.schoolCode);
        stuStatusInfo.setStuLength(0.0);
        stuStatusInfo.setQualificationNow(0);
        stuStatusInfo.setQualificationCode("");
        stuStatusInfo.setMajorCode("");
        stuStatusInfo.setOtherMajor("");
        stuStatusInfo.setIntendWhereaboutsCode("");
        stuStatusInfo.setWeipeiUnit("");
        stuStatusInfo.setWeipeiUnitPlace("");
        stuStatusInfo.setWeipeiUnitPlaceCode(0);
        stuStatusInfo.setPreHukouLocation("");
        stuStatusInfo.setPreArchiveLocation("");
        stuStatusInfo.setIsHukouIntoSchool(0);
        stuStatusInfo.setIsArchiveIntoSchool(0);
        stuStatusInfo.setCellphone("");
        stuStatusInfo.setCellphoneBak("");
        stuStatusInfo.setQq("");
        stuStatusInfo.setEmail("");
        stuStatusInfo.setWeixin("");
        stuStatusInfo.setHomePhone("");
        stuStatusInfo.setRelativePhone("");
        stuStatusInfo.setHomeAddress("");
        stuStatusInfo.setCheckStatus(0);
        stuStatusInfo.setCounsellorCheckResult(CheckResultEnums.Unchecked.getId());
        stuStatusInfo.setCounsellorCheckReason("");
        stuStatusInfo.setCounsellorCheckOperator("");
        stuStatusInfo.setDeputySecretaryCheckResult(CheckResultEnums.Unchecked.getId());
        stuStatusInfo.setDeputySecretaryCheckReason("");
        stuStatusInfo.setDeputySecretaryCheckOperator("");
        stuStatusInfo.setSchoolCheckResult(CheckResultEnums.Unchecked.getId());
        stuStatusInfo.setSchoolCheckReason("");
        stuStatusInfo.setSchoolCheckOperator("");

        return stuStatusInfo;
    }

    /**
     * 副书记批量审核
     * @param id
     * @throws SSException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public boolean batchAudit(List<Integer> id ,Date date, String realName) throws SSException{
        try{
            for(int i : id){
                StuStatusInfoDto stuStatusInfoDto = stuStatusInfoMapper.queryDtoById(i);
                if(stuStatusInfoDto.getCounsellorCheckResult().equals("未审核")||stuStatusInfoDto.getCounsellorCheckResult().equals("待修改")){
                    return false;
                }
            }
            stuStatusInfoMapper.batchAudit(id,date,realName);
            return true;
        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuStatusInfoBatchAuditFailed,e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SSException.class, Exception.class, RuntimeException.class})
    public void updateIntentionSurveyStatus(int statusInfoId,int intentionSurveyStatus)throws SSException{
        if(Assert.lessOrEqualZero(statusInfoId)){
            throw SSException.get(OaException.StuStatusInfoIdIllegal);
        }
        if(Assert.isNull(intentionSurveyStatus)){
            throw SSException.get(OaException.StuStatusInfoIntentionSurveyStatusStatusIllegal);
        }
        try{
            stuStatusInfoMapper.updateIntentionSurveyStatus(statusInfoId, intentionSurveyStatus);
        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.StuUpdateIntentionSurveyStatusFailed, e);
        }
    }
    @Override
    public String queryMajorByQualificationAndCode(String majorQualification,String majorCode)throws SSException{
        String major = null;
        try{
            major = stuStatusInfoMapper.queryMajorByQualificationAndCode(majorQualification,majorCode);
        }catch (Exception e){
            LogClerk.errLog.error(e);
        }
        return major;
    }

    @Override
    public List<String> listCollegesByStuNumber(String studentNumber) throws SSException{
        try{
            HashSet<String> hashSet = new HashSet<>(stuStatusInfoMapper.listCollegesByStuNumber(studentNumber));
            List<String> collegeList = new ArrayList<>();
            collegeList.addAll(hashSet);
            return collegeList;
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.UserInfoQueryFailed,e);
        }

    }
}
