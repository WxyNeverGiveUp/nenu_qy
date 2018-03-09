package com.pandawork.nenu.oa.common.entity.company;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * user: lishicao
 * date 15/12/2
 * time 上午12:14
 */
@Entity
@Table(name="t_company")
public class Company  extends AbstractEntity {

    private static final long serialVersionUID = -8575430874526458480L;
    //唯一标识
    @Id
    private Integer id;
    //单位名称
    @Column(name="company_name")
    private String companyName;
    //录入时间
    @Column(name="entering_time")
    private String enteringTime;
    //所属行业id
    @Column(name="trade_id")
    private Integer tradeId;
    //单位地点id
    @Column(name="city_id")
    private Integer cityId;
    //详细地址
    @Column(name="full_address")
    private String fullAddress;
    //单位电话
    @Column(name="phone")
    private String phone;
    //单位类型id
    @Column(name="type_id")
    private String typeId;
    //单位性质id
    @Column(name="property_id")
    private Integer propertyId;
    //邮政编码
    @Column(name="postal_code")
    private String postalCode;
    //单位邮箱
    @Column(name="E_mail")
    private String email;
    //走访日期
    @Column(name="interview_date")
    private String interviewDate;
    //走访人姓名
    @Column(name="interviewer")
    private String interviewer;

    //公司名称拼音首字母
    @Column(name="spell_initial")
    private String spellInitial;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEnteringTime() {
        return enteringTime;
    }

    public void setEnteringTime(String enteringTime) {
        this.enteringTime = enteringTime;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getSpellInitial() {
        return spellInitial;
    }

    public void setSpellInitial(String spellInitial) {
        this.spellInitial = spellInitial;
    }
}

