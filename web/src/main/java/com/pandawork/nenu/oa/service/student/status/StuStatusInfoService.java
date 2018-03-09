package com.pandawork.nenu.oa.service.student.status;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.core.framework.web.spring.fileupload.PandaworkMultipartFile;
import com.pandawork.nenu.oa.common.dto.student.status.StatusInfoListDto;
import com.pandawork.nenu.oa.common.dto.student.status.StuStatusInfoDto;
import com.pandawork.nenu.oa.common.dto.student.status.StudentExportDto;
import com.pandawork.nenu.oa.common.entity.general.Material;
import com.pandawork.nenu.oa.common.entity.student.status.StuStatusInfo;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * StuStatusInfoService
 * 学籍信息Service
 *
 * @author wlm
 * @date 2016/7/14 10:19
 */
public interface StuStatusInfoService {

    /**
     * 添加学籍信息
     *
     * @param stuStatusInfo
     * @return
     * @throws SSException
     */
    public StuStatusInfo newStuStatusInfo(StuStatusInfo stuStatusInfo) throws SSException;

    /**
     * 根据id修改学籍信息
     *
     * @param stuStatusInfo
     * @throws SSException
     */
    public void updateById(StuStatusInfo stuStatusInfo) throws SSException;

    /**
     * 根据学号修改审核状
     * （用以当学生提交或修改学籍信息时）
     *
     * @param stuNumber
     * @throws SSException
     */
    public void updateCheckStatusByStuNumber(String stuNumber) throws SSException;

    /**
     * 根据id查询学籍信息
     *
     * @param id
     * @return
     * @throws SSException
     */
    public StuStatusInfo queryById(int id) throws SSException;

    /**
     * 根据id查询学籍dto
     *
     * @param id
     * @return
     * @throws SSException
     */
    public StuStatusInfoDto queryDtoById(int id) throws SSException;

    /**
     * 根据身份证号查询学籍信息
     *
     * @param idNumber
     * @return
     * @throws SSException
     */
    public StuStatusInfo queryByIdNumber(String idNumber) throws SSException;

    /**
     * 根据考生号查询学籍信息
     *
     * @param candidateNumber
     * @return
     * @throws SSException
     */
    public StuStatusInfo queryByCandidateNumber(String candidateNumber) throws SSException;

    /**
     * 根据学号查询学籍id
     *
     * @param stuNumber
     * @return
     * @throws SSException
     */
    public Integer queryIdByStuNumber(String stuNumber) throws SSException;

    /**
     * 根据学号查询学籍信息
     *
     * @param stuNumber
     * @return
     * @throws SSException
     */
    public StuStatusInfo queryByStuNumber(String stuNumber) throws SSException;

    /**
     * 上传证明材料
     *
     * @return
     * @throws SSException
     */
    public String uploadMaterial(PandaworkMultipartFile image, Material material) throws SSException;

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
     * @param keyword
     * @param sex
     * @param politicalStand
     * @param colleges
     * @param majors
     * @param qualificationNow
     * @param nation
     * @param showUncommitted
     * @param isRegistered
     * @return
     * @throws SSException
     */
    public int countByCondition(int stuType, int college,
                                int normalStu, int counsellorCheckResult,
                                int deputySecretaryCheckResult, int schoolCheckResult,
                                int province, String qualification, int trainingMode,
                                String keyword, int sex, int politicalStand, double stuLength,
                                List<String> colleges, String majorCode, String majorQualification, List<String> majors, int qualificationNow, String nation,
                                int showUncommitted, int isRegistered) throws SSException ;

    /**
     * 分页查询符合条件的学籍信息
     * @param stuType
     * @param college
     * @param normalStu
     * @param counsellorCheckResult
     * @param deputySecretaryCheckResult
     * @param schoolCheckResult
     * @param province
     * @param qualification
     * @param trainingMode
     * @param keyword
     * @param sex
     * @param politicalStand
     * @param page
     * @param colleges                 辅导员或副书记所属学院，隐式传递辅导员管理专业，隐式传递
     * @param majors
     * @param qualificationNow
     * @param nation
     * @param showUncommitted
     * @param isRegistered
     * @return
     * @throws SSException
     */
        public List<StatusInfoListDto> listByCondition(int stuType, int college, int normalStu,
                                                       int counsellorCheckResult, int deputySecretaryCheckResult,
                                                       int schoolCheckResult, int province, String qualification,
                                                       int trainingMode, String keyword, int sex, int politicalStand, double stuLength,
                                                       Pagination page, List<String> colleges, String majorCode, String majorQualification, List<String> majors,
                                                       int qualificationNow, String nation,
                                                       int showUncommitted, int isRegistered) throws SSException ;

    /**
     * 分页查询符合条件的学籍idList
     * @param stuType
     * @param college
     * @param normalStu
     * @param counsellorCheckResult
     * @param deputySecretaryCheckResult
     * @param schoolCheckResult
     * @param province
     * @param qualification
     * @param trainingMode
     * @param keyword
     * @param sex
     * @param politicalStand
     * @param colleges
     * @param majors                  辅导员或副书记所属学院，隐式传递
     * @param qualificationNow
     * @param nation               辅导员管理专业，隐式传递
     * @param showUncommitted
     * @param page
     * @param isRegistered
     * @return
     * @throws SSException
     */
    public List<Integer> listIdByCondition(int stuType, int college,
                                                   int normalStu, int counsellorCheckResult,
                                                   int deputySecretaryCheckResult, int schoolCheckResult,
                                                   int province, String qualification, int trainingMode,
                                                   String keyword,
                                                   int sex, int politicalStand, double stuLength,
                                                   List<String> colleges, String majorCode,String majorQualification, List<String> majors,
                                                   int qualificationNow, String nation,
                                                   int showUncommitted, Pagination page, int isRegistered) throws SSException ;

    /**
     * 管理员修改学籍信息
     *
     * @param stuStatusInfo
     * @throws SSException
     */
    public void adminUpdateById(StuStatusInfo stuStatusInfo) throws SSException, IOException;

    /**
     * 判断师范生类别、生源所在地、培养方式、定向委培单位材料是否充足
     * @param stuStatusInfo
     * @return
     * @throws SSException
     * @throws IOException
     */
    public boolean materialUpdateById(StuStatusInfo stuStatusInfo) throws SSException, IOException;

    /**
     * 查询符合条件的学籍信息数量
     *
     * @param college
     * @param province
     * @param colleges
     * @param majors
     * @param qualificationNow
     * @param nation
     * @param showUncommitted
     * @return
     * @throws SSException
     */
    public int countMinorityByCondition(int college, int province, List<String> colleges, List<String> majors,
                                        int qualificationNow, String nation, int showUncommitted) throws SSException;

    /**
     * 分页查询符合条件的学籍信息
     *
     * @param college
     * @param province
     * @param page
     * @param colleges
     * @param majors
     * @param qualificationNow
     * @param nation
     * @param showUncommitted
     * @return
     * @throws SSException
     */
    public List<StatusInfoListDto> listMinorityByCondition(int college, int province, Pagination page, List<String> colleges,
                                                           List<String> majors, int qualificationNow, String nation,
                                                           int showUncommitted) throws SSException;

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
     * @param page
     * @return
     * @throws SSException
     */
    public List<Integer> listMinorityIdByCondition(int college, int province, List<String> colleges, List<String> majors,
                                                   int qualificationNow, String nation, int showUncommitted,
                                                   Pagination page) throws SSException;

    /**
     * 查询要导出的学生信息
     *
     * @return
     * @throws SSException
     * @param grade
     */
    public List<StudentExportDto> listAllExportInfo(int grade) throws SSException;

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
     * @param keyword
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
     * @throws SSException
     */
    public List<StudentExportDto> listExportInfoByCondition(int stuType, int college, int normalStu,
                                                            int counsellorCheckResult, int deputySecretaryCheckResult,
                                                            int schoolCheckResult, int province, String qualification,
                                                            int trainingMode, String keyword, int sex, int politicalStand,
                                                            List<String> colleges, String major,String majorQualification, List<String> majors,
                                                            int qualificationNow, String nation, double stuLength,
                                                            int showUncommitted, int isRegistered) throws SSException ;

    /**
     * 根据学籍id删除学生所有信息
     * @param statusInfoId
     * @throws SSException
     * @author chenyuting
     */
    public void deleteStudentInfo(int statusInfoId) throws SSException;

    /**
     * 根据id删除学籍表、学籍修改表数据
     * @param statusInfoId
     * @throws SSException
     * @author chenyuting
     */
    public void deleteStudentStatusInfo(int statusInfoId) throws SSException;

    /**
     * 副书记批量审核
     * @param id
     * @param date
     * @param realName
     * @return
     * @throws SSException
     */
    public boolean batchAudit(List<Integer> id, Date date, String realName) throws SSException;

    /**
     * 根据学籍id修改填写就业意向调查状态
     *
     * @param statusInfoId
     * @param intentionSurveyStatus
     * @throws SSException
     */
    public void updateIntentionSurveyStatus(int statusInfoId,int intentionSurveyStatus)throws SSException;

    /**
     * 根据专业层次和专业代码查询专业
     * @param majorQualification
     * @param majorCode
     * @return
     * @throws SSException
     */
    public String queryMajorByQualificationAndCode(String majorQualification,String majorCode)throws SSException;

    /**
     * 根据学号查询所属学院
     * @param studentNumber
     * @return
     * @throws SSException
     */
    public List<String> listCollegesByStuNumber(String studentNumber) throws SSException;
}
