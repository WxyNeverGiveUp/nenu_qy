package com.pandawork.nenu.oa.common.entity.dispatch;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * DispatchExtendItem
 * 派遣信息扩展项
 * Created by ZhangBiLai on 2017/7/17.
 */

@Entity
@Table(name = "t_dispatch_extend_item")
public class DispatchExtendItem extends AbstractEntity {

    //主键唯一标识
    @Id
    private Integer id;

    //派遣学生ID
    @Column(name = "dispatch_id")
    private Integer dispatchId;

    //有无定向解约材料
    @Column(name = "orientation_cancel_contract")
    private String orientationCancelContract;

    //有无免师解约材料
    @Column(name = "free_normal_cancel_contract")
    private String freeNormalCancelContract;

    //有无免师跨省材料
    @Column(name = "free_normal_trans_provincial")
    private String freeNormalTransProvincial;

    //有无进京函
    @Column(name = "enter_beijing")
    private String enterBeijing;

    //有无进津函
    @Column(name = "enter_tianjin")
    private String enterTianjin;

    //有无进沪函
    @Column(name = "enter_shanghai")
    private String enterShanghai;

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

    public Integer getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(Integer dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getOrientationCancelContract() {
        return orientationCancelContract;
    }

    public void setOrientationCancelContract(String orientationCancelContract) {
        this.orientationCancelContract = orientationCancelContract;
    }

    public String getFreeNormalCancelContract() {
        return freeNormalCancelContract;
    }

    public void setFreeNormalCancelContract(String freeNormalCancelContract) {
        this.freeNormalCancelContract = freeNormalCancelContract;
    }

    public String getFreeNormalTransProvincial() {
        return freeNormalTransProvincial;
    }

    public void setFreeNormalTransProvincial(String freeNormalTransProvincial) {
        this.freeNormalTransProvincial = freeNormalTransProvincial;
    }

    public String getEnterBeijing() {
        return enterBeijing;
    }

    public void setEnterBeijing(String enterBeijing) {
        this.enterBeijing = enterBeijing;
    }

    public String getEnterTianjin() {
        return enterTianjin;
    }

    public void setEnterTianjin(String enterTianjin) {
        this.enterTianjin = enterTianjin;
    }

    public String getEnterShanghai() {
        return enterShanghai;
    }

    public void setEnterShanghai(String enterShanghai) {
        this.enterShanghai = enterShanghai;
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
