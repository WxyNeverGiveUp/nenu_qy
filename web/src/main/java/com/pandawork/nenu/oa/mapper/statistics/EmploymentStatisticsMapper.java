package com.pandawork.nenu.oa.mapper.statistics;

import com.pandawork.nenu.oa.common.dto.statistics.EmploymentInfoExportDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EmploymentStatisticsMapper
 * 实时就业率统计
 *
 * @author Lw
 * @time 2017/8/9 15:42
 */
public interface EmploymentStatisticsMapper {

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
    public int countAboutQualification(@Param("whereaboutgoId") int whereaboutgoId,
                                       @Param("collegeCode") String collegeCode,
                                       @Param("year") int year,
                                       @Param("qualificationCode") int qualificationCode);



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
    public int countAboutNormal(@Param("whereaboutgoId") int whereaboutgoId,
                                @Param("collegeCode") String collegeCode,
                                @Param("year") int year,
                                @Param("normalStuCode") int normalStuCode);

    /**
     * 查询本科生/研究生总计数量
     * @param collegeCode
     * @param year
     * @param qualificationCode
     * @return
     */
    public int countAllAboutQualification(@Param("collegeCode") String collegeCode,
                                          @Param("year") int year,
                                          @Param("qualificationCode") int qualificationCode);

    /**
     * 根据师范生类型查询总计数量
     * @param collegeCode
     * @param year
     * @param normalStuCode
     * @return
     */
    public int countAllAboutNormal(@Param("collegeCode") String collegeCode,
                                   @Param("year") int year,
                                   @Param("normalStuCode") int normalStuCode);

    /**
     * 就业信息导出
     * @return
     */
    public List<EmploymentInfoExportDto> listEmploymentInfo() throws Exception;


    /**
     * 查询：包括免师、包括委培、包括免师和委培
     * @param code  1-免师，2-委培，3-免师和委培
     * @param year
     * @param collegeCode
     * @return
     */
    public int countAboutNormalAndWeipei(@Param("code") int code,
                                         @Param("year") int year,
                                         @Param("collegeCode") String collegeCode);



    /**
     * 查询免师的人数
     * 本科生/研究生
     * @return
     */
    public int countFreeNormal(@Param("year") int year,
                               @Param("collegeCode") String collegeCode);

    /**
     * 查询委培的人数
     * @param year
     * @param collegeCode
     * @return
     */
    public int countWeipei(@Param("year") int year,
                           @Param("collegeCode") String collegeCode);

    /**
     * 免师或委培的人数
     * @param year
     * @param collegeCode
     * @return
     */
    public int countFreeNormalOrWeipei(@Param("year") int year,
                                       @Param("collegeCode") String collegeCode);

/********************************************************************** Add In 2017/11/23 ***************************************************************************/

    /**
     * 就业人数(包括免师、关于qualificationCode)
     * @param year  毕业年份
     * @param collegeCode   学院代码
     * @return 就业人数(包括免师)
     */
    public int countEmploymentIncludeFreeNormal(@Param("year") Integer year,
                                                @Param("collegeCode") String collegeCode,
                                                @Param("qualificationCode") int qualificationCode);

    /**
     * 就业人数(包括免师、关于normalStuCode)
     * @param year  毕业年份
     * @param collegeCode   学院代码
     * @return 就业人数(包括免师)
     */
//    public int countEmploymentIncludeFreeNormalAboutNormal(@Param("year") Integer year,
//                                                           @Param("collegeCode") String collegeCode,
//                                                           @Param("normalStuCode") int normalStuCode);

    /**
     * 就业人数(包括委培、关于qualificationCode)
     * @param year  毕业年份
     * @param collegeCode   学院代码
     * @return 就业人数(包括委培)
     */
    public int countEmploymentIncludeWeipei(@Param("year") Integer year,
                                            @Param("collegeCode") String collegeCode,
                                            @Param("qualificationCode") int qualificationCode);

    /**
     * 就业人数(包括委培、关于normalStuCode)
     * @param year  毕业年份
     * @param collegeCode   学院代码
     * @return 就业人数(包括委培)
     */
//    public int countEmploymentIncludeWeipeiAboutNormal(@Param("year") Integer year,
//                                                       @Param("collegeCode") String collegeCode,
//                                                       @Param("normalStuCode") int normalStuCode);

    /**
     * 就业人数(包括免师和委培、关于qualificationCode)
     * @param year  毕业年份
     * @param collegeCode   学院代码
     * @return 就业人数(包括免师和委培)
     */
    public int countEmploymentIncludeFreeNormalAndWeipei(@Param("year") Integer year,
                                                         @Param("collegeCode") String collegeCode,
                                                         @Param("qualificationCode") int qualificationCode);

    /**
     * 就业人数(包括免师和委培、关于normalStuCode)
     * @param year  毕业年份
     * @param collegeCode   学院代码
     * @return 就业人数(包括免师和委培)
     */
//    public int countEmploymentIncludeFreeNormalAndWeipeiAboutNormal(@Param("year") Integer year,
//                                                                    @Param("collegeCode") String collegeCode,
//                                                                    @Param("normalStuCode") int normalStuCode);

/********************************************************************** Add In 2017/11/23 ***********************************************************************/

}
