package com.pandawork.nenu.oa.service.statistics;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.nenu.oa.common.dto.statistics.EmploymentInfoExportDto;
import com.pandawork.nenu.oa.common.dto.statistics.EmploymentInfoParsedExportDto;
import com.pandawork.nenu.oa.common.dto.statistics.EmploymentStatisticsInfoDto;

import java.util.List;

/**
 * 实时就业率统计Servive接口
 * @author Lw
 * @time 2017/8/9 15:59
 */
public interface EmploymentStatisticsService {

    /**
     * 本科生/研究生
     * 查询毕业年份为year、学院代码为collegeCode、
       毕业去向代码为whereaboutgoId的学生的数量
     * @param whereaboutgoId 毕业去向代码
     * @param collegeCode 学院代码
     * @param year 毕业年份
     * @param qualificationCode 专业层次  1-本科生  2-研究生
     * @return 学生的数量
     */
//    public int countAboutQualification(int whereaboutgoId, String collegeCode, int year, int qualificationCode);


    /**
     * 师范生类型
     * 查询毕业年份为year、学院代码为collegeCode、
     毕业去向代码为whereaboutgoId的学生的数量
     * @param whereaboutgoId 毕业去向代码
     * @param collegeCode 学院代码
     * @param year 毕业年份
     * @param normalStuCode 师范生类别 1-普通师范生 12-免费师范生 2-非师范生
     * @return 学生的数量
     */
//    public int countAboutNormal(int whereaboutgoId, String collegeCode, int year, int normalStuCode);

    /**
     * 根据专业层次获取表中的数据信息
     * @param collgeCode
     * @param year
     * @param qualificationCode
     * @return
     */
    public EmploymentStatisticsInfoDto getEmploymentStatisticsInfoAboutQualification(String collgeCode, int year, int qualificationCode);


    /**
     * 根据师范生类别获取表中数据信息
     * @param collegeCode
     * @param year
     * @param normalType
     * @return
     */
    public EmploymentStatisticsInfoDto getEmploymentStatisticsInfoAboutNormalType(String collegeCode, int year, int normalType);


    /**
     * 获取总计信息
     * @param employmentStatisticsInfoDtoList
     * @return
     */
    public EmploymentStatisticsInfoDto getAllInfo(List<EmploymentStatisticsInfoDto> employmentStatisticsInfoDtoList);

    /**
     * 就业信息导出
     * @return
     */
    public List<EmploymentInfoParsedExportDto> listEmploymentInfo() throws SSException;
}
