package com.pandawork.nenu.oa.mapper.student.status;

import com.pandawork.nenu.oa.common.dto.student.status.StatusInfoListDto;
import com.pandawork.nenu.oa.common.dto.student.status.StuStatusInfoDto;
import com.pandawork.nenu.oa.common.dto.student.status.StudentExportDto;
import com.pandawork.nenu.oa.common.entity.student.status.StuStatusInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * StuStatusInfoMapper
 *
 * @author wlm
 * @date 2016/7/21 10:04
 */
public interface StuStatusInfoMapper {

    /**
     * 根据学号查询学籍信息
     *
     * @param stuNumber
     * @return
     * @throws Exception
     */
    public StuStatusInfo queryByStuNumber(@Param("stuNumber") String stuNumber) throws Exception;

    /**
     * 根据身份证号查询学籍信息
     *
     * @param idNumber
     * @return
     * @throws Exception
     */
    public StuStatusInfo queryByIdNumber(@Param("idNumber") String idNumber) throws Exception;

    /**
     * 根据考生号查询学籍信息
     *
     * @param candidateNumber
     * @return
     * @throws Exception
     */
    public StuStatusInfo queryByCandidateNumber(@Param("candidateNumber") String candidateNumber) throws Exception;

    /**
     * 根据id查询学籍dto
     *
     * @param id
     * @return
     * @throws Exception
     */
    public StuStatusInfoDto queryDtoById(@Param("id") int id) throws Exception;

    /**
     * 根据学号查询学籍id
     *
     * @param stuNumber
     * @return
     * @throws Exception
     */
    public Integer queryIdByStuNumber(@Param("stuNumber") String stuNumber) throws Exception;

    /**
     * 根据学号查询学籍信息数量
     *
     * @param stuNumber
     * @return
     * @throws Exception
     */
    public Integer countByStuNumber(@Param("stuNumber") String stuNumber) throws Exception;

    /**
     * 查询符合条件的学籍信息数量
     * @param stuType
     * @param college
     * @param normalStu
     * @param counsellorCheckResult
     * @param deputySecretaryCheckResult
     * @param schoolCheckResult
     * @param province
     * @param qualification
     * @param trainingMode
     * @param name
     * @param stuNumber
     * @param idNumber
     * @param candidateNumber
     * @param sex
     * @param politicalStand
     * @param colleges
     * @param majors
     * @param qualificationNow
     * @param nation
     * @param showUncommitted
     * @param isRegistered
     * @return
     * @throws Exception
     */
    public Integer countByCondition(@Param("stuType") int stuType,
                                    @Param("college") int college,
                                    @Param("normalStu") int normalStu,
                                    @Param("counsellorCheckResult") int counsellorCheckResult,
                                    @Param("deputySecretaryCheckResult") int deputySecretaryCheckResult,
                                    @Param("schoolCheckResult") int schoolCheckResult,
                                    @Param("province") int province,
                                    @Param("qualification") String qualification,
                                    @Param("trainingMode") int trainingMode,
                                    @Param("name") String name,
                                    @Param("stuNumber") String stuNumber,
                                    @Param("idNumber") String idNumber,
                                    @Param("candidateNumber") String candidateNumber,
                                    @Param("sex") int sex,
                                    @Param("politicalStand") int politicalStand,
                                    @Param("stuLength") double stuLength,
                                    @Param("colleges") List<String> colleges,
                                    @Param("majorCode") String majorCode,
                                    @Param("majorQualification") String majorQualification,
                                    @Param("majors") List<String> majors,
                                    @Param("qualificationNow") int qualificationNow,
                                    @Param("nation") String nation,
                                    @Param("showUncommitted") int showUncommitted, @Param("isRegistered") int isRegistered) throws Exception;

    /**
     * 分页查询符合条件的学籍信息
     *
     * @param stuType
     * @param college
     * @param normalStu
     * @param counsellorCheckResult
     * @param deputySecretaryCheckResult
     * @param schoolCheckResult
     * @param province
     * @param qualification
     * @param trainingMode
     * @param name
     * @param stuNumber
     * @param colleges                   辅导员或副书记所属学院，隐式传递
     * @param majors                     辅导员管理专业，隐式传递
     * @param qualificationNow
     * @param nation
     * @param showUncommitted
     * @param curPage
     * @param pageSize                   @return
     * @param isRegistered               学生是否已注册
     * @throws Exception
     */
    public List<StatusInfoListDto> listByCondition(@Param("stuType") int stuType,
                                                   @Param("college") int college,
                                                   @Param("normalStu") int normalStu,
                                                   @Param("counsellorCheckResult") int counsellorCheckResult,
                                                   @Param("deputySecretaryCheckResult") int deputySecretaryCheckResult,
                                                   @Param("schoolCheckResult") int schoolCheckResult,
                                                   @Param("province") int province,
                                                   @Param("qualification") String qualification,
                                                   @Param("trainingMode") int trainingMode,
                                                   @Param("name") String name,
                                                   @Param("stuNumber") String stuNumber,
                                                   @Param("idNumber") String idNumber,
                                                   @Param("candidateNumber") String candidateNumber,
                                                   @Param("sex") int sex,
                                                   @Param("politicalStand") int politicalStand,
                                                   @Param("stuLength") double stuLength,
                                                   @Param("colleges") List<String> colleges,
                                                   @Param("majorCode") String majorCode,
                                                   @Param("majorQualification") String majorQualification,
                                                   @Param("majors") List<String> majors,
                                                   @Param("qualificationNow") int qualificationNow,
                                                   @Param("nation") String nation,
                                                   @Param("showUncommitted") int showUncommitted,
                                                   @Param("curPage") int curPage,
                                                   @Param("pageSize") int pageSize, @Param("isRegistered") int isRegistered) throws Exception;

    /**
     * 分页查询符合条件的学籍idList
     *
     * @param stuType
     * @param college
     * @param normalStu
     * @param counsellorCheckResult
     * @param deputySecretaryCheckResult
     * @param schoolCheckResult
     * @param province
     * @param qualification
     * @param trainingMode
     * @param name
     * @param stuNumber
     * @param colleges                   辅导员或副书记所属学院，隐式传递
     * @param majors                     辅导员管理专业，隐式传递
     * @param qualificationNow
     * @param nation
     * @param showUncommitted
     * @param curPage
     * @param pageSize
     * @param isRegistered               学生是否已注册
     * @return
     * @throws Exception
     */
    public List<Integer> listIdByCondition(@Param("stuType") int stuType,
                                           @Param("college") int college,
                                           @Param("normalStu") int normalStu,
                                           @Param("counsellorCheckResult") int counsellorCheckResult,
                                           @Param("deputySecretaryCheckResult") int deputySecretaryCheckResult,
                                           @Param("schoolCheckResult") int schoolCheckResult,
                                           @Param("province") int province,
                                           @Param("qualification") String qualification,
                                           @Param("trainingMode") int trainingMode,
                                           @Param("name") String name,
                                           @Param("stuNumber") String stuNumber,
                                           @Param("idNumber") String idNumber,
                                           @Param("candidateNumber") String candidateNumber,
                                           @Param("sex") int sex,
                                           @Param("politicalStand") int politicalStand,
                                           @Param("stuLength") double stuLength,
                                           @Param("colleges") List<String> colleges,
                                           @Param("majorCode") String majorCode,
                                           @Param("majorQualification") String majorQualification,
                                           @Param("majors") List<String> majors,
                                           @Param("qualificationNow") int qualificationNow,
                                           @Param("nation") String nation,
                                           @Param("showUncommitted") int showUncommitted,
                                           @Param("curPage") int curPage,
                                           @Param("pageSize") int pageSize, @Param("isRegistered") int isRegistered) throws Exception;


    /**
     * 查询符合条件的少数民族学籍信息数量
     *
     * @param college
     * @param province
     * @param colleges
     * @param majors
     * @param qualificationNow
     * @param nation
     * @param showUncommitted
     * @return
     * @throws Exception
     */
    public Integer countMinorityByCondition(@Param("college") int college,
                                            @Param("province") int province,
                                            @Param("colleges") List<String> colleges,
                                            @Param("majors") List<String> majors,
                                            @Param("qualificationNow") int qualificationNow,
                                            @Param("nation") String nation,
                                            @Param("showUncommitted") int showUncommitted) throws Exception;

    /**
     * 分页查询符合条件的少数民族学籍信息
     *
     * @param college
     * @param province
     * @param colleges
     * @param majors
     * @param qualificationNow
     * @param nation
     * @param showUncommitted
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<StatusInfoListDto> listMinorityByCondition(@Param("college") int college,
                                                           @Param("province") int province,
                                                           @Param("colleges") List<String> colleges,
                                                           @Param("majors") List<String> majors,
                                                           @Param("qualificationNow") int qualificationNow,
                                                           @Param("nation") String nation,
                                                           @Param("showUncommitted") int showUncommitted,
                                                           @Param("curPage") int curPage, @Param("pageSize") int pageSize) throws Exception;

    /**
     * 分页查询符合条件的学籍idList
     *
     * @param college
     * @param province
     * @param colleges
     * @param majors
     * @param qualificationNow
     * @param nation
     * @param showUncommitted
     * @param curPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<Integer> listMinorityIdByCondition(@Param("college") int college,
                                                   @Param("province") int province,
                                                   @Param("colleges") List<String> colleges,
                                                   @Param("majors") List<String> majors,
                                                   @Param("qualificationNow") int qualificationNow,
                                                   @Param("nation") String nation,
                                                   @Param("showUncommitted") int showUncommitted,
                                                   @Param("curPage") int curPage, @Param("pageSize") int pageSize) throws Exception;

    /**
     * 查询要导出的学生信息
     *
     * @return
     * @throws Exception
     * @param grade
     */
    public List<StudentExportDto> listAllExportInfo(@Param("grade") int grade) throws Exception;

    /**
     * 按照条件查询要导出的学生信息
     *
     * @param stuType
     * @param college
     * @param normalStu
     * @param counsellorCheckResult
     * @param deputySecretaryCheckResult
     * @param schoolCheckResult
     * @param province
     * @param qualification
     * @param trainingMode
     * @param name
     * @param stuNumber
     * @param idNumber
     * @param candidateNumber
     * @param sex
     * @param politicalStand
     * @param colleges
     * @param major
     * @param majors
     * @param qualificationNow
     * @param nation
     * @param showUncommitted
     * @param isRegistered
     * @return
     * @throws Exception
     */
    public List<StudentExportDto> listExportInfoByCondition(@Param("stuType") int stuType,
                                                            @Param("college") int college,
                                                            @Param("normalStu") int normalStu,
                                                            @Param("counsellorCheckResult") int counsellorCheckResult,
                                                            @Param("deputySecretaryCheckResult") int deputySecretaryCheckResult,
                                                            @Param("schoolCheckResult") int schoolCheckResult,
                                                            @Param("province") int province,
                                                            @Param("qualification") String qualification,
                                                            @Param("trainingMode") int trainingMode,
                                                            @Param("name") String name,
                                                            @Param("stuNumber") String stuNumber,
                                                            @Param("idNumber") String idNumber,
                                                            @Param("candidateNumber") String candidateNumber,
                                                            @Param("sex") int sex,
                                                            @Param("politicalStand") int politicalStand,
                                                            @Param("colleges") List<String> colleges,
                                                            @Param("majorCode") String major,
                                                            @Param("majorQualification") String majorQualification,
                                                            @Param("majors") List<String> majors,
                                                            @Param("qualificationNow") int qualificationNow,
                                                            @Param("nation") String nation,
                                                            @Param("stuLength") double stuLength,
                                                            @Param("showUncommitted") int showUncommitted,
                                                            @Param("isRegistered") int isRegistered) throws Exception;

    /**
     * 副书记批量审核
     * @param id
     * @param date
     * @param realName
     * @throws Exception
     */
    public void batchAudit(@Param("ids") List<Integer> id, @Param("date") Date date, @Param("realName") String realName) throws Exception;

    /**
     * 根据学籍id更新填写就业意向调查状态
     *
     * @param statusInfoId
     * @param intentionSurveyStatus
     * @throws Exception
     */
    public void updateIntentionSurveyStatus(@Param("statusInfoId") int statusInfoId, @Param("intentionSurveyStatus") int intentionSurveyStatus) throws Exception;

    /**
     * 根据专业层次和专业编码查询专业
     * @param majorQualification
     * @param majorCode
     * @return
     * @throws Exception
     */
    public String queryMajorByQualificationAndCode(@Param("majorQualification") String majorQualification,
                                                   @Param("majorCode") String majorCode) throws Exception;

    /**
     *
     * @param id
     * @return
     */
    public List<Integer> queryProtocolTypeById(@Param("id") int id);

    /**
     * 根据学号查询所属学院
     * @param studentNumber
     * @return
     * @throws Exception
     */
    public List<String> listCollegesByStuNumber(@Param("studentNumber")String studentNumber) throws Exception;
}
