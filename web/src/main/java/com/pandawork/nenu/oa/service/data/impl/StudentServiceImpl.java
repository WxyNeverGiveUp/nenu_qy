package com.pandawork.nenu.oa.service.data.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Pagination;
import com.pandawork.core.framework.dao.CommonDao;
import com.pandawork.nenu.oa.common.dto.data.StudentQueryDto;
import com.pandawork.nenu.oa.common.entity.data.MajorCode;
import com.pandawork.nenu.oa.common.entity.data.StudentInfomation;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.data.StudentMapper;
import com.pandawork.nenu.oa.service.data.InformationStatisticService;
import com.pandawork.nenu.oa.service.data.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hao Zhang on 2015/4/13.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    CommonDao commonDao;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    InformationStatisticService informationStatisticService;

    @Override
    public void addStudent(StudentInfomation student) throws SSException {
        try {
            StudentInfomation studentInfomation = studentMapper.getStudentByExamId(student.getExamId());
            if( studentInfomation != null ) {
                throw SSException.get(OaException.StudentExist) ;
            }
            else {
                commonDao.insert(student);
            }
        }catch (Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException,e);
        }
    }

    @Override
    public void deleteStudent(int id) throws SSException {
        try {
            commonDao.deleteById(StudentInfomation.class,id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException,e);
        }
    }

    @Override
    public void updateStudent(StudentInfomation student) throws SSException {
        try {
            commonDao.update(student);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException,e);
        }
    }

    @Override
    public List<StudentInfomation> listStudents() throws Exception {

        return studentMapper.getStudentInfomation();
    }

    @Override
    public StudentInfomation getStudentById(int id) throws SSException{
        try {
            return commonDao.queryById(StudentInfomation.class, id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException);
        }
    }

    public boolean studentExist( StudentInfomation studentInfomation ) throws SSException{
        try {
            if ( studentMapper.getStudentByExamId(studentInfomation.getExamId()) != null ) return true;
            else return false;
        }
        catch (Exception  ee ){
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    @Override
    public List<StudentInfomation> listS(){
        try {
            return studentMapper.getStudentInfomation();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentInfomation> selectStuByCondition (StudentQueryDto studentInfomation, Pagination page) throws SSException{
        try {
            int pCurrent = 0, pSize = 10;
            if (page != null) {
                pCurrent = page.getCurrentFristPosition();
                pSize = page.getPageSize();
                studentInfomation.setpCurrent(pCurrent);
                studentInfomation.setpSize(pSize);
            }
            return studentMapper.selectStuByCondition(studentInfomation);
        }
        catch (Exception ee ) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }

    @Override
    public List<StudentInfomation> selectStudentByCondition(StudentQueryDto studentInfomation) throws SSException{
        try {
            return studentMapper.selectStudentByCondition(studentInfomation);
        }
        catch (Exception ee ) {
            LogClerk.errLog.error(ee);
            throw SSException.get(OaException.SystemException);
        }
    }


    @Override
    public int countByCondition(StudentQueryDto studentInfomation) throws SSException {
        try {
            return studentMapper.countStuByCondition(studentInfomation);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException);
        }
    }

    @Override
    public StudentInfomation selectStuByid(Integer id) throws SSException {
        try {
            return studentMapper.selectStuByid(id);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException);
        }
    }

    @Override
    public List<MajorCode> getMajorByCollegeName(String collegeName,String qualification) throws SSException {
        try {
            return studentMapper.getMajorByCollegeName(collegeName, qualification);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException);
        }
    }

    @Override
    public String getMajorNameByMajorId(String majorId) throws SSException
    {
        try {
            return studentMapper.getMajorNameByMajorId(majorId);
        } catch (Exception e) {
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.SystemException);
        }
    }
}
