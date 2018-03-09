package com.pandawork.nenu.oa.service.statistics.impl;

import com.pandawork.core.common.exception.SSException;
import com.pandawork.core.common.log.LogClerk;
import com.pandawork.core.common.util.Assert;
import com.pandawork.nenu.oa.common.dto.statistics.EmploymentInfoExportDto;
import com.pandawork.nenu.oa.common.dto.statistics.EmploymentInfoParsedExportDto;
import com.pandawork.nenu.oa.common.dto.statistics.EmploymentStatisticsInfoDto;
import com.pandawork.nenu.oa.common.enums.statistics.*;
import com.pandawork.nenu.oa.common.exception.OaException;
import com.pandawork.nenu.oa.mapper.general.CityPropertyMapper;
import com.pandawork.nenu.oa.mapper.general.ProvincePropertyMapper;
import com.pandawork.nenu.oa.mapper.statistics.EmploymentStatisticsMapper;
import com.pandawork.nenu.oa.service.statistics.EmploymentStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * 实时就业率统计Servive实现类
 * @author Lw
 * @time 2017/8/9 16:03
 */
@Service("employmentStaticsService")
public class EmploymentStatisticsServiceImpl implements EmploymentStatisticsService {

    @Autowired
    EmploymentStatisticsMapper employmentStatisticsMapper;

    @Autowired
    CityPropertyMapper cityPropertyMapper;

    @Autowired
    ProvincePropertyMapper provincePropertyMapper;

    @Override
    public EmploymentStatisticsInfoDto getEmploymentStatisticsInfoAboutQualification(String collegeCode, int year, int qualificationCode) {

        EmploymentStatisticsInfoDto employmentStatisticsInfoDto = new EmploymentStatisticsInfoDto();

        //总计人数
        int countAll = employmentStatisticsMapper.countAllAboutQualification(collegeCode, year, qualificationCode);

        if(countAll == 0) {
            employmentStatisticsInfoDto.setCountColumn1("-");
            employmentStatisticsInfoDto.setCountColumn2("-");
            employmentStatisticsInfoDto.setCountColumn3("-");
            employmentStatisticsInfoDto.setCountColumn4("-");
            employmentStatisticsInfoDto.setCountColumn5("-");
            employmentStatisticsInfoDto.setCountColumn6("-");
            employmentStatisticsInfoDto.setCountColumn7("-");
            employmentStatisticsInfoDto.setCountColumn8("-");
            employmentStatisticsInfoDto.setCountColumn9("-");
            employmentStatisticsInfoDto.setCountColumn10("-");
            employmentStatisticsInfoDto.setCountColumn11("-");
            employmentStatisticsInfoDto.setCountColumn12("-");
            employmentStatisticsInfoDto.setCountEmployment("-");
            employmentStatisticsInfoDto.setCountAll("-");
            employmentStatisticsInfoDto.setStatisticsColumn1("-");
            employmentStatisticsInfoDto.setStatisticsColumn2("-");
            employmentStatisticsInfoDto.setStatisticsColumn3("-");
            employmentStatisticsInfoDto.setStatisticsColumn4("-");
            employmentStatisticsInfoDto.setStatisticsColumn5("-");
            employmentStatisticsInfoDto.setStatisticsColumn6("-");
            employmentStatisticsInfoDto.setStatisticsColumn7("-");
            employmentStatisticsInfoDto.setStatisticsColumn8("-");
            employmentStatisticsInfoDto.setStatisticsColumn9("-");
            employmentStatisticsInfoDto.setStatisticsColumn10("-");
            employmentStatisticsInfoDto.setStatisticsColumn11("-");
            employmentStatisticsInfoDto.setStatisticsColumn12("-");
            employmentStatisticsInfoDto.setStatisticsEmploymentNoClever("-");
            employmentStatisticsInfoDto.setStatisticsEmployment("-");
            employmentStatisticsInfoDto.setStatisticsWithNormal("-");
            employmentStatisticsInfoDto.setStatisticsWithWeipei("-");
            employmentStatisticsInfoDto.setStatisticsWithNormalAndWeipei("-");

            return employmentStatisticsInfoDto;

        } else {

            //签就业协议形式就业人数
            int count1 = employmentStatisticsMapper.countAboutQualification(10, collegeCode, year, qualificationCode);
            //签劳动合同形式就业人数
            int count2 = employmentStatisticsMapper.countAboutQualification(11, collegeCode, year, qualificationCode);
            //其他录用形式就业人数
            int count3 = employmentStatisticsMapper.countAboutQualification(12, collegeCode, year, qualificationCode);
            //升学人数
            int count4 = employmentStatisticsMapper.countAboutQualification(71, collegeCode, year, qualificationCode)
                    + employmentStatisticsMapper.countAboutQualification(80, collegeCode, year, qualificationCode);
            //出国（境）人数
            int count5 = employmentStatisticsMapper.countAboutQualification(85, collegeCode, year, qualificationCode);
            //科研助理人数
            int count6 = employmentStatisticsMapper.countAboutQualification(27, collegeCode, year, qualificationCode);
            //应征义务兵人数
            int count7 = employmentStatisticsMapper.countAboutQualification(46, collegeCode, year, qualificationCode);
            //国家基层项目人数
            int count8 = employmentStatisticsMapper.countAboutQualification(50, collegeCode, year, qualificationCode);
            //地方基层项目人数
            int count9 = employmentStatisticsMapper.countAboutQualification(51, collegeCode, year, qualificationCode);
            //自由职业人数
            int count10 = employmentStatisticsMapper.countAboutQualification(76, collegeCode, year, qualificationCode);
            //自主创业人数
            int count11 = employmentStatisticsMapper.countAboutQualification(75, collegeCode, year, qualificationCode);

            // 保留两位小数
            DecimalFormat df = new DecimalFormat("0.00");

            //就业人数
            int countEmployment = count1 + count2 + count3 + count4 + count5 + count6 + count7 + count8 + count9 + count10 + count11;

            //未就业人数是总人数减掉未就业人数
            int count12 = countAll - countEmployment;

            //就业人数（包括免师）
//            int countWithNormal = employmentStatisticsMapper.countFreeNormal(year, collegeCode) + employmentStatisticsMapper.countAboutNormalAndWeipei(1, year, collegeCode);
            int countWithNormal = employmentStatisticsMapper.countEmploymentIncludeFreeNormal(year, collegeCode, qualificationCode);

            //就业人数（包括委培）
//            int countWithWeipei = employmentStatisticsMapper.countWeipei(year, collegeCode) + employmentStatisticsMapper.countAboutNormalAndWeipei(2, year, collegeCode);
            int countWithWeipei = employmentStatisticsMapper.countEmploymentIncludeWeipei(year, collegeCode, qualificationCode);

            //就业人数（包括免师和委培）
//            int countWithNormalAndWeipei = employmentStatisticsMapper.countFreeNormalOrWeipei(year, collegeCode) + employmentStatisticsMapper.countAboutNormalAndWeipei(3, year, collegeCode);
            int countWithNormalAndWeipei = employmentStatisticsMapper.countEmploymentIncludeFreeNormalAndWeipei(year, collegeCode, qualificationCode);

            employmentStatisticsInfoDto.setCountWithNormal(countWithNormal);
            employmentStatisticsInfoDto.setCountWithWeipei(countWithWeipei);
            employmentStatisticsInfoDto.setCountWithNormalAndWeipei(countWithNormalAndWeipei);

            //计算比率，将其四舍五入保留两位小数并乘上100化为百分数
            //签就业协议形式就业比例
            String statistic1 = df.format(((double) count1 / countAll) * 100);

            //签劳动合同形式就业比例
            String statistic2 = df.format(((double) count2 / countAll) * 100);

            //其他录用形式就业比例
            String statistic3 = df.format(((double) count3 / countAll) * 100);

            //升学比例
            String statistic4 = df.format(((double) count4 / countAll) * 100);

            //出国（境）比例
            String statistic5 = df.format(((double) count5 / countAll) * 100);

            //科研助理比例
            String statistic6 = df.format(((double) count6 / countAll) * 100);

            //应征义务兵比例
            String statistic7 = df.format(((double) count7 / countAll) * 100);

            //国家基层项目比例
            String statistic8 = df.format(((double) count8 / countAll) * 100);

            //地方基层项目比例
            String statistic9 = df.format(((double) count9 / countAll) * 100);

            //自由职业比例
            String statistic10 = df.format(((double) count10 / countAll) * 100);

            //自主创业比例
            String statistic11 = df.format(((double) count11 / countAll) * 100);

            //未就业比例
            String statistic12 = df.format(((double) count12 / countAll) * 100);

            //就业率（不含灵活）
            String statisticEmploymentNoClever = df.format(((double) (countEmployment - count11 - count10) / countAll) * 100);

            //就业率
            String statisticEmployment = df.format(((double) countEmployment / countAll) * 100);

            //就业率（包括免师）
            String staticticsWithNormal = df.format(((double) countWithNormal / countAll) * 100);

            //就业率（包括委培）
            String staticticsWithWeipei = df.format(((double) countWithWeipei / countAll) * 100);

            //就业率（包括免师和委培）
            String statisticsWithNormalAndWeipei = df.format(((double) countWithNormalAndWeipei / countAll) * 100);

            //签就业协议形式就业数量
            if (count1 == 0) {
                employmentStatisticsInfoDto.setCountColumn1("");
            } else {
                employmentStatisticsInfoDto.setCountColumn1(count1 + "");
            }

            //签劳动合同形式就业数量
            if (count2 == 0) {
                employmentStatisticsInfoDto.setCountColumn2("");
            } else {
                employmentStatisticsInfoDto.setCountColumn2(count2 + "");
            }

            //其他录用形式就业数量
            if (count3 == 0) {
                employmentStatisticsInfoDto.setCountColumn3("");
            } else {
                employmentStatisticsInfoDto.setCountColumn3(count3 + "");
            }

            //升学数量
            if (count4 == 0) {
                employmentStatisticsInfoDto.setCountColumn4("");
            } else {
                employmentStatisticsInfoDto.setCountColumn4(count4 + "");
            }

            //出国（境）数量
            if (count5 == 0) {
                employmentStatisticsInfoDto.setCountColumn5("");
            } else {
                employmentStatisticsInfoDto.setCountColumn5(count5 + "");
            }

            //科研助理数量
            if (count6 == 0) {
                employmentStatisticsInfoDto.setCountColumn6("");
            } else {
                employmentStatisticsInfoDto.setCountColumn6(count6 + "");
            }

            //应征义务兵数量
            if (count7 == 0) {
                employmentStatisticsInfoDto.setCountColumn7("");
            } else {
                employmentStatisticsInfoDto.setCountColumn7(count7 + "");
            }

            //国家基层项目数量
            if (count8 == 0) {
                employmentStatisticsInfoDto.setCountColumn8("");
            } else {
                employmentStatisticsInfoDto.setCountColumn8(count8 + "");
            }

            //地方基层项目数量
            if (count9 == 0) {
                employmentStatisticsInfoDto.setCountColumn9("");
            } else {
                employmentStatisticsInfoDto.setCountColumn9(count9 + "");
            }

            //自由职业数量
            if (count10 == 0) {
                employmentStatisticsInfoDto.setCountColumn10("");
            } else {
                employmentStatisticsInfoDto.setCountColumn10(count10 + "");
            }

            //自主创业数量
            if (count11 == 0) {
                employmentStatisticsInfoDto.setCountColumn11("");
            } else {
                employmentStatisticsInfoDto.setCountColumn11(count11 + "");
            }

            //未就业数量
            if (count12 == 0) {
                employmentStatisticsInfoDto.setCountColumn12("");
            } else {
                employmentStatisticsInfoDto.setCountColumn12(count12 + "");
            }

            //就业人数
            employmentStatisticsInfoDto.setCountEmployment(countEmployment + "");

            //所有人数
            employmentStatisticsInfoDto.setCountAll(countAll + "");


            //签就业协议形式就业比例
            if (statistic1.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn1("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn1(statistic1 + "%");
            }

            //签劳动合同形式就业比例
            if (statistic2.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn2("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn2(statistic2 + "%");
            }

            //其他录用形式就业比例
            if (statistic3.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn3("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn3(statistic3 + "%");
            }

            //升学比例
            if (statistic4.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn4("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn4(statistic4 + "%");
            }

            //出国（境）比例
            if (statistic5.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn5("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn5(statistic5 + "%");
            }

            //科研助理比例
            if (statistic6.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn6("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn6(statistic6 + "%");
            }

            //应征义务兵比例
            if (statistic7.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn7("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn7(statistic7 + "%");
            }

            //国家基层项目比例
            if (statistic8.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn8("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn8(statistic8 + "%");
            }

            //地方基层项目比例
            if (statistic9.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn9("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn9(statistic9 + "%");
            }

            //自由职业比例
            if (statistic10.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn10("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn10(statistic10 + "%");
            }

            //自主创业比例
            if (statistic11.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn11("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn11(statistic11 + "%");
            }

            //未就业比例
            if (statistic12.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn12("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn12(statistic12 + "%");
            }

            //就业比例（不含灵活）
            employmentStatisticsInfoDto.setStatisticsEmploymentNoClever(statisticEmploymentNoClever + "%");

            //就业比例
            employmentStatisticsInfoDto.setStatisticsEmployment(statisticEmployment + "%");

            //就业率（包括免师）
            employmentStatisticsInfoDto.setStatisticsWithNormal(staticticsWithNormal + "%");

            //就业率（包括委培）
            employmentStatisticsInfoDto.setStatisticsWithWeipei(staticticsWithWeipei + "%");

            //就业率（包括免师和委培）
            employmentStatisticsInfoDto.setStatisticsWithNormalAndWeipei(statisticsWithNormalAndWeipei + "%");
        }
            return employmentStatisticsInfoDto;

    }

    @Override
    public EmploymentStatisticsInfoDto getEmploymentStatisticsInfoAboutNormalType(String collegeCode, int year, int normalType) {

        EmploymentStatisticsInfoDto employmentStatisticsInfoDto = new EmploymentStatisticsInfoDto();

        //总计人数
        int countAll = employmentStatisticsMapper.countAllAboutNormal(collegeCode, year, normalType);

        if(countAll == 0) {
            employmentStatisticsInfoDto.setCountColumn1("-");
            employmentStatisticsInfoDto.setCountColumn2("-");
            employmentStatisticsInfoDto.setCountColumn3("-");
            employmentStatisticsInfoDto.setCountColumn4("-");
            employmentStatisticsInfoDto.setCountColumn5("-");
            employmentStatisticsInfoDto.setCountColumn6("-");
            employmentStatisticsInfoDto.setCountColumn7("-");
            employmentStatisticsInfoDto.setCountColumn8("-");
            employmentStatisticsInfoDto.setCountColumn9("-");
            employmentStatisticsInfoDto.setCountColumn10("-");
            employmentStatisticsInfoDto.setCountColumn11("-");
            employmentStatisticsInfoDto.setCountColumn12("-");
            employmentStatisticsInfoDto.setCountEmployment("-");
            employmentStatisticsInfoDto.setCountAll("-");
            employmentStatisticsInfoDto.setStatisticsColumn1("-");
            employmentStatisticsInfoDto.setStatisticsColumn2("-");
            employmentStatisticsInfoDto.setStatisticsColumn3("-");
            employmentStatisticsInfoDto.setStatisticsColumn4("-");
            employmentStatisticsInfoDto.setStatisticsColumn5("-");
            employmentStatisticsInfoDto.setStatisticsColumn6("-");
            employmentStatisticsInfoDto.setStatisticsColumn7("-");
            employmentStatisticsInfoDto.setStatisticsColumn8("-");
            employmentStatisticsInfoDto.setStatisticsColumn9("-");
            employmentStatisticsInfoDto.setStatisticsColumn10("-");
            employmentStatisticsInfoDto.setStatisticsColumn11("-");
            employmentStatisticsInfoDto.setStatisticsColumn12("-");
            employmentStatisticsInfoDto.setStatisticsEmploymentNoClever("-");
            employmentStatisticsInfoDto.setStatisticsEmployment("-");
            employmentStatisticsInfoDto.setStatisticsWithWeipei("-");
            employmentStatisticsInfoDto.setStatisticsWithNormal("-");
            employmentStatisticsInfoDto.setStatisticsWithNormalAndWeipei("-");

            return employmentStatisticsInfoDto;
        } else {

            //签就业协议形式就业人数
            int count1 = employmentStatisticsMapper.countAboutNormal(10, collegeCode, year, normalType);
            //签劳动合同形式就业人数
            int count2 = employmentStatisticsMapper.countAboutNormal(11, collegeCode, year, normalType);
            //其他录用形式就业人数
            int count3 = employmentStatisticsMapper.countAboutNormal(12, collegeCode, year, normalType);
            //升学人数
            int count4 = employmentStatisticsMapper.countAboutNormal(71, collegeCode, year, normalType)
                    + employmentStatisticsMapper.countAboutNormal(80, collegeCode, year, normalType);
            //出国（境）人数
            int count5 = employmentStatisticsMapper.countAboutNormal(85, collegeCode, year, normalType);
            //科研助理人数
            int count6 = employmentStatisticsMapper.countAboutNormal(27, collegeCode, year, normalType);
            //应征义务兵人数
            int count7 = employmentStatisticsMapper.countAboutNormal(46, collegeCode, year, normalType);
            //国家基层项目人数
            int count8 = employmentStatisticsMapper.countAboutNormal(50, collegeCode, year, normalType);
            //地方基层项目人数
            int count9 = employmentStatisticsMapper.countAboutNormal(51, collegeCode, year, normalType);
            //自由职业人数
            int count10 = employmentStatisticsMapper.countAboutNormal(76, collegeCode, year, normalType);
            //自主创业人数
            int count11 = employmentStatisticsMapper.countAboutNormal(75, collegeCode, year, normalType);

            //就业人数
            int countEmployment = count1 + count2 + count3 + count4 + count5 + count6 + count7 + count8 + count9 + count10 + count11;

            //未就业人数 = 总数 - 就业人数
            int count12 = countAll - countEmployment;

            DecimalFormat df = new DecimalFormat("0.00");

            //计算比率，将其四舍五入保留两位小数并乘上100化为百分数
            //签就业协议形式就业比例
            String statistic1 = df.format(((double) count1 / countAll) * 100);

            //签劳动合同形式就业比例
            String statistic2 = df.format(((double) count2 / countAll) * 100 );

            //其他录用形式就业比例
            String statistic3 = df.format(((double) count3 / countAll) * 100 );

            //升学比例
            String statistic4 = df.format(((double) count4 / countAll) * 100 );

            //出国（境）比例
            String statistic5 = df.format(((double) count5 / countAll) * 100 );

            //科研助理比例
            String statistic6 = df.format(((double) count6 / countAll) * 100 );

            //应征义务兵比例
            String statistic7 = df.format(((double) count7 / countAll) * 100 );

            //国家基层项目比例
            String statistic8 = df.format(((double) count8 / countAll) * 100 );

            //地方基层项目比例
            String statistic9 = df.format(((double) count9 / countAll) * 100 );

            //自由职业比例
            String statistic10 = df.format(((double) count10 / countAll) * 100 );

            //自主创业比例
            String statistic11 = df.format(((double) count11 / countAll) * 100 );

            //未就业比例
            String statistic12 = df.format(((double) count12 / countAll) * 100 );

            //就业率（不含灵活）
            String statisticEmploymentNoClever = df.format(((double) (countEmployment - count11 - count10) / countAll) * 100 );

            //就业率
            String statisticEmployment = df.format(((double) countEmployment / countAll) * 100 );


            //签就业协议形式就业数量
            if(count1 == 0) {
                employmentStatisticsInfoDto.setCountColumn1("");
            } else {
                employmentStatisticsInfoDto.setCountColumn1(count1 + "");
            }

            //签劳动合同形式就业数量
            if(count2 == 0) {
                employmentStatisticsInfoDto.setCountColumn2("");
            } else {
                employmentStatisticsInfoDto.setCountColumn2(count2 + "");
            }

            //其他录用形式就业数量
            if(count3 == 0) {
                employmentStatisticsInfoDto.setCountColumn3("");
            } else {
                employmentStatisticsInfoDto.setCountColumn3(count3 + "");
            }

            //升学数量
            if(count4 == 0) {
                employmentStatisticsInfoDto.setCountColumn4("");
            } else {
                employmentStatisticsInfoDto.setCountColumn4(count4 + "");
            }

            //出国（境）数量
            if(count5 == 0) {
                employmentStatisticsInfoDto.setCountColumn5("");
            } else {
                employmentStatisticsInfoDto.setCountColumn5(count5 + "");
            }

            //科研助理数量
            if(count6 == 0) {
                employmentStatisticsInfoDto.setCountColumn6("");
            } else {
                employmentStatisticsInfoDto.setCountColumn6(count6 + "");
            }

            //应征义务兵数量
            if(count7 == 0) {
                employmentStatisticsInfoDto.setCountColumn7("");
            } else {
                employmentStatisticsInfoDto.setCountColumn7(count7 + "");
            }

            //国家基层项目数量
            if(count8 == 0) {
                employmentStatisticsInfoDto.setCountColumn8("");
            } else {
                employmentStatisticsInfoDto.setCountColumn8(count8 + "");
            }

            //地方基层项目数量
            if(count9 == 0) {
                employmentStatisticsInfoDto.setCountColumn9("");
            } else {
                employmentStatisticsInfoDto.setCountColumn9(count9 + "");
            }

            //自由职业数量
            if(count10 == 0) {
                employmentStatisticsInfoDto.setCountColumn10("");
            } else {
                employmentStatisticsInfoDto.setCountColumn10(count10 + "");
            }

            //自主创业数量
            if(count11 == 0) {
                employmentStatisticsInfoDto.setCountColumn11("");
            } else {
                employmentStatisticsInfoDto.setCountColumn11(count11 + "");
            }

            //未就业数量
            if(count12 == 0) {
                employmentStatisticsInfoDto.setCountColumn12("");
            } else {
                employmentStatisticsInfoDto.setCountColumn12(count12 + "");
            }

            //就业人数
            employmentStatisticsInfoDto.setCountEmployment(countEmployment + "");

            //所有人数
            employmentStatisticsInfoDto.setCountAll(countAll + "");

            //签就业协议形式就业比例
            if(statistic1.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn1("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn1(statistic1 + "%");
            }

            //签劳动合同形式就业比例
            if(statistic2.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn2("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn2(statistic2 + "%");
            }

            //其他录用形式就业比例
            if(statistic3.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn3("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn3(statistic3 + "%");
            }

            //升学比例
            if(statistic4.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn4("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn4(statistic4 + "%");
            }

            //出国（境）比例
            if(statistic5.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn5("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn5(statistic5 + "%");
            }

            //科研助理比例
            if(statistic6.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn6("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn6(statistic6 + "%");
            }

            //应征义务兵比例
            if(statistic7.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn7("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn7(statistic7 + "%");
            }

            //国家基层项目比例
            if(statistic8.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn8("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn8(statistic8 + "%");
            }

            //地方基层项目比例
            if(statistic9.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn9("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn9(statistic9 + "%");
            }

            //自由职业比例
            if(statistic10.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn10("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn10(statistic10 + "%");
            }

            //自主创业比例
            if(statistic11.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn11("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn11(statistic11 + "%");
            }

            //未就业比例
            if(statistic12.equals("0.00")) {
                employmentStatisticsInfoDto.setStatisticsColumn12("");
            } else {
                employmentStatisticsInfoDto.setStatisticsColumn12(statistic12 + "%");
            }

            //就业比例（不含灵活）
            employmentStatisticsInfoDto.setStatisticsEmploymentNoClever(statisticEmploymentNoClever + "%");

            //就业比例
            employmentStatisticsInfoDto.setStatisticsEmployment(statisticEmployment + "%");

            return employmentStatisticsInfoDto;
        }
    }

    @Override
    public EmploymentStatisticsInfoDto getAllInfo(List<EmploymentStatisticsInfoDto> employmentStatisticsInfoDtoList) {

        EmploymentStatisticsInfoDto allInfo = new EmploymentStatisticsInfoDto();
        allInfo.setCollege("总计");
        try {
            //总计
            int countAllColumn1 = 0;
            int countAllColumn2 = 0;
            int countAllColumn3 = 0;
            int countAllColumn4 = 0;
            int countAllColumn5 = 0;
            int countAllColumn6 = 0;
            int countAllColumn7 = 0;
            int countAllColumn8 = 0;
            int countAllColumn9 = 0;
            int countAllColumn10 = 0;
            int countAllColumn11 = 0;
            int countAllColumn12 = 0;
            int countAllEmployment = 0;
            int countAllAll = 0;
            int countAllWithNormal = 0;
            int countAllWithWeipei = 0;
            int countAllWithNormalAndWeipei = 0;

            for (EmploymentStatisticsInfoDto employmentStatisticsInfoDto : employmentStatisticsInfoDtoList) {
                if (employmentStatisticsInfoDto.getCountColumn1() != "" && employmentStatisticsInfoDto.getCountColumn1() != "-") {
                    countAllColumn1 += Integer.parseInt(employmentStatisticsInfoDto.getCountColumn1());
                }

                if (employmentStatisticsInfoDto.getCountColumn2() != "" && employmentStatisticsInfoDto.getCountColumn2() != "-") {
                    countAllColumn2 += Integer.parseInt(employmentStatisticsInfoDto.getCountColumn2());
                }

                if (employmentStatisticsInfoDto.getCountColumn3() != "" && employmentStatisticsInfoDto.getCountColumn3() != "-") {
                    countAllColumn3 += Integer.parseInt(employmentStatisticsInfoDto.getCountColumn3());
                }

                if (employmentStatisticsInfoDto.getCountColumn4() != "" && employmentStatisticsInfoDto.getCountColumn4() != "-") {
                    countAllColumn4 += Integer.parseInt(employmentStatisticsInfoDto.getCountColumn4());
                }

                if (employmentStatisticsInfoDto.getCountColumn5() != "" && employmentStatisticsInfoDto.getCountColumn5() != "-") {
                    countAllColumn5 += Integer.parseInt(employmentStatisticsInfoDto.getCountColumn5());
                }

                if (employmentStatisticsInfoDto.getCountColumn6() != "" && employmentStatisticsInfoDto.getCountColumn6() != "-") {
                    countAllColumn6 += Integer.parseInt(employmentStatisticsInfoDto.getCountColumn6());
                }

                if (employmentStatisticsInfoDto.getCountColumn7() != "" && employmentStatisticsInfoDto.getCountColumn7() != "-") {
                    countAllColumn7 += Integer.parseInt(employmentStatisticsInfoDto.getCountColumn7());
                }

                if (employmentStatisticsInfoDto.getCountColumn8() != "" && employmentStatisticsInfoDto.getCountColumn8() != "-") {
                    countAllColumn8 += Integer.parseInt(employmentStatisticsInfoDto.getCountColumn8());
                }

                if (employmentStatisticsInfoDto.getCountColumn9() != "" && employmentStatisticsInfoDto.getCountColumn9() != "-") {
                    countAllColumn9 += Integer.parseInt(employmentStatisticsInfoDto.getCountColumn9());
                }

                if (employmentStatisticsInfoDto.getCountColumn10() != "" && employmentStatisticsInfoDto.getCountColumn10() != "-") {
                    countAllColumn10 += Integer.parseInt(employmentStatisticsInfoDto.getCountColumn10());
                }

                if (employmentStatisticsInfoDto.getCountColumn11() != "" && employmentStatisticsInfoDto.getCountColumn11() != "-") {
                    countAllColumn11 += Integer.parseInt(employmentStatisticsInfoDto.getCountColumn11());
                }

                if (employmentStatisticsInfoDto.getCountColumn12() != "" && employmentStatisticsInfoDto.getCountColumn12() != "-") {
                    countAllColumn12 += Integer.parseInt(employmentStatisticsInfoDto.getCountColumn12());
                }

                if (employmentStatisticsInfoDto.getCountEmployment() != "" && employmentStatisticsInfoDto.getCountEmployment() != "-") {
                    countAllEmployment += Integer.parseInt(employmentStatisticsInfoDto.getCountEmployment());
                }

                if (employmentStatisticsInfoDto.getCountAll() != "" && employmentStatisticsInfoDto.getCountAll() != "-") {
                    countAllAll += Integer.parseInt(employmentStatisticsInfoDto.getCountAll());
                }

                if(employmentStatisticsInfoDto.getCountWithNormal() != 0) {
                    countAllWithNormal += employmentStatisticsInfoDto.getCountWithNormal();
                }

                if(employmentStatisticsInfoDto.getCountWithWeipei() != 0) {
                    countAllWithWeipei += employmentStatisticsInfoDto.getCountWithWeipei();
                }

                if(employmentStatisticsInfoDto.getCountWithNormalAndWeipei() != 0) {
                    countAllWithNormalAndWeipei += employmentStatisticsInfoDto.getCountWithNormalAndWeipei();
                }
            }

            DecimalFormat df = new DecimalFormat("0.00");

            String statisticsAllColumn1 = df.format((double) countAllColumn1 / countAllAll * 100);
            String statisticsAllColumn2 = df.format((double) countAllColumn2 / countAllAll * 100);
            String statisticsAllColumn3 = df.format((double) countAllColumn3 / countAllAll * 100);
            String statisticsAllColumn4 = df.format((double) countAllColumn4 / countAllAll * 100);
            String statisticsAllColumn5 = df.format((double) countAllColumn5 / countAllAll * 100);
            String statisticsAllColumn6 = df.format((double) countAllColumn6 / countAllAll * 100);
            String statisticsAllColumn7 = df.format((double) countAllColumn7 / countAllAll * 100);
            String statisticsAllColumn8 = df.format((double) countAllColumn8 / countAllAll * 100);
            String statisticsAllColumn9 = df.format((double) countAllColumn9 / countAllAll * 100);
            String statisticsAllColumn10 = df.format((double) countAllColumn10 / countAllAll * 100);
            String statisticsAllColumn11 = df.format((double) countAllColumn11 / countAllAll * 100);
            String statisticsAllColumn12 = df.format((double) countAllColumn12 / countAllAll * 100);
            String statisticsAllEmploymentNoClever = df.format((double) (countAllEmployment - countAllColumn11 - countAllColumn10) / countAllAll * 100);
            String statisticsAllEmployment = df.format((double) (countAllAll - countAllColumn12) / countAllAll * 100);

            String statisticsAllWithNormal = df.format((double) countAllWithNormal / countAllAll * 100);
            String statisticsAllWithWeipei = df.format((double) countAllWithWeipei / countAllAll * 100);
            String statisticsAllWithNormalAndWeipei = df.format((double) countAllWithNormalAndWeipei / countAllAll * 100);

            if (countAllAll == 0) {
                allInfo.setCountColumn1("-");
            }

            if (countAllColumn1 == 0) {
                allInfo.setCountColumn1("");
            } else {
                allInfo.setCountColumn1(countAllColumn1 + "");
            }

            if (countAllColumn2 == 0) {
                allInfo.setCountColumn2("");
            } else {
                allInfo.setCountColumn2(countAllColumn2 + "");
            }

            if (countAllColumn3 == 0) {
                allInfo.setCountColumn3("");
            } else {
                allInfo.setCountColumn3(countAllColumn3 + "");
            }

            if (countAllColumn4 == 0) {
                allInfo.setCountColumn4("");
            } else {
                allInfo.setCountColumn4(countAllColumn4 + "");
            }

            if (countAllColumn5 == 0) {
                allInfo.setCountColumn5("");
            } else {
                allInfo.setCountColumn5(countAllColumn5 + "");
            }

            if (countAllColumn6 == 0) {
                allInfo.setCountColumn6("");
            } else {
                allInfo.setCountColumn6(countAllColumn6 + "");
            }

            if (countAllColumn7 == 0) {
                allInfo.setCountColumn7("");
            } else {
                allInfo.setCountColumn7(countAllColumn7 + "");
            }

            if (countAllColumn8 == 0) {
                allInfo.setCountColumn8("");
            } else {
                allInfo.setCountColumn8(countAllColumn8 + "");
            }

            if (countAllColumn9 == 0) {
                allInfo.setCountColumn9("");
            } else {
                allInfo.setCountColumn9(countAllColumn9 + "");
            }

            if (countAllColumn10 == 0) {
                allInfo.setCountColumn10("");
            } else {
                allInfo.setCountColumn10(countAllColumn10 + "");
            }

            if (countAllColumn11 == 0) {
                allInfo.setCountColumn11("");
            } else {
                allInfo.setCountColumn11(countAllColumn11 + "");
            }

            if (countAllColumn12 == 0) {
                allInfo.setCountColumn12("");
            } else {
                allInfo.setCountColumn12(countAllColumn12 + "");
            }

            if (statisticsAllColumn1.equals("0.00")) {
                allInfo.setStatisticsColumn1("");
            } else {
                allInfo.setStatisticsColumn1(statisticsAllColumn1 + "%");
            }

            if (statisticsAllColumn2.equals("0.00")) {
                allInfo.setStatisticsColumn2("");
            } else {
                allInfo.setStatisticsColumn2(statisticsAllColumn2 + "%");
            }

            if (statisticsAllColumn3.equals("0.00")) {
                allInfo.setStatisticsColumn3("");
            } else {
                allInfo.setStatisticsColumn3(statisticsAllColumn3 + "%");
            }

            if (statisticsAllColumn4.equals("0.00")) {
                allInfo.setStatisticsColumn4("");
            } else {
                allInfo.setStatisticsColumn4(statisticsAllColumn4 + "%");
            }

            if (statisticsAllColumn5.equals("0.00")) {
                allInfo.setStatisticsColumn5("");
            } else {
                allInfo.setStatisticsColumn5(statisticsAllColumn5 + "%");
            }

            if (statisticsAllColumn6.equals("0.00")) {
                allInfo.setStatisticsColumn6("");
            } else {
                allInfo.setStatisticsColumn6(statisticsAllColumn6 + "%");
            }

            if (statisticsAllColumn7.equals("0.00")) {
                allInfo.setStatisticsColumn7("");
            } else {
                allInfo.setStatisticsColumn7(statisticsAllColumn7 + "%");
            }

            if (statisticsAllColumn8.equals("0.00")) {
                allInfo.setStatisticsColumn8("");
            } else {
                allInfo.setStatisticsColumn8(statisticsAllColumn8 + "%");
            }

            if (statisticsAllColumn9.equals("0.00")) {
                allInfo.setStatisticsColumn9("");
            } else {
                allInfo.setStatisticsColumn9(statisticsAllColumn9 + "%");
            }

            if (statisticsAllColumn10.equals("0.00")) {
                allInfo.setStatisticsColumn10("");
            } else {
                allInfo.setStatisticsColumn10(statisticsAllColumn10 + "%");
            }

            if (statisticsAllColumn11.equals("0.00")) {
                allInfo.setStatisticsColumn11("");
            } else {
                allInfo.setStatisticsColumn11(statisticsAllColumn11 + "%");
            }

            if (statisticsAllColumn12.equals("0.00")) {
                allInfo.setStatisticsColumn12("");
            } else {
                allInfo.setStatisticsColumn12(statisticsAllColumn12 + "%");
            }

            allInfo.setCountEmployment(countAllEmployment + "");
            allInfo.setCountAll(countAllAll + "");
            allInfo.setStatisticsEmploymentNoClever(statisticsAllEmploymentNoClever + "%");
            allInfo.setStatisticsEmployment(statisticsAllEmployment + "%");
            allInfo.setStatisticsWithNormal(statisticsAllWithNormal + "%");
            allInfo.setStatisticsWithWeipei(statisticsAllWithWeipei + "%");
            allInfo.setStatisticsWithNormalAndWeipei(statisticsAllWithNormalAndWeipei + "%");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allInfo;
    }

    /**
     * 就业信息导出
     * 在这里还需要解析，由于sql语句实现解析会很麻烦，所以这里也会产生很长的时间
     * @return
     */
    @Override
    public List<EmploymentInfoParsedExportDto> listEmploymentInfo() throws SSException{
        try{
            List<EmploymentInfoExportDto> unpraseList = employmentStatisticsMapper.listEmploymentInfo();
            List<EmploymentInfoParsedExportDto> list = parseList(unpraseList);

            return list;
        }catch(Exception e){
            LogClerk.errLog.error(e);
            throw SSException.get(OaException.DownloadFileFail, e);
        }
    }

    /**
     * 解析，很烦
     * @param unpraseList
     * @return
     */
    public List<EmploymentInfoParsedExportDto> parseList(List<EmploymentInfoExportDto> unpraseList) throws Exception {
        List<EmploymentInfoParsedExportDto> list = new ArrayList<>();
        for(Iterator<EmploymentInfoExportDto> i = unpraseList.iterator(); i.hasNext();) {
            EmploymentInfoExportDto unprase = i.next();
            EmploymentInfoParsedExportDto temp = new EmploymentInfoParsedExportDto();

            //学生的基本信息
            temp.setStatusId(unprase.getStatusId());
            temp.setName(unprase.getName());
            temp.setCellphone(unprase.getCellphone());
            temp.setIdNumber(unprase.getIdNumber());
            temp.setCandidateNumber(unprase.getCandidateNumber());
            temp.setStudentNumber(unprase.getStudentNumber());
            temp.setSex(unprase.getSex());
            temp.setNationCode(unprase.getNationCode());
            temp.setNation(unprase.getNation());
            temp.setPoliticalStand(unprase.getPoliticalStand());
            //temp.setSchoolCode(unprase.getSchoolCode());
            //temp.setSchool(unprase.getSchool());
            temp.setStuLength(unprase.getStuLength());
            //temp.setQualificationNow(unprase.getQualificationNow());
            temp.setQualification(unprase.getQualification());
            temp.setCollegeCode(unprase.getCollegeCode());
            temp.setCollege(unprase.getCollege());
            temp.setGrade(unprase.getGrade());
            temp.setMajorQualification(unprase.getMajorQualification());
            temp.setMajorCode(unprase.getMajorCode());
            temp.setMajor(unprase.getMajor());
            temp.setOtherMajor(unprase.getOtherMajor());
            temp.setIntendWhereabouts(unprase.getIntendWhereabouts());
            temp.setDifficulty(unprase.getDifficulty());
            temp.setNormalStu(unprase.getNormalStu());
            temp.setOriginPlace(unprase.getOriginPlace());
            temp.setOriginPlaceCode(unprase.getOriginPlaceCode());
            temp.setTrainingMode(unprase.getTrainingMode());

            //就业意向调查 需要解析
            //由于有的学生可能没有填写一些信息 解析的时候会注意到null

            //第一问题      就业意向
            if(unprase.getPk1() == null || unprase.getPk1().equals("")){
                temp.setPk1("");
            } else {
                int pk1 = Integer.parseInt(unprase.getPk1());
                temp.setPk1(PK1Enum.valueOf(pk1).getName());
            }

            //第二个问题     本科院校所属
            if(Assert.isNull(unprase.getPk2())) {
                temp.setPk2("");
            } else {
                int pk2 = Integer.parseInt(unprase.getPk2());
                temp.setPk2(PK2Enum.valueOf(pk2).getName());
            }
            temp.setUnderGraduate(unprase.getUnderGraduate());

            //第三个问题     希望获得的就业指导 12个
            int[] pk3Arrays = splitAndParseString(unprase.getPk3(), 12);
            //没法用循环     手打
            temp.setPk3_1(PK3Enum.valueOf(pk3Arrays[0]).getName());
            temp.setPk3_2(PK3Enum.valueOf(pk3Arrays[1]).getName());
            temp.setPk3_3(PK3Enum.valueOf(pk3Arrays[2]).getName());
            temp.setPk3_4(PK3Enum.valueOf(pk3Arrays[3]).getName());
            temp.setPk3_5(PK3Enum.valueOf(pk3Arrays[4]).getName());
            temp.setPk3_6(PK3Enum.valueOf(pk3Arrays[5]).getName());
            temp.setPk3_7(PK3Enum.valueOf(pk3Arrays[6]).getName());
            temp.setPk3_8(PK3Enum.valueOf(pk3Arrays[7]).getName());
            temp.setPk3_9(PK3Enum.valueOf(pk3Arrays[8]).getName());
            temp.setPk3_10(PK3Enum.valueOf(pk3Arrays[9]).getName());
            temp.setPk3_11(PK3Enum.valueOf(pk3Arrays[10]).getName());
            temp.setPk3_12(PK3Enum.valueOf(pk3Arrays[11]).getName());
            //其他
            temp.setOtherGuidance(unprase.getOtherGuidance());

            //第四个问题     就业意向地点 3组6个
            //这个调用不了普通的解析方法 因为里面存的是地区代码

            int[] pk4Arrays = new int[6];
            String[] strings = unprase.getPk4().split(",");
            for(int j = 0; j < strings.length; j++) {
                pk4Arrays[j] = Integer.parseInt(strings[j]);
            }

            //同样手打      一个省 一个市
            temp.setPk4_11(provincePropertyMapper.queryNameById(pk4Arrays[0]));
            temp.setPk4_12(cityPropertyMapper.queryCityById(pk4Arrays[1]));
            temp.setPk4_21(provincePropertyMapper.queryNameById(pk4Arrays[2]));
            temp.setPk4_22(cityPropertyMapper.queryCityById(pk4Arrays[3]));
            temp.setPk4_31(provincePropertyMapper.queryNameById(pk4Arrays[4]));
            temp.setPk4_32(cityPropertyMapper.queryCityById(pk4Arrays[5]));

            //第五个问题     预期问题
            if(Assert.isNull(unprase.getPk5())) {
                temp.setPk5("");
            } else {
                temp.setPk5(unprase.getPk5());
            }

            //第六个问题     就业意向所属行业
            if(Assert.isNull(unprase.getPk6())) {
                temp.setPk6("");
            } else {
                int pk6 = Integer.parseInt(unprase.getPk6());
                temp.setPk6(PK6Enum.valueOf(pk6).getName());
            }

            //第七个问题     第六个问题选择教育行业     教育行业倾向于9个
            int[] pk7Arrays = splitAndParseString(unprase.getPk7(), 9);
            //手打
            temp.setPk7_1(PK7Enum.valueOf(pk7Arrays[0]).getName());
            temp.setPk7_2(PK7Enum.valueOf(pk7Arrays[1]).getName());
            temp.setPk7_3(PK7Enum.valueOf(pk7Arrays[2]).getName());
            temp.setPk7_4(PK7Enum.valueOf(pk7Arrays[3]).getName());
            temp.setPk7_5(PK7Enum.valueOf(pk7Arrays[4]).getName());
            temp.setPk7_6(PK7Enum.valueOf(pk7Arrays[5]).getName());
            temp.setPk7_7(PK7Enum.valueOf(pk7Arrays[6]).getName());
            temp.setPk7_8(PK7Enum.valueOf(pk7Arrays[7]).getName());
            temp.setPk7_9(PK7Enum.valueOf(pk7Arrays[8]).getName());

            //第八个问题     第六个问题选择B-S
            if(Assert.isNull(unprase.getPk8())) {
                temp.setPk8("");
            } else {
                int pk8 = Integer.parseInt(unprase.getPk8());
                temp.setPk8(PK8Enum.valueOf(pk8).getName());
            }

            //第九个问题     第六个问题选择国际组织7个
            int[] pk9Arrays = splitAndParseString(unprase.getPk9(), 7);
            //手打
            temp.setPk9_1(PK9Enum.valueOf(pk9Arrays[0]).getName());
            temp.setPk9_2(PK9Enum.valueOf(pk9Arrays[1]).getName());
            temp.setPk9_3(PK9Enum.valueOf(pk9Arrays[2]).getName());
            temp.setPk9_4(PK9Enum.valueOf(pk9Arrays[3]).getName());
            temp.setPk9_5(PK9Enum.valueOf(pk9Arrays[4]).getName());
            temp.setPk9_6(PK9Enum.valueOf(pk9Arrays[5]).getName());
            temp.setPk9_7(PK9Enum.valueOf(pk9Arrays[6]).getName());

            //第十个问题     就业意向单位
            String[] strPk10 = unprase.getPk10().split(",");
            String[] assister = {"","",""};
            for(int j = 0; j < strPk10.length; j++) {
                assister[j] = strPk10[j];
            }
            temp.setPk10_1(assister[0]);
            temp.setPk10_2(assister[1]);
            temp.setPk10_3(assister[2]);

            if(!(temp.getName() == null || temp.getName().equals(""))) {
                list.add(temp);
            }
        }
        return list;
    }

    //解析数据库中的字符转 需要分割 并且 转成int数组
    public int[] splitAndParseString(String toParseString, int numberOfArray) {
        int[] arrays = new int[numberOfArray];
        Arrays.fill(arrays, 0);
        //我就怕 出现空指针
        if(toParseString == null || toParseString.equals("")) {
            return arrays;
        }

        //非空
        String[] strings = toParseString.split(",");
        for(int i = 0; i < strings.length; i++) {
            //arrays[i] = Integer.parseInt(strings[i]);
            int temp = Integer.parseInt(strings[i]);
            arrays[ temp - 1] = temp;//程序员的世界是从零开始的
        }
        return arrays;
    }
}
