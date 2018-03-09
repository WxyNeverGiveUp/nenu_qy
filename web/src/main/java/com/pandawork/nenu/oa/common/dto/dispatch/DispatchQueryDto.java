package com.pandawork.nenu.oa.common.dto.dispatch;

import com.pandawork.nenu.oa.common.entity.dispatch.DispatchInfo;

/**
 * User qiuxiao
 * Date ${Date}
 * User ${Time}
 */
public class DispatchQueryDto extends DispatchInfo {
    //列表页面所需数据部分在子类中有 开始
    //查询所需要的一些属性
    private Integer pSize; //每页数量
    private Integer pCurrent; //当前页数
    private Integer stuType; //学生类型
    private Integer depsOrMajors; //学院
    private Integer provinceId;//生源所在省
    private Integer trainingModeCode;//培养方式
    private String keyWord;//关键字
    private Integer qualificationNow; //在读学历
    private String nation; //民族代码
    private Integer showUncommitted; //是否显示未提交学生
    private Integer sex;//性别
    private Integer politicalStand;//政治面貌
    private Integer reportModeId;//报到证签发类别
    private Integer provinceInId;//就业所在省
    private String idNumber;//身份证号
    private String candidateNumber;//考生号
    private String stuLength;//学制

    //列表页中所需显示的其他一些数据行
    private String name;//学生姓名
    private String studentNumber;//学号
    private String major;//专业
    private Integer majorId;//专业Id
    private Integer userId;//userId
    private Integer normalStuId;//师范生类别代码
    private String qualificationId;//学历代码
    private Integer rowCount;//总行数

    //列表页面所需数据部分在子类中有 结束


    private String collegeCode;//学院
    private Integer previous;//上一个派遣信息id
    private Integer next;//下一个派遣信息id
    private Integer maxId;//当前存在的最大id
    private Integer minId;//当前存在的最大id


    //getdetail中需要的
    private String reportMode;
    private String propertyName;
    private String tradeName;
    private Integer dispatchId;
    private String freeTeacherProvince; //免师跨省

    //报到证前往单位名称 SJZ加
    private String reportCompany;
    private String reportAddressName;
    private String cityName;
    private String fileCompanyAddressName;

    private String job;

    private String whereaboutgo;

    @Override
    public String getReportAddressName() {
        return reportAddressName;
    }

    @Override
    public void setReportAddressName(String reportAddressName) {
        this.reportAddressName = reportAddressName;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getFileCompanyAddressName() {
        return fileCompanyAddressName;
    }

    public void setFileCompanyAddressName(String fileCompanyAddressName) {
        this.fileCompanyAddressName = fileCompanyAddressName;
    }

    @Override
    public String getJob() {
        return job;
    }

    @Override
    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String getTradeName() {
        return tradeName;
    }

    @Override
    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    @Override
    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getpSize() {
        return pSize;
    }

    public void setpSize(Integer pSize) {
        this.pSize = pSize;
    }

    public Integer getpCurrent() {
        return pCurrent;
    }

    public void setpCurrent(Integer pCurrent) {
        this.pCurrent = pCurrent;
    }

    public Integer getStuType() {
        return stuType;
    }

    public Integer getTrainingModeCode() {
        return trainingModeCode;
    }

    public void setTrainingModeCode(Integer trainingModeCode) {
        this.trainingModeCode = trainingModeCode;
    }

    public void setStuType(Integer stuType) {

        this.stuType = stuType;
    }

    public Integer getDepsOrMajors() {
        return depsOrMajors;
    }

    public void setDepsOrMajors(Integer depsOrMajors) {
        this.depsOrMajors = depsOrMajors;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getProvinceInId() {
        return provinceInId;
    }

    public void setProvinceInId(Integer provinceInId) {
        this.provinceInId = provinceInId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(String candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNormalStuId() {
        return normalStuId;
    }

    public void setNormalStuId(Integer normalStuId) {
        this.normalStuId = normalStuId;
    }

    public String getQualificationId() {
        if (qualificationId != null && !qualificationId.equals("*")) {
            return qualificationId;
        } else {
            return null;
        }
    }

    public void setQualificationId(String qualificationId) {
        if (qualificationId != null && !qualificationId.equals("*")) {
            this.qualificationId = qualificationId;
        } else {
            this.qualificationId = qualificationId;
        }
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public Integer getPrevious() {
        return previous;
    }

    public void setPrevious(Integer previous) {
        this.previous = previous;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public Integer getMaxId() {
        return maxId;
    }

    public void setMaxId(Integer maxId) {
        this.maxId = maxId;
    }

    public Integer getMinId() {
        return minId;
    }

    public void setMinId(Integer minId) {
        this.minId = minId;
    }

    public String getWhereaboutgo() {
        return whereaboutgo;
    }

    public void setWhereaboutgo(String whereaboutgo) {
        this.whereaboutgo = whereaboutgo;
    }

    public String getReportMode() {
        return reportMode;
    }

    public void setReportMode(String reportMode) {
        this.reportMode = reportMode;
    }

    public Integer getQualificationNow() {
        return qualificationNow;
    }

    public void setQualificationNow(Integer qualificationNow) {
        this.qualificationNow = qualificationNow;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getShowUncommitted() {
        return showUncommitted;
    }

    public void setShowUncommitted(Integer showUncommitted) {
        this.showUncommitted = showUncommitted;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getPoliticalStand() {
        return politicalStand;
    }

    public void setPoliticalStand(Integer politicalStand) {
        this.politicalStand = politicalStand;
    }

    @Override
    public Integer getReportModeId() {
        return reportModeId;
    }

    @Override
    public void setReportModeId(Integer reportModeId) {
        this.reportModeId = reportModeId;
    }

    public String getFreeTeacherProvince() {
        return freeTeacherProvince;
    }

    public void setFreeTeacherProvince(String freeTeacherProvince) {
        this.freeTeacherProvince = freeTeacherProvince;
    }
    @Override
    public String getReportCompany() {
        return reportCompany;
    }

    @Override
    public void setReportCompany(String reportCompany) {
        this.reportCompany = reportCompany;
    }

    public String getStuLength() {
        return stuLength;
    }

    public void setStuLength(String stuLength) {
        this.stuLength = stuLength;
    }

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }

    @Override
    public String toString() {
        return "DispatchQueryDto{" +
                "pSize=" + pSize +
                ", pCurrent=" + pCurrent +
                ", stuType='" + stuType + '\'' +
                ", depsOrMajors='" + depsOrMajors + '\'' +
                ", provinceId=" + provinceId +
                ", devType=" + trainingModeCode +
                ", keyWord='" + keyWord + '\'' +
                ", name='" + name + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", idNumber='" + idNumber +'\'' +
                ", candidateNumber='" + candidateNumber +'\'' +
                ", major='" + major + '\'' +
                ", majorId=" + majorId +
                ", userId=" + userId +
                ", normalStuId=" + normalStuId +
                ", qualificationId=" + qualificationId +
                ", rowCount=" + rowCount +
                ", collegeCode='" + collegeCode + '\'' +
                ", previous=" + previous +
                ", next=" + next +
                ", maxId=" + maxId +
                ", minId=" + minId +
                ", whereaboutgo='" + whereaboutgo + '\'' +
                ", reportMode='" + reportMode + '\'' +
                ", reportModeId='" + reportModeId + '\'' +
                '}' + super.toString();
    }
}
