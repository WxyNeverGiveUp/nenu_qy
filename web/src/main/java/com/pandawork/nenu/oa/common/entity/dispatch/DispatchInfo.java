package com.pandawork.nenu.oa.common.entity.dispatch;

import com.pandawork.core.common.entity.AbstractEntity;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by qiuxiao on 2016/7/12.
 */
@Entity
@Table(name="t_dispatch_info")
public class DispatchInfo extends AbstractEntity {

    //主键唯一标识
    @Id
    private Integer id;

    //学籍信息ID
    @Column(name = "stu_status_info_id")
    private Integer statusInfoId;

    //考生号
    @Column(name = "exam_number")
    private Integer examNumber;

    //协议编码
    @Column(name = "agreement_number")
    private String agreementNumber;

    //是否是当前协议(1-是,0-否)
    @Column(name = "current_agreement")
    private Integer currentAgreement;

    //毕业去向代码
    @Column(name = "whereaboutgo_id")
    private Integer whereaboutgoId;

    //毕业去向
    @Transient
    private String whereaboutgo;

    //签约单位名称
    @Column(name = "company_name")
    private String companyName;

    //单位组织机构代码
    @Column(name = "organization_code")
    private String organizationCode;

    //单位所在地代码
    @Column(name = "city_id")
    private Integer cityId;

    //单位所在地
    @Transient
    private String cityName;

    //单位性质代码
    @Column(name = "property_id")
    private Integer propertyId;

    //单位性质
    @Transient
    private String propertyName;

    //单位所属行业代码
    @Column(name = "trade_id")
    private Integer tradeId;

    //单位所属行业
    @Transient
    private String tradeName;

    //单位隶属部门
    @Column(name = "subordinate_department")
    private String subordinateDepartment;

    //工作职位类别代码
    @Column(name = "job_id")
    private Integer jobId;

    //工作职位类别
    @Transient
    private String job;

    //单位详细地址
    @Column(name = "full_address")
    private String fullAddress;

    //单位邮编
    @Column(name = "company_postcode")
    private String companyPostcode;

    //单位联系人
    @Column(name = "company_contact_person")
    private String companyContactPerson;

    //单位联系人传真
    @Column(name = "contact_person_fax")
    private String contactPersonFax;

    //单位联系人电话
    @Column(name = "contact_person_tele")
    private String contactPersonTele;

    //单位联系人手机号
    @Column(name = "contact_person_mobile")
    private String contactPersonMobile;

    //单位联系人邮箱号
    @Column(name = "contact_person_email")
    private String contactPersonEmail;

    //报到证类别代码
    @Column(name = "report_mode_id")
    private Integer reportModeId;

    //报到证类别
    @Transient
    private String reportMode;

    //报到证迁往单位
    @Column(name = "report_company")
    private String reportCompany;

    //报到证迁往单位地址代码
    @Column(name = "report_company_address")
    private Integer reportCompanyAddress;

    //报到证迁往单位地址
    @Transient
    private String reportAddressName;

    //是否接受档案代码，1是0否
    @Column(name = "accept_file")
    private Integer acceptFile;

    //档案转寄单位邮编，默认“，”
    @Column(name = "file_company_postcode")
    private String fileCompanyPostcode;

    //档案转寄单位名称
    @Column(name = "file_company")
    private String fileCompany;

    //档案转寄单位地址代码
    @Column(name = "file_company_address")
    private Integer fileCompanyAddress;

    //档案转寄单位地址
    @Transient
    @Column(name = "file_address_name")
    private String fileAddressName;

    //接收档案部门
    @Column(name = "accept_file_department")
    private String acceptFileDepartment;

    //接收档案部门
    @Column(name = "material_id")
    private String materialId;

    //接收档案联系人
    @Column(name = "accept_file_person")
    private String acceptFilePerson;

    //接收档案电话
    @Column(name = "accept_file_tele")
    private String acceptFileTele;

    //是否接受户口代码，1是0否
    @Column(name = "accept_hukou")
    private Integer acceptHukou;

    //审核状态 默认2，1-已保存未提交，2-未审核，3-辅导员审核未通过待修改，4-辅导员审核未通过已修改，5-辅导员审核通过，6-副书记审核未通过待修改，7-副书记审核未通过已修改，8-副书记审核通过，9-学校审核未通过待修改，10-学校审核未通过已修改，11-学校审核通过，12-学校直接审核未通过待修改，13-学校直接审核未通过已修改，14-学校直接审核通过
    @Column(name = "check_status")
    private Integer checkStatus;

    // 默认1，辅导员审核结果,1—未审核，2—审核通过，3—审核未通过待修改，4—审核未通过已修改
    @Column(name = "counsellor_check_result")
    private Integer counsellorCheckResult;

    //辅导员审核理由
    @Column(name = "counsellor_check_reason")
    private String counsellorCheckReason;

    //辅导员审核操作者
    @Column(name = "counsellor_check_operator")
    private String counsellorCheckOperator;

    //辅导员审核时间
    @Column(name = "counsellor_check_time")
    private Date counsellorCheckTime;

    //默认1，副书记审核结果,1—未审核，2—审核通过，3—审核未通过待修改，4—审核未通过已修改
    @Column(name = "deputy_secretary_check_result")
    private Integer deputySecretaryCheckResult;

    //副书记审核理由
    @Column(name = "deputy_secretary_check_reason")
    private String deputySecretaryCheckReason;

    //副书记审核操作者
    @Column(name = "deputy_secretary_check_operator")
    private String deputySecretaryCheckOperator;

    //副书记审核时间
    @Column(name = "deputy_secretary_check_time")
    private Date deputySecretaryCheckTime;

    //默认1，学校审核结果,1—未审核，2—审核通过，3—审核未通过待修改，4—审核未通过已修改
    @Column(name = "school_check_result")
    private Integer schoolCheckResult;

    //学校审核理由
    @Column(name = "school_check_reason")
    private String schoolCheckReason;

    //学校审核操作者
    @Column(name = "school_check_operator")
    private String schoolCheckOperator;

    //学校审核时间
    @Column(name = "school_check_time")
    private Date schoolCheckTime;

    //户口迁移地址
    @Column(name = "hukou_transfer_address")
    private String hukouTransferAddress;

    //是否确认提交(1是0否)
    @Column(name = "confirmed")
    private Integer confirmed;

    //创建时间
    @Column(name = "created_time")
    private Date createdTime;

    //最后修改时间
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    //20170717
    //学生派遣备注
    @Column(name = "stu_remark")
    private String stuRemark;

    //20170728
    //是否删除（假删）
    @Column(name = "is_deleted")
    private Integer isDeleted;

    public DispatchInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamNumber() {
        return examNumber;
    }

    public void setExamNumber(Integer examNumber) {
        this.examNumber = examNumber;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public Integer getWhereaboutgoId() {
        return whereaboutgoId;
    }

    public void setWhereaboutgoId(Integer whereaboutgoId) {
        this.whereaboutgoId = whereaboutgoId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
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

    public Integer getReportCompanyAddress() {
        return reportCompanyAddress;
    }

    public void setReportCompanyAddress(Integer reportCompanyAddress) {
        this.reportCompanyAddress = reportCompanyAddress;
    }

    public Integer getAcceptFile() {
        return acceptFile;
    }

    public void setAcceptFile(Integer acceptFile) {
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

    public Integer getAcceptHukou() {
        return acceptHukou;
    }

    public void setAcceptHukou(Integer acceptHukou) {
        this.acceptHukou = acceptHukou;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getCounsellorCheckResult() {
        return counsellorCheckResult;
    }

    public void setCounsellorCheckResult(Integer counsellorCheckResult) {
        this.counsellorCheckResult = counsellorCheckResult;
    }

    public String getCounsellorCheckReason() {
        return counsellorCheckReason;
    }

    public void setCounsellorCheckReason(String counsellorCheckReason) {
        this.counsellorCheckReason = counsellorCheckReason;
    }

    public String getCounsellorCheckOperator() {
        return counsellorCheckOperator;
    }

    public void setCounsellorCheckOperator(String counsellorCheckOperator) {
        this.counsellorCheckOperator = counsellorCheckOperator;
    }

    public Date getCounsellorCheckTime() {
        return counsellorCheckTime;
    }

    public void setCounsellorCheckTime(Date counsellorCheckTime) {
        this.counsellorCheckTime = counsellorCheckTime;
    }

    public Integer getDeputySecretaryCheckResult() {
        return deputySecretaryCheckResult;
    }

    public void setDeputySecretaryCheckResult(Integer deputySecretaryCheckResult) {
        this.deputySecretaryCheckResult = deputySecretaryCheckResult;
    }

    public String getDeputySecretaryCheckReason() {
        return deputySecretaryCheckReason;
    }

    public void setDeputySecretaryCheckReason(String deputySecretaryCheckReason) {
        this.deputySecretaryCheckReason = deputySecretaryCheckReason;
    }

    public String getDeputySecretaryCheckOperator() {
        return deputySecretaryCheckOperator;
    }

    public void setDeputySecretaryCheckOperator(String deputySecretaryCheckOperator) {
        this.deputySecretaryCheckOperator = deputySecretaryCheckOperator;
    }

    public Date getDeputySecretaryCheckTime() {
        return deputySecretaryCheckTime;
    }

    public void setDeputySecretaryCheckTime(Date deputySecretaryCheckTime) {
        this.deputySecretaryCheckTime = deputySecretaryCheckTime;
    }

    public Integer getSchoolCheckResult() {
        return schoolCheckResult;
    }

    public void setSchoolCheckResult(Integer schoolCheckResult) {
        this.schoolCheckResult = schoolCheckResult;
    }

    public String getSchoolCheckReason() {
        return schoolCheckReason;
    }

    public void setSchoolCheckReason(String schoolCheckReason) {
        this.schoolCheckReason = schoolCheckReason;
    }

    public String getSchoolCheckOperator() {
        return schoolCheckOperator;
    }

    public void setSchoolCheckOperator(String schoolCheckOperator) {
        this.schoolCheckOperator = schoolCheckOperator;
    }

    public Date getSchoolCheckTime() {
        return schoolCheckTime;
    }

    public void setSchoolCheckTime(Date schoolCheckTime) {
        this.schoolCheckTime = schoolCheckTime;
    }

    public Integer getCurrentAgreement() {
        return currentAgreement;
    }

    public void setCurrentAgreement(Integer currentAgreement) {
        this.currentAgreement = currentAgreement;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getWhereaboutgo() {
        return whereaboutgo;
    }

    public void setWhereaboutgo(String whereaboutgo) {
        this.whereaboutgo = whereaboutgo;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getReportMode() {
        return reportMode;
    }

    public void setReportMode(String reportMode) {
        this.reportMode = reportMode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getReportAddressName() {
        return reportAddressName;
    }

    public void setReportAddressName(String reportAddressName) {
        this.reportAddressName = reportAddressName;
    }

    public String getFileAddressName() {
        return fileAddressName;
    }

    public void setFileAddressName(String fileAddressName) {
        this.fileAddressName = fileAddressName;
    }

    public String getReportCompany() {
        return reportCompany;
    }

    public void setReportCompany(String reportCompany) {
        this.reportCompany = reportCompany;
    }

    public String getHukouTransferAddress() {
        return hukouTransferAddress;
    }

    public void setHukouTransferAddress(String hukouTransferAddress) {
        this.hukouTransferAddress = hukouTransferAddress;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getStatusInfoId() {
        return statusInfoId;
    }

    public void setStatusInfoId(Integer statusInfoId) {
        this.statusInfoId = statusInfoId;
    }

    public String getStuRemark() {
        return stuRemark;
    }

    public void setStuRemark(String stuRemark) {
        this.stuRemark = stuRemark;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "DispatchInfo{" +
                "id=" + id +
                ", statusInfoId=" + statusInfoId +
                ", examNumber=" + examNumber +
                ", agreementNumber='" + agreementNumber + '\'' +
                ", currentAgreement=" + currentAgreement +
                ", whereaboutgoId=" + whereaboutgoId +
                ", whereaboutgo='" + whereaboutgo + '\'' +
                ", companyName='" + companyName + '\'' +
                ", organizationCode=" + organizationCode +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", propertyId=" + propertyId +
                ", propertyName='" + propertyName + '\'' +
                ", tradeId=" + tradeId +
                ", tradeName='" + tradeName + '\'' +
                ", subordinateDepartment='" + subordinateDepartment + '\'' +
                ", jobId=" + jobId +
                ", job='" + job + '\'' +
                ", fullAddress='" + fullAddress + '\'' +
                ", companyPostcode='" + companyPostcode + '\'' +
                ", companyContactPerson='" + companyContactPerson + '\'' +
                ", contactPersonFax='" + contactPersonFax + '\'' +
                ", contactPersonTele='" + contactPersonTele + '\'' +
                ", contactPersonMobile='" + contactPersonMobile + '\'' +
                ", contactPersonEmail='" + contactPersonEmail + '\'' +
                ", reportModeId=" + reportModeId +
                ", reportMode='" + reportMode + '\'' +
                ", reportCompany='" + reportCompany + '\'' +
                ", reportCompanyAddress=" + reportCompanyAddress +
                ", reportAddressName='" + reportAddressName + '\'' +
                ", acceptFile=" + acceptFile +
                ", fileCompanyPostcode='" + fileCompanyPostcode + '\'' +
                ", fileCompany='" + fileCompany + '\'' +
                ", fileCompanyAddress=" + fileCompanyAddress +
                ", fileAddressName='" + fileAddressName + '\'' +
                ", acceptFileDepartment='" + acceptFileDepartment + '\'' +
                ", materialId='" + materialId + '\'' +
                ", acceptFilePerson='" + acceptFilePerson + '\'' +
                ", acceptFileTele='" + acceptFileTele + '\'' +
                ", acceptHukou=" + acceptHukou +
                ", checkStatus=" + checkStatus +
                ", counsellorCheckResult=" + counsellorCheckResult +
                ", counsellorCheckReason='" + counsellorCheckReason + '\'' +
                ", counsellorCheckOperator='" + counsellorCheckOperator + '\'' +
                ", counsellorCheckTime=" + counsellorCheckTime +
                ", deputySecretaryCheckResult=" + deputySecretaryCheckResult +
                ", deputySecretaryCheckReason='" + deputySecretaryCheckReason + '\'' +
                ", deputySecretaryCheckOperator='" + deputySecretaryCheckOperator + '\'' +
                ", deputySecretaryCheckTime=" + deputySecretaryCheckTime +
                ", schoolCheckResult=" + schoolCheckResult +
                ", schoolCheckReason='" + schoolCheckReason + '\'' +
                ", schoolCheckOperator='" + schoolCheckOperator + '\'' +
                ", schoolCheckTime=" + schoolCheckTime +
                ", hukouTransferAddress='" + hukouTransferAddress + '\'' +
                ", stuRemark=" + stuRemark + '\'' +
                ", confirmed=" + confirmed +
                ", createdTime=" + createdTime +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}