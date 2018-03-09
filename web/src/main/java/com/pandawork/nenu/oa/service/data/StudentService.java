package com.pandawork.nenu.oa.service.data;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.nenu.oa.common.dto.data.StudentQueryDto;
import com.pandawork.nenu.oa.common.entity.data.MajorCode;
import com.pandawork.nenu.oa.common.entity.data.StudentInfomation;

import java.util.List;

/**
 * Created by Hao Zhang on 2015/4/13.
 * Add, delete, list, update, query by id for t_stu_info table
 */
public interface StudentService {
    /**
     * Add a new student
     *
     * @param student
     * @throws SSException
     */
    public void addStudent(StudentInfomation student) throws SSException;

    /**
     * Delete a student by id.
     *
     * @param id
     * @throws SSException
     */
    public void deleteStudent(int id) throws SSException;

    /**
     * Update a student.
     *
     * @param student
     * @throws SSException
     */
    public void updateStudent(StudentInfomation student) throws SSException;

    /**
     * List All students.
     *
     * @return
     * @throws Exception
     */
    public List<StudentInfomation> listStudents() throws Exception;

    /**
     * Get a student by id
     *
     * @param id
     * @return
     * @throws Exception
     */
    public StudentInfomation getStudentById(int id) throws SSException;

    public boolean studentExist(StudentInfomation studentInfomation) throws SSException;

    public List<StudentInfomation> listS();

    public List<StudentInfomation> selectStuByCondition(StudentQueryDto studentInfomation, Pagination page) throws SSException;

    public List<StudentInfomation> selectStudentByCondition(StudentQueryDto studentInfomation) throws SSException;

    public int countByCondition(StudentQueryDto studentInfomation) throws SSException;

    public StudentInfomation selectStuByid(Integer id) throws SSException;

    public List<MajorCode> getMajorByCollegeName(String collegeName, String qualification) throws SSException;

    public String getMajorNameByMajorId(String majorId) throws SSException;
}
