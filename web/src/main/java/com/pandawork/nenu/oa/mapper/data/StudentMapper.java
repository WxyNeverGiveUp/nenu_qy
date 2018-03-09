package com.pandawork.nenu.oa.mapper.data;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.data.StudentQueryDto;
import com.pandawork.nenu.oa.common.entity.data.MajorCode;
import com.pandawork.nenu.oa.common.entity.data.StudentInfomation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * user: lishicao
 * date 15/4/9
 * time 下午10:04
 * @see ../jyoaCompany.mapper.xml
 */
public interface StudentMapper {
    /**
     * 取得学生列表
     * @return 学生列表
     * @throws Exception
     */
    public List<StudentInfomation> getStudentInfomation() throws Exception;

    public List<StudentInfomation> getStudentInfomationByYear( String year) throws Exception;

    /**
     * 插入学生信息
     * @param studentInfomation
     * @throws Exception
     */
    public void insertStudentInfomation( StudentInfomation studentInfomation ) throws Exception;

    /**
     * 删除
     * @param studentId
     * @throws Exception
     */
    public void delete( int studentId ) throws Exception;

    /**
     * 查询
     * @param examId
     * @return
     * @throws Exception
     */
    //public StudentInfomation getStudentByStudentId( long studentId ) throws Exception;

    public StudentInfomation getStudentByExamId( String examId ) throws Exception;

    /**
     * 条件查询 带分页
     * @param studentInfomation
     * @return
     */
    public List<StudentInfomation> selectStuByCondition(StudentQueryDto studentInfomation)throws Exception;

    /**
     * 无分页
     * @param studentInfomation
     * @return
     */
    public List<StudentInfomation> selectStudentByCondition( StudentQueryDto studentInfomation) throws Exception;

    public int countStuByCondition(StudentQueryDto studentInfomation) throws Exception;

    public StudentInfomation selectStuByid(Integer id) throws Exception;

    public List<MajorCode> getMajorByCollegeName(@Param("collegeName")String collegeName, @Param("qualification")String qualification)throws Exception;

    public String getMajorNameByMajorId(String majorId) throws Exception;
}
