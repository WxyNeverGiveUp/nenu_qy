package com.pandawork.nenu.oa.common.entity.dispatch;

import com.pandawork.core.common.entity.AbstractEntity;
import com.pandawork.core.common.util.Assert;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Comments
 * 办公信息实体
 *
 * @author wlm
 * @date 2016/9/21 16:39
 */

@Entity
@Table(name = "t_comments")
public class Comments extends AbstractEntity {
    //办公信息id
    @Id
    private Integer id;

    //学籍id
    @Column(name = "status_info_id")
    private Integer statusInfoId;

    //领取协议，0-否，1-是
    @Column(name = "receive_agreement")
    private Integer receiveAgreement;

    //返回协议，0-否，1-是
    @Column(name = "return_agreement")
    private Integer returnAgreement;

    //已就业，0-否，1-是
    @Column(name = "already_employed")
    private Integer alreadyEmployed;

    //免师解约，0-材料不全，1-材料齐全
    @Column(name = "free_nor_release")
    private Integer freeNorRelease;

    //免师跨省，0-材料不全，1-材料齐全
    @Column(name = "free_nor_trans_provincial")
    private Integer freeNorTransProvincial;

    //定向解约，0-材料不全，1-材料齐全
    @Column(name = "directional_release")
    private Integer directionalRelease;

    //春季派遣
    @Column(name = "spring_dispatch")
    private Date springDispatch;

    @Transient
    private String springDispatchDate;

    //进京沪深函，0-材料不全，1-材料齐全
    @Column(name = "beijing_letter")
    private Integer beijingLetter;

    //用人单位同意派遣至生源地函，0-材料不全，1-材料齐全
    @Column(name = "agree_to_origin_place")
    private Integer agreeToOriginPlace;

    //三方解约，0-材料不全，1-材料齐全
    @Column(name = "three_party_release")
    private Integer threePartyRelease;

    //协议丢失或污损，0-材料不全，1-材料齐全
    @Column(name = "protocol_lost_stained")
    private Integer protocolLostStained;

    //系统上传，0-未上传，1-已上传
    @Column(name = "system_upload")
    private Integer systemUpload;

    //资格审查漏报，0-材料不全，1-材料齐全
    @Column(name = "qualification_examination_missing")
    private Integer qualificationExaminationMissing;

    //协议领错，0-材料不全，1-材料齐全
    @Column(name = "protocol_get_wrong")
    private Integer protocolGetWrong;

    //创建时间
    @Column(name = "created_time")
    private Date createdTime;

    //最后修改时间
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;


    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatusInfoId() {
        return statusInfoId;
    }

    public void setStatusInfoId(Integer statusInfoId) {
        this.statusInfoId = statusInfoId;
    }

    public Integer getReceiveAgreement() {
        return receiveAgreement;
    }

    public void setReceiveAgreement(Integer receiveAgreement) {
        this.receiveAgreement = receiveAgreement;
    }

    public Integer getReturnAgreement() {
        return returnAgreement;
    }

    public void setReturnAgreement(Integer returnAgreement) {
        this.returnAgreement = returnAgreement;
    }

    public Integer getAlreadyEmployed() {
        return alreadyEmployed;
    }

    public void setAlreadyEmployed(Integer alreadyEmployed) {
        this.alreadyEmployed = alreadyEmployed;
    }

    public Integer getFreeNorRelease() {
        return freeNorRelease;
    }

    public void setFreeNorRelease(Integer freeNorRelease) {
        this.freeNorRelease = freeNorRelease;
    }

    public Integer getFreeNorTransProvincial() {
        return freeNorTransProvincial;
    }

    public void setFreeNorTransProvincial(Integer freeNorTransProvincial) {
        this.freeNorTransProvincial = freeNorTransProvincial;
    }

    public Integer getDirectionalRelease() {
        return directionalRelease;
    }

    public void setDirectionalRelease(Integer directionalRelease) {
        this.directionalRelease = directionalRelease;
    }

    public Date getSpringDispatch() {
        return springDispatch;
    }

    public void setSpringDispatch(Date springDispatch) {
        this.springDispatch = springDispatch;
    }

    public String getSpringDispatchDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (Assert.isNotNull(springDispatch)) {
            return sdf.format(springDispatch);
        } else {
            return null;
        }
    }

    public void setSpringDispatchDate(String springDispatchDate) {
        this.springDispatchDate = springDispatchDate;
    }

    public Integer getBeijingLetter() {
        return beijingLetter;
    }

    public void setBeijingLetter(Integer beijingLetter) {
        this.beijingLetter = beijingLetter;
    }

    public Integer getAgreeToOriginPlace() {
        return agreeToOriginPlace;
    }

    public void setAgreeToOriginPlace(Integer agreeToOriginPlace) {
        this.agreeToOriginPlace = agreeToOriginPlace;
    }

    public Integer getThreePartyRelease() {
        return threePartyRelease;
    }

    public void setThreePartyRelease(Integer threePartyRelease) {
        this.threePartyRelease = threePartyRelease;
    }

    public Integer getProtocolLostStained() {
        return protocolLostStained;
    }

    public void setProtocolLostStained(Integer protocolLostStained) {
        this.protocolLostStained = protocolLostStained;
    }

    public Integer getSystemUpload() {
        return systemUpload;
    }

    public void setSystemUpload(Integer systemUpload) {
        this.systemUpload = systemUpload;
    }

    public Integer getQualificationExaminationMissing() {
        return qualificationExaminationMissing;
    }

    public void setQualificationExaminationMissing(Integer qualificationExaminationMissing) {
        this.qualificationExaminationMissing = qualificationExaminationMissing;
    }

    public Integer getProtocolGetWrong() {
        return protocolGetWrong;
    }

    public void setProtocolGetWrong(Integer protocolGetWrong) {
        this.protocolGetWrong = protocolGetWrong;
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


}
