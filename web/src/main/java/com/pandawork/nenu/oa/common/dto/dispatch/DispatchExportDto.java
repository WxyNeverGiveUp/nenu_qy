package com.pandawork.nenu.oa.common.dto.dispatch;

/**
 * DispatchExportDto
 *
 * @author wlm
 * @date 2017/1/3 11:08
 */
public class DispatchExportDto {

    //派遣信息id
    private Integer id;

    //学籍信息ID
    private Integer statusInfoId;

    //学生姓名
    private String name;

    //身份证号
    private String idNumber;

    //考生号
    private String candidateNumber;

    //学号
    private String studentNumber;

    //性别
    private String sex;

    //协议编码
    private String agreementNumber;

    //是否是当前协议(1-是,0-否)
    private String currentAgreement;

    //毕业去向代码
    private Integer whereaboutgoId;

    //毕业去向
    private String whereaboutgo;

    //签约单位名称
    private String companyName;

    //单位组织结构代码
    private Integer organizationCode;

    //单位所在地代码
    private Integer cityId;

    //单位所在地
    private String cityName;

    //单位性质代码
    private Integer propertyId;

    //单位性质
    private String propertyName;

    //单位所属行业代码
    private Integer tradeId;

    //单位所属行业
    private String tradeName;

    //单位隶属部门
    private String subordinateDepartment;

    //工作职位类别代码
    private Integer jobId;

    //工作职位类别
    private String job;

    //单位详细地址
    private String fullAddress;

    //单位邮编
    private String companyPostcode;

    //单位联系人
    private String companyContactPerson;

    //单位联系人传真
    private String contactPersonFax;

    //单位联系人电话
    private String contactPersonTele;

    //单位联系人手机号
    private String contactPersonMobile;

    //单位联系人邮箱号
    private String contactPersonEmail;

    //报到证类别代码
    private Integer reportModeId;

    //报到证类别
    private String reportMode;

    //报到证迁往单位
    private String reportCompany;

    //报到证迁往单位地址代码
    private Integer reportCompanyAddress;

    //报到证迁往单位地址
    private String reportAddressName;

    //是否接受档案代码，1是0否
    private String acceptFile;

    //档案转寄单位邮编，默认“，”
    private String fileCompanyPostcode;

    //档案转寄单位名称
    private String fileCompany;

    //档案转寄单位地址代码
    private Integer fileCompanyAddress;

    //档案转寄单位地址
    private String fileAddressName;

    //接收档案部门
    private String acceptFileDepartment;

    //接收档案联系人
    private String acceptFilePerson;

    //接收档案电话
    private String acceptFileTele;

    //是否接受户口代码，1是0否
    private String acceptHukou;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatusInfoId() {
        return statusInfoId;
    }

    public void setStatusInfoId(Integer statusInfoId) {
        this.statusInfoId = statusInfoId;
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

    public String getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(String candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public String getCurrentAgreement() {
        return currentAgreement;
    }

    public void setCurrentAgreement(String currentAgreement) {
        this.currentAgreement = currentAgreement;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(Integer organizationCode) {
        this.organizationCode = organizationCode;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getSubordinateDepartment() {
        return subordinateDepartment;
    }

    public void setSubordinateDepartment(String subordinateDepartment) {
        this.subordinateDepartment = subordinateDepartment;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getCompanyPostcode() {
        return companyPostcode;
    }

    public void setCompanyPostcode(String companyPostcode) {
        this.companyPostcode = companyPostcode;
    }

    public String getCompanyContactPerson() {
        return companyContactPerson;
    }

    public void setCompanyContactPerson(String companyContactPerson) {
        this.companyContactPerson = companyContactPerson;
    }

    public String getContactPersonFax() {
        return contactPersonFax;
    }

    public void setContactPersonFax(String contactPersonFax) {
        this.contactPersonFax = contactPersonFax;
    }

    public String getContactPersonTele() {
        return contactPersonTele;
    }

    public void setContactPersonTele(String contactPersonTele) {
        this.contactPersonTele = contactPersonTele;
    }

    public String getContactPersonMobile() {
        return contactPersonMobile;
    }

    public void setContactPersonMobile(String contactPersonMobile) {
        this.contactPersonMobile = contactPersonMobile;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public Integer getReportModeId() {
        return reportModeId;
    }

    public void setReportModeId(Integer reportModeId) {
        this.reportModeId = reportModeId;
    }

    public String getReportMode() {
        return reportMode;
    }

    public void setReportMode(String reportMode) {
        this.reportMode = reportMode;
    }

    public String getReportCompany() {
        return reportCompany;
    }

    public void setReportCompany(String reportCompany) {
        this.reportCompany = reportCompany;
    }

    public Integer getReportCompanyAddress() {
        return reportCompanyAddress;
    }

    public void setReportCompanyAddress(Integer reportCompanyAddress) {
        this.reportCompanyAddress = reportCompanyAddress;
    }

    public String getReportAddressName() {
        return reportAddressName;
    }

    public void setReportAddressName(String reportAddressName) {
        this.reportAddressName = reportAddressName;
    }

    public String getAcceptFile() {
        return acceptFile;
    }

    public void setAcceptFile(String acceptFile) {
        this.acceptFile = acceptFile;
    }

    public String getFileCompanyPostcode() {
        return fileCompanyPostcode;
    }

    public void setFileCompanyPostcode(String fileCompanyPostcode) {
        this.fileCompanyPostcode = fileCompanyPostcode;
    }

    public String getFileCompany() {
        return fileCompany;
    }

    public void setFileCompany(String fileCompany) {
        this.fileCompany = fileCompany;
    }

    public Integer getFileCompanyAddress() {
        return fileCompanyAddress;
    }

    public void setFileCompanyAddress(Integer fileCompanyAddress) {
        this.fileCompanyAddress = fileCompanyAddress;
    }

    public String getFileAddressName() {
        return fileAddressName;
    }

    public void setFileAddressName(String fileAddressName) {
        this.fileAddressName = fileAddressName;
    }

    public String getAcceptFileDepartment() {
        return acceptFileDepartment;
    }

    public void setAcceptFileDepartment(String acceptFileDepartment) {
        this.acceptFileDepartment = acceptFileDepartment;
    }

    public String getAcceptFilePerson() {
        return acceptFilePerson;
    }

    public void setAcceptFilePerson(String acceptFilePerson) {
        this.acceptFilePerson = acceptFilePerson;
    }

    public String getAcceptFileTele() {
        return acceptFileTele;
    }

    public void setAcceptFileTele(String acceptFileTele) {
        this.acceptFileTele = acceptFileTele;
    }

    public String getAcceptHukou() {
        return acceptHukou;
    }

    public void setAcceptHukou(String acceptHukou) {
        this.acceptHukou = acceptHukou;
    }
}
