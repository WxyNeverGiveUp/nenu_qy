package com.pandawork.nenu.oa.common.entity.data;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 学生信息
 * user: lishicao
 * date: 14-8-25
 * time: 上午8:39
 */
@Entity
@Table(name = "t_stu_info")
public class StudentInfomation extends AbstractEntity{


    //唯一表示
    @Id
    private Integer id ;

    //学生姓名
    @Column( name = "name")
    private String name ;

    //协议编码
    @Column( name = "agreement_number")
    private Integer agreementNumber;

    //身份证号码
    @Column( name = "id_number")
    private String idNumber;

    //性别代码
    @Column( name = "sex_id" )
    private Integer sexId;

    //性别
    @Transient
    private String sex;

    //出生日期
    @Column( name = "dob")
    private String dob;

    //考生号
    @Column( name = "exam_id")
    private String examId;

    //民族号码
    @Column( name = "nation_id" )
    private String nationId;

    //民族
    @Transient
    private String nation;

    //政治面貌代码
    @Column( name = "political_stand_id" )
    private String politicalStandId;

    //政治面貌
    @Transient
    private String politicalStand;

    //高校代码
    @Column( name = "school_id")
    private Integer schoolId;

    //高校名称
    @Transient
    private String school;

    //手机号
    @Column( name = "tele")
    private String tele;

    //学院代码
    @Column( name = "college_id" )
    private Integer collegeId;

    //学院名称
    @Column( name = "college_name" )
    private String collegeName;

    //修改时间
    @Column( name = "modify_time" )
    private String modifyTime;

    //班级
    @Column( name = "student_class" )
    private Integer studentClass;

    //学号
    @Column( name = "student_id")
    private Long studentId;

    //学历代码
    @Column( name = "qualification_id" )
    private String qualificationId;

    //学历层次( 毕业的时候是怎样毕业的，或者结业的，比如博士生毕业，或者博士生结业，本科生毕业，本科生结业，二学历)
    @Transient
    private String graduatQualification;

    //学历  现在读的学历，比如本科，硕士，博士
    @Column( name = "qualification_study")
    private String qualification;

    //专业代码
    @Column( name = "major_id")
    private String majorId;

    //专业
    @Transient
    private String major;

    //专业方向
    @Column( name = "major_field")
    private String majorField;

    //学科
    @Transient
    private String majorDivision;

    //培养方式代码
    @Column( name = "training_mode_id")
    private Integer trainingModeId;

    //培养方式
    @Transient
    private String trainingMode;

    //委培单位
    @Column( name = "weipei_insitution" )
    private String weipeiInsitution;

    //生源所在地代码
    @Column( name = "native_place_id" )
    private Integer nativePlaceId;

    //生源所在地参考
    @Column( name = "native_place_remind")
    private String nativePlaceRemind;

    //生源所在地
    @Transient
    private String nativePlace;
    //生源所在省份
    @Transient
    private String fromProvince;

    @Transient
    private String fromPlace;
    //高等教育类型
    @Column( name = "education_mod" )
    private String educationMod;

    //学制
    @Column( name = "stu_length" )
    private float stuLength;

    //注册时间，入学时间
    @Column( name = "regist_time" )
    private String registTime;

    //预期毕业时间
    @Column( name = "graduation_time" )
    private String graduationTime;

    //困难生代码
    @Column( name = "difficulty_id" )
    private Integer difficultyTypeId;

    //困难生类别
    @Transient
    private String difficultyType;

    //入学年级
    @Column( name = "grade" )
    private Integer grade;

    //师范生类别代码
    @Column( name = "normal_stu_id" )
    private Integer normalStuId;

    //师范生类别
    @Transient
    private String normalStu;

    //注册状态
    @Column( name = "regist_status" )
    private String registStatus;

    //毕业去向代码
    @Column( name = "whereaboutgo_id")
    private Integer whereaboutgoId;

    //毕业去向
    @Transient
    private String whereaboutgo;

    //单位组织机构代码
    @Column( name = "institution_id")
    private String institutionId;

    //对应jyoa中的单位ID，可以为空
    @Column( name = "company_id")
    private Integer companyId;

    //单位名称
    @Column( name = "institution_name" )
    private String institutionName;

    //单位性质代码
    @Column( name = "ins_mode_id")
    private Integer insModeId;

    //单位性质
    @Transient
    private String insMode;

    //单位行业代码
    @Column( name = "ins_field_id")
    private Integer insFieldId;

    //单位行业
    @Transient
    private String insField;

    //单位所在地代码
    @Column( name = "ins_place_id")
    private Integer insPlaceId;

    //单位所在地
    @Transient
    private String insPlace;

    //工作职位代码
    @Column( name = "job_id")
    private Integer jobId;

    //工作职位类别
    @Transient
    private String jobType;

    //报到证签发类别代码
    @Column( name = "report_mode_id")
    private Integer reportModeId;

    //报到证签发类别
    @Column( name = "report_mode")
    private String reportMode;

    //报到证迁往单位名称
    @Column( name = "report_ins")
    private String reportIns;

    //迁往单位所在地代码
    @Column( name = "des_ins_id")
    private Integer desInsId;

    //就业城市
    @Transient
    private String goPlace;

    //就业区域类型
    @Transient
    private String goPlaceType;

    //就业区域
    @Transient
    private String goArea;

    //就业省份
    @Transient
    private String goProvince;

    //迁往单位所在地
    @Transient
    private String desIns;

    //报到证编号
    @Column( name = "report_num_id")
    private String reportNumId;

    //报到起始时间
    @Column( name = "report_start_time")
    private String reportStartTime;

    //审核状态
    @Column( name = "verify_status" )
    private String verifyStatus;

    //审核时间
    @Column( name = "verify_time" )
    private Date verifyTime;

    //拟就业类型
    @Column( name = "wherewantgo" )
    private String wherewantgo;

    //拟就业单位
    @Column( name = "wherewantgo_ins" )
    private String wherewantgoIns;

    //就业困难类型　
    @Column( name = "work_difficulty_mode" )
    private String workDifficultyMode;
    //总数
    @Transient
    private Integer counts;

    @Column( name = "document_in")
    private Integer documentIn;


    @Column( name = "hukou_in")
    private Integer hukouIn;


    @Column( name = "docIns")
    private String docIns;


    @Column( name = "hukouIns")
    private String hukouIns;

    //录入状态
    @Column( name = "checkin_status")
    private String checkinStatus;

    //录入时间
    @Column( name = "checkin_time")
    private Date checkinTime;

    //考生类别：城市应届，城市往届，农村应届，农村往届
    @Column( name = "student_classification" )
    private String studentClassification;

    //备注
    @Column( name = "remark")
    private String remark;

    @Override
    public String toString() {
        return "StudentInfomation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", sexId=" + sexId +
                ", sex='" + sex + '\'' +
                ", dob='" + dob + '\'' +
                ", examId='" + examId + '\'' +
                ", nationId='" + nationId + '\'' +
                ", nation='" + nation + '\'' +
                ", politicalStandId='" + politicalStandId + '\'' +
                ", politicalStand='" + politicalStand + '\'' +
                ", schoolId=" + schoolId +
                ", school='" + school + '\'' +
                ", tele='" + tele + '\'' +
                ", collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", studentClass=" + studentClass +
                ", studentId=" + studentId +
                ", qualificationId='" + qualificationId + '\'' +
                ", graduatQualification='" + graduatQualification + '\'' +
                ", qualification='" + qualification + '\'' +
                ", majorId='" + majorId + '\'' +
                ", major='" + major + '\'' +
                ", majorField='" + majorField + '\'' +
                ", majorDivision='" + majorDivision + '\'' +
                ", trainingModeId=" + trainingModeId +
                ", trainingMode='" + trainingMode + '\'' +
                ", weipeiInsitution='" + weipeiInsitution + '\'' +
                ", nativePlaceId=" + nativePlaceId +
                ", nativePlace='" + nativePlace + '\'' +
                ", fromProvince='" + fromProvince + '\'' +
                ", fromPlace='" + fromPlace + '\'' +
                ", educationMod='" + educationMod + '\'' +
                ", stuLength=" + stuLength +
                ", registTime='" + registTime + '\'' +
                ", graduationTime='" + graduationTime + '\'' +
                ", difficultyTypeId=" + difficultyTypeId +
                ", difficultyType='" + difficultyType + '\'' +
                ", grade=" + grade +
                ", normalStuId=" + normalStuId +
                ", normalStu='" + normalStu + '\'' +
                ", registStatus='" + registStatus + '\'' +
                ", whereaboutgoId=" + whereaboutgoId +
                ", whereaboutgo='" + whereaboutgo + '\'' +
                ", institutionId='" + institutionId + '\'' +
                ", institutionName='" + institutionName + '\'' +
                ", insModeId=" + insModeId +
                ", insFieldId=" + insFieldId +
                ", insField='" + insField + '\'' +
                ", insPlaceId=" + insPlaceId +
                ", insPlace='" + insPlace + '\'' +
                ", jobId=" + jobId +
                ", jobType='" + jobType + '\'' +
                ", reportModeId=" + reportModeId +
                ", reportMode='" + reportMode + '\'' +
                ", reportIns='" + reportIns + '\'' +
                ", desInsId=" + desInsId +
                ", goPlace='" + goPlace + '\'' +
                ", goPlaceType='" + goPlaceType + '\'' +
                ", goArea='" + goArea + '\'' +
                ", goProvince='" + goProvince + '\'' +
                ", desIns='" + desIns + '\'' +
                ", reportNumId='" + reportNumId + '\'' +
                ", reportStartTime='" + reportStartTime + '\'' +
                ", verifyStatus='" + verifyStatus + '\'' +
                ", wherewantgo='" + wherewantgo + '\'' +
                ", wherewantgoIns='" + wherewantgoIns + '\'' +
                ", workDifficultyMode='" + workDifficultyMode + '\'' +
                ", counts='" + counts + '\'' +
                '}';
    }

    // get 与 set方法

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getGoArea() {
        return goArea;
    }

    public void setGoArea(String goArea) {
        this.goArea = goArea;
    }

    public String getGoProvince() {
        return goProvince;
    }

    public void setGoProvince(String goProvince) {
        this.goProvince = goProvince;
    }

    public String getGoPlace() {
        return goPlace;
    }

    public void setGoPlace(String goPlace) {
        this.goPlace = goPlace;
    }

    public String getGoPlaceType() {
        return goPlaceType;
    }

    public void setGoPlaceType(String goPlaceType) {
        this.goPlaceType = goPlaceType;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getFromProvince() {
        return fromProvince;
    }

    public void setFromProvince(String fromProvince) {
        this.fromProvince = fromProvince;
    }

    public String getGraduatQualification() {
        if( graduatQualification == null ) return "" ;
        return graduatQualification;
    }

    public void setGraduatQualification(String graduatQualification) {
        this.graduatQualification = graduatQualification;
    }

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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
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

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
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

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(Integer aClass) {
        studentClass = aClass;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMajorField() {
        return majorField;
    }

    public void setMajorField(String majorField) {
        this.majorField = majorField;
    }

    public Integer getTrainingModeId() {
        return trainingModeId;
    }

    public void setTrainingModeId(Integer trainingModeId) {
        this.trainingModeId = trainingModeId;
    }

    public String getTrainingMode() {
        return trainingMode;
    }

    public void setTrainingMode(String trainingMode) {
        this.trainingMode = trainingMode;
    }

    public String getWeipeiInsitution() {
        return weipeiInsitution;
    }

    public void setWeipeiInsitution(String weipeiInsitution) {
        this.weipeiInsitution = weipeiInsitution;
    }

    public Integer getNativePlaceId() {
        return nativePlaceId;
    }

    public void setNativePlaceId(Integer nativePlaceId) {
        this.nativePlaceId = nativePlaceId;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getEducationMod() {
        return educationMod;
    }

    public void setEducationMod(String educationMod) {
        this.educationMod = educationMod;
    }

    public float getStuLength() {
        return stuLength;
    }

    public void setStuLength(float stuLength) {
        this.stuLength = stuLength;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public Integer getDifficultyTypeId() {
        return difficultyTypeId;
    }

    public void setDifficultyTypeId(Integer difficultyTypeId) {
        this.difficultyTypeId = difficultyTypeId;
    }

    public String getDifficultyType() {
        return difficultyType;
    }

    public void setDifficultyType(String difficultyType) {
        this.difficultyType = difficultyType;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
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

    public String getRegistStatus() {
        return registStatus;
    }

    public void setRegistStatus(String registStatus) {
        this.registStatus = registStatus;
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

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
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

    public Integer getInsPlaceId() {
        return insPlaceId;
    }

    public void setInsPlaceId(Integer insPlaceId) {
        this.insPlaceId = insPlaceId;
    }

    public String getInsPlace() {
        return insPlace;
    }

    public void setInsPlace(String insPlace) {
        this.insPlace = insPlace;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getReportMode() {
        return reportMode;
    }

    public void setReportMode(String reportMode) {
        this.reportMode = reportMode;
    }

    public String getReportIns() {
        return reportIns;
    }

    public void setReportIns(String reportIns) {
        this.reportIns = reportIns;
    }

    public Integer getDesInsId() {
        return desInsId;
    }

    public void setDesInsId(Integer desInsId) {
        this.desInsId = desInsId;
    }

    public String getDesIns() {
        return desIns;
    }

    public void setDesIns(String desIns) {
        this.desIns = desIns;
    }

    public String getReportNumId() {
        return reportNumId;
    }

    public void setReportNumId(String reportNumId) {
        this.reportNumId = reportNumId;
    }

    public String getReportStartTime() {
        return reportStartTime;
    }

    public void setReportStartTime(String reportStartTime) {
        this.reportStartTime = reportStartTime;
    }

    public Integer getReportModeId() {
        return reportModeId;
    }

    public void setReportModeId(Integer reportModeId) {
        this.reportModeId = reportModeId;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
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

    public String getWorkDifficultyMode() {
        return workDifficultyMode;
    }

    public void setWorkDifficultyMode(String workDifficultyMode) {
        this.workDifficultyMode = workDifficultyMode;
    }

    public String getMajorDivision() {
        return majorDivision;
    }

    public void setMajorDivision(String majorDevision) {
        this.majorDivision = majorDivision;
    }

    public Integer getDocumentIn() {
        return documentIn;
    }

    public void setDocumentIn(Integer documentIn) {
        this.documentIn = documentIn;
    }

    public Integer getHukouIn() {
        return hukouIn;
    }

    public void setHukouIn(Integer hukouIn) {
        this.hukouIn = hukouIn;
    }

    public String getDocIns() {
        return docIns;
    }

    public void setDocIns(String docIns) {
        this.docIns = docIns;
    }

    public String getHukouIns() {
        return hukouIns;
    }

    public void setHukouIns(String hukouIns) {
        this.hukouIns = hukouIns;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getCheckinStatus() {
        return checkinStatus;
    }

    public void setCheckinStatus(String checkinStatus) {
        this.checkinStatus = checkinStatus;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getStudentClassification() {
        return studentClassification;
    }

    public void setStudentClassification(String studentClassification) {
        this.studentClassification = studentClassification;
    }

    public Integer getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(Integer agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public String getNativePlaceRemind() {
        return nativePlaceRemind;
    }

    public void setNativePlaceRemind(String nativePlaceRemind) {
        this.nativePlaceRemind = nativePlaceRemind;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
