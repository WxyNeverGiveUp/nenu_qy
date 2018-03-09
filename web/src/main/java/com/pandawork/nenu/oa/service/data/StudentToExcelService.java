package com.pandawork.nenu.oa.service.data;

import com.pandawork.core.common.exception.SSException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;

/**
 * 学生信息的EXCEL导入数据、导出数据
 * user: lishicao
 * date 15/4/7
 * time 下午9:44
 */
public interface StudentToExcelService {

    /**
     * 导出学生数据，导出上传国家的EXCEL
     *
     * @return 学生信息EXCEL
     */
    public XSSFWorkbook getStudentInfomation(Integer year) throws SSException;

    /**
     * 传入从学信网导入的学生数据的EXCEL，导入数据库
     *
     * @param studentExcel 传入学生信息EXCEL
     */
    public void setStudentInfomation(File studentExcel) throws SSException;

    /**
     * 传入从国家系统导入的EXCEL，导入数据库
     *
     * @param studentExcel
     * @throws SSException
     */
    public void setStudentInfomationFormNationSystem(File studentExcel) throws SSException;

    /**
     * 补全从国家系统导出的EXCEL中的学号缺失
     *
     * @param studentExcel
     * @throws SSException
     */
    public void completeStudentId(File studentExcel) throws SSException;

    /**
     * 传入EXCEL，判断是否符合符合要求
     *
     * @param studentExcel
     * @return
     */
    public boolean varifyStudentExcel(File studentExcel) throws SSException;

    /**
     * 传入从国家系统导出的EXCEL，用于2015级学生的东西
     *
     * @param studentExcel
     * @return
     * @throws SSException
     */
    public boolean varifyNationExcel(File studentExcel) throws SSException;
}
