package com.pandawork.nenu.oa.common.dto.statistics;

/**
 * 就业比例信息Dto
 * @author Lw
 * @time 2017/8/9 18:43
 */
public class EmploymentStatisticsInfoDto implements Comparable<EmploymentStatisticsInfoDto> {

    //就业比例信息id
    private Integer id;

    //所在学院
    private String college;

    /*就业数据*/
    //签就业协议形式就业人数
    private String countColumn1;

    //签就业协议形式就业比例
    private String statisticsColumn1;

    //签劳动合同形式就业人数
    private String countColumn2;

    //签劳动合同形式就业比例
    private String statisticsColumn2;

    //其他录用形式就业人数
    private String countColumn3;

    //其他录用形式就业比例
    private String statisticsColumn3;

    //升学人数
    private String countColumn4;

    //升学比例
    private String statisticsColumn4;

    //出国（境）人数
    private String countColumn5;

    //出国（境）比例
    private String statisticsColumn5;

    //科研助理人数
    private String countColumn6;

    //科研助理比例
    private String statisticsColumn6;

    //应征义务兵人数
    private String countColumn7;

    //应征义务兵比例
    private String statisticsColumn7;

    //国家基层项目人数
    private String countColumn8;

    //国家基层项目比例
    private String statisticsColumn8;

    //地方基层项目人数
    private String countColumn9;

    //地方基层项目比例
    private String statisticsColumn9;

    //自由职业人数
    private String countColumn10;

    //自由职业比例
    private String statisticsColumn10;

    //自主创业人数
    private String countColumn11;

    //自主创业比例
    private String statisticsColumn11;

    //未就业人数
    private String countColumn12;

    //未就业比例
    private String statisticsColumn12;

    //就业人数
    private String countEmployment;

    //总计
    private String countAll;

    //就业率(不含灵活)
    private String statisticsEmploymentNoClever;

    //就业率
    private String statisticsEmployment;

    //就业人数（包括免师）
    private int countWithNormal;

    //就业人数（包括委培）
    private int countWithWeipei;

    //就业人数（包括免师和委培）
    private int countWithNormalAndWeipei;

    //就业率（包括免师）
    private String statisticsWithNormal;

    //就业率（包括委培）
    private String statisticsWithWeipei;

    //就业率（包括免师和委培）
    private String statisticsWithNormalAndWeipei;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCountColumn1() {
        return countColumn1;
    }

    public void setCountColumn1(String countColumn1) {
        this.countColumn1 = countColumn1;
    }

    public String getStatisticsColumn1() {
        return statisticsColumn1;
    }

    public void setStatisticsColumn1(String statisticsColumn1) {
        this.statisticsColumn1 = statisticsColumn1;
    }

    public String getCountColumn2() {
        return countColumn2;
    }

    public void setCountColumn2(String countColumn2) {
        this.countColumn2 = countColumn2;
    }

    public String getStatisticsColumn2() {
        return statisticsColumn2;
    }

    public void setStatisticsColumn2(String statisticsColumn2) {
        this.statisticsColumn2 = statisticsColumn2;
    }

    public String getCountColumn3() {
        return countColumn3;
    }

    public void setCountColumn3(String countColumn3) {
        this.countColumn3 = countColumn3;
    }

    public String getStatisticsColumn3() {
        return statisticsColumn3;
    }

    public void setStatisticsColumn3(String statisticsColumn3) {
        this.statisticsColumn3 = statisticsColumn3;
    }

    public String getCountColumn4() {
        return countColumn4;
    }

    public void setCountColumn4(String countColumn4) {
        this.countColumn4 = countColumn4;
    }

    public String getStatisticsColumn4() {
        return statisticsColumn4;
    }

    public void setStatisticsColumn4(String statisticsColumn4) {
        this.statisticsColumn4 = statisticsColumn4;
    }

    public String getCountColumn5() {
        return countColumn5;
    }

    public void setCountColumn5(String countColumn5) {
        this.countColumn5 = countColumn5;
    }

    public String getStatisticsColumn5() {
        return statisticsColumn5;
    }

    public void setStatisticsColumn5(String statisticsColumn5) {
        this.statisticsColumn5 = statisticsColumn5;
    }

    public String getCountColumn6() {
        return countColumn6;
    }

    public void setCountColumn6(String countColumn6) {
        this.countColumn6 = countColumn6;
    }

    public String getStatisticsColumn6() {
        return statisticsColumn6;
    }

    public void setStatisticsColumn6(String statisticsColumn6) {
        this.statisticsColumn6 = statisticsColumn6;
    }

    public String getCountColumn7() {
        return countColumn7;
    }

    public void setCountColumn7(String countColumn7) {
        this.countColumn7 = countColumn7;
    }

    public String getStatisticsColumn7() {
        return statisticsColumn7;
    }

    public void setStatisticsColumn7(String statisticsColumn7) {
        this.statisticsColumn7 = statisticsColumn7;
    }

    public String getCountColumn8() {
        return countColumn8;
    }

    public void setCountColumn8(String countColumn8) {
        this.countColumn8 = countColumn8;
    }

    public String getStatisticsColumn8() {
        return statisticsColumn8;
    }

    public void setStatisticsColumn8(String statisticsColumn8) {
        this.statisticsColumn8 = statisticsColumn8;
    }

    public String getCountColumn9() {
        return countColumn9;
    }

    public void setCountColumn9(String countColumn9) {
        this.countColumn9 = countColumn9;
    }

    public String getStatisticsColumn9() {
        return statisticsColumn9;
    }

    public void setStatisticsColumn9(String statisticsColumn9) {
        this.statisticsColumn9 = statisticsColumn9;
    }

    public String getCountColumn10() {
        return countColumn10;
    }

    public void setCountColumn10(String countColumn10) {
        this.countColumn10 = countColumn10;
    }

    public String getStatisticsColumn10() {
        return statisticsColumn10;
    }

    public void setStatisticsColumn10(String statisticsColumn10) {
        this.statisticsColumn10 = statisticsColumn10;
    }

    public String getCountColumn11() {
        return countColumn11;
    }

    public void setCountColumn11(String countColumn11) {
        this.countColumn11 = countColumn11;
    }

    public String getStatisticsColumn11() {
        return statisticsColumn11;
    }

    public void setStatisticsColumn11(String statisticsColumn11) {
        this.statisticsColumn11 = statisticsColumn11;
    }

    public String getCountColumn12() {
        return countColumn12;
    }

    public void setCountColumn12(String countColumn12) {
        this.countColumn12 = countColumn12;
    }

    public String getStatisticsColumn12() {
        return statisticsColumn12;
    }

    public void setStatisticsColumn12(String statisticsColumn12) {
        this.statisticsColumn12 = statisticsColumn12;
    }

    public String getCountEmployment() {
        return countEmployment;
    }

    public void setCountEmployment(String countEmployment) {
        this.countEmployment = countEmployment;
    }

    public String getCountAll() {
        return countAll;
    }

    public void setCountAll(String countAll) {
        this.countAll = countAll;
    }

    public String getStatisticsEmploymentNoClever() {
        return statisticsEmploymentNoClever;
    }

    public void setStatisticsEmploymentNoClever(String statisticsEmploymentNoClever) {
        this.statisticsEmploymentNoClever = statisticsEmploymentNoClever;
    }

    public String getStatisticsEmployment() {
        return statisticsEmployment;
    }

    public void setStatisticsEmployment(String statisticsEmployment) {
        this.statisticsEmployment = statisticsEmployment;
    }

    public String getStatisticsWithNormal() {
        return statisticsWithNormal;
    }

    public void setStatisticsWithNormal(String statisticsWithNormal) {
        this.statisticsWithNormal = statisticsWithNormal;
    }

    public String getStatisticsWithWeipei() {
        return statisticsWithWeipei;
    }

    public void setStatisticsWithWeipei(String statisticsWithWeipei) {
        this.statisticsWithWeipei = statisticsWithWeipei;
    }

    public String getStatisticsWithNormalAndWeipei() {
        return statisticsWithNormalAndWeipei;
    }

    public void setStatisticsWithNormalAndWeipei(String statisticsWithNormalAndWeipei) {
        this.statisticsWithNormalAndWeipei = statisticsWithNormalAndWeipei;
    }

    public int getCountWithNormal() {
        return countWithNormal;
    }

    public void setCountWithNormal(int countWithNormal) {
        this.countWithNormal = countWithNormal;
    }

    public int getCountWithWeipei() {
        return countWithWeipei;
    }

    public void setCountWithWeipei(int countWithWeipei) {
        this.countWithWeipei = countWithWeipei;
    }

    public int getCountWithNormalAndWeipei() {
        return countWithNormalAndWeipei;
    }

    public void setCountWithNormalAndWeipei(int countWithNormalAndWeipei) {
        this.countWithNormalAndWeipei = countWithNormalAndWeipei;
    }

    @Override
    public int compareTo(EmploymentStatisticsInfoDto o) {
        String strThis = this.getStatisticsEmployment();
        String strO = o.getStatisticsEmployment();
        if(!strThis.equals("") && !strThis.equals("-") && !strO.equals("") && !strO.equals("-")) {
            while(strThis.contains("%")) {
                strThis = strThis.substring(0, strThis.length() - 1);
            }
            while(strO.contains("%")) {
                strO = strO.substring(0, strO.length() - 1);
            }

            double doubleThis = Double.parseDouble(strThis);
            double doubleO = Double.parseDouble(strO);

            double compare = doubleThis - doubleO;
            if (compare > 0) {
                return -1;
            } else if (compare < 0) {
                return 1;
            } else {
                return 0;
            }
        } else if((strThis.equals("") || strThis.equals("-")) && (!strO.equals("") && !strO.equals("-"))){
            return 1;
        } else if((!strThis.equals("") && !strThis.equals("-")) && (strO.equals("") || strO.equals("-"))) {
            return -1;
        } else {
            return 0;
        }
    }
}
