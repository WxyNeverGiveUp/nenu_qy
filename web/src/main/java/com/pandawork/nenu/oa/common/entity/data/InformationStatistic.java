package com.pandawork.nenu.oa.common.entity.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user: lishicao
 * date 15/4/23
 * time 下午2:02
 */

@Entity
@Table(name = "t_information_statistic")
public class InformationStatistic extends AbstractEntity{

    @Id
    private Integer id ;

    //姓名
    @Column( name = "name" )
    private String name ;
    //学号
    @Column( name = "exam_id" )
    private String examId ;
    //学院
    @Column( name = "college_id" )
    private Integer collegeId;
    //学院名称
    @Column( name = "college_name" )
    private String collegeName ;

    //性别
    @Column( name = "sex_id" )
    private Integer sexId ;
    //
    @Column( name = "sex" )
    private String sex ;

    //学历
    @Column( name = "qualification_study" )
    private String qualificationStudy;

    //学科
    @Column( name = "major_division" )
    private String majorDivision;

    //专业
    @Column( name = "major_id" )
    private String majorId;
    //
    @Column( name = "major_name" )
    private String majorName;

    //师范生类型
    @Column( name = "normal_stu_id" )
    private Integer normalStuId;
    //
    @Column( name = "normal_stu" )
    private String normalStu;

    //生源省份
    @Column( name = "province_id" )
    private Integer provinceId;
    //
    @Column( name = "province" )
    private String province;

    //困难生类型
    @Column( name = "difficulty_id" )
    private Integer difficultyId ;
    //
    @Column( name = "difficulty_mode" )
    private String difficultyMode;

    //民族
    @Column( name = "nation_id" )
    private String nationId ;
    //
    @Column( name = "nation" )
    private String nation;

    //政治面貌
    @Column( name = "political_stand_id" )
    private String politicalStandId;
    //
    @Column( name = "political_stand" )
    private String politicalStand;

    //毕业去向
    @Column( name = "whereaboutgo_id" )
    private Integer whereaboutgoId;
    //
    @Column( name = "whereaboutgo" )
    private String whereaboutgo;

    //就业地区类型
    @Column( name = "place_class_id" )
    private Integer placeClassId;
    //
    @Column( name = "place_class" )
    private String placeClass;

    //就业省份
    @Column( name = "job_province_id" )
    private Integer jobProvinceId;
    //
    @Column( name = "job_province" )
    private String jobProvince;

    //就业区域（如东北地区）
    @Column( name = "job_region")
    private String jobRegion;

    //单位性质
    @Column( name = "ins_mode_id" )
    private Integer insModeId;
    //
    @Column( name = "ins_mode" )
    private String insMode;

    //单位行业
    @Column( name = "ins_field_id" )
    private Integer insFieldId;
    //
    @Column( name = "ins_field" )
    private String insField;

    //拟就业类型
    @Column( name = "wherewantgo")
    private String wherewantgo;

    //拟就业单位
    @Column( name = "wherewantgo_ins")
    private String wherewantgoIns;

    //预计毕业时间
    @Column( name = "graduation_time" )
    private Integer graduationTime;

    //考生类别：城市应届，城市往届，农村应届，农村往届
    @Column( name = "student_classification" )
    private String studentClassification;

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Integer getSexId() {
        return sexId;
    }

    public void setSexId(Integer sexId) {
        this.sexId = sexId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getQualificationStudy() {
        return qualificationStudy;
    }

    public void setQualificationStudy(String qualificationStudy) {
        this.qualificationStudy = qualificationStudy;
    }

    public String getMajorDivision() {
        return majorDivision;
    }

    public void setMajorDivision(String majorDivision) {
        this.majorDivision = majorDivision;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getNormalStuId() {
        return normalStuId;
    }

    public void setNormalStuId(Integer normalStuId) {
        this.normalStuId = normalStuId;
    }

    public String getNormalStu() {
        return normalStu;
    }

    public void setNormalStu(String normalStu) {
        this.normalStu = normalStu;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(Integer difficultyId) {
        this.difficultyId = difficultyId;
    }

    public String getDifficultyMode() {
        return difficultyMode;
    }

    public void setDifficultyMode(String difficultyMode) {
        this.difficultyMode = difficultyMode;
    }

    public String getNationId() {
        return nationId;
    }

    public void setNationId(String nationId) {
        this.nationId = nationId;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPoliticalStandId() {
        return politicalStandId;
    }

    public void setPoliticalStandId(String politicalStandId) {
        this.politicalStandId = politicalStandId;
    }

    public String getPoliticalStand() {
        return politicalStand;
    }

    public void setPoliticalStand(String politicalStand) {
        this.politicalStand = politicalStand;
    }

    public Integer getWhereaboutgoId() {
        return whereaboutgoId;
    }

    public void setWhereaboutgoId(Integer whereaboutgoId) {
        this.whereaboutgoId = whereaboutgoId;
    }

    public String getWhereaboutgo() {
        return whereaboutgo;
    }

    public void setWhereaboutgo(String whereaboutgo) {
        this.whereaboutgo = whereaboutgo;
    }

    public Integer getPlaceClassId() {
        return placeClassId;
    }

    public void setPlaceClassId(Integer placeClassId) {
        this.placeClassId = placeClassId;
    }

    public String getPlaceClass() {
        return placeClass;
    }

    public void setPlaceClass(String placeClass) {
        this.placeClass = placeClass;
    }

    public Integer getJobProvinceId() {
        return jobProvinceId;
    }

    public void setJobProvinceId(Integer jobProvinceId) {
        this.jobProvinceId = jobProvinceId;
    }

    public String getJobProvince() {
        return jobProvince;
    }

    public void setJobProvince(String jobProvince) {
        this.jobProvince = jobProvince;
    }

    public String getJobRegion() {
        return jobRegion;
    }

    public void setJobRegion(String jobRegion) {
        this.jobRegion = jobRegion;
    }

    public Integer getInsModeId() {
        return insModeId;
    }

    public void setInsModeId(Integer insModeId) {
        this.insModeId = insModeId;
    }

    public String getInsMode() {
        return insMode;
    }

    public void setInsMode(String insMode) {
        this.insMode = insMode;
    }

    public Integer getInsFieldId() {
        return insFieldId;
    }

    public void setInsFieldId(Integer insFieldId) {
        this.insFieldId = insFieldId;
    }

    public String getInsField() {
        return insField;
    }

    public void setInsField(String insField) {
        this.insField = insField;
    }

    public String getWherewantgo() {
        return wherewantgo;
    }

    public void setWherewantgo(String wherewantgo) {
        this.wherewantgo = wherewantgo;
    }

    public String getWherewantgoIns() {
        return wherewantgoIns;
    }

    public void setWherewantgoIns(String wherewantgoIns) {
        this.wherewantgoIns = wherewantgoIns;
    }

    public Integer getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Integer graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getStudentClassification() {
        return studentClassification;
    }

    public void setStudentClassification(String studentClassification) {
        this.studentClassification = studentClassification;
    }
}
