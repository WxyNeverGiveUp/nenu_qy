package com.pandawork.nenu.oa.common.entity.general;

import com.pandawork.core.common.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * description:材料表实体类
 * user: lusi
 * date:2016/7/12
 * time:11:01
 */
@Entity
@Table (name = "t_material")
public class Material extends AbstractEntity {

    //材料表唯一标识
    @Id
    private Integer id;

    //学籍Id
    @Column(name = "status_info_id")
    private Integer statusInfoId;

    //协议Id
    @Column(name = "protocol_id")
    private Integer protocolId;

    //派遣Id
    @Column(name = "dispatch_info_id")
    private Integer dispatchInfoId;

    //材料名称
    @Column(name = "material_name")
    private String materialName;

    //材料地址
    @Column(name = "material_url")
    private String materialUrl;

    //材料类型：1-学籍，2-派遣，3-身份变更，4-申领新协议,5-毕业去向变更协议，6-免费师范生业务，7-定向、委培业务
    @Column(name = "material_type")
    private Integer materialType;

    //是否已删除：0-否,1-是
    @Column(name = "is_deleted")
    private Integer isDeleted;

    //创建时间
    @Column(name = "created_time")
    private Date createdTime;

    //最后修改时间
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    public Integer getId() {
        return id;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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

    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    public Integer getDispatchInfoId() {
        return dispatchInfoId;
    }

    public void setDispatchInfoId(Integer dispatchInfoId) {
        this.dispatchInfoId = dispatchInfoId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialUrl() {
        return materialUrl;
    }

    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
    }

    public Integer getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
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
