package com.pandawork.nenu.oa.common.entity.history;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 六年学生历史数据
 *
 * @author Lw
 * @time 2017/7/25 13:57
 */
@Entity
@Table(name = "t_stu_history_data")
public class StuHistoryData extends AbstractEntity{

    //历史数据id
    @Id
    private Integer id;

    //毕业年份
    @Column(name = "year")
    private String year;

    //学生姓名
    @Column(name = "name")
    private String name;

    //是否为东师信使，1-是；2-否
    @Column(name = "messenger")
    private Integer messenger;

    //性别，1-男；2-女
    @Column(name = "sex")
    private Integer sex;

    //民族代码
    @Column(name = "nation_code")
    private String nationCode;

    //专业名
    @Column(name = "major_name")
    private String majorName;

    //生源所在地
    @Column(name = "origin_place")
    private String originPlace;

    //毕业去向代码
    @Column(name = "whereabout_go")
    private String whereaboutGo;

    //单位名称
    @Column(name = "unit_name")
    private String unitName;

    //单位所在省
    @Column(name = "unit_province")
    private String unitProvince;

    //单位所在市、州
    @Column(name = "unit_city")
    private String unitCity;

    //单位性质代码
    @Column(name = "unit_property")
    private String unitProperty;

    //单位行业代码
    @Column(name = "unit_industry")
    private String unitIndustry;

    //学院代码
    @Column(name = "college_code")
    private String collegeCode;

    //专业层次代码，2-本科生专业；1-研究生专业
    @Column(name = "major_qualification")
    private String majorQualification;

    //报到证签发类别代码
    @Column(name = "report_mode_code")
    private Integer reportModeCode;

    //政治面貌代码
    @Column(name = "political_stand_code")
    private String politicalStandCode;

    //师范生类别代码
    @Column(name = "normal_stu_code")
    private Integer normalStuCode;

    //困难生类别代码
    @Column(name = "difficulty_code")
    private Integer difficultyCode;

    //电话号码
    @Column(name = "cellphone")
    private String cellphone;

    //建立时间
    @Column(name = "created_time")
    private Date createdTime;

    //最后一次修改时间
    @Column(name = "last_modified_name")
    private Date lastModifiedName;

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMessenger() {
        return messenger;
    }

    public void setMessenger(Integer messenger) {
        this.messenger = messenger;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getWhereaboutGo() {
        return whereaboutGo;
    }

    public void setWhereaboutGo(String whereaboutGo) {
        this.whereaboutGo = whereaboutGo;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitProvince() {
        return unitProvince;
    }

    public void setUnitProvince(String unitProvince) {
        this.unitProvince = unitProvince;
    }

    public String getUnitCity() {
        return unitCity;
    }

    public void setUnitCity(String unitCity) {
        this.unitCity = unitCity;
    }

    public String getUnitProperty() {
        return unitProperty;
    }

    public void setUnitProperty(String unitProperty) {
        this.unitProperty = unitProperty;
    }

    public String getUnitIndustry() {
        return unitIndustry;
    }

    public void setUnitIndustry(String unitIndustry) {
        this.unitIndustry = unitIndustry;
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public String getMajorQualification() {
        return majorQualification;
    }

    public void setMajorQualification(String majorQualification) {
        this.majorQualification = majorQualification;
    }

    public Integer getReportModeCode() {
        return reportModeCode;
    }

    public void setReportModeCode(Integer reportModeCode) {
        this.reportModeCode = reportModeCode;
    }

    public String getPoliticalStandCode() {
        return politicalStandCode;
    }

    public void setPoliticalStandCode(String politicalStandCode) {
        this.politicalStandCode = politicalStandCode;
    }

    public Integer getNormalStuCode() {
        return normalStuCode;
    }

    public void setNormalStuCode(Integer normalStuCode) {
        this.normalStuCode = normalStuCode;
    }

    public Integer getDifficultyCode() {
        return difficultyCode;
    }

    public void setDifficultyCode(Integer difficultyCode) {
        this.difficultyCode = difficultyCode;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedName() {
        return lastModifiedName;
    }

    public void setLastModifiedName(Date lastModifiedName) {
        this.lastModifiedName = lastModifiedName;
    }

}
