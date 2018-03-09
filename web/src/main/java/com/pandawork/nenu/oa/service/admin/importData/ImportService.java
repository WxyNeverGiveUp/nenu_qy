package com.pandawork.nenu.oa.service.admin.importData;

import com.pandawork.core.common.exception.SSException;

import java.io.File;

/**
 * ImportService
 *
 * @author wlm
 * @date 2016/9/7 16:31
 */
public interface ImportService {

    /**
     * 检查传入的excel是否符合要求
     *
     * @param studentExcel
     * @return
     * @throws SSException
     */
    public boolean checkStuStatusInfo(File studentExcel) throws SSException;

    /**
     * 导入学籍信息
     *
     * @param studentExcel
     * @throws SSException
     */
    public void importStuStatusInfo(File studentExcel) throws SSException;

    /**
     * 检查传入的excel是否符合要求
     *
     * @param dispatchExcel
     * @return
     * @throws SSException
     */
    public boolean checkDispatchInfo(File dispatchExcel) throws SSException;

    /**
     * 导入派遣数据
     *
     * @param dispatchExcel
     * @throws SSException
     */
    public void importDispatchInfo(File dispatchExcel) throws SSException;

    /**
     * 检查传入的excel是否符合要求
     *
     * @param reportCardExcel
     * @return
     * @throws SSException
     */
    public boolean checkReportCard(File reportCardExcel) throws SSException;

    /**
     * 导入报到证信息
     *
     * @param reportCardExcel
     * @throws SSException
     */
    public void importReportCard(File reportCardExcel) throws SSException;
}
