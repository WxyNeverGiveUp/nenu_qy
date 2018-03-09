package com.pandawork.nenu.oa.service.data;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.data.EmploymentRateDTO;
import com.pandawork.nenu.oa.common.dto.data.EmploymentStructureDTO;
import com.pandawork.nenu.oa.common.dto.data.StudentStructureDTO;
import com.pandawork.nenu.oa.common.entity.data.StudentInfomation;

/**
 * 数据统计服务
 * user: lishicao
 * date 15/4/22
 * time 上午10:12
 */
public interface InformationStatisticService {

    public void add( StudentInfomation studentInfomation ) throws SSException;
    public void addFromNation(StudentInfomation studentInfomation) throws SSException;
    public void update( StudentInfomation studentInfomation ) throws SSException;
    public void updateFromNation( StudentInfomation studentInfomation ) throws SSException;
    public void delete( StudentInfomation studentInfomation ) throws SSException;
    public void delete( String examId ) throws SSException;


    //生源结构
    public StudentStructureDTO getStudentStructure( String dimension1 , String dimension2 , String college , String qualification , int start , int end ) throws SSException;
    //就业率
    public EmploymentRateDTO getEmploymentRate( String dimension1 , String dimension2 , String college , String qualification , int start , int end ) throws SSException ;
    //拟就业率
    public EmploymentRateDTO getPlanEmploymentRate( String dimension1 , String dimension2 , String college , String qualification , int start , int end ) throws SSException ;
    //就业结构
    public EmploymentStructureDTO getEmployStructure( String dimension1 , String dimension2 , String college , String qualification , int start , int end ) throws SSException;
}
